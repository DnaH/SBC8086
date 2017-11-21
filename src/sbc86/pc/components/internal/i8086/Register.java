package sbc86.pc.components.internal.i8086;

import sbc86.pc.components.api.i8086.Reg16;
import sbc86.pc.components.api.i8086.Reg8;
import sbc86.pc.components.internal.memory.Memory;

public class Register implements sbc86.pc.components.api.i8086.IRegister
{
	// initialisieren aller Register
	private short al = 0;
	private short ah = 0;
	private short bl = 0;
	private short bh = 0;
	private short cl = 0;
	private short ch = 0;
	private short dl = 0;
	private short dh = 0;

	private char si = 0;
	private char di = 0;
	private char bp = 0;

	private char pc = 0;
	private char sp = 0;

	private char ss = 0;
	private char cs = 0;
	private char ds = 0;
	private char es = 0;


	@Override
	public short getByte(Reg8 reg8)
	{
		switch (reg8)
		{
			case AH:
				return ah;
			case AL:
				return al;
			case BH:
				return bh;
			case BL:
				return bl;
			case CH:
				return ch;
			case CL:
				return cl;
			case DH:
				return dh;
			case DL:
				return dl;
			default:
				return 0;
		}
	}

	@Override
	public char getWord(Reg16 reg16)
	{
		switch (reg16)
		{
			case AX:
				return (char) (((ah & 0xff) << 8) | (al & 0xff)); // Zusammenfassung von AL und AH zu AX
			case BX:
				return (char) (((bh & 0xff) << 8) | (bl & 0xff));
			case CX:
				return (char) (((ch & 0xff) << 8) | (cl & 0xff));
			case DX:
				return (char) (((dh & 0xff) << 8) | (dl & 0xff));
			case SI:
				return si;
			case DI:
				return di;
			case BP:
				return bp;
			case PC:
				return pc;
			case SP:
				return sp;
			case SS:
				return ss;
			case CS:
				return cs;
			case DS:
				return ds;
			case ES:
				return es;
			default:
				return 0;
		}
	}

	@Override
	public void setByte(Reg8 reg8, short b)
			throws IllegalArgumentException
	{
		// Wertebereich überprüfen
		if((b & 0xFF) != 0) // nicht nur die letzten 8 bit genutzt
		{
			throw new IllegalArgumentException("Invalid byte. The given short-value exceeds 8-Bit: " + b);
		}

		switch (reg8)
		{
			case AH:
				ah = b;
				break;
			case AL:
				al = b;
				break;
			case BH:
				bh = b;
				break;
			case BL:
				bl = b;
				break;
			case CH:
				ch = b;
				break;
			case CL:
				cl = b;
				break;
			case DH:
				dh = b;
				break;
			case DL:
				dl = b;
		}
	}

	@Override
	public void setWord(Reg16 reg16, char w)
	{
		switch (reg16)
		{
			case AX:
				al = (short) (w & 0xff); // Aufteilen von AX zu AH und AL
				ah = (short) (w >> 8);
				break;
			case BX:
				bl = (short) (w & 0xff);
				bh = (short) (w >> 8);
				break;
			case CX:
				cl = (short) (w & 0xff);
				ch = (short) (w >> 8);
				break;
			case DX:
				dl = (short) (w & 0xff);
				dh = (short) (w >> 8);
				break;
			case SI:
				si = w;
				break;
			case DI:
				di = w;
				break;
			case BP:
				bp = w;
				break;
			case PC:
				pc = w;
				break;
			case SP:
				sp = w;
				break;
			case SS:
				ss = w;
				break;
			case CS:
				cs = w;
				break;
			case DS:
				ds = w;
				break;
			case ES:
				es = w;
				break;
		}
	}


	public static void main(String[] args)
	{
		char flags = 0b1010101010101011;
		char mask = 0b0000000000000001;
		System.out.println(Integer.toBinaryString(flags & ~mask));
	}

}