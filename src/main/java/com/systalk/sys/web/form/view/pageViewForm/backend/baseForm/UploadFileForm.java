package com.systalk.sys.web.form.view.pageViewForm.backend.baseForm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 檔案上傳form.
 */
public class UploadFileForm extends SettingForm {
	private static final long serialVersionUID = 1L;

	private List<MultipartFile> files = new ArrayList<>();
	
	private MultipartFile file;
	
	/** 首頁輪播 mobile圖片上傳 */
	private MultipartFile file2;

	public UploadFileForm() {
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}


	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile getFile2() {
		return file2;
	}

	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}
}
