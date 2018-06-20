/*
Name: Harlan Chang
Class: CS20B
Assignment: Project 1
*/
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Project1
{
	public static int[] encodeChange = new int[26]; //array used to store the differences for each letter
	
	
	public static boolean checkKey(String myKey) 
	//method used to make sure the key is a valid one
	{
		if(myKey.length() != 26) /*checks the length first to make sure it will encrypt all 26 characters*/{return false;}for(int i  = 0; i < myKey.length(); i++){
if((myKey.charAt(i) > 64 && myKey.charAt(i) < 91) || (myKey.charAt(i) > 96 && myKey.charAt(i) < 123))
			//makes sure that these are letters, not other symbols 
			{
				for(int j = i + 1; j < myKey.length(); j++) 
				//used to check if there are duplicates in the key
				{
					if(myKey.charAt(i) == myKey.charAt(j))
					//returns false if there are any duplicates
					{
						return false;
					}
				}
			}
			else
			{
				//returns false if there are any symbols 
				return false;
			}
		}
		
		return true;
	}
	
	public static char encryptLetter(char c)
	//method used to encrypt the letter and returns the encrypted letter
	{
		if(((c - 'a') >= 0)&&((c - 'a') <= 26))
		//used to check if the letter sent for encryption is lower case
		{
			return (char)(c - encodeChange[c - 'a']);
		}
		else if(((c - 'A') >= 0)&&((c - 'A') <= 26))
		//used to check if the letter sent for encryption is upper case
		{
			return (char)(c - encodeChange[c - 'A']);
		}
		
		return c; //returns the char if it's a symbol
	}
	
	public static void getMapping(String myKey)
	//method used to get the differences for each letter
	{
			char encodeCh = 'a';
			
			for(int i = 0; i < 26; i++)
			//goes through each character and gets the differences to encrypt, then puts in from array index 0 to 25 
			{
				encodeChange[i] = (encodeCh - myKey.charAt(i));
				encodeCh++;
			}
	}
	
	public static void main(String [] args)
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			String encodeKey; //used to store the encoding key
			String inputFile; //name of the file
			String line; //used to store the line being read at the time
			String myOutput = ""; //used to hold the encrypted content
			char ch; //used to hold the character to be encrypted 
		
			System.out.print("Enter encoding key: ");
			encodeKey = sc.nextLine();
			
			while(!checkKey(encodeKey))
			//makes sure the user inputs a valid key 
			{
				System.out.println("Key is not valid.");
				System.out.print("Enter encoding key: ");
				encodeKey = sc.nextLine();
			}
			
			getMapping(encodeKey); //gets the mapping for each character
			
			System.out.print("Enter the file name: ");
			inputFile = sc.nextLine();
			
			File inputF = new File(inputFile); 
			sc = new Scanner(inputF); //changes the scanner to read from the input file instead because we no longer need it to read input from the user
			
			System.out.println("***********Encoded Contents*************");
			
			while(sc.hasNextLine())
			{
				line = sc.nextLine(); //gets each line from the input file 
				for(int i = 0; i < line.length(); i++)
				{
					ch = line.charAt(i);
					myOutput += encryptLetter(ch);
					//goes through each character of the line and sends it to be encrypted and added into myOutput 
				}
				myOutput += '\n'; //skips a line after each line of input 
			}
			
			System.out.println(myOutput); //prints out the whole output 
			
			System.out.println("****************************************");
			System.out.println("Good Bye!");
			
			sc.close(); //closes scanner
		} 
		catch(FileNotFoundException exception)
		{
			System.out.println("That file does not exist"); //error message if the file the user inputs is not a valid input 
		}
	}
	
	
	
}
