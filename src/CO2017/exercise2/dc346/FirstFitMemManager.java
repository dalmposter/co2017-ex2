package CO2017.exercise2.dc346;

/**
 * A memory manager that uses the first fit method. Inherits from MemManager and implements findSpace().
 * @author Dominic Cousins
 *
 */
public class FirstFitMemManager extends MemManager
{

	public FirstFitMemManager(int s)
	{
		super(s);
	}

	/**
	 * Finds the right space in memory for size s. Assumes there is a space of at least size s so be careful!
	 * This implementation returns the space with the lowest index that has a size at least s.
	 * @return start index of found space
	 */
	@Override
	protected int findSpace(int s)
	{
		int curr = 0;
			
		//loop until we reach the end of memory or we otherwise return a value.
		while(curr < _memory.length)
		{
			//get size of free space starting at curr. COULD BE ZERO.
			int size = countFreeSpacesAt(curr);
			//System.out.println("FirstFitMemManager counted " + size + " free spaces at " + curr);
					
			//is the space enough?
			if(size >= s)
			{
				//my job here is done, returning first fit
				return curr;
			}
				
			//increase curr to point to the next address not considered in calculating size.
			curr += Math.max(1, size);
		}
				
		//if this is called when no space of size s is available, the return will be -1 (not valid).
		return -1;
	}
}
