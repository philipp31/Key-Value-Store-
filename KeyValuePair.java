package ex01;

/**
 *
 * provides the KeyValuePair-class, that is used to save the key-value-pairs
 *
 * @author Philipp, Slebioda, 4809007
 */
public class KeyValuePair {			
	private String key;		//declaring of the attribute key
	private String value;		//declaring of the attribute value

	/**
	 * constructor for the class keyvaluepair
	 *
	 * @param key value name of the key and the value that is saved
	 */
	public KeyValuePair(String key, String value) {
		setKey(key);				//key gets written in the attribute key
		setValue(value);			//value gets written in the attribute value
	}

	/**
	 * method getKey from keyvaluepair returns the key 
	 *
	 * @return the key of the object
	 */
	public String getKey() {
		return key;				//returning of a key
	}

	/**
	 * method setKey from keyvaluepair sets a given key as the new one
	 *
	 * @param key value name of the key and the value that is saved
	 */
	public void setKey(String key) {
		this.key = key;				//setting the attribute
	}

	/**
	 * method getValue from keyvaluepair delivers the value 
	 *
	 * @return the value of the object
	 */
	public String getValue(){
		return value;				// returning of the value
	}

	/**
	 * method setValue from keyvaluepair saves a given value as the new one
	 *
	 * @param value is the new value 
	 */
	public void setValue(String value) {
		this.value = value;			//setting the attribute
	}


}