package co.edu.poligran.Algorithms;

import java.util.HashMap;
import java.util.Map.Entry;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class DegreeCentrality {

	private Graph graph;
	public HashMap<Integer, Integer> inputDegree;
	public HashMap<Integer, Integer> outputDegree;
	public HashMap<Integer,Integer> totalDegree;
	public int min;
	public int max;
	public DegreeCentrality(Graph g) {
		this.graph = g;
		min=Integer.MAX_VALUE;
		max=Integer.MIN_VALUE;
		inputDegree = InputDegree();
		outputDegree = OutputDegree();
	}

	public HashMap<Integer, Integer> InputDegree() {
		// Rate of popularity of all nodes in a Graph
		inputDegree = new HashMap<>();
		totalDegree=new HashMap<>();
		for (Node n : graph) {
			inputDegree.put(n.getIndex(), n.getInDegree());
			int d=n.getDegree();
			totalDegree.put(n.getIndex(), d);
			min=Math.min(min, d);
			max=Math.max(max, d);
		}

		return inputDegree;
	}

	public HashMap<Integer, Integer> OutputDegree() {
		// Rate of sociality of all nodes in a Graph
		outputDegree = new HashMap<>();
		for (Node n : graph) {
			outputDegree.put(n.getIndex(), n.getOutDegree());
		}
		return outputDegree;
	}
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	public HashMap<Integer, Integer> getTotalDegree() {
		return totalDegree;
	}
}
