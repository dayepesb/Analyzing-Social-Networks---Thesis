package co.edu.poligran.Panels;

import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
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

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import co.edu.poligran.Lists.ModelListSelectBy;

public class PanelNodes extends JPanel implements MouseListener {
	private DefaultComboBoxModel<String> defautlComboBoxModel;
	public int nodes, minWidthColumProperties;
	public JScrollPane jsp, jspPrperties;
	public DefaultTableModel dtm;
	private HashMap<String, String> propertiesMap;
	private JTable table;
	private Graph graph;
	private final Border border = LineBorder.createGrayLineBorder();
	private DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
	private JButton addNodeButton, addProperty, removeProperty;
	private JLabel labelId, labelAddNode, labelLabel, labelProperties, labelSelectNode, labelComboBoxSelectNode,
			labelPropertiesSelect;
	private TextField textIdNode, textLabel;
	private JList<String> listProperties;
	private DefaultListModel<String> DefaultListPropertiesAddNode;
	private JComboBox<String> nodesComboBox;

	public PanelNodes(Graph g, int width, int height) throws IOException {
		this.graph = g;
		this.setLayout(null);
		this.add(new JButton());
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		minWidthColumProperties = 100;
		nodes = graph.getNodeCount();
		dtm = new DefaultTableModel(nodes, 4) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
		};

		// indentificadores para la primera fila osea los indices
		String[] colums = new String[] { "Id Node", "Label", "Degree", "Color", "Properties" };
		dtm.setColumnIdentifiers(colums);

