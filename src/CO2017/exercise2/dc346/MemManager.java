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
			_memory[s] = '.';
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
	public void allocate(Process p) throws java.lang.InterruptedException
	{
		//memory has now changed since last toString call
		_changed = true;
	}
	
	//TODO: implement
	public void free(Process p)
	{
		//memory has now changed since last toString call
		_changed = true;
	}
	
	//TODO: implement
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