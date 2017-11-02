package example;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class ProgressSample {
	public static void main(String args[]) {
		JProgressBar aJProgressBar = new JProgressBar();
		aJProgressBar.setValue(50);	
		JProgressBar bJProgressBar = new JProgressBar();
		bJProgressBar.setValue(25);
		bJProgressBar.setStringPainted(true);
		Border border = BorderFactory.createTitledBorder("Reading File");
		bJProgressBar.setBorder(border);
		JProgressBar cJProgressBar = new JProgressBar(JProgressBar.VERTICAL);
		cJProgressBar.setValue(75);
		cJProgressBar.setBorderPainted(false);

		JProgressBar dJProgressBar = new JProgressBar(JProgressBar.VERTICAL);
		dJProgressBar.setValue(100);
		dJProgressBar.setString("Ack");
		dJProgressBar.setStringPainted(true);

		JFrame theFrame = new JFrame("ProgressBars R Us");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contentPane = theFrame.getContentPane();
		contentPane.add(aJProgressBar, BorderLayout.NORTH);
		contentPane.add(bJProgressBar, BorderLayout.SOUTH);
		contentPane.add(cJProgressBar, BorderLayout.EAST);
		contentPane.add(dJProgressBar, BorderLayout.WEST);
		theFrame.setSize(300, 200);
		theFrame.setVisible(true);
	}
}