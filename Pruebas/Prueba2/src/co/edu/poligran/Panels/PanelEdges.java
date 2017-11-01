package co.edu.poligran.Panels;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import javax.swing.AbstractButton;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import co.edu.poligran.Lists.ModelListSelectBy;

public class PanelEdges extends JPanel implements MouseListener, ActionListener {
	private int t, minWidthColumProperties;
	private HashMap<String, String> propertiesMapaAddEdge, propertiesMapaSelectEdge;
	private Graph graph;
	private DefaultTableModel defaultTableModel;
	private DefaultListModel<String> defaultListModel;
	private JTable table;
	private JScrollPane jspEdges, jspPropertiesAddEdge, jspPropertiesSelectEdge;
	private final Border border = LineBorder.createGrayLineBorder();
	private DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private JLabel labelAddEdge, labelNodeA, labelNodeB, labelProperties, labelSelectEdge, labelComboBoxSelectEdge,
			labelPropertiesSelect, labelRemoveEdge;
	private ComboBoxModel<String> selectNodeDefautlComboBoxModel, removeEdgeDefautlComboBoxModel, defaultComboBoxA,
			defaultComboBoxB;
	private JComboBox<String> edgesComboBox, removeComboBox, comboBoxNodeA, comboBoxNodeB;
	// private TextField comboBoxNodeA, textLabel;
	private DefaultListModel<String> DefaultListPropertiesAddEdge, defaultListPropertiesSelectEdge;
	private JList<String> listPropertiesAddEdge, listPropertiesSelectEdge;
	private JButton addEdgeButton, addPropertyAddEdge, removePropertyAddEdge, addPropertySelectEdge,
			removePropertySelectEdge, modifyEdgeSelectEdge, removeEdge;

	public PanelEdges(Graph graph) {
		t = 0;
		this.graph = graph;
		t = 0;
		this.setLayout(null);
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		minWidthColumProperties = 100;

		table = new JTable() {
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				if (columnIndex == 0)
					super.changeSelection(rowIndex, columnIndex + 1, toggle, extend);
				else
					super.changeSelection(rowIndex, columnIndex, toggle, extend);
			}
		};

		updateTable();
		processEdges();

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table.setBorder(border);
		jspEdges = new JScrollPane(table);
		jspEdges.setBorder(border);
		this.add(jspEdges);

		labelAddEdge = new JLabel("Add Edge", JLabel.CENTER);
		labelAddEdge.setBorder(border);
		this.add(labelAddEdge);

		labelNodeA = new JLabel("Node A :", JLabel.CENTER);
		this.add(labelNodeA);

		defaultComboBoxA = new DefaultComboBoxModel<>();
		comboBoxNodeA = new JComboBox<>();
		this.add(comboBoxNodeA);

		labelNodeB = new JLabel("Node B :", JLabel.CENTER);
		this.add(labelNodeB);

		defaultComboBoxB = new DefaultComboBoxModel<>();
		comboBoxNodeB = new JComboBox<>();
		this.add(comboBoxNodeB);

		labelProperties = new JLabel("Properties:", JLabel.CENTER);
		this.add(labelProperties);

		DefaultListPropertiesAddEdge = new DefaultListModel<>();
		listPropertiesAddEdge = new JList<>(DefaultListPropertiesAddEdge);
		jspPropertiesAddEdge = new JScrollPane(listPropertiesAddEdge);
		jspPropertiesAddEdge.setBorder(border);
		this.add(jspPropertiesAddEdge);

		addPropertyAddEdge = new JButton("+");
		addPropertyAddEdge.addMouseListener(this);
		this.add(addPropertyAddEdge);

		removePropertyAddEdge = new JButton("-");
		removePropertyAddEdge.addMouseListener(this);
		this.add(removePropertyAddEdge);
		// list properties
		addEdgeButton = new JButton("Add Edge");
		addEdgeButton.addMouseListener(this);
		this.add(addEdgeButton);

		propertiesMapaAddEdge = new HashMap<>();
		propertiesMapaSelectEdge = new HashMap<>();

		labelSelectEdge = new JLabel("Select Edge", JLabel.CENTER);
		labelSelectEdge.setBorder(border);
		this.add(labelSelectEdge);

