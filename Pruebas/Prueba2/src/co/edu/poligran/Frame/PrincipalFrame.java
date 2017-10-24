package co.edu.poligran.Frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

public class PrincipalFrame extends JFrame implements MouseListener, ComponentListener, KeyListener {
	private static final long serialVersionUID = -8099070856715541164L;
	private final Border border = LineBorder.createGrayLineBorder();
	private JMenuBar mb;
	private JMenu archive;
	private JMenuItem close;
	private Dimension dim;

	public PrincipalFrame() {
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
		setVisible(true);

		Graph g = new MultiGraph("Social");
		
	}

	// KeyListenerc
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	// ComponentListener
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	// MouseListener
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
