package CO2017.exercise2.dc346;

public abstract class MemManager
{
	//has state of memory changed since last call of toString()?
	boolean _changed;
	//size of largest free block of memory
	int _largestSpace;
	//array representation of the memory
	char[] _memory;
	
	//done
	public MemManager(int s)
	{
		_largestSpace = s;
		_changed = true;
		
		_memory = new char[s];
		for(int i = 0; i < s; i++)
		{
			_memory[i] = '.';
		}
	}
	
	public boolean isChanged()
	{
		return _changed;
	}
	
	protected abstract int findSpace(int s);
	
	int countFreeSpacesAt(int pos)
	{
		int out = 0;
		while(true)
		{
			//reached end of array
			if(pos >= _memory.length) return out;
			//if pos is empty, increment pos and out
			if(_memory[pos] == '.')
			{
				out++;
				pos++;
			}
			//if pos is full return out
			else return out;
		}
	}

	//TODO: implement
	public synchronized void allocate(Process p) throws java.lang.InterruptedException
	{
		System.out.println(p + " called allocate()");
		//not enough space, waiting
		while(p.getSize() > _largestSpace) wait();
		
		System.out.println(p + " passed guard on allocate()");
		
		//start address of free space found
		int foundAddress = findSpace(p.getSize());
		//the address immediately after the space to use for p
		int endAddress = foundAddress + p.getSize();
		
		System.out.println(p + " calculated start and end address : " + foundAddress + ", " + endAddress);
		
		//assign the space
		p.setAddress(foundAddress);
		for(int i = foundAddress; i < endAddress; i++)
		{
			_memory[i] = p.getId();
		}
		
		System.out.println(p + " finished assigning it's memory");
		
		//update _largestSpace
		_largestSpace = 0;
		int curr = 0;
		while(curr < _memory.length)
		{
			//size of space at curr
			int thisPass = countFreeSpacesAt(curr);
			_largestSpace = Math.max(_largestSpace, thisPass);
			//jump to the address after the free block beginning at curr
			curr += Math.max(1, thisPass);
		}
		
		System.out.println(p + " finished updating _largestSpace");
		
		//memory has now changed since last toString call
		_changed = true;
		
		notifyAll();
		
		System.out.println(p + " notified all");
	}
	
	//TODO: implement
	public synchronized void free(Process p)
	{
		//wipe memory used by p
		//store end address of p in a variable to save the loop recalculating each pass
		int end = p.getAddress() + p.getSize();
		for(int i = p.getAddress(); i < end; i++)
		{
			_memory[i] = '.';
		}
		
		p.setAddress(-1);
		
		//update _largestSpace
		_largestSpace = 0;
		int curr = 0;
		while(curr < _memory.length)
		{
			//size of space at curr
			int thisPass = countFreeSpacesAt(curr);
			_largestSpace = Math.max(_largestSpace, thisPass);
			//jump to the address after the free block beginning at curr
			curr += Math.max(1, thisPass);
		}
		
		//memory has now changed since last toString call
		_changed = true;
		
		notifyAll();
	}
	
	public String toString()
	{
		_changed = false;
		String out = "";
		
		for(int i = 0; i < _memory.length; i++)
		{
			//start a new row
			if(i % 20 == 0)
			{
				String ad = Integer.toString(i);
				while(ad.length() < 3) ad = " " + ad;
				out += ad + "|";
			}
			
			//print current address
			out += _memory[i];
			
			//end current row
			if(i % 20 == 19 || i == _memory.length - 1)
			{
				out += "|\n";
			}
		}
		
		out += "ls: " + _largestSpace;
		
		return out;
	}
}