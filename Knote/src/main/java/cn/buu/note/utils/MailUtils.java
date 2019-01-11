package cn.buu.note.utils;

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

import cn.buu.note.entity.UserDao;

/**
 * 发送邮件工具类
 * @author ABC
 *
 */
public class MailUtils {
	
	public static void sendMail(String to,String code) throws AddressException, MessagingException {
	    //  String to = "542133879@qq.com";
	 
	      String from = "1587483947@qq.com";
	 
	      String host = "smtp.qq.com";  //QQ �ʼ�������
	  
	      Properties properties = System.getProperties();

	      properties.setProperty("mail.smtp.host", host);
	 
	      properties.put("mail.smtp.auth", "true");
	      Session session = Session.getDefaultInstance(properties,new Authenticator(){
	        public PasswordAuthentication getPasswordAuthentication()
	        {
	         return new PasswordAuthentication("1587483947@qq.com", "zgxfzfmnjgweihcc"); //�������ʼ��û�������Ȩ��
	        }
	       });
	 
	      try{
	         MimeMessage message = new MimeMessage(session);
	 
	         message.setFrom(new InternetAddress(from));
	 
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	 
	         message.setSubject("激活邮件0.0");
	         message.setContent("<a href='"+code+"'>"+code+"</a>","text/html;charset=UTF-8");
	       //  message.setText(code);
	 
	         Transport.send(message);
	         System.out.println("Sent message successfully....from runoob.com");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
	public static void main(String[] args) throws Exception {
		UserDao d =  new UserDao();
		String code = "Http://120.79.10.49:8888/Knote/login/activate?user="+SerializableUtils.serializable(d);
		sendMail("542133879@qq.com",code);
	}
	
}
