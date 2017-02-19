package com.exclusively.web.common;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Properties;

public class emailReport {

    public static void main(String[] args) throws UnsupportedEncodingException {

  emailReport example = new emailReport();

  example.sendEmail();

    }
    
    
    
    public void sendEmail() throws UnsupportedEncodingException{
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("pramod.kumar03@exclusively.com", "ah@2305208606");
                    }
                });

        try {

            String msgBody =("Kindly find the Attached Web Automation Report : PFA \n\n"
            		+"Sanity Scenarios Covered: \n\n"
            		+ "1. Placing the Order end to end\n\n\n"
            		+"Regards :\n"
            		+"Exclusively.com Automation Team"
            		);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pramod.kumar03@exclusively.com","Exclusively.com  QA Automation Team"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("pramod.kumar03@exclusively.com,goyal.ankur@exclusively.com,qa@exclusively.com,arjunkapoor@exclusively.com,jayant.yadav@exclusively.com,animesh@exclusively.com")
                    //InternetAddress.parse("pramod.kumar03@exclusively.com")
            		);
            message.setSubject("Web Automation Report!!");
            
            Multipart multipart = new MimeMultipart();

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            //String file = "D:\\Exclusively_Automation\\Exclusively_selenium_test\\test-output\\Exclusively_TestSuite";
            String file = "D:\\Exclusively_Automation\\Exclusively_selenium_test\\test-output\\Exclusively_TestSuite\\Build no-XXX test.html";
            String fileName = "Automation Report";
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            
            //message body
            MimeBodyPart messageText = new MimeBodyPart();     
            messageText.setText(msgBody);
            
            
            //adding 
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(messageText);

       
            message.setContent(multipart);


            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}