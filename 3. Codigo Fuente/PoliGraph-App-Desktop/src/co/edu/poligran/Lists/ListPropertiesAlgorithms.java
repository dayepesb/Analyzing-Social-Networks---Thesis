package co.edu.poligran.Lists;

import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ListPropertiesAlgorithms extends JList<String> {
	public ListPropertiesAlgorithms() throws IOException {
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		DefaultListModel<String> model =  new ModelListAlgorithms();
		setModel(model);
	}
}
