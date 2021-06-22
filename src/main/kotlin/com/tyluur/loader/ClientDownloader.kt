package com.tyluur.loader

import com.tyluur.Launcher
import com.tyluur.utility.Constants
import com.tyluur.utility.DirectoryManager
import com.tyluur.utility.WebpageUtils
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import javax.swing.JProgressBar

/*
 * @author Tyluur<itstyluur@icloud.com>
 * @since 3/1/2016
 */
class ClientDownloader(private val progressBar: JProgressBar) : Runnable {
    override fun run() {
        val checker = UpdateChecker()
        println(if (checker.updateRequired()) "Update is required" else "No update is required")
        if (checker.updateRequired()) {
            Launcher.state = States.LOADING
            Launcher.updateText(Constants.LOADER_NAME + " is currently downloading.")
            download()
        } else {
            onComplete()
        }
    }

    /**
     * Downloads the client
     */
    private fun download() {
        var `in`: BufferedInputStream? = null
        var out: FileOutputStream? = null
        try {
            var count: Int
            val url = URL(Constants.CLIENT_URL)
            setBarSize(url)
            val inputStream = WebpageUtils.getStream(Constants.CLIENT_URL)
            `in` = BufferedInputStream(inputStream)
            out = FileOutputStream(Constants.GAMEPACK_LOCATION)
            val data = ByteArray(1024)
            val file = DirectoryManager.getFile(Constants.GAMEPACK_LOCATION)
            while (`in`.read(data, 0, 1024).also { count = it } != -1) {
                out.write(data, 0, count)
                val newValue = file.length().toInt()
                progressBar.value = newValue
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                `in`?.close()
                out?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            onComplete()
        }
    }

    /**
     * Sets the maximum size of the progress bar
     *
     * @param url
     * The url
     */
    private fun setBarSize(url: URL) {
        var conn: HttpURLConnection? = null
        try {
            conn = url.openConnection() as HttpURLConnection
            conn.addRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)"
            )
            progressBar.maximum = conn.contentLength
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            assert(conn != null)
            conn!!.disconnect()
        }
    }

    /**
     * Handles what to do when the download is complete
     */
    private fun onComplete() {
        Launcher.state = States.READY
        Launcher.updateText(Constants.LOADER_NAME + " is ready to launch!")
        progressBar.value = progressBar.maximum
    }

    companion object {
        @JvmStatic
        fun initializeDownload(bar: JProgressBar) {
            Executors.newSingleThreadExecutor().submit(ClientDownloader(bar))
        }
    }
}