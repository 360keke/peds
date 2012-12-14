package cn.com.peds.jetty;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class TestDesktopOpenFile {
	 private JFrame frame;
	 
     /**
      * Launch the application
      * 
      * @param args
      */
     public static void main(String args[]) {
         try {
             TestDesktopOpenFile window = new TestDesktopOpenFile();
             window.frame.setVisible(true);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
 
     /**
      * Create the application
      */
     public TestDesktopOpenFile() {
         initialize();
     }
 
     /**
      * Initialize the contents of the frame
      */
     private void initialize() {
         frame = new JFrame();
         frame.getContentPane().setLayout(null);
         frame.setBounds(100, 100, 225, 86);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         final JButton openButton = new JButton();
         openButton.setBounds(53, 10, 106, 31);
         openButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 if (Desktop.isDesktopSupported()) {
                     Desktop desktop = Desktop.getDesktop();
                     JFileChooser jfc = new JFileChooser();
                     jfc.showOpenDialog(frame);
                     File file = jfc.getSelectedFile();
                     if (file != null) {
                         try {
                             desktop.open(file);
                         } catch (IOException e1) {
                             e1.printStackTrace();
                         }
                     }
                 } else {
                     System.out.println("不支持desktop");
                 }
             }
         });
         openButton.setText("open");
         frame.getContentPane().add(openButton);
     }
}
