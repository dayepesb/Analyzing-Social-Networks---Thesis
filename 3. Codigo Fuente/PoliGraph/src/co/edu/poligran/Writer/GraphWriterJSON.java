package co.edu.poligran.Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import co.edu.poligran.Frame.PrincipalFrame;

public class GraphWriterJSON {
	private PrincipalFrame principalFrame;

	public static void main(String[] args) throws IOException {
		Writer writer = new FileWriter(
				"C:\\Users\\johansteven\\Desktop\\JulianBorrar\\Analyzing-Social-Networks---Thesis\\Pruebas\\Prueba2\\src\\co\\edu\\poligran\\Writer\\write.json");
		Gson grson = new GsonBuilder().create();
		JsonObject g = new JsonObject();
		g.addProperty("userId", "12213123");
		g.addProperty("userName", "asdsajdkg");
		JsonArray nodes = new JsonArray();
		JsonObject node = new JsonObject();
		node.addProperty("saldkahsd", "sd");
		nodes.add(node);
		JsonArray links = new JsonArray();
		JsonObject link = new JsonObject();
		link.addProperty("saldkahsd", "sd");
		links.add(node);
		g.add("nodes", nodes);
		g.add("links", links);
		grson.toJson(g, writer);
		writer.close();
	}

	public GraphWriterJSON(Graph graph,String url) {
		Writer writer = null;
		try {
			File fichero = new File(url);
			if(fichero.exists()){
				writer = new FileWriter(url+".json");				
			}else{
				writer = new FileWriter(url+".json");				
			}
			/*
			 * "C:\\Users\\johansteven\\Desktop\\JulianBorrar\\Analyzing-Social-Networks---Thesis\\Pruebas\\Prueba2\\src\\co\\edu\\poligran\\Writer\\write.json"
			 */
		} catch (Exception e) {
			// TODO: handle exception
		}
		Gson gsron = new GsonBuilder().create();
		JsonObject g = new JsonObject();
		g.addProperty("userId", graph.getAttribute("-attribute-userId") + "");
		g.addProperty("userName", graph.getAttribute("-attribute-userName") + "");
		JsonArray nodes = new JsonArray();
		for (Node n : graph) {
			JsonObject temp = new JsonObject();
			for (String s : n.getAttributeKeySet()) {
				temp.addProperty(s, n.getAttribute(s) + "");

			}
			nodes.add(temp);
		}
		g.add("nodes", nodes);
		JsonArray links = new JsonArray();
		for (Edge e : graph.getEdgeSet()) {
			JsonObject temp = new JsonObject();
			for (String s : e.getAttributeKeySet()) {

				temp.addProperty(s, "" + e.getAttribute(s));

			}
			String nodeA = e.getNode0().getId();
			String nodeB = e.getNode1().getId();
			temp.addProperty("source", nodeA);
			temp.addProperty("target", nodeB);

			links.add(temp);
		}
		g.add("links", links);
		gsron.toJson(g, writer);
		try {
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
