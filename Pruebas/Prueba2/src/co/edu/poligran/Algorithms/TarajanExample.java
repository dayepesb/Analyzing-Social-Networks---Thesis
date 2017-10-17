package co.edu.poligran.Algorithms;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.graphstream.algorithm.TarjanStronglyConnectedComponents;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

import co.edu.poligran.Colors.ColorRandom;


public class TarajanExample extends MultiGraph {
	
	
	public TarajanExample(String id, String nodes) throws Exception {
		super(id);
		for (int i = 0; i < nodes.length(); i++)
			addNode(nodes.substring(i, i + 1));
		addEdge("ac", "a", "c", true).addAttribute("xy", 1,0);
		addEdge("cd", "c", "d", true).addAttribute("xy", 2,0);;
		addEdge("db", "d", "b", true).addAttribute("xy", 1,2);;
		addEdge("ba", "b", "a", true).addAttribute("xy", 1,5);;
		addEdge("hd", "h", "d", true).addAttribute("xy", 7,3);;
		addEdge("fh", "f", "h", true).addAttribute("xy", 8,9);;
		addEdge("ef", "e", "f", true).addAttribute("xy", 2,7);;
		addEdge("ge", "g", "e", true).addAttribute("xy", 5,5);;
		addEdge("fg", "f", "g", true).addAttribute("xy", 7,9);;


		TarjanStronglyConnectedComponents tscc = new TarjanStronglyConnectedComponents();
		tscc.init(this);
		tscc.compute();
		ColorRandom cr = new ColorRandom();
		String arrayColors[] = cr.getArrayColors();
		for (Node n : this.getEachNode()) {
			n.addAttribute("label", n.getAttribute(tscc.getSCCIndexAttribute()).toString());
			int index = Integer.parseInt(n.getAttribute("label").toString());
			n.addAttribute("ui.style", "fill-color:" + arrayColors[index]);
		}
	}

	public static void main(String[] args) throws Exception {
		
		
		Graph g = new TarajanExample("g", "abcdefgh");
		g.display();

	}
}
