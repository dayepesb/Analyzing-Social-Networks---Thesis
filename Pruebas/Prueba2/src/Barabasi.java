import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.NodeFactory;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;

public class Barabasi {
	public static void main(String[] args) {
		Graph graph = new SingleGraph("Barabàsi-Albert");
		// Between 1 and 3 new links per node added.
		Generator gen = new BarabasiAlbertGenerator(3);
		// Generate 100 nodes:
		gen.addSink(graph); 
		gen.begin();

		for(int i=0; i<100; i++) {
			gen.nextEvents();
		}
		
		Graph t= new MultiGraph("sdsd");
			
		gen.end();
		graph.display();
	}
}
