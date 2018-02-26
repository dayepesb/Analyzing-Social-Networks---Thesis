package co.edu.poligran.Algorithms;

import java.util.HashMap;
import java.util.Map.Entry;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import co.edu.poligran.Colors.ColorRandom;

public class GirvanNewman {
	private Graph graph;
	int max;

	public GirvanNewman(Graph graph) {
		this.graph = graph;
	}

	public void compute() {

		loop: while (true) {
			Tarjan tj = new Tarjan(this.graph);
			try {
				tj.computeGirvanNewman();
				BetweennessEdge bedg = new BetweennessEdge(this.graph);
				bedg.compute();
				int max = bedg.getMax();
				// System.out.println(max);
				if (tj.getMax() + 2 < max) {
					HashMap<Integer, int[]> dic = bedg.getDic();
					for (Entry<Integer, int[]> c : dic.entrySet()) {
						if (c.getValue()[0] == max) {
							this.graph.removeEdge(this.graph.getEdge(c.getKey()).getId());
						}
					}
				} else {
					// System.out.println("djkags");
					ColorRandom cr = new ColorRandom();
					String arrayColors[] = cr.getColorRamdom();
					for (Node n : graph) {
						if (n.getEdgeSet().size() == 0) {
							this.graph.removeNode(n);
						} else {
							int index = Integer.parseInt(n.getAttribute("scc").toString());
							n.addAttribute("ui.style", "fill-color:" + arrayColors[(index % arrayColors.length)]);
						}
					}
					break loop;
				}
				// Thread.sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
				break loop;
			}

		}

	}

}
