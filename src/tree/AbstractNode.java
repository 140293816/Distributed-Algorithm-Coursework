package tree;
import java.util.HashMap;
import java.util.HashSet;


            
public abstract class AbstractNode {
	
	private final int ID;
	private boolean flagOfM=false;	
	private Integer sn=null;
	private static HashMap<Integer, AbstractNode> treeNodes=new HashMap<Integer, AbstractNode>();
	private HashMap<Integer,Boolean> nei=new HashMap<Integer,Boolean>();
	
	                  
	/**
	 * Construct a node with the given id and neighbor list
	 * @param id
	 * @param nei
	 */
	public AbstractNode(int id,HashSet<Integer> nei)
	{
		if(nei==null||nei.size()==0){throw new IllegalArgumentException("Error!");}	                	  
		this.ID=id;
		for(int i:nei)
		{
		this.nei.put(i,false);
		}
		treeNodes.put(ID, this);
	}
	
	
	
	
	/**
	 * Clear the elements in treeNodes
	 */
	public static void clear(){treeNodes.clear();}
	
	/**
	 * Return an existing object in treeNodes according its id
	 * @param i
	 * @return
	 */
	public static AbstractNode selectNode(int i)
	{
		return treeNodes.get(i);
	}
	
	
	public int getID(){return ID;}
	
	protected HashMap<Integer,Boolean> getNei(){return nei;} 
	
	public boolean getM(){return flagOfM;}
	
	protected void setM(){flagOfM=true;}
	  
	/**
	 * Calculate the number of neighbors from which the node have not received any message;
	 * If this method returns 1, that means the silent neighbor has shown up.
	 * @return
	 */
	public int flagOfSN()
	{
		if(!nei.containsValue(false)){return 0;}
		int j=0;
		for(int i:nei.keySet())
		{
			if(!nei.get(i)){j++;}
		}
		return j;
	}
	
	/**
	 * Store the ID of silent neighbor in sn;
	 * Actually it is just assigning the first key of which the value is false to sn,
	 * so before using this method we have to make sure flagOfSN() returns 1.
	 */
	protected void setSN()
	{
		for(int i:nei.keySet())
		{
			if(!nei.get(i)){sn=i;break;}
		}
	    
	}
	
	public int getSN()
	{
		if(sn==null){throw new IllegalStateException("Error!");}
		return sn;
	}
	
	public abstract boolean decide();
	
	
}
