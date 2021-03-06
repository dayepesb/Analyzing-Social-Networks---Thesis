package co.edu.poligran.Frame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.ViewerPipe;

import co.edu.poligran.Listener.ListenerMouseInGraph;
import co.edu.poligran.Panels.PanelEdges;
import co.edu.poligran.Panels.PanelGraph;
import co.edu.poligran.Panels.PanelGraphics;
import co.edu.poligran.Panels.PanelMatriz;
import co.edu.poligran.Panels.PanelNodes;
import co.edu.poligran.Reader.GraphReaderJSON;
import co.edu.poligran.Writer.GraphWriterJSON;

public class PrincipalFrame implements Runnable, ActionListener, ComponentListener {
	private final Border border = LineBorder.createGrayLineBorder();
	private Graph graph;
	private JFrame windows;
	private Dimension dim;
	private JMenuBar mb;
	private JMenu archive, importGraph, exportGraph;
	private JMenuItem close, jsonImport, jsonExport;
	private PanelGraph panelGraph;
	private PanelNodes panelNodes;
	private PanelEdges panelEdges;
	private PanelMatriz panelMatriz;
	private PanelGraphics panelGraphics;
	private final Image icono = Toolkit.getDefaultToolkit()
			.getImage("src/co/edu/poligran/Images/LogoPoliGraphApplicationExecutable.png");
	private ProgressBarFrame pbf;
	private boolean loop = true;
	public String background, nodes, edges;
	private ViewerPipe fromViewer;

	public PrincipalFrame(ProgressBarFrame pbf) {
		this.pbf = pbf;
		background = "#FFF";
		nodes = "#000";
		edges = "#000";
		run();
	}

