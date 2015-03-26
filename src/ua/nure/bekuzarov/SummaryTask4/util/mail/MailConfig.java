package ua.nure.bekuzarov.SummaryTask4.util.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.exception.FileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Stores mail configuration.
 */
public final class MailConfig {

    private static final String FILE = "/mail.properties";
    private static final Logger LOGGER = LoggerFactory.getLogger(MailConfig.class);
    private static final String EMAIL;
    private static final String PASSWORD;
    private static final String HOST;
    private static final int PORT;
    private static final String SOCKET_FACTORY_CLASS;
    private static final int SOCKET_FACTORY_PORT;

    static {
        try (InputStream resource = MailConfig.class.getResourceAsStream(FILE)) {
            Properties prop = new Properties();
            prop.load(resource);
            EMAIL = prop.getProperty("mail.address");
            PASSWORD = prop.getProperty("mail.password");
            HOST = prop.getProperty("mail.host");
            PORT = Integer.parseInt(prop.getProperty("mail.port"));
            SOCKET_FACTORY_CLASS = prop.getProperty("mail.socketFactory.class");
            SOCKET_FACTORY_PORT = Integer.parseInt(prop.getProperty("mail.socketFactory.port"));
        } catch (IOException e) {
            LOGGER.error("Cannot load file: '{}'", FILE);
            throw new FileException("Cannot load file: '" + FILE + "'", e);
        }
    }

    public static String getEmail() {
        return EMAIL;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getHost() {
        return HOST;
    }

    public static int getPort() {
        return PORT;
    }

    public static String getSocketFactoryClass() {
        return SOCKET_FACTORY_CLASS;
    }

    public static int getSocketFactoryPort() {
        return SOCKET_FACTORY_PORT;
    }

    private MailConfig() {
    }

}
