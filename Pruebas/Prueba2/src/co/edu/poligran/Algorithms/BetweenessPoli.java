package co.edu.poligran.Algorithms;

import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class BetweenessPoli {
	private Graph graph;
	
	public BetweenessPoli(Graph graph) {
		this.graph=graph;
		BetweennessCentrality bc= new BetweennessCentrality();
		bc.init(this.graph);
		bc.compute();
		double min=Double.MAX_VALUE;
		double max=Double.MIN_VALUE;
		for (Node node : graph) {
			node.setAttribute("ui.style", "fill-color:#fff;");
		}
		for (Edge edge : graph.getEdgeSet()) {
			edge.setAttribute("ui.style", "fill-color:#fff;");
		}
		for(Node n:graph){
			double temp=(double)n.getAttribute("Cb");
			if(temp>max)max=temp;
			if(temp<min)min=temp;
			
//			n.setAttribute("label", n.getId());
//			System.out.println(n.getId()+" "+(Double)n.getAttribute("Cb"));
		}
		for(Node n:graph) {
			double temp=(double)n.getAttribute("Cb");
			double color=((temp-min)/(max-min));
			System.out.println(color);
			n.setAttribute("ui.color", 0.50000);
		}
	}
	
	public void compute(){
	}
}