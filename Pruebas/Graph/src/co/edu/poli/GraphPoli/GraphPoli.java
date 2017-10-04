package co.edu.poli.GraphPoli;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import co.edu.poli.Link.Link;
import co.edu.poli.Node.Vertex;

public interface GraphPoli {

	public abstract long getIdGraphPoli();

	public abstract String getLabel();

	public abstract void setLabel(String label);

	public abstract long getNextIdVertex();

	public abstract long getNextIdLink();

	public abstract int cardinalityVertex();

	public abstract int cardinalityLinks();

	public abstract ArrayList<Vertex> getListVertex();

	public abstract ArrayList<Link> getListLinks();

	public abstract HashMap<Long, ArrayList<Link>> getGraphPoli();

	public abstract boolean addVertex();

	public abstract boolean addVertex(Color color);

	public abstract boolean addVertex(String label);

	public abstract boolean addVertex(String label, Color color);

	public abstract boolean addVertex(Vertex nodeA, Vertex nodeB, boolean isDirect);

	public abstract boolean addLink(Vertex nodeA, Vertex nodeB, double weight, boolean isDirect);

	public abstract boolean addLink(Vertex nodeA, Vertex nodeB, String label, boolean isDirect);

	public abstract boolean addLink(Vertex nodeA, Vertex nodeB, double weight, String label, boolean isDirect);

	public abstract boolean removeVertex(long idNode);

	public abstract boolean removeLink(long idEdge);

}
