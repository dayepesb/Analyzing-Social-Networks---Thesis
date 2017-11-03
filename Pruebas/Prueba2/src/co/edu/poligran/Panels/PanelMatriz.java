package co.edu.poligran.Panels;

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

public class PanelMatriz extends JPanel {
	private Graph graph;
	private JScrollPane jspMatriz;
	private static final String INF = "\u221E";
	private DefaultTableModel dtm;
	private JTable table;
	private String nodes[];
	private final Border border = LineBorder.createGrayLineBorder();

	public PanelMatriz(Graph graph) {
		this.graph = graph;
		this.setLayout(null);
		initTable();
		updateValues();
		jspMatriz = new JScrollPane(table);
		jspMatriz.setBorder(border);
		this.add(jspMatriz);
	}

	public void updateValues() {
		int i = 1;
		nodes = new String[graph.getNodeCount() + 1];
		nodes[0] = "Nodes";
		for (Node n : graph) {
			nodes[i] = n.getId();
			i++;
		}
		dtm = new DefaultTableModel(nodes.length-1, nodes.length) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (0 == column)
					return false;
				return super.isCellEditable(row, column);
			}
		};
		dtm.setColumnIdentifiers(nodes);
		for (i = 1; i < nodes.length; i++) {
			dtm.setValueAt(nodes[i], i - 1, 0);
		}
		for (i = 1; i < nodes.length; i++) {
			Node a = graph.getNode(nodes[i]);
			for (int j = 1; j < nodes.length; j++) {
				Node b = graph.getNode(nodes[j]);
				if (a.hasEdgeBetween(b)) {
					Edge edge = a.getEdgeBetween(b);
					boolean bo = false;
					for (String s : edge.getAttributeKeySet()) {
						if(s.equals("-attribute-width")) {
							dtm.setValueAt(edge.getAttribute(s), i - 1, j);
							bo = true;
							break;
						}
					}
					if(!bo) {
						dtm.setValueAt(1.0+"", i - 1, j);
					}
				} else {
					dtm.setValueAt(INF, i - 1, j);
				}
			}
		}
		table.setModel(dtm);
		table.getColumnModel().getColumn(0).setCellRenderer(table.getTableHeader().getDefaultRenderer());
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		for (i = 1; i < nodes.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(50);
			table.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}

	}

	public void resizedComponents(int width, int height) {
		this.setBounds(0, 0, width, height);
		jspMatriz.setBounds(0, 0, width, height + 58);
	}

	public void initTable() {
		table = new JTable() {
			@Override
			public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
				if (columnIndex == 0)
					super.changeSelection(rowIndex, columnIndex + 1, toggle, extend);
				else
					super.changeSelection(rowIndex, columnIndex, toggle, extend);
			}
		};
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		table.setBorder(border);
		table.getColumnModel().setColumnMargin(2);
	}
}

