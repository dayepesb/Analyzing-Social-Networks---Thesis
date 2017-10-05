package co.edu.poli.Algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import co.edu.poli.GraphPoli.GraphPoli;
import co.edu.poli.GraphPoli.GraphPoliDirect;
import co.edu.poli.GraphPoli.GraphPoliMixed;
import co.edu.poli.GraphPoli.GraphPoliUndirect;
import co.edu.poli.Link.Link;
import co.edu.poli.Vertex.Vertex;

public class Dijkstra {

	private GraphPoli graph;
	private HashMap<Long, Long> centrality;
	private final long INF = Long.MAX_VALUE / 3;
	private ArrayList<Vertex> listNodes;

	public Dijkstra(GraphPoliDirect graph) {
		this.graph = graph;
	}

	public Dijkstra(GraphPoliUndirect graph) {
		this.graph = graph;
	}

	public Dijkstra(GraphPoliMixed graph) {
		this.graph = graph;
	}

	public HashMap<Long, Double> DijkstraImplementation(Vertex node) {
		HashSet<Long> visited = new HashSet<Long>();
		HashMap<Long, Double> distance = new HashMap<Long, Double>();
		ArrayDeque<Vertex> q = new ArrayDeque<Vertex>();
		visited.add(node.getIdNode());
		q.add(node);
		distance.put(node.getIdNode(), 0d);
		while (!q.isEmpty()) {
			Vertex u = q.poll();
			for (Link e : graph.getGraphPoli().get(u.getIdNode())) {
				Vertex v = e.getNodeB();
				if (!visited.contains(v.getIdNode())) {
					distance.put(v.getIdNode(), distance.get(u.getIdNode()) + e.getWeight());
					visited.add(v.getIdNode());
					q.add(v);
				} else {
					if (distance.get(u.getIdNode()) + e.getWeight() < distance.get(v.getIdNode())) {
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
		for (Vertex node : listNodes) {
			solve.put(node.getIdNode(), (double) INF);
		}
		pq.add(v);
		solve.put(v, 0.);
		camino.put(v, v + "");
		while (!pq.isEmpty()) {
			long u = pq.poll();
			ArrayList<Link> ady = graph.getGraphPoli().get(u);
			for (Link edge : ady) {
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
