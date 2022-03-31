package com.company.star.form;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class EmailForm implements Form {

	@NotBlank(message = " 'subject' field is required")
	private String subject;

	@NotEmpty(message = " 'to' field is required")
	private List<@Email String> to;

	private List<@Email String> cc;

	private String body;

	// @Email(message = "Email should be valid")
	// String from;

	public void setSubject(String sub) {
		this.subject = sub;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getTo() {
		return this.to;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public List<String> getCc() {
		return this.cc;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBody() {
		return this.body;
	}

}
