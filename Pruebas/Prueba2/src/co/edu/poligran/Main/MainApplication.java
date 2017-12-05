package co.edu.poligran.Main;

import javax.swing.SwingUtilities;

import co.edu.poligran.Frame.ProgressBarFrame;

public class MainApplication {
	public static void main(String[] args) {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		SwingUtilities.invokeLater(new ProgressBarFrame());
	}
}