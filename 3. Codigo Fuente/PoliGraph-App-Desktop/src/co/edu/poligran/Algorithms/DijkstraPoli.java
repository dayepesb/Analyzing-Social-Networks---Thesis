package co.edu.poligran.Algorithms;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class DijkstraPoli {
	
	private Graph graph;
	
	public DijkstraPoli(Graph graph) {
		this.graph=graph;
		for(Edge e:this.graph.getEachEdge())
			if(!e.hasAttribute("-attribute-width"))
				e.setAttribute("-attribute-width", 1.0);
	}
	
	public void compute(String nodeA, String nodeB){
		Dijkstra dijkstra= new Dijkstra(Dijkstra.Element.EDGE,"result","-attribute-width");
		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(nodeA));
		dijkstra.compute();
	
		for (Node node : graph) {
			node.setAttribute("ui.style", "fill-color:#fff;");
		}
		for (Edge edge : graph.getEdgeSet()) {
			edge.setAttribute("ui.style", "fill-color:#fff;");
		}
		for(Node node: dijkstra.getPathNodes(graph.getNode(nodeB))){
			node.addAttribute("ui.style","fill-color: yellow;");
			node.addAttribute("ui.style","size: 10px;");
		}
		for(Edge e: dijkstra.getPathEdges(graph.getNode(nodeB))){
			e.addAttribute("ui.style", "fill-color: red;");
		}
	}
	
	
}
