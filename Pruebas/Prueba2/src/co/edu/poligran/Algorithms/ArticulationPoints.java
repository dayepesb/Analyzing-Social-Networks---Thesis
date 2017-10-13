package co.edu.poligran.Algorithms;

import java.util.ArrayList;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;

public class ArticulationPoints {
	private Graph graph;
	private ArrayList<Integer> res;
	private int cnt, ord[], low[];

	public ArticulationPoints(Graph graph) {
		this.graph = graph;
	}

	public void compute() {
		int n = graph.getNodeCount();
		ord = new int[n];
		low = new int[n];
		// res = new boolean[n];
		res = new ArrayList<>();
		for (int v = 0; v < n; v++)
			if (ord[v] == 0) {
				cnt = 1;
				articulationPoints(v, v, graph, res);
			}
	}

	void articulationPoints(int v, int w, Graph graph, ArrayList<Integer> res) {
		low[w] = ord[w] = cnt++;
		for (Edge x : graph.getNode(w).getEdgeSet()) {
			int a = x.getNode0().getIndex(), b = x.getNode1().getIndex();
			int t = a == w ? b : a;
			if (ord[t] == 0) {
				articulationPoints(w, t, graph, res);
				low[w] = Math.min(low[w], low[t]);
				if ((ord[w] == 1 && ord[t] != 2) || (ord[w] != 1 && low[t] >= ord[w]))
					res.add(w);
			} else if (t != v)
				low[w] = Math.min(low[w], ord[t]);
		}
	}

	public ArrayList<Integer> getArticulationPoints() {
		return res;
	}

}
