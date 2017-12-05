package co.edu.poligran.PopUp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import co.edu.poligran.Algorithms.HomofilyPoli;

public class PopUpText implements ActionListener {
	private Icon icon;
	private int resp;
	private JButton buttonLeft, buttonRigth, buttonColorA, buttonColorB;
	private JScrollPane leftScroll, rigthScroll;
	private JList<String> listLeft, listRigth;
	private DefaultListModel<String> listModelLeft, listModelRigth;

	private Set<String> properties, propertiesLeft, propertiesRigth;

	public PopUpText(Graph graph, String att) {
		Set<String> diff = new HashSet<>();
		for (Node n : graph) {
			for (String s : n.getAttributeKeySet()) {
				if (s.equals("-attribute-" + att)) {
					diff.add(n.getAttribute(s));
				}
			}
		}
		String[] c = new String[diff.size()];
		int i = 0;
		for (String s : diff) {
			c[i++] = s;
		}
		initSetProperties(c);
		initSetPropertiesLeft();
		initButtons();
		initSetPropertiesRigth();
		resizedCompoents();
		icon = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		panel.add(leftScroll, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(buttonColorA, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(buttonLeft, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(buttonRigth, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		rigthScroll.setSize(200, 200);
		panel.add(rigthScroll, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(buttonColorB, gbc);
		resp = JOptionPane.showConfirmDialog(null, panel, "Select Atttribute", JOptionPane.CANCEL_OPTION,
				JOptionPane.YES_NO_OPTION, icon);
		if (resp == 0) {
			ArrayList<String> listGroupA = new ArrayList<>();
			for (String s : propertiesLeft) {
				listGroupA.add(s);
			}
			ArrayList<String> listGroupB = new ArrayList<>();
			for (String s : propertiesRigth) {
				listGroupB.add(s);
			}
			HomofilyPoli homofily = new HomofilyPoli(graph,att,listGroupA, listGroupB,buttonColorA.getBackground(),buttonColorB.getBackground());
		}
	}

	private void initButtons() {
		buttonLeft = new JButton("⇐");
		buttonLeft.addActionListener(this);
		buttonRigth = new JButton("⇒");
		buttonRigth.addActionListener(this);
		buttonColorA = new JButton("");
		buttonColorA.setBackground(Color.RED);
		buttonColorA.addActionListener(this);
		buttonColorB = new JButton("");
		buttonColorB.addActionListener(this);
		buttonColorB.setBackground(Color.BLUE);
	}

	private void initSetPropertiesRigth() {
		propertiesRigth = new HashSet<>();
		listModelRigth = new DefaultListModel<>();
		int max = 0;
		for (String s : propertiesLeft) {
			max = Math.max(max, s.length());
		}
		String s = "";
		for (int i = 0; i < max; i++) {
			s += "   ";
		}
		listModelRigth.addElement(s);
		listRigth = new JList<>(listModelRigth);
		rigthScroll = new JScrollPane(listRigth);
	}

	private void initSetPropertiesLeft() {
		propertiesLeft = new HashSet<>();
		for (String s : properties) {
			propertiesLeft.add(s);
		}
		listModelLeft = new DefaultListModel<>();
		for (String s : propertiesLeft) {
			listModelLeft.addElement(s);
		}
		listLeft = new JList<>(listModelLeft);
		leftScroll = new JScrollPane(listLeft);
	}

	private void initSetProperties(String[] c) {
		properties = new HashSet<>();
		for (String s : c) {
			properties.add(s);
		}
	}

	private void resizedCompoents() {
	}

	private void updateList() {
		listModelLeft = new DefaultListModel<>();
		for (String string : propertiesLeft) {
			listModelLeft.addElement(string);
		}
		if (listModelLeft.size() == 0) {
			int max = 0;
			for (String s : propertiesLeft) {
				max = Math.max(max, s.length());
			}
			String s = "";
			for (int i = 0; i < max; i++) {
				s += "   ";
			}
			listModelLeft.addElement(s);
		}
		listLeft.setModel(listModelLeft);
		listModelRigth = new DefaultListModel<>();
		System.out.println();
		for (String string : propertiesRigth) {
			listModelRigth.addElement(string);
		}
		listRigth.setModel(listModelRigth);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonLeft)) {
			String select = listRigth.getSelectedValue();
			if (select != null && !select.trim().equals("")) {
				propertiesRigth.remove(select);
				propertiesLeft.add(select);
			}
		}
		if (e.getSource().equals(buttonRigth)) {
			String select = listLeft.getSelectedValue();
			if (select != null && !select.trim().equals("")) {
				propertiesLeft.remove(select);
				propertiesRigth.add(select);
			}
		}
		if (e.getSource().equals(buttonColorA)) {
			JColorChooser ventanaDeColores = new JColorChooser();
			Color color = ventanaDeColores.showDialog(null, "Seleccione un Color", buttonColorA.getBackground());
			buttonColorA.setBackground(color);
		}
		if (e.getSource().equals(buttonColorB)) {
			JColorChooser ventanaDeColores = new JColorChooser();
			Color color = ventanaDeColores.showDialog(null, "Seleccione un Color", buttonColorB.getBackground());
			buttonColorB.setBackground(color);
		}

		updateList();
	}
}
