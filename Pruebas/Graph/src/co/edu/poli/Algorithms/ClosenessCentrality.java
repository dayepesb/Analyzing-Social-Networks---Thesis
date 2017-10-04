package co.edu.poli.Algorithms;

import java.util.HashMap;
import java.util.Map.Entry;

import co.edu.poli.GraphPoli.GraphPoli;
import co.edu.poli.GraphPoli.GraphPoliDirect;
import co.edu.poli.GraphPoli.GraphPoliMixed;
import co.edu.poli.GraphPoli.GraphPoliUndirect;
import co.edu.poli.Node.Vertex;

public class ClosenessCentrality {

	private GraphPoli graph;
	private boolean mixted, direct;

	public ClosenessCentrality(GraphPoliDirect graph) {
		this.graph = graph;
		direct = true;
		mixted = false;
	}

	public ClosenessCentrality(GraphPoliUndirect graph) {
		this.graph = graph;
		direct = false;
		mixted = false;
	}

	public ClosenessCentrality(GraphPoliMixed graph) {
		this.graph = graph;
		direct = false;
		mixted = true;
	}

	public HashMap<Long, Double> closeness(GraphPoli graph) {
		Double total = 0d;
		HashMap<Long, Double> closeness = new HashMap<Long, Double>();
		HashMap<Long, Double> remoteness = remoteness(graph);
		for (Entry<Long, Double> dist : remoteness.entrySet()) {
			total += dist.getValue();
		}
		for (Vertex node : graph.getListVertex()) {
			closeness.put(node.getIdNode(), closeness(graph, node, total, remoteness));
		}
		return closeness;
	}

	public double closeness(GraphPoli graph, Vertex node, double total, HashMap<Long, Double> remoteness) {
		return total / remoteness.get(node.getIdNode());
	}

	public HashMap<Long, Double> remoteness(GraphPoli graph) {
		// Cercania de todos los nodos con respecto a los demas
		HashMap<Long, Double> cercania = new HashMap<Long, Double>();
		for (Vertex node : graph.getListVertex()) {
			double cercaniaNodo = remoteness(graph, node);
			cercania.put(node.getIdNode(), cercaniaNodo);
		}
		return cercania;
	}

	public double remoteness(GraphPoli graph, Vertex node) {
		// Cercania de un nodo con respecto a los demas
		Dijkstra dijkstra;
		if (mixted)
			dijkstra = new Dijkstra((GraphPoliMixed) graph);
		else if (direct)
			dijkstra = new Dijkstra((GraphPoliDirect) graph);
		else
			dijkstra = new Dijkstra((GraphPoliUndirect) graph);
		HashMap<Long, Double> distance = dijkstra.DijkstraImplementation(node);
		double cercania = 0;
		for (Entry<Long, Double> dist : distance.entrySet()) {
			cercania += dist.getValue();
		}
		return cercania;
	}
}
