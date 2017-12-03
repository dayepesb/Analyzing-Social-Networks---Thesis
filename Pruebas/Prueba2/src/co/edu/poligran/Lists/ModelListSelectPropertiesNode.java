package co.edu.poligran.Lists;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.DefaultListModel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class ModelListSelectPropertiesNode extends DefaultListModel<String> {
	public ModelListSelectPropertiesNode(Graph graph) throws IOException {
		for (Node n : graph) {
			for(String s:n.getAttributeKeySet()){
				if(s.startsWith("-attribute-")){
					
				}
			}
//			if(n.hasAttribute("-attribute-userName")){
//				translate.put(n.getAttribute("-attribute-name"),n.getId());
//				this.addElement(n.getAttribute("-attribute-name"));
//			}else{
//				translate.put(n.getId(),n.getId());
//				this.addElement(n.getId());
//			}
			//this.addElement(n.hasAttribute("-attribute-userName"?n.getAttribute("")));
		}
	}

}
