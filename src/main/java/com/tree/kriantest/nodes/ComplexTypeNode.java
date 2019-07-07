package com.tree.kriantest.nodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Complex type node bean class
 * @author sreedhar
 *
 */
public class ComplexTypeNode {
	private String nodeName ;
	private int id;
	private List <ComplexTypeNode> ComplexNodesList;
	private List <SimpleTypeNode> simpleNodesList;
	private String complexTypeName;
	private ComplexTypeNode parentNode ;

	public ComplexTypeNode() {
		ComplexNodesList = new <ComplexTypeNode> ArrayList();
		simpleNodesList = new <SimpleTypeNode> ArrayList();
		
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public List<ComplexTypeNode> getComplexNodesList() {
		return ComplexNodesList;
	}
	public void setComplexNodesList(List<ComplexTypeNode> complexNodesList) {
		ComplexNodesList = complexNodesList;
	}
	public List<SimpleTypeNode> getSimpleNodesList() {
		return simpleNodesList;
	}
	public void setSimpleNodesList(List<SimpleTypeNode> simpleNodesList) {
		this.simpleNodesList = simpleNodesList;
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
	public String getComplexTypeName() {
		return complexTypeName;
	}
	public void setComplexTypeName(String complexTypeName) {
		this.complexTypeName = complexTypeName;
	}
	
	
	
	
	
		

}
