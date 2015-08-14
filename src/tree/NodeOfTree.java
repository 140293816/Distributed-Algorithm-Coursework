package tree;

import java.util.HashSet;
import java.util.LinkedHashSet;


public final class NodeOfTree extends AbstractNode {
	private LinkedHashSet<Integer> message=new LinkedHashSet<Integer>();
	
	
	public NodeOfTree(int id,HashSet<Integer> nei)
	{
		super(id, nei);
	}
	
	/**
	 * Receive messages from  its neighbors and change the corresponding value
	 * in the nei HashSet until the silent neighbor shows up
	 */	
	private void receive()
	{
		
		for(int i:message)
		{
			if(flagOfSN()<=1)break;
    		if(getNei().containsKey(i))
			{
				getNei().put(i, true);
				System.out.println("Node "+getID()+" receives a message from "+i);
			}
			
		}
		if(flagOfSN()>1){message.clear();}
		
		
	}
	
	/**
	 * Add the id of the message sender to the message list
	 * @param i
	 */
	private void addMessage(int i)
	{
		message.add(i);
	}
	
	/**
	 * Add its id to its neighbor's message list
	 */
	private void sendMessage()
	{
		Object obj=AbstractNode.selectNode(getSN());
		if(!(obj instanceof NodeOfTree)){throw new IllegalStateException("Error!");}
		((NodeOfTree)obj).addMessage(getID());
		System.out.println("Node "+getID()+" sends a message to "+getSN());
		setM();
	}
	
	/**
	 * decide() method is coded according to the pseudo-code of election algorithm;
     * if this node decides, this method returns true; otherwise it returns false.
	 */
	public boolean decide()
	{
		
		if(flagOfSN()==0)
		{
			System.out.println("Node "+getID()+" has finished the algorithm");
			return false;
		}
		
		receive();
		
		if(flagOfSN()==1)
		{
			if(getM()==false)
			{
				setSN();
				try{sendMessage();}catch(IllegalStateException e)
				{
					System.out.println("Failure in tree algorithm, invalid node:"+getID());
				}
			}
			
			if(message.contains(getSN()))
			{
			getNei().put(getSN(), true);
			System.out.println("Node "+getID()+" receives a message from "+getSN());
			return true;
			}
		
			
			System.out.println("Node "+getID()+ " is waiting for a message from its silent neighbour");
			
			
		}
		
		return false;
	}

}

