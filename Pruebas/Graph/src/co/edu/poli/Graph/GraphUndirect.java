package co.edu.poli.Graph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import co.edu.poli.Edge.Edge;
import co.edu.poli.Edge.EdgeDirect;
import co.edu.poli.Node.Node;
import co.edu.poli.Node.NodeUniq;

public class GraphUndirect implements Graph {
	private long idGraph;
	private String label;
	private ArrayList<Node> listNodes;
	private ArrayList<Edge> listEdges;
	private HashMap<Long, ArrayList<Edge>> graph;

	public GraphUndirect() {
		this.idGraph = 1;
		this.label = "Empty";
		this.listNodes = new ArrayList<>();
		this.listEdges = new ArrayList<>();
		graph = new HashMap<>();
	}

	public GraphUndirect(long idGraph) {
		this.idGraph = idGraph;
		this.label = "Empty";
		this.listNodes = new ArrayList<>();
		this.listEdges = new ArrayList<>();
		graph = new HashMap<>();
	}

	public GraphUndirect(String label) {
		this.idGraph = 1;
		this.label = label;
		this.listNodes = new ArrayList<>();
		this.listEdges = new ArrayList<>();
		graph = new HashMap<>();
	}

	public GraphUndirect(long idGraph, String label) {
		this.idGraph = idGraph;
		this.label = label;
		this.listNodes = new ArrayList<>();
		this.listEdges = new ArrayList<>();
		graph = new HashMap<>();
	}

	@Override
	public long getIdGraph() {
		return idGraph;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int cardinalityNodes() {
		return listNodes.size();
	}

	@Override
	public int cardinalityEdges() {
		return listEdges.size();
	}

	@Override
	public ArrayList<Node> getListNodes() {
		return listNodes;
	}

	@Override
	public ArrayList<Edge> getListEdges() {
		return listEdges;
	}

	@Override
	public HashMap<Long, ArrayList<Edge>> getGraph() {
		return graph;
	}

	@Override
	public void addNode(long idNode) {
		Node n1 = new NodeUniq(idNode);
		listNodes.add(n1);
		graph.put(idNode, new ArrayList<>());
	}

	@Override
	public void addNode(long idNode, Color color) {
		Node n1 = new NodeUniq(idNode, color);
		listNodes.add(n1);
		graph.put(idNode, new ArrayList<>());
	}

	@Override
	public void addNode(long idNode, String label) {
		Node n1 = new NodeUniq(idNode, label);
		listNodes.add(n1);
		graph.put(idNode, new ArrayList<>());
	}

	@Override
	public void addNode(long idNode, String label, Color color) {
		Node n1 = new NodeUniq(idNode, label, color);
		listNodes.add(n1);
		graph.put(idNode, new ArrayList<>());
	}

	@Override
	public void addEdge(long idEdge, Node nodeA, Node nodeB, boolean isDirect) {
		EdgeDirect ed = new EdgeDirect(idEdge, nodeA, nodeB);
		listEdges.add(ed);
		graph.get(ed.getNodeA().getIdNode()).add(ed);
	}

	@Override
	public void addEdge(long idEdge, Node nodeA, Node nodeB, double weight, boolean isDirect) {
		EdgeDirect ed = new EdgeDirect(idEdge, nodeA, nodeB, weight);
		listEdges.add(ed);
		graph.get(ed.getNodeA().getIdNode()).add(ed);
	}

	@Override
	public void addEdge(long idEdge, Node nodeA, Node nodeB, String label, boolean isDirect) {
		EdgeDirect ed = new EdgeDirect(idEdge, nodeA, nodeB, label);
		listEdges.add(ed);
		graph.get(ed.getNodeA().getIdNode()).add(ed);

	}

	@Override
	public void addEdge(long idEdge, Node nodeA, Node nodeB, double weight, String label, boolean isDirect) {
		EdgeDirect ed = new EdgeDirect(idEdge, nodeA, nodeB, weight, label);
		listEdges.add(ed);
		graph.get(ed.getNodeA().getIdNode()).add(ed);
	}

}
