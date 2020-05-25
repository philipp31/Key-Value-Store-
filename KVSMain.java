package ex01;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.IOException;

/**
 * main program for manipulating a KeyValueStore
 *
 * @author Philipp, Slebioda, 4809007
 */
public class KVSMain {
	
	/**
 	 * main function of the program
	 *
	 * @param args optional command line arguments
	 */
	public static void main (String[] args) {
		String commandLine;
		String file = "backupdata.txt";
		KeyValueStore valueStoreObject = new KeyValueStore(1000);
		File kvsData = new File(file); 			//file with name backupdata.txt
		
		//if file exists read the contained KeyValuePairs
		try {
			if (kvsData.exists()) {	//file exists				
				KVPInputStream inStream = new KVPInputStream(kvsData);		
				KeyValuePair kvp = inStream.readKVP();			
				while (kvp != null) { //read KVPs in a loop				
					valueStoreObject.newKVP(kvp.getKey(), kvp.getValue());		
					kvp = inStream.readKVP();							
				}
				//close stream
				inStream.close();
			}
			

		} catch (IOException e) {
			System.out.println(e.getMessage());

			//create empty KeyValueStore
			valueStoreObject = new KeyValueStore(1000);
		}

	     	Scanner scanner = new Scanner(System.in);
		commandLine = scanner.nextLine();
 		
		String key = null;
		String value = null;
		String newValue = null;

		StringTokenizer tokenizer = new StringTokenizer(commandLine);
		String command = tokenizer.nextToken(); // tokenizern.nextToken() is used to extract the words from "commandLine"
		int counter = 0; 				//initialize commandcounter with 0

		//main loop, if command is "exit" the program terminates
		while (!command.equals("exit")) {
		 	key = null;
			value = null;
			newValue = null;

			if (command.equals("new")) { //new command
				if (tokenizer.hasMoreTokens()) {
					key = tokenizer.nextToken(); //read key 
				}
				if (tokenizer.hasMoreTokens()) {
					value = tokenizer.nextToken(); //read value
				}
				try { //create new KeyValuePair with key and value
					valueStoreObject.newKVP(key, value);
					System.out.println("ok");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			}

			if (command.equals("get")) { //get command
				if (tokenizer.hasMoreTokens()) {
					key = tokenizer.nextToken(); //read key
				}
				try { //print value of the object
					System.out.println(valueStoreObject.getKVP(key)); 
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				
			
			}

			if (command.equals("delete")) {
				if (tokenizer.hasMoreTokens()) { 
					key = tokenizer.nextToken(); //read key
				}
				try { // delete KeyValuePair
					valueStoreObject.deleteKVP(key);
					System.out.println("ok");						
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			
			}

			if (command.equals("update")) {
				if (tokenizer.hasMoreTokens()) {
					key = tokenizer.nextToken(); //read key
				}
				if (tokenizer.hasMoreTokens()) {
					newValue = tokenizer.nextToken(); //read new Value
				}
				try { //update Value of object
					valueStoreObject.updateKVP(key, newValue);
					System.out.println("ok");						
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
			} 
			
			if(command.equals("help")) {
				System.out.println("For creating a new KeyValuePair use the key word \"new\" ,a key and a value ");
				System.out.println("To get a value to a given Key use the key word \"get\" and a key ");
				System.out.println("To update a keyValuePair with a new Value use the key word \"update\", a key and a newValue ");
				System.out.println("To delete a KeyValuePair with a KeyValuePair use the key word \"delete\" and a key ");
			}
			counter++; //increment counter
			if (counter==5) {				// save KVP data after 5 commands
				try { //write KeyValueStore to file
					KeyValuePair[] kvpObjectsArray = valueStoreObject.getKVPObjects();
					KVPOutputStream outStream = new KVPOutputStream(file);
					counter=0;
				
					for (int k = 0; k < valueStoreObject.getLength(); k++) {
						outStream.writeKVP(kvpObjectsArray[k]);	
					}
					outStream.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}

			// read new command line  
			commandLine = scanner.nextLine();
			//create tokenizer for commandLine
			tokenizer = new StringTokenizer(commandLine);
			command = tokenizer.nextToken();
			
		}
		try { 	// write KeyValueStore to file before exiting program
			KeyValuePair[] kvpObjectsArray = valueStoreObject.getKVPObjects();
			KVPOutputStream outStream = new KVPOutputStream(file);
			// write every single object	
			for(int k = 0; k < valueStoreObject.getLength(); k++) {
				outStream.writeKVP(kvpObjectsArray[k]);	
			}
			outStream.close(); //close stream
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
       }

}
