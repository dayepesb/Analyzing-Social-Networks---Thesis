package co.edu.poligran.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;

public class GirvanNewman {
	private Graph graph;
	int max;

	public GirvanNewman(Graph graph) {
		this.graph = graph;
	}

	public static void main(String[] args) {

	}

	private void compute() {
		while (true) {
			BetweennessEdge bedg = new BetweennessEdge(this.graph);
			int max = bedg.getMax();

			HashMap<Integer, int[]> dic = bedg.getDic();
			for (Entry<Integer, int[]> c : dic.entrySet()) {
				if (c.getValue()[0] == max) {
					this.graph.removeEdge(this.graph.getEdge(c.getKey()).getId());
				}
			}
			Tarjan tj=new Tarjan(this.graph);
			try{
			tj.compute();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
