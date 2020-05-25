package ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * contains the fileinputstream to read from files
 *
 * @author Philipp, Slebioda, 4809007
 */
public class KVPInputStream extends InputStream {

	private File fileObject;		//fileobject gets declared
	private FileInputStream inStream;	//fileinputstream object gets declared
	private int offset;			//offset as int gets declared

	/**
 	 * the KVPInputStream constructor creates a FileInputStream object with a fileobject
	 *
	 * @param object fileobject that is used to create the FileInputStream object
	 */
	public KVPInputStream(File object) {
		try {
			inStream = new FileInputStream(object);  // the fileinputstream object gets fileobject
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());	//filenotfoundexception get printed
			inStream = null;			//instream gets set null
		}
		offset = 0;					//the offset gets initialized with 0
	}

	/**
 	 * method readKVP() allows it to read a KVP out of the file and return it as a KVP object
	 *
	 * @return the next keyvaluepair or null if there are no more keyvaluepairs
	 */
	public KeyValuePair readKVP() throws IOException {
		int lengthKey = 0;
		int lengthValue = 0;
		int read = 0;
		if (inStream == null) {
			return null;	// if instream == null the method returns null
		}
		lengthKey = inStream.read();
		
		if (lengthKey == -1) { //if instream.read() delivers -1 the end of the data has reached
			return null;	//if there are no more KVPs left return null
		}

		lengthValue = inStream.read();	//second time using read() delivers the next byte
		if (lengthValue == -1) { 
			throw new IOException("Unexpected End of File is reached"); //Except. for false data format
		}
	
		byte[] key = new byte[lengthKey];	//initialize two byte arrays for the storage of the key and value data
		byte[] value = new byte[lengthValue];

		read = inStream.read(key,0,lengthKey); //variable read contains the number of bytes for the keylength or -1 if the end of file is reached
		if (read < lengthKey) { 
			throw new IOException("Unexpected End of File is reached");	// if the end is reached throw ioexception
		}	
		read = inStream.read(value, 0,lengthValue);
		if (read < lengthValue) { 
			throw new IOException("Unexpected End of File is reached");	// if the end is reached throw ioexception
		}
		return new KeyValuePair(new String(key),new String(value));		// return a keyvaluepair object with the read key and data as string
	}
	

	/**
 	 * close method allows it to close the fileinputstream object
	 *
	 */
	@Override
	public void close() throws IOException {
		inStream.close();			// the fileinputstream gets closed 
	}
	
	
	/**
 	 * this method is not used in the program, see readKVP()
	 *
	 */
	@Override
	public int read() throws IOException {
		// Kann ignoriert werden
		return 0;
	}

}
