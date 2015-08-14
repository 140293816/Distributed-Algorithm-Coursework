package test;

import static org.junit.Assert.*;

import org.junit.Test;

import tree.TreeModel;

public class TreeTest {

	@Test
	public void test() {
		final int N=10;
		System.out.println("Suppose that we have "+N+" nodes in \n");
		
		
		TreeModel tree1=new TreeModel(N);

/**
 * 		Choose a type of tree to create:
 */
		
//		tree1.arbitrary();		
		tree1.balancedBinary();
//		tree1.unbalancedBinary();
/**
 *     Choose a type of algorithm to test:
 */
	
//		tree1.treeA();
		tree1.electionA();
	}

}
