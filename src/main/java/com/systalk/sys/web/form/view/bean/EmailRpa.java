package com.systalk.sys.web.form.view.bean;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Rpa諮詢信件.
 */
public class EmailRpa implements Serializable {

	/** 姓名. */
	@NotEmpty(message="{validate.require}")
	private String rpaName;
	
	/** email. */
    @Email(message="{validate.email.error}")
    @NotEmpty(message="{validate.require}")
	private String rpaEmail;
	
	/** 電話. */
    @Pattern(regexp = "^[0-9]\\d{1,10}", message = "{validate.digits.over.max}") // 請輸入最多10個數字號碼
	private String rpaPhone;
    
	/** 公司名稱. */
    @NotEmpty(message="{validate.require}")
	private String rpaCompanyName;
	
	/** 需求描述. */
    @NotEmpty(message="{validate.require}")
	private String rpaDes;


    private String captchaResponse;

	public String getRpaName() {
		return rpaName;
	}


	public void setRpaName(String rpaName) {
		this.rpaName = rpaName;
	}


	public String getRpaEmail() {
		return rpaEmail;
	}


	public void setRpaEmail(String rpaEmail) {
		this.rpaEmail = rpaEmail;
	}


	public String getRpaPhone() {
		return rpaPhone;
	}


	public void setRpaPhone(String rpaPhone) {
		this.rpaPhone = rpaPhone;
	}


	public String getRpaCompanyName() {
		return rpaCompanyName;
	}


	public void setRpaCompanyName(String rpaCompanyName) {
		this.rpaCompanyName = rpaCompanyName;
	}


	public String getRpaDes() {
		return rpaDes;
	}


	public void setRpaDes(String rpaDes) {
		this.rpaDes = rpaDes;
	}

	public String getCaptchaResponse() {
		return captchaResponse;
	}


	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}
}