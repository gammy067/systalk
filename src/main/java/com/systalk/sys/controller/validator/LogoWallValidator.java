package com.systalk.sys.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.systalk.sys.web.form.view.pageViewForm.backend.EditLogoWallViewForm;

public class LogoWallValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return LogoWallValidator.class.isAssignableFrom(clazz);
    }
 
	@Override
    public void validate(Object target, Errors errors) {
		EditLogoWallViewForm editLogoWall = (EditLogoWallViewForm)target;
		String pushingDate = editLogoWall.getUploadFileForm().getPushingDate();

		// 生效日
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uploadFileForm.pushingDate", "", "此欄位必填");
        
        
        // 新增時 - 圖片需選擇檔案
        if(editLogoWall.getUploadFileForm().getSeq() == null) {
        	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uploadFileForm.fileName", "", "此欄位必填");
        }
  
//        //Business validation
//        if(!password.equals(confPassword)){
//            errors.rejectValue("password","customer.password.missMatch");
//        }
//        if(age < 18 || age > 60){
//            errors.rejectValue("age", "customer.age.range.invalid");
//        }
   
    }
}
