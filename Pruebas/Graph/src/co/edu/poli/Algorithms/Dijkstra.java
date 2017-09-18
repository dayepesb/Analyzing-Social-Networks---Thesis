package co.edu.poli.Algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import co.edu.poli.Edge.Edge;
import co.edu.poli.Graph.Graph;
import co.edu.poli.Graph.GraphDirect;
import co.edu.poli.Graph.GraphMixed;
import co.edu.poli.Graph.GraphUndirect;
import co.edu.poli.Node.Node;

public class Dijkstra {

	private Graph graph;
	private HashMap<Long, Long> centrality;
	private final long INF = Long.MAX_VALUE / 3;
	private ArrayList<Node> listNodes;

	public Dijkstra(GraphDirect graph) {
		this.graph = graph;
	}

	public Dijkstra(GraphUndirect graph) {
		this.graph = graph;
	}

	public Dijkstra(GraphMixed graph) {
		this.graph = graph;
	}


	public HashMap<Long,Double> DijkstraImplementation(Node node) {
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
	public HashMap<Long, String> pathDijkstra(long v) {
		PriorityQueue<Long> pq = new PriorityQueue<>();
		HashMap<Long, Double> solve = new HashMap<>();
		HashMap<Long, String> camino = new HashMap<>();
		for (Node node : listNodes) {
			solve.put(node.getIdNode(), (double) INF);
		}
		pq.add(v);
		solve.put(v, 0.);
		camino.put(v, v + "");
		while (!pq.isEmpty()) {
			long u = pq.poll();
			ArrayList<Edge> ady = graph.getGraph().get(u);
			for (Edge edge : ady) {
				if (edge.getWeight() + solve.get(u) < solve.get(edge.getNodeB().getIdNode())) {
					solve.put(edge.getNodeB().getIdNode(), (edge.getWeight() + solve.get(u)));
					pq.add(edge.getNodeB().getIdNode());
					camino.put(edge.getNodeB().getIdNode(), camino.get(u) + " " + edge.getNodeB().getIdNode());
				}
			}
		}

		return camino;
	}

}
