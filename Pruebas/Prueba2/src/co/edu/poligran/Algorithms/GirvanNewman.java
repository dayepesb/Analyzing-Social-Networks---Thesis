package co.edu.poligran.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import co.edu.poligran.Colors.ColorRandom;

public class GirvanNewman {
	private Graph graph;
	int max;

	public GirvanNewman(Graph graph) {
		this.graph = graph;
	}

	public static void main(String[] args) {
		Graph g = new SingleGraph("example");

		g.addNode("A").addAttribute("xy", 0, 1);
		g.addNode("B").addAttribute("xy", 1, 2);
		g.addNode("C").addAttribute("xy", 1, 1);
		g.addNode("D").addAttribute("xy", 1, 0);
		
		g.addNode("E").addAttribute("xy", 2, 2);
		g.addNode("F").addAttribute("xy", 2, 1);
		g.addNode("G").addAttribute("xy", 2, 4);
		
		g.addNode("H").addAttribute("xy", 2, 2);
		g.addNode("I").addAttribute("xy", 2, 1);
		g.addNode("J").addAttribute("xy", 2, 4);
		
		g.addEdge("AB", "A", "B").addAttribute("-attribute-width", 1);
		g.addEdge("AC", "A", "C").addAttribute("-attribute-width", 1);
		g.addEdge("AD", "A", "D").addAttribute("-attribute-width", 1);
		g.addEdge("BC", "B", "C").addAttribute("-attribute-width", 1);
		g.addEdge("CD", "C", "D").addAttribute("-attribute-width", 1);
		
		g.addEdge("BG", "B", "G").addAttribute("-attribute-width", 1);
		g.addEdge("GE", "G", "E").addAttribute("-attribute-width", 1);
		g.addEdge("GF", "G", "F").addAttribute("-attribute-width", 1);
		g.addEdge("EF", "E", "F").addAttribute("-attribute-width", 1);
		
		g.addEdge("HI", "H", "I").addAttribute("-attribute-width", 1);
		g.addEdge("IJ", "I", "J").addAttribute("-attribute-width", 1);
		g.addEdge("JH", "J", "H").addAttribute("-attribute-width", 1);
		g.addEdge("CH", "C", "H").addAttribute("-attribute-width", 1);
		g.addEdge("HF", "H", "F").addAttribute("-attribute-width", 1);

		g.addAttribute("ui.style", "fill-color:#fff;");
		
		g.display();
		GirvanNewman gv=new GirvanNewman(g);
		gv.compute();
		
	}

	public void compute() {
		
		loop:while (true) {
			Tarjan tj = new Tarjan(this.graph);
			try {
				tj.computeGirvanNewman();
				BetweennessEdge bedg = new BetweennessEdge(this.graph);
				bedg.compute();
				int max = bedg.getMax();
				//System.out.println(max);
				if (tj.getMax()+2 < max) {
					HashMap<Integer, int[]> dic = bedg.getDic();
					for (Entry<Integer, int[]> c : dic.entrySet()) {
						if (c.getValue()[0] == max) {
							this.graph.removeEdge(this.graph.getEdge(c.getKey()).getId());
						}
					}
				}else{
					//System.out.println("djkags");
					ColorRandom cr=new ColorRandom();
					String arrayColors[]= cr.getColorRamdom();
					for(Node n :graph){
						int index = Integer.parseInt(n.getAttribute("scc").toString());
						n.addAttribute("ui.style", "fill-color:" + arrayColors[(index%arrayColors.length)]);
					}
					break loop;
				}
				//Thread.sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
				break loop;
			}
			
		}
	}

}
