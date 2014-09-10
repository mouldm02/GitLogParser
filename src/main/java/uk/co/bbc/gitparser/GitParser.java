package uk.co.bbc.gitparser;

import com.thoughtworks.xstream.XStream;

import javax.xml.transform.TransformerConfigurationException;
import java.io.*;

public class GitParser {

	public static void main(String[] args) throws IOException, TransformerConfigurationException {
		BufferedReader reader = new BufferedReader(new FileReader("resources/git_history.txt"));
		Graph graph = new Graph();

		String line;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("commit ")) {
				String[] commits = line.replace("commit ", "").split(" ");
				addNodes(graph, commits);
				addEdges(graph, commits);
			}
		}

		XStream xstream = new XStream();
		String xml = xstream.toXML(graph);

		Writer writer = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream("resources/graph.xml"), "utf-8"));
		writer.write(xml);
	}

	private static void addNodes(Graph graph, String[] commits) {
		for (int i = 0; i < commits.length; i++) {
			graph.addNode(new Node(commits[i]));
		}
	}

	private static void addEdges(Graph graph, String[] commits) {
		for (int i = 1; i < commits.length; i++) {
			graph.addEdge(new Edge(commits[0], commits[i]));
		}
	}
}
