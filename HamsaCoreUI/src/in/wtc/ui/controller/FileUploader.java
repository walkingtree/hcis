package in.wtc.ui.controller;

import in.wtc.hcis.bm.base.ImagePopertyBM;
import in.wtc.hcis.bo.constants.ImageConstants;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.directwebremoting.io.FileTransfer;
import org.springframework.web.context.ServletContextAware;

public class FileUploader implements ServletContextAware
{
	
	private ResourceBundle ImagesConfig = ResourceBundle.getBundle("in.wtc.hcis.bo.properties.Image", Locale.getDefault());
	private ServletContext servletContext;
	
    public ImagePopertyBM uploadFile(FileTransfer uploadImage) throws IOException {
    	
    	InputStream in = uploadImage.getInputStream();
    	String mimeType = uploadImage.getMimeType();
    	
    	if (!FileUploader.canReadMimeType(mimeType))
    		throw new IOException("File type not suppported");
    	
    	BufferedImage image = ImageIO.read(in);

    	AffineTransform atx = new AffineTransform();
    	atx.scale(200d / image.getWidth(), 200d / image.getHeight());
    	AffineTransformOp afop = new AffineTransformOp(atx, AffineTransformOp.TYPE_BILINEAR);
    	image = afop.filter(image, null);

    	
    	String fileName =  Calendar.getInstance().getTimeInMillis() + uploadImage.getFilename();
    	String[] filePart = mimeType.split("/");
    	
    	String tmpImageDirPath = servletContext.getRealPath("/") +
		ImagesConfig.getString(ImageConstants.IMAGES_DIR_PATH).replaceAll("/", File.separator) ;

    	String fullPath = tmpImageDirPath +File.separator+ fileName;
    	
    	File out = new File( fullPath );
    	
    	
    	ImageIO.write(image, filePart[filePart.length - 1], out);
    	
    	ImagePopertyBM imagePopertyBM = new ImagePopertyBM();
    	
    	imagePopertyBM.setBufferedImage( image );
    	imagePopertyBM.setFileName(fileName);
    	imagePopertyBM.setFilePath(tmpImageDirPath);
    	
    	
    	return imagePopertyBM;
    }
    
    // Returns true if the specified mime type can be read
    private static boolean canReadMimeType(String mimeType) {
        Iterator iter = ImageIO.getImageReadersByMIMEType(mimeType);
        return iter.hasNext();
    }
    
    // Returns true if the specified mime type can be written
    private static boolean canWriteMimeType(String mimeType) {
        Iterator iter = ImageIO.getImageWritersByMIMEType(mimeType);
        return iter.hasNext();
    }

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}


}
