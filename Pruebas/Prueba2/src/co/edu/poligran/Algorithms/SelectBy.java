package co.edu.poligran.Algorithms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import co.edu.poligran.Colors.ColorToHex;

public class SelectBy {
	public SelectBy(Graph graph, String att, Color color, String select) {
		for (Node n : graph) {
			n.setAttribute("ui.style", "fill-color:#000000;");
		}
		for (Edge edge : graph.getEdgeSet()) {
			edge.setAttribute("ui.style", "fill-color:#000000;");
		}
		Set<String> list = new HashSet<>();
		ColorToHex cth = new ColorToHex(color.getRed(), color.getGreen(), color.getBlue());
		String hex = cth.getHex();
		select = select.toLowerCase();
		for (Node n : graph) {
			if (n.hasAttribute("-attribute-" + select)) {
				String s = n.getAttribute("-attribute-" + select);
				if (s.equalsIgnoreCase(att)) {
					n.setAttribute("ui.style", "fill-color:" + hex + ";");
					list.add(n.getId());
				}
			}
		}
		for (Edge edge : graph.getEdgeSet()) {
			if(list.contains(edge.getNode0().getId())&&list.contains(edge.getNode1().getId())) {
				edge.setAttribute("ui.style", "fill-color:" + hex + ";");
			}
		}
	}
}
