package src;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestFlightMap {

	private FlightMap fm;

	@Test
	public void constructerTest() {
		ArrayList<String> alTest = new ArrayList<String>(); 
		ArrayList<Pair> pairTest = new ArrayList<Pair>();
		Map<String, ArrayList<Pair>> mapTest = new HashMap<String, ArrayList<Pair>>();

		//Initialize origin
		String origin = "A";

		// Initialize alTest
		alTest.add("A"); 
		alTest.add("B"); 
		alTest.add("C"); 

		// Initialize pairTest
		pairTest.add(new Pair("B", 200)); 
		pairTest.add(new Pair("C", 150)); 

		//Initialize Map
		mapTest.put("A", pairTest);

		fm = new FlightMap(mapTest, origin, alTest);
		assertEquals(origin, fm.origin);
		assertEquals(mapTest, fm.map);
		assertEquals(alTest, fm.cities);
	}

}
