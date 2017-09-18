package co.edu.poli.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import co.edu.poli.Edge.Edge;
import co.edu.poli.Graph.Graph;
import co.edu.poli.Graph.GraphDirect;
import co.edu.poli.Graph.GraphMixed;
import co.edu.poli.Graph.GraphUndirect;
import co.edu.poli.Node.Node;

public class Tarjan {
	private Graph graph;
	private long vertices;
	private long preCount;
	private HashMap<Long, Long> low;
	private HashMap<Long, Boolean> visited;
	private ArrayList<ArrayList<Long>> sccComponent;
	private Stack<Long> stack;

	public Tarjan(GraphDirect graph) {
		this.graph = graph;
	}

	public Tarjan(GraphUndirect graph) {
		this.graph = graph;
	}

	public Tarjan(GraphMixed graph) {
		this.graph = graph;
	}

	public ArrayList<ArrayList<Long>> getSCComponents() {
		vertices = graph.cardinalityNodes();
		low = new HashMap<>();
		visited = new HashMap<>();
		stack = new Stack<>();
		sccComponent = new ArrayList<>();
		preCount = 0;

		ArrayList<Node> nodes = graph.getListNodes();
		for (Node n : nodes) {
			low.put(n.getIdNode(), 0l);
			visited.put(n.getIdNode(), false);
		}

		for (Node n : nodes) {
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
		for (Edge w : graph.getGraph().get(v)) {
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
