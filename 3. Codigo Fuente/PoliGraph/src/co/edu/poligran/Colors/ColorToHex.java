package co.edu.poligran.Colors;

import java.awt.Color;

public class ColorToHex {
	private Color color;

	public ColorToHex(int r, int g, int b) {
		color = new Color(r, g, b);
	}

	public String getHex() {
		return toHex(color.getRed(), color.getGreen(), color.getBlue());
	}

	private String toHex(int r, int g, int b) {
		return "#" + toBrowserHexValue(r) + toBrowserHexValue(g) + toBrowserHexValue(b);
	}

	private String toBrowserHexValue(int number) {
		StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
		while (builder.length() < 2) {
			builder.append("0");
		}
		return builder.toString().toUpperCase();
	}
}
