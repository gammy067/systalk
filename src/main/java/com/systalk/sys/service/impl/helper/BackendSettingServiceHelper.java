package com.systalk.sys.service.impl.helper;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.util.ImgUtil;

/**
 * 後台設定 service 輔助程式.
 */
public class BackendSettingServiceHelper {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 刪除相同的檔案.
	 *
	 * @param folderPath the folder path
	 */
	public void deleteSameFile(String folderPath, String fileName) {
    	String dateStr = DateFormatUtils.format(new Date(), CommonUtil.formatyyyyMMddmmssSSSS);
		File folder = new File(folderPath);

		if (folder.isDirectory()) {
	    	for (String localFileName : folder.list()) {
	        	if(localFileName.indexOf(fileName + dateStr) != -1) {
	        		File deleteFile = new File(folderPath + localFileName);
					deleteFile.delete();
					logger.debug("deleteFile" + folderPath + localFileName);
					return;
	        	}
	    	}

		}
	}
	
	/**
	 * 圖片壓縮.縮小處理.
	 *
	 * @param type the type
	 * @param filePath the file path
	 */
	public void compressimage(String type, String filePath) {
		if ("images".equals(type)) {
			try {
				File f = new File(filePath);
				if (f.exists()) {
					ImgUtil.compressPictureByQality(f, 0.7f);
					logger.debug("compressPictureByQality " + filePath);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private BackendSettingServiceHelper() {
	}

	private static class Loader {
		private static final BackendSettingServiceHelper instance = new BackendSettingServiceHelper();
	}

	public static BackendSettingServiceHelper getInstance() {
		return Loader.instance;
	}
}
