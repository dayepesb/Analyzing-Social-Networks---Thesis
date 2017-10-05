package co.edu.poli.Layots;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyEventPostProcessor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout.Constraints;
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

	private int nodes, edges;

	public FramePrincipal() throws IOException {
		// Creación de la ventana
		super("Analizing Social Network");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width * 7 / 10, dim.height * 8 / 10);
		setMinimumSize(new Dimension(956, 614));
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
		setLayout(new GridLayout());
		this.addComponentListener(this);
		this.addKeyListener(this);

		// MenuBar
		mb = new JMenuBar();
		archive = new JMenu("Archive");
		close = new JMenuItem("Close");
		close.addActionListener(this);
		archive.add(close);
		mb.add(archive);
		setJMenuBar(mb);

		// Creación del panel, que contendra JList
		panel = new JPanel();
		panel.setLayout(null);
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

		add(panel);
		setVisible(true);
	}

	private void resizedComponents() {

		int widthFrame = (int) dim.getWidth();
		int heightFrame = (int) dim.getHeight();
		int widthColum = (int) (panel.getWidth() * (2) / 10);
		int longLabels = (int) (panel.getHeight() * 0.7 / 20);
		int xColum = panel.getWidth() - widthColum;

		// label Contex
		labelContex.setText("Contex");
		labelContex.setFont(new Font("TIMES NEW ROMAN", 0, 15));
		int yLabelContex = 0;
		labelContex.setBounds(xColum, yLabelContex, widthColum, longLabels);

		// TextArea
		updateGraphValues();
		contextGraph.setText(
				String.format("  Nodes :          %d\n  Edges :          %d\n  Type Graph : %s", nodes, edges, "None"));

		// Scroll Bar Contex
		int longContex = this.getHeight() / 7;
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(close)) {
			dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(lista)) {
			System.out.println(lista.getSelectedValue());
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
		System.out.println("ddd");
		if(e.isControlDown() && e.getSource().equals(KeyEvent.VK_W)) {
			this.dispose();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("ddd");
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		System.out.println("ddd");
	}

}