package co.edu.poligran.Examples;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class PrubaBarras extends JFrame {
	JPanel panel;

	public PrubaBarras() {
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
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(8, "Mujeres", "Lunes");
		dataset.setValue(7, "Hombres", "Lunes");
		dataset.setValue(9, "Mujeres", "Martes");
		dataset.setValue(4, "Hombres", "Martes");
		dataset.setValue(4, "Mujeres", "Miercoles");
		dataset.setValue(5, "Hombres", "Miercoles");
		dataset.setValue(8, "Mujeres", "Jueves");
		dataset.setValue(9, "Hombres", "Jueves");
		dataset.setValue(7, "Mujeres", "Viernes");
		dataset.setValue(8, "Hombres", "Viernes");
		// Creando el Grafico
		JFreeChart chart = ChartFactory.createBarChart3D("Participacion por Genero", "Genero", "Dias", dataset,
				PlotOrientation.VERTICAL, true, true, false);
//		chart.setBackgroundPaint(Color.BLUE);
		chart.getTitle().setPaint(Color.black);
		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.red);
		// Mostrar Grafico
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 0, 400, 400);
		panel.setBounds(0, 0, 600, 600);
		panel.add(chartPanel);
	}

	public static void main(String args[]) {
		new PrubaBarras().setVisible(true);
	}
}
