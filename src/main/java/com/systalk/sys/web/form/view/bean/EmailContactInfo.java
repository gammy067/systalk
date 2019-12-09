package com.systalk.sys.web.form.view.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 聯絡我們.
 */
public class EmailContactInfo implements Serializable {

	/** 姓名. */
	@NotEmpty(message="{validate.require}")
	private String name;
	
	/** 電話. */
    @Pattern(regexp = "^[0-9]\\d{1,10}", message = "{validate.digits.over.max}")
	private String phone;
	
	/** email. */
    @Email(message="{validate.email.error}")
    @NotEmpty(message="{validate.require}")
	private String email;
	
	/** 留言內容. */
    @Size(min = 1, message="{validate.require}")
	private String content;
    
	public EmailContactInfo(){	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}