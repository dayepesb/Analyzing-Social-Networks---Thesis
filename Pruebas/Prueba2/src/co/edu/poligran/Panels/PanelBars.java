package co.edu.poligran.Panels;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class PanelBars extends JPanel {
	private Graph graph;
	private ChartPanel chartPanel;

	public PanelBars(Graph graph) {
		this.graph = graph;
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		setComponents("none");
	}

	public void setComponents(String property) {
		this.removeAll();
		this.repaint();
		HashMap<String, Integer> count = new HashMap<>();
		count.put("None", 0);
		for (Node n : graph) {
			if (n.hasAttribute("-attribute-" + property)) {
				String s = n.getAttribute("-attribute-" + property);
				if (count.containsKey(s)) {
					int l = count.get(s);
					count.put(s, l + 1);
				} else {
					count.put(s, 1);
				}
			} else {
				int l = count.get("None");
				count.put("None", l + 1);
			}
		}
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Entry<String, Integer> e : count.entrySet()) {
			dataset.setValue(e.getValue(), e.getKey(), property);
		}
		JFreeChart chart = ChartFactory.createBarChart3D(property.toUpperCase(), "Grups", "Number Of Nodes", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		chart.getTitle().setPaint(Color.black);
		chartPanel = new ChartPanel(chart);
		int width = (int) (this.getWidth() * 0.7);
		int heigth = (int) (this.getHeight() * 0.8);
		chartPanel.setBounds((this.getWidth() / 2) - (width / 2), (this.getHeight() / 2) - (heigth / 2), width, heigth);
		this.add(chartPanel);
	}

	public void resizedComponents(int width, int height) {
		this.setBounds(0, 50, width, height + 7);
		int width1 = (int) (this.getWidth() * 0.7);
		int heigth1 = (int) (this.getHeight() * 0.8);
		chartPanel.setBounds((this.getWidth() / 2) - (width1 / 2), (this.getHeight() / 2) - (heigth1 / 2), width1, heigth1);
	}
}
