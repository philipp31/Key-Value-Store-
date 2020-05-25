package ex01;

/**
 *
 * provides a KeyValueStore that saves KVP object and delivers methods to manipulate the stored KVP data
 *
 * @author Philipp, Slebioda, 4809007
 */
public class KeyValueStore {
	//attributes:
	private KeyValuePair[] kvpArray;	//declaring of a keyvaluepair-array to store the keyvaluepairs
	private int kvpCounter;			//declaring of a kvpcounter

	/**
	 * constructor for KeyValueStore
	 *
	 * @param size integer that contains the max. amount of savabel keyvaluepairs
	 */
	public KeyValueStore(int size) {
		kvpCounter = 0;				//kvpcounter gets initialized with 0
		kvpArray = new KeyValuePair[size];	//kvparray get initialized with the parameter value
	}

	/**
	 * newKVP method allows it to save new keyvaluepairs in the KVP-array
	 *
	 * @param key name of the new key for the KVP
	 * @param value value that is saved for the new KVP
	 */
       	public void newKVP(String key, String value) throws IllegalArgumentException {
		//check parameters:
		if (key == null) {
                	throw new IllegalArgumentException("Key == NULL");	//if the key is null a illegalargumentexception is given
		}
		else if (value == null) { 
			throw new IllegalArgumentException("Value == NULL"); 	//if the value is null a illegalargumentexception is given
		}
		for (int i = 0; i < kvpCounter; i++) { 
			if(kvpArray[i].getKey().equals(key)) {
				throw new IllegalArgumentException("key already exists");	//if the same key is already existing  then an illegalargumentexception is given out
			} 
		}
		KeyValuePair newkvp = new KeyValuePair(key, value);	//the given KVP is used to create a new KVP object
		kvpArray[kvpCounter] = newkvp;			//the KVP object is getting stored in kvpArray		
		kvpCounter++;					//the counter gets incremented
       	}
	
	/**
	 * get the value of the KeyValuePair object that is associated with the key
	 *
	 * @param key name of the new key for the KVP
	 * @return the value associated with the given key
	 */
	public String getKVP(String key) throws IllegalArgumentException {
		//check parameters:
		if (key == null){
                	throw new IllegalArgumentException("Key == NULL");
		}

		for (int i = 0; i < kvpCounter; i++){ 
			if (kvpArray[i].getKey().equals(key)){
				return kvpArray[i].getValue();
			} 
		}
		throw new IllegalArgumentException("Key not found");
	}

	/**
	 * update the value of a KeyValuePair object
	 *
	 * @param key of the KVP object
	 * @param newValue the the new value for the KVP object
	 */
	public void updateKVP(String key, String newValue) throws IllegalArgumentException {
		//check parameters:
		if (key == null) {
                	throw new IllegalArgumentException("Key == NULL");
		}

		if (newValue == null) {
                	throw new IllegalArgumentException("newValue == NULL");
		}
	
		for (int i = 0; i < kvpCounter; i++) { 
			if (kvpArray[i].getKey().equals(key)) {   
				kvpArray[i].setValue(newValue); 
				return;	
			} 
		}
		throw new IllegalArgumentException("Key not found");
	}

	/**
	 * delete a KeyValuePair object from the array
	 *
	 * @param key key of the KVP object
	 */
       	public void deleteKVP(String key) throws IllegalArgumentException {
		//check parameters:
		if (key == null) {
                	throw new IllegalArgumentException("Key == NULL");
		}
	
		for (int i = 0; i < kvpCounter; i++) { 			//iterate over all elements in the array
			if(kvpArray[i].getKey().equals(key)) { 
				for (int k = i; k < kvpCounter; k++) { //if the object is found, move every element that is above the found object so that the element is deleted 
					kvpArray[k] = kvpArray[k+1];
				}
				kvpCounter--;
				return ;
			}
			
		} 
		throw new IllegalArgumentException("Key not found");
	}

	/**
	 * get number of elements in array that is stored in knpCounter
	 *
	 * @return the number of elements
	 */
	public int getLength() {
		return kvpCounter;
	}

	/**
	 * get all KeyValuePair objects as an array
	 *
	 * @return the array with KeyValuePairs
	 */
	public KeyValuePair[] getKVPObjects() {
		return kvpArray;
	}

}

