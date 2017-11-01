package co.edu.poligran.Panels;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

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

public class PanelNodes extends JPanel implements MouseListener, ActionListener {
	private int t;
	private DefaultComboBoxModel<String> selectNodeDefautlComboBoxModel, removeNodeDefautlComboBoxModel;
	public int minWidthColumProperties;
	public JScrollPane jspNodes, jspPropertiesAddNode, jspPropertiesSelectNode;
	public DefaultTableModel dtm;
	private HashMap<String, String> propertiesMapaAddNode, propertiesMapaSelectNode;
	private JTable table;
	private Graph graph;
	private final Border border = LineBorder.createGrayLineBorder();
	private DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private JButton addNodeButton, addPropertyAddNode, removePropertyAddNode, addPropertySelectNode,
			removePropertySelectNode, modifyNodeSelectNode, removeNode;
	private JLabel labelId, labelAddNode, labelLabel, labelProperties, labelSelectNode, labelComboBoxSelectNode,
			labelPropertiesSelect, labelRemoveNode;
	private TextField textIdNode, textLabel;
	private JList<String> listPropertiesAddNode, listPropertiesSelectNode;
	private DefaultListModel<String> DefaultListPropertiesAddNode, defaultListPropertiesSelectNode;
	private JComboBox<String> nodesComboBox, removeComboBox;

	public PanelNodes(Graph g) throws IOException {
		this.graph = g;
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
		ProcessNodes();

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table.setBorder(border);
		jspNodes = new JScrollPane(table);
		jspNodes.setBorder(border);
		this.add(jspNodes);

		labelAddNode = new JLabel("Add Node", JLabel.CENTER);
		labelAddNode.setBorder(border);
		this.add(labelAddNode);

		labelId = new JLabel("Node A :", JLabel.CENTER);
		this.add(labelId);

		textIdNode = new TextField();
		this.add(textIdNode);

		labelLabel = new JLabel("Node B :", JLabel.CENTER);
		this.add(labelLabel);

		textLabel = new TextField();
		this.add(textLabel);

		labelProperties = new JLabel("Properties:", JLabel.CENTER);
		this.add(labelProperties);

		addNodeButton = new JButton("Add Node");
		addNodeButton.addMouseListener(this);
		this.add(addNodeButton);

		addPropertyAddNode = new JButton("+");
		addPropertyAddNode.addMouseListener(this);
		this.add(addPropertyAddNode);
		removePropertyAddNode = new JButton("-");
		removePropertyAddNode.addMouseListener(this);
		this.add(removePropertyAddNode);
		// list properties
		DefaultListPropertiesAddNode = new DefaultListModel<>();
		listPropertiesAddNode = new JList<>(DefaultListPropertiesAddNode);
		jspPropertiesAddNode = new JScrollPane(listPropertiesAddNode);
		jspPropertiesAddNode.setBorder(border);
		this.add(jspPropertiesAddNode);

		propertiesMapaAddNode = new HashMap<>();
		propertiesMapaSelectNode = new HashMap<>();

		labelSelectNode = new JLabel("Select Node", JLabel.CENTER);
		labelSelectNode.setBorder(border);
		this.add(labelSelectNode);

		labelComboBoxSelectNode = new JLabel("Label Node :", JLabel.CENTER);
		this.add(labelComboBoxSelectNode);

		selectNodeDefautlComboBoxModel = new DefaultComboBoxModel<>();
		nodesComboBox = new JComboBox<>();
		nodesComboBox.addActionListener(this);
		this.add(nodesComboBox);

		labelPropertiesSelect = new JLabel("Properties", JLabel.CENTER);
		this.add(labelPropertiesSelect);

		defaultListPropertiesSelectNode = new DefaultListModel<>();
		listPropertiesSelectNode = new JList<>(defaultListPropertiesSelectNode);
		jspPropertiesSelectNode = new JScrollPane(listPropertiesSelectNode);
		jspPropertiesSelectNode.setBorder(border);
		this.add(jspPropertiesSelectNode);

		addPropertySelectNode = new JButton("+");
		addPropertySelectNode.addMouseListener(this);
		this.add(addPropertySelectNode);
		removePropertySelectNode = new JButton("-");
		removePropertySelectNode.addMouseListener(this);
		this.add(removePropertySelectNode);

		modifyNodeSelectNode = new JButton("Modify Node");
		modifyNodeSelectNode.addMouseListener(this);
		this.add(modifyNodeSelectNode);

		labelRemoveNode = new JLabel("Remove Node", JLabel.CENTER);
		labelRemoveNode.setBorder(border);
		this.add(labelRemoveNode);

		removeNodeDefautlComboBoxModel = new DefaultComboBoxModel<>();
		removeComboBox = new JComboBox<>(removeNodeDefautlComboBoxModel);
		removeComboBox.addActionListener(this);
		this.add(removeComboBox);

		cargarInfoInComoboBox();

		removeNode = new JButton("Remove");
		removeNode.addMouseListener(this);
		this.add(removeNode);
	}

