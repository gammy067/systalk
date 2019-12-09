package com.systalk.sys.controller.validator.response;

import java.util.Map;

/**
 * 驗證訊息回傳 json.
 */
public class ValidateJsonRespone {
	private boolean validate;
	private boolean hasException;
	private Map<String, String> errorMessages;


	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public boolean isHasException() {
		return hasException;
	}

	public void setHasException(boolean hasException) {
		this.hasException = hasException;
	}

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}
}