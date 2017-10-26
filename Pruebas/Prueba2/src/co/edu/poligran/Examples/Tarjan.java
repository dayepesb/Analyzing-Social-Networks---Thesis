package co.edu.poligran.Examples;
import java.util.Arrays;

import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class Tarjan {
	public static void main(String[] args) {
		Graph g = new MultiGraph("g");

		String nodes = "abcdefgh";
		String edges = "abbccddccgdhhdhggffgbfefbeea";

		for (int i = 0; i < 8; i++)
			g.addNode(nodes.substring(i, i + 1));

		for (int i = 0; i < 14; i++) {
			g.addEdge(edges.substring(2 * i, 2 * i + 2), edges.substring(2 * i, 2 * i + 1),
					edges.substring(2 * i + 1, 2 * i + 2), true);
		}

		System.out.println(Toolkit.density(g));
		System.out.println(Toolkit.diameter(g));
		System.out.println(Arrays.toString(Toolkit.clusteringCoefficients(g)));

		g.display();

		TarjanStronglyConnectedComponents tscc = new TarjanStronglyConnectedComponents();
		tscc.init(g);
		tscc.compute();

		for (Node n : g.getEachNode()){
			String k=tscc.getSCCIndexAttribute()+"";
			n.addAttribute("label", n.getAttribute(k).toString());

		}
	}
}
