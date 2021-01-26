package org.redrune.loader

import kotlin.Throws
import java.io.IOException
import java.security.NoSuchAlgorithmException
import java.security.MessageDigest
import org.redrune.utility.WebpageUtils
import java.io.File
import java.io.FileInputStream
import org.redrune.loader.UpdateChecker
import org.redrune.utility.Constants
import org.redrune.utility.DirectoryManager
import java.io.InputStream
import java.lang.Exception
import java.net.URL
import kotlin.experimental.and

class UpdateChecker {
    private var updateRequired = false
    val fileHash: String
        get() {
            try {
                if (DirectoryManager.getFile(Constants.GAMEPACK_LOCATION).exists()) {
                    val b = createChecksum(DirectoryManager.getFile(Constants.GAMEPACK_LOCATION))
                    var result = ""
                    var arrayOfByte1: ByteArray
                    val j = b.also { arrayOfByte1 = it }.size
                    for (i in 0 until j) {
                        val aB = arrayOfByte1[i]
                        result = result + Integer.toString((aB and 0xFF.toByte()).toInt() + 256, 16).substring(1)
                    }
                    return result
                }
                return ""
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }

    //		return WebpageUtils.getText(Constants.WEBSITE_MD5_URL).get(0);
    @get:Throws(IOException::class, NoSuchAlgorithmException::class)
    val uRLHash: String
        get() {
            val url = URL(Constants.CLIENT_URL)
            val `is` = url.openStream()
            val md = MessageDigest.getInstance("MD5")
            val digest = WebpageUtils.getDigest(`is`, md, 2048)
            println("MD5 Digest:$digest")
            //		return WebpageUtils.getText(Constants.WEBSITE_MD5_URL).get(0);
            return digest
        }

    @Throws(Exception::class)
    private fun createChecksum(file: File): ByteArray {
        val fis: InputStream = FileInputStream(file)
        val buffer = ByteArray('?'.toInt())
        val complete = MessageDigest.getInstance("MD5")
        var numRead: Int
        do {
            numRead = fis.read(buffer)
            if (numRead > 0) {
                complete.update(buffer, 0, numRead)
            }
        } while (numRead != -1)
        fis.close()
        return complete.digest()
    }

    fun updateRequired(): Boolean {
        return updateRequired
    }

    companion object {
        private val INSTANCE = UpdateChecker()
        fun get(): UpdateChecker {
            return INSTANCE
        }
    }

    init {
        val fileHash = fileHash
        var urlHash: String? = null
        try {
            urlHash = uRLHash
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        updateRequired = fileHash != urlHash
        println("fileHash=$fileHash, urlHash=$urlHash")
    }
}