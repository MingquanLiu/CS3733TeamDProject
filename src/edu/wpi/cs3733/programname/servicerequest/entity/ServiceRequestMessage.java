package edu.wpi.cs3733.programname.servicerequest.entity;

import com.sun.mail.smtp.SMTPMessage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

public class ServiceRequestMessage {
    private String from;
    private String recipient;
    private String host;
    private ServiceRequest request;

    public ServiceRequestMessage(String from, String recipient, ServiceRequest request) {
        this.from = from;
        this.recipient = recipient;
        host = "localhost";
        this.request = request;
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
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(request.getType() + " Request at location");
            message.setText("Service Request Description: \n\t" + request.getDescription());
            Transport.send(message);
            System.out.println("Message sent successfully");
        }
        catch(MessagingException me) {
            me.printStackTrace();
        }
    }
}
