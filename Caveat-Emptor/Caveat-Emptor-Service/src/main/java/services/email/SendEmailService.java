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

import constants.Constant;
import model.UserDto;

public class SendEmailService {

	public static String sendEmail(UserDto user) {

		Properties properties = getProperties();

		Session session = createSession(properties);

		try {

			String key = UUID.randomUUID().toString();
			
			Message message = buildMessage(session, properties, user, key);

			try {
				
				Transport.send(message);

				return key;
				
			} catch (Exception e) {
				
				return null;
				
			}
			
		} catch (MessagingException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	private static Message buildMessage(Session session, Properties properties, UserDto user, String key) throws MessagingException{
		
		
		String link = Constant.MAIL_REGISTRATION_SITE_LINK + "?scope=activation"+ "&key=" + key;
		
		Message message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(properties.getProperty("username")));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
		message.setSubject("Account activation");
		message.setText(Constant.HELLO_MSG + ", " + user.getFirstName() + " " + user.getLastName() + ","
				+ Constant.NEW_LINE + Constant.MAIL_SUBJECT + Constant.NEW_LINE
				+ link
				+ Constant.NEW_LINE + Constant.MAIL_SENDER);
		
		return message;
	}
	
	private static Session createSession(Properties properties){
		
		return Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty("username"),
						properties.getProperty("password"));
			}
		});
		
	}

	private static Properties getProperties() {
		
		Properties properties = new Properties();
		InputStream input = null;

		try {

			String filename = "config.properties";
			
			input = SendEmailService.class.getClassLoader().getResourceAsStream(filename);

			properties.load(input);

		} catch (IOException ex) {
			
			ex.printStackTrace();
			
		} finally {
			
			if (input != null) {
				
				try {
					
					input.close();
					
				} catch (IOException e) {
					
					e.printStackTrace();
					
				}
			}
		}
		
		return properties;
		
	}

}
