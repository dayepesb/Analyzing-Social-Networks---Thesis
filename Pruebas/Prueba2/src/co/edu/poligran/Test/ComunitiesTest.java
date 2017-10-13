package co.edu.poligran.Test;

import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class ComunitiesTest {
	public static void main(String[] args) {
		Graph graph = new MultiGraph("Comunities");
		String nodes = "abcdefghijkl";
		for (int i = 0; i < nodes.length(); i++)
			graph.addNode(nodes.substring(i, i + 1));
		graph.addEdge("ab", "a", "b");
		graph.addEdge("ac", "a", "c");
		graph.addEdge("ad", "a", "d");
		graph.addEdge("bd", "b", "d");
		graph.addEdge("bf", "b", "f");
		graph.addEdge("be", "b", "e");
		graph.addEdge("cd", "c", "d");
		graph.addEdge("cf", "c", "f");
		graph.addEdge("cg", "c", "g");
		graph.addEdge("de", "d", "e");
		graph.addEdge("dh", "d", "h");
		graph.addEdge("dg", "d", "g");
		graph.addEdge("ei", "e", "i");
		graph.addEdge("ej", "e", "j");
		graph.addEdge("eh", "e", "h");
		graph.addEdge("eg", "e", "g");
		graph.addEdge("ef", "e", "f");
		graph.addEdge("fh", "f", "h");
		graph.addEdge("fj", "f", "j");
		graph.addEdge("fg", "f", "g");
		graph.addEdge("fk", "f", "k");
		graph.addEdge("gj", "g", "j");
		graph.addEdge("gh", "g", "h");
		graph.addEdge("hi", "h", "i");
		graph.addEdge("hj", "h", "j");
		graph.addEdge("il", "i", "l");
		graph.addEdge("ij", "i", "j");
		graph.addEdge("jl", "j", "l");
		graph.addEdge("jk", "j", "k");
		graph.addEdge("kl", "k", "l");
		graph.getNode("e").addAttribute("comunity", "true");
		graph.getNode("f").addAttribute("comunity", "true");
		graph.getNode("h").addAttribute("comunity", "true");
		graph.getNode("g").addAttribute("comunity", "true");

		BetweennessCentrality bc = new BetweennessCentrality();

		bc.init(graph);
		bc.compute();

		for (Node u : graph) {
			System.out.println(u.getId()+"-"+u.getAttribute("Cb"));
		}

		for (Node u : graph) {
			u.addAttribute("ui.style", "size:"+((double)u.getAttribute("Cb")*2.5+10)+"px;");
			u.addAttribute("label", u.getId());
		}


		graph.display();
		try {
		Thread.sleep(5000);}catch (Exception e) {
			// TODO: handle exception
		}
		graph.removeNode("f");
		bc.init(graph);
		bc.compute();
		for (Node u : graph) {
			u.addAttribute("ui.style", "size:"+((double)u.getAttribute("Cb")*2.5+10)+"px;");
			u.addAttribute("label", u.getId());
		}
	}
}
