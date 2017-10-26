package co.edu.poligran.Examples;

import java.util.ArrayList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import co.edu.poligran.Algorithms.ArticulationPoints;
import co.edu.poligran.Colors.ColorRandom;

public class TestArticulationPoints {
	public static void main(String[] args) throws Exception {
		Graph graph = new MultiGraph("Graph");

		String nodes = "abcdefgh";
		for (int i = 0; i < nodes.length(); i++)
			graph.addNode(nodes.substring(i, i + 1));
		graph.addEdge("ac", "a", "c");
		graph.addEdge("cd", "c", "d");
		graph.addEdge("db", "d", "b");
		graph.addEdge("ba", "b", "a");
		graph.addEdge("hd", "h", "d");
		graph.addEdge("fh", "f", "h");
		graph.addEdge("ef", "e", "f");
		graph.addEdge("ge", "g", "e");
		graph.addEdge("fg", "f", "g");

		ArticulationPoints ap = new ArticulationPoints(graph);
		ap.compute();
		ArrayList<Integer> articulationPoints = ap.getArticulationPoints();
		String colors [] = new ColorRandom().getArrayColors();

		for (Node n : graph) {
			n.addAttribute("label", n.getId());
		}

		int index=0;
		for (Integer w : articulationPoints) {
			graph.getNode(w).addAttribute("ui.style", "fill-color:"+colors[index]);
			graph.getNode(w).addAttribute("label", "Punto de articulacion");
			index++;
		}

		graph.display();
	}
}
