package co.edu.poli.Algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;

import co.edu.poli.GraphPoli.GraphPoli;
import co.edu.poli.GraphPoli.GraphPoliDirect;
import co.edu.poli.GraphPoli.GraphPoliMixed;
import co.edu.poli.GraphPoli.GraphPoliUndirect;
import co.edu.poli.Link.Link;
import co.edu.poli.Link.LinkDirect;
import co.edu.poli.Node.Vertex;

public class EdmondsKarp {
	double flow;
	GraphPoli graph;
	HashMap<Long, Long>pi;
	
	public EdmondsKarp(GraphPoliDirect graph) {
		this.graph=graph;
	}
	
	public EdmondsKarp(GraphPoliUndirect graph) {
		this.graph=graph;
	}
	
	public EdmondsKarp(GraphPoliMixed graph) {
		this.graph=graph;
	}
	
	double flow(long s,long t){
		double flow=0;
		HashMap<Long, ArrayList<Link>>residual=graph.getGraphPoli();
		pi=new HashMap<>();
		while(bfs(s, t)){
			double minFlow=Double.MAX_VALUE;
			for(long v=t;v!=s;v=pi.get(v)){
				long u=pi.get(v);
				double weight=0;
				for(Link e:residual.get(u)){
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
				for(Link e:residual.get(u)){
					if(e.getNodeB().getIdNode()==v){
						e.setWeight(e.getWeight()-minFlow);
					}
				}
				boolean found=false;
				for(Link e:residual.get(v)){
					if(e.getNodeB().getIdNode()==u){
						e.setWeight(e.getWeight()+minFlow);
						found=true;
						break;
					}
				}
				
				Vertex uT=null;
				Vertex vT=null;
				int cont=0;
				for (Vertex node:graph.getListVertex()) {
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
					residual.get(v).add(new LinkDirect(graph.getNextIdLink(), vT,uT, minFlow));
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
			for (Link e:graph.getGraphPoli().get(u)) {
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