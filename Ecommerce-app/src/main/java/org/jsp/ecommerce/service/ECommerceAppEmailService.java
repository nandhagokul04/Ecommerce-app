package org.jsp.ecommerce.service;

import org.jsp.ecommerce.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import static org.jsp.ecommerce.util.ApplicationConstants.VERIFY_LINK;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ECommerceAppEmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public String MailSender(Merchant merchant,HttpServletRequest request) {
		String siteurl=request.getRequestURL().toString();
		String url=siteurl.replace(request.getServletPath(), "");
		String actual_url= url + VERIFY_LINK +merchant.getToken() ;
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		try {
			helper.setText(actual_url);
			helper.setSubject("Account Activation Mail");
			helper.setTo(merchant.getEmail());
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
