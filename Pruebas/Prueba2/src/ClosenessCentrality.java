import java.util.HashMap;
import java.util.Map.Entry;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class ClosenessCentrality {
	
	Graph g;
	HashMap<Integer,Double>remoteness;
	HashMap<Integer,Double>closeness;
	
	public ClosenessCentrality(Graph g) {
		this.g=g;
		remoteness=remoteness();
		closeness=closeness();
	}
	
	
	public static void main(String[] args) {
		Graph graph=new MultiGraph("Test");
		Node A = graph.addNode("A");
		Node B = graph.addNode("B");
		Node C = graph.addNode("C");
		//Node D = graph.addNode("D");
		//Node E = graph.addNode("E");
		//Node F = graph.addNode("F");
		
		graph.addEdge("AB", "A", "B").addAttribute("length", 14.0);
		graph.addEdge("AC", "A", "C").addAttribute("length", 9.0);
		//graph.addEdge("AD", "A", "D").addAttribute("length", 7.0);
		graph.addEdge("BC", "B", "C").addAttribute("length", 2.0);
		//graph.addEdge("CD", "C", "D").addAttribute("length", 10.5);
		//graph.addEdge("BE", "B", "E").addAttribute("length", 9.0);
		//graph.addEdge("CF", "C", "F").addAttribute("length", 11.9999);
		//graph.addEdge("DF", "D", "F").addAttribute("length", 15.9);
		//graph.addEdge("EF", "E", "F").addAttribute("length", 6.3);
		
		ClosenessCentrality c=new ClosenessCentrality(graph);
		
		for (Node n : graph)
			n.addAttribute("label", n.getId());
		for (Edge e : graph.getEachEdge())
			e.addAttribute("label", "" + (double) e.getNumber("length"));
		
		graph.display();
		
		for(Entry<Integer,Double>dist:c.closeness.entrySet()){
			System.out.println("La cercania del nodo"+dist.getKey()+" es "+dist.getValue());
		}
		for(Entry<Integer,Double>dist:c.remoteness.entrySet()){
			System.out.println("La lejania del nodo" +dist.getKey()+" es "+dist.getValue());
		}
		
		
	}
	
	public HashMap<Integer,Double>closeness(){
		Double total=0d;
		HashMap<Integer, Double>closeness=new HashMap<>();
		remoteness=remoteness();
		for(Entry<Integer,Double>dist:remoteness.entrySet()){
			total+=dist.getValue();
		}
		for(Node n:g){
			closeness.put(n.getIndex(), closeness(total,n.getIndex() ));
		}
		return closeness;
		
	}
	
	
	
	public double closeness(double total,int node){
		return total/remoteness.get(node);
	}
	
	
	public HashMap<Integer,Double> remoteness(){
		HashMap<Integer, Double>remoteness=new HashMap<>();
		for(Node n:g){
			int id=n.getIndex();
			double remotenessNode=remoteness(id);
			remoteness.put(id, remotenessNode);
		}
		return remoteness;
	}
	
	
	public double remoteness(int node){
		Dijkstra dijkstra=new Dijkstra(Dijkstra.Element.EDGE,"result","length");
		dijkstra.init(g);
		dijkstra.setSource(g.getNode(node));
		dijkstra.compute();
		double distTotal=0;
		for(Node n:g){
			distTotal+=dijkstra.getPathLength(n);
		}
		return distTotal;
	}
	
}
