package co.edu.poligran.Colors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class ColorRandom {
	private String url = "src/co/edu/poligran/Files/Colors.txt";
	private ArrayList<String> colors;
	private String arrayColors[];

	public ColorRandom() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File(url)));
		colors = new ArrayList<>();
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			colors.add(line.trim());
		}
		arrayColors = new String[colors.size()];
		int index = 0;
		while (true) {
			if (colors.size() == 0)
				break;
			Random r = new Random();
			int random = r.nextInt(colors.size());
			arrayColors[index] = colors.get(random)+";";
			colors.remove(random);
			index++;
		}
	}

	public String[] getArrayColors() {
		return arrayColors;
	}
}
