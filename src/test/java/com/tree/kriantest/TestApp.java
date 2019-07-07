package com.tree.kriantest;

import static org.junit.Assert.assertEquals;				
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.tree.kriantest.MessageParser;		

/*
@author Sridhar
 */

public class TestApp {


   @Test
   public void testDotformat_Case1() {		
    	String inputText = 		"sn1=1,sn2=2,sn3=3,[cn1: ComplexType1, cn1_sn1=42, [cn1_cn1: ComplexType2, cn1_cn1_sn1=4, cn1_cn1_sn2=5], [cn1_cn2: ComplexType2, cn1_cn2_sn1=6, cn1_cn2_sn2=7], [cn1_cn3: ComplexType2, [cn1_cn3_cn1: ComplexType2, cn1_cn3_cn1_sn1=2, cn1_cn3_cn1_sn2=0]]]";
    	String outputText= 			"Digraph G {\n"
    			+"	Node [style = rounded];\n"
    			+"	rankdir = LR ;\n"
    			+"\"com.tree.node.TreeOperations buildTree\" [shape=record label=\"buildTree |{ com.tree.node.TreeOperations | buildTree}\"]\n"
    			+"\"sn1=1\" [shape=record label=\"SimpleTypeNode|{sn1=1}\"]\n"
    			+"\"com.tree.node.TreeOperations buildTree\"->\"sn1=1\"[ label=\"1\"]\n"
    			+"\"com.tree.node.TreeOperations buildTree\" [shape=record label=\"buildTree |{ com.tree.node.TreeOperations | buildTree}\"]\n"
    			+"\"sn2=2\" [shape=record label=\"SimpleTypeNode|{sn2=2}\"]\n"
    			+"\"com.tree.node.TreeOperations buildTree\"->\"sn2=2\"[ label=\"2\"]\n"
    			+"\"com.tree.node.TreeOperations buildTree\" [shape=record label=\"buildTree |{ com.tree.node.TreeOperations | buildTree}\"]\n"
    			+"\"sn3=3\" [shape=record label=\"SimpleTypeNode|{sn3=3}\"]\n"
    			+"\"com.tree.node.TreeOperations buildTree\"->\"sn3=3\"[ label=\"3\"]\n"
    			+"\"com.tree.node.TreeOperations buildTree\" [shape=record label=\"buildTree |{ com.tree.node.TreeOperations | buildTree}\"]\n"
    			+"\"cn1= ComplexType1\" [shape=record label=\"ComplexTypeNode|{cn1= ComplexType1}\"]\n"
    			+"\"com.tree.node.TreeOperations buildTree\"->\"cn1= ComplexType1\"[ label=\"4\"]\n"
    			+"\"cn1_sn1=42\" [shape=record label=\"SimpleTypeNode|{cn1_sn1=42}\"]\n"
    			+"\"cn1= ComplexType1\"->\"cn1_sn1=42\"[ label=\"5\"]\n"
    			+"\"cn1_cn1= ComplexType2\" [shape=record label=\"ComplexTypeNode|{cn1_cn1= ComplexType2}\"]\n"
    			+"\"cn1= ComplexType1\"->\"cn1_cn1= ComplexType2\"[ label=\"6\"]\n"
    			+"\"cn1_cn1_sn1=4\" [shape=record label=\"SimpleTypeNode|{cn1_cn1_sn1=4}\"]\n"
    			+"\"cn1_cn1= ComplexType2\"->\"cn1_cn1_sn1=4\"[ label=\"7\"]\n"
    			+"\"cn1_cn1_sn2=5\" [shape=record label=\"SimpleTypeNode|{cn1_cn1_sn2=5}\"]\n"
    			+"\"cn1_cn1= ComplexType2\"->\"cn1_cn1_sn2=5\"[ label=\"8\"]\n"
    			+"\"cn1_cn2= ComplexType2\" [shape=record label=\"ComplexTypeNode|{cn1_cn2= ComplexType2}\"]\n"
    			+"\"cn1_cn1= ComplexType2\"->\"cn1_cn2= ComplexType2\"[ label=\"9\"]\n"
    			+"\"cn1_cn2_sn1=6\" [shape=record label=\"SimpleTypeNode|{cn1_cn2_sn1=6}\"]\n"
    			+"\"cn1_cn2= ComplexType2\"->\"cn1_cn2_sn1=6\"[ label=\"10\"]\n"
    			+"\"cn1_cn2_sn2=7\" [shape=record label=\"SimpleTypeNode|{cn1_cn2_sn2=7}\"]\n"
    			+"\"cn1_cn2= ComplexType2\"->\"cn1_cn2_sn2=7\"[ label=\"11\"]\n"
    			+"\"cn1_cn3= ComplexType2\" [shape=record label=\"ComplexTypeNode|{cn1_cn3= ComplexType2}\"]\n"
    			+"\"cn1_cn2= ComplexType2\"->\"cn1_cn3= ComplexType2\"[ label=\"12\"]\n"
    			+"\"cn1_cn3_cn1= ComplexType2\" [shape=record label=\"ComplexTypeNode|{cn1_cn3_cn1= ComplexType2}\"]\n"
    			+"\"cn1_cn3= ComplexType2\"->\"cn1_cn3_cn1= ComplexType2\"[ label=\"13\"]\n"
    			+"\"cn1_cn3_cn1_sn1=2\" [shape=record label=\"SimpleTypeNode|{cn1_cn3_cn1_sn1=2}\"]\n"
    			+"\"cn1_cn3_cn1= ComplexType2\"->\"cn1_cn3_cn1_sn1=2\"[ label=\"14\"]\n"
    			+"\"cn1_cn3_cn1_sn2=0\" [shape=record label=\"SimpleTypeNode|{cn1_cn3_cn1_sn2=0}\"]\n"
    			+"\"cn1_cn3_cn1= ComplexType2\"->\"cn1_cn3_cn1_sn2=0\"[ label=\"15\"]\n"
    			+"}";
		MessageParser parseInput = new MessageParser(inputText);
		
		assertEquals(parseInput.getDotFormat(), outputText);			
    }
   @Test(expected=NullPointerException.class)
   public void testDotformat_NullCheck(){
	   new MessageParser(null);
			
   }
   	
