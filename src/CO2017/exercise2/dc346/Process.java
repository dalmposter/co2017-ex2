package CO2017.exercise2.dc346;

/**
 * Representation of a process. Stores meta data about this process.
 * Implements a run method to simulate this process running. Uses given memManager to assign memory.
 * @author Dominic Cousins
 *
 */
public class Process implements Runnable
{
	//one character id for this process
	private char id;
	
	//memory needed for this process
	private int size;
	
	//expected runtime of this process
	private int runtime;
	
	//start address of allocated memory (-1 if unassigned)
	private int address;
	
	MemManager memManager;
	
	/**
	 * Creates a new process with given values. Initialises address to -1 (unassigned).
	 * @param memManager the memory manager to be used for memory allocation
	 * @param id the one character id for this process
	 * @param size the memory required by this process
	 * @param runtime the estimated runtime of this process
	 */
	public Process(MemManager memManager, char id, int size, int runtime)
	{
		this.id = id;
		this.size = size;
		this.runtime = runtime;
		this.memManager = memManager;
		address = -1;
	}

	public int getAddress()
	{
		return address;
	}

	public void setAddress(int address)
	{
		this.address = address;
	}

	public char getId()
	{
		return id;
	}

	public int getSize()
	{
		return size;
	}

	/**
	 * Simulate running this process.
	 * Tries to take memory equal to the size of this process. Waits until it can.
	 * Then waits for runtime and releases held memory. Prints status updates to the console.
	 */
	@Override
	public void run()
	{
		System.out.println(this + " waiting to run.");
		
		//TODO: might need to do something with this exception.
		try
		{
			memManager.allocate(this);
		} catch (InterruptedException e1)
		{
			e1.printStackTrace();
		}
		
		System.out.println(this + " running.");
		
		try
		{
			Thread.sleep(100 * runtime);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		memManager.free(this);
		
		System.out.println(this + " has finished.");
	}
	
	/**
	 * Get String representation of this process. Gives id character, working address and size.
	 */
	public String toString()
	{
		String ad;
		if(address < 0) ad = "  U";
		else
		{
			//get address and pad to 3 characters
			ad = Integer.toString(address);
			while(ad.length() < 3) ad = " " + ad;
		}
		
		//get size and pad to 3 characters
		String si = Integer.toString(size);
		while(si.length() < 2) si = " " + si;
		return id + ":" + ad + "+" + si;
	}
}
