package edu.wpi.cs3733.programname.pathfind.entity;

import com.sun.mail.smtp.SMTPMessage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

public class PathfindingMessage {

    private String to;
    private String body;

    public PathfindingMessage(String recipient, String body) {
        to = recipient;
        this.body = body;
    }

    public void sendMessage() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "805");

        Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cs3733teamD@gmail.com", "teamDteamD");
            }
        });
        try {
            SMTPMessage message = new SMTPMessage(session);
            message.setFrom(new InternetAddress("cs3733teamD@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("B&W Directions!");
            message.setText(body);
            Transport.send(message);
            System.out.println("Message sent successfully");
        }
        catch(MessagingException me) {
            me.printStackTrace();
        }
    }
}
