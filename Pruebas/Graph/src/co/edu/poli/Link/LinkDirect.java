package co.edu.poli.Link;

import java.util.Random;

import co.edu.poli.Vertex.Vertex;
import co.edu.poli.Vertex.VertexUniq;

public class LinkDirect implements Link {
	private long idLink;
	// From NodeA to NodeB
	private Vertex nodeA;
	private Vertex nodeB;
	private double weight;
	private String label;

	public LinkDirect() {
		Random r = new Random();
		this.idLink = r.nextLong();
		nodeA = new VertexUniq();
		nodeB = new VertexUniq();
		weight = 0.;
		label = "Empty";
	}

	public LinkDirect(long idEdge) {
		this.idLink = idEdge;
		nodeA = new VertexUniq();
		nodeB = new VertexUniq();
		weight = 1.;
		label = "Empty";
	}

	public LinkDirect(long idEdge, Vertex nodeA, Vertex nodeB) {
		this.idLink = idEdge;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		weight = 1.;
		label = "Empty";
	}

	public LinkDirect(long idEdge, Vertex nodeA, Vertex nodeB, double weight) {
		this.idLink = idEdge;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		this.weight = weight;
		label = "Empty";
	}

	public LinkDirect(long idEdge, Vertex nodeA, Vertex nodeB, String label) {
		this.idLink = idEdge;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		this.weight = 1.;
		this.label = label;
	}

	public LinkDirect(long idEdge, Vertex nodeA, Vertex nodeB, double weight, String label) {
		this.idLink = idEdge;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		this.weight = weight;
		this.label = label;
	}

	// geters
	@Override
	public long getId() {
		return this.idLink;
	}

	@Override
	public Vertex getNodeA() {
		return this.nodeA;
	}

	@Override
	public Vertex getNodeB() {
		return this.nodeB;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public String getLabel() {
		return this.getLabel();
	}

	// setters
	@Override
	public void setId(long id) {
		this.idLink = id;
	}

	@Override
	public void setNodeA(Vertex nodeA) {
		this.nodeA = nodeA;
	}

	@Override
	public void setNodeB(Vertex nodeB) {
		this.nodeB = nodeB;
	}

	@Override
	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public boolean isDirect() {
		return true;
	}

	@Override
	public String toString() {
		return String.format("ID : %d%nLabel : %s%nNode A :%n%s%nNode B :%n%s%nWeight : %.5f%n", this.idLink,
				this.label, this.nodeA.toString(), this.nodeB.toString(), this.weight);
	}

}
