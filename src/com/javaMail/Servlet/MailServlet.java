package com.javaMail.Servlet;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import sun.misc.BASE64Encoder;


/**
 * Servlet implementation class MailServlet
 */
@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//更改编码格式
		request.setCharacterEncoding("utf-8");

		
		//发送邮箱
		String sendUser =request.getParameter("sendUser");
		System.out.println(sendUser);
		//邮箱密码
		String sendPwd=request.getParameter("sendPwd");
		System.out.println(sendPwd);
		//收件邮箱
		String recUser=request.getParameter("recUser");
		System.out.println(recUser);
		//标题
		String subject=request.getParameter("subject");
		System.out.println(subject);
		//附件
		String Filename=request.getParameter("Filename");
		System.out.println(Filename);
		//内容
		String context=request.getParameter("context");
		System.out.println(context);
		
		/**
		 * 邮箱发送
		 */
		
		//配置参数 创建属性对象
		Properties props=new Properties();
		//设置邮箱服务器并启用登陆验证
		props.setProperty("mail.smtp.host","smtp.qq.com");
		props.put("mail.smtp.auth","true");
		
		//发送邮件协议名称
		props.put("mail.transport.protocol","smtp");
		
		//创建会话
		Session session=Session.getInstance(props);
		
		//创建消息对象
		MimeMessage msg=new MimeMessage(session);
		
		
		try {
			//发件人
			msg.setFrom(new InternetAddress(sendUser));
			//收件人
			msg.setRecipient(RecipientType.TO,new InternetAddress(recUser));
			//标题
			msg.setSubject(subject);
			//内容
			msg.setText(context);
			
			//添加附件
			Multipart mp=new MimeMultipart();
			
			MimeBodyPart bodyPart=new MimeBodyPart();
			
			DataSource ds=new FileDataSource(new File("C:\\Users\\13947\\Desktop\\"+Filename));
			
			DataHandler dh=new DataHandler(ds);
			
			bodyPart.setDataHandler(dh);
			
			//加密
			//sun.misc.BASE64Encoder enc=new BASE64Encoder();
			//bodyPart.setFileName("=?GBK?B?"+enc.encode(new File("C:\\Users\\13947\\Desktop\\"+Filename).getName().getBytes())+"?=");
			
			mp.addBodyPart(bodyPart);
			
			msg.setContent(mp);
			msg.saveChanges();
			
			//得到发送消息的对象
			Transport ts=session.getTransport();
			
			ts.connect("smtp.qq.com", sendUser,sendPwd);
			
			//调用发送方法
			ts.sendMessage(msg, msg.getAllRecipients());
			ts.close();
			
			System.out.println("发送成功");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
