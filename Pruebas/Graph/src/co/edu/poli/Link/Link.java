package co.edu.poli.Link;

import co.edu.poli.Vertex.Vertex;

public interface Link {

	public abstract Vertex getNodeA();

	public abstract Vertex getNodeB();

	public abstract double getWeight();

	public abstract String getLabel();

	public abstract long getId();

	public abstract void setNodeA(Vertex nodeA);

	public abstract void setNodeB(Vertex nodeB);

	public abstract void setWeight(double weigth);

	public abstract void setLabel(String label);

	public abstract void setId(long id);

	public abstract boolean isDirect();

}
