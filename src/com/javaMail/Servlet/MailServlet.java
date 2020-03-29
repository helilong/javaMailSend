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
		//���ı����ʽ
		request.setCharacterEncoding("utf-8");

		
		//��������
		String sendUser =request.getParameter("sendUser");
		System.out.println(sendUser);
		//��������
		String sendPwd=request.getParameter("sendPwd");
		System.out.println(sendPwd);
		//�ռ�����
		String recUser=request.getParameter("recUser");
		System.out.println(recUser);
		//����
		String subject=request.getParameter("subject");
		System.out.println(subject);
		//����
		String Filename=request.getParameter("Filename");
		System.out.println(Filename);
		//����
		String context=request.getParameter("context");
		System.out.println(context);
		
		/**
		 * ���䷢��
		 */
		
		//���ò��� �������Զ���
		Properties props=new Properties();
		//������������������õ�½��֤
		props.setProperty("mail.smtp.host","smtp.qq.com");
		props.put("mail.smtp.auth","true");
		
		//�����ʼ�Э������
		props.put("mail.transport.protocol","smtp");
		
		//�����Ự
		Session session=Session.getInstance(props);
		
		//������Ϣ����
		MimeMessage msg=new MimeMessage(session);
		
		
		try {
			//������
			msg.setFrom(new InternetAddress(sendUser));
			//�ռ���
			msg.setRecipient(RecipientType.TO,new InternetAddress(recUser));
			//����
			msg.setSubject(subject);
			//����
			msg.setText(context);
			
			//��Ӹ���
			Multipart mp=new MimeMultipart();
			
			MimeBodyPart bodyPart=new MimeBodyPart();
			
			DataSource ds=new FileDataSource(new File("C:\\Users\\13947\\Desktop\\"+Filename));
			
			DataHandler dh=new DataHandler(ds);
			
			bodyPart.setDataHandler(dh);
			
			//����
			//sun.misc.BASE64Encoder enc=new BASE64Encoder();
			//bodyPart.setFileName("=?GBK?B?"+enc.encode(new File("C:\\Users\\13947\\Desktop\\"+Filename).getName().getBytes())+"?=");
			
			mp.addBodyPart(bodyPart);
			
			msg.setContent(mp);
			msg.saveChanges();
			
			//�õ�������Ϣ�Ķ���
			Transport ts=session.getTransport();
			
			ts.connect("smtp.qq.com", sendUser,sendPwd);
			
			//���÷��ͷ���
			ts.sendMessage(msg, msg.getAllRecipients());
			ts.close();
			
			System.out.println("���ͳɹ�");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