	public void resizedComponents(int width, int height) {
		this.setBounds(0, 0, width, height);
		jspNodes.setBounds(0, 0, width - 200, height + 57);

		labelAddNode.setBounds(jspNodes.getWidth(), 1, 200, 20);

		labelId.setBounds(20 + jspNodes.getWidth(), 10 + labelAddNode.getHeight(), 20, 20);
		textIdNode.setBounds(labelId.getWidth() + labelId.getX() + 10, 10 + labelAddNode.getHeight(), 130, 18);

		labelLabel.setBounds(jspNodes.getWidth() + 10, 10 + labelId.getY() + labelId.getHeight(), 40, 20);
		textLabel.setBounds(labelLabel.getWidth() + labelLabel.getX(), 10 + textIdNode.getHeight() + textIdNode.getY(),
				130, 18);

		labelProperties.setBounds(jspNodes.getWidth() + 10, 10 + labelLabel.getY() + labelLabel.getHeight(), 60, 20);

		jspPropertiesAddNode.setBounds(jspNodes.getWidth() + 10, labelProperties.getY() + labelProperties.getHeight(),
				180, 100);

		addPropertyAddNode.setBounds(jspNodes.getWidth() + 10,
				jspPropertiesAddNode.getY() + jspPropertiesAddNode.getHeight() + 10, 35, 20);

		removePropertyAddNode.setBounds(addPropertyAddNode.getX() + addPropertyAddNode.getWidth() + 5,
				jspPropertiesAddNode.getY() + jspPropertiesAddNode.getHeight() + 10, 35, 20);

		addNodeButton.setBounds(jspNodes.getWidth() + 90,
				jspPropertiesAddNode.getY() + jspPropertiesAddNode.getHeight() + 10, 100, 20);

		labelSelectNode.setBounds(jspNodes.getWidth(), addNodeButton.getY() + addNodeButton.getHeight() + 10, 200, 20);

		labelComboBoxSelectNode.setBounds(jspNodes.getWidth() + 10,
				labelSelectNode.getY() + labelSelectNode.getHeight() + 5, 70, 20);
		nodesComboBox.setBounds(jspNodes.getWidth() + 80, labelSelectNode.getY() + labelSelectNode.getHeight() + 5, 100,
				20);

		labelPropertiesSelect.setBounds(jspNodes.getWidth() + 10,
				labelComboBoxSelectNode.getY() + labelPropertiesSelect.getHeight() + 10, 70, 20);
		jspPropertiesSelectNode.setBounds(jspNodes.getWidth() + 10,
				labelPropertiesSelect.getY() + labelPropertiesSelect.getHeight(), 180, 100);

		addPropertySelectNode.setBounds(jspNodes.getWidth() + 10,
				jspPropertiesSelectNode.getY() + jspPropertiesSelectNode.getHeight() + 10, 35, 20);
		removePropertySelectNode.setBounds(addPropertySelectNode.getX() + addPropertySelectNode.getWidth() + 5,
				jspPropertiesSelectNode.getY() + jspPropertiesSelectNode.getHeight() + 10, 35, 20);
		modifyNodeSelectNode.setBounds(jspNodes.getWidth() + 90,
				jspPropertiesSelectNode.getY() + jspPropertiesSelectNode.getHeight() + 10, 100, 20);

		labelRemoveNode.setBounds(jspNodes.getWidth(),
				modifyNodeSelectNode.getY() + modifyNodeSelectNode.getHeight() + 5, 200, 20);
		removeComboBox.setBounds(jspNodes.getWidth() + 10, labelRemoveNode.getY() + labelRemoveNode.getHeight() + 10,
				70, 20);
		removeNode.setBounds(removeComboBox.getX() + removeComboBox.getWidth() + 10, removeComboBox.getY(), 100, 20);
	}

