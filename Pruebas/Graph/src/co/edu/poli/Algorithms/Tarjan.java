package co.edu.poli.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import co.edu.poli.GraphPoli.GraphPoli;
import co.edu.poli.GraphPoli.GraphPoliDirect;
import co.edu.poli.GraphPoli.GraphPoliMixed;
import co.edu.poli.GraphPoli.GraphPoliUndirect;
import co.edu.poli.Link.Link;
import co.edu.poli.Node.Vertex;

public class Tarjan {
	private GraphPoli graph;
	private long vertices;
	private long preCount;
	private HashMap<Long, Long> low;
	private HashMap<Long, Boolean> visited;
	private ArrayList<ArrayList<Long>> sccComponent;
	private Stack<Long> stack;

	public Tarjan(GraphPoliDirect graph) {
		this.graph = graph;
	}

	public Tarjan(GraphPoliUndirect graph) {
		this.graph = graph;
	}

	public Tarjan(GraphPoliMixed graph) {
		this.graph = graph;
	}

	public ArrayList<ArrayList<Long>> getSCComponents() {
		vertices = graph.cardinalityVertex();
		low = new HashMap<>();
		visited = new HashMap<>();
		stack = new Stack<>();
		sccComponent = new ArrayList<>();
		preCount = 0;

		ArrayList<Vertex> nodes = graph.getListVertex();
		for (Vertex n : nodes) {
			low.put(n.getIdNode(), 0l);
			visited.put(n.getIdNode(), false);
		}

		for (Vertex n : nodes) {
			if (!visited.get(n.getIdNode())) {
				dfs(n.getIdNode());
			}
		}

		return sccComponent;
	}

	private void dfs(long v) {
		low.put(v, preCount + 1);
		visited.put(v, true);
		stack.push(v);
		long min = low.get(v);
		for (Link w : graph.getGraphPoli().get(v)) {
			if (!visited.get(w.getNodeB().getIdNode())) {
				dfs(w.getNodeB().getIdNode());
			}
			if (low.get(w.getNodeB().getIdNode()) < min) {
				min = low.get(w.getNodeB().getIdNode());
			}
		}
		if (min < low.get(v)) {
			// low[v] = min;
			low.put(v, min);
			return;
		}
		ArrayList<Long> component = new ArrayList<>();
		long w;
		do {
			w = stack.pop();
			component.add(w);
			low.put(w, vertices);
		} while (w != v);
		sccComponent.add(component);
	}
}
