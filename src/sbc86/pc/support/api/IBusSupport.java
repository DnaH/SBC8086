package sbc86.pc.support.api;

public interface IBusSupport
{
	void writeByte(char address, short b);
	short readByte(char address);
}
