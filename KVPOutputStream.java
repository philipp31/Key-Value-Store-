package ex01;

import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

/**
 *
 * provides an OutputStream for KeyValuePairs, uses a FileOutputStream for file access
 *
 * @author Philipp, Slebioda, 4809007
 */
public class KVPOutputStream extends OutputStream {

	private FileOutputStream outstream; //declaring of a Fileoutputstream object
	private File fileObject;		//declaring of a File object

	/**
	 * we don't use this function, see writeKVP
	 */
	@Override
	public void write(int arg0) throws IOException {	//can be ignored
		// Kann ignoriert werden
	}

	/**
	 * constructor for KVPOutputStream
	 *
	 * @param filename name of the file containing KVP objects
	 */
	public KVPOutputStream(String filename) throws IOException {	
		fileObject = new File(filename);
		if (fileObject.exists()) { 			// Checking if the Fileobject exists
			fileObject.delete(); 			// if the File exists the object should delete the file (not the object itself) 
		}
		fileObject.createNewFile(); 			// for createNewFile its neccessary that the file doesn't exist
		outstream = new FileOutputStream(fileObject);	// Constructor is used to create a new fileoutputstream
	}	

	/**
	 * write function for KVPOutputStream
	 *
	 * @param KVP given KeyValuePair object that is used to write KeyValuePairs in the DataFile
	 *
	 */
	public void writeKVP(KeyValuePair KVP) throws IOException {
		outstream.write((byte)KVP.getKey().length());		//First the Key-and value-length in byte is written to the file	// the class string allows it to use the method length()
		outstream.write((byte)KVP.getValue().length());
		byte[] KeyBytes = KVP.getKey().getBytes(); 		//In the next step the key-and value in byte is written to the file // the string is getting converted in byte format and saved 
		byte[] ValueBytes = KVP.getValue().getBytes();
		outstream.write(KeyBytes);
		outstream.write(ValueBytes);
	}

	/**
	 * close function for KVPOutputStream, it only closes the used FileOutputStream
	 */
	@Override
	public void close() throws IOException {
		outstream.close();			//close fileoutputstream
	}
}
