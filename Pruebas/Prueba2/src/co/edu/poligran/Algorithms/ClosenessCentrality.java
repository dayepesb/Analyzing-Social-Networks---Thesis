package co.edu.poligran.Algorithms;

import java.util.HashMap;
import java.util.Map.Entry;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class ClosenessCentrality {

	private Graph graph;
	public HashMap<Integer, Double> remoteness;
	public HashMap<Integer, Double> closeness;

	public ClosenessCentrality(Graph g) {
		this.graph = g;
		remoteness = remoteness();
		closeness = closeness();
	}

	public HashMap<Integer, Double> closeness() {
		Double total = 0d;
		HashMap<Integer, Double> closeness = new HashMap<>();
		remoteness = remoteness();
		for (Entry<Integer, Double> dist : remoteness.entrySet()) {
			total += dist.getValue();
		}
		for (Node n : graph) {
			closeness.put(n.getIndex(), closeness(total, n.getIndex()));
		}
		return closeness;

	}

	public double closeness(double total, int node) {
		return total / remoteness.get(node);
	}

	public HashMap<Integer, Double> remoteness() {
		HashMap<Integer, Double> remoteness = new HashMap<>();
		for (Node n : graph) {
			int id = n.getIndex();
			double remotenessNode = remoteness(id);
			remoteness.put(id, remotenessNode);
		}
		return remoteness;
	}

	public double remoteness(int node) {
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, "result", "length");
		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(node));
		dijkstra.compute();
		double distTotal = 0;
		for (Node n : graph) {
			distTotal += dijkstra.getPathLength(n);
		}
		return distTotal;
	}

}
