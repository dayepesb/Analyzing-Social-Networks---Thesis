package co.edu.poligran.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.DefaultListModel;

public class ModelListAlgorithms extends DefaultListModel<String> {
	private static String line;

	public ModelListAlgorithms() throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("../Files/Algorithms.txt"));
		File archivo = new File ("bin/co/edu/poligran/Files/Algorithms.txt");
		FileReader fr = new FileReader (archivo);
		BufferedReader in = new BufferedReader(fr);
		while (true) {
			try {
				line = in.readLine().trim();
				addElement(line);
			}catch (Exception e) {
				break;
			}
		}
	}
}
