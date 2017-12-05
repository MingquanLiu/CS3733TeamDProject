package edu.wpi.cs3733.programname.commondata.servicerequestdata;

import com.sun.mail.smtp.SMTPMessage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

public class ServiceRequestMessage {
    private String from;
    private String recipient;
    private String host;
    private String content;
    private ServiceRequest request;

    public ServiceRequestMessage(String recipient, String content) {
        this.recipient = recipient;
        this.content = content;
        host = "localhost";
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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("New Service Request");
            message.setText("Service Request Description:" + content);
            Transport.send(message);
            System.out.println("Message sent successfully");
        }
        catch(MessagingException me) {
            me.printStackTrace();
        }
    }
}
