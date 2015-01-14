package test;

//http://stackoverflow.com/questions/3649014/send-email-using-java

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail_v3 {

	public SendEmail_v3(String receiver, String subject, String body) {
		Boolean result = mailSend(receiver, subject, body);
		if(result){
			System.out.println("Mail has been sent");
		}else {System.out.println("Error sending email");}
	}

	public boolean mailSend(String receiver, String subject, String body) {

		final String username = "ladiyafofee@gmail.com";
		final String password = "hellolilo";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(
					"Whatever you want to put here eg. your info name email etc"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(receiver));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

			return true;

		} catch (MessagingException e) {
			return false;
		}

	}
	
	public static void main(String[] args) {
		new SendEmail_v3("laddaSingh921@gmail.com", "Hello", "this is a demo mesage");
	}

}