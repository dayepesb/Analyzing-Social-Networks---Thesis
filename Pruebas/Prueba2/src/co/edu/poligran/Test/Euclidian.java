package co.edu.poligran.Test;
import org.graphstream.algorithm.generator.ChvatalGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;

public class Euclidian {
	public static void main(String[] args) {
		Graph graph = new SingleGraph("random euclidean");
		Generator gen = new ChvatalGenerator();
		gen.addSink(graph);
		gen.begin();
		for (int i = 0; i < 35; i++) {
			gen.nextEvents();
		}
		int n=graph.getNodeCount();
		
		
		//byte adjacentMatrix[][]=new byte[n][n];
		Graph mat=new MultiGraph("Adj");
		int countNodes=0;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(graph.getNode(i).hasEdgeBetween(j)){
					mat.addNode(countNodes+"").addAttribute("xy", i,j);;
					countNodes++;
				}
			}
		}
		
		
		
		
		gen.end();
		graph.display();
		mat.display(false);
	}
}
