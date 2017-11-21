package sbc86.pc.components.api.memory;

import sbc86.pc.support.api.IBusSupport;

public interface IMemory extends IBusSupport
{
	int SEGMENT_SIZE = 0x10000;
	int ROM_START = 0xC0000;

	// zusätzliche Funktionen, die mehrere Segmente unterstützen
	void setSegment(short segment) throws IllegalArgumentException;
	short getSegment();

	void writeByte(short segment, char address, short b);
	short readByte(short segment, char address);

	// zusätzliche Funktionen, um 16-Bit-Worte zusammen zu fassen
	void writeWord(short segment, char address, char w);
	char readWord(short segment, char address);
	void writeWord(char address, char w);
	char readWord(char address);
}
