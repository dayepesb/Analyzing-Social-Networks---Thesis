package co.edu.poli.GraphPoli;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import co.edu.poli.Link.Link;
import co.edu.poli.Link.LinkDirect;
import co.edu.poli.Node.Vertex;
import co.edu.poli.Node.VertexUniq;

public class GraphPoliDirect implements GraphPoli {

	private long idGraphPoli;
	private String label;
	private ArrayList<Vertex> listVertex;
	private ArrayList<Link> listLinks;
	private HashMap<Long, ArrayList<Link>> graphPoli;
	private long nextIdVertex, nextIdLink;

	public GraphPoliDirect() {
		this.idGraphPoli = 1;
		this.label = "Empty";
		this.listVertex = new ArrayList<>();
		this.listLinks = new ArrayList<>();
		graphPoli = new HashMap<>();
		this.nextIdVertex = 0;
		this.nextIdLink = 0;
	}

	public GraphPoliDirect(long idGraph) {
		this.idGraphPoli = idGraph;
		this.label = "Empty";
		this.listVertex = new ArrayList<>();
		this.listLinks = new ArrayList<>();
		graphPoli = new HashMap<>();
		this.nextIdVertex = 0;
		this.nextIdLink = 0;
	}

	public GraphPoliDirect(String label) {
		this.idGraphPoli = 1;
		this.label = label;
		this.listVertex = new ArrayList<>();
		this.listLinks = new ArrayList<>();
		graphPoli = new HashMap<>();
		this.nextIdVertex = 0;
		this.nextIdLink = 0;
	}

	public GraphPoliDirect(long idGraph, String label) {
		this.idGraphPoli = idGraph;
		this.label = label;
		this.listVertex = new ArrayList<>();
		this.listLinks = new ArrayList<>();
		graphPoli = new HashMap<>();
		this.nextIdVertex = 0;
		this.nextIdLink = 0;
	}

	@Override
	public long getIdGraphPoli() {
		return idGraphPoli;
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
	public long getNextIdVertex() {
		nextIdVertex += 1;
		return (nextIdVertex - 1);
	}

	@Override
	public long getNextIdLink() {
		nextIdLink += 1;
		return (nextIdLink - 1);
	}

	@Override
	public int cardinalityVertex() {
		return listVertex.size();
	}

	@Override
	public int cardinalityLinks() {
		return listLinks.size();
	}

	@Override
	public ArrayList<Vertex> getListVertex() {
		return listVertex;
	}

	@Override
	public ArrayList<Link> getListLinks() {
		return listLinks;
	}

	@Override
	public HashMap<Long, ArrayList<Link>> getGraphPoli() {
		return graphPoli;
	}

	@Override
	public boolean addVertex() {
		try {
			Vertex n1 = new VertexUniq(this.getNextIdVertex());
			listVertex.add(n1);
			graphPoli.put(n1.getIdNode(), new ArrayList<>());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addVertex(Color color) {
		try {
			Vertex n1 = new VertexUniq(this.getNextIdVertex(), color);
			listVertex.add(n1);
			graphPoli.put(n1.getIdNode(), new ArrayList<>());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addVertex(String label) {
		try {
			Vertex n1 = new VertexUniq(this.getNextIdVertex(), label);
			listVertex.add(n1);
			graphPoli.put(n1.getIdNode(), new ArrayList<>());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addVertex(String label, Color color) {
		try {
			Vertex n1 = new VertexUniq(this.getNextIdVertex(), label, color);
			listVertex.add(n1);
			graphPoli.put(n1.getIdNode(), new ArrayList<>());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addVertex(Vertex nodeA, Vertex nodeB, boolean isDirect) {
		try {
			LinkDirect ed = new LinkDirect(this.getNextIdLink(), nodeA, nodeB);
			listLinks.add(ed);
			graphPoli.get(ed.getNodeA().getIdNode()).add(ed);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addLink(Vertex nodeA, Vertex nodeB, double weight, boolean isDirect) {
		try {
			LinkDirect ed = new LinkDirect(this.getNextIdLink(), nodeA, nodeB, weight);
			listLinks.add(ed);
			graphPoli.get(ed.getNodeA().getIdNode()).add(ed);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addLink(Vertex nodeA, Vertex nodeB, String label, boolean isDirect) {
		try {
			LinkDirect ed = new LinkDirect(this.getNextIdLink(), nodeA, nodeB, label);
			listLinks.add(ed);
			graphPoli.get(ed.getNodeA().getIdNode()).add(ed);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean addLink(Vertex nodeA, Vertex nodeB, double weight, String label, boolean isDirect) {
		try {
			LinkDirect ed = new LinkDirect(this.getNextIdLink(), nodeA, nodeB, weight, label);
			listLinks.add(ed);
			graphPoli.get(ed.getNodeA().getIdNode()).add(ed);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeVertex(long idNode) {
		boolean b = false;
		Vertex nu = null;
		for (int i = 0; i < listVertex.size(); i++) {
			if (listLinks.get(i).getId() == idNode) {
				nu = listVertex.remove(i);
				graphPoli.remove(i);
				b = true;
				break;
			}
		}
		if (!b)
			return false;

		for (Entry<Long, ArrayList<Link>> entry : graphPoli.entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				if (entry.getValue().get(i).getNodeB().getIdNode() == nu.getIdNode()) {
					graphPoli.get(entry.getKey()).remove(i);
					break;
				}
			}
		}

		return true;
	}

	@Override
	public boolean removeLink(long idEdge) {
		boolean b = false;
		Link ed = null;
		for (int i = 0; i < listLinks.size(); i++) {
			if (listLinks.get(i).getId() == idEdge) {
				b = true;
				ed = listLinks.remove(i);
				break;
			}
		}
		if (!b)
			return false;

		for (int i = 0; i < graphPoli.get(ed.getNodeA().getIdNode()).size(); i++) {
			if (graphPoli.get(ed.getNodeA().getIdNode()).get(i).getNodeB().getIdNode() == idEdge) {
				graphPoli.get(ed.getNodeA().getIdNode()).remove(i);
				break;
			}
		}

		return true;
	}

}