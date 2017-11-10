package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class Lab1Test {

	@Test
	public void testQueryBridgeWords1() {
		Lab1 lab1test=new Lab1();
		Graph G1=lab1test.createDirectedGraph("file1.txt");
		String actualresult=lab1test.queryBridgeWords(G1, "can", "help");
		
		String expectresult="No bridge words from \"can\" to \"help\"!" ;
		assertEquals(expectresult,actualresult);
	}
	@Test
	public void testQueryBridgeWords2() {
		Lab1 lab1test=new Lab1();
		Graph G1=lab1test.createDirectedGraph("file1.txt");
		String actualresult=lab1test.queryBridgeWords(G1, "english", "123");
		
		String expectresult="No \"english\" or \"123\" in the graph!" ;
		assertEquals(expectresult,actualresult);
	}
	@Test
	public void testQueryBridgeWords3() {
		Lab1 lab1test=new Lab1();
		Graph G1=lab1test.createDirectedGraph("file1.txt");
		String actualresult=lab1test.queryBridgeWords(G1, "english", "more");
		
		String expectresult="The bridge words from \"english\" to \"more\" are: becoming " ;
		assertEquals(expectresult,actualresult);
	}
	
	@Test
	public void testQueryBridgeWords4() {
		Lab1 lab1test=new Lab1();
		Graph G1=lab1test.createDirectedGraph("file1.txt");
		String actualresult=lab1test.queryBridgeWords(G1, "english", "aa");
		
		String expectresult="No \"english\" or \"aa\" in the graph!" ;
		assertEquals(expectresult,actualresult);
	}
	
	@Test
	public void testQueryBridgeWords5() {
		Lab1 lab1test=new Lab1();
		Graph G1=lab1test.createDirectedGraph("file1.txt");
		String actualresult=lab1test.queryBridgeWords(G1, "a", "job");
		
		String expectresult="The bridge words from \"a\" to \"job\" are: better useful " ;
		assertEquals(expectresult,actualresult);
	}
	
	@Test
	public void testQueryBridgeWords6() {
		Lab1 lab1test=new Lab1();
		Graph G1=lab1test.createDirectedGraph("file1.txt");
		String actualresult=lab1test.queryBridgeWords(G1, "can", "help");
		
		String expectresult="No bridge words from \"can\" to \"help\"!" ;
		assertEquals(expectresult,actualresult);
	}
	
	
	

}
