package org.redrune.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebpageUtils {

	public static List<String> getText(String page) throws IOException {
		List<String> text = new ArrayList<>();
		URL url = new URL(page);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "Mozilla Firefox");
		connection.setDoOutput(true);
		connection.setDoInput(true);

		InputStream input;
		if (connection.getResponseCode() >= 400) {
			input = connection.getErrorStream();
		} else {
			input = connection.getInputStream();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line;
		while ((line = reader.readLine()) != null) {
			text.add(line);
		}
		reader.close();
		return text;
	}
}