		labelComboBoxSelectEdge = new JLabel("Label Edge :", JLabel.CENTER);
		this.add(labelComboBoxSelectEdge);

		selectNodeDefautlComboBoxModel = new DefaultComboBoxModel<>();
		edgesComboBox = new JComboBox<>();
		edgesComboBox.addActionListener(this);
		this.add(edgesComboBox);

		labelPropertiesSelect = new JLabel("Properties", JLabel.CENTER);
		this.add(labelPropertiesSelect);

		defaultListPropertiesSelectEdge = new DefaultListModel<>();
		listPropertiesSelectEdge = new JList<>(defaultListPropertiesSelectEdge);
		jspPropertiesSelectEdge = new JScrollPane(listPropertiesSelectEdge);
		jspPropertiesSelectEdge.setBorder(border);
		this.add(jspPropertiesSelectEdge);

		addPropertySelectEdge = new JButton("+");
		addPropertySelectEdge.addMouseListener(this);
		this.add(addPropertySelectEdge);
		removePropertySelectEdge = new JButton("-");
		removePropertySelectEdge.addMouseListener(this);
		this.add(removePropertySelectEdge);

		modifyEdgeSelectEdge = new JButton("Modify Node");
		modifyEdgeSelectEdge.addMouseListener(this);
		this.add(modifyEdgeSelectEdge);

		labelRemoveEdge = new JLabel("Remove Edge", JLabel.CENTER);
		labelRemoveEdge.setBorder(border);
		this.add(labelRemoveEdge);

		removeEdgeDefautlComboBoxModel = new DefaultComboBoxModel<>();
		removeComboBox = new JComboBox<>(removeEdgeDefautlComboBoxModel);
		removeComboBox.addActionListener(this);
		this.add(removeComboBox);

		removeEdge = new JButton("Remove");
		removeEdge.addMouseListener(this);
		this.add(removeEdge);

