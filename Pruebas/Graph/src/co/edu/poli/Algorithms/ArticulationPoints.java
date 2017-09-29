package co.edu.poli.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import co.edu.poli.Edge.Edge;
import co.edu.poli.Graph.Graph;
import co.edu.poli.Graph.GraphDirect;
import co.edu.poli.Graph.GraphMixed;
import co.edu.poli.Graph.GraphUndirect;

public class ArticulationPoints {
	private long cnt;
	private HashMap<Long, Long> ord, low;
	private Graph graph;
	private HashMap<Long, ArrayList<Edge>> map;

	public ArticulationPoints(GraphDirect graph) {
		this.graph = graph;
	}

	public ArticulationPoints(GraphUndirect graph) {
		this.graph = graph;
	}

	public ArticulationPoints(GraphMixed graph) {
		this.graph = graph;
	}

	public ArrayList<Long> articulationPoints() {
		map = graph.getGraph();
		ord = new HashMap<>();
		low = new HashMap<>();
		for (Entry<Long, ArrayList<Edge>> m : map.entrySet()) {
			ord.put(m.getKey(), 0L);
			low.put(m.getKey(), 0L);
		}
		ArrayList<Long> res = new ArrayList<>();
		for (Entry<Long, ArrayList<Edge>> m : map.entrySet()) {
			if (ord.get(m.getKey()) == 0) {
				cnt = 1;
				searchArticulationPoints(m.getKey(), m.getKey(), res);
			}
		}
		return res;
	}

	private void searchArticulationPoints(long v, long w, ArrayList<Long> res) {
		low.put(w, cnt);
		ord.put(w, cnt);
		cnt++;
		for (Edge e : map.get(w)) {
			long t = e.getNodeB().getIdNode();
			if (ord.get(t) == 0) {
				searchArticulationPoints(w, t, res);
				low.put(w, Math.min(low.get(w), low.get(t)));
				if ((ord.get(w) == 1 && ord.get(t) != 2) || (ord.get(w) != 1 && low.get(t) >= ord.get(w))) {
					res.add(w);
				}else {
					low.put(w,Math.min(low.get(w), ord.get(t)));
				}
			}
		}
	}
}
