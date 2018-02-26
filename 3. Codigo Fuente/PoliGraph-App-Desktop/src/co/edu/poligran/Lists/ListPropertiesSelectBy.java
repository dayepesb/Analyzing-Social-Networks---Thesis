package co.edu.poligran.Lists;

import java.io.IOException; 

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import org.graphstream.graph.Graph;

public class ListPropertiesSelectBy extends JList<String> {
	
	public ListPropertiesSelectBy(Graph graph) throws IOException {
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		DefaultListModel<String> model =  new ModelListSelectBy(graph);
		setModel(model);
	}
}
