package org.redrune.utility;

import java.io.File;

/**
 * @author Tyluur <itstyluur@gmail.com>
 * @since 3/1/2016
 */
public class DirectoryManager {

	public static void mkdirs() {
		getFile(Constants.GAMEPACK_LOCATION).getParentFile().mkdirs();
	}

	public static File getFile(String loc) {
		return new File(loc);
	}
}
