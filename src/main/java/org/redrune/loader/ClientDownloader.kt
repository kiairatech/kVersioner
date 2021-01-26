package org.redrune.loader;

import org.redrune.Launcher;
import org.redrune.utility.Constants;
import org.redrune.utility.DirectoryManager;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

/**
 * @author Tyluur <itstyluur@gmail.com>
 * @since 3/1/2016
 */
public class ClientDownloader implements Runnable {

	private final JProgressBar progressBar;

	public ClientDownloader(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public static void initializeDownload(JProgressBar bar) {
		Executors.newSingleThreadExecutor().submit(new ClientDownloader(bar));
	}

	@Override
	public void run() {
		UpdateChecker checker = new UpdateChecker();
		System.out.println(checker.updateRequired() ? "Update is required" : "No update is required");
		if (checker.updateRequired()) {
			Launcher.get().setState(States.LOADING);
			Launcher.get().updateText(Constants.LOADER_NAME + " is currently downloading.");
			download();
		} else {
			onComplete();
		}
	}

	/**
	 * Downloads the client
	 */
	private void download() {
		BufferedInputStream in = null;
		FileOutputStream out = null;
		try {
			int count;
			URL url = new URL(Constants.CLIENT_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			setBarSize(url);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla Firefox");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			in = new BufferedInputStream(connection.getInputStream());
			out = new FileOutputStream(Constants.GAMEPACK_LOCATION);
			byte[] data = new byte[1024];
			File file = DirectoryManager.getFile(Constants.GAMEPACK_LOCATION);
			while ((count = in.read(data, 0, 1024)) != -1) {
				out.write(data, 0, count);
				int newValue = (int) file.length();
				progressBar.setValue(newValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.onComplete();
		}
	}

	/**
	 * Sets the maximum size of the progress bar
	 *
	 * @param url
	 * 		The url
	 */
	private void setBarSize(URL url) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)");
			this.progressBar.setMaximum(conn.getContentLength());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			assert (conn != null);
			conn.disconnect();
		}
	}

	/**
	 * Handles what to do when the download is complete
	 */
	private void onComplete() {
		Launcher.get().setState(States.READY);
		Launcher.get().updateText(Constants.LOADER_NAME + " is ready to launch!");
		progressBar.setValue(progressBar.getMaximum());
	}
}
