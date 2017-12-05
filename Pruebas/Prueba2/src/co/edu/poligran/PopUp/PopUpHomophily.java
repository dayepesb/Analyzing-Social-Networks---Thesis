package co.edu.poligran.PopUp;

import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class PopUpHomophily {
	private JComboBox<String> comboBox;
	private DefaultComboBoxModel<String> modelComboBox;
	private int resp;
	private Icon icon;
	private int min, max;

	public PopUpHomophily(Graph graph) {
		JPanel panel = new JPanel();
		icon = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
		Set<String> set = new HashSet<>();
		for (Node n : graph) {
			for (String s : n.getAttributeKeySet()) {
				if (s.startsWith("-attribute-")) {
					set.add(s.substring(11).toLowerCase());
				}
			}
		}
		String c[] = new String[set.size()];
		int i = 0;
		for (String s : set) {
			c[i++] = s;
		}
		modelComboBox = new DefaultComboBoxModel<>(c);
		comboBox = new JComboBox<>(modelComboBox);
		panel.add(comboBox);
		resp = JOptionPane.showConfirmDialog(null, panel, "Select Atttribute", JOptionPane.CANCEL_OPTION,
				JOptionPane.YES_NO_OPTION, icon);
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		if (resp == 0) {
			String att = (String) comboBox.getSelectedItem();
			if (isNumber(graph, att)) {
				PopUpNumber poPupNumber = new PopUpNumber(graph,att, min, max);
			} else {
				PopUpText puPopUpText = new PopUpText(graph, att);
			}
		}
	}

	private boolean isNumber(Graph graph, String att) {
		boolean b = true;
		for (Node n : graph) {
			String value = n.getAttribute("-attribute-" + att);
			try {
				int a = Integer.parseInt(value);
				min = Math.min(a, min);
				max = Math.max(a, max);
			} catch (Exception e) {
				b = false;
				break;
			}
		}
		return b;
	}
}
