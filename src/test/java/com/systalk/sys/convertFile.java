package com.systalk.sys;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class convertFile {

	public static void main(String[] args) throws IOException {
		convert();
	}
	public static void convert(){
		File file = new File("D://title.txt");
		try {
			List<String> oriStrList = FileUtils.readLines(file);
			
			StringBuffer sb = new StringBuffer();
			for(String oriStr : oriStrList) {
				String changeStr = oriStr;
				if(oriStr.contains("=")) {
					int index = oriStr.indexOf("=");
					changeStr = oriStr.substring(0, index);
//					changeStr = oriStr.substring(index + 1, oriStr.length());
				}
				sb.append(changeStr + "\r\n");
			}
//
//			
//			
//			for(String str : FileUtils.readLines(file2)) {
//				int spiltIndex = StringUtils.indexOfIgnoreCase(str, "=");
//				if(spiltIndex > 0) {
//					String key = StringUtils.split(str, "=")[0];
//					String value = StringUtils.split(str, "=")[1];
//					
//					if(StringUtils.contains(value, ",")) {
//						String pilArr[] = StringUtils.split(value, ",");
//						if (pilArr.length> 1) {
//							value = pilArr[1];
//						}
//
//					} else {
//						continue;
//					}
//					
//					System.out.println("key=" + key + ", value=" + value);
//					
//					int start = StringUtils.indexOf(oriStr, key);
//					String lineStr = StringUtils.substring(oriStr, start);
//					
//					int end = StringUtils.indexOf(lineStr, "\r\n");
//					
//					lineStr = StringUtils.substring(lineStr, 0, end);
//					
//					
//					String oriValue = StringUtils.split(lineStr, "=")[1];
//					
//					System.out.println("oriValue = " + oriValue);
//					
////					int end = StringUtils.indexOf(getStartStr, "\r\n");
////					String oriValue = StringUtils.substring(getStartStr, start, end);
//					
////					System.out.println("oriValue = " + oriValue);
//					
//					sb.append(StringUtils.replace(oriStr, oriValue, value));
//				}
//			}
			
			FileUtils.writeStringToFile(new File("convertFile.txt"), sb.toString());
		} catch (IOException e) {
			
		}
	}
}
