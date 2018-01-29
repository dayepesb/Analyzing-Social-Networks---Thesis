package co.edu.poligran.Examples;
//Tabla de selector de colores: JColorChooser

import java.awt.Color;
import javax.swing.JColorChooser;

public class SelectorColor extends javax.swing.JFrame {
	public SelectorColor() {
		initComponents();
		this.setLocationRelativeTo(null); // centrar pantalla
	}

	private void initComponents() {
		// Codigo generado por Netbeans al crear la interface grafica manualmente...
	}

	private void JButtonCargarColoresActionPerformed(java.awt.event.ActionEvent evt) {
		Color c = JColorChooser.showDialog(this, "Seleccion color", Color.white);
		if (c != null)
			getContentPane().setBackground(c);
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SelectorColor().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton JButtonCargarColores;
	// End of variables declaration
}