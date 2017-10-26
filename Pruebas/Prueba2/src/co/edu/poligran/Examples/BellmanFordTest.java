package co.edu.poligran.Examples;

import java.io.IOException;
import java.io.StringReader;

import org.graphstream.algorithm.BellmanFord;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.DefaultGraph;
import org.graphstream.stream.file.FileSourceDGS;

public class BellmanFordTest {

	// B-(1)-C
	// / \
	// (1) (10)
	// / \
	// A F
	// \ /
	// (1) (1)
	// \ /
	// D-(1)-E
	static String my_graph = "DGS004\n" + "my 0 0\n" + "an A \n" + "an B \n" + "an C \n" + "an D \n" + "an E \n"
			+ "an F \n" + "ae AB A B weight:1.0 \n" + "ae AD A D weight:1.0 \n" + "ae BC B C weight:1.0 \n"
			+ "ae CF C F weight:10.0 \n" + "ae DE D E weight:1.0 \n" + "ae EF E F weight:1.0 \n";

	public static void main(String[] args) throws IOException {
		Graph graph = new DefaultGraph("Bellman-Ford Test");
		StringReader reader = new StringReader(my_graph);

		FileSourceDGS source = new FileSourceDGS();
		source.addSink(graph);
		source.readAll(reader);

		BellmanFord bf = new BellmanFord("weight", "A");
		bf.init(graph);
		bf.compute();

		graph.display();

		System.out.println(bf.getShortestPath(graph.getNode("F")));
	}
}