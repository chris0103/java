package org.chris.study.algorithms.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
	
	private Node root;
	
	public BinaryTree() {
		
		// test
		root = new Node(9);
		Node node1 = new Node(1);
		Node node2 = new Node(6);
		Node node3 = new Node(13);
		Node node4 = new Node(8);
		Node node5 = new Node(12);
		Node node6 = new Node(10);
		Node node7 = new Node(6);
		Node node8 = new Node(8);
		
		root.setLeftChild(node1);
		root.setRightChild(node2);
		node1.setLeftChild(node3);
		node1.setRightChild(node4);
		node2.setLeftChild(node5);
		node2.setRightChild(node6);
		node4.setRightChild(node7);
		node6.setLeftChild(node8);
	}
	
	public void insert(Object data) {
		
	}
	
	public void search(Object data) {
		
	}
	
	public void delete(Object data) {
		
	}
	
	public void breadthFirstSearch() {
		List<Node> result = new ArrayList<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			result.add(node);
			
			if (node.getLeftChild() != null) {
				queue.add(node.getLeftChild());
			}
			if (node.getRightChild() != null) {
				queue.add(node.getRightChild());
			}
		}
		
		System.out.println("Breadth First Search: ");
		for (Node node : result) {
			System.out.print(node.getData() + " ");
		}
	}
	
	public void depthFirstSearch() {
		List<Node> result = new ArrayList<>();
		result.add(root);
		result.addAll(depthFirstSearch(root.getLeftChild()));
		result.addAll(depthFirstSearch(root.getRightChild()));
		System.out.println("Depth First Search: ");
		for (Node node : result) {
			System.out.print(node.getData() + " ");
		}
	}
	
	private List<Node> depthFirstSearch(Node root) {
		List<Node> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		result.add(root);
		if (root.getLeftChild() != null) {
			result.addAll(depthFirstSearch(root.getLeftChild()));
		}
		if (root.getRightChild() != null) {
			result.addAll(depthFirstSearch(root.getRightChild()));
		}
		return result;
	}
	
	public void findLongestPath() {
		List<Node> path = new ArrayList<>();
		path.add(root);
		List<Node> leftPath = findLongestPath(root.getLeftChild());
		List<Node> rightPath = findLongestPath(root.getRightChild());
		if (leftPath.size() >= rightPath.size()) {
			path.addAll(leftPath);
		} else {
			path.addAll(rightPath);
		}
		System.out.println("The longest path:");
		for (Node node : path) {
			System.out.print(node.getData() + " ");
		}
	}
	
	private List<Node> findLongestPath(Node root) {
		List<Node> path = new ArrayList<>();
		if (root == null) {
			return path;
		}
		path.add(root);
		if (root.getLeftChild() == null && root.getRightChild() == null) {
			return path;
		}
		List<Node> leftPath = findLongestPath(root.getLeftChild());
		List<Node> rightPath = findLongestPath(root.getRightChild());
		if (leftPath.size() >= rightPath.size()) {
			path.addAll(leftPath);
		} else {
			path.addAll(rightPath);
		}
		return path;
	}

	public Node getRoot() {
		return root;
	}

	private class Node {
		
		private Object data;
		private Node leftChild;
		private Node rightChild;
		
		public Node(Object data, Node leftChild, Node rightChild) {
			this.data = data;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		
		public String toString() {
			return String.valueOf(data);
		}
		
		public Node(Object data) {
			this(data, null, null);
		}

		public Object getData() {
			return data;
		}

		@SuppressWarnings("unused")
		public void setData(Object data) {
			this.data = data;
		}

		public Node getLeftChild() {
			return leftChild;
		}

		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}

		public Node getRightChild() {
			return rightChild;
		}

		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}
	}
	
	public static void main(String[] args) {
		new BinaryTree().depthFirstSearch();
	}
}
