package org.redrune.utility

import org.apache.commons.codec.binary.Hex
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

/*
 * @author Tyluur<itstyluur@icloud.com>
 * @since 3/1/2016
 */
object WebpageUtils {

    fun getStream(path: String): InputStream {
        val url = URL(path)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("User-Agent", "Mozilla Firefox")
        connection.doOutput = true
        connection.doInput = true
        try {
            return if (connection.responseCode >= 400) {
                connection.errorStream
            } else {
                connection.inputStream
            }
        } catch (e: Exception) {
            throw IllegalStateException(e)
        }

    }


    @Throws(IOException::class)
    fun getText(page: String?): List<String> {
        val text: MutableList<String> = ArrayList()
        val input = page?.let { getStream(it) }
        val reader = BufferedReader(InputStreamReader(input))
        var line: String
        while (reader.readLine().also { line = it } != null) {
            text.add(line)
        }
        reader.close()
        return text
    }

    @Throws(NoSuchAlgorithmException::class, IOException::class)
    fun getDigest(`is`: InputStream, md: MessageDigest, byteArraySize: Int): String {
        md.reset()
        val bytes = ByteArray(byteArraySize)
        var numBytes: Int
        while (`is`.read(bytes).also { numBytes = it } != -1) {
            md.update(bytes, 0, numBytes)
        }
        val digest = md.digest()
        return String(Hex.encodeHex(digest))
    }
}