package co.edu.poligran.Frame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.print.PrinterJob;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import com.sun.prism.paint.Color;

import co.edu.poligran.Panels.PanelGraph;
import co.edu.poligran.Panels.PanelNodes;

public class PrincipalFrame implements Runnable, ActionListener, ComponentListener {
	private final Border border = LineBorder.createGrayLineBorder();
	private Graph graph;
	private JFrame windows;
	private Dimension dim;
	private JMenuBar mb;
	private JMenu archive;
	private JMenuItem close;
	private PanelGraph panelGraph;
	private PanelNodes panelNodes;
	private final Image icono = Toolkit.getDefaultToolkit()
			.getImage("src/co/edu/poligran/Images/LogoPoliGraphApplication.png");

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
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		windows = new JFrame("Analizing Social Network");
		windows.setIconImage(icono);
		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		windows.setSize(dim.width * 7 / 10, dim.height * 8 / 10);
		windows.setMinimumSize(new Dimension(956, 614));
		windows.addComponentListener(this);

		// MenuBar
		mb = new JMenuBar();
		archive = new JMenu("Archive");
		mb.add(archive);
		windows.setJMenuBar(mb);

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
		close = new JMenuItem("Close");
		close.addActionListener(this);
		archive.add(close);

		// create graph
		graph = new SingleGraph("Random");
		Generator gen = new RandomGenerator(1);
		gen.addSink(graph);
		gen.begin();
		for (int i = 0; i < 10; i++) {
			gen.nextEvents();
		}
		gen.end();
		for (Node n : graph) {
			n.addAttribute("-attribute-Age", "13");
			n.addAttribute("-attribute-Country", "Colombia");
		}

		// panel Graph
		try {
			panelGraph = new PanelGraph(graph, windows.getWidth(), windows.getHeight());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Panel nodes
		try {
			panelNodes = new PanelNodes(graph, windows.getWidth(), windows.getHeight());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// create view graph
		addTabbedPane(graph);

		windows.pack();
		windows.setLocationRelativeTo(null);
		windows.setVisible(true);
	}

	private void addTabbedPane(Graph graph) {

		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Graph", panelGraph);
		tabbedPane.addTab("Nodes", panelNodes);
		tabbedPane.addTab("Edges", new JPanel());
		tabbedPane.addTab("Matrix", new JPanel());
		windows.add(tabbedPane);

	}

	private void resizedConponents() {
		panelGraph.rezisedComponents(windows.getWidth(), windows.getHeight());
		panelNodes.resizedComponents(windows.getWidth() - 15, windows.getHeight() - 150);
	}

	// ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(close)) {
			windows.dispose();
			System.exit(0);
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