/**
 * @author David Yepes Buitrago
 */
package co.edu.poligran.Colors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ColorRandom {
	private String url = "src/co/edu/poligran/Files/Colors.txt";
	private String urldegre ="src/co/edu/poligran/Files/ColorsDegre.txt";
	private ArrayList<String> colors;
	private String arrayColors[];
	private String arrayColorsDegre[];
	private ArrayList<String> colorsDegre;

	public String[] getColorRandomDegre()throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(urldegre)));
		colorsDegre = new ArrayList<>();
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			colorsDegre.add(line.trim());
		}
		arrayColorsDegre = new String[colorsDegre.size()];
		int index = 0;
		while (index<1024) {
			if (colorsDegre.size() == 0)
				break;
			arrayColorsDegre[index] = colorsDegre.get(index)+";";
			//colorsDegre.remove(index);
			index++;
		}
		return arrayColorsDegre;
		
	}
	
	public String []generateColorRandomDegre(){
		String[] hexa={"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		String [] resp=new String[256];
		int index=0;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				resp[index]="FF"+hexa[i]+hexa[j]+"00;";
				//System.out.println(resp[index]);
				index++;
			}
		}
		return resp;
	}

	public String[] getColorRamdom() throws IOException {
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
		return arrayColors;
	}

}