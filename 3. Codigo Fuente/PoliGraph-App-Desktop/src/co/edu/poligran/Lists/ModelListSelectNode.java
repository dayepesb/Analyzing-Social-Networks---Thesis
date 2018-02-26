package co.edu.poligran.Lists;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class ModelListSelectNode extends DefaultListModel<String>{
	HashMap<String, String>translate;
	
	public ModelListSelectNode(Graph graph) throws IOException {
		translate=new HashMap<String,String>();	
		for (Node n : graph) {
			if(n.hasAttribute("-attribute-userName")){
				translate.put(n.getAttribute("-attribute-name"),n.getId());
				this.addElement(n.getAttribute("-attribute-name"));
			}else{
				translate.put(n.getId(),n.getId());
				this.addElement(n.getId());
			}
			//this.addElement(n.hasAttribute("-attribute-userName"?n.getAttribute("")));
		}
	}
	public HashMap<String, String> getTranslate() {
		return translate;
	}
}