		cargarInfoInComoboBoxEdges();
		updateInfoInComboBoxNodes();
	}

	public void resizedComponents(int width, int height) {
		this.setBounds(0, 0, width, height);
		jspEdges.setBounds(0, 0, width - 200, height + 57);

		labelAddEdge.setBounds(jspEdges.getWidth(), 1, 200, 20);

		labelNodeA.setBounds(jspEdges.getWidth() + 10, 10 + labelAddEdge.getHeight(), 60, 20);
		comboBoxNodeA.setBounds(labelNodeA.getWidth() + labelNodeA.getX() + 10, 10 + labelAddEdge.getHeight(), 70, 20);

		labelNodeB.setBounds(jspEdges.getWidth() + 10, 10 + labelNodeA.getY() + labelNodeA.getHeight(), 60, 20);
		comboBoxNodeB.setBounds(labelNodeB.getWidth() + labelNodeB.getX() + 10,
				10 + comboBoxNodeA.getHeight() + comboBoxNodeA.getY(), 70, 20);
		labelProperties.setBounds(jspEdges.getWidth() + 10, 10 + labelNodeB.getY() + labelNodeB.getHeight(), 60, 20);

		jspPropertiesAddEdge.setBounds(jspEdges.getWidth() + 10, labelProperties.getY() + labelProperties.getHeight(),
				180, 100);

		addPropertyAddEdge.setBounds(jspEdges.getWidth() + 10,
				jspPropertiesAddEdge.getY() + jspPropertiesAddEdge.getHeight() + 10, 35, 20);

		removePropertyAddEdge.setBounds(addPropertyAddEdge.getX() + addPropertyAddEdge.getWidth() + 5,
				jspPropertiesAddEdge.getY() + jspPropertiesAddEdge.getHeight() + 10, 35, 20);

		addEdgeButton.setBounds(jspEdges.getWidth() + 90,
				jspPropertiesAddEdge.getY() + jspPropertiesAddEdge.getHeight() + 10, 100, 20);

		labelSelectEdge.setBounds(jspEdges.getWidth(), addEdgeButton.getY() + addEdgeButton.getHeight() + 10, 200, 20);

		labelComboBoxSelectEdge.setBounds(jspEdges.getWidth() + 10,
				labelSelectEdge.getY() + labelSelectEdge.getHeight() + 5, 70, 20);
		edgesComboBox.setBounds(jspEdges.getWidth() + 80, labelSelectEdge.getY() + labelSelectEdge.getHeight() + 5, 100,
				20);

		labelPropertiesSelect.setBounds(jspEdges.getWidth() + 10,
				labelComboBoxSelectEdge.getY() + labelPropertiesSelect.getHeight() + 10, 70, 20);
		jspPropertiesSelectEdge.setBounds(jspEdges.getWidth() + 10,
				labelPropertiesSelect.getY() + labelPropertiesSelect.getHeight(), 180, 100);

		addPropertySelectEdge.setBounds(jspEdges.getWidth() + 10,
				jspPropertiesSelectEdge.getY() + jspPropertiesSelectEdge.getHeight() + 10, 35, 20);
		removePropertySelectEdge.setBounds(addPropertySelectEdge.getX() + addPropertySelectEdge.getWidth() + 5,
				jspPropertiesSelectEdge.getY() + jspPropertiesSelectEdge.getHeight() + 10, 35, 20);
		modifyEdgeSelectEdge.setBounds(jspEdges.getWidth() + 90,
				jspPropertiesSelectEdge.getY() + jspPropertiesSelectEdge.getHeight() + 10, 100, 20);

		labelRemoveEdge.setBounds(jspEdges.getWidth(),
				modifyEdgeSelectEdge.getY() + modifyEdgeSelectEdge.getHeight() + 5, 200, 20);
		removeComboBox.setBounds(jspEdges.getWidth() + 10, labelRemoveEdge.getY() + labelRemoveEdge.getHeight() + 10,
				70, 20);
		removeEdge.setBounds(removeComboBox.getX() + removeComboBox.getWidth() + 10, removeComboBox.getY(), 100, 20);
	}

	private void updateInfoInComboBoxNodes() {
		String nodes[] = new String[graph.getNodeCount() + 1];
		nodes[0] = "";
		int i = 1;
		for (Node node : graph) {
			nodes[i++] = node.getId();
		}
		defaultComboBoxA = new DefaultComboBoxModel<>(nodes);
		comboBoxNodeA.setModel(defaultComboBoxA);
		defaultComboBoxB = new DefaultComboBoxModel<>(nodes);
		comboBoxNodeB.setModel(defaultComboBoxB);
	}

	private void cargarInfoInComoboBoxEdges() {
		String[] edges = new String[graph.getEdgeCount() + 1];
		edges[0] = "";
		int i = 1;
		for (Edge e : graph.getEdgeSet()) {
			edges[i++] = e.getId() + "";
		}
		selectNodeDefautlComboBoxModel = new DefaultComboBoxModel<>(edges);
		edgesComboBox.setModel(selectNodeDefautlComboBoxModel);
		removeEdgeDefautlComboBoxModel = new DefaultComboBoxModel<>(edges);
		removeComboBox.setModel(removeEdgeDefautlComboBoxModel);
	}

	public void processEdges() {
		int i = 0;
		updateTable();
		for (Edge e : graph.getEdgeSet()) {
			int j = 0;
			defaultTableModel.setValueAt(e.getId(), i, j++);
			defaultTableModel.setValueAt(e.getNode0().getId(), i, j++);
			defaultTableModel.setValueAt(e.getNode1().getId(), i, j++);
			if (e.hasAttribute("-attribute-width")) {
				// System.out.println("sdfgh");
				defaultTableModel.setValueAt(e.getAttribute("-attribute-width"), i, j++);
			} else {
				defaultTableModel.setValueAt(1.0 + "", i, j++);
			}
			String color = e.getAttribute("ui.style") == null ? "Black" : e.getAttribute("ui.style");
			defaultTableModel.setValueAt(
					color.startsWith("fill-color") ? color.substring(11, color.length() - 1) : "Black", i, j++);
			StringBuffer sb = new StringBuffer();
			for (String a : e.getAttributeKeySet()) {
				if (a.startsWith("-attribute-")) {
					sb.append(a.substring(11, a.length()) + " : " + e.getAttribute(a) + " ;    ");
				}
			}
			defaultTableModel.setValueAt(sb.toString(), i, j++);
			minWidthColumProperties = Math.max(minWidthColumProperties, 100 + 5 * sb.toString().trim().length());
			i++;
		}
		if (t > 0)
			cargarInfoInComoboBoxEdges();
		t++;
		table.getColumnModel().getColumn(5).setPreferredWidth(minWidthColumProperties);
		table.getColumnModel().getColumn(5).setCellRenderer(tcr);
	}

	private void updateTable() {
		defaultTableModel = new DefaultTableModel(graph.getEdgeCount(), 4) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
		};
		table.setModel(defaultTableModel);
		// indentificadores para la primera fila osea los indices
		String[] colums = new String[] { "Id Edge", "Node A", "Node B", " Width", "Color", "Properties" };
		defaultTableModel.setColumnIdentifiers(colums);
		// Se cambia el tamaño de la columnas por defecto
		table.getColumnModel().getColumn(0).setCellRenderer(table.getTableHeader().getDefaultRenderer());
		table.getColumnModel().setColumnMargin(2);
		for (int i = 0; i < 4; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(70);
			table.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}

		table.getColumnModel().getColumn(5).setPreferredWidth(minWidthColumProperties);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);
	}

	private void updateListSelectNode() {
		defaultListPropertiesSelectEdge = new DefaultListModel<>();
		listPropertiesSelectEdge.setModel(defaultListPropertiesSelectEdge);
		for (Entry<String, String> pa : propertiesMapaSelectEdge.entrySet()) {
			defaultListPropertiesSelectEdge.addElement(pa.getKey().toLowerCase() + " : " + pa.getValue());
		}
	}

	private void clearListAddEdge() {
		DefaultListPropertiesAddEdge = new DefaultListModel<>();
		listPropertiesAddEdge.setModel(DefaultListPropertiesAddEdge);
	}

	private void updateListAddNode() {
		clearListAddEdge();
		for (Entry<String, String> pa : propertiesMapaAddEdge.entrySet()) {
			DefaultListPropertiesAddEdge.addElement(pa.getKey() + " : " + pa.getValue());
		}
		cargarInfoInComoboBoxEdges();
	}

	private void addEdge(String id, Node a, Node b) {
		try {
			graph.addEdge(id, a, b);
			graph.getEdge(id).setAttribute("ui.style", "fill-color:#fff;");
			// agregar propiedades
			for (Entry<String, String> pa : propertiesMapaAddEdge.entrySet()) {
				graph.getEdge(id).addAttribute("-attribute-" + pa.getKey(), pa.getValue());
			}
			defaultTableModel.addRow(new Object[] { id });
			DefaultListPropertiesAddEdge = new DefaultListModel<>();
			listPropertiesAddEdge.setModel(DefaultListPropertiesAddEdge);
			propertiesMapaAddEdge = new HashMap<>();
			// comboBoxNodeA.setText("");
			// comboBoxNodeB.setText("");
			processEdges();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "The selected id already exists, please enter a different one.",
					"Add Edge Exception", JOptionPane.WARNING_MESSAGE);
			System.out.println(e);
		}
	}

	private void paintGraph() {
		for (Node node : graph) {
			node.setAttribute("ui.style", "fill-color:#fff;");
		}
		for (Edge edge : graph.getEdgeSet()) {
			edge.setAttribute("ui.style", "fill-color:#fff;");
		}
	}

	// Mouse Listener
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(addEdgeButton)) {
			String a = comboBoxNodeA.getSelectedItem() + "";
			String b = comboBoxNodeB.getSelectedItem() + "";
			if (!a.trim().equals("") && !b.trim().equals(""))
				addEdge(a + "-" + b, graph.getNode(Integer.parseInt(a)), graph.getNode(Integer.parseInt(b)));
		}
		if (e.getSource().equals(modifyEdgeSelectEdge)) {
			if (edgesComboBox.getSelectedIndex() > 0) {
				String edge = (edgesComboBox.getSelectedItem() + "");
				int index = edgesComboBox.getSelectedIndex();
				Edge n = graph.getEdge(edge);
				ArrayList<String> delete = new ArrayList<>();
				for (String s : n.getAttributeKeySet()) {
					if (s.startsWith("-attribute-"))
						delete.add(s);
				}
				for (String s : delete) {
					n.removeAttribute(s);
				}
				for (Entry<String, String> p : propertiesMapaSelectEdge.entrySet()) {
					n.addAttribute("-attribute-" + p.getKey(), p.getValue());
				}
				updateListSelectNode();
				processEdges();
				edgesComboBox.setSelectedIndex(index);
			}
		}
		if (e.getSource().equals(addPropertyAddEdge)) {
			// jOptionPane
			try {
				ModelListSelectBy mlsb = new ModelListSelectBy();
				Object[] propeties = new Object[mlsb.getSize()];
				for (int i = 0; i < propeties.length; i++) {
					propeties[i] = mlsb.get(i);
				}
				JPanel panel = new JPanel();
				JComboBox<Object> properties = new JComboBox<>(propeties);
				JTextField value = new JTextField(20);
				panel.add(properties);
				panel.add(value);
				Icon icono = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
				int resp = JOptionPane.showConfirmDialog(null, panel, "Add Edge", JOptionPane.CANCEL_OPTION,
						JOptionPane.YES_NO_OPTION, icono);
				if (resp == 0) {
					String p = properties.getSelectedItem().toString().trim().toLowerCase();
					propertiesMapaAddEdge.put(p, value.getText().trim().toLowerCase());
					updateListAddNode();
				}
			} catch (Exception ex) {
			}
		}
		//////
		if (e.getSource().equals(removePropertyAddEdge)) {
			try {
				int index = listPropertiesAddEdge.getSelectedIndex();
				String remove = listPropertiesAddEdge.getSelectedValue();
				StringTokenizer st = new StringTokenizer(remove);
				DefaultListPropertiesAddEdge.remove(index);
				propertiesMapaAddEdge.remove(st.nextToken().trim());
			} catch (Exception ex) {
			}
		}
		if (e.getSource().equals(addPropertySelectEdge)) {
			if (edgesComboBox.getSelectedIndex() > 0) {
				try {
					ModelListSelectBy mlsb = new ModelListSelectBy();
					Object[] propeties = new Object[mlsb.getSize()];
					for (int i = 0; i < propeties.length; i++) {
						propeties[i] = mlsb.get(i);
					}
					JPanel panel = new JPanel();
					JComboBox<Object> properties = new JComboBox<>(propeties);
					JTextField value = new JTextField(20);
					panel.add(properties);
					panel.add(value);
					Icon icono = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
					int resp = JOptionPane.showConfirmDialog(null, panel, "Add Node", JOptionPane.CANCEL_OPTION,
							JOptionPane.YES_NO_OPTION, icono);
					if (resp == 0) {
						String edgeId = (edgesComboBox.getSelectedItem() + "".trim());
						String p = properties.getSelectedItem().toString().trim().toLowerCase();
						propertiesMapaSelectEdge.put(p, value.getText().trim().toLowerCase());
						Edge n = graph.getEdge(edgeId);
						n.addAttribute("-attribute-" + p, value.getText().trim().toLowerCase());
						updateListSelectNode();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}
		if (e.getSource().equals(removePropertySelectEdge)) {
			try {
				int index = listPropertiesSelectEdge.getSelectedIndex();
				String remove = listPropertiesSelectEdge.getSelectedValue();
				StringTokenizer st = new StringTokenizer(remove);
				defaultListPropertiesSelectEdge.remove(index);
				propertiesMapaSelectEdge.remove(st.nextToken().trim());
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		if (e.getSource().equals(removeEdge)) {
			if (removeComboBox.getSelectedIndex() > 0) {
				String item = (removeComboBox.getSelectedItem() + "");
				graph.removeEdge(item);
				cargarInfoInComoboBoxEdges();
				paintGraph();
				processEdges();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(edgesComboBox)) {
			defaultListPropertiesSelectEdge = new DefaultListModel<>();
			listPropertiesSelectEdge.setModel(defaultListPropertiesSelectEdge);
			propertiesMapaSelectEdge = new HashMap<>();
			if (edgesComboBox.getSelectedIndex() > 0) {
				String index = (edgesComboBox.getSelectedItem() + "");
				Edge n = graph.getEdge(index);
				for (String s : n.getAttributeKeySet()) {
					if (s.startsWith("-attribute-")) {
						String l = n.getAttribute(s).toString().toLowerCase();
						s = s.substring(11, s.length()).toLowerCase();
						defaultListPropertiesSelectEdge.addElement(s.toLowerCase() + " : " + l);
						propertiesMapaSelectEdge.put(s, l);
					}
				}
			}
		}
	}
}
