package co.edu.poligran.Panels;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;

import co.edu.poligran.Algorithms.ArticulationPoints;
import co.edu.poligran.Algorithms.BetweenessPoli;
import co.edu.poligran.Algorithms.ClosenessCentrality;
import co.edu.poligran.Algorithms.DegreeCentrality;
import co.edu.poligran.Algorithms.DijkstraPoli;
import co.edu.poligran.Algorithms.PrimPoli;
import co.edu.poligran.Algorithms.Tarjan;
import co.edu.poligran.Frame.PrincipalFrame;
import co.edu.poligran.Listener.ListenerMouseInGraph;
import co.edu.poligran.Lists.ListPropertiesAlgorithms;
import co.edu.poligran.Lists.ListPropertiesSelectBy;
import co.edu.poligran.PopUp.PopUpDijkstra;

public class PanelGraph extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private ViewPanel view;
	private Viewer viewer;
	private ViewerPipe fromViewer;
	private final Border border = LineBorder.createGrayLineBorder();
	private JTextArea contextGraphArea;
	private JLabel labelContext, labelAlgorithms, labelSelectBy;
	private JList<String> listAlgorithms, listSelectBy;
	private JScrollPane jspAlgorithms, jspSelectBy;
	private Graph graph;
	private PanelNodes panelNodes;
	private PanelEdges panelEdges;
	private PrincipalFrame principalFrame;

	public PanelGraph(Graph graph, PanelNodes panelNodes, PanelEdges panelEdges) throws IOException {
		this.panelNodes = panelNodes;
		this.panelEdges = panelEdges;
		this.graph = graph;

		this.setLayout(null);
		for (Node node : this.graph) {
			node.setAttribute("ui.style", "fill-color:#fff;");
		}
		for (Edge edge : this.graph.getEdgeSet()) {
			edge.setAttribute("ui.style", "fill-color:#fff;");
		}
		viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();
		viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
		view = viewer.addDefaultView(false);
		view.setBorder(border);
		this.add(view);

		fromViewer = viewer.newViewerPipe();
		ListenerMouseInGraph t = new ListenerMouseInGraph();
		fromViewer.addViewerListener(t);

		// Label del contex Graph
		labelContext = new JLabel("Graph Context", SwingConstants.CENTER);
		labelContext.setBorder(border);
		this.add(labelContext);

		// TextArea del contex Graph
		contextGraphArea = new JTextArea();
		contextGraphArea.setBorder(border);
		contextGraphArea.setEditable(false);
		setContext();
		this.add(contextGraphArea);

		// Label Algorithms
		labelAlgorithms = new JLabel("Algorithms", JLabel.CENTER);
		labelAlgorithms.setBorder(border);
		this.add(labelAlgorithms);

		// List Algoritshms
		listAlgorithms = new ListPropertiesAlgorithms();
		listAlgorithms.addMouseListener(this);
		jspAlgorithms = new JScrollPane(listAlgorithms);
		jspAlgorithms.setBorder(border);
		this.add(jspAlgorithms);

		// Label selectBy
		labelSelectBy = new JLabel("Select By", JLabel.CENTER);
		labelSelectBy.setBorder(border);
		this.add(labelSelectBy);

		// lista selectBy
		listSelectBy = new ListPropertiesSelectBy(graph);
		listSelectBy.addMouseListener(this);
		jspSelectBy = new JScrollPane(listSelectBy);
		jspSelectBy.setBorder(border);
		this.add(jspSelectBy);
	}

	public void setGraph(Graph graph) {
		this.remove(view);
		this.graph = graph;
		// Paint Graph
		this.graph.addAttribute("ui.stylesheet", "graph { fill-color: BLACK; }");
		for (Node node : this.graph) {
			node.setAttribute("ui.style", "fill-color:#fff;");
		}
		for (Edge edge : this.graph.getEdgeSet()) {
			edge.setAttribute("ui.style", "fill-color:#fff;");
		}
		viewer = new Viewer(this.graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();
		viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
		view = viewer.addDefaultView(false);
		view.setBorder(border);
		this.add(view);
	}

	public void rezisedComponents(int width, int height) {
		int widthColum;
		widthColum = 236;
		// graph
		view.setBounds(0, 0, (int) (width - 252), (int) (height - 90));
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
		// jspSelectBy ScrollPane
		jspSelectBy.setBounds(view.getWidth(), labelSelectBy.getY() + labelSelectBy.getHeight(), widthColum,
				(int) (height * 0.2));
	}

	// MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(listAlgorithms) && e.getClickCount() == 2) {
			// Aca se ejecutan los algoritmos
			String select = listAlgorithms.getSelectedValue();
			if (select.equals("Articulation Points")) {
				ArticulationPoints ap = new ArticulationPoints(graph);
				ap.compute();
			} else if (select.equals("Dijkstra")) {
				DijkstraPoli dij = new DijkstraPoli(graph);
				try {
					String url = ".../Images/ImgaeMensajeDialog.png";
					PopUpDijkstra pop = new PopUpDijkstra();
					String[] p = pop.PopUpDijkstra(url, "Selected Nodes", JOptionPane.CANCEL_OPTION,
							JOptionPane.YES_NO_OPTION, graph);
					if (p != null) {
						dij.compute(p[0], p[1]);
					}
				} catch (Exception ex) {
					// TODO: handle exception
				}

			} else if (select.equals("Betweenness Centrality")) {
				BetweenessPoli bi = new BetweenessPoli(graph);
				bi.compute();
			} else if (select.equals("Tarjan")) {
				Tarjan tj = new Tarjan(graph);
				try {
					tj.compute();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (select.equals("Prim (Minimum Spanning Tree)")) {
				PrimPoli prim = new PrimPoli(graph);
				prim.compute();
			} else if (select.equals("Closeness And Remoteness Centrality")) {
				ClosenessCentrality clos = new ClosenessCentrality(graph);
			} else if (select.equals("Degree Centrality")) {
				DegreeCentrality dg = new DegreeCentrality(graph);
				int min = dg.getMin();
				int max = (dg.getMax());
				HashMap<Integer, Integer> degree = dg.getTotalDegree();
				for (Entry<Integer, Integer> deg : degree.entrySet()) {
					int size = deg.getValue() * 30 / max;
					graph.getNode(deg.getKey()).setAttribute("ui.style", "size:" + (size < 5 ? 5 : size) + "px;");
					graph.getNode(deg.getKey()).setAttribute("ui.style", "stroke-color:#000;");
				}
			}
			panelNodes.ProcessNodes();
			panelEdges.processEdges();
		}
		if (e.getSource().equals(listSelectBy) && e.getClickCount() == 2) {
			System.out.println(listSelectBy.getSelectedValue());
		}
	}

	public void setContext() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("src/co/edu/poligran/Files/ContextGraph.txt")));
		String context = "";
		Toolkit tol = new Toolkit();
		context += br.readLine() + " " + graph.getNodeCount();
		context += "\n" + br.readLine() + " " + graph.getEdgeCount();
		double density = tol.density(graph);
		context += "\n" + br.readLine() + " " + String.format("%.4f", density);
		context += "\n" + br.readLine() + " " +tol.diameter(graph);
		contextGraphArea.setText(context);
	}

	public ViewerPipe getFromViewer() {
		return fromViewer;
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

	public void setComponentsPrincipalFrame() {
		try {
			this.setContext();
			this.remove(jspSelectBy);
			listSelectBy = new ListPropertiesSelectBy(graph);
			listSelectBy.addMouseListener(this);
			jspSelectBy = new JScrollPane(listSelectBy);
			jspSelectBy.setBorder(border);
			this.add(jspSelectBy);
			principalFrame.resizedConponents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPrincipalFrame(PrincipalFrame pf) {
		this.principalFrame = pf;
	}
}