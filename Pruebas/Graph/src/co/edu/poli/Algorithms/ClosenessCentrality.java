package co.edu.poli.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import co.edu.poli.Edge.Edge;
import co.edu.poli.Graph.Graph;
import co.edu.poli.Node.Node;

public class ClosenessCentrality {

	public static HashMap<Long, Double> closeness(Graph graph) {
		Double total=0d;
		HashMap<Long, Double>closeness=new HashMap<Long,Double>();
		HashMap<Long, Double>remoteness=remoteness(graph);
		for(Entry<Long,Double>dist:remoteness.entrySet()){
			total+=dist.getValue();
		}
		for(Node node:graph.getListNodes()){
			closeness.put(node.getIdNode(), closeness(graph, node, total,remoteness));
		}
		return closeness;
	}

	public static double closeness(Graph graph, Node node,double total,HashMap<Long, Double>remoteness) {
		return total/remoteness.get(node.getIdNode());
	}

	public static HashMap<Long, Double> remoteness(Graph graph) {
		// Cercania de todos los nodos con respecto a los demas
		HashMap<Long, Double> cercania = new HashMap<Long, Double>();
		for (Node node : graph.getListNodes()) {
			double cercaniaNodo = remoteness(graph, node);
			cercania.put(node.getIdNode(), cercaniaNodo);
		}
		return cercania;
	}

	public static double remoteness(Graph graph, Node node) {
		// Cercania de un nodo con respecto a los demas
		HashMap<Long, Double> distance = Dijkstra.DijkstraImplementation(graph, node);
		double cercania = 0;
		for (Entry<Long, Double> dist : distance.entrySet()) {
			cercania += dist.getValue();
		}
		return cercania;
	}
}
