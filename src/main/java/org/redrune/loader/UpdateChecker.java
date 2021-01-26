package org.redrune.loader;

import org.redrune.utility.Constants;
import org.redrune.utility.DirectoryManager;
import org.redrune.utility.WebpageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class UpdateChecker {

	private static final UpdateChecker INSTANCE = new UpdateChecker();

	private boolean updateRequired = false;

	public UpdateChecker() {
		String fileHash = getFileHash();
		String urlHash = null;
		try {
			urlHash = getURLHash();
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateRequired = !fileHash.equals(urlHash);
		System.out.println("fileHash=" + fileHash + ", urlHash=" + urlHash);
	}

	public String getFileHash() {
		try {
			if (DirectoryManager.getFile(Constants.GAMEPACK_LOCATION).exists()) {
				byte[] b = createChecksum(DirectoryManager.getFile(Constants.GAMEPACK_LOCATION));
				String result = "";
				byte[] arrayOfByte1;
				int j = (arrayOfByte1 = b).length;
				for (int i = 0; i < j; i++) {
					byte aB = arrayOfByte1[i];
					result = result + Integer.toString((aB & 0xFF) + 256, 16).substring(1);
				}
				return result;
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getURLHash() throws IOException {
		return WebpageUtils.getText(Constants.WEBSITE_MD5_URL).get(0);
	}

	private byte[] createChecksum(File file) throws Exception {
		InputStream fis = new FileInputStream(file);
		byte[] buffer = new byte['?'];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;
		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);
		fis.close();
		return complete.digest();
	}

	public static UpdateChecker get() {
		return INSTANCE;
	}

	public boolean updateRequired() {
		return updateRequired;
	}
}
