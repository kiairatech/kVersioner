package org.redrune.loader

import org.redrune.utility.Constants
import java.io.File
import java.awt.Desktop
import java.io.IOException
import java.lang.Exception

object ClientDispatcher {
    @JvmStatic
	fun open() {
        try {
            val jar = File(Constants.GAMEPACK_LOCATION)
            val directory = jar.absolutePath
            val osName = System.getProperty("os.name").toLowerCase()
            val desktopSupported = Desktop.isDesktopSupported()
            println("Attempting to execute on " + osName + " with" + (if (desktopSupported) "" else "out") + " desktop browsing supported.")
            if (desktopSupported) {
                Desktop.getDesktop().browse(jar.toURI())
            } else {
                val isMac = osName.contains("mac")
                if (isMac) {
                    val arrayOfString = arrayOf("osascript", "-e", "open location \"" + jar.absolutePath + "\"")
                    try {
                        Runtime.getRuntime().exec(arrayOfString)
                    } catch (ex: IOException) {
                        System.err.println("Desktop not supported, IOException occurred while opening url (osascript): $directory")
                    }
                } else {
                    try {
                        Runtime.getRuntime().exec("xdg-open $directory")
                    } catch (ex2: IOException) {
                        System.err.println("Desktop not supported, IOException occurred while opening url (xdg-open): $directory")
                    }
                }
            }
            System.exit(-1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}