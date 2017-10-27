package co.edu.poligran.Panels;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import co.edu.poligran.Algorithms.ArticulationPoints;
import co.edu.poligran.Lists.ListPropertiesAlgorithms;
import co.edu.poligran.Lists.ListPropertiesSelectBy;

public class PanelGraph extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private ViewPanel view;
	private Viewer viewer;
	private final Border border = LineBorder.createGrayLineBorder();
	private JTextArea contextGraphArea;
	private JLabel labelContext, labelAlgorithms, labelSelectBy;
	private JList<String> listAlgorithms,listSelectBy;
	private JScrollPane jspAlgorithms,jspSelectBy;
	private Graph graph;
	private PanelNodes panelNodes;

	public PanelGraph(Graph graph,PanelNodes panelNodes) throws IOException {
		this.panelNodes=panelNodes;
		this.graph = graph;
		this.setLayout(null);
		// Grphic Graph
		viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();
		viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
		view = viewer.addDefaultView(false);
		view.setBorder(border);
		this.add(view);

		// colum

		// Label del contex Graph
		labelContext = new JLabel("Graph Context", SwingConstants.CENTER);
		labelContext.setBorder(border);
		this.add(labelContext);

		// TextArea del contex Graph
		contextGraphArea = new JTextArea();
		contextGraphArea.setBorder(border);
		contextGraphArea.setEditable(false);
		this.add(contextGraphArea);

		// Label Algorithms
		labelAlgorithms = new JLabel("Algorithms", JLabel.CENTER);
		labelAlgorithms.setBorder(border);
		this.add(labelAlgorithms);

		// List Algorithms
		listAlgorithms = new ListPropertiesAlgorithms();
		listAlgorithms.addMouseListener(this);
		jspAlgorithms = new JScrollPane(listAlgorithms);
		jspAlgorithms.setBorder(border);
		this.add(jspAlgorithms);

		// Label selectBy
		labelSelectBy = new JLabel("Select By",JLabel.CENTER);
		labelSelectBy.setBorder(border);
		this.add(labelSelectBy);
		

		//lista selectBy
		listSelectBy = new ListPropertiesSelectBy();
		listSelectBy.addMouseListener(this);
		jspSelectBy = new JScrollPane(listSelectBy);
		jspSelectBy.setBorder(border);
		this.add(jspSelectBy);
	}

	public void rezisedComponents(int width, int height) {
		int widthColum;
		widthColum = 236;
		// graph
		view.setBounds(0, 0, (int) (width-252), (int) (height - 90));
		// label Contex
		labelContext.setBounds(view.getWidth(), 0, widthColum, (int) (height * 0.025));
		// contex Graph
		contextGraphArea.setBounds(view.getWidth(), labelContext.getHeight(), widthColum, (int) (height * 0.2));
		// label Algorithms
		labelAlgorithms.setBounds(view.getWidth(), contextGraphArea.getHeight() + contextGraphArea.getY(), widthColum,
				(int) (height * 0.025));
		// jspAlgorithms JScrollPane
		jspAlgorithms.setBounds(view.getWidth(), labelAlgorithms.getY() + labelAlgorithms.getHeight(), widthColum,
				(int) (height * 0.3));
		// selectBy label
		labelSelectBy.setBounds(view.getWidth(), jspAlgorithms.getY() + jspAlgorithms.getHeight(), widthColum,
				(int) (height * 0.025));
		//jspSelectBy ScrollPane
		jspSelectBy.setBounds(view.getWidth(), labelSelectBy.getY() + labelSelectBy.getHeight(), widthColum,
				(int) (height * 0.2));
	}

	// MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(listAlgorithms) && e.getClickCount() == 2) {
			// Aca se ejecutan los algoritmos
			int index = listAlgorithms.getSelectedIndex();
			if(index == 0) {
				ArticulationPoints ap = new ArticulationPoints(graph);
				ap.compute();
			}
			panelNodes.ProcessNodes();
		}
		if(e.getSource().equals(listSelectBy)&&e.getClickCount()==2) {
			//Aca se selecionan por nodos
			System.out.println(listSelectBy.getSelectedValue());
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
