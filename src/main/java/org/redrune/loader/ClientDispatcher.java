package org.redrune.loader;

import org.redrune.utility.Constants;

import java.awt.*;
import java.io.*;

public class ClientDispatcher {

	public static void open() {
		try {
			final File jar = new File(Constants.GAMEPACK_LOCATION);
			final String directory = jar.getAbsolutePath();
			final String osName = System.getProperty("os.name").toLowerCase();
			boolean desktopSupported = Desktop.isDesktopSupported();
			System.out.println("Attempting to execute on " + osName + " with"+ (desktopSupported ? "" : "out") + " desktop browsing supported.");
			if (desktopSupported) {
				Desktop.getDesktop().browse(jar.toURI());
			} else {
				final boolean isMac = osName.contains("mac");
				if (isMac) {
					final String[] arrayOfString = { "osascript", "-e", "open location \"" + jar.getAbsolutePath() + "\"" };
					try {
						Runtime.getRuntime().exec(arrayOfString);
					} catch (IOException ex) {
						System.err.println("Desktop not supported, IOException occurred while opening url (osascript): " + directory);
					}
				} else {
					try {
						Runtime.getRuntime().exec("xdg-open " + directory);
					} catch (IOException ex2) {
						System.err.println("Desktop not supported, IOException occurred while opening url (xdg-open): " + directory);
					}
				}
			}
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
