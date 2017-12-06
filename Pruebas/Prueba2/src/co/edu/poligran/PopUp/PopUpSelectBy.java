package co.edu.poligran.PopUp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import co.edu.poligran.Algorithms.SelectBy;

public class PopUpSelectBy implements ActionListener {
	private Graph graph;
	private DefaultComboBoxModel<String> comboBoxDefault;
	private JComboBox<String> comboBox;
	private JLabel selectLabel;
	private JButton color;
	private JPanel panel;
	private String select;
	private Icon icon;

	public PopUpSelectBy(Graph graph, String select) {
		this.graph = graph;
		this.select = select;
		icon = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
		initializer();
		addComponents();
		int resp = JOptionPane.showConfirmDialog(null, panel, "Select Range", JOptionPane.CANCEL_OPTION,
				JOptionPane.YES_NO_OPTION, icon);
		if(resp==0) {
			SelectBy sb = new SelectBy(graph, (String) comboBox.getSelectedItem(),color.getBackground(),select);
		}
	}

	private void addComponents() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(selectLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(comboBox, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(color, gbc);
	}

	private void initializer() {
		HashSet<String> set = new HashSet<>();
		for (Node n : graph) {
			if (n.hasAttribute("-attribute-" + select.toLowerCase())) {
				set.add(n.getAttribute("-attribute-" + select.toLowerCase()));
			}
		}
		String c[] = new String[set.size()];
		int i = 0;
		for (String s : set) {
			c[i++] = s;
		}
		panel = new JPanel(new GridBagLayout());
		comboBoxDefault = new DefaultComboBoxModel<>(c);
		comboBox = new JComboBox<>(comboBoxDefault);
		selectLabel = new JLabel("Select : ");
		color = new JButton("");
		color.setBackground(Color.YELLOW);
		color.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(color)) {
			JColorChooser ventanaDeColores = new JColorChooser();
			Color c = ventanaDeColores.showDialog(null, "Seleccione un Color", color.getBackground());
			color.setBackground(c);
		}
	}
}
