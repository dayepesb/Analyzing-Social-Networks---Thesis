package co.edu.poligran.Algorithms;

import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import co.edu.poligran.Colors.ColorRandom;


public class Tarjan  {
	
	private Graph graph;
	
	public static void main(String[] args) {
		Graph g = new SingleGraph("example");

		g.addNode("A").addAttribute("xy", 0, 1);
		g.addNode("B").addAttribute("xy", 1, 2);
		g.addNode("C").addAttribute("xy", 1, 1);
		g.addNode("D").addAttribute("xy", 1, 0);
		g.addNode("E").addAttribute("xy", 2, 2);
		g.addNode("F").addAttribute("xy", 2, 1);
		g.addNode("G").addAttribute("xy", 2, 4);
		
		g.addEdge("AB", "A", "B").addAttribute("-attribute-width", 1);
		g.addEdge("AC", "A", "C").addAttribute("-attribute-width", 1);
		g.addEdge("AD", "A", "D").addAttribute("-attribute-width", 1);
		g.addEdge("BC", "B", "C").addAttribute("-attribute-width", 1);
		g.addEdge("CD", "C", "D").addAttribute("-attribute-width", 1);
		
//		g.addEdge("BG", "B", "G").addAttribute("-attribute-width", 1);
		g.addEdge("GE", "G", "E").addAttribute("-attribute-width", 1);
		g.addEdge("GF", "G", "F").addAttribute("-attribute-width", 1);
		g.addEdge("EF", "E", "F").addAttribute("-attribute-width", 1);
		Tarjan tj=new Tarjan(g);
		try{
		tj.compute();
		g.display();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Tarjan(Graph graph) {
		this.graph=graph;
	}
	
	public void compute() throws Exception{
		TarjanStronglyConnectedComponents tscc=new TarjanStronglyConnectedComponents();
		tscc.init(graph);
		tscc.compute();
		ColorRandom cr=new ColorRandom();
		String arrayColors[]= cr.getColorRamdom();
		for (Node node : graph) {
			node.setAttribute("ui.style", "fill-color:#fff;");
		}
		for (Edge edge : graph.getEdgeSet()) {
			edge.setAttribute("ui.style", "fill-color:#fff;");
		}
		for(Node n :graph){
			int index = Integer.parseInt(n.getAttribute(tscc.getSCCIndexAttribute()).toString());
			n.addAttribute("ui.style", "fill-color:" + arrayColors[(index%arrayColors.length)]);
		}
	}
	
}
