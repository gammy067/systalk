package com.systalk.sys.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.systalk.sys.controller.validator.common.DateValidator;
import com.systalk.sys.enums.Status;
import com.systalk.sys.web.form.view.pageViewForm.backend.EditSuccessCaseViewForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;

/**
 * 成功案例 Validator.
 */
public class SuccessCaseValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return SuccessCaseValidator.class.isAssignableFrom(clazz);
    }
 
	@Override
    public void validate(Object target, Errors errors) {
		EditSuccessCaseViewForm editSuccessCase = (EditSuccessCaseViewForm)target;
		
		UploadFileForm uploadFileForm = editSuccessCase.getUploadFileForm();
		// 生效日
		String pushingDate = uploadFileForm.getPushingDate();
		// 編輯內文
		String content = uploadFileForm.getContent();

        // 編輯標題
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uploadFileForm.title", "", "欄位必填");
        
        // 編輯英文標題
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uploadFileForm.titleEn", "", "欄位必填");
        
        // 編輯內文
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uploadFileForm.content", "", "欄位必填");
        
        // 編輯英文內文
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uploadFileForm.contentEn", "", "欄位必填");
        
        // 新增 - 圖片需選擇檔案
        if(editSuccessCase.getUploadFileForm().getSeq() == null) {
        	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uploadFileForm.fileName", "", "欄位必填");
        }

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uploadFileForm.fileName", "validate.require", "");
  
//        //Business validation
//        if(!password.equals(confPassword)){
//            errors.rejectValue("password","customer.password.missMatch");
//        }
//        if(age < 18 || age > 60){
//            errors.rejectValue("age", "customer.age.range.invalid");
//        }
   
    }
}
