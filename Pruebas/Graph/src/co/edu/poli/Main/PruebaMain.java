package co.edu.poli.Main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import co.edu.poli.Layots.FramePrincipal;

public class PruebaMain {
	public static void main(String[] args) throws IOException {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
		FramePrincipal fp = new FramePrincipal();
	}

}
