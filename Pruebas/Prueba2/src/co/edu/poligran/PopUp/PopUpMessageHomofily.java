package co.edu.poligran.PopUp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class PopUpMessageHomofily {
	private Icon icon;

	public PopUpMessageHomofily(int p, int q, int pq, int cont) {
		icon = new ImageIcon(getClass().getResource("../Images/ImageMensajeDialog.png"));
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(new Label("P :\t"+p), gbc);		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(new Label("Q :\t"+q), gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(new Label("2PQ Expected :\t"+pq), gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		panel.add(new Label("2PQ Obtained :\t"+cont), gbc);
		
		JOptionPane.showConfirmDialog(null, panel, "Select Atttribute", JOptionPane.CANCEL_OPTION,
				JOptionPane.YES_NO_OPTION, icon);
	}
}
