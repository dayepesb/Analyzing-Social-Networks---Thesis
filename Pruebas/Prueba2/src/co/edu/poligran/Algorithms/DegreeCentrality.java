package co.edu.poligran.Algorithms;

import java.util.HashMap;
import java.util.Map.Entry;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class DegreeCentrality {

	private Graph graph;
	public HashMap<Integer, Integer> inputDegree;
	public HashMap<Integer, Integer> outputDegree;

	public DegreeCentrality(Graph g) {
		this.graph = g;
		inputDegree = InputDegree();
		outputDegree = OutputDegree();
	}

	public HashMap<Integer, Integer> InputDegree() {
		// Rate of popularity of all nodes in a Graph
		inputDegree = new HashMap<>();
		for (Node n : graph) {
			inputDegree.put(n.getIndex(), n.getInDegree());
		}

		return inputDegree;
	}

	public HashMap<Integer, Integer> OutputDegree() {
		// Rate of sociality of all nodes in a Graph
		outputDegree = new HashMap<>();
		for (Node n : graph) {
			outputDegree.put(n.getIndex(), n.getOutDegree());
		}
		return outputDegree;
	}
}
