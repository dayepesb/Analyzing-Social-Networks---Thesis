package co.edu.poli.Layots;

import java.awt.Dimension;
import java.awt.Toolkit;

public class PruebaMain {
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
		FramePrincipal fp = new FramePrincipal("Analizis Social Network");
	}

}
