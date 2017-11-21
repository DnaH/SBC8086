package sbc86.pc.components.internal.i8086;

import sbc86.pc.components.api.i8086.IFlags;

public class Flags implements IFlags
{
	// eine Variable, die 16 Bit (alle Flags + unused) speichert
	private char flags;					//         hl
	private final char carryMask 		= 0b0000000000000001;
	private final char parityMask 		= 0b0000000000000100;
	private final char auxiliaryMask 	= 0b0000000000010000;
	private final char zeroMask 		= 0b0000000001000000;
	private final char signMask 		= 0b0000000010000000;
	private final char trapMask			= 0b0000000100000000;
	private final char interruptMask	= 0b0000001000000000;
	private final char directionMask	= 0b0000010000000000;
	private final char overflowMask		= 0b0000100000000000;

	@Override
	public boolean getFlag(Flag f)
	{
		switch (f)
		{
			case CF:
				return (flags & carryMask) == carryMask;
			case PF:
				return (flags & parityMask) == parityMask;
			case AF:
				return (flags & auxiliaryMask) == auxiliaryMask;
			case ZF:
				return (flags & zeroMask) == zeroMask;
			case SF:
				return (flags & signMask) == signMask;
			case TF:
				return (flags & trapMask) == trapMask;
			case IF:
				return (flags & interruptMask) == interruptMask;
			case DF:
				return (flags & directionMask) == directionMask;
			case OF:
				return (flags & overflowMask) == overflowMask;
			default:
				return false;
		}
	}

	@Override
	public void setFlag(Flag f, boolean bit)
	{
		switch (f)
		{
			case CF:
				if(bit) // setze Flag auf 1
				{
					flags = (char) (flags | carryMask);
				}
				else // setze Flag auf 0
				{
					flags = (char) (flags & ~carryMask);
				}
				break;
			case PF:
				if(bit)
				{
					flags = (char) (flags | parityMask);
				}
				else
				{
					flags = (char) (flags & ~parityMask);
				}
				break;
			case AF:
				if(bit)
				{
					flags = (char) (flags | auxiliaryMask);
				}
				else
				{
					flags = (char) (flags & ~auxiliaryMask);
				}
				break;
			case ZF:
				if(bit)
				{
					flags = (char) (flags | zeroMask);
				}
				else
				{
					flags = (char) (flags & ~zeroMask);
				}
				break;
			case SF:
				if(bit)
				{
					flags = (char) (flags | signMask);
				}
				else
				{
					flags = (char) (flags & ~signMask);
				}
				break;
			case TF:
				if(bit)
				{
					flags = (char) (flags | trapMask);
				}
				else
				{
					flags = (char) (flags & ~trapMask);
				}
				break;
			case IF:
				if(bit)
				{
					flags = (char) (flags | interruptMask);
				}
				else
				{
					flags = (char) (flags & ~interruptMask);
				}
				break;
			case DF:
				if(bit)
				{
					flags = (char) (flags | directionMask);
				}
				else
				{
					flags = (char) (flags & ~directionMask);
				}
				break;
			case OF:
				if(bit)
				{
					flags = (char) (flags | overflowMask);
				}
				else
				{
					flags = (char) (flags & ~overflowMask);
				}
				break;
		}
	}
}
