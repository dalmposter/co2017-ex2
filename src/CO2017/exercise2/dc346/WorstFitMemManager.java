package CO2017.exercise2.dc346;

public class WorstFitMemManager extends MemManager
{

	public WorstFitMemManager(int s)
	{
		super(s);
	}

	@Override
	protected int findSpace(int s)
	{
		// info about currently found worst fit. head: address, tail: size
		int[] worst = { -1, 0 };
		int curr = 0;

		// loop until we reach the end of memory or we otherwise return a value.
		while (curr < _memory.length)
		{
			// get size of free space starting at curr. COULD BE ZERO.
			int size = countFreeSpacesAt(curr);

			// is the space enough?
			if (size > s)
			{
				// is it worse than current worst? (or is there no worst)
				if (worst[0] < 0 || worst[1] < size)
				{
					worst[0] = curr;
					worst[1] = size;
				}
			}

			// increase curr to point to the next address not considered in calculating
			// size.
			curr += Math.max(1, size);
		}

		// if this is called when no space of size s is available, the return will be -1
		// (not valid).
		return worst[0];
	}
}
