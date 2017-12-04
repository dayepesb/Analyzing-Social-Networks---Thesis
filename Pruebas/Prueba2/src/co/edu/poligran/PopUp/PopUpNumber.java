package co.edu.poligran.PopUp;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.graphstream.graph.Graph;

public class PopUpNumber implements ChangeListener {
	private Icon icon;
	private int resp;
	private JSlider slider;

	public PopUpNumber(Graph graph, int min, int max) {
		icon = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
		JPanel panel = new JPanel();
		slider = new JSlider(JSlider.HORIZONTAL, min, max, (int) ((max + min) / 2));
		slider.addChangeListener(this);
		int t = (int)((max-min)/5);
		slider.setMajorTickSpacing(t);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		panel.add(slider);
		resp = JOptionPane.showConfirmDialog(null, panel, "Select Atttribute", JOptionPane.CANCEL_OPTION,
				JOptionPane.YES_NO_OPTION, icon);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource().equals(slider)) {
			
		}
	}
}
