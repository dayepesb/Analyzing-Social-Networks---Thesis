package co.edu.poligran.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class PanelProgressBar extends JPanel {

	private BufferedImage image;
	public JProgressBar progressBar;

	public PanelProgressBar() {
		this.setLayout(null);
		this.setBackground(new Color(84, 153, 199));
		try {
			image = ImageIO.read(new File("src/co/edu/poligran/Images/LogoPoliGraphApplicationProgressBar.png"));
		} catch (IOException ex) {
		}

		progressBar = new JProgressBar();
		progressBar.setBounds(0, 300, 500, 12);
		this.add(progressBar);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, (this.getWidth() / 2) - (image.getWidth() / 2), 50, this);
	}
}