package co.edu.poligran.PopUp;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import co.edu.poligran.Lists.ModelListSelectNode;

public class PopUpPropertiesNode {
	private int resp;

	public String[] PopUpNode(String url, String title, int optionCancel, int optionYes, Graph graph, String id)
			throws IOException {
		Node A = graph.getNode(id);
		ModelListSelectNode mlsb = new ModelListSelectNode(graph);
		HashMap<String, String> tranlsate = mlsb.getTranslate();
		Object[] propeties = new Object[mlsb.getSize()];
		for (int i = 0; i < propeties.length; i++) {
			propeties[i] = mlsb.get(i);
		}
		JPanel panel = new JPanel();
		int n = A.getAttributeCount();
		GridLayout grid = new GridLayout(n, 0);
		panel.setLayout(grid);
		HashMap<String, String> dic = new HashMap<String, String>();
		JTextField[]atribute=new JTextField[n];
		int index=0;
		for (String s : A.getEachAttributeKey()) {

			JPanel tempPanel = new JPanel();
			atribute[index] = new JTextField(20);
			String text = A.getAttribute(s) + "";
			atribute[index].setText(text);

			if (s.startsWith("-attribute-")) {
				dic.put(s, s.substring(11));
				s = s.substring(11);
			} else {
				dic.put(s, s);
			}
			JLabel label = new JLabel(s);
			tempPanel.add(label);
			tempPanel.add(atribute[index]);
			panel.add(tempPanel);
		}
		Icon icono = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
		resp = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.CANCEL_OPTION, JOptionPane.YES_NO_OPTION,
				icono);
		if (resp == 0) {
			for (String s : A.getEachAttributeKey()) {
				
			}
		}
		return null;
	}

}
