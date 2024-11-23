package EncryptionMachine;

import java.util.Scanner;

/**
 * This class represents an encryption machine using a caeser cipher
 * 
 * @author Alycia Christiansen
 * @version 1.0
 * @since 9/29/2024
 */
public class EncriptionMachine {
	
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	public static int SHIFT = 3;

	/**
	 * Default function for the class to run the caeser cipher prompt
	 * @param args default parameters
	 */
	public static void main(String[] args) {
		runCaeserCipherEncyptionPrompt();
	}

	/**
	 * Prompts the user for a message to encrypt using a caeser cipher
	 */
	public static void runCaeserCipherEncyptionPrompt() {
		Scanner scan = new Scanner(System.in);
		/*Introduction*/
		System.out.println("Welcome to the CSCI717 Encryption Machine Construction\r\n"
				 + "The program lets you encrypt a message\r\n"
				 + "with a key for your recipient to decrypt!");
		
		encryptingPrompt(scan, "Enter Key:");
		
		/*How many words we'll encrypt*/
		System.out.println("How many words is your message?:");		
		int words = scan.nextInt();
		scan.nextLine(); //getting rid of the \n so next scan is prompted correctly, without this the next scan happens immediately

		/*Encrypt message one word at a time*/
		for(int iteration=0; iteration<words; iteration++) {
			encryptingPrompt(scan, "Next word:");
		}	
				
		System.out.println("Message fully encrypted. Happy secret messaging!");
	}
	
	/**
	 * Prompts the user for the next word to encrypt and then gives them the encrypted version
	 * @param scan The scanner used to get the user input
	 * @param promptMessage What the prompt message will say when asking for the word to encrypt
	 */
	private static void encryptingPrompt(Scanner scan, String promptMessage) {
		System.out.println(promptMessage);
		String word = scan.nextLine();		
		String encryptedWord = encryptWord(word);
		System.out.println("\"" + word + "\"  has been encrypted to: " + encryptedWord + "\n");
	}
	
	/**
	 * Encrypts the given word
	 * @param wordToEncrypt The word that will be encrypted
	 * @return The encrypted word
	 */
	public static String encryptWord(String wordToEncrypt){
		String encryptedWord ="";
		
		for(int letterIndex=0; letterIndex<wordToEncrypt.length();letterIndex++) {
			encryptedWord = encryptedWord + encryptLetter(wordToEncrypt.charAt(letterIndex));
		}
		
		return encryptedWord;
	}
	
	/**
	 * Encrypts the given letter
	 * @param letterToEncrypt The letter that will be encrypted
	 * @return The encrypted letter
	 */
	public static char encryptLetter(char letterToEncrypt){			
		int encodedIndex = ALPHABET.indexOf(letterToEncrypt) + SHIFT;
		/*If the new index spans past the alphabet then loop back to the 
		 * beginning by finding the difference between the values
		 */
		if(encodedIndex >= ALPHABET.length()) {
			encodedIndex = encodedIndex - ALPHABET.length();
		}
		
		return ALPHABET.charAt(encodedIndex);
	}
}
