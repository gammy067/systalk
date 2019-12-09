package com.systalk.sys.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class ImgUtil.
 */
public class ImgUtil {

	private final static String PNG = "png";

	private final static String JPG = "jpg";

	/**
	 * 圖片壓縮.縮小處理.
	 *
	 * @param file 檔案
	 * @param qality 壓縮比例
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void compressPictureByQality(File file, float qality) throws IOException {

		String fileExtension = "";
		int i = file.getName().lastIndexOf('.');
		if (i > 0) {
			fileExtension = file.getName().substring(i + 1);
		}

		if (StringUtils.equalsIgnoreCase(PNG, fileExtension)) {
			File output = file;

			BufferedImage image = ImageIO.read(file);
			BufferedImage result = new BufferedImage(image.getWidth() / 2, image.getHeight() / 2, BufferedImage.TYPE_INT_RGB);
			result.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
			ImageIO.write(result, JPG, output);
		}else{
			BufferedImage src = null;
			FileOutputStream out = null;
			ImageWriter imgWrier;
			ImageWriteParam imgWriteParams;
		
			imgWrier = ImageIO.getImageWritersByFormatName(fileExtension).next();
			imgWriteParams = new JPEGImageWriteParam(null);
		
			imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
			
			imgWriteParams.setCompressionQuality(qality);
			imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
			ColorModel colorModel = ImageIO.read(file).getColorModel();// ColorModel.getRGBdefault();
			
			// imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
			// colorModel, colorModel.createCompatibleSampleModel(16, 16)));
			imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

			
			if (!file.exists()) {
				throw new FileNotFoundException("Not Found Img File,文件不存在");
			} else {
			
				src = ImageIO.read(file);
				// 縮小圖片
				src = zoomOutImage(src, 2);
				
				out = new FileOutputStream(file);

				imgWrier.reset();
				
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));
				
				imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * 縮小圖片.
	 * @author Richard
	 * @param originalImage 原始圖片
	 * @param scale         縮小倍数
	 * @return 縮小後的Image
	 */
	public static BufferedImage zoomOutImage(BufferedImage originalImage, Integer scale) {
		int width = originalImage.getWidth() / scale;
		int height = originalImage.getHeight() / scale;
		BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return newImage;
	}
}
