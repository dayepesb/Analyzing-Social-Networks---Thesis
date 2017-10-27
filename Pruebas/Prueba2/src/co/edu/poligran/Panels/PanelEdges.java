package co.edu.poligran.Panels;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
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

public class PanelEdges extends JPanel implements MouseListener {
	private int t, minWidthColumProperties;
	private Graph graph;
	private DefaultTableModel defaultTableModel;
	private DefaultListModel<String> defaultListModel;
	private JTable table;
	private JScrollPane jspEdges;
	private final Border border = LineBorder.createGrayLineBorder();
	private DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();

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
		updateEdges();

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table.setBorder(border);
		jspEdges = new JScrollPane(table);
		jspEdges.setBorder(border);
		this.add(jspEdges);
	}

	public void resizedComponents(int width, int height) {
		this.setBounds(0, 0, width, height);
		jspEdges.setBounds(0, 0, width - 200, height + 57);

	}

	private void updateEdges() {
		int i = 0;
		updateTable();
		for (Edge e : graph.getEdgeSet()) {
			int j = 0;
			defaultTableModel.setValueAt(e.getId(), i, j++);
			defaultTableModel.setValueAt(e.getNode0().getId(), i, j++);
			defaultTableModel.setValueAt(e.getNode1().getId(), i, j++);
			if (e.hasAttribute("-attribute-width")) {
				System.out.println("sdfgh");
				defaultTableModel.setValueAt(e.getAttribute("-attribute-width"), i, j++);
			} else {
				defaultTableModel.setValueAt(1.0+"", i, j++);
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
		defaultTableModel = new DefaultTableModel(graph.getNodeCount(), 4) {
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
}
