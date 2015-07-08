/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.awt.image.BufferedImage;

/**
 * @author M@n!$H
 *
 */
public class ImagePopertyBM {

	BufferedImage bufferedImage;
	String fileName;
	String filePath;
	
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
