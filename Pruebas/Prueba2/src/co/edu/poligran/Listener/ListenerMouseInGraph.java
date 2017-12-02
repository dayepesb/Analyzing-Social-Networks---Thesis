package co.edu.poligran.Listener;

import org.graphstream.ui.view.ViewerListener;

public class ListenerMouseInGraph implements ViewerListener {
	public ListenerMouseInGraph() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buttonPushed(String id) {
		System.out.println("Button pushed on node "+id);
		
	}

	@Override
	public void buttonReleased(String id) {
		System.out.println("Button released on node "+id);
		
	}

	@Override
	public void viewClosed(String arg0) {
		// TODO Auto-generated method stub
		
	}

}
