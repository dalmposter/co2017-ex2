package CO2017.exercise2.dc346;

public class BestFitMemManager extends MemManager
{

	public BestFitMemManager(int s)
	{
		super(s);
	}

	@Override
	protected int findSpace(int s)
	{
		//info about currently found best fit. head: address, tail: size
		int[] best = {-1, 0};
		int curr = 0;
		
		//loop until we reach the end of memory or we otherwise return a value.
		while(curr < _memory.length)
		{
			//get size of free space starting at curr. COULD BE ZERO.
			int size = countFreeSpacesAt(curr);
			
			//is the space enough?
			if(size > s)
			{
				//is it better than current best? (or is there no best)
				if(best[0] < 0 || best[1] > size)
				{
					best[0] = curr;
					best[1] = size;
				}
			}
			
			//increase curr to point to the next address not considered in calculating size.
			curr += Math.min(1, size);
		}
		
		//if this is called when no space of size s is available, the return will be -1 (not valid).
		return best[0];
	}
}
