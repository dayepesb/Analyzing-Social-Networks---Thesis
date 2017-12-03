package co.edu.poligran.Panels;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class PanelGraphics extends JPanel implements MouseListener {
	private Graph graph;
	private JComboBox<String> properties, type;
	private DefaultComboBoxModel<String> dcbm;
	private final Border border = LineBorder.createGrayLineBorder();
	private HashSet<String> propertiesSet;
	private JButton generateGraphic;
	private int isBars;
	private PanelBars bars;
	private PanelCake cake;
	private JPanel panel;

	public PanelGraphics(Graph graph) {
		this.graph = graph;
		this.setLayout(null);
		isBars = 0;
		// this.setBackground(Color.WHITE);

		type = new JComboBox<>();
		type.addItem("Bars");
		type.addItem("Cake");
		this.add(type);

		dcbm = new DefaultComboBoxModel<>();
		properties = new JComboBox<>();
		updateComboBoxModel();
		this.add(properties);

		generateGraphic = new JButton("Generate");
		generateGraphic.addMouseListener(this);
		this.add(generateGraphic);

		bars = new PanelBars(graph);
		bars.setBorder(border);
		bars.setVisible(false);
		this.add(bars);

		cake = new PanelCake(graph);
		cake.setBorder(border);
		cake.setVisible(false);
		this.add(cake);

		panel = new JPanel();
		panel.setBorder(border);
		panel.setBackground(Color.white);
		this.add(panel);

		resizedComponents(this.getWidth(), this.getHeight());

	}

	public void resizedComponents(int width, int height) {
		this.setBounds(0, 0, width, height);
		generateGraphic.setBounds(10, 20, 100, 20);
		type.setBounds(generateGraphic.getX() + generateGraphic.getWidth() + 20, 20, 100, 20);
		properties.setBounds(type.getX() + type.getWidth() + 20, 20, 100, 20);
		bars.resizedComponents(this.getWidth(), this.getHeight());
		cake.resizedComponents(this.getWidth(), this.getHeight());
		panel.setBounds(0, 50, width, height + 7);
	}

	public void updateComboBoxModel() {
		propertiesSet = new HashSet<>();
		for (Node n : graph) {
			for (String s : n.getAttributeKeySet()) {
				if (s.startsWith("-attribute-"))
					propertiesSet.add(s.substring(11).toLowerCase());
			}
		}
		String[] items = new String[propertiesSet.size()];
		int i = 0;
		for (String string : propertiesSet) {
			items[i++] = string;
		}
		dcbm = new DefaultComboBoxModel<>(items);
		properties.setModel(dcbm);
	}
	
	public void setGraph(Graph graph) {
		this.graph = graph;
		updateComboBoxModel();
	}

	// MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(generateGraphic)) {
			String typeGraphic = type.getSelectedItem().toString().toLowerCase();
			String propetiGraphic = properties.getSelectedItem().toString().toLowerCase();
			if (typeGraphic.equalsIgnoreCase("bars")) {
				isBars = -1;
				bars.setVisible(true);
				cake.setVisible(false);
				bars.setComponents(propetiGraphic);
				panel.setVisible(false);
			} else if (typeGraphic.equalsIgnoreCase("cake")) {
				isBars = 1;
				cake.setVisible(true);
				bars.setVisible(false);
				panel.setVisible(false);
				cake.setComponents(propetiGraphic);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
