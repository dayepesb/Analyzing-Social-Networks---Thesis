package co.edu.poli.Algorithms;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

import co.edu.poli.Edge.Edge;
import co.edu.poli.Graph.Graph;
import co.edu.poli.Node.Node;

public class Dijkstra {

	public static HashMap<Long,Double> DijkstraImplementation(Graph graph, Node node) {
		HashSet<Long> visited = new HashSet<Long>();
		HashMap<Long, Double> distance = new HashMap<Long, Double>();
		ArrayDeque<Node> q = new ArrayDeque<Node>();
		visited.add(node.getIdNode());
		q.add(node);
		distance.put(node.getIdNode(), 0d);
		while (!q.isEmpty()) {
			Node u = q.poll();
			for (Edge e : graph.getGraph().get(u.getIdNode())) {
				Node v = e.getNodeB();
				if (!visited.contains(v.getIdNode())) {
					distance.put(v.getIdNode(), distance.get(u.getIdNode()) + e.getWeight());
					visited.add(v.getIdNode());
					q.add(v);
				}else{
					if (distance.get(u.getIdNode()) + e.getWeight() < distance.get(v.getIdNode())){
						distance.put(v.getIdNode(), distance.get(u.getIdNode()) + e.getWeight());
					}
				}
			}
		}
		return distance;
	}
}
