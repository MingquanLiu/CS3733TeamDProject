package edu.wpi.cs3733.programname.servicerequest.entity;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
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
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
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
