package tree;

import java.util.HashSet;
import java.util.LinkedHashMap;

public final class NodeOfElection extends AbstractNode  {	
	private boolean flagOfW=false;
	private boolean flagOfS=false;
	private int messageOfW=0;
	private HashSet<Integer> wakeMessage=new HashSet<Integer>();
	private int mini;	
	private LinkedHashMap<Integer,Integer> message=new LinkedHashMap<Integer,Integer>();
	
	
	
	public NodeOfElection(int id,HashSet<Integer> nei)

	{
		super(id, nei);
		mini=id;		
	}
	
	
	
	public int getMini(){return mini;}	
	
	/**
	 * Add the id of the message sender to the message list
	 * @param i
	 */
	private void receiveWakeMessage(int i){wakeMessage.add(i);}
	
	/**
	 * Send a wake-up message to a certain neighbor
	 */
	private void wakemessage(int i)
	{
		Object obj=selectNode(i);		
		if(!(obj instanceof NodeOfElection)){throw new IllegalStateException("Error!");}
        ((NodeOfElection)obj).receiveWakeMessage(getID());
        System.out.println("Node "+getID()+" sends a wake-up message to "+i);
	}
	
	/**
	 * Send wake messages to all its neighbors by adding its id to the neighbors'
	 * wake-up message list
	 */
	public void sendWakeMessage()
	{
		flagOfS=true;
		System.out.println("Node "+getID()+" wakes up and send messages to all its neighbours");
		for(int i:getNei().keySet())
		{
			try{wakemessage(i);}catch(IllegalStateException e)
			{
				System.out.println("Failure in sendwakemessage, invalid node:"+ getID());
			}
			
			
			
		}
		
	}
	
	/**
	 * Add a message to the LinkedHashMap, i is the id of the sender while j is the minimal token
	 * @param i
	 * @param j
	 */
    private void addMessage(int i, int j)
	{
		message.put(i, j);
	}
	
	
	/**
	 * Receive messages from  its neighbors, change the corresponding value
	 * in the nei HashSet and update the minimal token until the silent neighbor shows up
	 */
	private void receive()
	{
		
		for(int i:message.keySet())
		{	
			if(flagOfSN()<=1)break;
			if(getNei().containsKey(i))
			{
				mini=Math.min(message.get(i), mini);
				getNei().put(i,true);
				System.out.println("Node "+getID()+" receives a message from "+i);
			}			
		}
		if(flagOfSN()>1){message.clear();}
		
	}
	
	/**
	 * Send a message to the silent neighbor's message list 
	 */
	private void sendMessage( int i)
	{
		Object obj=AbstractNode.selectNode(i);	
		if(!(obj instanceof NodeOfElection)){throw new IllegalStateException("Error!");}
        ((NodeOfElection)obj).addMessage(getID(),mini);		
        System.out.println("Node "+getID()+" sends a message to "+i);
		setM();
	}
	
	

    /**
     * decide() method is coded according to the pseudo-code of tree algorithm,
     * if this node decides, this method returns true; otherwise it returns false.
     */
	public boolean decide()
	{		
		if(flagOfSN()==0)
		{
			System.out.println("Node "+getID()+" has finished the algorithm.");
			return false;
		}
		if(!flagOfW)
		{
			for(int i:wakeMessage)
			{
				if(getNei().containsKey(i))
				{messageOfW++;}
			}
			wakeMessage.clear();
			if(messageOfW>0&&flagOfS==false)
			{
				sendWakeMessage();
				
			}
			if(messageOfW>=getNei().size())
			{
				System.out.println("Node "+getID()+" receives messages from all its neighbours and starts to execute wave algorithm");
				flagOfW=true;
			}
		}
		if(flagOfW)
		{	
		receive();
		if(flagOfSN()==1)
		{
		if(getM()==false)
		{
			setSN();			
			try{sendMessage(getSN());}
			catch(IllegalStateException e)
			{
				System.out.println("Failure in election algorithm, invalid node:"+getID());
				
			}			
			
		}
		if(message.containsKey(getSN()))
		{
			mini=Math.min(mini, message.get(getSN()));
			getNei().put(getSN(),true);
			System.out.println("Node "+getID()+" receives a message from "+getSN());
			if(getID()==mini)
			{System.out.println("Node " +getID()+" is the leader");}
			else{System.out.println("Node "+getID()+" loses");}	
			for(int i:getNei().keySet())
			{
				if(i!=getSN())sendMessage(i);
			}
			return true;			
		}
		System.out.println("Node "+getID()+ " is waiting for a message from its silent neighbour");
		}
		}
		return false;
	}
}
	
		
		
		


