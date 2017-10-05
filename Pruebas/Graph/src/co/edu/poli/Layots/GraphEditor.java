package co.edu.poli.Layots;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.apache.commons.collections15.Factory;

import co.edu.poli.Link.LinkDirect;
import co.edu.poli.Vertex.VertexUniq;
import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.annotations.AnnotationControls;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;

public class GraphEditor extends JApplet implements Printable {
	private static final long serialVersionUID = -202323689258876709L;

	Graph<VertexUniq, LinkDirect> graph;
	AbstractLayout<VertexUniq, LinkDirect> layout;
	VisualizationViewer<VertexUniq, LinkDirect> vv;
	static String instructions = "<html>" + "<h3>Información de ayuda</h3>" + "</html>";

	public GraphEditor() {
		graph = new SparseMultigraph<VertexUniq, LinkDirect>();
		this.layout = new StaticLayout<VertexUniq, LinkDirect>(graph, new Dimension(600, 600));
		vv = new VisualizationViewer<VertexUniq, LinkDirect>(layout);
		vv.setBackground(Color.white);

		vv.setVertexToolTipTransformer(vv.getRenderContext().getVertexLabelTransformer());

		Container content = getContentPane();
		final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
		content.add(panel);

		Factory<VertexUniq> vertexFactory = new Factory<VertexUniq>() {
			public VertexUniq create() {
				return new VertexUniq(graph.getVertexCount());
			}
		};

		Factory<LinkDirect> edgeFactory = new Factory<LinkDirect>() {
			public LinkDirect create() {

				return new LinkDirect(graph.getEdgeCount());
			}
		};

		final EditingModalGraphMouse<VertexUniq, LinkDirect> graphMouse = new EditingModalGraphMouse<VertexUniq, LinkDirect>(
				vv.getRenderContext(), vertexFactory, edgeFactory);

		vv.setGraphMouse(graphMouse);
		vv.addKeyListener(graphMouse.getModeKeyListener());

		graphMouse.setMode(ModalGraphMouse.Mode.EDITING);

		final ScalingControl scaler = new CrossoverScalingControl();

		JButton plus = new JButton("+");
		plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scaler.scale(vv, 1.1f, vv.getCenter());
			}
		});

		JButton minus = new JButton("-");
		minus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scaler.scale(vv, 1 / 1.1f, vv.getCenter());
			}
		});

		JButton help = new JButton("Help");
		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(vv, instructions);
			}
		});

		AnnotationControls annotationControls = new AnnotationControls<>(graphMouse.getAnnotatingPlugin());
		JPanel controls = new JPanel();
		controls.add(plus);
		controls.add(minus);
		JComboBox modeBox = graphMouse.getModeComboBox();
		controls.add(modeBox);
		// controls.add(annotationControls.getAnnotationsToolBar());

		controls.add(help);
		content.add(controls, BorderLayout.SOUTH);
	}

	public void writeJPEGImage(File file) {
		int width = vv.getWidth();
		int height = vv.getHeight();

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bi.createGraphics();
		vv.paint(graphics);
		graphics.dispose();

		try {
			ImageIO.write(bi, "jpeg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int print(java.awt.Graphics graphics, java.awt.print.PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		if (pageIndex > 0) {
			return (Printable.NO_SUCH_PAGE);
		} else {
			java.awt.Graphics2D g2d = (java.awt.Graphics2D) graphics;
			vv.setDoubleBuffered(false);
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			vv.paint(g2d);
			vv.setDoubleBuffered(true);
			return Printable.PAGE_EXISTS;
		}
	}
}
