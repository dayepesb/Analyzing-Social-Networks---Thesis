package co.edu.poli.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import co.edu.poli.Graph.Graph;
import co.edu.poli.Graph.GraphDirect;
import co.edu.poli.Graph.GraphMixed;
import co.edu.poli.Graph.GraphUndirect;
import co.edu.poli.Node.Node;

public class BetweennessCentrality {
	private Graph graph;
	private HashMap<Long, Long> centrality;
	private final long INF = Long.MAX_VALUE / 3;
	private ArrayList<Node> listNodes;
	private boolean mixted,direct;

	public BetweennessCentrality(GraphDirect graph) {
		this.graph = graph;
		direct = true;
		mixted = false;
	}

	public BetweennessCentrality(GraphUndirect graph) {
		this.graph = graph;
		direct = false;
		mixted = false;
	}

	public BetweennessCentrality(GraphMixed graph) {
		this.graph = graph;
		direct = false;
		mixted = true;
	}

	public HashMap<Long, Long> centralityNodes() {
		centrality = new HashMap<>();
		listNodes = graph.getListNodes();

		for (Node n : listNodes) {
			centrality.put(n.getIdNode(), 0L);
		}

		Dijkstra dijkstra ;
		if(mixted) {
			dijkstra = new Dijkstra((GraphMixed)graph);
		}else if(direct) {
			dijkstra = new Dijkstra((GraphDirect)graph);
		}else {
			dijkstra  = new Dijkstra((GraphUndirect)graph);
		}
		for (Node n : listNodes) {
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
