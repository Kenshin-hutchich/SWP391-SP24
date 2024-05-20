/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author KENSHIN
 */
public class MailService implements IMailService {

    private final String host;
    private final int port;
    private final boolean useSsl;
    private final String emailSender;
    private final String password;

    public MailService() {
        this.host = "smtp.gmail.com";
        this.port = 587;
        this.useSsl = true;
        this.emailSender = "tudo766@gmail.com";
        this.password = "fhui cstc tayy hxhj";
    }

    @Override
    public void SendConfirmationCode(String email, String code) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email verify";
        String body = "Your verify code is: " + code + ". Enter to the textbox to confirm";
        Properties props = new Properties();
        props.put("mail.smtp.host", host); //SMTP Host, mailtrap la mailtrap.io, gmail la gmail.com
        props.put("mail.smtp.port", String.valueOf(port)); //TLS Port
        props.put("mail.smtp.auth", String.valueOf(useSsl)); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailSender, password);//gmail la fromEmail, mailtrap la username
            }
        };
        try {
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(emailSender, "LMS System"));
            msg.setReplyTo(InternetAddress.parse(emailSender, false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            Transport.send(msg);

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void SendPassword(String email, String pasw) throws MessagingException, UnsupportedEncodingException {
        String subject = "An account using this email has been registered at EduChamp.com";
        String body = "You can use your email and attached password to login. Here's your password: " + pasw;
        Properties props = new Properties();
        props.put("mail.smtp.host", host); //SMTP Host, mailtrap la mailtrap.io, gmail la gmail.com
        props.put("mail.smtp.port", String.valueOf(port)); //TLS Port
        props.put("mail.smtp.auth", String.valueOf(useSsl)); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailSender, password);//gmail la fromEmail, mailtrap la username
            }
        };
        try {
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(emailSender, "LMS System"));
            msg.setReplyTo(InternetAddress.parse(emailSender, false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            Transport.send(msg);

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
