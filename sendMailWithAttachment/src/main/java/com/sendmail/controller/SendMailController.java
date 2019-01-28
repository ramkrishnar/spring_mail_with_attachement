package com.sendmail.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class SendMailController {
	 @Autowired
	    private JavaMailSender sender;
	
/*	 @GetMapping(value="/")
	 public ModelAndView welcome(){
		 String msg="Send Mail";
		 ModelAndView mv=new ModelAndView("welcome");
		 msg="mail send successfully";
		 mv.setViewName("welcome");
		 mv.addObject("msg",msg);
		 return mv;
	 }
	 
	
	 @RequestMapping("/sendMail")
	    public ModelAndView sendMail(HttpServletRequest req) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
		 ModelAndView mv=new ModelAndView("welcome");
		 String too=req.getParameter("to");
		 String sub=req.getParameter("sub");
		 String mesg=req.getParameter("msg");
		
		 int num=Integer.parseInt(req.getParameter("num"));
		 String[] to=too.split(",");
		 String msg="";
	
	   
	        MimeMessage message = sender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message,true);

	        try {
	        	Velocity.init();

	             lets make a Context and put data into it 

	            VelocityContext context = new VelocityContext();

	            context.put("name", "Velocity");
	            context.put("project", "Jakarta");

	             lets render a template 

	            StringWriter w = new StringWriter();

	            Velocity.mergeTemplate("src/main/resources/templates/test.vm", context, w );
	            
	           
	            System.out.println("----------->"+w.toString());
        	 String meg=w.toString();
	        	
	            helper.setTo(to);
	            helper.setText(meg);
	            helper.setSubject(sub);
	            List<String> attachments=new ArrayList<String>();
	            
	          for(int i=1;i<num;i++){
	        	  MultipartFile fileName1 = req.getpart;
	            	 File file2 = new File(req.getServletContext().getRealPath("/")+fileName1);
	            	attachments.add(file2.getAbsolutePath());
	            	System.out.println("file path "+i +"--->"+file2.getAbsolutePath());
	            }
	            byte[] bytes = file.getBytes();
	            FileOutputStream fout=new FileOutputStream(new File(UPLOADED_FOLDER + file.getOriginalFilename()));
	            fout.write( bytes);
	            attachments.add("E://primse.txt");
	            attachments.add("E://primse.txt");
	            Iterator it = attachments.iterator();
	            while (it.hasNext()) {
	            FileSystemResource file1 
	            = new FileSystemResource(new File((String) it.next()));
	           // helper.addAttachment(file1.getFilename(), file1);
	            }
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            msg= "Error while sending mail...";
	        }
	        sender.send(message);
	        msg= "Mail Sent Success!";
	        mv.setViewName("welcome");
	        mv.addObject("msg",msg);
	        return mv;
	    }
	 @RequestMapping("/sendMailWithAttachment")
	    public ModelAndView sendMailWithAttachment(HttpServletRequest req,@RequestParam("file") MultipartFile file) throws MessagingException, ServletException, IOException {
		 ModelAndView mv=new ModelAndView("welcome");
		 String too=req.getParameter("to");
		 String sub=req.getParameter("sub");
		 String mesg=req.getParameter("msg");
		 
		
		 String[] to=too.split(",");
		 String msg="";
	


	        MimeMessage message = sender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message,true);

	        try {
	        	
	            helper.setTo(to);
	            helper.setText(mesg);
	            helper.setSubject(sub);
	            List<String> attachments=new ArrayList<String>();
	            
	         
	            if (!file.isEmpty()) {
	            	System.out.println("dffffffff"+file.getOriginalFilename());
	    			try {
	    				byte[] bytes = file.getBytes();

	    				// Creating the directory to store file
	    				String rootPath = System.getProperty("catalina.home");
	    				File dir = new File(rootPath + File.separator + "tmpFiles");
	    				if (!dir.exists())
	    					dir.mkdirs();

	    				// Create the file on server
	    				File serverFile = new File(dir.getAbsolutePath()
	    						+ File.separator + file.getOriginalFilename());
	    				BufferedOutputStream stream = new BufferedOutputStream(
	    						new FileOutputStream(serverFile));
	    				System.out.println("dffffffff"+serverFile);
	    				attachments.add(serverFile.getAbsolutePath());
	    				stream.write(bytes);
	    				stream.close();
	    				System.out.println("dffffffff"+serverFile);
	    				
	    			
	    			} catch (Exception e) {
	    				
	    			}
	    		} else {
	    			
	    		}
	           
	           // attachments.add("E://primse.txt");
	            Iterator it = attachments.iterator();
	            while (it.hasNext()) {
	            FileSystemResource file1 
	            = new FileSystemResource(new File((String) it.next()));
	            helper.addAttachment(file1.getFilename(), file1);
	            }
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            msg= "Error while sending mail...";
	        }
	        sender.send(message);
	        msg= "Mail Sent Success!";
	        mv.setViewName("welcome");
	        mv.addObject("msg",msg);
	        return mv;
	    }
	 @RequestMapping("/sendMailWithMoreAttachment")
	    public ModelAndView sendMailWithMoreAttachment(HttpServletRequest req,@RequestParam("file") MultipartFile[] files) throws MessagingException, ServletException, IOException {
		 ModelAndView mv=new ModelAndView("welcome");
		 String too=req.getParameter("to");
		 String sub=req.getParameter("sub");
		 String mesg=req.getParameter("msg");
		 VelocityEngine velocity = new VelocityEngine();
		 velocity.init();
	       VelocityContext context = new VelocityContext();
	       context.put("hello", "hello");
	       context.put("ram", "ram");
		 String[] to=too.split(",");
		 String msg="";
	


	        MimeMessage message = sender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message,true);

	        try {
	        	String content="${hello}"+"${ram}";
	        	 StringWriter w = new StringWriter();
	        	  Velocity.evaluate( context, w, "mystring", content );
	            helper.setTo(to);
	            helper.setText(mesg);
	            helper.setSubject(sub);
	            
	            List<String> attachments=new ArrayList<String>();
	            
	         
	            if (files.length!=0) {
	            	for (int i = 0; i < files.length; i++) {
	        			MultipartFile file = files[i];
	        		
	        		
	        				byte[] bytes = file.getBytes();

	        				// Creating the directory to store file
	        				String rootPath = System.getProperty("catalina.home");
	        				File dir = new File(rootPath + File.separator + "tmpFiles");
	        				if (!dir.exists())
	        					dir.mkdirs();

	        				// Create the file on server
	        				File serverFile = new File(dir.getAbsolutePath()
	        						+ File.separator + file.getOriginalFilename()	);
	        				BufferedOutputStream stream = new BufferedOutputStream(
	        						new FileOutputStream(serverFile));
	        				stream.write(bytes);
	        				attachments.add(serverFile.getAbsolutePath());
	        				stream.close();
	        				

	            	}
	        			
	            	}
	           // attachments.add("E://primse.txt");
	            Iterator it = attachments.iterator();
	            while (it.hasNext()) {
	            FileSystemResource file1 
	            = new FileSystemResource(new File((String) it.next()));
	            helper.addAttachment(file1.getFilename(), file1);
	            }
	            	} catch (MessagingException e) {
	            e.printStackTrace();
	            msg= "Error while sending mail...";
	        }
	        sender.send(message);
	        msg= "Mail Sent Success!";
	        mv.setViewName("welcome");
	        mv.addObject("msg",msg);
	        return mv;
	    }*/
	 @PostMapping("/sendMailvelocity")
	    public void sendMailvelocity(HttpServletRequest req) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
		
		 String too="ramakrishnaecec7@gmail.com";
		 String sub="subject";
		 String mesg=req.getParameter("msg");
		 String cc="apmrv05@gmail.com";
		

		 String[] to=too.split(",");
		 String msg="";
	
	   
	        MimeMessage message = sender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message,true);
	    	Velocity.init();

            /* lets make a Context and put data into it */

            VelocityContext context = new VelocityContext();

            context.put("name", "ram");
            context.put("project", "email notification");

            /* lets render a template */

            StringWriter w = new StringWriter();

            Velocity.mergeTemplate("src/main/resources/templates/test.vm", context, w );
            
           
            System.out.println("----------->"+w.toString());
    	 String meg=w.toString();
    	 message.setContent(meg,"text/html");

	        try {
	        	
	            helper.setTo(to);
	            helper.setText(meg);
	            helper.setSubject(sub);
	            helper.setCc(cc);
	            List<String> attachments=new ArrayList<String>();
	            
	          /*for(int i=1;i<num;i++){
	        	  MultipartFile fileName1 = req.getpart;
	            	 File file2 = new File(req.getServletContext().getRealPath("/")+fileName1);
	            	attachments.add(file2.getAbsolutePath());
	            	System.out.println("file path "+i +"--->"+file2.getAbsolutePath());
	            }
	            byte[] bytes = file.getBytes();
	            FileOutputStream fout=new FileOutputStream(new File(UPLOADED_FOLDER + file.getOriginalFilename()));
	            fout.write( bytes);*/
	         
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            msg= "Error while sending mail...";
	        }
	        sender.send(message);
	        msg= "Mail Sent Success!";
	      
	    }

}
