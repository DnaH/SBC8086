package sbc86.pc.components.internal.memory;

import sbc86.pc.components.api.memory.IMemory;

public class Memory implements IMemory
{
	private short segment;		// es gibt 16 Segmente, effektive Adresse = segment * 64K + address
	private char[] memory; 		// 16-Bit-Chars, weil kein unsigned Byte verfügbar

	public Memory()
	{
		// lege Char-Array für Speicher an
		memory = new char[0x80000];
		segment = 0;
	}


	// ---------------------------------------------------------------------------
	// Segment-Modifikations-Methoden

	@Override
	public void setSegment(short segment) throws IllegalArgumentException
	{
		if(segment < 0 || segment > 15) // fehlerhafte Angabe
		{
			throw new IllegalArgumentException("Invalid segment: " + segment
					+ ". Segment number has to be between 0 and 15.");
		}

		this.segment = segment;
	}

	@Override
	public short getSegment()
	{
		return segment;
	}


	// ---------------------------------------------------------------------------
	// Byte-weise Methoden

	@Override
	public void writeByte(char address, short b)
	{
		// Berechnung der effektiven Adresse zum Zugriff auf das Array
		int effectiveAddress = this.segment * SEGMENT_SIZE + (char) (address / 2);

		if (checkRAM(effectiveAddress)) // RAM oder ROM? -> nur in RAM schreiben
		{
			if ((address & 1) == 1) // ungerade Adresse -> Byte wird an obere Stelle des Wortes geschrieben
			{
				this.memory[effectiveAddress] = (char) (((b & 0xFF) << 8) + (char) (this.memory[effectiveAddress] & 0xFF));
			} else // gerade Adresse -> Byte wird an untere Stelle des Wortes geschrieben
			{
				this.memory[effectiveAddress] = (char) ((this.memory[effectiveAddress] & 0xFF00) + (b & 0xFF));
			}
		}
	}

	@Override
	public void writeByte(short segment, char address, short b)
	{
		setSegment(segment);
		writeByte(address, b);
	}


	@Override
	public short readByte(char address)
	{
		// Berechnung der effektiven Adresse zum Zugriff auf das Array
		int effectiveAddress = this.segment * SEGMENT_SIZE + (char) (address / 2);

		if((address & 1) == 1) // ungerade Adresse -> oberes Byte
		{
			return (short) ((this.memory[effectiveAddress] & 0xFF00) >> 8);
		}
		else // gerade Adresse -> unteres Byte
		{
			return (short) (this.memory[effectiveAddress] & 0xFF);
		}
	}


	@Override
	public short readByte(short segment, char address)
	{
		setSegment(segment);
		return readByte(address);
	}


	// ---------------------------------------------------------------------------
	// Wort-weise Methoden

	@Override
	public void writeWord(char address, char w)
	{
		// Berechnung der effektiven Adresse zum Zugriff auf das Array
		int effectiveAddress = this.segment * SEGMENT_SIZE + (char) (address / 2);

		if((address & 1) == 1) // ungerade Adresse -> gegebenes Wort muss auf zwei Wörter im Speicher aufgeteilt werden
		{
			// Aufteilen:
			char low = (char) ((w & 0x00FF) << 8);
			char high = (char) ((w & 0xFF00) >> 8);

			if(checkRAM(effectiveAddress))
			{
				// an effectiveAddress muss das high Byte verändert werden
				this.memory[effectiveAddress] = (char) ((this.memory[effectiveAddress] & 0xFF) + low);
			}
			effectiveAddress++;
			if(checkRAM(effectiveAddress))
			{
				// an effectiveAddress + 1 muss das low-Byte verändert werden
				this.memory[effectiveAddress] = (char) ((this.memory[effectiveAddress] & 0xFF00) + high);
			}
		}
		else // gerade Adresse -> nur ein Wort im Speicher muss verändert werden
		{
			if(checkRAM(effectiveAddress))
			{
				this.memory[effectiveAddress] = w; // einfachste Variante, zu bevorzugen
			}
		}

	}

	@Override
	public void writeWord(short segment, char address, char w)
	{
		setSegment(segment);
		writeWord(address, w);
	}

	/**
	 *
	 * @param address
	 * @return
	 */
	@Override
	public char readWord(char address)
	{
		// Berechnung der effektiven Adresse zum Zugriff auf das Array
		int effectiveAddress = this.segment * SEGMENT_SIZE + (char) (address / 2);

		if((address & 1) == 1) // ungerade Adresse -> zwei Wörter müssen gelesen werden
		{
			char low = (char) (this.memory[effectiveAddress] & 0xFF00); // low-Byte des fertigen Wertes ist high-Byte im Speicher
			char high = (char) (this.memory[effectiveAddress + 1] & 0xFF); // low-Byte im Speicher

			return (char) ((high << 8) + (low >> 8));
		}
		else // gerade Adresse -> nur ein Wort lesen, zurückgeben (einfachste Variante, zu bevorzugen)
		{
			return this.memory[effectiveAddress];
		}

	}

	@Override
	public char readWord(short segment, char address)
	{
		setSegment(segment);
		return readWord(address);
	}


	// nur zur Ausgabe:
	public char[] getMemory()
	{
		return memory;
	}

	// private Methoden:

	private boolean checkRAM(int address)
	{
		// Überprüft, ob die Adresse in den RAM zeigt, oder in den ROM
		return address < ROM_START;
	}
}
