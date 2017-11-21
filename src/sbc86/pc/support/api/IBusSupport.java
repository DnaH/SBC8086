package sbc86.pc.support.api;

/**
 * Interface for general Bus support.
 * It should be implemented by all components that communicate via the system's bus.
 *
 * @author Elias Ullrich
 * @created 11/2017
 */
public interface IBusSupport
{

	/**
	 * Writes a given byte (wrapped in a short-value) to the implementing component, if possible.
	 *
	 * @param address The 16-Bit address of the target memory address or I/O port.
	 * @param b The byte (wrapped in a short value) to write.
	 */
	void writeByte(char address, short b);
	short readByte(char address);
}
