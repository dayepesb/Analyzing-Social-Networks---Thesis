package co.edu.poli.MatrixAdj;

import java.util.ArrayList;

import co.edu.poli.Edge.Edge;
import co.edu.poli.Graph.GraphDirect;

public class MatrixDirected implements Matrix {
	ArrayList<ArrayList<Double>> matAdj;
	GraphDirect graph;

	public MatrixDirected(GraphDirect graph) {

		matAdj = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < graph.cardinalityNodes(); i++) {
			ArrayList<Double> t = new ArrayList<Double>();
			for (int j = 0; j < graph.cardinalityNodes(); j++)
				t.add(0.0);
		}

		for (Edge e : graph.getListEdges()) {
			int idNodeA = (int) e.getNodeA().getIdNode();
			int idNodeB = (int) e.getNodeB().getIdNode();
			double weight = e.getWeight();
			matAdj.get(idNodeA).add(idNodeB, weight);
		}
	}

	@Override
	public double getWidht(int idA, int idB) {
		return matAdj.get(idA).get(idB);
	}

	@Override
	public int whoIs() {
		return 1;
	}

	@Override
	public void setWidht(int idA, int idB, double newValue) {
		matAdj.get(idA).set(idB, newValue);
	}

	@Override
	public void addNode(long idNode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNode(long idNode) {
		// TODO Auto-generated method stub
		
	}
}
