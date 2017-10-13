package co.edu.poli.Layots;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import co.edu.poli.Lists.ListProperties;;

public class FramePrincipal extends JFrame implements MouseListener, ActionListener, ComponentListener, KeyListener {

	private static final long serialVersionUID = -8099070856715541164L;
	private final Border border = LineBorder.createGrayLineBorder();
	private JMenuBar mb;
	private JMenu archive;
	private JMenuItem close;
	private Dimension dim;
	private JLabel labelAlgorithms, labelContex;
	private JList<String> lista;
	private JPanel panel;
	private JScrollPane barraDesplazamientoAlgorithms, barraDesplazamientoContex;
	private JTextArea contextGraph;
	private GraphEditor graphGraphics;

	private int nodes, edges;

	public FramePrincipal() throws IOException {
		// Creación de la ventana
		super("Analizing Social Network");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width * 7 / 10, dim.height * 8 / 10);
		setMinimumSize(new Dimension(956, 614));
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
		setLayout(null);
		this.addComponentListener(this);
		this.addKeyListener(this);
		this.addMouseListener(this);

		// MenuBar
		graphGraphics = new GraphEditor();
		graphGraphics.addMouseListener(this);
		mb = new JMenuBar();
		archive = new JMenu("Archive");
		mb.add(archive);
		setJMenuBar(mb);
		archive.add(new AbstractAction("Make Image") {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int option = chooser.showSaveDialog(graphGraphics);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					graphGraphics.writeJPEGImage(file);
				}
			}
		});

		archive.add(new AbstractAction("Print") {
			public void actionPerformed(ActionEvent e) {
				PrinterJob printJob = PrinterJob.getPrinterJob();
				printJob.setPrintable(graphGraphics);
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

		// Creación del panel, que contendra JList
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 500, 500);
		panel.setBorder(border);

		// Se agrega un label para el contexto del grafo
		labelContex = new JLabel("Contex", SwingConstants.CENTER);
		labelContex.setBorder(border);
		panel.add(labelContex);

		// Se agrega en text Area
		contextGraph = new JTextArea();
		contextGraph.setBorder(border);
		contextGraph.setEditable(false);

		// scrollPaneTextArea
		barraDesplazamientoContex = new JScrollPane(contextGraph);
		barraDesplazamientoContex.setBorder(border);
		panel.add(barraDesplazamientoContex);

		// Se agrega una etiqueta de label de los algoritmos
		labelAlgorithms = new JLabel("Algorithms", SwingConstants.CENTER);
		labelAlgorithms.setBorder(border);
		panel.add(labelAlgorithms);

		// creación del objeto lista
		lista = new ListProperties();
		lista.addMouseListener(this);

		// aquí se crea el objeto, es decir la barra de desplazamiento
		barraDesplazamientoAlgorithms = new JScrollPane(lista);
		barraDesplazamientoAlgorithms.setBorder(border);
		panel.add(barraDesplazamientoAlgorithms);

		// graphGraphics.vv.getRenderContext().setVertexFillPaintTransformer(arg0);

		panel.add(graphGraphics);
		getContentPane().add(panel);
		setVisible(true);
	}

	private void resizedComponents() {

		// Panel
		int xPnael = 0;
		int yPanel = 0;
		int widthPanel = this.getWidth();
		int heigtPanel = this.getHeight();
		panel.setBounds(xPnael, yPanel, widthPanel, heigtPanel);

		// Graphics
		int xGraphics = 0;
		int yGraphics = 0;
		int widthGraphics = (int) (panel.getWidth() * 0.8);
		int heigthGraphics = (int) (panel.getHeight() * 0.9);
		graphGraphics.setBounds(xGraphics, yGraphics, widthGraphics, heigthGraphics);

		// Colums
		int widthColum = (int) (panel.getWidth() * (2) / 10);
		int longLabels = (int) (panel.getHeight() * 0.7 / 20);
		int xColum = widthGraphics;

		// label Contex
		labelContex.setText("Contex");
		labelContex.setFont(new Font("TIMES NEW ROMAN", 0, 15));
		int yLabelContex = 0;
		labelContex.setBounds(xColum, yLabelContex, widthColum, longLabels);

		// TextArea
		// updateGraphValues();
		contextGraph.setText(
				String.format("  Nodes :          %d\n  Edges :          %d\n  Type Graph : %s", nodes, edges, "None"));

		// Scroll Bar Contex
		int longContex = panel.getHeight() / 7;
		int yContex = yLabelContex + longLabels;
		barraDesplazamientoContex.setBounds(xColum, yContex, widthColum, longContex);

		// label Algorithms
		labelAlgorithms.setText("Algorithms");
		labelAlgorithms.setFont(new Font("TIMES NEW ROMAN", 0, 15));
		int yLabelAlgorithms = yContex + longContex;
		labelAlgorithms.setBounds(xColum, yLabelAlgorithms, widthColum, longLabels);

		// list font
		lista.setFont(new Font("TIMES NEW ROMAN", 0, 15));

		// list
		int longList = this.getHeight() * 8 / 30;
		int yList = yLabelAlgorithms + longLabels;
		barraDesplazamientoAlgorithms.setBounds(xColum, yList, widthColum, longList);

	}

	private void updateGraphValues() {
		// Aca se van a cargar los datos de numero de nodos actuales y numero de
		// adyacencias actuales
		nodes = graphGraphics.graph.getVertexCount();
		edges = graphGraphics.graph.getEdgeCount();
		contextGraph.setText(
				String.format("  Nodes :          %d\n  Edges :          %d\n  Type Graph : %s", nodes, edges, "None"));
		System.out.println(graphGraphics.graph.toString());
		System.out.println("----------------------------------------------------------");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(close)) {
			dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		updateGraphValues();
		if (e.getSource().equals(lista)) {
			System.out.println(lista.getSelectedValue());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		updateGraphValues();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		updateGraphValues();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		updateGraphValues();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		updateGraphValues();
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		resizedComponents();
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isControlDown() && e.getSource().equals(KeyEvent.VK_W)) {
			this.dispose();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}


}
