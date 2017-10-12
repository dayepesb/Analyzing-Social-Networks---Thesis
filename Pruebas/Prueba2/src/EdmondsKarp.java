import java.util.ArrayDeque;
import java.util.HashMap;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class EdmondsKarp {

	double flow;
	static Graph g;
	static HashMap<Integer, Integer> pi;

	public static void main(String[] args) {
		g=new MultiGraph("Edmonds Karp");
		
		
		g.addNode("1");
		g.addNode("2");
		g.addNode("3");
		g.addNode("4");
		
		//g.addEdge("14", "1", "4");
		g.addEdge("12", "1", "2",true);
		g.addEdge("13", "1", "3",true);
		g.addEdge("23", "2", "3",true);
		g.addEdge("24", "2", "4",true);
		g.addEdge("34", "3", "4",true);
		
		g.addEdge("21", "2", "1",true);
		g.addEdge("31", "3", "1",true);
		g.addEdge("32", "3", "2",true);
		g.addEdge("42", "4", "2",true);
		g.addEdge("43", "4", "3",true);
		
		//g.getEdge(0).setAttribute("capacity", 5.0);
		g.getEdge(0).setAttribute("capacity", 20.0);
		g.getEdge(1).setAttribute("capacity", 10.0);
		g.getEdge(2).setAttribute("capacity", 5.0);
		g.getEdge(3).setAttribute("capacity", 10.0);
		g.getEdge(4).setAttribute("capacity", 20.0);
		
		g.getEdge(5).setAttribute("capacity", 20.0);
		g.getEdge(6).setAttribute("capacity", 10.0);
		g.getEdge(7).setAttribute("capacity", 5.0);
		g.getEdge(8).setAttribute("capacity", 10.0);
		g.getEdge(9).setAttribute("capacity", 20.0);
		
		g.getEdge(0).setAttribute("ui.style", "padding: 20px;");
		
		
//		g.addNode("a");
//		g.addNode("b");
//		g.addNode("c");
//		g.addNode("d");
//		g.addNode("e");
//		g.addNode("f");
//		
//		g.addEdge("ab", "a", "b",true);
//		g.addEdge("ac", "a", "c",true);
//		g.addEdge("bc", "b", "c",true);
//		g.addEdge("ce", "c", "e",true);
//		g.addEdge("cd", "c", "d",true);
//		g.addEdge("de", "d", "e",true);
//		g.addEdge("df", "d", "f",true);
//		g.addEdge("bd", "b", "d",true);
//		g.addEdge("be", "b", "e",true);
//		g.addEdge("ef", "e", "f",true);
//		
//		g.getEdge("ab").setAttribute("capacity", 20.0);
//		g.getEdge("ac").setAttribute("capacity", 18.0);
//		g.getEdge("bc").setAttribute("capacity", 7.0);
//		g.getEdge("ce").setAttribute("capacity", 5.0);
//		g.getEdge("cd").setAttribute("capacity", 6.0);
//		g.getEdge("de").setAttribute("capacity", 7.0);
//		g.getEdge("df").setAttribute("capacity", 18.0);
//		g.getEdge("bd").setAttribute("capacity", 14.0);
//		g.getEdge("be").setAttribute("capacity", 3.0);
//		g.getEdge("ef").setAttribute("capacity", 6.0);
		for(Node n:g){
			n.addAttribute("label",n.getId());
		}
		
		for(Edge e:g.getEdgeSet()){
			e.addAttribute("label", ""+e.getAttribute("capacity"));
		}
		
		g.display();
		
		
		
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		System.out.println("El flujo maximo es: "+flow(0, 3));
		
		
		
		for(Edge e:g.getEdgeSet()){
			e.addAttribute("label", ""+e.getAttribute("capacity"));
		}
		
		
		
	}

	static double flow(int s, int t) {
		double flow = 0;

		pi = new HashMap<>();
		while (bfs(s, t)) {
			double minFlow = Double.MAX_VALUE;
			for (int v = t; v != s; v = pi.get(v)) {
				int u = pi.get(v);
				Edge e = g.getNode(u).getEdgeBetween(v);
				minFlow = Math.min(minFlow, (Double) g.getEdge(e.getIndex()).getAttribute("capacity"));
			}

			for (int v = t; v != s; v = pi.get(v)) {
				int u = pi.get(v);
				// Restar
				if (g.getNode(u).hasEdgeBetween(v)) {
					Edge e = g.getNode(u).getEdgeBetween(v);
					double c = (double) g.getEdge(e.getIndex()).getAttribute("capacity");
					if (c - minFlow == 0) {
						g.removeEdge(e.getIndex());
					} else {
						g.getEdge(e.getIndex()).setAttribute("capacity", (c - minFlow));
					}
				}

				// Sumar
				if (g.getNode(v).hasEdgeBetween(u)) {
					Edge e = g.getNode(v).getEdgeBetween(u);
					double c = (double) g.getEdge(e.getIndex()).getAttribute("capacity");
					g.getEdge(e.getIndex()).setAttribute("capacity", (c + minFlow));
					g.getEdge(e.getIndex()).addAttribute("ui.style", "fill-color: red;");

				} else {
					String name = g.getNode(v).getId() + g.getNode(u).getId();
					g.addEdge(name, v, u, true);
					g.getEdge(name).addAttribute("capacity", minFlow);
					g.getEdge(name).addAttribute("ui.style", "fill-color: red;");

				}

			}
			flow += minFlow;
		}
		return flow;
	}

	static boolean bfs(int s, int t) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(s);
		pi = new HashMap<>();
		pi.put(s, -1);
		while (!q.isEmpty()) {
			int u = q.poll();
			for (Edge e : g.getNode(u).getEdgeSet()) {
				int v = e.getNode1().getIndex();
				if (!pi.containsKey(v)) {
					q.offer(v);
					pi.put(v, u);
				}
			}
		}
		if (pi.containsKey(t)) {
			return true;
		}
		return false;
	}

}
