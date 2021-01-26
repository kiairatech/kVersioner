package org.redrune.utility

import kotlin.Throws
import java.io.IOException
import java.io.BufferedReader
import java.security.NoSuchAlgorithmException
import java.security.MessageDigest
import org.apache.commons.codec.binary.Hex
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.ArrayList

object WebpageUtils {
    @Throws(IOException::class)
    fun getText(page: String?): List<String> {
        val text: MutableList<String> = ArrayList()
        val url = URL(page)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("User-Agent", "Mozilla Firefox")
        connection.doOutput = true
        connection.doInput = true
        val input: InputStream
        input = if (connection.responseCode >= 400) {
            connection.errorStream
        } else {
            connection.inputStream
        }
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