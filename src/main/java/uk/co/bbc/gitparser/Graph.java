package uk.co.bbc.gitparser;

import java.util.LinkedList;
import java.util.List;

public class Graph {
	private List<Node> nodes = new LinkedList<Node>();
	private List<Edge> edges = new LinkedList<Edge>();

	public void addNode(Node node) {
		nodes.add(node);
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}
}
