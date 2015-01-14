package test;

import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendApp {
  public static void send(String smtpHost, int smtpPort, String from, String to, String subject,
      String content) throws AddressException, MessagingException {

    java.util.Properties props = new java.util.Properties();
    props.put("mail.smtp.host", smtpHost);
    props.put("mail.smtp.port", "" + smtpPort);
    Session session = Session.getDefaultInstance(props, null);

    Message msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(from));
    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
    msg.setSubject(subject);
    msg.setText(content);

    Transport.send(msg);
  }

  public static void main(String[] args) throws Exception {
    send("hostname", 25, "j@s.com", "s@s.com", "re: dinner", "body");
  }
}
