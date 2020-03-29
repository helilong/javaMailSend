package com.lx.JavaMail;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 使用qq邮箱发送邮件
 * @author 13947
 *
 */
public class demo {
	public static void main(String[] args) {
		//配置参数 创建属性对象
		Properties props=new Properties();
		//发送服务器需要身份验证
		props.put("mail.smtp.auth","true");
		//设置邮件服务器主机名
		props.put("mail.host","smtp.qq.com");
		//发送邮件协议名称
		props.put("mail.transport.protocol","smtp");
		//端口号
		props.put("mail.smtp.port","995");
		
		//QQ邮箱需要ssl加密（端口 ）
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		//创建邮箱会话对象
		Session session=Session.getInstance(props);
		
		//创建消息对象
		MimeMessage msg=new MimeMessage(session);
		
		
		try {
			//设置消息的标题 
			msg.setSubject("测试使用java发送QQ邮件的主题");
			
			//设置消息内容
			msg.setText("这是邮件的测试内容");
			
			//设置消息的发件人
			msg.setFrom(new InternetAddress("1394772492@qq.com"));
			
			//设置消息的接收人
			msg.setRecipient(RecipientType.TO,new InternetAddress("706757053@qq.com"));
			
			//创建发送消息的对象
			Transport ts=session.getTransport();
			
			//设置发送人的地址，发送人的账号和密码（邮箱传输密码）
			ts.connect("smtp.qq.com","1394772492@qq.com","");
			
			//发送消息
			ts.sendMessage(msg,msg.getAllRecipients());
			
			//关闭发送
			ts.close();
			
			System.out.println("发送成功");
			
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
