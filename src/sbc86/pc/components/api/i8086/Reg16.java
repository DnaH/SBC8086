package sbc86.pc.components.api.i8086;

/**
 * Enumeration listing all available 16-Bit registers.
 *
 * @author Elias Ullrich
 * @created 11/2017
 */
public enum Reg16
{
	AX, BX, CX, DX, // Zusammenfassung von 8-Bit-Registern
	SI, DI, BP, 	// allg. Register
	PC, SP, 		// Spezielle Register
	SS, DS, CS, ES	// Segment-Register
}
