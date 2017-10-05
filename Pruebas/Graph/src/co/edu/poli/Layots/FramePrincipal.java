package co.edu.poli.Layots;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import co.edu.poli.Lists.ListProperties;;

public class FramePrincipal extends JFrame implements MouseListener, ActionListener, ComponentListener {
	private static final long serialVersionUID = -8099070856715541164L;
	private final Border border = LineBorder.createGrayLineBorder();
	private JMenuBar mb;
	private JMenu file;
	private JMenuItem close;
	private Dimension dim;
	private JLabel labelAlgorithms;
	private JList<String> lista;
	private JPanel panel;
	private JScrollPane barraDesplazamiento;

	public FramePrincipal() throws IOException {
		// Creación de la ventana
		super("Analizing Social Network");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width * 7 / 10, dim.height * 8 / 10);
		setMinimumSize(new Dimension(956, 614));
		System.out.println(dim.width * 7 / 10 + " " + dim.height * 8 / 10);
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
		setLayout(new GridLayout());
		this.addComponentListener(this);

		// MenuBar
		mb = new JMenuBar();
		file = new JMenu("File");
		close = new JMenuItem("Close");
		close.addActionListener(this);
		file.add(close);
		mb.add(file);
		setJMenuBar(mb);

		// Creación del panel, que contendra JList
		panel = new JPanel();
		panel.setLayout(null);

		// Se agrega una etiqueta de label
		labelAlgorithms = new JLabel("Algorithms");
		labelAlgorithms.setBorder(border);
		panel.add(labelAlgorithms);

		// creación de los elememtos que componen la lista
		// creación del objeto lista
		lista = new ListProperties();
		lista.addMouseListener(this);


		// aquí se crea el objeto, es decir la barra de desplazamiento
		barraDesplazamiento = new JScrollPane(lista);
		// Agrega la barra de desplazamiento al panel
		panel.add(barraDesplazamiento);

		add(panel);
		setVisible(true);
	}

	private void resizedComponents() {
		labelAlgorithms.setText("Algorithms");
		labelAlgorithms.setFont(new Font("TIMES NEW ROMAN", 0, 15));
		int anchoLabelAlgorithms = (int) (this.getWidth() * (2) / 10);
		int largoLabelAlgorithms = (int) (this.getHeight() *0.7/ 20);
		int xLabelAlgorithms = this.getWidth() - anchoLabelAlgorithms - 15;
		int yLabelAlgorithms = 0;
		labelAlgorithms.setBounds(xLabelAlgorithms,yLabelAlgorithms,anchoLabelAlgorithms,largoLabelAlgorithms);

		lista.setFont(new Font("TIMES NEW ROMAN", 0, 15));
		int anchoList = (int) (this.getWidth() * (2) / 10);
		int largoList = this.getHeight() * 8 / 30;
		int xList = this.getWidth() - anchoList - 15;
		int yList = largoLabelAlgorithms;
		barraDesplazamiento.setBounds(xList, yList, anchoList, largoList);
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
}