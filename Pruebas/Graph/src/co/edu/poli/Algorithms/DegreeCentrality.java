package co.edu.poli.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import co.edu.poli.Edge.Edge;
import co.edu.poli.Graph.Graph;
import co.edu.poli.Graph.GraphDirect;
import co.edu.poli.Graph.GraphMixed;
import co.edu.poli.Graph.GraphUndirect;
import co.edu.poli.Node.Node;

public class DegreeCentrality {

	private Graph graph;
	private boolean mixted,direct;

	public DegreeCentrality(GraphDirect graph) {
		this.graph = graph;
		direct = true;
		mixted = false;
	}

	public DegreeCentrality(GraphUndirect graph) {
		this.graph = graph;
		direct = false;
		mixted = false;
	}

	public DegreeCentrality(GraphMixed graph) {
		this.graph = graph;
		direct = false;
		mixted = true;
	}


	public HashMap<Long, Integer> InputDegree() {
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

	public HashMap<Long, Integer> OutputDegree() {
		// Rate of sociality of all nodes in a Graph
		HashMap<Long, Integer> outputDegree = new HashMap<Long, Integer>();
		for (Entry<Long, ArrayList<Edge>> edges : graph.getGraph().entrySet()) {
			outputDegree.put(edges.getKey(), edges.getValue().size());
		}
		return outputDegree;
	}

	public int OutputDegree(Node node) {
		// Rate of sociality of one node in a Graph
		return graph.getGraph().get(node.getIdNode()).size();
	}

	

}
