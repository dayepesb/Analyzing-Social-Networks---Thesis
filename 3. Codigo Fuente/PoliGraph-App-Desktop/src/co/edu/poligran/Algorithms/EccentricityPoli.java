package co.edu.poligran.Algorithms;

import org.graphstream.algorithm.APSP;
import org.graphstream.algorithm.Eccentricity;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.stream.file.FileSourceDGS;

public class EccentricityPoli {
	
	private Graph graph;
	
	public EccentricityPoli(Graph graph) {
		this.graph=graph;
		
	}
	
	public void compute(){
		APSP apsp = new APSP();
 		apsp.init(graph);
 		apsp.compute();
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
