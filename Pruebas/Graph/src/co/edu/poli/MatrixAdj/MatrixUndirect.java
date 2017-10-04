package co.edu.poli.MatrixAdj;

import java.util.ArrayList;

import co.edu.poli.GraphPoli.GraphPoliDirect;
import co.edu.poli.Link.Link;

public class MatrixUndirect implements Matrix {
	ArrayList<ArrayList<Double>> matAdj;
	GraphPoliDirect graph;

	public MatrixUndirect() {
		matAdj = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < graph.cardinalityVertex(); i++) {
			ArrayList<Double> t = new ArrayList<Double>();
			for (int j = 0; j < graph.cardinalityVertex(); j++)
				t.add(0.0);
		}

		for (Link e : graph.getListLinks()) {
			int idNodeA = (int) e.getNodeA().getIdNode();
			int idNodeB = (int) e.getNodeB().getIdNode();
			double weight = e.getWeight();
			matAdj.get(idNodeA).add(idNodeB, weight);
			matAdj.get(idNodeB).add(idNodeA, weight);
		}
	}

	@Override
	public double getWidht(int idA, int idB) {
		return matAdj.get(idA).get(idB);
	}

	@Override
	public int whoIs() {
		return -1;
	}

	@Override
	public void setWidht(int idA, int idB, double newValue) {
		matAdj.get(idA).set(idB, newValue);
		matAdj.get(idB).set(idA, newValue);
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
