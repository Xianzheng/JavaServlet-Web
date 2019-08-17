package cn.itcast.store.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	public static void main(String[] args) throws AddressException, MessagingException {
		MailUtils.sendMail("markfang@live.com", "abcdefg");
	}

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		

		  Properties props = new Properties();
		    props.put("mail.smtp.user","fangxianzheng1234@gmail.com");
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "465");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.socketFactory.port", "465");
		    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    props.put("mail.smtp.socketFactory.fallback", "false");
		   // SecurityManager security = System.getSecurityManager();

		    try{
		        Authenticator auth = new SMTPAuthenticator();
		        Session session = Session.getInstance(props, auth);


		        MimeMessage msg = new MimeMessage(session);
		        String url="http://localhost:8080/store_v5/UserServlet?method=active&code="+emailMsg;
				String content="<h1>please to click to activate</h1><h3><a href='"+url+"'>"+url+"</a></h3>";
				//璁剧疆閭欢鍐呭
				msg.setContent(content, "text/html;charset=utf-8");
		       
		        msg.setSubject("Regist Information");
		        msg.setFrom(new InternetAddress("fangxianzheng1234@gmail.com"));
		        msg.addRecipient(Message.RecipientType.TO,
		        new InternetAddress(email));
		        //BodyPart messageBodyPart = new MimeBodyPart();
		        System.out.println("Send Mail from : "+"fangxianzheng1234@gmail.com");
		        System.out.println("Connection Service is : "+"smtp.gmail.com");
		        System.out.println("Send Mail to : "+email);
		        System.out.println("Email is sending.....");
		        Transport.send(msg);
		        System.out.println("Message send Successfully:)"); }

		    catch (Exception mex){
		        mex.printStackTrace();}
		    


		    }
	
	
}
 class SMTPAuthenticator extends javax.mail.Authenticator{
    public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication("fangxianzheng1234@gmail.com", "fxyCEO9Qeq");
    }
}
