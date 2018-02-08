package co.edu.poligran.Algorithms;

import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import co.edu.poligran.Colors.ColorRandom;

public class BetweenessPoli {
	private Graph graph;

	public BetweenessPoli(Graph graph) {
		this.graph = graph;
		BetweennessCentrality bc = new BetweennessCentrality();
		bc.init(this.graph);
		bc.compute();
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (Node node : graph) {
			node.setAttribute("ui.style", "fill-color:#fff;");
		}
		for (Edge edge : graph.getEdgeSet()) {
			edge.setAttribute("ui.style", "fill-color:#fff;");
		}

		ColorRandom cr = new ColorRandom();
		String colors[] = new String[256];
		try {
			colors = cr.getColorRandomDegre();
		} catch (Exception e) {
		}

		for (Node n : graph) {
			double temp = (double) n.getAttribute("Cb");
			if (temp > max)
				max = temp;
			if (temp < min)
				min = temp;

		}
		for (Node n : graph) {
			double temp = (double) n.getAttribute("Cb");
			double color = ((temp - min) / (max - min));
			if ((color * 100) >= colors.length)
				n.setAttribute("ui.style", "fill-color :" + colors[colors.length - 1]);
			else
				n.setAttribute("ui.style", "fill-color :" + colors[((int) (color * 1000)) % colors.length]);
		}
	}
}
