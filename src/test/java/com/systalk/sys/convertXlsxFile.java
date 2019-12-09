package com.systalk.sys;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class convertXlsxFile {
	
	// 預設的properties
	static final String ORI_PROPERTIES_PATH = "D://messages.properties";
	
	// 欲新增語系文件路徑
	static final String XLSX_PATH = "D://message-for-WORK_OPER更新中.xlsx";
	
	// 欲新增語系文件路徑
	static final String NEW_PROPERTIES_PATH = "convertMessage.properties";

	public static void main(String[] args) throws IOException {
		List<String> oriStrList = FileUtils.readLines(new File(ORI_PROPERTIES_PATH));

		// 產生設定檔Map
		Map<String, String> propertiesMap = getPropertiesMap(oriStrList);

		// 將語系value取代成文件中翻譯後的值
		changeValue(propertiesMap);
        
		// 產生檔案 輸出的字串
		String outPutStr = getOutPutStr(oriStrList, propertiesMap);

        FileUtils.writeStringToFile(new File(NEW_PROPERTIES_PATH), outPutStr, "UTF-8");
	}

	/**
	 * 產生設定檔Map.
	 *
	 * @param oriStrList the ori str list
	 * @return the properties map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static Map<String, String> getPropertiesMap(List<String> oriStrList) throws IOException {
		Map<String, String> propertiesMap = new ConcurrentHashMap<String, String>();

		for(String oriStr : oriStrList) {
			if(StringUtils.contains(oriStr, "=")) {
				propertiesMap.put(oriStr.split("=")[0], oriStr.split("=")[1]);
			}
		}
		return propertiesMap;
	}
	
	/**
	 * 將語系value取代成文件中翻譯後的值.
	 *
	 * @param propertiesMap the properties map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void changeValue(Map<String, String> propertiesMap) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(XLSX_PATH);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        Iterator<Row> rows = sheet.rowIterator();
        
        String key = "";
        String value = "";

        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext()) {
                cell = (XSSFCell) cells.next();
                String cellValue = cell.getStringCellValue();
                System.out.println(cellValue);

                
                if(cell.getColumnIndex() == 0 && cellValue.indexOf("=") > 0) {
                    key = StringUtils.split(cellValue,"=")[0];
                   
                }
                if(cell.getColumnIndex() == 1) {
                	value = StringUtils.trim(cellValue).replace("\n", "");
                }
                
                if(propertiesMap.get(key) != null && StringUtils.isNoneEmpty(value)) {
                	propertiesMap.put(key, value);
                }
            }
            
            key = "";
            value = "";
        }
	}
	
	/**
	 * 產生檔案 輸出的字串.
	 *
	 * @param oriStrList the ori str list
	 * @param propertiesMap the properties map
	 * @return the out put str
	 */
	private static String getOutPutStr(List<String> oriStrList, Map<String, String> propertiesMap) {
        StringBuffer sb = new StringBuffer();
        for(String oriStr : oriStrList) {
        	
        	String convertStr = oriStr;
        	if(oriStr.contains("=")) {
        		int index = oriStr.indexOf("=");
        		String messageKey = oriStr.split("=")[0];
        		convertStr = oriStr.substring(0, index) + "=" + propertiesMap.get(messageKey);
        	}
        	
        	sb.append(convertStr + "\r\n");
        }
        return sb.toString();
	}
}
