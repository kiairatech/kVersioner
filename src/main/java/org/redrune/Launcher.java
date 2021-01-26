package org.redrune;

import org.redrune.loader.ClientDispatcher;
import org.redrune.loader.ClientDownloader;
import org.redrune.loader.States;
import org.redrune.utility.DirectoryManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author EliteBook
 */
public class Launcher extends JFrame {
	
	/**
	 * Creates new form Launcher
	 */
	public Launcher() {
		initComponents();
		DirectoryManager.mkdirs();
		ClientDownloader.initializeDownload(downloadProgressBar);
	}
	
	private void initComponents() {

		titleLabel = new JLabel();
		downloadProgressBar = new JProgressBar();
		downloadProgressBar.setStringPainted(true);
		launchButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		titleLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		titleLabel.setText("Lotica");

		launchButton.setText("Launch");
		launchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				loadClient();
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(downloadProgressBar, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(140, 140, 140).addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addGap(135, 135, 135).addComponent(launchButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(titleLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(downloadProgressBar, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(launchButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE).addContainerGap()));

		pack();
		setTitle("Lotica Loader");
		setLocationRelativeTo(null);

		/*oldschoolButton = new JButton();
		onyxLabel = new JLabel();
		newschoolButton = new JButton();
		progressBar = new JProgressBar();
		progressBar.setBorderPainted(false);
		progressLabel = new JTextField();
		
		oldschoolButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadClient();
			}
		});
		newschoolButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadClient();
			}
		});
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		oldschoolButton.setText("Play RS2007 Graphics");
		
		onyxLabel.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
		onyxLabel.setText(Constants.LOADER_NAME);
		
		newschoolButton.setText("Play RS2012 Graphics");
		progressLabel.setEditable(false);
		progressLabel.setHorizontalAlignment(JTextField.CENTER);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(progressBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(oldschoolButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE).addComponent(newschoolButton)).addGroup(layout.createSequentialGroup().addGap(138, 138, 138).addComponent(onyxLabel).addGap(0, 0, Short.MAX_VALUE))).addContainerGap()).addComponent(progressLabel));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(onyxLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(progressLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(4, 4, 4).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(newschoolButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addComponent(oldschoolButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)).addContainerGap()));
		updateText(Constants.LOADER_NAME);
		pack();
		setLocationRelativeTo(null);*/
	}// </editor-fold>
	
	/**
	 * @param args
	 * 		the command line arguments
	 */
	public static void main(String args[]) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Launcher.get().setVisible(true);
			}
		});
	}
	
	private static Launcher singleton;
	
	private States state;
	
	private JProgressBar downloadProgressBar;
	
	private JButton launchButton;
	
	private JLabel titleLabel;
	
	public void updateText(String text) {
		downloadProgressBar.setString(text);
	}
	
	private void loadClient() {
		if (state == States.READY) {
			dispose();
			ClientDispatcher.open();
		} else {
			JOptionPane.showMessageDialog(null, "The client is still being updated...");
		}
	}
	
	public static Launcher get() {
		if (singleton == null) {
			singleton = new Launcher();
		}
		return singleton;
	}
	
	public void setState(States state) {
		this.state = state;
	}
	
}
