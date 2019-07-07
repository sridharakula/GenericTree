package com.tree.kriantest.nodes;

/**
 * Simple type node bean class
 * @author sreedhar
 *
 */

public class SimpleTypeNode {
	
	private int nodeValue ;
	private int id;
	private String nodeName;
	private ComplexTypeNode parentNode ;
	public SimpleTypeNode () {
		
	}
	public int getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(int nodeValue) {
		this.nodeValue = nodeValue;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ComplexTypeNode getParentNode() {
		return parentNode;
	}
	public void setParentNode(ComplexTypeNode parentNode) {
		this.parentNode = parentNode;
	}
	
	
	
	

}
