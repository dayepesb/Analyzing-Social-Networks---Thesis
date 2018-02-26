package co.edu.poligran.PopUp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.graphstream.graph.Graph;

import co.edu.poligran.Algorithms.HomofilyPoli;

public class PopUpNumber implements ChangeListener, ActionListener {
	private Icon icon;
	private int resp;
	private JSlider slider;
	private Label number;
	private JButton buttonColorA, buttonColorB;

	public PopUpNumber(Graph graph, String att,int min, int max) {
		icon = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
		JPanel panel = new JPanel(new GridBagLayout());
		slider = new JSlider(JSlider.HORIZONTAL, min, max, (int) ((max + min) / 2));
		slider.addChangeListener(this);
		int t = (int) ((max - min) / 5);
		slider.setMajorTickSpacing(t);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		number = new Label(((int) ((max + min) / 2)) + "");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(slider, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		panel.add(number);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		buttonColorA = new JButton("");
		buttonColorA.setBackground(Color.RED);
		buttonColorA.addActionListener(this);
		panel.add(buttonColorA, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		buttonColorB = new JButton("");
		buttonColorB.setBackground(Color.BLUE);
		buttonColorB.addActionListener(this);
		panel.add(buttonColorB, gbc);
		resp = JOptionPane.showConfirmDialog(null, panel, "Select Range", JOptionPane.CANCEL_OPTION,
				JOptionPane.YES_NO_OPTION, icon);
		if (resp == 0) {
			if (Integer.parseInt(number.getText()) != min && Integer.parseInt(number.getText()) != max) {
				int value = Integer.parseInt(number.getText());
				HomofilyPoli homofily = new HomofilyPoli(graph,att,min, value, max,buttonColorA.getBackground(),buttonColorB.getBackground());
			} else {
				PopUpNumber p;
				p = new PopUpNumber(graph,att, min, max);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource().equals(slider)) {
			int value = slider.getValue();
			number.setText(value + "");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonColorA)) {
			JColorChooser ventanaDeColores = new JColorChooser();
			Color color = ventanaDeColores.showDialog(null, "Seleccione un Color", buttonColorA.getBackground());
			buttonColorA.setBackground(color);
		}
		if (e.getSource().equals(buttonColorB)) {
			JColorChooser ventanaDeColores = new JColorChooser();
			Color color = ventanaDeColores.showDialog(null, "Seleccione un Color", buttonColorB.getBackground());
			buttonColorB.setBackground(color);
		}
	}
}
