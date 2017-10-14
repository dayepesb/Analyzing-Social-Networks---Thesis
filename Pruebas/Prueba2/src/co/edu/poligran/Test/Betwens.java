package co.edu.poligran.Test;
import org.graphstream.algorithm.BetweennessCentrality;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.algorithm.Toolkit;

public class Betwens {
	public static void main(String[] args) {
		Graph graph = new SingleGraph("Betweenness Test");
		

//	      E----D  AB=1, BC=5, CD=3, DE=2, BE=6, EA=4  
	//   /|    |  Cb(A)=4
	//  / |    |  Cb(B)=2
	// A  |    |  Cb(C)=0
	//  \ |    |  Cb(D)=2
	//   \|    |  Cb(E)=4
//	      B----C

		Node A = graph.addNode("A");
		Node B = graph.addNode("B");
		Node E = graph.addNode("E");
		Node C = graph.addNode("C");
		Node D = graph.addNode("D");
		Node F = graph.addNode("F");
		
		//A.addAttribute("ui.style", "");
		

		graph.addEdge("AB", "A", "B");
		//graph.addEdge("BE", "B", "E");
		graph.addEdge("BC", "B", "C");
		//graph.addEdge("ED", "E", "D");
		graph.addEdge("CD", "C", "D");
		graph.addEdge("AE", "A", "E");
		//graph.addEdge("EC", "E", "C");
		//graph.addEdge("BD", "B", "D");
		graph.addEdge("EF", "E", "F");
		
	
		
		
		BetweennessCentrality bcb = new BetweennessCentrality();
//		bcb.setWeightAttributeName("weight");
//		bcb.setWeight(A, B, 100);
//		bcb.setWeight(B, E, 1);
//		bcb.setWeight(B, C,100);
//		bcb.setWeight(E, D, 1);
//		bcb.setWeight(C, D, 1);
//		bcb.setWeight(A, E, 1);
//		bcb.setWeight(E, C, 1);
//		bcb.setWeight(B, D, 1);
//		
//		
//		bcb.setWeight(A, B, 1);
//		bcb.setWeight(B, E, 6);
//		bcb.setWeight(B, C, 5);
//		bcb.setWeight(E, D, 2);
//		bcb.setWeight(C, D, 3);
//		bcb.setWeight(A, E, 4);
		
		bcb.init(graph);
		bcb.compute();

		System.out.println("A=" + A.getAttribute("Cb"));
		System.out.println("B=" + B.getAttribute("Cb"));
		System.out.println("C=" + C.getAttribute("Cb"));
		System.out.println("D=" + D.getAttribute("Cb"));
		System.out.println("E=" + E.getAttribute("Cb"));
		
		for (Node n: graph.getNodeSet()) {
			n.addAttribute("label", n.getId());
			n.addAttribute("ui.style", "size: "+((((double)n.getAttribute("Cb"))*2.5)+10)+"px;");
		}
		
		
		
		graph.display();
	}
}
