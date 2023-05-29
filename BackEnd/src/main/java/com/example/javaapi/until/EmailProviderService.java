package com.example.javaapi.until;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
public class EmailProviderService {
//    public  void sendMail(String emailContact, String emailSubject, String body) {

//        String fromEmail ="";
//        String password ="";
//        Properties property = new Properties();
//        property.put("mail.smtp.auth", "true");
//        property.put("mail.smtp.starttls.enable", "true");
//        property.put("mail.smtp.host", "smtp.gmail.com");
//        property.put("mail.smtp.port", "587");
//
//        AU auth = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, password);
//            }
//        };
//
//
//        Session session = Session.getInstance(property, auth);
//
//        //send(session, fromEmail, emailContact, emailSubject, body);
//
//
//        sendHtml(session, fromEmail, emailContact, emailSubject, body);
//    }// end of send mail
//
//    private void send(Session session, String fromEmail, String emailContact, String emailSubject, String body) {
//        // TODO Auto-generated method stub
//        try {
//
//            MimeMessage mimeMessage = new MimeMessage(session);
//
//            mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
//
//            mimeMessage.addHeader("format", "flowed");
//
//            mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
//
//            mimeMessage.setFrom(new InternetAddress(fromEmail, "Bookstore"));
//
//            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailContact));
//
//            mimeMessage.setReplyTo(InternetAddress.parse(" u cannot sandeep.rayala14@gmial.com",false));
//
//            mimeMessage.setSubject(emailSubject);
//
//            mimeMessage.setText(body);
//
//            Transport.send(mimeMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("exception occured while sending mail");
//
//        }
//    }

    //for html content


//    private void sendHtml(Session session, String fromEmail, String emailContact, String emailSubject, String body) {
//        // TODO Auto-generated method stub
//        try {
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
//
//            mimeMessage.addHeader("format", "flowed");
//
//            mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
//
//            mimeMessage.setFrom(new InternetAddress(fromEmail, "Bookstore"));
//
//            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailContact));
//
//
//
//
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
//            helper.setTo(emailContact);
//            helper.setSubject(emailSubject);
//            helper.setText(body, true);
//
//            Transport.send(mimeMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("exception occured while sending mail");
//
//        }
//    }

}
