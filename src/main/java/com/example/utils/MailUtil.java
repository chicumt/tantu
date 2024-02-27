package com.example.utils;

import com.example.properties.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
@Component
public class MailUtil {
    private  static EmailProperties emailProperties;
    @Autowired
    public  void setEmailProperties(EmailProperties emailProperties) {
        MailUtil.emailProperties=emailProperties;
    }
    public static void sendEmail(String to,String code){
        // 获取系统属性
        Properties properties = System.getProperties();
        System.out.println(emailProperties);
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", emailProperties.getHost());

        properties.put("mail.smtp.auth", "true");
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(emailProperties.getSendEmail(), emailProperties.getPassword()); //发件人邮件用户名、授权码
            }
        });

        // 设置debug模式便于调试:
        session.setDebug(true);

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(emailProperties.getSendEmail()));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject(EmailTemplate.topic,"UTF-8");

            // 设置消息体
            message.setText(EmailTemplate.vericodeHtml1+code+EmailTemplate.vericodeHtml2,"UTF-8","html");

            // 发送消息
            Transport.send(message);

            //发送完成后控制台打印输出
//            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


}
