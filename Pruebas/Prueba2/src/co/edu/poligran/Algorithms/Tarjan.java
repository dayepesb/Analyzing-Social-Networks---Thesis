package co.edu.poligran.Algorithms;

import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import co.edu.poligran.Colors.ColorRandom;


public class Tarjan  {
	
	private Graph graph;
	
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
			n.addAttribute("label", n.getAttribute(tscc.getSCCIndexAttribute()).toString());
			int index = Integer.parseInt(n.getAttribute("label").toString());
			n.addAttribute("ui.style", "fill-color:" + arrayColors[index]);
		}
	}
	
}
