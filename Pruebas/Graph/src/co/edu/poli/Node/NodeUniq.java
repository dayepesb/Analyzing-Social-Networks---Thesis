package co.edu.poli.Node;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

public class NodeUniq implements Node {

	private long idNode;
	private HashMap<String, String> properties;
	private String label;
	private Color color;

	public NodeUniq() {
		Random r = new Random();
		this.idNode = r.nextLong();
		this.properties = new HashMap<>();
		this.label = "Empty";
		this.color = new Color(0, 0, 0);
	}

	public NodeUniq(long idNode) {
		this.idNode = idNode;
		properties = new HashMap<>();
		this.label = "Empty";
		this.color = new Color(0, 0, 0);
	}

	public NodeUniq(long idNode, String label) {
		this.idNode = idNode;
		properties = new HashMap<>();
		this.label = label;
		this.color = new Color(0, 0, 0);
	}

	public NodeUniq(long idNode, Color color) {
		this.idNode = idNode;
		properties = new HashMap<>();
		this.label = "Empty";
		this.color = color;
	}

	public NodeUniq(long idNode, String label, Color color) {
		this.idNode = idNode;
		this.label = label;
		this.color = color;
		properties = new HashMap<>();
	}

	@Override
	public long getIdNode() {
		return this.idNode;
	}

	@Override
	public HashMap<String, String> getProperties() {
		return this.properties;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setIdNode(long idNode) {
		this.idNode = idNode;
	}

	@Override
	public void setProperties(HashMap<String, String> properties) {
		this.properties = properties;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void addPropertie(String key, String value) {
		this.properties.put(key, value);
	}

	@Override
	public String toString() {
		return String.format("	ID : %d%n	Label : %s%n	Properties : %s%n	Color : %s%n", this.idNode, this.label,
				this.properties.toString(), this.color.toString());
	}
}
