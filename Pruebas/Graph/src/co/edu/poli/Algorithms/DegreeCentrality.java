package co.edu.poli.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import co.edu.poli.Edge.Edge;
import co.edu.poli.Graph.Graph;
import co.edu.poli.Node.Node;

public class DegreeCentrality {

	public static HashMap<Long, Integer> InputDegree(Graph graph) {
		// Rate of popularity of all nodes in a Graph
		HashMap<Long, Integer> inputDegree = new HashMap<Long, Integer>();
		for (Edge e : graph.getListEdges()) {
			Node u = e.getNodeB();
			if (inputDegree.containsKey(u.getIdNode())) {
				inputDegree.put(u.getIdNode(), 1);
			} else {
				inputDegree.put(u.getIdNode(), inputDegree.get(u.getIdNode()) + 1);
			}
		}
		return inputDegree;
	}

	public static HashMap<Long, Integer> OutputDegree(Graph graph) {
		// Rate of sociality of all nodes in a Graph
		HashMap<Long, Integer> outputDegree = new HashMap<Long, Integer>();
		for (Entry<Long, ArrayList<Edge>> edges : graph.getGraph().entrySet()) {
			outputDegree.put(edges.getKey(), edges.getValue().size());
		}
		return outputDegree;
	}

	public static int OutputDegree(Graph graph, Node node) {
		// Rate of sociality of one node in a Graph
		return graph.getGraph().get(node.getIdNode()).size();
	}

	

}
