package co.edu.poligran.Panels;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

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
	private JLabel labelAddEdge, labelId, labelLabel, labelProperties, labelSelectEdge, labelComboBoxSelectEdge,
			labelPropertiesSelect;
	private ComboBoxModel<String> selectNodeDefautlComboBoxModel;
	private JComboBox<String> nodesComboBox;
	private TextField textIdEdge, textLabel;
	private DefaultListModel<String> DefaultListPropertiesAddEdge, defaultListPropertiesSelectEdge;
	private JList<String> listPropertiesAddEdge, listPropertiesSelectEdge;
	private JButton addEdgeButton, addPropertyAddEdge, removePropertyAddEdge,addPropertySelectEdge,removePropertySelectEdge,modifyNodeSelectEdge;

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

		labelId = new JLabel("Id :", JLabel.CENTER);
		this.add(labelId);

		textIdEdge = new TextField();
		this.add(textIdEdge);

		labelLabel = new JLabel("Label:", JLabel.CENTER);
		this.add(labelLabel);

		textLabel = new TextField();
		this.add(textLabel);

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

		labelComboBoxSelectEdge = new JLabel("Label Node :", JLabel.CENTER);
		this.add(labelComboBoxSelectEdge);

		selectNodeDefautlComboBoxModel = new DefaultComboBoxModel<>();
		nodesComboBox = new JComboBox<>();
		nodesComboBox.addActionListener(this);
		this.add(nodesComboBox);

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

		modifyNodeSelectEdge = new JButton("Modify Node");
		modifyNodeSelectEdge.addMouseListener(this);
		this.add(modifyNodeSelectEdge);

		cargarInfoInComoboBox();
	}

	public void resizedComponents(int width, int height) {
		this.setBounds(0, 0, width, height);
		jspEdges.setBounds(0, 0, width - 200, height + 57);

		labelAddEdge.setBounds(jspEdges.getWidth(), 1, 200, 20);

		labelId.setBounds(20 + jspEdges.getWidth(), 10 + labelAddEdge.getHeight(), 20, 20);
		textIdEdge.setBounds(labelId.getWidth() + labelId.getX() + 10, 10 + labelAddEdge.getHeight(), 130, 18);

		labelLabel.setBounds(jspEdges.getWidth() + 10, 10 + labelId.getY() + labelId.getHeight(), 40, 20);
		textLabel.setBounds(labelLabel.getWidth() + labelLabel.getX(), 10 + textIdEdge.getHeight() + textIdEdge.getY(),
				130, 18);
		labelProperties.setBounds(jspEdges.getWidth() + 10, 10 + labelLabel.getY() + labelLabel.getHeight(), 60, 20);

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
		nodesComboBox.setBounds(jspEdges.getWidth() + 80, labelSelectEdge.getY() + labelSelectEdge.getHeight() + 5, 100,
				20);

		labelPropertiesSelect.setBounds(jspEdges.getWidth() + 10,
				labelComboBoxSelectEdge.getY() + labelPropertiesSelect.getHeight() + 10, 70, 20);
		jspPropertiesSelectEdge.setBounds(jspEdges.getWidth() + 10,
				labelPropertiesSelect.getY() + labelPropertiesSelect.getHeight(), 180, 100);

		addPropertySelectEdge.setBounds(jspEdges.getWidth() + 10,
				jspPropertiesSelectEdge.getY() + jspPropertiesSelectEdge.getHeight() + 10, 35, 20);
		removePropertySelectEdge.setBounds(addPropertySelectEdge.getX() + addPropertySelectEdge.getWidth() + 5,
				jspPropertiesSelectEdge.getY() + jspPropertiesSelectEdge.getHeight() + 10, 35, 20);
		modifyNodeSelectEdge.setBounds(jspEdges.getWidth() + 90,
				jspPropertiesSelectEdge.getY() + jspPropertiesSelectEdge.getHeight() + 10, 100, 20);

	}

	private void cargarInfoInComoboBox() {
		String[] edges = new String[graph.getEdgeCount() + 1];
		edges[0] = " ";
		int i = 1;
		for (Edge e : graph.getEdgeSet()) {
			edges[i++] = e.getId() + "";
		}
		selectNodeDefautlComboBoxModel = new DefaultComboBoxModel<>(edges);
		nodesComboBox.setModel(selectNodeDefautlComboBoxModel);
		// removeNodeDefautlComboBoxModel = new DefaultComboBoxModel<>(nodes);
		// removeComboBox.setModel(removeNodeDefautlComboBoxModel);
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
			// cargarInfoInComoboBox();
			t++;
		table.getColumnModel().getColumn(4).setPreferredWidth(minWidthColumProperties);
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

	// Mouse Listener
	@Override
	public void mouseClicked(MouseEvent arg0) {
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
		// TODO Auto-generated method stub

	}
}
