
package co.edu.poligran.Examples;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PruebaTorta extends JFrame {
	JPanel panel;

	public PruebaTorta() {
		setTitle("Como Hacer Graficos con Java");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		init();
	}

	private void init() {
		panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		// Fuente de Datos
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("C", 40);
		data.setValue("Java", 45);
		data.setValue("Python", 15);

		// Creando el Grafico
		JFreeChart chart = ChartFactory.createPieChart("Ejemplo Rapido de Grafico en un ChartFrame", data, true, true,
				false);

		// Mostrar Grafico
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 0, 800, 500);
		panel.setBounds(0, 0, 900, 600);
		panel.add(chartPanel);
	}

	public static void main(String args[]) {
		new PruebaTorta();
	}
}
