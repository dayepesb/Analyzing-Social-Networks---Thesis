package co.edu.poligran.Algorithms;

import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import co.edu.poligran.Colors.ColorRandom;

public class TarajanExample extends MultiGraph {
	public TarajanExample(String id, String nodes) throws Exception {
		super(id);
		for (int i = 0; i < nodes.length(); i++)
			addNode(nodes.substring(i, i + 1));
		addEdge("ac", "a", "c", true);
		addEdge("cd", "c", "d", true);
		addEdge("db", "d", "b", true);
		addEdge("ba", "b", "a", true);
		addEdge("hd", "h", "d", true);
		addEdge("fh", "f", "h", true);
		addEdge("ef", "e", "f", true);
		addEdge("ge", "g", "e", true);
		addEdge("fg", "f", "g", true);

		TarjanStronglyConnectedComponents tscc = new TarjanStronglyConnectedComponents();
		tscc.init(this);
		tscc.compute();
		ColorRandom cr = new ColorRandom();
		String arrayColors[] = cr.getArrayColors();
		for (Node n : this.getEachNode()) {
			n.addAttribute("label", n.getAttribute(tscc.getSCCIndexAttribute()).toString());
			int index = Integer.parseInt(n.getAttribute("label").toString());
			n.addAttribute("ui.style", "fill-color:" + arrayColors[index]);
		}
	}

	public static void main(String[] args) throws Exception {
		Graph g = new TarajanExample("g", "abcdefgh");
		g.display();

	}
}
