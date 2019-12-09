package com.systalk.sys.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DocUtil.class);
	private static String OS = System.getProperty("os.name").toLowerCase();
	private static Double size;
	
	/**
	 * <p>
	 * 上傳檔案路徑:File/word/YYYYMM/abc.docx(已做完rename,原本檔案名稱存在db)<br>
	 * 				  /html/YYYYMM/abc.html<br>
	 * 							  /abc/IMG/xxx.png<br>
	 * </p>
	 * <p>
	 * contentPath：放的是application的實體路徑 ＋ word,html 儲存的最上層路徑(eg:/users/jaychen/webapps/tpu/File/),
	 * 並非單指word的路徑！
	 * </p>
	 * <p>
	 * sourceFileName：僅帶入word檔案名稱，不包含附檔名(.docx)
	 * </p>
	 * <p>
	 * branch：存入哪個月份的資料夾下(eg:yyyyMM)
	 * </p>
	 * @param contentPath
	 * @param sourceFileName
	 * @param branch
	 * @return 
	 * @throws TpuRuntimeException 
	 */
//	public static Map<String, String> createHTMLFromDocx(File sourceFile, String contentPath, String sourceFileName, String branch, 
//			ArticleForm articleForm) throws TpuRuntimeException{
//		Map<String, String> result = new HashMap<String, String>();
//		String resultUri = "";
//		InputStream is = null;
//		OutputStream out = null;
//		FileInputStream fis = null;
//		try {
//			long start = System.currentTimeMillis();
//			/*
//			 * 各檔案路徑
//			 * docx:設定於application.properties的article.source.path
//			 * html			:/tpu/File/html/201507/20150722113956.html		(原始html)
//			 * _copy.html	:/tpu/File/html/201507/20150722113956_copy.html	(文章檢視)
//			 * _f.html		:/tpu/File/html/201507/20150722113956_f.html	(友善閱讀)
//			 * img			:/tpu/File/html/201507/20150722113956/images/word/media/image1.png
//			 */
//			//eg:contentPath = /users/jaychen/webapps/tpu/File/
//			// 1) Load DOCX into XWPFDocument
//			is = new FileInputStream(sourceFile);
//			XWPFDocument document = new XWPFDocument(is);
//			
//			// 2) Prepare Html options
//			XHTMLOptions options = XHTMLOptions.create();
//			// Extract image
//	        File imageFolder = new File( contentPath + 
//	        		parseFilePath(false, false, articleForm.getSpecialPath(),ArticleService.FIXED_HTML_PATH, branch, sourceFileName, ArticleService.FIXED_IMG_PATH));
//	        imageFolder.getParentFile().mkdirs();
//	        options.setExtractor( new FileImageExtractor( imageFolder ) );//image實體路徑
//	        // URI resolver
//	        final String partImageFolderUri =  parseURIPath(true, true, "tpu", ArticleService.FIXED_ROOT_PATH, 
//    				articleForm.getSpecialPath(), ArticleService.FIXED_HTML_PATH, branch, sourceFileName, ArticleService.FIXED_IMG_PATH );
//	        
//	        //File partImageFolder = new File(partImageFolderUri);
//	        //image網站路徑
//	        options.URIResolver(new IURIResolver(){
//				@Override
//				public String resolve(String uri) {
//					// TODO Auto-generated method stub
//		            return partImageFolderUri + uri;
//				}
//	        });
//	        
//			// 3) Convert XWPFDocument to HTML
//	        String copyHtmlFileName = sourceFileName + ArticleService.FIXED_HTML_COPY_FILENAME_APPEND;
//	        resultUri = parseURIPath(true, false, 
//	        		ArticleService.FIXED_ROOT_PATH, articleForm.getSpecialPath(), ArticleService.FIXED_HTML_PATH, branch, copyHtmlFileName + ArticleService.FIXED_HTML_EXTENSION);
//	        File genFile = new File(contentPath + 
//	        		parseFilePath(false, false, articleForm.getSpecialPath(), ArticleService.FIXED_HTML_PATH, branch, sourceFileName + ArticleService.FIXED_HTML_EXTENSION));
//			out = new FileOutputStream(genFile);
//			XHTMLConverter.getInstance().convert(document, out, options);
//			
//			logger.debug("Generate " + genFile.getPath() + " with " + (System.currentTimeMillis() - start) + "ms");
//			
//			/* 4) Copy HTML
//			 * 調整html樣式
//			 */
//			File copyFile = new File(contentPath + 
//					parseFilePath(false, false, articleForm.getSpecialPath(), ArticleService.FIXED_HTML_PATH, branch, copyHtmlFileName + ArticleService.FIXED_HTML_EXTENSION));
//			byte[] buffer = new byte[1000];
//			fis = new FileInputStream(genFile);
//			int totalRead = 0;
//	        int nRead = 0;
//	        StringBuffer innerHtml = new StringBuffer(); 
//	    	while((nRead = fis.read(buffer)) != -1) {
//	        	String tmpStr = new String(buffer, 0, nRead);
//	        	innerHtml.append(tmpStr);
//	        	totalRead += nRead;
//	        }
//	    	
//	    	String copyHtml = innerHtml.toString();
//	    	// 1. 加上防止右鍵(只需做一次)
//        	copyHtml = copyHtml.replace(ArticleService.HTML_COPY_PREVENT_RIGHT_CLICK_TARGET, ArticleService.HTML_COPY_PREVENT_RIGHT_CLICK_REPLACEMENT);
//        	
//        	// 2. 圖片的path去掉根目錄(Windows)
//        	if(isWindows()){
//        		copyHtml = replaceMatching(copyHtml, ArticleService.HTML_COPY_IMAGE_PATH_lOWERBOUND, ArticleService.HTML_COPY_IMAGE_PATH_UPPERBOUND);
//        	}
//        	
//        	// 3.連結加上開新視窗
//        	copyHtml = replaceMatching(copyHtml, ArticleService.HTML_COPY_LINK_LOWERBOUND, ArticleService.HTML_COPY_LINK_UPPERBOUND, ArticleService.HTML_COPY_LINK_REPLACEMENT);
//        	
//        	// 4.目錄拿掉開新視窗
//        	copyHtml = replaceMatching(copyHtml, ArticleService.HTML_COPY_LINK_LOWERBOUND, ArticleService.HTML_COPY_CATALOG_UPPERBOUND);
//        	
//        	// 5. 標題項目標號size調整
//        	Resource resource = new ClassPathResource("/application.properties");
//    		try {
//    			Properties props = PropertiesLoaderUtils.loadProperties(resource);
//    			String itemLowerbounds = props.getProperty("doc.item.lowerbound");
//    			String itemUpperbound = props.getProperty("doc.item.upperbound");
//    			String itemFontsize = " style='font-size:"+props.getProperty("doc.item.fontsize")+".0pt;' ";
//    			String articleLowerbounds = props.getProperty("doc.article.lowerbound");
//    			String articleUpperbound = props.getProperty("doc.article.upperbound");
//    			String articleFontsize = " style='font-size:"+props.getProperty("doc.article.fontsize")+".0pt;font-weight:"+props.getProperty("doc.article.fontweight")+"' ";
//    			
//            	//標題size調整
//    			String[] itemLowerbondArray = itemLowerbounds.split(";");
//    			for(String lowerbound:itemLowerbondArray){
//    				String itemLowerbound = "class=\""+ lowerbound + "\""; 
//    				copyHtml = replaceMatching(copyHtml, itemLowerbound, itemUpperbound, itemFontsize);
//    			}
//    			
//    			//內文size調整
//    			String[] articleLowerbondArray = articleLowerbounds.split(";");
//    			for(String lowerbound:articleLowerbondArray){
//    				String articleLowerbound = "class=\""+ lowerbound + "\""; 
//    				copyHtml = replaceMatching(copyHtml, articleLowerbound, articleUpperbound, articleFontsize);
//    			}
//    		} catch (Exception e) {
//    			logger.error("Can't read document format from properties, " + e);
//    			throw new TpuRuntimeException("error.500.msg", null);
//    		}
//        	
//        	// 6. 調整圖片大小
//        	if(size==null){
//        		getImageSize();
//        	}
//        	copyHtml = HTMLFormatter.ajustImgSize(copyHtml, size);
//        	
//        	// 7. 存成_copy.html
//        	byte[] copybuffer = copyHtml.getBytes();
//        	int copyBufferSize = copybuffer.length;
//	        out = new FileOutputStream(copyFile);
//        	out.write(copybuffer, 0, copyBufferSize);
//        	out.close();
//        	// 作簡體版時再開啟Start TODO
////        	// 7-1. 存成_copy_cn.html
////        	String copyCnHtmlFileName = sourceFileName + ArticleService.FIXED_HTML_COPY_ZHCN_FILENAME_APPEND;
////			File copyCnFile = new File(contentPath + 
////        			parseFilePath(false, false, ArticleService.FIXED_HTML_PATH, branch, copyCnHtmlFileName + ArticleService.FIXED_HTML_EXTENSION));
////
////			YCoding zhcoder = new YCoding();
////			String copyCnHtml = zhcoder.convertStringW3C( copyHtml, YCoding.UNICODET, YCoding.UNICODES );
////			copyCnHtml = StringUtils.replaceEach(copyCnHtml, new String[]{"&amp;", "&#34;", "&#39;", "&lt;", "&gt;"}, new String[]{"&", "\"", "\'", "<", ">"});
//			
////			byte[] copyCnbuffer = copyCnHtml.getBytes();
////        	int copyCnBufferSize = copyCnbuffer.length;
////	        out = new FileOutputStream(copyCnFile);
////        	out.write(copyCnbuffer, 0, copyCnBufferSize);
////        	out.close();
//        	// 作簡體版時再開啟End
//        	
//        	// 8. 準備友善閱讀_f.html
//        	String friendlyHtmlFileName = sourceFileName + ArticleService.FIXED_HTML_FRIENDLY_FILENAME_APPEND;
//        	String friendlyFileName = parseFilePath(true, false, 
//	        		ArticleService.FIXED_ROOT_PATH, articleForm.getSpecialPath(), ArticleService.FIXED_HTML_PATH, branch, friendlyHtmlFileName + ArticleService.FIXED_HTML_EXTENSION);
//        	File friendlyFile = new File(contentPath + 
//        			parseFilePath(false, false, articleForm.getSpecialPath(), ArticleService.FIXED_HTML_PATH, branch, friendlyHtmlFileName + ArticleService.FIXED_HTML_EXTENSION));
//
//        	// 作簡體版時再開啟Start TODO
////        	 8-1. 準備友善閱讀簡體_f_cn.html
////        	String friendlyCnHtmlFileName = sourceFileName + ArticleService.FIXED_HTML_FRIENDLY_ZHCN_FILENAME_APPEND;
////        	File friendlyCnFile = new File(contentPath + 
////        			parseFilePath(false, false, ArticleService.FIXED_HTML_PATH, branch, friendlyCnHtmlFileName + ArticleService.FIXED_HTML_EXTENSION));
////        	
//        	// 作簡體版時再開啟End
//        	
//        	// 9. 準備友善閱讀版面
//        	copyHtml = convertToFriendlyHtml(copyHtml, resultUri, articleForm, Locale.TRADITIONAL_CHINESE);
//
//        	// 作簡體版時再開啟Start TODO
////        	// 9-1. 準備友善閱讀簡體版面
////        	copyCnHtml = convertToFriendlyHtml(copyCnHtml, resultUri, articleForm, Locale.SIMPLIFIED_CHINESE);
//        	// 作簡體版時再開啟End
//        	
//        	// 10. 存成_f.html檔
//        	byte[] friendlybuffer = copyHtml.getBytes();
//        	int friendlyBufferSize = friendlybuffer.length;
//	        out = new FileOutputStream(friendlyFile);
//        	out.write(friendlybuffer, 0, friendlyBufferSize);
//
//        	// 作簡體版時再開啟Start TODO
////        	out.close();
//        	
////        	// 10-1. 存成_f_cn.html檔
////			byte[] friendlyCnbuffer = copyCnHtml.getBytes();
////        	int friendlyCnBufferSize = friendlyCnbuffer.length;
////	        out = new FileOutputStream(friendlyCnFile);
////        	out.write(friendlyCnbuffer, 0, friendlyCnBufferSize);
//        	// 作簡體版時再開啟End
//        	
//	        logger.debug("Read " + totalRead + " bytes, Write " + copyBufferSize + "bytes" );
//            
//			result.put("filename", resultUri);
//			result.put("friendlyFilename", friendlyFileName);
//		} catch (Throwable e) {
//			logger.error("Parsing docx with exception, " + e);
//			throw new TpuRuntimeException("error.500.msg", null);
//		} finally{
//			try {
//				if (is != null)
//					is.close();
//				if (out != null)
//					out.close();
//				if (fis != null)
//					fis.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//	
//	public static boolean deleteFile(String file){
//		return deleteFile(file, null);
//	}
//	/**
//	 * <p>刪除文件</p>
//	 * @param path
//	 * @param fileName
//	 */
//	public static boolean deleteFile(String path, String fileName){
//		String filePath = (fileName != null && !"".equals(fileName))?path + File.separator + fileName : path;
//		logger.debug("deleteFile start, file path:" + filePath);
//		boolean result = false;
//		File file = new File(filePath);
//		if(file.exists()){
//			result = file.delete();
//		}
//		return result;
//	}
//	/**
//	 * <p>刪除路徑下所有檔案</p>
//	 * @param addr
//	 * @return
//	 */
//	public static boolean deleteDirectory(String addr) {
//		File path = new File(addr);
//		logger.debug("deleteDirectory start, file path:" + path.getAbsolutePath());
//	    if( path.exists() ) {
//	    	File[] files = path.listFiles();
//		    for(int i=0; i<files.length; i++) {
//		       if(files[i].isDirectory()) {
//		    	   deleteDirectory(files[i].getAbsolutePath());
//		       }else {
//		    	   files[i].delete();
//		       }
//		    }
//	    }
//	    return( path.delete() );
//	}
//	
//	/**
//	 * <b>更改HTML位置</b>
//	 * @param oldSubPath	文章舊子路徑
//	 * @param oldCopyPath	文章 _copy.html舊路徑
//	 * @param newSubPath	新子路徑
//	 * @throws TpuRuntimeException 
//	 */
//	public static String moveArticleHtml(String oldSubPath, String oldCopyPath, String newSubPath, String contextPath) throws TpuRuntimeException {
//		logger.info("[moveArticleHtml] oldSubPath: {}, oldCopyPath: {}, newSubPath: {}", oldSubPath, oldCopyPath, newSubPath);
//
//		try {
//        	/*	組新路徑Start	*/
//        	String newCopyPathWithoutContexPath = oldCopyPath;
//        	if (!StringUtils.isEmpty(oldSubPath)) {
//        		// Step1. 若舊路徑含有子路徑先刪除
//        		logger.debug("[moveArticleHtml] oldSubPath exist, remove it!");
//        		newCopyPathWithoutContexPath = newCopyPathWithoutContexPath.replaceFirst(File.separator + oldSubPath, "");
//        	}
//        	if (!StringUtils.isEmpty(newSubPath)) {
//				// Step2. 若新發布學院含有子路徑加入
//        		logger.debug("[moveArticleHtml] newSubPath exist, add it! newCopyPath: {}, seperator: {}.....{}", newCopyPathWithoutContexPath, File.separator);
//        		newCopyPathWithoutContexPath = DocUtil.replaceMatching(
//        				newCopyPathWithoutContexPath, 
//        				ArticleService.FIXED_ROOT_PATH, 
//        				File.separator, 
//        				File.separator + newSubPath);
//			}
//        	String newCopyPath = new StringBuilder(contextPath).append(newCopyPathWithoutContexPath).toString();
//        	oldCopyPath = new StringBuilder(contextPath).append(oldCopyPath).toString();
//        	/*	組新路徑End	*/
////   		 * _copy.html	:/tpu/File/${subPath}/html/201507/20150722113956_copy.html	(文章檢視)
//						
////			 * html			:/tpu/File/${subPath}/html/201507/20150722113956.html		(原始html)
//			String oldOrgPath = oldCopyPath.replace(ArticleService.FIXED_HTML_COPY_FILENAME_APPEND, "");
//			String newOrgPath = newCopyPath.replace(ArticleService.FIXED_HTML_COPY_FILENAME_APPEND, "");
//			
////			 * _f.html		:/tpu/File/${subPath}/html/201507/20150722113956_f.html		(友善閱讀)
//			String oldfPath = oldCopyPath.replace(ArticleService.FIXED_HTML_COPY_FILENAME_APPEND, ArticleService.FIXED_HTML_FRIENDLY_FILENAME_APPEND);
//			String newfPath = newCopyPath.replace(ArticleService.FIXED_HTML_COPY_FILENAME_APPEND, ArticleService.FIXED_HTML_FRIENDLY_FILENAME_APPEND);
//			
////			 * images		:/tpu/File/${subPath}/html/201507/20150722113956/*			(圖片)
//			String oldImgPath = oldOrgPath.replace(ArticleService.FIXED_HTML_EXTENSION, "");
//			String newImgPath = newOrgPath.replace(ArticleService.FIXED_HTML_EXTENSION, "");
//			
//			createDirectoryIfNotExsist(Paths.get(newImgPath)); // 建立新路徑
//			Files.move(Paths.get(oldCopyPath), Paths.get(newCopyPath), StandardCopyOption.REPLACE_EXISTING);
//			Files.move(Paths.get(oldOrgPath), Paths.get(newOrgPath), StandardCopyOption.REPLACE_EXISTING);
//			Files.move(Paths.get(oldfPath), Paths.get(newfPath), StandardCopyOption.REPLACE_EXISTING);
////			FIXME 目前圖檔會留存一份在原檔案夾，若要改html檔圖檔路徑，在使用move
////			Files.move(Paths.get(oldImgPath), Paths.get(newImgPath), StandardCopyOption.REPLACE_EXISTING);
//			Files.copy(Paths.get(oldImgPath), Paths.get(newImgPath), StandardCopyOption.REPLACE_EXISTING);
//			
//			return newCopyPathWithoutContexPath;
//		} catch (Throwable e) {
//			logger.error("Errors occured when moving files, ", e);
//			throw new TpuRuntimeException("error.500.msg", null);
//		}
//	}
//	
//	protected static boolean isWindows() {
//		return (OS.indexOf("win") >= 0);
//	}
//	
//	/**
//	 * <b>清空兩個字串中的值</b>
//	 * <p>EX. replaceMatching("123456", "1", "56"); => 156</p>
//	 * @param input	輸入的字串
//	 * @param lowerBound  前字串
//	 * @param upperBound  後字串
//	 * @return
//	 */
//	protected static String replaceMatching(String input, String lowerBound, String upperBound){
//		return replaceMatching(input, lowerBound, upperBound, null);
//	}
//	
//	/**
//	 * <b>取代兩個字串中的值</b>
//	 * <p>EX. replaceMatching("123456", "1", "56", "5"); => 1556</p>
//	 * @param input	輸入的字串
//	 * @param lowerBound  前字串
//	 * @param upperBound  後字串
//	 * @param replacement 取代的字串
//	 * @return
//	 */
//	protected static String replaceMatching(String input, String lowerBound, String upperBound, String replacement){
//		replacement = (replacement == null)? "" : replacement;
//		String regexString = lowerBound + ".*?" + upperBound;
//		String result = input.replaceAll(regexString, lowerBound + replacement + upperBound);
//		return result; 
//	}
//	
//	protected static String getReplacement(ArticleForm articleForm){
//		StringBuffer replacement = new StringBuffer();
//		replacement
//			.append("<meta name='title' content='").append("[昕力大學]" + articleForm.getAtcTopic()).append("'/>")
//			.append("<meta name='description' content='").append(articleForm.getAtcBrief()).append("'/>")
//			.append("<meta name='image' content='").append(getPropertie(PropertieName.DOMAIN_URL)+"tpu/images/og-image.png").append("'/>")
//			.append("<meta name='keywords' content='").append("昕力大學, 昕力, 昕力資訊, ThinkPower, ThinkPower University").append("'/>")
//			.append("<meta itemprop='image' content='").append(getPropertie(PropertieName.DOMAIN_URL)+"tpu/images/og-image.png").append("'/>")
//			.append("<meta property='og:image' content='").append(getPropertie(PropertieName.DOMAIN_URL)+"tpu/images/og-image.png").append("'/>");
//		return replacement.toString();
//	}
//	protected static void getImageSize() {
//		Resource resource = new ClassPathResource("/application.properties");
//		try {
//			Properties props = PropertiesLoaderUtils.loadProperties(resource);
//			size = Double.parseDouble(props.getProperty("tpu.toDraft.size"));
//		} catch (Exception e) {
//			size=1.5;
//			logger.error("Can't read size setting from properties, " + e);
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 由各資料夾名稱組合檔案路徑
//	 * 
//	 * @param prefix 是否由 "/" 開頭
//	 * @param suffix 是否由 "/" 結尾
//	 * @param strings 所有資料夾名稱
//	 * @return
//	 */
//	public static String parseURIPath(boolean prefix, boolean suffix, String ... strings){
//		String filePath = parseFilePath(prefix, suffix, strings);
//		return (File.separatorChar == '\\') ? filePath.replace(File.separatorChar, '/') : filePath;
//	}
//	
//	/**
//	 * 由各資料夾名稱組合檔案路徑
//	 * 
//	 * @param prefix 是否由 "/" 開頭
//	 * @param suffix 是否由 "/" 結尾
//	 * @param strings 所有資料夾名稱
//	 * @return
//	 */
//	public static String parseFilePath(boolean prefix, boolean suffix, String ... strings){
//		StringBuffer filePath = new StringBuffer();
//		
//		for(String str : strings){
//			if (str != null){
//				if (str.trim().length()>0){
//					if(prefix){
//						filePath.append(File.separator);
//					}else{
//						prefix = true;
//					}
//					filePath.append(str);
//				}
//			}
//		}
//		
//		if(suffix){
//			filePath.append(File.separator);
//		}
//		return filePath.toString();
//	}
//	
//	private static String convertToFriendlyHtml(String originHtml, String resultUri, ArticleForm articleForm, Locale locale){
//		
//		String friendly_logo_replacement = ArticleService.HTML_FRIENDLY_HTML_LOGO_REPLACEMENT;
//		String friendly_header_replacement = getReplacement(articleForm);
//		String share_filename_append = ArticleService.FIXED_HTML_FRIENDLY_FILENAME_APPEND;
//		
//		if (articleForm.getIsShare() == false)
//			friendly_logo_replacement = ArticleService.HTML_FRIENDLY_HTML_LOGO_REPLACEMENT_NOSHARE;
//		
//		if(Locale.SIMPLIFIED_CHINESE.equals(locale)){
//			YCoding zhcoder = new YCoding();
//			friendly_logo_replacement = zhcoder.convertStringW3C( friendly_logo_replacement, YCoding.UNICODET, YCoding.UNICODES ); 
//			friendly_logo_replacement = StringUtils.replaceEach(friendly_logo_replacement, new String[]{"&amp;", "&#34;", "&#39;", "&lt;", "&gt;"}, new String[]{"&", "\"", "\'", "<", ">"});
//			
//			friendly_header_replacement = zhcoder.convertStringW3C( friendly_header_replacement, YCoding.UNICODET, YCoding.UNICODES );
//			friendly_header_replacement = StringUtils.replaceEach(friendly_header_replacement, new String[]{"&amp;", "&#34;", "&#39;", "&lt;", "&gt;"}, new String[]{"&", "\"", "\'", "<", ">"});
//			
//			share_filename_append = ArticleService.FIXED_HTML_FRIENDLY_ZHCN_FILENAME_APPEND;
//			
//		}
//		// 1. 加入style
//		originHtml = originHtml.replace(ArticleService.HTML_FRIENDLY_STYLE_LOWERBOUND, ArticleService.HTML_FRIENDLY_STYLE_LOWERBOUND + ArticleService.HTML_FRIENDLY_STYLE_REPLACEMENT);
//            	
//    	// 2. 加入logo & share to fb for友善閱讀,
//		originHtml = replaceMatching(originHtml, ArticleService.HTML_FRIENDLY_HTML_LOGO_LOWERBOUND, ArticleService.HTML_FRIENDLY_HTML_LOGO_UPPERBOUND, friendly_logo_replacement);
//    	
//    	// 2-1. 分享的網址換入此文章網址
//    	if(originHtml.indexOf(ArticleService.HTML_SHARE_TO_FB_URL) != -1){
//			originHtml = originHtml.replace(ArticleService.HTML_SHARE_TO_FB_URL, 
//					getPropertie(PropertieName.FB_SHARE_LINK) + "?u="+ getPropertie(PropertieName.DOMAIN_URL) +"tpu" + 
//							resultUri.replace(ArticleService.FIXED_HTML_COPY_FILENAME_APPEND, share_filename_append));
//    	}
//    	
//    	// 3. 加入fb分享 head metadata
//    	originHtml = replaceMatching(originHtml, ArticleService.HTML_HEAD_METADATA_LOWERBOUND, ArticleService.HTML_HEAD_METADATA_UPPERBOUND, friendly_header_replacement);
//    	
//    	// 3-1. Domain 網址換入正確網址
//    	if(originHtml.indexOf(ArticleService.HTML_DOMAIN_NAME_URL) != -1){
//			originHtml = originHtml.replace(ArticleService.HTML_DOMAIN_NAME_URL, getPropertie(PropertieName.DOMAIN_URL));
//    	}
//    	
//    	return originHtml;
//		
//	}
//	
//	static Properties _props = null;
//	protected static Properties getProperties() throws TpuRuntimeException{
// 		try {
// 			
// 			if (_props == null){
// 				Resource resource = new ClassPathResource("/application.properties");
// 		   		_props = PropertiesLoaderUtils.loadProperties(resource);
// 			}
//			
//			return _props;
//		} catch (Exception e) {
//			logger.error("Can't read document format from properties, " + e);
//			throw new TpuRuntimeException("error.500.msg", null);
//		}
//	}
//
//	protected static String getPropertie(PropertieName name){
// 		
//		try {
// 			return getPropertieName(name);
//		}catch (Exception e) {
//			logger.error("Get Propertie from properties name [" + name.getValue() +"], " + e);
//			return "";
//		}
//	}
//
//	protected static String getPropertieName(PropertieName name) throws TpuRuntimeException {
// 		
// 		return getProperties().getProperty(name.getValue());
//	}
//	
//	protected enum PropertieName {
//		
//		DOMAIN_URL("tpu.url"),
//	    FB_SHARE_LINK("facebook.share.link");
//	 
//	    private String value;
//	 
//	    private PropertieName(String value) {
//	        this.value = value;
//	    }
//	 
//	    public String getValue() {
//	        return this.value;
//	    }
//		
//	}
//	
//	public static void createDirectoryIfNotExsist(Path path) {
//		//if directory exists?
//        if (!Files.exists(path)) {
//            try {
//                Files.createDirectories(path);
//            } catch (IOException e) {
//                //fail to create directory
//                e.printStackTrace();
//            }
//        }
//	}
}