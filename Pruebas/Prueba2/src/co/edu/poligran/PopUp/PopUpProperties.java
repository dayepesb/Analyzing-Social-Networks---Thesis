package co.edu.poligran.PopUp;

import java.io.IOException;

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
		Object[] propeties = new Object[mlsb.getSize()];
		for (int i = 0; i < propeties.length; i++) {
			propeties[i] = mlsb.get(i);
		}
		JComboBox<Object> properties = new JComboBox<>(propeties);
		JTextField value = new JTextField(20);
		JPanel panel = new JPanel();
		panel.add(properties);
		panel.add(value);
		Icon icono = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
		resp = JOptionPane.showConfirmDialog(null, panel, "Add Node", JOptionPane.CANCEL_OPTION,
				JOptionPane.YES_NO_OPTION, icono);
		if (resp == 0) {
			return new String[] {properties.getSelectedItem().toString().trim().toLowerCase(),value.getText().trim().toLowerCase()};
		}
		return null;
	}

}
