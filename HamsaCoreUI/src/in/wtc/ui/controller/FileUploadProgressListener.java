package in.wtc.ui.controller;

import java.text.NumberFormat;

import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUploadProgressListener implements ProgressListener {
	private static Log log = LogFactory.getLog(FileUploadProgressListener.class);

	private static long bytesTransferred = 0;

	private static long fileSize = -100;

	private long tenKBRead = -1;

	public FileUploadProgressListener() {
	}

	public String getFileUploadStatus() {
		// per looks like 0% - 100%, remove % before submission
		String per = NumberFormat.getPercentInstance().format(
				(double) bytesTransferred / (double) fileSize);
		return per.substring(0, per.length() - 1);
	}

	public void update(long bytesRead, long contentLength, int items) {
		// update bytesTransferred and fileSize (if required) every 10 KB is
		// read
		long tenKB = bytesRead / 10240;
		if (tenKBRead == tenKB)
			return;
		tenKBRead = tenKB;

		bytesTransferred = bytesRead;
		if (fileSize != contentLength)
			fileSize = contentLength;
	}

}
