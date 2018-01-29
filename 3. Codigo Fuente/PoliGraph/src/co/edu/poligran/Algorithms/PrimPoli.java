package co.edu.poligran.Algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class PrimPoli {

	private Graph graph;

	public PrimPoli(Graph graph) {
		this.graph = graph;
		for (Edge e : this.graph.getEachEdge()) {
			if (!e.hasAttribute("-attribute-width"))
				e.setAttribute("-attribute-width", 1.0);
		}
	}

	public void compute() {
		HashMap<String, Boolean> vis = new HashMap<>();
		for (Edge edge : graph.getEdgeSet()) {
			edge.setAttribute("ui.style", "fill-color: #000;");
		}
		for (Node n : graph) {
			n.setAttribute("ui.style", "fill-color: #FFF;");
			if (!vis.containsKey(n.getId())) {
				PriorityQueue<Edge> q = new PriorityQueue<Edge>(graph.getEdgeCount(), new Comparator<Edge>() {
					@Override
					public int compare(Edge a, Edge b) {
						String s = a.getAttribute("-attribute-width").toString();
						double widthA = (double) Double.parseDouble(s);
						s = b.getAttribute("-attribute-width").toString();
						double widthB = (double) Double.parseDouble(s);
						return Double.compare(widthA, widthB);
					}
				});
				ArrayList<Node> nodes = new ArrayList<Node>();
				HashSet<Integer> nodesHash = new HashSet<Integer>();
				HashSet<Integer> edgesHash = new HashSet<Integer>();
				Node nodeRandom = n;
				q.addAll(nodeRandom.getEdgeSet());
				nodes.add(nodeRandom);
				nodesHash.add(nodeRandom.getIndex());
				while (!q.isEmpty()) {
					Edge p = q.poll();
					if (p != null) {	
						Node A = p.getNode0();
						Node B = p.getNode1();
						if (!nodesHash.contains(A.getIndex())) {
							nodes.add(A);
							q.addAll(A.getEdgeSet());
							nodesHash.add(A.getIndex());
							edgesHash.add(p.getIndex());
						} else if (!nodesHash.contains(B.getIndex())) {
							nodes.add(B);
							q.addAll(B.getEdgeSet());
							nodesHash.add(B.getIndex());
							edgesHash.add(p.getIndex());
						}
					}
				}

				graph.setAttribute("ui.style", "background-color: black;");

				for (Edge e : graph.getEdgeSet()) {
					if (edgesHash.contains(e.getIndex()))
						e.setAttribute("ui.style", "fill-color: #FFF;");
				}

				for (Node m : graph) {
					if (nodesHash.contains(m.getIndex()))
						vis.put(m.getId(), true);
					// m.setAttribute("ui.style", "fill-color: #FFF;");
				}
			}
		}

	}

}
