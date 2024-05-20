/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ultility;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *  *
 *  * @author DELL String fromEmail = "tudo766@gmail.com"; String username =
 * "0faaaca094d676";//username mailtrap String password = "fhui cstc tayy
 * hxhj";//password mailtrap- gmail se la key khac
 *
 */
public class SendEmail {

    public static void sendEmail(String toEmail, String code) throws MessagingException, UnsupportedEncodingException {

        String subject = "Email verify";
        String body = "Your verify code is: " + code + ". Enter to the textbox to confirm";

        String fromEmail = "tudo766@gmail.com";
        String username = "0faaaca094d676";//username mailtrap
        String password = "fhui cstc tayy hxhj";//password mailtrap- gmail se la key khac

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host, mailtrap la mailtrap.io, gmail la gmail.com
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);//gmail la fromEmail, mailtrap la username
            }
        };
        try {
            Session session = Session.getInstance(props, auth);
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(fromEmail, "LMS System"));
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

}
