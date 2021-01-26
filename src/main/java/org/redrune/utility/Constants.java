package org.redrune.utility;

import java.io.File;

/**
 * @author Tyluur <itstyluur@gmail.com>
 * @since 3/1/2016
 */
public class Constants {

	/**
	 * The name of the org.redrune.loader's server
	 */
	public static final String LOADER_NAME = "Lotica";

	/**
	 * The system separator
	 */
	private static final String SEPARATOR = File.separator;

	/**
	 * The directory all client org.redrune.loader resources will be saved in
	 */
	public static final String RESOURCE_DIRECTORY = System.getProperty("user.home") + SEPARATOR + "LoticaLoader";

	/**
	 * The location of the gamepack when downloaded
	 */
	public static final String GAMEPACK_LOCATION = RESOURCE_DIRECTORY + SEPARATOR + "gamepack.jar";

	/**
	 * The url to download the client from
	 */
	public static final String CLIENT_URL = "http://lotica.soulplayps.com/services/client/client.jar";

	/**
	 * The url to check for the online md5 client hash
	 */
	public static final String WEBSITE_MD5_URL = "http://lotica.soulplayps.com/services/client/clientmd5.php";

}
