package com.lx.JavaMail;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * ʹ��qq���䷢���ʼ�
 * @author 13947
 *
 */
public class demo {
	public static void main(String[] args) {
		//���ò��� �������Զ���
		Properties props=new Properties();
		//���ͷ�������Ҫ�����֤
		props.put("mail.smtp.auth","true");
		//�����ʼ�������������
		props.put("mail.host","smtp.qq.com");
		//�����ʼ�Э������
		props.put("mail.transport.protocol","smtp");
		//�˿ں�
		props.put("mail.smtp.port","995");
		
		//QQ������Ҫssl���ܣ��˿� ��
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		//��������Ự����
		Session session=Session.getInstance(props);
		
		//������Ϣ����
		MimeMessage msg=new MimeMessage(session);
		
		
		try {
			//������Ϣ�ı��� 
			msg.setSubject("����ʹ��java����QQ�ʼ�������");
			
			//������Ϣ����
			msg.setText("�����ʼ��Ĳ�������");
			
			//������Ϣ�ķ�����
			msg.setFrom(new InternetAddress("1394772492@qq.com"));
			
			//������Ϣ�Ľ�����
			msg.setRecipient(RecipientType.TO,new InternetAddress("706757053@qq.com"));
			
			//����������Ϣ�Ķ���
			Transport ts=session.getTransport();
			
			//���÷����˵ĵ�ַ�������˵��˺ź����루���䴫�����룩
			ts.connect("smtp.qq.com","1394772492@qq.com","");
			
			//������Ϣ
			ts.sendMessage(msg,msg.getAllRecipients());
			
			//�رշ���
			ts.close();
			
			System.out.println("���ͳɹ�");
			
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
