package CO2017.exercise2.dc346;

public class FirstFitMemManager extends MemManager
{

	public FirstFitMemManager(int s)
	{
		super(s);
	}

	@Override
	protected int findSpace(int s)
	{
		int curr = 0;
			
		//loop until we reach the end of memory or we otherwise return a value.
		while(curr < _memory.length)
		{
			//get size of free space starting at curr. COULD BE ZERO.
			int size = countFreeSpacesAt(curr);
					
			//is the space enough?
			if(size > s)
			{
				//my job here is done, returning first fit
				return curr;
			}
				
			//increase curr to point to the next address not considered in calculating size.
			curr += Math.min(1, size);
		}
				
		//if this is called when no space of size s is available, the return will be -1 (not valid).
		return -1;
	}
}
