package co.edu.poligran.Lists;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;


public class ModelListSelectBy extends DefaultListModel<String> {
	private static String line;

	public ModelListSelectBy(Graph graph) throws IOException {
		Set<String> set = new HashSet<>();
		for (Node n: graph) {
			for (String s : n.getAttributeKeySet()) {
				if(s.startsWith("-attribute-"))
				set.add(s.substring(11));
			}
		}
		for (String string : set) {
			this.addElement(string.substring(0,1).toUpperCase()+string.substring(1).toLowerCase());
		}
	}
}
