package com.example.a2fa_10_dhjetor;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private String sourceEmail ="Get Your Gmail Addreess";
    private String tokenEmail ="Get Your GMAIL TOKEN";
    private String host="smtp.gmail.com";
    private String port="587";

    public void sendEmail(String destEmail,String otp) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port",port);


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sourceEmail,tokenEmail);
            }
        });

        Transport.send(message(session,destEmail,otp));

    }

    public Message message(Session session, String destEmail,String otp) throws MessagingException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sourceEmail));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(destEmail));
        message.setSubject("Your OTP Code is");
        message.setText(otp);

        return message;

    }


}
