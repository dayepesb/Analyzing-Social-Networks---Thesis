package co.edu.poligran.Algorithms;

import java.util.ArrayList;
import java.util.Comparator;
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
		PriorityQueue<Edge> q = new PriorityQueue<Edge>(graph.getEdgeCount(), new Comparator<Edge>() {
			@Override
			public int compare(Edge a, Edge b) {
				double widthA = (double) a.getAttribute("-attribute-width");
				double widthB = (double) b.getAttribute("-attribute-width");
				return Double.compare(widthA, widthB);
			}
		});
		ArrayList<Node> nodes = new ArrayList<Node>();
		HashSet<Integer> nodesHash = new HashSet<Integer>();
		HashSet<Integer> edgesHash = new HashSet<Integer>();
		Node nodeRandom = null;
		for (Node n : graph) {
			nodeRandom = n;
			break;
		}
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
			if (!edgesHash.contains(e.getIndex())) {
				e.setAttribute("ui.style", "fill-color: #000;");
			} else
				e.setAttribute("ui.style", "fill-color: #FFF;");

		}

		for (Node n : graph) {
			if (!nodesHash.contains(n.getIndex())) {
				n.setAttribute("ui.style", "fill-color: #000;");
			} else
				n.setAttribute("ui.style", "fill-color: #FFF;");

		}

	}

}