	private void updateTable() {
		dtm = new DefaultTableModel(graph.getNodeCount(), 4) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
		};
		table.setModel(dtm);
		// indentificadores para la primera fila osea los indices
		String[] colums = new String[] { "Id Node", "Label", "Degree", "Color", "Properties" };
		dtm.setColumnIdentifiers(colums);
		// Se cambia el tamaño de la columnas por defecto
		table.getColumnModel().getColumn(0).setCellRenderer(table.getTableHeader().getDefaultRenderer());
		table.getColumnModel().setColumnMargin(2);
		for (int i = 0; i < 4; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(70);
			table.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}

		table.getColumnModel().getColumn(4).setPreferredWidth(minWidthColumProperties);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);
	}

	private void cargarInfoInComoboBox() {
		String[] nodes = new String[graph.getNodeCount() + 1];
		nodes[0] = " ";
		int i = 1;
		for (Node node : graph) {
			nodes[i++] = node.getIndex() + "";
		}
		selectNodeDefautlComboBoxModel = new DefaultComboBoxModel<>(nodes);
		nodesComboBox.setModel(selectNodeDefautlComboBoxModel);
		removeNodeDefautlComboBoxModel = new DefaultComboBoxModel<>(nodes);
		removeComboBox.setModel(removeNodeDefautlComboBoxModel);
	}

	private void updateListSelectNode() {
		defaultListPropertiesSelectNode = new DefaultListModel<>();
		listPropertiesSelectNode.setModel(defaultListPropertiesSelectNode);
		for (Entry<String, String> pa : propertiesMapaSelectNode.entrySet()) {
			defaultListPropertiesSelectNode.addElement(pa.getKey().toLowerCase() + " : " + pa.getValue());
		}
	}

	private void clearListAddNode() {
		DefaultListPropertiesAddNode = new DefaultListModel<>();
		listPropertiesAddNode.setModel(DefaultListPropertiesAddNode);
	}

	private void updateListAddNode() {
		clearListAddNode();
		for (Entry<String, String> pa : propertiesMapaAddNode.entrySet()) {
			DefaultListPropertiesAddNode.addElement(pa.getKey() + " : " + pa.getValue());
		}
		cargarInfoInComoboBox();
	}

	public void ProcessNodes() {
		int i = 0;
		updateTable();
		for (Node n : graph.getNodeSet()) {
			int j = 0;
			dtm.setValueAt(n.getId(), i, j++);
			dtm.setValueAt(n.getIndex(), i, j++);
			dtm.setValueAt(n.getDegree(), i, j++);
			String color = n.getAttribute("ui.style") == null ? "Black" : n.getAttribute("ui.style");
			dtm.setValueAt(color.startsWith("fill-color") ? color.substring(11, color.length() - 1) : "Black", i, j++);
			StringBuffer sb = new StringBuffer();
			for (String a : n.getAttributeKeySet()) {
				if (a.startsWith("-attribute-")) {
					sb.append(a.substring(11, a.length()) + " : " + n.getAttribute(a) + " ;    ");
				}
			}
			dtm.setValueAt(sb.toString(), i, j++);
			minWidthColumProperties = Math.max(minWidthColumProperties, 100 + 5 * sb.toString().trim().length());
			i++;
		}
		if (t > 0)
			cargarInfoInComoboBox();
		t++;
		table.getColumnModel().getColumn(4).setPreferredWidth(minWidthColumProperties);
	}

	private void addNode(String id) {
		try {
			graph.addNode(id);
			graph.getNode(id).setAttribute("ui.style", "fill-color:#fff;");
			// agregar propiedades
			for (Entry<String, String> pa : propertiesMapaAddNode.entrySet()) {
				graph.getNode(id).addAttribute("-attribute-" + pa.getKey(), pa.getValue());
			}
			dtm.addRow(new Object[] { id });
			DefaultListPropertiesAddNode = new DefaultListModel<>();
			listPropertiesAddNode.setModel(DefaultListPropertiesAddNode);
			propertiesMapaAddNode = new HashMap<>();
			textIdNode.setText("");
			textLabel.setText("");
			ProcessNodes();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "The selected id already exists, please enter a different one.",
					"Add Node Exception", JOptionPane.WARNING_MESSAGE);
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
		if (e.getSource().equals(addNodeButton)) {
			addNode(textIdNode.getText());
		}
		if (e.getSource().equals(modifyNodeSelectNode)) {
			if (nodesComboBox.getSelectedIndex() > 0) {
				int node = Integer.parseInt(nodesComboBox.getSelectedItem() + "");
				int index = nodesComboBox.getSelectedIndex();
				Node n = graph.getNode(node);
				ArrayList<String> delete = new ArrayList<>();
				for (String s : n.getAttributeKeySet()) {
					if (s.startsWith("-attribute-"))
						delete.add(s);
				}
				for (String s : delete) {
					n.removeAttribute(s);
				}
				for (Entry<String, String> p : propertiesMapaSelectNode.entrySet()) {
					n.addAttribute("-attribute-" + p.getKey(), p.getValue());
				}
				updateListSelectNode();
				ProcessNodes();
				nodesComboBox.setSelectedIndex(index);
			}
		}
		if (e.getSource().equals(addPropertyAddNode)) {
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
				int resp = JOptionPane.showConfirmDialog(null, panel, "Add Node", JOptionPane.CANCEL_OPTION,
						JOptionPane.YES_NO_OPTION, icono);
				if (resp == 0) {
					String p = properties.getSelectedItem().toString().trim().toLowerCase();
					propertiesMapaAddNode.put(p, value.getText().trim().toLowerCase());
					updateListAddNode();
				}
			} catch (Exception ex) {
			}
		}
		if (e.getSource().equals(removePropertyAddNode)) {
			try {
				int index = listPropertiesAddNode.getSelectedIndex();
				String remove = listPropertiesAddNode.getSelectedValue();
				StringTokenizer st = new StringTokenizer(remove);
				DefaultListPropertiesAddNode.remove(index);
				propertiesMapaAddNode.remove(st.nextToken().trim());
			} catch (Exception ex) {
			}
		}
		if (e.getSource().equals(addPropertySelectNode)) {
			if (nodesComboBox.getSelectedIndex() > 0) {
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
						int nodeId = Integer.parseInt(nodesComboBox.getSelectedItem() + "".trim());
						String p = properties.getSelectedItem().toString().trim().toLowerCase();
						propertiesMapaSelectNode.put(p, value.getText().trim().toLowerCase());
						Node n = graph.getNode(nodeId);
						n.addAttribute("-attribute-" + p, value.getText().trim().toLowerCase());
						updateListSelectNode();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		}
		if (e.getSource().equals(removePropertySelectNode)) {
			try {
				int index = listPropertiesSelectNode.getSelectedIndex();
				String remove = listPropertiesSelectNode.getSelectedValue();
				StringTokenizer st = new StringTokenizer(remove);
				defaultListPropertiesSelectNode.remove(index);
				propertiesMapaSelectNode.remove(st.nextToken().trim());
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		if (e.getSource().equals(removeNode)) {
			if (removeComboBox.getSelectedIndex() > 0) {
				int item = Integer.parseInt(removeComboBox.getSelectedItem() + "");
				graph.removeNode(item);
				cargarInfoInComoboBox();
				paintGraph();
				ProcessNodes();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	// ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(nodesComboBox)) {
			defaultListPropertiesSelectNode = new DefaultListModel<>();
			listPropertiesSelectNode.setModel(defaultListPropertiesSelectNode);
			propertiesMapaSelectNode = new HashMap<>();
			if (nodesComboBox.getSelectedIndex() > 0) {
				int index = Integer.parseInt(nodesComboBox.getSelectedItem() + "");
				Node n = graph.getNode(index);
				for (String s : n.getAttributeKeySet()) {
					if (s.startsWith("-attribute-")) {
						String l = n.getAttribute(s).toString().toLowerCase();
						s = s.substring(11, s.length()).toLowerCase();
						defaultListPropertiesSelectNode.addElement(s.toLowerCase() + " : " + l);
						propertiesMapaSelectNode.put(s, l);
					}
				}
			}
		}
	}
}
