package sbc86.pc.components.api.i8086;


/**
 * Enumeration listing all available commands on Intel's 8086 processor.
 *
 * @author Elias Ullrich
 * @created 11/2017
 */
public enum Commands
{
	MOV, XCHG, PUSH, POP, LAHF, SAHF, XLAT, LEA, LDS, LES,							// Transportbefehle
	IN, OUT,																		// I/O-Befehle
	ADD, ADC, DAA, AAA, INC, SUB, SBB, DAS, AAS, DEC, NEG,							// Arithmetische Befehle
	CMP, MUL, IMUL, AAM, DIV, IDIV, AAD, CBW, CWD,
	NOT, AND, TEST, OR, XOR,														// Logische Befehle
	SHL, SAL, SHR, SAR, ROL, ROR, RCL, RCR,											// Rotations- und Schiebebefehle
	JMP, JCXZ, LOOP, JC, JNC, JO, JNO, JS, JNS, JP, JPE, JNP, JPO, JZ, JNZ, JE, JNE,// Sprungbefehle
	JG, JNG, JGE, JNGE, JL, JNL, JLE, JNLE, JA, JNA, JAE, JNAE, JB, JNB, JBE, JNBE,
	CALL, RET,																		// Unterprogrammbefehle
	INT, IRET,																		// Interrupts
	MOVS, LODS, STOS, SCAS, CMPS, REP,												// Stringbefehle
	CLC, STC, CMC, CLD, STD, CLI, STI,												// Flagbefehle
	/*SR, TODO*/ NOP, HLT, WAIT, ESC, LOCK											// Sonstige Befehle

}