package CO2017.exercise2.dc346;

public class Process implements Runnable
{
	private char id;
	
	//memory needed for this process
	private int size;
	
	//expected runtime of this process
	private int runtime;
	
	//start address of allocated memory (-1 if unassigned)
	private int address;
	
	MemManager memManager;
	
	public Process(MemManager memManager, char id, int size, int runtime)
	{
		this.id = id;
		this.size = size;
		this.runtime = runtime;
		this.memManager = memManager;
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

	@Override
	public void run()
	{
		System.out.println(this + " waiting to run.");
		
		//TODO allocate memory
		
		System.out.println(this + " running.");
		
		try
		{
			Thread.sleep(100 * runtime);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		//TODO release memory
		
		System.out.println(this + " has finished.");
	}
	
	public String toString()
	{
		String ad;
		if(address < 0) ad = " U ";
		else
		{
			//get address and pad to 3 characters
			ad = Integer.toString(address);
			while(ad.length() < 3) ad = " " + ad;
		}
		
		//get size and pad to 3 characters
		String si = Integer.toString(size);
		while(si.length() < 3) si = " " + si;
		return id + ":" + ad + "+" + si;
	}
}
