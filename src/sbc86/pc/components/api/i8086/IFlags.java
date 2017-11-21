package sbc86.pc.components.api.i8086;

/**
 * Interface for handling the processor's flags.
 *
 * @author Elias Ullrich
 * @created 11/2017
 */
public interface IFlags
{

	/**
	 * Enumeration listing all available flags.
	 */
	enum Flag
	{
		CF, // Carry
		PF, // Parity
		ZF, // Zero
		SF, // Signed
		OF, // Overflow
		DF, // Direction
		IF, // Interrupt-Enable
		TF, // Single-Step (Trap)
		AF  // Auxiliary Carry
	}


	/**
	 * Returns the current state of a single flag.
	 *
	 * @param f The target flag.
	 * @return The current state (bit value) of the flag.
	 */
	boolean getFlag(Flag f);

	/**
	 * Sets the state of a single flag to the given value.
	 *
	 * @param f The target flag that's content shall be changed.
	 * @param bit The new value for this flag.
	 */
	void setFlag(Flag f, boolean bit);
}

