package com.tree.kriantest;

import java.util.List;

import com.tree.kriantest.nodes.ComplexTypeNode;
import com.tree.kriantest.nodes.SimpleTypeNode;

/**
 * This class will build dot format from the tree
 * @author Sridhar
 *
 */
public class DotFormat {
	private ComplexTypeNode mainNode;
	// Main variable to store text
	private StringBuffer dotStringBuffer;
	private String double_quote = "\"";
	
	/**
	 * This constructor will take complex node type and prints the values in dot format 
	 */
	public DotFormat(ComplexTypeNode paramMainNode) {
		mainNode = paramMainNode;
		dotStringBuffer = new StringBuffer();
		addHeaderText();
		// add first header Text
		displayNode(mainNode);
		// add the final closing bracket
		dotStringBuffer.append("}");
		// Print the final text
		//System.out.println("final tree:\n\n" );
		//System.out.println(dotStringBuffer.toString());
		
		
	}
	/**
	 * This method will return the dot format of the input
	 * @return
	 */
	public String getDotFormat() {
		return dotStringBuffer.toString();
		
	}

	/**
	 * This method will add the header text to the dot string
	 */
	private void addHeaderText() {
		dotStringBuffer.append("Digraph G {"+"\n")
						.append("	Node [style = rounded];"+"\n")
						.append("	rankdir = LR ;"+"\n");
		
	}
	/**
	 * This method will display content of the tree
	 */
	private void displayNode(ComplexTypeNode currentNode) {
		
	  //  System.out.println(currentNode.getId()  + " : " + currentNode.getNodeName());

		
		// The complex type node have simples nodes and also complex nodes
		// print all simple nodes first
		List <SimpleTypeNode>  simpleTypeNodesList = currentNode.getSimpleNodesList();
		displaySimpleNodesList(simpleTypeNodesList);
		// get all complex nodes
		List <ComplexTypeNode>  complexTypeNodesList = currentNode.getComplexNodesList();
		displayComplexTypeNodesList(complexTypeNodesList);
		
		
	}
	/**
	 * This method will print all the content from list of simple node types
	 * @param paramSimpleTypeNodesList
	 */
	private void displaySimpleNodesList(List <SimpleTypeNode>  paramSimpleTypeNodesList) {
		for (SimpleTypeNode simpleTypeNode: paramSimpleTypeNodesList) {
		    //System.out.println(simpleTypeNode.getId() + " : " + simpleTypeNode.getNodeValue() + " : " + simpleTypeNode.getNodeName());
		    if(simpleTypeNode.getParentNode() == null) {
			    dotStringBuffer.append(double_quote).append("com.tree.node.TreeOperations buildTree").append(double_quote).append(" [shape=record label=").append(double_quote).append("buildTree |{ com.tree.node.TreeOperations | buildTree}").append(double_quote).append("]"+"\n")
			    .append(double_quote+simpleTypeNode.getNodeName() +"=" + simpleTypeNode.getNodeValue() +double_quote  ).append(" [shape=record label=").append(double_quote).append("SimpleTypeNode|{"+simpleTypeNode.getNodeName() +"=" + simpleTypeNode.getNodeValue() +"}").append(double_quote).append("]\n")
			    .append(double_quote).append("com.tree.node.TreeOperations buildTree").append(double_quote).append(  "->" ).append(double_quote).append(simpleTypeNode.getNodeName() +"=" + simpleTypeNode.getNodeValue()  ).append(double_quote).append("[ label="+double_quote+ simpleTypeNode.getId() +double_quote+"]" +"\n");
		    } else {
			    dotStringBuffer.append(double_quote+simpleTypeNode.getNodeName() +"=" + simpleTypeNode.getNodeValue() +double_quote  ).append(" [shape=record label=").append(double_quote).append("SimpleTypeNode|{"+simpleTypeNode.getNodeName() +"=" + simpleTypeNode.getNodeValue() +"}").append(double_quote).append("]\n")
			    .append(double_quote).append(simpleTypeNode.getParentNode().getNodeName() +"=" + simpleTypeNode.getParentNode().getComplexTypeName() ).append(double_quote).append(  "->" ).append(double_quote).append(simpleTypeNode.getNodeName() +"=" + simpleTypeNode.getNodeValue()  ).append(double_quote).append("[ label="+double_quote+ simpleTypeNode.getId() +double_quote+"]" +"\n");
		    	
		    }
		}
		
	}
	/**
	 * This method will print and iterate the complex type nodes 
	 * @param paramComplexTypeNodesList
	 */
	private void displayComplexTypeNodesList(List <ComplexTypeNode> paramComplexTypeNodesList) {
		for (ComplexTypeNode complexTypeNode: paramComplexTypeNodesList) {
			
			if(complexTypeNode.getParentNode() == null) {
				//System.out.println(complexTypeNode.getId() + " : "  + complexTypeNode.getNodeName());
			    dotStringBuffer.append(double_quote).append("com.tree.node.TreeOperations buildTree").append(double_quote).append(" [shape=record label=").append(double_quote).append("buildTree |{ com.tree.node.TreeOperations | buildTree}").append(double_quote).append("]"+"\n")
			    .append(double_quote+complexTypeNode.getNodeName() +"=" + complexTypeNode.getComplexTypeName() +double_quote  ).append(" [shape=record label=").append(double_quote).append("ComplexTypeNode|{"+complexTypeNode.getNodeName() +"=" + complexTypeNode.getComplexTypeName() +"}").append(double_quote).append("]\n")
			    .append(double_quote).append("com.tree.node.TreeOperations buildTree").append(double_quote).append(  "->" ).append(double_quote).append(complexTypeNode.getNodeName() +"=" + complexTypeNode.getComplexTypeName()  ).append(double_quote).append("[ label="+double_quote+ complexTypeNode.getId() +double_quote+"]" +"\n");

			} else {
				//System.out.println(complexTypeNode.getId() + " : "  + complexTypeNode.getNodeName());
				dotStringBuffer.append(double_quote+complexTypeNode.getNodeName() +"=" + complexTypeNode.getComplexTypeName() +double_quote  ).append(" [shape=record label=").append(double_quote).append("ComplexTypeNode|{"+complexTypeNode.getNodeName() +"=" + complexTypeNode.getComplexTypeName() +"}").append(double_quote).append("]\n")
			    .append(double_quote).append(complexTypeNode.getParentNode().getNodeName() +"=" + complexTypeNode.getParentNode().getComplexTypeName() ).append(double_quote).append(  "->" ).append(double_quote).append(complexTypeNode.getNodeName() +"=" + complexTypeNode.getComplexTypeName()  ).append(double_quote).append("[ label="+double_quote+ complexTypeNode.getId() +double_quote+"]" +"\n");
				
			}
		    // call the display nod method for each node you get
				displayNode(complexTypeNode);
		}
		
	}
	

}
