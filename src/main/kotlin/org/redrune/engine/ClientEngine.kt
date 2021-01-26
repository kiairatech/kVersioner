package org.redrune.engine

import java.util.concurrent.Executors

/**
 * @author Tyluur <itstyluur@icloud.com>
 * @since January 26, 2021
 */
object ClientEngine {

    /**
     * The executors for the engine
     */
    val executors = Executors.newScheduledThreadPool(1)
}