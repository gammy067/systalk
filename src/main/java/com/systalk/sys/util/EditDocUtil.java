package com.systalk.sys.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class EditDocUtil extends DocUtil {
	
	private static Logger logger = LoggerFactory.getLogger(EditDocUtil.class);
	
	String path;
	String folder;
	String wantFileName;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	public String getWantFileName() {
		return wantFileName;
	}
	public void setWantFileName(String wantFileName) {
		this.wantFileName = wantFileName;
	}
	
	public Boolean generateFolder(){
		return generateFolder(path + folder + File.separator);
	}

	public static Boolean generateFolder(String path){
		File folder = new File(path);
		folder.mkdirs();
		return true;
	}
	
	public String uploadfile(MultipartFile file){
		return uploadfile(file, path + folder + File.separator, wantFileName);
	}
	
	public static String uploadfile(MultipartFile file, String folderPath, String wantFileName){
		
		File folder = new File(folderPath);
		if (!folder.exists())
			folder.mkdirs();
		
		String realFileName = wantFileName + "." + FilenameUtils.getExtension(file.getOriginalFilename());
		//3. Write to server side
		if (!file.isEmpty()||file.getOriginalFilename() != null){
			String sourceFile = folder + File.separator + realFileName;

			byte[] bytes;
			try {
				bytes = file.getBytes();
		    	int bufferSize = bytes.length;
		        BufferedOutputStream buffStream = 
		        		new BufferedOutputStream(new FileOutputStream(sourceFile));
		        buffStream.write(bytes);
		        buffStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("upload",e.fillInStackTrace());
			}
			
			return realFileName;
		}else{
			logger.error("upload not data" );
		}
		
		return null;
	}
	
	public static String readFileToString (String filePath){
		File file = new File(filePath);  
		return readFileToString(file);
	}
	
	public static String readFileToString (File file){
		
		String content; 
		
		try {
			content = FileUtils.readFileToString(file, "UTF8");
			
		} catch (Exception e) {
			// TODO: handle exception
			content = "";
		}
		
		return content;
	}

		
	public static void writerFile (String filePath, String content){
		
		File file = new File(filePath);
		writerFile(file, content);
	}
	
	public static void writerFile (File file, String content){
		try {
			FileUtils.writeStringToFile(file, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("writerFile:" + file.getPath(),e.fillInStackTrace());
		}
	}
	
	public static void copyDirectory(String sourcefolder, String destfolder){
		
		File source = new File(sourcefolder);
		File dest = new File(destfolder);
		copyDirectory(source, dest);
	}
	
	public static void copyDirectory(File sourcefolder, File destfolder){
		
		try {
			

			if (!destfolder.exists())
				destfolder.mkdirs();

			FileUtils.copyDirectory(sourcefolder, destfolder, true);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("copyDirectory:" + sourcefolder.getPath(),e.fillInStackTrace());
		}
	}
			
//	public static Boolean createHTMLFromContent(String contextPath, String sourcePath, String branch, ArticleForm dataForm, String content){
//		
//		//複製 EDIT 附加檔案
//		File sourcefolder = new File(EditDocUtil.parseFilePath(false, false, contextPath, sourcePath));
//        
//		//contextPath + ArticleService.FIXED_ROOT_PATH + File.separator
//		String destPath = EditDocUtil.parseFilePath(true, true, 
//				ArticleService.FIXED_ROOT_PATH, dataForm.getSpecialPath(), 
//				ArticleService.FIXED_HTML_PATH, branch, dataForm.getCreateDate());				
//		File destfolder = new File(contextPath + destPath);
//
//        EditDocUtil.copyDirectory(sourcefolder, destfolder);
//        
//        String webSiteUrl = getPropertie(PropertieName.DOMAIN_URL);
//        String sourceUrl = webSiteUrl + "tpu" + sourcePath.replace(File.separator, "/");
//        String destUrl = webSiteUrl + "tpu" + destPath.replace(File.separator, "/");
//        
//        //XXXXXX.html 附件顯示原始位置
//        String filePath = destfolder.getParent() + File.separator + dataForm.getCreateDate()+ ArticleService.FIXED_HTML_EXTENSION;
//        //String originHtml = generateContentToHtml(dataForm, content, false).toString();
//        String originHtml ="<html><body>"+content+"</body></html>";
//        originHtml = originHtml.replaceAll(OnlineEditService.HTML_DOMAIN_NAME_URL, webSiteUrl);
//        EditDocUtil.writerFile(filePath, originHtml.replaceAll(OnlineEditService.HTML_FRIENDLY_HTML_LOGO, ""));
//
//        //copy html 鎖滑鼠鍵, 附件URL換成自己
//        filePath = destfolder.getParent() + File.separator + dataForm.getCreateDate()+ArticleService.FIXED_HTML_COPY_FILENAME_APPEND + ArticleService.FIXED_HTML_EXTENSION;
//        String copyHtml = originHtml;
//    	copyHtml = copyHtml.replace(sourceUrl, "/tpu" + destPath.replace(File.separator, "/"));
//
//        copyHtml = copyHtml.replace(ArticleService.HTML_COPY_PREVENT_RIGHT_CLICK_TARGET, ArticleService.HTML_COPY_PREVENT_RIGHT_CLICK_REPLACEMENT);
//        EditDocUtil.writerFile(filePath, copyHtml.replaceAll(OnlineEditService.HTML_FRIENDLY_HTML_LOGO, ""));
//
//        //friendly html 加 logo
//        filePath = destfolder.getParent() + File.separator + dataForm.getCreateDate() + ArticleService.FIXED_HTML_FRIENDLY_FILENAME_APPEND + ArticleService.FIXED_HTML_EXTENSION;
//        String friendlyhtml = copyHtml;
//        if (dataForm.getIsShare())
//        	friendlyhtml = friendlyhtml.replaceAll(OnlineEditService.HTML_FRIENDLY_HTML_LOGO, OnlineEditService.HTML_FRIENDLY_HTML_LOGO_REPLACEMENT);
//        else
//        	friendlyhtml = friendlyhtml.replaceAll(OnlineEditService.HTML_FRIENDLY_HTML_LOGO, OnlineEditService.HTML_FRIENDLY_HTML_LOGO_REPLACEMENT_NOSHARE);
//        
//        friendlyhtml = friendlyhtml.replaceAll(OnlineEditService.HTML_DOMAIN_NAME_URL, webSiteUrl);
//        friendlyhtml = friendlyhtml.replaceAll(OnlineEditService.HTML_SHARE_TO_FB_URL, getPropertie(PropertieName.FB_SHARE_LINK));
//        
//        EditDocUtil.writerFile(filePath, friendlyhtml);
//        	
//        File file = new File(destPath);
//        dataForm.setViewHtml(file.getParent() + File.separator + dataForm.getCreateDate()+ ArticleService.FIXED_HTML_COPY_FILENAME_APPEND +ArticleService.FIXED_HTML_EXTENSION);
//        dataForm.setFriendlyHtml(dataForm.getCreateDate() + ArticleService.FIXED_HTML_FRIENDLY_FILENAME_APPEND + ArticleService.FIXED_HTML_EXTENSION);
//        return true;
//	}
//	
//	private static StringBuilder generateContentToHtml(ArticleForm dataForm, String content, boolean showLogo){
//		StringBuilder sbContent = new StringBuilder();
//        
//		String friendly_header_replacement = getReplacement(dataForm);
//		
//		sbContent.append("<!DOCTYPE html>" +"\n");
//		sbContent.append("<html>" +"\n");
//		sbContent.append("<head>");
//		sbContent.append("  <meta charset=\"utf-8\" />" +"\n");
//		sbContent.append("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" +"\n");
//		sbContent.append("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">" +"\n");
//		sbContent.append(friendly_header_replacement +"\n");
//		sbContent.append("  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />" +"\n");
//		sbContent.append("  <title>" + dataForm.getAtcTopic() + "</title>" +"\n");
//		sbContent.append("  <link href=\"" + OnlineEditService.HTML_DOMAIN_NAME_URL + "tpu/css/edit/layoutdisplay.css\" rel=\"stylesheet\" />" +"\n");
//		sbContent.append("  <link href=\"" + OnlineEditService.HTML_DOMAIN_NAME_URL + "tpu/css/edit/bootstrap.css\" rel=\"stylesheet\" />" +"\n");
//		sbContent.append("  <link href=\"" + OnlineEditService.HTML_DOMAIN_NAME_URL + "tpu/css/edit/bootstrap-theme.css\" rel=\"stylesheet\" />" +"\n");
//		sbContent.append("  <link href=\"" + OnlineEditService.HTML_DOMAIN_NAME_URL + "tpu/css/edit/prism.css\" rel=\"stylesheet\" />" +"\n");
//		sbContent.append("  <script src=\"" + OnlineEditService.HTML_DOMAIN_NAME_URL + "tpu/js/edit/jquery-2.2.2.js\"></script>");
//		sbContent.append("  <script src=\"" + OnlineEditService.HTML_DOMAIN_NAME_URL + "tpu/js/edit/jquery-ui-1.11.4.js\"></script>" +"\n");
//		sbContent.append("  <script src=\"" + OnlineEditService.HTML_DOMAIN_NAME_URL + "tpu/js/edit/bootstrap.js\"></script>");
//		sbContent.append("  <script src=\"" + OnlineEditService.HTML_DOMAIN_NAME_URL + "tpu/js/edit/paging.js\"></script>" +"\n");
//		sbContent.append("  <script src=\"" + OnlineEditService.HTML_DOMAIN_NAME_URL + "tpu/js/edit/loadTablePaging.js\"></script>" +"\n");
//		sbContent.append("  <script src=\"" + OnlineEditService.HTML_DOMAIN_NAME_URL + "tpu/js/edit/prism.js\"></script>" +"\n");
//		sbContent.append("  <script>$(function(){ initTablePaging(); });</script>" +"\n");
//		
//		sbContent.append("  <!-- Google Tag Manager -->");
//		sbContent.append("  <script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':");
//		sbContent.append("  new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],");
//		sbContent.append("  j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=");
//		sbContent.append("  'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);");
//		sbContent.append("  })(window,document,'script','dataLayer','GTM-NCD9ZPM');</script>");
//		sbContent.append("  <!-- End Google Tag Manager -->");
//		
//		sbContent.append("</head>" +"\n");
//		sbContent.append("<body class='editbody'>" +"\n");
//		
//		sbContent.append("  <!-- Google Tag Manager (noscript) -->");
//		sbContent.append("  <noscript><iframe src=\"https://www.googletagmanager.com/ns.html?id=GTM-NCD9ZPM\"");
//		sbContent.append("  height=\"0\" width=\"0\" style=\"display:none;visibility:hidden\"></iframe></noscript>");
//		sbContent.append("  <!-- End Google Tag Manager (noscript) --> ");
//		
//		sbContent.append(OnlineEditService.HTML_FRIENDLY_HTML_LOGO);
//		sbContent.append("  <div style=\"width:595.0pt;margin-bottom:42.0pt;margin-top:42.0pt;margin-left:28.0pt;margin-right:28.0pt;\">" +"\n");
//		sbContent.append(getArticleInfo(dataForm)+"<p><br/></p>"+content +"\n");
//		sbContent.append("  </div>" +"\n");
//		sbContent.append("</body>" +"\n");
//		sbContent.append("</html>");
//
//		return sbContent;
//	}
//	
//	private static String getArticleInfo(ArticleForm dataForm) {
//		StringBuffer html=new StringBuffer();
//		
//		html.append("<h1 class=\"text-center\" align=\"center\" style=\"color:#296684\">"+dataForm.getAtcTopic()+"</h1>");
//		html.append("<p><br/></p>");
//		html.append("<table border=\"0\" class=\"table table-bordered\" style=\"width: 100%; border-collapse: separate; border-spacing: 1px;\">");
//		html.append("      <tr>");
//		html.append("         <td bgcolor=\"#DCDCDC\" style=\"width: 20%; text-align: center;\">");
//		html.append("            <p><span style=\"font-size:14.0pt;\">簡介</span></p>");
//		html.append("         </td>");
//		html.append("         <td bgcolor=\"#DCDCDC\" style=\"width: 80%;\">");
//		html.append("			<span style=\"font-size:14.0pt;\">"+dataForm.getAtcBrief()+"</span>");
//		html.append("         </td>");
//		html.append("      </tr>");
//		html.append("      <tr>");
//		html.append("         <td bgcolor=\"#DCDCDC\" style=\"width: 20%; text-align: center;\">");
//		html.append("            <p><span style=\"font-size:14.0pt;\">作者</span></p>");
//		html.append("         </td>");
//		html.append("         <td bgcolor=\"#DCDCDC\" style=\"width: 80%;\">");
//		html.append("			<span style=\"font-size:14.0pt;\">"+dataForm.getAuthorName()+"</span>");
//		html.append("         </td>");
//		html.append("      </tr>");
//		html.append("</table>");
//		return html.toString();
//	}
}
