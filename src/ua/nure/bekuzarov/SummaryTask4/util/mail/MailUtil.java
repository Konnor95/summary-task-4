package ua.nure.bekuzarov.SummaryTask4.util.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

/**
 * Util for sending emails.
 */
public final class MailUtil {

    private static final Session SESSION;
    private static final Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);

    static {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", MailConfig.getHost());
        props.put("mail.smtp.port", MailConfig.getPort());
        props.put("mail.smtp.socketFactory.port", MailConfig.getSocketFactoryPort());
        props.put("mail.smtp.socketFactory.class", MailConfig.getSocketFactoryClass());
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfig.getEmail(), MailConfig.getPassword());
            }
        };
        SESSION = Session.getDefaultInstance(props, authenticator);
    }

    /**
     * Gets template for the email as a {@link String}
     * @param filePath file path to template
     * @return template for the email as a {@link String}
     */
    public static String getTemplate(String filePath) {
        InputStream fis = MailUtil.class.getResourceAsStream(filePath);
        return new Scanner(fis, "UTF-8").useDelimiter("\\A").next();
    }

    /**
     * Sends an email.
     * @param from sender of the email
     * @param to recipient of email
     * @param title title of the email
     * @param content content of the email
     * @return {@code true} if email was sent, {@code false} otherwise
     */
    public static boolean send(String from, String to, String title, String content) {
        try {
            MimeMessage message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(title, "UTF-8");
            message.setContent(content, "text/html; charset=UTF-8");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            LOGGER.error("Mail was not sent. Exception: '{}'", e.getMessage());
            return false;
        }
    }

}
