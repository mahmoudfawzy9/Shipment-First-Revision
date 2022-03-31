package com.company.star.runnable;

import java.util.Date;
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

import com.apptcom.kidstar.exception.type.ServerErrorException;
import com.company.star.form.EmailForm;

public class SendEmailRunnable implements Runnable {
	private static final Logger l = LoggerFactory.getLogger(SendEmailRunnable.class);
	private EmailForm form;

	@Override
	public void run() {
		sendEmail();
	}

	private void sendEmail() {
		try {
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
			msg.setText(form.getBody());
			msg.setSentDate(new Date());

			Transport.send(msg);
		} catch (MessagingException e) {
			throw new ServerErrorException("Error sending email");// need to send the suberrors??
		}

		l.debug("Done sending email");
	}

	public EmailForm getForm() {
		return form;
	}

	public void setForm(EmailForm form) {
		this.form = form;
	}
}
