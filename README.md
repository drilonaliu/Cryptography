# Cryptography
This is a cryptography package I wrote for my Data Security class from my University. Features: 

   * A simplified version of AES - which uses 16 bit block encryption and decryption, and a 16 bit key. Has SBOX, ShiftRows,
      MixColums, AddRoundKey and their inverses InvSBOX, InvShiftRows, InvMixColums (analogous to the AES algorithm).
      * Galois Field 16 -  A mathematical field which AES uses for binary arithmetic. 
   * A simplified version of SHA-1 - which uses 32 block sizes. Features three types of attacks 
       * A brute for force attack for finding the message from given the hash value.
       * A collision attack - Check if there is another message other than M, that has the same hash as M.
       * A birthday attack - for finding any two different messages that produce the same hash.
   * RSA  -  A complete version of RSA encryption algorithm build with the help of BigInteger java class.
       * Features attack when Bob is not careful when choosing prime numbers $p,q$; that is when $|p-q|<2n^{1/4}$.
   * HMAC with Simplified SHA-1 - A hash message authentication code written based on a scheme. 
   * Digital Signature Scheme - Uses simplified AES for encryption of the message, signs with RSA the hash of the message calculated with Simplified SHA-1
   * Block Ciphers - Features several modes of operation with any encryption algorithm:
       * ECB - Electronic Code Book mode
       * CBC - Cipher Block Chaining mode
       * CFB - Cipher Feedback mode
       * OFB - Output Feedback mode 
       * CTR - Counter Mode 
       * GCM - Galois Counter Mode
   * Relative Frequencies Attack - performs an attack on a text based on relative frequency distribution. The attacker supposes that the text was encrypted on a 
    alphabet mapping rule.
   * A Non Linear Feedback Shift Register - NLFSR which I designed.
    
    
    
   
       
       
