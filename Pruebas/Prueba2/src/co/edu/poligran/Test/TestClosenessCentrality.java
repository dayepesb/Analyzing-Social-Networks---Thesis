package co.edu.poligran.Test;

import java.util.Map.Entry;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import co.edu.poligran.Algorithms.ClosenessCentrality;

public class TestClosenessCentrality {
	public static void main(String[] args) {
		Graph graph = new MultiGraph("Test");
		Node A = graph.addNode("A");
		Node B = graph.addNode("B");
		Node C = graph.addNode("C");
		// Node D = graph.addNode("D");
		// Node E = graph.addNode("E");
		// Node F = graph.addNode("F");

		graph.addEdge("AB", "A", "B").addAttribute("length", 14.0);
		graph.addEdge("AC", "A", "C").addAttribute("length", 9.0);
		// graph.addEdge("AD", "A", "D").addAttribute("length", 7.0);
		graph.addEdge("BC", "B", "C").addAttribute("length", 2.0);
		// graph.addEdge("CD", "C", "D").addAttribute("length", 10.5);
		// graph.addEdge("BE", "B", "E").addAttribute("length", 9.0);
		// graph.addEdge("CF", "C", "F").addAttribute("length", 11.9999);
		// graph.addEdge("DF", "D", "F").addAttribute("length", 15.9);
		// graph.addEdge("EF", "E", "F").addAttribute("length", 6.3);

		ClosenessCentrality c = new ClosenessCentrality(graph);

		for (Node n : graph)
			n.addAttribute("label", n.getId());
		for (Edge e : graph.getEachEdge())
			e.addAttribute("label", "" + (double) e.getNumber("length"));

		graph.display();

		for (Entry<Integer, Double> dist : c.closeness.entrySet()) {
			System.out.println("La cercania del nodo" + dist.getKey() + " es " + dist.getValue());
		}
		for (Entry<Integer, Double> dist : c.remoteness.entrySet()) {
			System.out.println("La lejania del nodo" + dist.getKey() + " es " + dist.getValue());
		}

	}

}
