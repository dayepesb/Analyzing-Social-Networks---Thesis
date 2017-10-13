package co.edu.poligran.Test;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;

import org.graphstream.algorithm.Prim;
import org.graphstream.algorithm.generator.DorogovtsevMendesGenerator;

public class PrimTest {

	public static void main(String... args) {
		DorogovtsevMendesGenerator gen = new DorogovtsevMendesGenerator();
		Graph graph = new DefaultGraph("Prim Test");

		String css = "edge .notintree {size:1px;fill-color:gray;} " + "edge .intree {size:3px;fill-color:black;}";

		graph.addAttribute("ui.stylesheet", css);
		graph.display();

		gen.addEdgeAttribute("weight");
		gen.setEdgeAttributesRange(1, 100);
		gen.addSink(graph);
		gen.begin();
		for (int i = 0; i < 100 && gen.nextEvents(); i++);
		gen.end();

		Prim prim = new Prim("ui.class", "intree", "notintree");

		prim.init(graph);
		prim.compute();
	}
}