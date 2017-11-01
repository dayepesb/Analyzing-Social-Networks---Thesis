package co.edu.poligran.Algorithms;

import java.util.HashMap;
import java.util.Map.Entry;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import scala.runtime.StringFormat;

public class ClosenessCentrality {

	private Graph graph;

	public ClosenessCentrality(Graph g) {
		this.graph = g;
		for (Edge e : graph.getEdgeSet()) {
			if (!e.hasAttribute("-attribute-width"))
				e.setAttribute("-attribute-width", 1.0);
		}
		remoteness();
		closeness();
	}

	public void closeness() {
		Double total = 0d;

		for (Node n : graph) {
			total += (double) n.getAttribute("-attribute-remoteness");
		}
		for (Node n : graph) {
			double closenessNode = closeness(total, n);
			if ((double) n.getAttribute("-attribute-remoteness") != 0) {
				n.setAttribute("-attribute-closeness", String.format("%.5f", closenessNode));
			} else {
				n.setAttribute("-attribute-closeness", String.format("%.5f", Double.MAX_VALUE));
			}
		}

	}

	public double closeness(double total, Node node) {
		return total / (double) node.getAttribute("-attribute-remoteness");
	}

	public void remoteness() {
		for (Node n : graph) {
			int id = n.getIndex();
			double remotenessNode = remoteness(id);
			n.setAttribute("-attribute-remoteness", remotenessNode);
		}
	}

	public double remoteness(int node) {
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, "result", "-attribute-width");
		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(node));
		dijkstra.compute();
		double distTotal = 0;
		for (Node n : graph) {
			double dist=dijkstra.getPathLength(n);
			if(dist!=Double.POSITIVE_INFINITY)
				distTotal += dist;
		}
		return distTotal;
	}
}
