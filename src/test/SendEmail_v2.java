package test;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendEmail_v2 {
	public static void main(String[] args) {
		Properties props = new Properties();
	    Session session = Session.getDefaultInstance(props, null);

	    String msgBody = "This is your message body";

	    try {
	        Message msg = new MimeMessage(session);
	        msg.setFrom(new InternetAddress("pranish.stha03@gmail.com", "okbcsqshHSDJ003"));
	        
	        msg.addRecipient(Message.RecipientType.TO,
	                         new InternetAddress("pranish.shres@gmail.com", "Mr. User"));
	        msg.setSubject("Your Example.com account has been activated");
	        msg.setText(msgBody);
	        Transport.send(msg);

	    } catch (AddressException e) {
	        System.out.println("Address Exception : " +e.getMessage());
	    } catch (MessagingException e) {
	    	System.out.println("Messaging Exception : " + e.getMessage());
	    } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Unsuppported Encoding Exception : " +e.getMessage());
		}
	}
	
}
