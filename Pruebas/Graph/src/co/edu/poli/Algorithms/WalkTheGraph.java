package co.edu.poli.Algorithms;

import java.util.ArrayList;
import java.util.Collection;

import co.edu.poli.Link.LinkDirect;
import co.edu.poli.Vertex.VertexUniq;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public class WalkTheGraph {
	public static Object [] graphToPoli(Graph<VertexUniq, LinkDirect> graph) {
		Collection<VertexUniq> nodes =  graph.getVertices();
		System.out.println(nodes);
		System.out.println("----------------------------------");
		Collection<LinkDirect> links = graph.getEdges();
		System.out.println(links);
//		graph.getEd
//		for (VertexUniq vu : graph) {
//			
//		}
		return null;
	}
	public static void main(String[] args) {
		Graph<VertexUniq, LinkDirect> g = new SparseMultigraph<>();
		VertexUniq v0 = new VertexUniq(0);
		VertexUniq v1 = new VertexUniq(1);
		VertexUniq v2 = new VertexUniq(2);
		g.addVertex(v1);
		g.addVertex(v1);
		g.addVertex(v2);
		g.addEdge(new LinkDirect(12,v1,v2), v1,v2);
		g.addEdge(new LinkDirect(111,v0,v2), v0,v2);
		graphToPoli(g);
	}
}
