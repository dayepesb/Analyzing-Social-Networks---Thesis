package co.edu.poli.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import co.edu.poli.GraphPoli.GraphPoli;
import co.edu.poli.GraphPoli.GraphPoliDirect;
import co.edu.poli.GraphPoli.GraphPoliMixed;
import co.edu.poli.GraphPoli.GraphPoliUndirect;
import co.edu.poli.Link.Link;

public class ArticulationPoints {
	private long cnt;
	private HashMap<Long, Long> ord, low;
	private GraphPoli graph;
	private HashMap<Long, ArrayList<Link>> map;

	public ArticulationPoints(GraphPoliDirect graph) {
		this.graph = graph;
	}

	public ArticulationPoints(GraphPoliUndirect graph) {
		this.graph = graph;
	}

	public ArticulationPoints(GraphPoliMixed graph) {
		this.graph = graph;
	}

	public ArrayList<Long> articulationPoints() {
		map = graph.getGraphPoli();
		ord = new HashMap<>();
		low = new HashMap<>();
		for (Entry<Long, ArrayList<Link>> m : map.entrySet()) {
			ord.put(m.getKey(), 0L);
			low.put(m.getKey(), 0L);
		}
		ArrayList<Long> res = new ArrayList<>();
		for (Entry<Long, ArrayList<Link>> m : map.entrySet()) {
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
		for (Link e : map.get(w)) {
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
