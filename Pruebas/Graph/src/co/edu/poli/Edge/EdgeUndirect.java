package co.edu.poli.Edge;

import java.util.Random;

import co.edu.poli.Node.Node;
import co.edu.poli.Node.NodeUniq;

public class EdgeUndirect implements Edge {
	private long id;
	//	From NodeA to NodeB and From NodeB to NodeA
	private Node nodeA;
	private Node nodeB;
	private double weight;
	private String label;

	public EdgeUndirect() {
		Random r = new Random();
		this.id = r.nextLong();
		nodeA = new NodeUniq();
		nodeB = new NodeUniq();
		weight = 0.;
		label = "Empty";
	}

	public EdgeUndirect(long id) {
		this.id = id;
		nodeA = new NodeUniq();
		nodeB = new NodeUniq();
		weight = 0.;
		label = "Empty";
	}

	public EdgeUndirect(long id, Node nodeA, Node nodeB) {
		this.id = id;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		weight = 1.;
		label = "Empty";
	}

	public EdgeUndirect(long id, Node nodeA, Node nodeB, double weight) {
		this.id = id;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		this.weight = weight;
		label = "Empty";
	}

	public EdgeUndirect(long id, Node nodeA, Node nodeB, String label) {
		this.id = id;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		this.weight = 1.;
		this.label = label;
	}

	public EdgeUndirect(long id, Node nodeA, Node nodeB, double weight, String label) {
		this.id = id;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
		this.weight = weight;
		this.label = label;
	}

	// geters
	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public Node getNodeA() {
		return this.nodeA;
	}

	@Override
	public Node getNodeB() {
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
		this.id = id;
	}

	@Override
	public void setNodeA(Node nodeA) {
		this.nodeA = nodeA;
	}

	@Override
	public void setNodeB(Node nodeB) {
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
		return false;
	}

	@Override
	public String toString() {
		return String.format("ID : %d%nLabel : %s%nNode A :%n%s%nNode B :%n%s%nWeight : %.5f%n", this.id, this.label,
				this.nodeA.toString(), this.nodeB.toString(), this.weight);
	}

}