		table = new JTable(dtm) {
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				if (columnIndex == 0)
					super.changeSelection(rowIndex, columnIndex + 1, toggle, extend);
				else
					super.changeSelection(rowIndex, columnIndex, toggle, extend);
			}
		};

		ProcessNodes();

		// Se cambia el tamaño de la columnas por defecto
		table.getColumnModel().getColumn(0).setCellRenderer(table.getTableHeader().getDefaultRenderer());
		table.getColumnModel().setColumnMargin(2);
		for (int i = 0; i < 4; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(70);
			table.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}

		table.getColumnModel().getColumn(4).setPreferredWidth(minWidthColumProperties);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table.setBorder(border);
		jsp = new JScrollPane(table);
		jsp.setBorder(border);
		this.add(jsp);

		labelAddNode = new JLabel("Add Node", JLabel.CENTER);
		labelAddNode.setBorder(border);
		this.add(labelAddNode);

		labelId = new JLabel("Id :", JLabel.CENTER);
		this.add(labelId);

		textIdNode = new TextField();
		this.add(textIdNode);

		labelLabel = new JLabel("Label:", JLabel.CENTER);
		this.add(labelLabel);

		textLabel = new TextField();
		this.add(textLabel);

		labelProperties = new JLabel("Properties:", JLabel.CENTER);
		this.add(labelProperties);

		addNodeButton = new JButton("Add Node");
		addNodeButton.addMouseListener(this);
		this.add(addNodeButton);

		addProperty = new JButton("+");
		addProperty.addMouseListener(this);
		this.add(addProperty);
		removeProperty = new JButton("-");
		removeProperty.addMouseListener(this);
		this.add(removeProperty);
		// list properties
		DefaultListPropertiesAddNode = new DefaultListModel<>();
		listProperties = new JList<>(DefaultListPropertiesAddNode);
		jspPrperties = new JScrollPane(listProperties);
		jspPrperties.setBorder(border);
		this.add(jspPrperties);

		propertiesMap = new HashMap<>();

		labelSelectNode = new JLabel("Select Node", JLabel.CENTER);
		labelSelectNode.setBorder(border);
		this.add(labelSelectNode);

		labelComboBoxSelectNode = new JLabel("Label Node :", JLabel.CENTER);
		this.add(labelComboBoxSelectNode);

		defautlComboBoxModel = new DefaultComboBoxModel<>();
		nodesComboBox = new JComboBox<>();
		cargarInfoInComoboBox();
		nodesComboBox.setBorder(border);
		this.add(nodesComboBox);

		labelPropertiesSelect = new JLabel("Properties", JLabel.CENTER);
		this.add(labelPropertiesSelect);
	}

	public void resizedComponents(int width, int height) {
		this.setBounds(0, 0, width, height);
		jsp.setBounds(0, 0, width - 200, height + 57);

		labelAddNode.setBounds(jsp.getWidth(), 1, 200, 20);

		labelId.setBounds(20 + jsp.getWidth(), 10 + labelAddNode.getHeight(), 20, 20);
		textIdNode.setBounds(labelId.getWidth() + labelId.getX() + 10, 10 + labelAddNode.getHeight(), 130, 18);

		labelLabel.setBounds(jsp.getWidth() + 10, 10 + labelId.getY() + labelId.getHeight(), 40, 20);
		textLabel.setBounds(labelLabel.getWidth() + labelLabel.getX(), 10 + textIdNode.getHeight() + textIdNode.getY(),
				130, 18);

		labelProperties.setBounds(jsp.getWidth() + 10, 10 + labelLabel.getY() + labelLabel.getHeight(), 60, 20);

		jspPrperties.setBounds(jsp.getWidth() + 10, labelProperties.getY() + labelProperties.getHeight(), 180, 100);

		addProperty.setBounds(jsp.getWidth() + 10, jspPrperties.getY() + jspPrperties.getHeight() + 10, 35, 20);

		removeProperty.setBounds(addProperty.getX() + addProperty.getWidth() + 5,
				jspPrperties.getY() + jspPrperties.getHeight() + 10, 35, 20);

		addNodeButton.setBounds(jsp.getWidth() + 90, jspPrperties.getY() + jspPrperties.getHeight() + 10, 100, 20);

		labelSelectNode.setBounds(jsp.getWidth(), addNodeButton.getY() + addNodeButton.getHeight() + 10, 200, 20);

		labelComboBoxSelectNode.setBounds(jsp.getWidth() + 10, labelSelectNode.getY() + labelSelectNode.getHeight() + 5,
				70, 20);
		nodesComboBox.setBounds(jsp.getWidth() + 80, labelSelectNode.getY() + labelSelectNode.getHeight() + 5, 100, 20);
	}

	private void cargarInfoInComoboBox() {
		String[] nodes = new String[graph.getNodeCount()];
		int i = 0;
		for (Node node : graph) {
			nodes[i++] = node.getIndex() + "";
		}
		defautlComboBoxModel = new DefaultComboBoxModel<>(nodes);
		nodesComboBox.setModel(defautlComboBoxModel);
	}

	private void clearListAddNode() {
		DefaultListPropertiesAddNode = new DefaultListModel<>();
		listProperties.setModel(DefaultListPropertiesAddNode);
	}

	private void actualizarLista() {
		clearListAddNode();
		for (Entry<String, String> pa : propertiesMap.entrySet()) {
			DefaultListPropertiesAddNode.addElement(pa.getKey() + " : " + pa.getValue());
		}
		cargarInfoInComoboBox();
	}

	public void ProcessNodes() {
		int i = 0;
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
		table.getColumnModel().getColumn(4).setPreferredWidth(minWidthColumProperties);
	}

	private void addNode(String id) {
		try {
			graph.addNode(id);
			graph.getNode(id).setAttribute("ui.style", "fill-color:#fff;");
			// agregar propiedades
			for (Entry<String, String> pa : propertiesMap.entrySet()) {
				graph.getNode(id).addAttribute("-attribute-" + pa.getKey(), pa.getValue());
			}
			dtm.addRow(new Object[] { id });
			DefaultListPropertiesAddNode = new DefaultListModel<>();
			listProperties.setModel(DefaultListPropertiesAddNode);
			propertiesMap = new HashMap<>();
			textIdNode.setText("");
			textLabel.setText("");
			cargarInfoInComoboBox();
			ProcessNodes();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "The selected id already exists, please enter a different one.",
					"Add Node Exception", JOptionPane.WARNING_MESSAGE);
		}
	}

	// Mouse Listener
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(addNodeButton)) {
			addNode(textIdNode.getText());
		}
		if (e.getSource().equals(addProperty)) {
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
					propertiesMap.put(p, value.getText().trim().toLowerCase());
					actualizarLista();
				}
			} catch (Exception ex) {
			}
		}
		if (e.getSource().equals(removeProperty)) {
			try {
				int index = listProperties.getSelectedIndex();
				String remove = listProperties.getSelectedValue();
				StringTokenizer st = new StringTokenizer(remove);
				DefaultListPropertiesAddNode.remove(index);
				propertiesMap.remove(st.nextToken().trim());
			} catch (Exception ex) {
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
}
