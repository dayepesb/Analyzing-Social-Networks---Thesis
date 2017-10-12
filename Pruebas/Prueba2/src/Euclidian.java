import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomEuclideanGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Euclidian {
	public static void main(String[] args) {
		Graph graph = new SingleGraph("random euclidean");
		Generator gen = new RandomEuclideanGenerator();
		gen.addSink(graph);
		gen.begin();
		for (int i = 0; i < 1000; i++) {
			gen.nextEvents();
		}
		gen.end();
		graph.display(false);
	}
}
