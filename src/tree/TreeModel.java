package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;

public final class TreeModel {
	private final int N;
	private HashMap<Integer,HashSet<Integer>> model=new HashMap<Integer,HashSet<Integer>>();
	private static final Random ram=new Random();

	
	public  TreeModel(int N)	
	{
		if(N<7){throw new IllegalArgumentException("Error");}
		this.N=N;			
	}
	
	/**
	 * Show the neighbor list of each node.
	 */
	private void showNeighbours()
	{
		for(int i:model.keySet())
		{
			System.out.println("Neighbours of node "+i+": "+model.get(i));
			
		}
		System.out.println();
	}
	
	/**
	 * Generate an arbitrary tree:
	 * This tree does not have any branches, which means it only contains leaves and a root;
	 * thus in each iteration, each node gets connected to the root.
	 */
	public void arbitrary()
	{
		
		model.put(0,new HashSet<Integer>());
		for(int i=1;i<N;i++)
		  {
			  model.put(i,new HashSet<Integer>());
			  model.get(i).add(0);
			  model.get(0).add(i);		
		  }		
		System.out.println("Arbitrary tree:\n");
		showNeighbours();		
	}
	
	/**
	 * Build a complete tree:
	 * Each level is completely filled, except possibly the last and 
	 * all nodes are as far left as possible.
	 */
	public void balancedBinary()
	{
		model.put(0,new HashSet<Integer>());
		for(int i=1;i<N;i++)
		{
			model.put(i,new HashSet<Integer>());
			int j=(i-1)/2;
			model.get(i).add(j);
			model.get(j).add(i);
	    }
		System.out.println("Balanced binary tree:\n");	
		showNeighbours();
	}
	
	
	/**
	 * Build an unbalanced binary tree by restricting all the right
	 * descendants to have no child and force all the left descendants,
	 * except the last one, to have 2 children 
	 */
	public void unbalancedBinary()
	{
		model.put(0,new HashSet<Integer>());
		model.put(1,new HashSet<Integer>());
		model.put(2,new HashSet<Integer>());
		model.get(0).add(1);
		model.get(0).add(2);
		model.get(1).add(0);
		model.get(2).add(0);
		for(int i=3;i<N;i++)
		{
			model.put(i,new HashSet<Integer>());
			model.get(i).add(i-3+i%2);
			model.get(i-3+i%2).add(i);
		}
		System.out.println("Unbalanced binary tree:\n");
		showNeighbours();
	}
	
	/**
	 * Clear the present tree structure so that a new one could be built
	 */
	public void clear()
	{
		model.clear();
	}
	
	/**
	 * Test the Tree Algorithm:
	 * Construct N nodes according to the model HashMap;
	 * Execute 2*N rounds to test the algorithm;
	 * In each iteration, generate a random number R,
	 * choose R different random nodes to execute the protocol.
	 */
	public void treeA()
	{
		if(model.size()<N)
		{
			System.out.println("Invalid tree model");
			throw new IllegalStateException("Error!");
		}
		int rounds=0;
		
		System.out.println("Tree wave algorithm:");
		ArrayList<NodeOfTree> tree=new ArrayList<NodeOfTree>();			
		for(int i:model.keySet())
		{
			tree.add(new NodeOfTree(i,model.get(i)));
		}		
		
		
		for(int i=0;i<N*50;i++)
		{
			LinkedHashSet<Integer> group=new LinkedHashSet<Integer>();
			int R=ram.nextInt(N+1);
			while(group.size()<R)
			{
				group.add(ram.nextInt(N));
			}
			for(int j:group)
			{
			if(tree.get(j).decide())
			{
				System.out.println("Node "+tree.get(j).getID()+" decides in the "+i+"th iteration");
				rounds++;
			}
			}
		
		}		
		System.out.println("The number of processes which decide in tree algorithm:"+rounds+"\n" );
		
	}
	
	/**
	 * Test the Election Algorithm:
	 * Construct N nodes according to the model HashMap;
	 * Execute 2*N rounds to test the algorithm;
	 * In each iteration, generate a random number R,
	 * choose R different random nodes to execute the protocol.
	 */
	public void electionA()
	{
		if(model.size()<N)
		{
			System.out.println("Invalid tree model");
			throw new IllegalStateException("Error!");
		}
		int rounds=0;
		HashSet<Integer> leader=new HashSet<Integer>();
	
		System.out.println("Election algorithm:");
		ArrayList<NodeOfElection> tree=new ArrayList<NodeOfElection>();			
		for(int i:model.keySet()){tree.add(new NodeOfElection(i,model.get(i)));}
		
		tree.get(ram.nextInt(N)).sendWakeMessage();		
		
		for(int i=0;i<N*50;i++)
		{
			LinkedHashSet<Integer> group=new LinkedHashSet<Integer>();
			int R=ram.nextInt(N+1);
			while(group.size()<R)
			{
				group.add(ram.nextInt(N));
			}
			for(int j:group)
			{
			if(tree.get(j).decide())
			{
				System.out.println("Node "+tree.get(j).getID()+" decides in the "+i+"th iteration");
				leader.add(tree.get(j).getMini());
				rounds++;
			}
			}
		}		
		System.out.println("The number of processes which decide in election algorithm:"+rounds);
		System.out.println("the leader which has the minimal ID is "+leader+"\n");		
	}
		
	
	

}
