package co.edu.poligran.Test;
 import java.io.IOException;
import java.io.StringReader;

import org.graphstream.algorithm.APSP;
import org.graphstream.algorithm.Centroid;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSourceDGS;
 
 //                     +--- E
 // A --- B --- C -- D -|--- F
 //                     +--- G
 
 public class CentroidTest {
 	static String my_graph =
                "DGS004\n" + 
                "my 0 0\n" + 
                "an A \n" +
                "an B \n" +
                "an C \n" +
                "an D \n" +
                "an E \n" +
                "an F \n" +
                "an G \n" +
                "ae AB A B \n" +
                "ae BC B C \n" +
                "ae CD C D \n" +
                "ae DE D E \n" +
                "ae DF D F \n" +
                "ae DG D G \n";
    static String styleSheet =
		"edge {"+
		"	size: 2px;"+
		"	fill-color: red, yellow, green, #444;"+
		"	fill-mode: dyn-plain;"+
		"}"+
		"node {"+
		"	size: 10px;"+
		"	fill-color: #444;"+
		"}";
 
 	public static void main(String[] args) throws IOException {
 		Graph graph = new DefaultGraph("Centroid Test");
 		StringReader reader = new StringReader(my_graph);
 
 		FileSourceDGS source = new FileSourceDGS();
 		source.addSink(graph);
 		source.readAll(reader);
 
 		APSP apsp = new APSP();
 		apsp.init(graph);
 		apsp.compute();
 
 		Centroid centroid = new Centroid();
 		centroid.init(graph);
 		centroid.compute();
	    graph.addAttribute("ui.stylesheet", styleSheet);

 
 		for (Node n : graph.getEachNode()) {
 			Boolean in = n.getAttribute("centroid");
 			
 			for (Edge e:n.getEdgeSet()) {
 				e.setAttribute("ui.color", 100);
			}
 			
 
 			System.out.printf("%s is%s in the centroid.\n", n.getId(), in ? ""
 					: " not");
 		}
 		graph.display();
 	}
 }