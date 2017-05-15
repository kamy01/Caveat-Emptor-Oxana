package services.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import constants.EmailConstants;
import exception.CaveatEmptorException;
import model.UserDto;

public class SendEmailService {

	public static String sendEmail(UserDto user) throws CaveatEmptorException {

		try {
			
			Properties properties = getProperties();

			Session session = createSession(properties);

			String key = UUID.randomUUID().toString();

			Message message = buildMessage(session, properties, user, key);

			Transport.send(message);

			return key;

		} catch (MessagingException | IOException e) {

			throw new CaveatEmptorException();

		}

	}

	private static Message buildMessage(Session session, Properties properties, UserDto user, String key)
			throws MessagingException {

		String link = EmailConstants.MAIL_REGISTRATION_SITE_LINK.getValue() + "?scope=activation" + "&key=" + key;

		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(properties.getProperty("username")));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
		message.setSubject("Account activation");
		message.setText(
				EmailConstants.MAIL_HELLO_MSG.getValue() + ", " + user.getFirstName() + " " + user.getLastName() + "," + EmailConstants.MAIL_NEW_LINE.getValue()
						+ EmailConstants.MAIL_SUBJECT.getValue() + EmailConstants.MAIL_NEW_LINE.getValue() + link + EmailConstants.MAIL_NEW_LINE.getValue() + EmailConstants.MAIL_SENDER.getValue());

		return message;
	}

	private static Session createSession(Properties properties) {

		return Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty("username"),
						properties.getProperty("password"));
			}
		});

	}

	private static Properties getProperties() throws IOException {

		Properties properties = new Properties();
		String filename = "config.properties";

		try (InputStream input = SendEmailService.class.getClassLoader().getResourceAsStream(filename)) {

			properties.load(input);

		}

		return properties;

	}

}
