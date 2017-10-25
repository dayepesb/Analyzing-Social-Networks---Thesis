package co.edu.poligran.Test;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

public class TestMatrix {
	private static PanelMatrix panelMatrix;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 600);

		// Graph
		Graph graph = new MultiGraph("David");
		for (int i = 0; i < 50; i++) {
			graph.addNode(i + "");
		}
		panelMatrix = new PanelMatrix(graph, frame.getWidth(), frame.getHeight());
		panelMatrix.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		panelMatrix.setName("matrix");
		frame.setLayout(null);
		frame.add(panelMatrix);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {
			}

			@Override
			public void componentResized(ComponentEvent e) {
				// panelMatrix = new PanelMatrix(graph, frame.getWidth(), frame.getHeight());
				// frame.add(panelMatrix);
				panelMatrix.resizedComponents(frame.getWidth() - 100, frame.getHeight() - 100);
				frame.add(panelMatrix);
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
	}

	static class PanelMatrix extends JPanel {
		public int nodes;
		public JScrollPane jsp;

		public PanelMatrix(Graph graph, int width, int height) {
			this.setLayout(null);
			this.add(new JButton());
			nodes = graph.getNodeCount();
			DefaultTableModel dtm = new DefaultTableModel(nodes, nodes) {
				@Override
				public boolean isCellEditable(int row, int column) {
					if (0 == column)
						return false;
					return super.isCellEditable(row, column);
				}
			};

			// indentificadores para la primera fila osea los indices
			String[] colums = new String[nodes + 1];
			colums[0] = "INDEX";
			for (int i = 0; i < nodes; i++) {
				colums[i + 1] = i + "";
			}
			dtm.setColumnIdentifiers(colums);

			// identificadores para la columna 1 osea los indices
			for (int i = 0; i < nodes; i++) {
				dtm.setValueAt(i, i, 0);
			}

			JTable table = new JTable(dtm) {
				@Override
				public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
					if (columnIndex == 0)
						super.changeSelection(rowIndex, columnIndex + 1, toggle, extend);
					else
						super.changeSelection(rowIndex, columnIndex, toggle, extend);
				}
			};

			// Se cambia el tamaño de la columnas por defecto
			table.getColumnModel().getColumn(0).setCellRenderer(table.getTableHeader().getDefaultRenderer());
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			for (int i = 1; i <= nodes; i++) {
				table.getColumnModel().getColumn(i).setPreferredWidth(50);
			}

			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jsp = new JScrollPane(table);

			this.setBounds(0, 0, width, height);
			this.add(jsp);
		}

		public void resizedComponents(int width, int height) {
			this.setBounds(0, 0, width, height);
			jsp.setBounds(0, 0, width, height);
		}
	}
}
