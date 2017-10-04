package co.edu.poli.Layots;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FramePrincipal extends JFrame implements ActionListener, MouseListener {
	JPanel panel;
	JLabel label;
	JMenuBar mb;
	JMenu file;
	JMenuItem close;
	Dimension dim;

	public FramePrincipal(String title) {
		super(title);
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width * 7 / 10, dim.height * 8 / 10);
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
		setLayout(new FlowLayout()); // set the layout manager

		// Label
		label = new JLabel("Hello Swing!"); // construct a JLabel
		add(label);
		// Menu bar
		mb = new JMenuBar();
		file = new JMenu("File");
		close = new JMenuItem("Close");
		close.addActionListener(this);
		file.add(close);
		mb.add(file);
		setJMenuBar(mb);

		// pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
