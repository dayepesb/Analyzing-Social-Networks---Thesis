package co.edu.poligran.Frame;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import co.edu.poligran.Panels.PanelProgressBar;

public class ProgressBarFrame extends JFrame implements Runnable {
	private final Image icono = Toolkit.getDefaultToolkit()
			.getImage("src/co/edu/poligran/Images/LogoPoliGraphApplication.png");
	private PanelProgressBar ppg;

	public ProgressBarFrame() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 350);
		setLayout(null);
		setIconImage(icono);
		getContentPane().setBackground(new Color(84, 153, 199));

		ppg = new PanelProgressBar();
		ppg.setBounds(0, 0, 500, 350);
		this.add(ppg);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void run() {
		new PrincipalFrame(this);
		
	}

	public void setProgressBar(int value) {
		int val = ppg.progressBar.getValue();
		ppg.progressBar.setValue(val+value+5);
		if(ppg.progressBar.getValue()>99) {
			this.setVisible(false);
		}
	}
}
