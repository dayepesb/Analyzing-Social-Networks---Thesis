package co.edu.poli.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import co.edu.poli.GraphPoli.GraphPoli;
import co.edu.poli.GraphPoli.GraphPoliDirect;
import co.edu.poli.GraphPoli.GraphPoliMixed;
import co.edu.poli.GraphPoli.GraphPoliUndirect;
import co.edu.poli.Vertex.Vertex;

public class BetweennessCentrality {
	private GraphPoli graph;
	private HashMap<Long, Long> centrality;
	private final long INF = Long.MAX_VALUE / 3;
	private ArrayList<Vertex> listNodes;
	private boolean mixted,direct;

	public BetweennessCentrality(GraphPoliDirect graph) {
		this.graph = graph;
		direct = true;
		mixted = false;
	}

	public BetweennessCentrality(GraphPoliUndirect graph) {
		this.graph = graph;
		direct = false;
		mixted = false;
	}

	public BetweennessCentrality(GraphPoliMixed graph) {
		this.graph = graph;
		direct = false;
		mixted = true;
	}

	public HashMap<Long, Long> centralityNodes() {
		centrality = new HashMap<>();
		listNodes = graph.getListVertex();

		for (Vertex n : listNodes) {
			centrality.put(n.getIdNode(), 0L);
		}

		Dijkstra dijkstra ;
		if(mixted) {
			dijkstra = new Dijkstra((GraphPoliMixed)graph);
		}else if(direct) {
			dijkstra = new Dijkstra((GraphPoliDirect)graph);
		}else {
			dijkstra  = new Dijkstra((GraphPoliUndirect)graph);
		}
		for (Vertex n : listNodes) {
			HashMap<Long, String> path = dijkstra.pathDijkstra(n.getIdNode());
			getCentrality(path);
		}

		return centrality;
	}

	private void getCentrality(HashMap<Long, String> path) {
		StringTokenizer st;
		for (Entry<Long, String>pathNode : path.entrySet()) {
			st = new StringTokenizer(pathNode.getValue());
			st.nextToken();
			int c = st.countTokens();
			for (int i = 0; i < c; i++) {
				long w = Long.parseLong(st.nextToken());
				if(pathNode.getKey()!=w) {
					centrality.put(w, centrality.get(w)+1);
				}
			}
		}
	}
}
