package co.edu.poligran.Reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Map.Entry;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.edu.poligran.Frame.PrincipalFrame;

public class GraphReaderJSON {
	private PrincipalFrame principalFrame;
	private Graph graph;

	public GraphReaderJSON(File filename, PrincipalFrame principalFrame) {
		this.principalFrame = principalFrame;
		JsonParser parser = new JsonParser();
		String text = "";
		try {
			text = readFile(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonObject JsonFriends = parser.parse(text).getAsJsonObject();

		JsonElement userId = JsonFriends.get("userId");
		JsonElement userName = JsonFriends.get("userName");
		JsonElement nodes = JsonFriends.get("nodes");
		JsonElement links = JsonFriends.get("links");
		graph = new MultiGraph(userId.getAsString());
		graph.setAttribute("-attribute-userName", userName.toString());
		int indexNode = 0;
		for (JsonElement node : nodes.getAsJsonArray()) {
			JsonObject thisNode = node.getAsJsonObject();
			graph.addNode(indexNode + "");
			Node n = graph.getNode(indexNode + "");
			n.setAttribute("-attribute-id", thisNode.get("id").getAsString());
			n.setAttribute("-attribute-name", thisNode.get("name").getAsString());
			n.setAttribute("-attribute-userName", thisNode.get("userName").getAsString());
			n.setAttribute("-attribute-profile", thisNode.get("profile").getAsString());

			n.setAttribute("-attrubute-dataUrl", thisNode.get("dataUrl") == null ? "" : thisNode.get("dataUrl"));
			indexNode++;
		}
		for (JsonElement link : links.getAsJsonArray()) {
			JsonObject thisLink = link.getAsJsonObject();
			String source = thisLink.get("source").getAsString();
			String target = thisLink.get("target").getAsString();
			String nameEdge = (String) graph.getNode(source).getAttribute("-attribute-userName")
					+"----"+ (String) graph.getNode(target).getAttribute("-attribute-userName");
			if (!graph.getNode(source).hasEdgeBetween(target))
				graph.addEdge(nameEdge, source, target).addAttribute("-attribute-width", 1.0);
		}
//		System.out.println("Doble Simon");
//		graph.display();

	}
	
	public Graph getGraph() {
		return graph;
	}

	public String readFile(File path) throws IOException {
		FileInputStream stream = new FileInputStream(path);
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

			return Charset.defaultCharset().decode(bb).toString();
		} finally {
			stream.close();
		}
	}

}
