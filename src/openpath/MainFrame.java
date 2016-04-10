/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openpath;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

/**
 *
 * @author cmlee
 */
public class MainFrame extends javax.swing.JFrame {

	private Future<Void> future = null;

	/**
	 * Creates new form MainFrame
	 */
	public MainFrame() {
		initComponents();

		Map<String, String> prefs = UserPreferences.load();
		chromeDrvTF.setText(prefs.get(UserPreferences.CHROME_DRIVER));
		chromeTF.setText(prefs.get(UserPreferences.CHROME_BROWSER));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        directoryChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        userListTF = new javax.swing.JTextField();
        userListBtn = new javax.swing.JButton();
        downloadDirTF = new javax.swing.JTextField();
        downloadDirBtn = new javax.swing.JButton();
        chromeDrvTF = new javax.swing.JTextField();
        chromeDrvBtn = new javax.swing.JButton();
        chromeTF = new javax.swing.JTextField();
        chromeBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        savePrefMI = new javax.swing.JMenuItem();
        goMI = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMI = new javax.swing.JMenuItem();

        directoryChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OpenPath Download");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        jLabel1.setText("User List:");

        jLabel2.setText("Download Directory:");

        jLabel3.setText("Chrome Driver:");

        jLabel4.setText("Chrome Browser:");

        userListBtn.setText("...");
        userListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userListBtnActionPerformed(evt);
            }
        });

        downloadDirBtn.setText("...");
        downloadDirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadDirBtnActionPerformed(evt);
            }
        });

        chromeDrvBtn.setText("...");
        chromeDrvBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chromeDrvBtnActionPerformed(evt);
            }
        });

        chromeBtn.setText("...");
        chromeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chromeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(downloadDirTF)
                    .addComponent(chromeDrvTF)
                    .addComponent(chromeTF, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                    .addComponent(userListTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userListBtn)
                    .addComponent(downloadDirBtn)
                    .addComponent(chromeDrvBtn)
                    .addComponent(chromeBtn)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(userListTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userListBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(downloadDirTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(downloadDirBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(chromeDrvTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chromeDrvBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(chromeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chromeBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, userListBtn, userListTF});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {downloadDirBtn, downloadDirTF, jLabel2});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {chromeDrvBtn, chromeDrvTF, jLabel3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {chromeBtn, chromeTF, jLabel4});

        outputArea.setEditable(false);
        outputArea.setColumns(20);
        outputArea.setRows(5);
        jScrollPane1.setViewportView(outputArea);

        fileMenu.setText("File");

        savePrefMI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/openpath/resources/document-save.png"))); // NOI18N
        savePrefMI.setText("Save Prefs");
        savePrefMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePrefMIActionPerformed(evt);
            }
        });
        fileMenu.add(savePrefMI);

        goMI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/openpath/resources/go-next.png"))); // NOI18N
        goMI.setText("GO");
        goMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goMIActionPerformed(evt);
            }
        });
        fileMenu.add(goMI);
        fileMenu.add(jSeparator1);

        exitMI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/openpath/resources/system-log-out.png"))); // NOI18N
        exitMI.setText("Exit");
        exitMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMIActionPerformed(evt);
            }
        });
        fileMenu.add(exitMI);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chromeDrvBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chromeDrvBtnActionPerformed
        // TODO add your handling code here:
		selectFile(fileChooser, chromeDrvTF);
    }//GEN-LAST:event_chromeDrvBtnActionPerformed

    private void userListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userListBtnActionPerformed
        // TODO add your handling code here:
		selectFile(fileChooser, userListTF);
    }//GEN-LAST:event_userListBtnActionPerformed

    private void chromeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chromeBtnActionPerformed
        // TODO add your handling code here:
		selectFile(fileChooser, chromeTF);
    }//GEN-LAST:event_chromeBtnActionPerformed

    private void downloadDirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadDirBtnActionPerformed
        // TODO add your handling code here:
		selectFile(directoryChooser, downloadDirTF);
    }//GEN-LAST:event_downloadDirBtnActionPerformed

    private void exitMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMIActionPerformed
        // TODO add your handling code here:
		setVisible(false);
		System.exit(0);
    }//GEN-LAST:event_exitMIActionPerformed

    private void savePrefMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePrefMIActionPerformed
        // TODO add your handling code here:
		UserPreferences.save(chromeDrvTF.getText(), chromeTF.getText());
    }//GEN-LAST:event_savePrefMIActionPerformed

    private void goMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goMIActionPerformed
        // TODO add your handling code here:
		if ((null != future) && (!future.isDone())) {
			write("Processing not complete");
			return;
		}

		DownloadTask task = new DownloadTask(userListTF.getText()
				, downloadDirTF.getText(), chromeDrvTF.getText()
				, chromeTF.getText());
		task.log(msg -> { write(msg); });

		future = Executors.newSingleThreadExecutor().submit(task);
    }//GEN-LAST:event_goMIActionPerformed

	private void write(String msg) {
		outputArea.setText(msg + "\n" + outputArea.getText());
	}

	private void selectFile(JFileChooser chooser, JTextField textField) {
		if (chooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION)
			return;
		textField.setText(chooser.getSelectedFile().getAbsolutePath());
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chromeBtn;
    private javax.swing.JButton chromeDrvBtn;
    private javax.swing.JTextField chromeDrvTF;
    private javax.swing.JTextField chromeTF;
    private javax.swing.JFileChooser directoryChooser;
    private javax.swing.JButton downloadDirBtn;
    private javax.swing.JTextField downloadDirTF;
    private javax.swing.JMenuItem exitMI;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem goMI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextArea outputArea;
    private javax.swing.JMenuItem savePrefMI;
    private javax.swing.JButton userListBtn;
    private javax.swing.JTextField userListTF;
    // End of variables declaration//GEN-END:variables
}