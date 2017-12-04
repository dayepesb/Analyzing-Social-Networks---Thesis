package co.edu.poligran.Algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class BetweennessEdge {

	private Graph graph;
	private HashMap<Integer, int[]> dic;
	static int max;
	static int index;

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

		g.addEdge("BG", "B", "G").addAttribute("-attribute-width", 1);
		g.addEdge("GE", "G", "E").addAttribute("-attribute-width", 1);
		g.addEdge("GF", "G", "F").addAttribute("-attribute-width", 1);
		g.addEdge("EF", "E", "F").addAttribute("-attribute-width", 1);
		for (Node n : g) {
			n.addAttribute("label", n.getId());
		}
		g.display();
		BetweennessEdge bt = new BetweennessEdge(g);
		bt.compute();
		for (Entry<Integer, int[]> c : bt.getDic().entrySet()) {
			System.out.println(g.getEdge(c.getKey()) + "  " + c.getValue()[0]);
		}
	}

	public BetweennessEdge(Graph graph) {
		this.graph = graph;
		for (Node n : graph) {
			if (n.hasAttribute("-attribute-width")) {
				n.setAttribute("-attribute-width", 1.0);
			}
		}
	}

	public void compute() {
		max = -1;
		index = -1;
		dic = new HashMap<Integer, int[]>();
		for (Node u : graph) {
			Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, "result", "-attribute-width");
			dijkstra.init(graph);
			dijkstra.setSource(u);
			dijkstra.compute();
			for (Node node : graph) {
				List<Edge> path = dijkstra.getPath(node).getEdgePath();
				for (Edge e : path) {
					if (!dic.containsKey(e.getIndex())) {
						dic.put(e.getIndex(), new int[] { 0 });
					}
					dic.get(e.getIndex())[0]++;
					int maxT = dic.get(e.getIndex())[0];
					if (maxT > max) {
						index = e.getIndex();
						max = maxT;
					}
				}
			}
		}
	}

	public HashMap<Integer, int[]> getDic() {
		return dic;
	}

	public static int getIndex() {
		return index;
	}

	public static int getMax() {
		return max;
	}

}
