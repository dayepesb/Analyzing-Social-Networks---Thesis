package co.edu.poligran.Test;
 import java.awt.Color;
import java.io.IOException;
 import java.io.StringReader;
 
 import org.graphstream.algorithm.coloring.WelshPowell;
 import org.graphstream.graph.ElementNotFoundException;
 import org.graphstream.graph.Graph;
 import org.graphstream.graph.Node;
 import org.graphstream.graph.implementations.DefaultGraph;
 import org.graphstream.stream.GraphParseException;
 import org.graphstream.stream.file.FileSourceDGS;
 
 public class WelshPowellTest {
 	//     B-(1)-C
 	//    /       \
 	//  (1)       (10)
 	//  /           \
 	// A             F
 	//  \           /
 	//  (1)       (1)
 	//    \       /
 	//     D-(1)-E
 	static String my_graph = 
 		"DGS004\n" 
 		+ "my 0 0\n" 
 		+ "an A \n" 
 		+ "an B \n"
 		+ "an C \n"
 		+ "an D \n"
 		+ "an E \n"
 		+ "an F \n"
 		+ "ae AB A B weight:1 \n"
 		+ "ae AD A D weight:1 \n"
 		+ "ae BC B C weight:1 \n"
 		+ "ae CF C F weight:10 \n"
 		+ "ae DE D E weight:1 \n"
 		+ "ae EF E F weight:1 \n"
 		;
 	public static void main(String[] args) throws IOException, ElementNotFoundException, GraphParseException {
 		Graph graph = new DefaultGraph("Welsh Powell Test");
 		StringReader reader  = new StringReader(my_graph);
 		
 		FileSourceDGS source = new FileSourceDGS();
 		source.addSink(graph);
 		source.readAll(reader);
 		
 		WelshPowell wp = new WelshPowell("color");
 		wp.init(graph);
 		wp.compute();
 		
 		System.out.println("The chromatic number of this graph is : "+wp.getChromaticNumber());
 		for(Node n : graph){
 			System.out.println("Node "+n.getId()+ " : color " +n.getAttribute("color"));
 		}
 		
 		 Color[] cols = new Color[wp.getChromaticNumber()];
 		 for(int i=0;i< wp.getChromaticNumber();i++){
 		 	cols[i]=Color.getHSBColor((float) (Math.random()), 0.8f, 0.9f);
 		 }
 		 for(Node n : graph){ 
 		 	int col = (int) n.getNumber("color");
 		 	n.addAttribute("ui.style", "fill-color:rgba("+cols[col].getRed()+","+cols[col].getGreen()+","+cols[col].getBlue()+",200);" );
 		 }
 		 
 		 graph.display();
 	}
 }