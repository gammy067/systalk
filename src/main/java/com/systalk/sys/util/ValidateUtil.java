package com.systalk.sys.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ValidateUtil.
 * @author Richard
 */

public class ValidateUtil {
	private static Logger logger = LoggerFactory.getLogger(ValidateUtil.class);
	
	/** google recaptchav2 secretKey. */
	private static final String SECRET_KEY = SpringUtil.getProperty("google.recaptcha.v2.secretKey");
	
	// 驗證API url
	private static final String GOOGLE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify?";
	
	public static final Map<String, String> RECAPTCHA_ERROR_CODE = new HashMap<>();

	static {
		RECAPTCHA_ERROR_CODE.put("missing-input-secret", "The secret parameter is missing");
		RECAPTCHA_ERROR_CODE.put("invalid-input-secret", "The secret parameter is invalid or malformed");
		RECAPTCHA_ERROR_CODE.put("missing-input-response", "The response parameter is missing");
		RECAPTCHA_ERROR_CODE.put("invalid-input-response", "The response parameter is invalid or malformed");
		RECAPTCHA_ERROR_CODE.put("bad-request", "The request is invalid or malformed");
	}

    /**
     * Google reCAPTCHA V2 驗證.
     * Validates Google reCAPTCHA V2 or Invisible reCAPTCHA.
     * @param secretKey Secret key (key given for communication between your site and Google)
     * @param response reCAPTCHA response from client side. (g-recaptcha-response)
     * @return true if validation successful, false otherwise.
     */
    public synchronized static boolean isCaptchaValid(String response) {
        try {
            String url = GOOGLE_VERIFY_URL+ "secret=" + SECRET_KEY + "&response=" + response;
            
            InputStream res = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            String strCurrentLine = "";
            while ((strCurrentLine = rd.readLine()) != null) {
            	sb.append(strCurrentLine);
            }
            String jsonString = sb.toString();
            res.close();
            
            if(StringUtils.isNotEmpty(jsonString)) {
            	logger.debug("Google reCAPTCHA response = " + jsonString);
            	JsonNode jsonNode = new ObjectMapper().readTree(jsonString);
            	if(jsonNode != null) {
            		return jsonNode.get("success").asBoolean();
            	}
            }

        } catch (Exception e) {
            //e.printStackTrace();
        }
        return false;
    }
    
	
//	public static void main(String[] args) {
//		isCaptchaValid("","123");
//	}
}
