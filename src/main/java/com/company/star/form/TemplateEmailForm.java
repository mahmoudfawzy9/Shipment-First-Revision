package com.company.star.form;

import java.util.Map;

import javax.validation.constraints.NotBlank;

public class TemplateEmailForm extends EmailForm {

	@NotBlank(message = " 'template' field is required")
	private String template;

	private Map<String, Object> data;

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
