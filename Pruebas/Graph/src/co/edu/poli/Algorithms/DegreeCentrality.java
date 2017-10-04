package co.edu.poli.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import co.edu.poli.GraphPoli.GraphPoliDirect;
import co.edu.poli.GraphPoli.GraphPoli;
import co.edu.poli.GraphPoli.GraphPoliMixed;
import co.edu.poli.GraphPoli.GraphPoliUndirect;
import co.edu.poli.Link.Link;
import co.edu.poli.Node.Vertex;

public class DegreeCentrality {

	private GraphPoli graph;
	private boolean mixted,direct;

	public DegreeCentrality(GraphPoliDirect graph) {
		this.graph = graph;
		direct = true;
		mixted = false;
	}

	public DegreeCentrality(GraphPoliUndirect graph) {
		this.graph = graph;
		direct = false;
		mixted = false;
	}

	public DegreeCentrality(GraphPoliMixed graph) {
		this.graph = graph;
		direct = false;
		mixted = true;
	}


	public HashMap<Long, Integer> InputDegree() {
		// Rate of popularity of all nodes in a Graph
		HashMap<Long, Integer> inputDegree = new HashMap<Long, Integer>();
		for (Link e : graph.getListLinks()) {
			Vertex u = e.getNodeB();
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
		for (Entry<Long, ArrayList<Link>> edges : graph.getGraphPoli().entrySet()) {
			outputDegree.put(edges.getKey(), edges.getValue().size());
		}
		return outputDegree;
	}

	public int OutputDegree(Vertex node) {
		// Rate of sociality of one node in a Graph
		return graph.getGraphPoli().get(node.getIdNode()).size();
	}

	

}
