package co.edu.poligran.Algorithms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import co.edu.poligran.Colors.ColorToHex;
import co.edu.poligran.PopUp.PopUpMessageHomofily;

public class HomofilyPoli {

	private Graph graph;
	private Color colorA, colorB;
	private ColorToHex cth;

	public HomofilyPoli(Graph graph, String att, ArrayList<String> groupA, ArrayList<String> groupB, Color colorA,
			Color colorB) {
		this.graph = graph;
		this.colorA = colorA;
		this.colorB = colorB;
		HashSet<String> nodesA = new HashSet<>();
		HashSet<String> nodesB = new HashSet<>();
		for (Node n : graph) {
			if (n.hasAttribute("-attribute-" + att)) {
				String sol = n.getAttribute("-attribute-" + att);
				for (String s : groupA) {
					if (sol.equalsIgnoreCase(s)) {
						nodesA.add(n.getId());
						continue;
					}
				}
				for (String s : groupB) {
					if (sol.equalsIgnoreCase(s)) {
						nodesB.add(n.getId());
						continue;
					}
				}
			}
			compute(nodesA, nodesB);
		}
	}

	public HomofilyPoli(Graph graph, String att, int min, int mid, int max, Color colorA, Color colorB) {
		this.graph = graph;
		this.colorA = colorA;
		this.colorB = colorB;
		HashSet<String> nodesA = new HashSet<>();
		HashSet<String> nodesB = new HashSet<>();
		for (Node n : graph) {
			if (n.hasAttribute("-attribute-" + att)) {
				int sol = Integer.parseInt(n.getAttribute("-attribute-" + att) + "");
				if (sol <= mid) {
					nodesA.add(n.getId());
				} else if (sol > mid) {
					nodesB.add(n.getId());
				}
			}
		}

		compute(nodesA, nodesB);

	}

	private Set<String> union(HashSet<String> nodesA, HashSet<String> nodesB) {
		Set<String> union = new HashSet<>();
		for (String string : nodesA) {
			if (nodesB.contains(string))
				union.add(string);
		}
		return union;
	}

	private void compute(HashSet<String> nodesA, HashSet<String> nodesB) {
		Set<String> union = union(nodesA, nodesB);
		int p = nodesA.size();
		int q = nodesB.size();
		int pq = 2 * p * q;
		int cont = 0;
		for (Edge edge : graph.getEdgeSet()) {
			if ((nodesA.contains(edge.getNode0().getId()) && nodesB.contains(edge.getNode1().getId()))
					|| (nodesB.contains(edge.getNode0().getId()) && nodesA.contains(edge.getNode1().getId()))) {
				cont++;
			}
		}
		paintGraph(nodesA, nodesB, union);
		getMessage(p, q, pq, cont);
	}

	private void getMessage(int p, int q, int pq, int cont) {
		PopUpMessageHomofily popup = new PopUpMessageHomofily(p, q, pq, cont);
	}

	private void paintGraph(HashSet<String> nodesA, HashSet<String> nodesB, Set<String> union) {
		cth = new ColorToHex(this.colorA.getRed(), this.colorA.getGreen(), this.colorA.getBlue());
		String one = cth.getHex();
		cth = new ColorToHex(this.colorB.getRed(), this.colorB.getGreen(), this.colorB.getBlue());
		String two = cth.getHex();
		for (Node node : graph) {
			if (nodesA.contains(node.getId())) {
				node.setAttribute("ui.style", "fill-color:" + one + ";");
			} else if (nodesB.contains(node.getId())) {
				node.setAttribute("ui.style", "fill-color:" + two + ";");
			}
			if (union.contains(node.getId())) {
				node.setAttribute("ui.style", "gradient-diagonal2:(" + one + "," + two + ");");
			}
		}
	}
}
