package org.redrune.utility;

import java.io.File;

/**
 * @author Tyluur <itstyluur@gmail.com>
 * @since 3/1/2016
 */
public class Constants {

	/**
	 * The name of the loader's server
	 */
	public static final String LOADER_NAME = "Dusk";

	/**
	 * The system separator
	 */
	private static final String SEPARATOR = File.separator;

	/**
	 * The directory all client loader resources will be saved in
	 */
	public static final String RESOURCE_DIRECTORY = System.getProperty("user.home") + SEPARATOR + LOADER_NAME + "Loader";

	/**
	 * The location of the gamepack when downloaded
	 */
	public static final String GAMEPACK_LOCATION = RESOURCE_DIRECTORY + SEPARATOR + "gamepack.jar";

	/**
	 * The url to download the client from
	 */
	public static final String CLIENT_URL = "https://dusk.rs/client/dusk-1.0.1.jar";

}