   @Test(expected=IllegalArgumentException.class)
   public void testDotformat_InvalidInputCheck(){
	   new MessageParser("Invalid Text");
	   
   }
      
   @Test
   public void testDotformat_OnlySimpleNodesCheck(){
	   String inputText = "sn1=1,sn2=2,sn3=3";
	   String outputText = "Digraph G {\n"
			   +"	Node [style = rounded];\n"
			   +"	rankdir = LR ;\n"
			   +"\"com.tree.node.TreeOperations buildTree\" [shape=record label=\"buildTree |{ com.tree.node.TreeOperations | buildTree}\"]\n"
			   +"\"sn1=1\" [shape=record label=\"SimpleTypeNode|{sn1=1}\"]\n"
			   +"\"com.tree.node.TreeOperations buildTree\"->\"sn1=1\"[ label=\"1\"]\n"
			   +"\"com.tree.node.TreeOperations buildTree\" [shape=record label=\"buildTree |{ com.tree.node.TreeOperations | buildTree}\"]\n"
			   +"\"sn2=2\" [shape=record label=\"SimpleTypeNode|{sn2=2}\"]\n"
			   +"\"com.tree.node.TreeOperations buildTree\"->\"sn2=2\"[ label=\"2\"]\n"
			   +"\"com.tree.node.TreeOperations buildTree\" [shape=record label=\"buildTree |{ com.tree.node.TreeOperations | buildTree}\"]\n"
			   +"\"sn3=3\" [shape=record label=\"SimpleTypeNode|{sn3=3}\"]\n"
			   +"\"com.tree.node.TreeOperations buildTree\"->\"sn3=3\"[ label=\"3\"]\n"
			   +"}";

	   MessageParser parseInput = new MessageParser(inputText);	   
	   assertEquals(parseInput.getDotFormat(), outputText);
	   
   }
   @Test
   public void testDotformat_OnlyComplexCheck(){
	   String inputText = "[cn1: ComplexType1, cn1_sn1=42]";
   	String outputText= "Digraph G {\n"
			+"	Node [style = rounded];\n"
			+"	rankdir = LR ;\n"
			+"\"com.tree.node.TreeOperations buildTree\" [shape=record label=\"buildTree |{ com.tree.node.TreeOperations | buildTree}\"]\n"
			+"\"cn1= ComplexType1\" [shape=record label=\"ComplexTypeNode|{cn1= ComplexType1}\"]\n"
			+"\"com.tree.node.TreeOperations buildTree\"->\"cn1= ComplexType1\"[ label=\"1\"]\n"
			+"\"cn1_sn1=42\" [shape=record label=\"SimpleTypeNode|{cn1_sn1=42}\"]\n"
			+"\"cn1= ComplexType1\"->\"cn1_sn1=42\"[ label=\"2\"]\n"
			+"}";

	   MessageParser parseInput = new MessageParser(inputText);	   
	   assertEquals(parseInput.getDotFormat(), outputText);
	   
   }
   


  
}	
