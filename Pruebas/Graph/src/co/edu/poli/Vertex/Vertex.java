package co.edu.poli.Vertex;

import java.awt.Color;
import java.util.HashMap;

public interface Vertex {

	
	// getters
	public abstract long getIdNode();

	public abstract HashMap<String, String> getProperties();

	public abstract String getLabel();

	public abstract Color getColor();

	// setters
	public abstract void setIdNode(long idNode);

	public abstract void setProperties(HashMap<String, String> properties);

	public abstract void setLabel(String label);

	public abstract void setColor(Color color);
	
	public abstract void addPropertie(String key,String value);

}
