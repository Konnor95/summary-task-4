package ua.nure.bekuzarov.SummaryTask4.util.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.annotation.Autowired;
import ua.nure.bekuzarov.SummaryTask4.annotation.Repository;
import ua.nure.bekuzarov.SummaryTask4.annotation.Serializer;
import ua.nure.bekuzarov.SummaryTask4.annotation.Service;
import ua.nure.bekuzarov.SummaryTask4.constant.Attributes;
import ua.nure.bekuzarov.SummaryTask4.db.TransactionHandler;
import ua.nure.bekuzarov.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bekuzarov.SummaryTask4.db.manager.ConnectionManager;
import ua.nure.bekuzarov.SummaryTask4.util.Settings;
import ua.nure.bekuzarov.SummaryTask4.util.file.FileServiceImpl;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads all beans, services and serializers.
 *
 * @author Dmitry Bekuzarov
 */
public class ContextLoader extends AbstractContextLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextLoader.class);
    private ServletContext servletContext;
    private ConnectionHolder connectionHolder;
    private ConnectionManager connectionManager;
    private Map<String, Object> beans = new HashMap<>();
    private Map<String, Object> services = new HashMap<>();
    private Map<String, Object> serializers = new HashMap<>();

    /**
     * Creates new {@code ContextLoader} object.
     *
     * @param context servlet context
     * @param holder  connection holder
     * @param manager connection manager
     */
    public ContextLoader(ServletContext context, ConnectionHolder holder, ConnectionManager manager) {
        this.servletContext = context;
        this.connectionHolder = holder;
        this.connectionManager = manager;
        context.setAttribute(Attributes.CONNECTION_MANAGER, manager);
        context.setAttribute(Attributes.FILE_SERVICE, new FileServiceImpl(Settings.getUploadDirectory()));
    }

    /**
     * Loads all beans from specified packages.
     *
     * @param packagesNames packages to load beans from
     */
    public void load(String... packagesNames) {
        try {
            loadBeans(packagesNames);
            beans.putAll(services);
            autowireBeans();
            manageServices();
            manageSerializers();
        } catch (ReflectiveOperationException e) {
            LOGGER.error("Cannot load bean(s). Cause", e);
        } catch (IOException e) {
            LOGGER.error("Cannot load resources. Cause", e);
        }
        LOGGER.info("All beans loaded successfully");
    }

    /**
     * Cleans up taken resources.
     *
     * @param context servlet context.
     */
    public void destroy(ServletContext context) {
        connectionManager = (ConnectionManager) context.getAttribute(Attributes.CONNECTION_MANAGER);
        connectionManager.shutdown();
    }

    @Override
    protected void loadBean(Class<?> c)
            throws ReflectiveOperationException {
        if (c.isAnnotationPresent(Repository.class)) {
            loadRepository(c);
            return;
        }
        if (c.isAnnotationPresent(Service.class)) {
            loadService(c);
            return;
        }
        Serializer annotation = c.getAnnotation(Serializer.class);
        if (annotation != null) {
            loadSerializer(annotation.value(), c);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadRepository(Class c) throws ReflectiveOperationException {
        Constructor<?> constructor = c.getConstructor(ConnectionHolder.class);
        Object o = constructor.newInstance(connectionHolder);
        beans.put(o.getClass().getName(), o);
    }

    private void loadService(Class c) throws IllegalAccessException, InstantiationException {
        services.put(c.getName(), c.newInstance());
    }

    private void loadSerializer(String prefix, Class c) throws IllegalAccessException, InstantiationException {
        serializers.put(prefix, c.newInstance());
    }

    private void autowireBeans() throws IllegalAccessException {
        for (Object bean : beans.values()) {
            for (Field field : bean.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object existingBean = beans.get(field.getType().getName());
                    if (existingBean != null) {
                        field.set(bean, existingBean);
                    }
                }
            }
        }
    }

    private void manageServices() {
        for (Map.Entry<String, Object> entry : services.entrySet()) {
            Object service = entry.getValue();
            Object proxy = Proxy.newProxyInstance(
                    this.getClass().getClassLoader(),
                    service.getClass().getInterfaces(),
                    new TransactionHandler(connectionManager, connectionHolder, service)
            );
            servletContext.setAttribute(entry.getKey(), proxy);
        }
    }

    private void manageSerializers() {
        servletContext.setAttribute(Attributes.SERIALIZERS, serializers);
    }

}
