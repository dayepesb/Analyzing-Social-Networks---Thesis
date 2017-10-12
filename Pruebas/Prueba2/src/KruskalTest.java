import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;

import org.graphstream.algorithm.Kruskal;
import org.graphstream.algorithm.generator.DorogovtsevMendesGenerator;

public class KruskalTest{
	
	public static void main(String ... args) {
		//System.out.println("asdsad");
		DorogovtsevMendesGenerator gen = new DorogovtsevMendesGenerator();
		Graph graph = new DefaultGraph("Kruskal Test");

	  	String css = "edge .notintree {size:1px;fill-color:gray;} " +
				 "edge .intree {size:3px;fill-color:black;}";

	  	graph.addAttribute("ui.stylesheet", css);
	 	
	 	
	 	
	 
	 	gen.addEdgeAttribute("weight");
	  	gen.setEdgeAttributesRange(1, 100);
	  	gen.addSink(graph);
	 	gen.begin();
	 	graph.display(false);
	 	for (int i = 0; i < 100 && gen.nextEvents(); i++);
	 	
	 	
	 	
	 	for (int i = 0; i < 100; i++) {
			graph.getNode(i).addAttribute("xyz",Math.random()*800,Math.random()*800 ,Math.random()*600);
			try{
				Thread.sleep(250);
			}catch (Exception e) {
			}
		}
	 	gen.end();
	 	
	 	

	 	Kruskal kruskal = new Kruskal("ui.class", "intree", "notintree");
	 
	 	kruskal.init(graph);
	 	kruskal.compute();
	}
}