	@Override
	public void run() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}
		pbf.setProgressBar(2);
		windows = new JFrame("Analizing Social Network");
		windows.setIconImage(icono);
		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		windows.setSize(dim.width * 7 / 10, dim.height * 8 / 10);
		windows.setMinimumSize(new Dimension(956, 614));
		windows.addComponentListener(this);
		pbf.setProgressBar(7);

		// MenuBar
		mb = new JMenuBar();
		archive = new JMenu("Archive");
		mb.add(archive);
		windows.setJMenuBar(mb);
		pbf.setProgressBar(4);

		archive.add(new AbstractAction("Print") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				PrinterJob printJob = PrinterJob.getPrinterJob();
				if (printJob.printDialog()) {
					try {
						printJob.print();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		importGraph = new JMenu("Import");
		jsonImport = new JMenuItem("Json File");
		jsonImport.addActionListener(this);
		importGraph.add(jsonImport);
		archive.add(importGraph);

		exportGraph = new JMenu("Export");
		jsonExport = new JMenuItem("Json File");
		jsonExport.addActionListener(this);
		exportGraph.add(jsonExport);
		archive.add(exportGraph);

		close = new JMenuItem("Close");
		close.addActionListener(this);
		archive.add(close);
		pbf.setProgressBar(4);
		Runnable r = new Runnable() {

			@Override
			public void run() {
				while (loop) {
					panelGraph.getFromViewer().pump();
				}
			}
		};
		Thread t = new Thread(r);

		// create graph
		graph = new SingleGraph("Random");
		for (Node n : graph)
			n.addAttribute("label", n.getId());
		for (Edge e : graph.getEachEdge()) {
			e.addAttribute("label", "" + (int) e.getNumber("length"));
		}
		graph.addAttribute("ui.stylesheet", "edge.label { color: " + edges + "; }");

		for (Node n : graph) {
			n.addAttribute("-attribute-age", "" + (int) (Math.random() * 26));
			n.addAttribute("-attribute-country", "Colombia");
		}
		for (Edge edge : graph.getEdgeSet()) {
			edge.addAttribute("-attribute-country", "4567");
		}
		pbf.setProgressBar(7);

		// Paint Graph
		graph.addAttribute("ui.stylesheet", "graph { fill-color: #000; }");
		for (Node node : graph) {
			node.setAttribute("ui.style", "fill-color:BLUE;");
		}
		for (Edge edge : graph.getEdgeSet()) {
			edge.setAttribute("ui.style", "fill-color:BLUE;");
		}

		// Panel Graphics
		panelGraphics = new PanelGraphics(graph);
		// Panel matriz
		panelMatriz = new PanelMatriz(graph);
		pbf.setProgressBar(10);
		// Panel Edges
		panelEdges = new PanelEdges(graph, panelMatriz);
		pbf.setProgressBar(10);
		// Panel nodes
		try {
			panelNodes = new PanelNodes(graph, panelEdges, panelMatriz);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pbf.setProgressBar(10);
		// panel Graph
		try {
			panelGraph = new PanelGraph(graph, panelNodes, panelEdges);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pbf.setProgressBar(10);

		panelNodes.setPanelGraph(panelGraph);
		panelEdges.setPanelGraph(panelGraph);
		// create view graph
		addTabbedPane(graph);

		panelGraph.setPrincipalFrame(this);
		windows.pack();
		windows.setLocationRelativeTo(null);
		windows.setVisible(true);

		t.start();
	}

	private void addTabbedPane(Graph graph) {

		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Graph", panelGraph);
		tabbedPane.addTab("Nodes", panelNodes);
		tabbedPane.addTab("Edges", panelEdges);
		tabbedPane.addTab("Matrix", panelMatriz);
		tabbedPane.addTab("Statics", panelGraphics);
		windows.add(tabbedPane);

	}

	public void resizedConponents() {
		panelGraph.rezisedComponents(windows.getWidth(), windows.getHeight());
		panelNodes.resizedComponents(windows.getWidth() - 15, windows.getHeight() - 150);
		panelEdges.resizedComponents(windows.getWidth() - 15, windows.getHeight() - 150);
		panelMatriz.resizedComponents(windows.getWidth() - 15, windows.getHeight() - 150);
		panelGraphics.resizedComponents(windows.getWidth() - 15, windows.getHeight() - 150);
	}

	public void setGraph(Graph graph) throws IOException {
		this.graph = graph;
		// pinta el grafo
		panelGraph.setGraph(graph);
		panelGraph.setContext();
		panelNodes.setGraph(graph);
		panelNodes.ProcessNodes();
		panelNodes.updateListSelectNode();
		panelEdges.setGraph(graph);
		panelEdges.processEdges();
		panelEdges.updateListSelectNode();
		panelEdges.updateInfoInComboBoxNodes();
		panelMatriz.setGraph(graph);
		panelMatriz.updateValues();
		panelGraphics.setGraph(graph);

		// actualiza Listener
		fromViewer = panelGraph.getViewer().newViewerPipe();
		ListenerMouseInGraph listenerGraph = new ListenerMouseInGraph(graph);
		fromViewer.addViewerListener(listenerGraph);
		panelGraph.setFromViewer(fromViewer);

		HashSet<String> set = new HashSet<>();
		for (Node node : graph) {
			if (node.getDegree() == 0)
				set.add(node.getId());
		}
		for (String s : set) {
			graph.removeNode(s);
		}
		/*
		String Country[] = new String[] { "Mexico", "Per�", "Colombia", "Chile", "Argentina", "Brasil", "Bolivia" };
		Random r = new Random();
		for (Node n : graph) {
			int sa = (int) (Math.random() * 7);
			System.out.println(sa);
			n.addAttribute("-attribute-country", Country[sa]);
			while (true) {
				int s = r.nextInt(30);
				if (s > 17) {
					n.addAttribute("-attribute-age", s + "");
					break;
				}
			}
			n.addAttribute("-attribute-gender",Math.random()*2>=1.5?"MALE":"FEMALE");
		}
		*/
		this.resizedConponents();
		panelGraphics.updateComboBoxModel();
		panelGraph.setComponentsPrincipalFrame();
	}

	// ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(close)) {
			windows.dispose();
			System.exit(0);
		}
		if (e.getSource().equals(jsonImport)) {
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Import Graph to PoliGraph");
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.json", "json");
			fc.setFileFilter(filtro);
			int seleccion = fc.showOpenDialog(windows);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File fichero = fc.getSelectedFile();
				GraphReaderJSON reader = new GraphReaderJSON(fichero, this);
				try {
					setGraph(reader.getGraph());
				} catch (IOException e1) {
				}
			}

		}
		if (e.getSource().equals(jsonExport)) {
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Export Graph from PoliGraph");
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.json", "json");
			fc.setFileFilter(filtro);
			int userSelection = fc.showSaveDialog(windows);
			if (userSelection == fc.APPROVE_OPTION) {
				File fileToSave = fc.getSelectedFile();
				GraphWriterJSON g = new GraphWriterJSON(graph, fileToSave.getAbsolutePath());
			}
		}
	}

	// ComponentListener
	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		resizedConponents();
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}