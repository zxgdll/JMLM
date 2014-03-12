package net.realqinwei.hzcrm.crm.util;

import java.io.*;

public final class FileUploader {

	public final static String UPLOAD_DIR = "/upload";

	public FileUploader() {

	}

	public void upload(String path, File file, String fileName) {

		InputStream is = null;
		OutputStream os = null;
		byte[] buffer = new byte[1024];
		int length = 0;
		try {
			is = new FileInputStream(file);
			os = new FileOutputStream(new File(path, fileName));
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			is.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
