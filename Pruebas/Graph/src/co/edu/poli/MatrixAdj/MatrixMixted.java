package co.edu.poli.MatrixAdj;

import java.util.ArrayList;

import co.edu.poli.Edge.Edge;
import co.edu.poli.Graph.GraphDirect;

public class MatrixMixted implements Matrix {
	ArrayList<ArrayList<Double>> matAdj;
	GraphDirect graph;

	public MatrixMixted() {
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
			if (!e.isDirect()) {
				matAdj.get(idNodeB).add(idNodeA, weight);
			}
		}
	}

	@Override
	public double getWidht(int idA, int idB) {
		return matAdj.get(idA).get(idB);
	}

	@Override
	public int whoIs() {
		return 0;
	}

	@Override
	public void setWidht(int idA, int idB, double newValue) {
		boolean isDirect = true;
		for (Edge e : graph.getGraph().get(idA)) {
			if(e.getId()==idB){
				isDirect=e.isDirect();
				break;
			}
		}
		matAdj.get(idA).set(idB, newValue);
		if (!isDirect){
			matAdj.get(idB).set(idA, newValue);
		}
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
