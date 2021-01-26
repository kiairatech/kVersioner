package org.redrune

import org.redrune.loader.ClientDispatcher.open
import org.redrune.loader.ClientDownloader.Companion.initializeDownload
import org.redrune.loader.States
import org.redrune.utility.Constants
import org.redrune.utility.DirectoryManager.mkdirs
import java.awt.EventQueue
import java.awt.Font
import javax.swing.*

/**
 * @author Tyluur<itstyluur@icloud.com>
 * @since 3/1/2016
 */
object Launcher : JFrame() {

    private fun initComponents() {
        titleLabel = JLabel()
        downloadProgressBar = JProgressBar()
        downloadProgressBar!!.isStringPainted = true
        launchButton = JButton()
        defaultCloseOperation = EXIT_ON_CLOSE
        titleLabel!!.font = Font("Tahoma", 0, 36) // NOI18N
        titleLabel!!.text = "Play"
        launchButton!!.text = "Launch"
        launchButton!!.addActionListener { loadClient() }
        val layout = GroupLayout(contentPane)
        contentPane.layout = layout
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE.toInt())
                    .addComponent(downloadProgressBar, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
            ).addGroup(
                layout.createSequentialGroup().addGroup(
                    layout.createParallelGroup(
                        GroupLayout.Alignment.LEADING
                    ).addGroup(
                        layout.createSequentialGroup().addGap(140, 140, 140)
                            .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                    ).addGroup(
                        layout.createSequentialGroup().addGap(135, 135, 135)
                            .addComponent(launchButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                    )
                ).addContainerGap(
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE.toInt()
                )
            )
        )
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addComponent(titleLabel).addPreferredGap(
                    LayoutStyle.ComponentPlacement.RELATED,
                    GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE.toInt()
                ).addComponent(downloadProgressBar, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(launchButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()
            )
        )
        pack()
        title = Constants.LOADER_NAME
        setLocationRelativeTo(null)
    } // </editor-fold>

    var state: States? = null

    private var downloadProgressBar: JProgressBar? = null
    private var launchButton: JButton? = null
    private var titleLabel: JLabel? = null
    fun updateText(text: String?) {
        downloadProgressBar!!.string = text
    }

    private fun loadClient() {
        if (state === States.READY) {
            dispose()
            open()
        } else {
            JOptionPane.showMessageDialog(null, "The client is still being updated...")
        }
    }

    /**
     * @param args
     * the command line arguments
     */
    @Throws(
        ClassNotFoundException::class,
        UnsupportedLookAndFeelException::class,
        InstantiationException::class,
        IllegalAccessException::class
    )

    @JvmStatic
    fun main(args: Array<String>) {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        EventQueue.invokeLater { Launcher.isVisible = true }
    }

    /**
     * Creates new form Launcher
     */
    init {
        initComponents()
        mkdirs()
        initializeDownload(downloadProgressBar!!)
    }
}