package co.edu.poligran.Frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.print.PrinterJob;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import co.edu.poligran.Panels.PanelGraph;

public class PrincipalFrame implements Runnable, ActionListener, ComponentListener {
	private final Border border = LineBorder.createGrayLineBorder();
	private JFrame windows;
	private Dimension dim;
	private JMenuBar mb;
	private JMenu archive;
	private JMenuItem close;
	private PanelGraph panelGraph;

	public static void main(String[] args) {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		SwingUtilities.invokeLater(new PrincipalFrame());
	}

	@Override
	public void run() {
		windows = new JFrame("Analizing Social Network");
		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		windows.setSize(dim.width * 7 / 10, dim.height * 8 / 10);
		windows.setMinimumSize(new Dimension(956, 614));
		windows.setLocation(dim.width / 2 - windows.getSize().width / 2, dim.height / 2 - windows.getSize().height / 2);
		windows.addComponentListener(this);

		// MenuBar
		mb = new JMenuBar();
		archive = new JMenu("Archive");
		mb.add(archive);
		windows.setJMenuBar(mb);

		archive.add(new AbstractAction("Print") {
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
		Graph graph = new SingleGraph("Random");
		Generator gen = new RandomGenerator(2);
		gen.addSink(graph);
		gen.begin();
		for (int i = 0; i < 100; i++) {
			gen.nextEvents();
		}
		gen.end();

		// panel Graph
		try {
			panelGraph = new PanelGraph(graph, windows.getWidth(), windows.getHeight());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// create view graph
		addTabbedPane(graph);

		windows.pack();
		windows.setVisible(true);
	}

	private void addTabbedPane(Graph graph) {

		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Graph", panelGraph);
		tabbedPane.addTab("Edges", new JPanel());
		tabbedPane.addTab("Nodes", new JPanel());
		tabbedPane.addTab("Matrix", new JPanel());
		windows.add(tabbedPane);

	}

	private void resizedConponents() {
		panelGraph.rezisedComponents(windows.getWidth(), windows.getHeight());
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
