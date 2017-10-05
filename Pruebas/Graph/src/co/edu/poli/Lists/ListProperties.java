package co.edu.poli.Lists;

import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ListProperties extends JList<String> {
	public ListProperties() throws IOException {
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		DefaultListModel<String> model =  new ModelListAlgorithms();
		setModel(model);
	}
}
