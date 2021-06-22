package com.tyluur.utility

import java.io.File

/**
 * @author Tyluur<itstyluur@icloud.com>
 * @since 3/1/2016
 */
object DirectoryManager {
    @JvmStatic
	fun mkdirs() {
        getFile(Constants.GAMEPACK_LOCATION).parentFile.mkdirs()
    }

    fun getFile(loc: String): File {
        return File(loc)
    }
}