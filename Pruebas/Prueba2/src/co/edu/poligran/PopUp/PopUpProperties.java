package co.edu.poligran.PopUp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.graphstream.graph.Graph;

import co.edu.poligran.Lists.ModelListSelectBy;

public class PopUpProperties extends JOptionPane {
	private int resp;

	public String[] PopUpProperties(String url, String title, int optionCancel, int optionYes, Graph graph)
			throws IOException {
		ModelListSelectBy mlsb = new ModelListSelectBy(graph);
		Set<String> set = new HashSet<>();
		for (int i = 0; i < mlsb.size(); i++) {
			set.add(mlsb.get(i).toLowerCase());
		}
		BufferedReader in = new BufferedReader(new FileReader(new File("src/co/edu/poligran/Files/SelectBy.txt")));
		for (String line; (line = in.readLine()) != null;)
			set.add(line.trim().toLowerCase());
		Object[] propeties = new Object[set.size()];
		int i = 0;
		for (String string : set) {
			propeties[i++] = string;
		}
		JComboBox<Object> properties = new JComboBox<>(propeties);
		JTextField value = new JTextField(20);
		JPanel panel = new JPanel();
		panel.add(properties);
		panel.add(value);
		Icon icono = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
		resp = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.CANCEL_OPTION, JOptionPane.YES_NO_OPTION,
				icono);
		if (resp == 0) {
			return new String[] { properties.getSelectedItem().toString().trim().toLowerCase(),
					value.getText().trim().toLowerCase() };
		}
		return null;
	}

}
