package sbc86.pc.components.api.i8086;

/**
 * Interface for handling the content of the processor's registers.
 *
 * @author Elias Ullrich
 * @created 11/2017
 */
public interface IRegister
{

	/**
	 * Returns the byte an 8-Bit register stores.
	 *
	 * @param reg8 The 8-Bit register.
	 * @return An unsigned byte (stored in a short variable), the content of the register.
	 */
	short getByte(Reg8 reg8);

	/**
	 * Returns the word a 16-Bit register stores.
	 *
	 * @param reg16 The 16-Bit register.
	 * @return A 16 bit char variable, the content of the register.
	 */
	char getWord(Reg16 reg16);


	/**
	 * Sets the content of an 8-Bit register.
	 *
	 * @param reg8 The target 8-Bit register.
	 * @param b The unsigned byte (wrapped in a short variable) to be stored in the register.
	 * @throws IllegalArgumentException if the given short-value is out of the range of an unsigned byte.
	 */
	void setByte(Reg8 reg8, short b) throws IllegalArgumentException;

	/**
	 * Sets the content of a 16-Bit register.
	 *
	 * @param reg16 The target 16-Bit register.
	 * @param w The unsigned word (2 byte) to be stored in the register.
	 */
	void setWord(Reg16 reg16, char w);
}
