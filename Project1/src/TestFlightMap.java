

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestFlightMap {

	public FlightMap fm;
	public ArrayList<String> alTest;
	public String origin = "";
	public Map<String, ArrayList<Pair>> mapTest;

	@Test
	public void constructorTest() {
		alTest = new ArrayList<String>(); 
		ArrayList<Pair> pairTest = new ArrayList<Pair>();
		mapTest = new HashMap<String, ArrayList<Pair>>();

		//Initialize origin
		origin = "A";

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
		assertEquals("testing origin", origin, FlightMap.origin);
		assertEquals("testing map",mapTest, FlightMap.map);
		assertEquals("testing cities",alTest, FlightMap.cities);
	}
	
//	@Test
//	public void testFindPaths() {
//		
//		ArrayList<String> cityTest = new ArrayList<String>(); 
//		ArrayList<Pair> pairTest = new ArrayList<Pair>();
//		Map<String, ArrayList<Pair>> mapTest = new HashMap<String, ArrayList<Pair>>();
//
//		//Initialize origin
//		String origin = "A";
//
//		// Initialize alTest
//		alTest.add("A"); 
//		alTest.add("B"); 
//		alTest.add("C"); 
//
//		// Initialize pairTest
//		pairTest.add(new Pair("B", 200)); 
//		pairTest.add(new Pair("C", 150)); 
//
//		//Initialize Map
//		mapTest.put("A", pairTest);
//
//		fm = new FlightMap(mapTest, origin, alTest);
//		assertEquals("testing origin", origin, fm.origin);
//		assertEquals("testing map",mapTest, fm.map);
//		assertEquals("testing cities",alTest, fm.cities);
//	}
	
	@Test
	public void testGetCosts()
	{
		fm = new FlightMap(mapTest, origin, alTest);
		Map<String, Integer> testerCosts = new HashMap<String, Integer>();
		testerCosts.put("B", 200);
		fm.setCosts(testerCosts);
		
		assertSame(testerCosts, fm.getCosts());
		
		
	}

}
