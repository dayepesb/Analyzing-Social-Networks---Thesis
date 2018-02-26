package co.edu.poligran.PopUp;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.graphstream.graph.Graph;

import co.edu.poligran.Lists.ModelListSelectNode;

public class PopUpDijkstra {
	private int resp;

	public String[] PopUpDijkstra(String url, String title, int optionCancel, int optionYes, Graph graph)
			throws IOException {
		ModelListSelectNode mlsb = new ModelListSelectNode(graph);
		HashMap<String,String> tranlsate = mlsb.getTranslate();
		Object[] propeties = new Object[mlsb.getSize()];
		for (int i = 0; i < propeties.length; i++) {
			propeties[i] = mlsb.get(i);
		}
		JComboBox<Object> propertiesFrom = new JComboBox<>(propeties);
		JComboBox<Object> propertiesTo = new JComboBox<>(propeties);
		JLabel from=new JLabel("From :");
		JLabel to=new JLabel("To :");
		//JTextField value = new JTextField(20);
		JPanel panel = new JPanel();
		panel.add(from);
		panel.add(propertiesFrom);
		panel.add(to);
		panel.add(propertiesTo);
		Icon icono = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
		resp = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.CANCEL_OPTION, JOptionPane.YES_NO_OPTION,
				icono);
		if (resp == 0) {
			return new String[] { tranlsate.get(propertiesFrom.getSelectedItem().toString()),
					tranlsate.get(propertiesTo.getSelectedItem().toString())};
		}
		return null;
	}
}
