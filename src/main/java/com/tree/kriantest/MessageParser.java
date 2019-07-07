package com.tree.kriantest;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.tree.kriantest.nodes.ComplexTypeNode;
import com.tree.kriantest.nodes.SimpleTypeNode;

/**
 * This method will parse the input and build the tree
 * @author Sridhar
 *
 */
public class MessageParser {
	private ComplexTypeNode mainNode;
	private int nodeCounter ;

	/**
	 * This is the main method which takes text as input and build the tree with the text
	 * @param inputText
	 */
	public MessageParser(String inputText) {
		if(inputText == null) {
			
			 throw new NullPointerException("input is null");
			
		}
		// check if input is valid
		if(inputText.contains("=") || inputText.contains(":")) {
			
		} else {
			throw new IllegalArgumentException("Invalid input");
		}
		
		mainNode = new ComplexTypeNode();
		nodeCounter = 1;
		String firstSimpleNodes = null;
		// check if it has only simple nodes
		if(inputText.indexOf("[") == -1) {
			firstSimpleNodes = inputText;
			
		} else {
			// parse first simple nodes 		
			// parse untill you hit [
			firstSimpleNodes = inputText.substring(0,inputText.indexOf("["));

		}
		
		//System.out.println("firstSimpleNodes"+ firstSimpleNodes);
		// break first simple nodes into individual nodes
		//breakFirstSimpleNodes(firstSimpleNodes);
		// add simple array to main node
		
		// check if simplenodes are empty
		if(firstSimpleNodes != null &&  firstSimpleNodes.length() > 0) {
			mainNode.setSimpleNodesList(breakFirstSimpleNodes(firstSimpleNodes));
		}
		if(inputText.indexOf("[") != -1) {
			String firstComplexNodes =  inputText.substring(inputText.indexOf("["),inputText.length());
			// System.out.println("firstComplexNodes: "+ firstComplexNodes);
			mainNode.setComplexNodesList(buildComplexTypeNodeList(firstComplexNodes,null));
		}
		
	}
	/**
	 * This method will return the dot format of the input
	 * @return
	 */
	public String getDotFormat() {
		DotFormat dotFormat = new DotFormat(mainNode);		
		return dotFormat.getDotFormat();
		
	}
	/**
	 * This method will break the first simple nodes and add it to the tree
	 * @param paramFirstSimpleNodes
	 * @return
	 */
	private List <SimpleTypeNode> breakFirstSimpleNodes(String paramFirstSimpleNodes) {
		
		//trim the last empty char
		paramFirstSimpleNodes = paramFirstSimpleNodes.trim();
		// System.out.println("paramFirstSimpleNodes : "+ paramFirstSimpleNodes);
		// remove last comma if it exits
		char lastChar = paramFirstSimpleNodes.charAt(paramFirstSimpleNodes.length() - 1);
		if(paramFirstSimpleNodes.endsWith(",") ) {
			paramFirstSimpleNodes = paramFirstSimpleNodes.substring(0, paramFirstSimpleNodes.length() - 1);
		}
		// System.out.println("after trim and last char : "+ paramFirstSimpleNodes);
		
		// break the simple nodes to each node seperated by comma
		StringTokenizer tokenizer = new StringTokenizer(paramFirstSimpleNodes, ",");
		List <SimpleTypeNode>  simpleTypeNodesList = new <SimpleTypeNode> ArrayList();
	    while (tokenizer.hasMoreElements()) {
	       // tokens.add(tokenizer.nextToken());
	    	String tokenText = tokenizer.nextToken().trim();
	       // System.out.println("tokens : "+ tokenText);
	        // build simple tree from all tokens
	        StringTokenizer nodeTokens = new StringTokenizer(tokenText, "="); 
	        while (nodeTokens.hasMoreElements()) {
	        	// first token will be node name second will be node value
	        	String nodeName = nodeTokens.nextToken().trim();
	        	int nodeValue = Integer.parseInt(nodeTokens.nextToken().trim());
	        	/// build simple node and add it to parent tree
	        	simpleTypeNodesList.add(buildSimpleTypeNode(nodeCounter++,nodeValue,nodeName,null));
	        }
	    }
	    return simpleTypeNodesList;
	}
	/**
	 * This method will build the simple node type Object
	 * @param paramNodeValue
	 * @return
	 */
	private SimpleTypeNode buildSimpleTypeNode(int paramId,int paramNodeValue, String paramNodeName, ComplexTypeNode paramParentNode) {
		SimpleTypeNode simpleTypeNode = new SimpleTypeNode();
		simpleTypeNode.setNodeValue(paramNodeValue);
		simpleTypeNode.setId(paramId);
		simpleTypeNode.setNodeName(paramNodeName);
		simpleTypeNode.setParentNode(paramParentNode);
		return simpleTypeNode;
	}
	/**
	 * This method will build the complex nodes from the text
	 * @param firstComplexNodes
	 * @param paramParentNode
	 * @return
	 */
	private List <ComplexTypeNode> buildComplexTypeNodeList(String firstComplexNodes, ComplexTypeNode paramParentNode) {
		List <ComplexTypeNode>  complexTypeNodesList = new <ComplexTypeNode> ArrayList();
		
		//System.out.println("firstComplexNodes: " + firstComplexNodes);
		// trim text 
		firstComplexNodes = firstComplexNodes.trim();
		// remove first [
		firstComplexNodes = firstComplexNodes.substring(1,firstComplexNodes.length() );
		//parse the string untill you get [ or ]
		char[] stringToCharArray = firstComplexNodes.toCharArray();
		 
		for (int i = 0;i<stringToCharArray.length;i++ ) {
			char eachChar = stringToCharArray[i];
			//System.out.println(eachChar);
			// check if it is ] or [
			
			if(eachChar == '[') {
				// System.out.println(" ends with [ this has sub complex node");
				//if it has [ then there is sub complex node inside this node
				// get the sub string which contains 
				String mainNodeText = firstComplexNodes.substring(0,i);
			//	System.out.println("mainNodeText: " + mainNodeText);
				mainNodeText = mainNodeText.trim();
				
				StringTokenizer tokenizer = new StringTokenizer(mainNodeText, ",");
				// first token will be complex 
				String firstComplexNode = tokenizer.nextToken().trim();
				// breakcomplex node by :
				StringTokenizer complexNodeTokens = new StringTokenizer(firstComplexNode, ":");
				String nodeName = complexNodeTokens.nextToken();
				String complexTypeName = complexNodeTokens.nextToken();
				
				//break the first node seperated by comma
				ComplexTypeNode complexTypeNode = new ComplexTypeNode();
				complexTypeNode.setNodeName(nodeName);
				complexTypeNode.setComplexTypeName(complexTypeName);
				complexTypeNode.setId(nodeCounter++);
				complexTypeNode.setParentNode(paramParentNode);
				
				// from second node it will be simple nodes
				List <SimpleTypeNode>  simpleTypeNodesList = new <SimpleTypeNode> ArrayList();
			    while (tokenizer.hasMoreElements()) {
				       // tokens.add(tokenizer.nextToken());
				    	String tokenText = tokenizer.nextToken().trim();
				       // System.out.println("tokens : "+ tokenText);
				        // build simple tree from all tokens
				        StringTokenizer nodeTokens = new StringTokenizer(tokenText, "="); 
				        while (nodeTokens.hasMoreElements()) {
				        	// first token will be node name second will be node value
				        	String smallNodeName = nodeTokens.nextToken().trim();
				        	int smallNodeValue = Integer.parseInt(nodeTokens.nextToken().trim());
				        	/// build simple node and add it to parent tree
				        	simpleTypeNodesList.add(buildSimpleTypeNode(nodeCounter++,smallNodeValue,smallNodeName,complexTypeNode));
				        }
				}
			    complexTypeNode.setSimpleNodesList(simpleTypeNodesList);
				
			    // add complex nodes iteratevely
			    String secondPartNodeText = firstComplexNodes.substring(i,firstComplexNodes.length());
			    
			    complexTypeNode.setComplexNodesList(buildComplexTypeNodeList(secondPartNodeText, complexTypeNode));
			    
			    complexTypeNodesList.add(complexTypeNode);
				break;
			}
			if(eachChar == ']') {
				//if it has ] then there is end of this complex node and has simple node
				// System.out.println(" ends with ] this complex node ends here");
				String mainNodeText = firstComplexNodes.substring(0,i);
				// System.out.println("mainNodeText: " + mainNodeText);
				mainNodeText = mainNodeText.trim();
				// remove last , fromthetext
				if((",").equals(mainNodeText.substring(mainNodeText.length() - 1))) {
					// remove the last , from string 
					mainNodeText = mainNodeText.substring(mainNodeText.length() - 1);
				}
				
				// remove last ] fromthetext
				if(("]").equals(mainNodeText.substring(mainNodeText.length() - 1))) {
					// remove the last ] from string 
					mainNodeText = mainNodeText.substring(mainNodeText.length() - 1);
				}
				//System.out.println("mainNodeText: after last 2 chars " + mainNodeText);
				

				StringTokenizer tokenizer = new StringTokenizer(mainNodeText, ",");
				// first token will be complex 
				String firstComplexNode = tokenizer.nextToken().trim();
				// breakcomplex node by :
				StringTokenizer complexNodeTokens = new StringTokenizer(firstComplexNode, ":");
				String nodeName = complexNodeTokens.nextToken();
				String complexTypeName = complexNodeTokens.nextToken();
				
				//break the first node seperated by comma
				ComplexTypeNode complexTypeNode = new ComplexTypeNode();
				complexTypeNode.setNodeName(nodeName);
				complexTypeNode.setComplexTypeName(complexTypeName);
				complexTypeNode.setId(nodeCounter++);
				complexTypeNode.setParentNode(paramParentNode);

				
				// from second node it will be simple nodes
				List <SimpleTypeNode>  simpleTypeNodesList = new <SimpleTypeNode> ArrayList();
			    while (tokenizer.hasMoreElements()) {
				       // tokens.add(tokenizer.nextToken());
				    	String tokenText = tokenizer.nextToken().trim();
				      //  System.out.println("tokens : "+ tokenText);
				        // build simple tree from all tokens
				        StringTokenizer nodeTokens = new StringTokenizer(tokenText, "="); 
				        while (nodeTokens.hasMoreElements()) {
				        	// first token will be node name second will be node value
				        	String smallNodeName = nodeTokens.nextToken().trim();
				        	int smallNodeValue = Integer.parseInt(nodeTokens.nextToken().trim());
				        	/// build simple node and add it to parent tree
				        	simpleTypeNodesList.add(buildSimpleTypeNode(nodeCounter++,smallNodeValue,smallNodeName,complexTypeNode));
				        }
				}
			    complexTypeNode.setSimpleNodesList(simpleTypeNodesList);
				
			    // add complex nodes iteratevely
			    String secondPartNodeText = firstComplexNodes.substring(i,firstComplexNodes.length());
			  //  System.out.println("secondPartNodeText : "+ secondPartNodeText);
			    // trium the white space
			    secondPartNodeText = secondPartNodeText.trim();
			    
			    //remove if first char is ]
				// remove last ] fromthetext
			   // System.out.println("secondPartNodeText afirst char  : "+ secondPartNodeText.substring(0,1));
				if(("]").equals(secondPartNodeText.substring(0,1))) {
					// remove the last ] from string 
					secondPartNodeText = secondPartNodeText.substring(1,secondPartNodeText.length());
				}
				//System.out.println("secondPartNodeText after removing ] :"+ secondPartNodeText);

				// remove last ] fromthetext
				if( secondPartNodeText.length() > 1 &&  (",").equals(secondPartNodeText.substring(0,1))) {
					// remove the last ] from string 
					secondPartNodeText = secondPartNodeText.substring(1,secondPartNodeText.length());
				}
				secondPartNodeText = secondPartNodeText.trim();
				//System.out.println("secondPartNodeText after all triming  : "+ secondPartNodeText);
			    
				// special check if it has only ]] means the 
				StringBuffer  specialEndCheck = new StringBuffer(secondPartNodeText.trim());
				//System.out.println("secondPartNodeText before  all triming  : "+ specialEndCheck);
				// remove all the ]] from specialEndCheck
		
			   for(int k = 0; k <specialEndCheck.length();k++ ){
				   int index = 0;
				   while((index= specialEndCheck.indexOf("]"))!=-1){
					   specialEndCheck.deleteCharAt(index);
				   }
			   }
					
			   //System.out.println("secondPartNodeText after all triming  : "+ specialEndCheck);
			    if(specialEndCheck.length() > 1) {
			    	complexTypeNode.setComplexNodesList(buildComplexTypeNodeList(secondPartNodeText, complexTypeNode));
			    }
			    
			    complexTypeNodesList.add(complexTypeNode);
				break;
			}
		}
		return complexTypeNodesList;
		
	}
	public static void main(String str[]) {
		// original input
		// sn1=1,sn2=2,sn3=3,[cn1: ComplexType1, cn1_sn1=42, [cn1_cn1: ComplexType2, cn1_cn1_sn1=4, cn1_cn1_sn2=5], [cn1_cn2: ComplexType2, cn1_cn2_sn1=6, cn1_cn2_sn2=7], [cn1_cn3: ComplexType2, [cn1_cn3_cn1: ComplexType2, cn1_cn3_cn1_sn1=2, cn1_cn3_cn1_sn2=0]]]
		// String inputText = "sn1=1,sn2=2,sn3=3, [cn1: ComplexType1, cn1_sn1=42]";
		String inputText = 		"sn1=1,sn2=2,sn3=3,[cn1: ComplexType1, cn1_sn1=42, [cn1_cn1: ComplexType2, cn1_cn1_sn1=4, cn1_cn1_sn2=5], [cn1_cn2: ComplexType2, cn1_cn2_sn1=6, cn1_cn2_sn2=7], [cn1_cn3: ComplexType2, [cn1_cn3_cn1: ComplexType2, cn1_cn3_cn1_sn1=2, cn1_cn3_cn1_sn2=0]]]";
//		
		//String inputText = 	"sn1=1,sn2=2,sn3=3,[cn1: ComplexType1, cn1_sn1=42, [cn1_cn1: ComplexType2, cn1_cn1_sn1=4, cn1_cn1_sn2=5], [cn1_cn2: ComplexType2, cn1_cn2_sn1=6, cn1_cn2_sn2=7], [cn1_cn3: ComplexType2, [cn1_cn3_cn1: ComplexType2, cn1_cn3_cn1_sn1=2, cn1_cn3_cn1_sn2=0]]]";	
		// String inputText = "sn1=1,sn2=2,sn3=3";
		//String inputText = "[cn1: ComplexType1, cn1_sn1=42]";
	    MessageParser ParseInput = new MessageParser(inputText);
		System.out.println(ParseInput.getDotFormat());
		
	}
}
