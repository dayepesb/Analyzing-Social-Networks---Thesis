package co.edu.poligran.Listener;

import javax.swing.JOptionPane;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.ViewerListener;

import co.edu.poligran.PopUp.PopUpPropertiesNode;

public class ListenerMouseInGraph implements ViewerListener {
	Graph graph;
	int countClicks = 0;
	long firstClick, secondClick;

	public ListenerMouseInGraph(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void buttonPushed(String id) {
		Node n = graph.getNode(id);
		if (n.hasAttribute("-attribute-name")) {
			//System.out.println(n.getAttribute("-attribute-name") + "");
		} else {
			//System.out.println(id);
		}
		// System.out.println(graph.getNode(id).hasAttribute("-"));

	}

	@Override
	public void buttonReleased(String id) {
		if (countClicks == 0) {
			firstClick = System.currentTimeMillis();
		} else if (countClicks == 1) {
			secondClick = System.currentTimeMillis();
		}
		countClicks++;
		if (countClicks == 2 && secondClick - firstClick < 500) {
			try {
				String url = ".../Images/ImgaeMensajeDialog.png";
				PopUpPropertiesNode pop = new PopUpPropertiesNode();
				String[] p = pop.PopUpNode(url, "Node Description", JOptionPane.CANCEL_OPTION, JOptionPane.YES_NO_OPTION,
						graph,id);

			} catch (Exception e) {
				// TODO: handle exception
			}

			Node n = graph.getNode(id);
			if (n.hasAttribute("-attribute-name")) {
				//System.out.println(n.getAttribute("-attribute-name") + "");
			} else {
				//System.out.println(id);
			}
			countClicks = 0;
		}
		if (countClicks == 2) {
			countClicks = 0;
		}
	}

	@Override
	public void viewClosed(String arg0) {
		// TODO Auto-generated method stub

	}

}
