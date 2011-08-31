package org.albianj.security;

public enum DESStyle
{
	DES, // key size must be equal to 56
	DESede, // (TripleDES) key size must be equal to 112 or 168
	AES, // key size must be equal to 128, 192 or 256,but 192 and 256 bits may
			// not be available
	Blowfish, // key size must be multiple of 8, and can only range from 32 to
				// 448 (inclusive)
	RC2, // key size must be between 40 and 1024 bits
	RC4, // (ARCFOUR) key size must be between 40 and 1024 bits
}
