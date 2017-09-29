package co.edu.poli.Algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

import co.edu.poli.Edge.Edge;
import co.edu.poli.Edge.EdgeDirect;
import co.edu.poli.Graph.Graph;
import co.edu.poli.Graph.GraphDirect;
import co.edu.poli.Graph.GraphMixed;
import co.edu.poli.Graph.GraphUndirect;
import co.edu.poli.Node.Node;

public class EdmondsKarp {
	double flow;
	Graph graph;
	HashMap<Long, Long>pi;
	
	public EdmondsKarp(GraphDirect graph) {
		this.graph=graph;
	}
	
	public EdmondsKarp(GraphUndirect graph) {
		this.graph=graph;
	}
	
	public EdmondsKarp(GraphMixed graph) {
		this.graph=graph;
	}
	
	double flow(long s,long t){
		double flow=0;
		HashMap<Long, ArrayList<Edge>>residual=graph.getGraph();
		pi=new HashMap<>();
		while(bfs(s, t)){
			double minFlow=Double.MAX_VALUE;
			for(long v=t;v!=s;v=pi.get(v)){
				long u=pi.get(v);
				double weight=0;
				for(Edge e:residual.get(u)){
					if(e.getNodeB().getIdNode()==v){
						weight=e.getWeight();
						break;
					}
				}
				minFlow=Math.min(minFlow, weight);
			}
			
			for(long v=t;v!=s;v=pi.get(v)){
				long u=pi.get(v);
				double weight=0;
				for(Edge e:residual.get(u)){
					if(e.getNodeB().getIdNode()==v){
						e.setWeight(e.getWeight()-minFlow);
					}
				}
				boolean found=false;
				for(Edge e:residual.get(v)){
					if(e.getNodeB().getIdNode()==u){
						e.setWeight(e.getWeight()+minFlow);
						found=true;
						break;
					}
				}
				
				Node uT=null;
				Node vT=null;
				int cont=0;
				for (Node node:graph.getListNodes()) {
					if(node.getIdNode()==u){
						uT=node;
						cont++;
					}
					if(node.getIdNode()==v){
						vT=node;
						cont++;
					}
					if(cont==2)break;
				}
				
				if(!found){
					residual.get(v).add(new EdgeDirect(graph.getNextIdEdge(), vT,uT, minFlow));
				}
				
				minFlow=Math.min(minFlow, weight);
			}
			flow+=minFlow;
		}
		return flow;
	}


	
	boolean bfs(long s, long t){
		ArrayDeque<Long>q=new ArrayDeque<>();
		q.offer(s);
		pi=new HashMap<>();
		pi.put(s, -1L);
		while(!q.isEmpty()){
			long u=q.poll();
			for (Edge e:graph.getGraph().get(u)) {
				long v=e.getNodeB().getIdNode();
				if(!pi.containsKey(v)){
					q.offer(v);
					pi.put(v, u);
				}
			}
		}
		if(pi.containsKey(t)){
			return true;
		}
		return false;
	}
	
}