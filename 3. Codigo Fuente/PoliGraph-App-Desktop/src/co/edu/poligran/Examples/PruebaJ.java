package co.edu.poligran.Examples;
import java.util.Collection;
import java.util.HashMap;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

public class PruebaJ {

	public static void main(String[] args) {
		
		Graph g=new MultiGraph("prueba");
		g.addNode("a");
		g.addNode("b");
		g.addNode("c");
		g.addNode("d");
		g.addEdge("ab", "a", "b",true);
		g.addEdge("bc", "b", "c");
		g.addEdge("ac", "a", "c");
		g.addEdge("ad", "a", "d");
		//g.addEdge("ba", "b", "a",true);
		

		for (Edge e : g.getEachEdge()) {
			System.out.println(e);
		}
		
		g.addAttribute("a", 100);
		System.out.println(g.getNodeSet());
		System.out.println(g.getEachAttributeKey());
		g.display();
		g.getNode(0).setAttribute("Nombre", "Julian");
		System.out.println((String)g.getNode(0).getAttribute("Nombre"));
		
		
		System.out.println(g.getNode(0).hasEdgeBetween(1));
		Edge e=g.getNode("a").getEdgeBetween(1);
		System.out.println(e);
		System.out.println(e.isDirected());
		
	}

}
