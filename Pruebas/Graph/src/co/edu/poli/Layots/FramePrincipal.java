package co.edu.poli.Layots;

import javax.swing.*;

import co.edu.poli.Lists.ListProperties;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;;

public class FramePrincipal extends JFrame implements MouseListener, ActionListener,ComponentListener {
	private JMenuBar mb;
	private JMenu file;
	private JMenuItem close;
	private Dimension dim;

	private JList<String> lista;
	private JPanel panel;
	private JScrollPane barraDesplazamiento;

	public FramePrincipal() throws IOException {
		// Creación de la ventana
		super("Analizing Social Network");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width * 7 / 10, dim.height * 8 / 10);
		setMinimumSize(new Dimension(956,614));
		System.out.println(dim.width * 7 / 10+" "+ dim.height * 8 / 10);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(close)) {
			dispose();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int index = lista.getSelectedIndex();
		System.out.println(lista.getSelectedValue());
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
		int anchoList = (int) (this.getWidth()*(1.5)/10);
		int largoList = this.getHeight()* 8 / 20;
		int x = this.getWidth()-anchoList-15;
		int y = 0;
		barraDesplazamiento.setBounds(x, y,anchoList,largoList);
	
	}
	@Override
	public void componentShown(ComponentEvent e) {
	}
}