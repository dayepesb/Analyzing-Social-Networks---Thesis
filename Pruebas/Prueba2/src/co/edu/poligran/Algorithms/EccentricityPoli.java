package co.edu.poligran.Algorithms;

import org.graphstream.algorithm.Eccentricity;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class EccentricityPoli {
	
	private Graph graph;
	
	public EccentricityPoli(Graph graph) {
		this.graph=graph;
		
	}
	
	public void compute(){
		Eccentricity eccentricity = new Eccentricity();
 		eccentricity.init(graph);
 		eccentricity.compute();
 		for(Node n:graph){
 			Boolean e=n.getAttribute("eccentricity");
 			if(e){
 				n.setAttribute("ui.style", "fill-color: #f00;");
 			}else{
 				n.setAttribute("ui.style", "fill-color: #04ff00;");
 			}
 		}
	}
}
