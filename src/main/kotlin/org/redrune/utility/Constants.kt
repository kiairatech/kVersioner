package org.redrune.utility

import java.io.File

/**
 * @author Tyluur<itstyluur@icloud.com>
 * @since 3/1/2016
 */
object Constants {
    /**
     * The name of the loader's server
     */
    const val LOADER_NAME = "Dusk"

    /**
     * The system separator
     */
    private val SEPARATOR = File.separator

    /**
     * The directory all client loader resources will be saved in
     */
    val RESOURCE_DIRECTORY = System.getProperty("user.home") + SEPARATOR + LOADER_NAME + "Loader"

    /**
     * The location of the gamepack when downloaded
     */
	@JvmField
	val GAMEPACK_LOCATION = RESOURCE_DIRECTORY + SEPARATOR + "gamepack.jar"

    /**
     * The url to download the client from
     */
    const val CLIENT_URL = "https://dusk.rs/client/dusk-1.0.1.jar"
}