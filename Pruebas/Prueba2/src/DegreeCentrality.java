import java.util.HashMap;
import java.util.Map.Entry;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class DegreeCentrality {

	Graph g;
	HashMap<Integer, Integer> inputDegree;
	HashMap<Integer, Integer> outputDegree;

	public DegreeCentrality(Graph g) {
		this.g = g;
		inputDegree = InputDegree();
		outputDegree = OutputDegree();
	}

	public static void main(String[] args) {
		Graph graph = new MultiGraph("Test");
		Node A = graph.addNode("A");
		Node B = graph.addNode("B");
		Node C = graph.addNode("C");
		Node D = graph.addNode("D");
		Node E = graph.addNode("E");
		Node F = graph.addNode("F");

		graph.addEdge("AB", "A", "B", true).addAttribute("length", 14.0);
		graph.addEdge("AC", "A", "C", true).addAttribute("length", 9.0);
		graph.addEdge("AD", "A", "D").addAttribute("length", 7.0);
		graph.addEdge("BC", "B", "C").addAttribute("length", 2.0);
		graph.addEdge("CD", "C", "D").addAttribute("length", 10.5);
		graph.addEdge("BE", "B", "E").addAttribute("length", 9.0);
		graph.addEdge("CF", "C", "F").addAttribute("length", 11.9999);
		graph.addEdge("DF", "D", "F").addAttribute("length", 15.9);
		graph.addEdge("EF", "E", "F").addAttribute("length", 6.3);
		graph.addEdge("EC", "E", "C").addAttribute("length", 6.3);

		ClosenessCentrality c = new ClosenessCentrality(graph);
		DegreeCentrality d = new DegreeCentrality(graph);

		for (Node n : graph)
			n.addAttribute("label", n.getId());
		for (Edge e : graph.getEachEdge())
			e.addAttribute("label", "" + (double) e.getNumber("length"));

		graph.display();

		for (Entry<Integer, Integer> dist : d.inputDegree.entrySet()) {
			System.out.println("Popularidad de " + dist.getKey() + " es " + dist.getValue());
		}
		System.out.println();
		for (Entry<Integer, Integer> dist : d.outputDegree.entrySet()) {
			System.out.println("Sociabilidad de " + dist.getKey() + " es " + dist.getValue());
		}

	}

	public HashMap<Integer, Integer> InputDegree() {
		// Rate of popularity of all nodes in a Graph
		inputDegree = new HashMap<>();
		for (Node n : g) {
			inputDegree.put(n.getIndex(), n.getInDegree());
		}

		return inputDegree;
	}

	public HashMap<Integer, Integer> OutputDegree() {
		// Rate of sociality of all nodes in a Graph
		outputDegree = new HashMap<>();
		for (Node n : g) {
			outputDegree.put(n.getIndex(), n.getOutDegree());
		}
		return outputDegree;
	}
}
