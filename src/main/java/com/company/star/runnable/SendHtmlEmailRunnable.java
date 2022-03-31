package com.company.star.runnable;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.apptcom.kidstar.exception.type.ServerErrorException;
import com.company.star.form.TemplateEmailForm;

public class SendHtmlEmailRunnable implements Runnable {

	private static final Logger l = LoggerFactory.getLogger(SendHtmlEmailRunnable.class);
	private TemplateEmailForm form;
	private TemplateEngine templateEngine;

	@Override
	public void run() {
		sendHtmlEmail();
	}

	private void sendHtmlEmail() {

		try {
			form.setBody(generateEmailTemplate(form.getData()));
			Address[] toRecipientsArr = new Address[form.getTo().size()];
			Properties props = new Properties();

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("info@kidstarapp.com", "3&%4.7ywYpRo");
				}
			});
			Message msg = new MimeMessage(session);
			for (int i = 0; i < toRecipientsArr.length; i++) {
				toRecipientsArr[i] = new InternetAddress(form.getTo().get(i));
			}

			if (form.getCc() != null && !form.getCc().isEmpty()) {
				Address[] ccRecipientsArr = new Address[form.getCc().size()];
				for (int i = 0; i < ccRecipientsArr.length; i++) {
					ccRecipientsArr[i] = new InternetAddress(form.getCc().get(i));
				}

				msg.setRecipients(Message.RecipientType.CC, ccRecipientsArr);
			}
			// ------------------------End Of
			// Initializing------------------------------------
			props.put("mail.smtp.host", "gator4152.hostgator.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			msg.setFrom(new InternetAddress("info@kidstarapp.com", false));
			msg.setRecipients(Message.RecipientType.TO, toRecipientsArr);
			msg.setSubject(form.getSubject());
			msg.setContent(form.getBody(), "text/html");
			msg.setSentDate(new Date());

			Transport.send(msg);
		} catch (MessagingException e) {
			throw new ServerErrorException("Error sending email");
		}

		l.debug("Done sending email");
	}

	private String generateEmailTemplate(Map<String, Object> data) {
		final String templateFileName = form.getTemplate();
		Context thymeleafContext = new Context();

		thymeleafContext.setVariables(data);
		return this.templateEngine.process(templateFileName, thymeleafContext);
	}

	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public TemplateEngine getTemplateEngine() {
		return templateEngine;
	}

	public TemplateEmailForm getForm() {
		return form;
	}

	public void setForm(TemplateEmailForm form) {
		this.form = form;
	}
}
