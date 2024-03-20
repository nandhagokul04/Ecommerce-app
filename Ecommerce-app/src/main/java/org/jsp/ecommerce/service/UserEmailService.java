package org.jsp.ecommerce.service;

import static org.jsp.ecommerce.util.ApplicationConstants.USER_VERIFY_LINK;

import org.jsp.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserEmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String MailSender(User user,HttpServletRequest request) {
		String siteurl=request.getRequestURL().toString();
		String url=siteurl.replace(request.getServletPath(), "");
		String actual_url= url + USER_VERIFY_LINK +user.getToken() ;
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		try {
			helper.setText(actual_url);
			helper.setSubject("Account Activation Mail");
			helper.setTo(user.getEmail());
			javaMailSender.send(message);
			return "Verification mail has sent";
		}
		catch(MessagingException e)
		{
			e.printStackTrace();
			return"cannot send Verification link";
		}
	}
}
