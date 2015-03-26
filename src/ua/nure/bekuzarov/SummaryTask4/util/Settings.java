package ua.nure.bekuzarov.SummaryTask4.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bekuzarov.SummaryTask4.exception.FileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Stores application configuration.
 */
public final class Settings {

    private static final String FILE = "/config.properties";
    private static final Logger LOGGER = LoggerFactory.getLogger(Settings.class);
    private static final int SUBSCRIPTION_DURATION;
    private static final int ORDER_DURATION;
    private static final int MAX_CHECKING_OUT;
    private static final int BOOKS_PER_PAGE;
    private static final float FEE;
    private static final String UPLOAD_DIRECTORY;
    private static final String UPLOAD_AUTHORS_DIRECTORY;
    private static final String UPLOAD_BOOKS_DIRECTORY;
    private static final String UPLOAD_PATH_MAPPING;
    private static final List<String> ALLOWED_IMAGE_TYPES;
    private static final String UPDATE_ORDERS_TASK_EXECUTION_TIME;

    static {
        try (InputStream resource = Settings.class.getResourceAsStream(FILE)) {
            Properties prop = new Properties();
            prop.load(resource);
            SUBSCRIPTION_DURATION = Integer.parseInt(prop.getProperty("subscription.defaultDuration"));
            ORDER_DURATION = Integer.parseInt(prop.getProperty("order.orderDuration"));
            MAX_CHECKING_OUT = Integer.parseInt(prop.getProperty("order.maxCheckingOut"));
            BOOKS_PER_PAGE = Integer.parseInt(prop.getProperty("search.booksPerPages"));
            FEE = Float.parseFloat(prop.getProperty("order.defaultFee"));
            UPLOAD_DIRECTORY = prop.getProperty("upload.dir");
            UPLOAD_AUTHORS_DIRECTORY = prop.getProperty("upload.dir.authors");
            UPLOAD_BOOKS_DIRECTORY = prop.getProperty("upload.dir.books");
            UPLOAD_PATH_MAPPING = prop.getProperty("upload.pathMapping");
            ALLOWED_IMAGE_TYPES = Arrays.asList(prop.getProperty("upload.image.allowedContentType").split(";"));
            UPDATE_ORDERS_TASK_EXECUTION_TIME = prop.getProperty("order.updateExecutionTime");
        } catch (IOException e) {
            LOGGER.error("Cannot load file: '{}'", FILE);
            throw new FileException("Cannot load file: '" + FILE + "'", e);
        }
    }

    public static int getSubscriptionDuration() {
        return SUBSCRIPTION_DURATION;
    }

    public static int getOrderDuration() {
        return ORDER_DURATION;
    }

    public static int getMaxCheckingOut() {
        return MAX_CHECKING_OUT;
    }

    public static int getBooksPerPage() {
        return BOOKS_PER_PAGE;
    }

    public static float getFee() {
        return FEE;
    }

    public static String getUploadDirectory() {
        return UPLOAD_DIRECTORY;
    }

    public static String getUploadAuthorsDirectory() {
        return UPLOAD_AUTHORS_DIRECTORY;
    }

    public static String getUploadBooksDirectory() {
        return UPLOAD_BOOKS_DIRECTORY;
    }

    public static String getUploadPathMapping() {
        return UPLOAD_PATH_MAPPING;
    }

    public static String getUpdateOrdersTaskExecutionTime() {
        return UPDATE_ORDERS_TASK_EXECUTION_TIME;
    }

    /**
     * Defines whether the system allows the {@code contentType} as image.
     *
     * @param contentType contentType to check
     * @return {@code true} if system allows the {@code contentType} as image, {@code false} otherwise
     */
    public static boolean isImage(String contentType) {
        return ALLOWED_IMAGE_TYPES.contains(contentType);
    }

    /**
     * Configuration for images of books.
     */
    public static final class Books {

        public static final String TEMP_DIRECTORY = "D://library";
        public static final int SIZE_THRESHOLD = 1024 * 1024;
        public static final int MAX_SIZE = 1024 * 1024 * 5;

        private Books() {
        }

    }

    /**
     * Configuration for images of authors.
     */
    public static final class Authors {

        public static final String TEMP_DIRECTORY = "D://library";
        public static final int SIZE_THRESHOLD = 1024 * 1024;
        public static final int MAX_SIZE = 1024 * 1024 * 5;

        private Authors() {

        }
    }

    private Settings() {
    }

}
