package ex01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;	// necessary to import the BeforeEach Annotation!!

/**
 *
 * provides a Junit Test for the KeyValueStore class
 *
 * @author Philipp, Slebioda, 4809007
 */
public class KVSTest {
	
	private KeyValueStore a;	// object gets declared and can be used in all methods
		
	/**
	 * init method that is once called before all other @Test methods
	 *
	 */
	@BeforeAll
	public void init() {
		a = new KeyValueStore(100);	
		for(int i = 0; i < 80; i++) {
			a.newKVP("key" + (i+1), "value" + (i+1)); // KeyValueStore object gets filled with KVP data, from 0 to 81
		}
	}
	
		
	/**
	 * test method for the newKVP() method of "KeyValueStore"
	 *
	 */	
	@Test
	public void testNew() {
		a.newKVP("key82", "value82");
		a.newKVP("key99", "value99");
		assertEquals(a.getKVP("key82"), "value82");		//standard case of the newKVP() test
		assertEquals(a.getKVP("key99"), "value99");		//standard case of the newKVP() test
		assertThrows(IllegalArgumentException.class, () -> a.newKVP(null, "Value"));  // fail case of the newKVP() test
	}
	
	/**
	 * test method for the getKVP() method of "KeyValueStore"
	 *
	 */	
	@Test
	public void testGet() {
		assertTrue(a.getKVP("key1").equals("value1") ); // standard case of the getKVP() test
		assertThrows(IllegalArgumentException.class, () -> a.getKVP(null));  // fail case of the getKVP() test
	}
	
	/**
	 * test method for the updateKVP() method of "KeyValueStore"
	 *
	 */	
	@Test
	public void testUpdate() {
		a.updateKVP("key1","neuervalue");
		assertTrue(a.getKVP("key1").equals("neuervalue"));	// standard case of the updateKVP() test
		assertThrows(IllegalArgumentException.class, () -> a.updateKVP(null, "newkey"));  // fail case of the updateKVP() test
	}
	
	/**
	 * test method for the deleteKVP() method of "KeyValueStore"
	 *
	 */	
	@Test
	public void testDelete() {
		a.deleteKVP("key1");		//String key1 übergeben
		assertThrows(IllegalArgumentException.class, () -> a.getKVP("key1")); // standard case of the deleteKVP() test, method needs lambda expression
		assertThrows(IllegalArgumentException.class , () -> a.deleteKVP("key1"));  // fail case of the deleteKVP() test, method needs lambda expression
	}
}
