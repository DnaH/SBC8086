package sbc86.pc.components.api.memory;

import sbc86.pc.support.api.IBusSupport;

/**
 * Interface for the memory (RAM and ROM) and actions to read and write to it.
 *
 * @author Elias Ullrich
 * @created 11/2017
 */
public interface IMemory extends IBusSupport
{

	/**
	 * The byte size of one segment.
	 */
	int SEGMENT_SIZE = 0x10000;
	/**
	 * The first address of the read-only part of memory.
	 */
	int ROM_START = 0xC0000;


	// geerbte Funktionen von IBusSupport:

	/**
	 * Writes a given byte (wrapped in a short-value) into memory, if possible.
	 * Works like {@link #writeByte(short, char, short)}, but uses the previously given current segment.
	 *
	 * @param address The 16-Bit address of the target byte in memory.
	 * @param b The byte (wrapped in a short value) to write.
	 */
	@Override
	void writeByte(char address, short b);

	/**
	 * Reads a byte from the given address in memory.
	 * Works like {@link #readByte(short, char)}, but uses the previously given current segment.
	 *
	 * @param address The 16-Bit address of the target byte in memory.
	 * @return The desired byte (wrapped in a short value).
	 */
	@Override
	short readByte(char address);


	// zusätzliche Funktionen, die mehrere Segmente unterstützen

	/**
	 * Sets the currently used segment.
	 *
	 * @param segment The new segment number. <b>(Has to be between 0 and 15)</b>
	 * @throws IllegalArgumentException when the given short-value is not in range 0...15.
	 */
	void setSegment(short segment) throws IllegalArgumentException;

	/**
	 * Returns the currently used segment.
	 *
	 * @return The current segment's number.
	 */
	short getSegment();

	/**
	 * Writes a given byte (wrapped in a short-value) into memory, if possible.
	 *
	 * @param segment The segment number to use for the address.
	 * @param address The 16-Bit address of the target byte in memory to write to.
	 *                Has to be between 0 and 65536 (2 ^ 16).
	 *                Only the writable part of memory will be changed.
	 * @param b The new byte value. Independently from the given value,
	 *          the lower byte of this short value is used, the higher byte ignored.
	 */
	void writeByte(short segment, char address, short b);

	/**
	 * Reads a byte from the given address in memory.
	 *
	 * @param segment The segment number to use for the address.
	 * @param address The 16-Bit address of the target byte in memory to read.
	 *                Has to be between 0 and 65536 (2 ^ 16).
	 * @return The desired byte (wrapped in a short value).
	 */
	short readByte(short segment, char address);


	// zusätzliche Funktionen, um 16-Bit-Worte zusammen zu fassen

	/**
	 * Writes 2 bytes (a word) into memory, if possible.
	 *
	 * @param segment The segment number to use for the address.
	 * @param address The 16-Bit address of the first target byte (lower part of the word) in memory to write to.
	 *                Has to be between 0 and 65536 (2 ^ 16).
	 *                Only the writable part of memory will be changed.
	 * @param w The new word value (2 bytes stored in a char value) to write to memory.
	 *          The low byte is written to address = <i>address</i>,
	 *          the high byte is written to address = <i>address + 1</i>.
	 */
	void writeWord(short segment, char address, char w);

	/**
	 * Reads 2 bytes (a word) from the given address in memory.
	 *
	 * @param segment The segment number to use for the address.
	 * @param address The 16-Bit address of the first target byte in memory to read.
	 *                (Reads at address and address + 1).
	 * @return The desired 16-Bit word.
	 * 		   Its low byte is the one at <i>address</i>, its high byte is the one at <i>address + 1</i>.
	 */
	char readWord(short segment, char address);

	/**
	 * Writes 2 bytes (a word) into memory, if possible.
	 * Works like {@link #writeWord(short, char, char)}, but uses the previously given current segment.
	 *
	 * @param address The 16-Bit address.
	 * @param w The new word value.
	 */
	void writeWord(char address, char w);

	/**
	 * Reads 2 bytes (a word) from the given address in memory.
	 * Works like {@link #readWord(short, char)}, but uses the previously given current segment.
	 *
	 * @param address The 16-Bit address.
	 * @return The desired 16-Bit word.
	 */
	char readWord(char address);
}
