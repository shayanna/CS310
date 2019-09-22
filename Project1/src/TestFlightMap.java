

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class TestFlightMap {

	public static FlightMap fm;
	public static ArrayList<String> alTest;
	public static String origin = "";
	public static Map<String, ArrayList<Pair>> mapTest;

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
		alTest.add("X"); 

		// Initialize pairTest
		pairTest.add(new Pair("B", 200)); 
		pairTest.add(new Pair("C", 150)); 

		//Initialize Map
		mapTest.put("A", pairTest);
		
		pairTest = new ArrayList<Pair>();
		pairTest.add(new Pair("X", 180)); 
		mapTest.put("B", pairTest);

		fm = new FlightMap(mapTest, origin, alTest);
		assertEquals("testing origin", origin, FlightMap.origin);
		assertEquals("testing map",mapTest, FlightMap.map);
		assertEquals("testing cities",alTest, FlightMap.cities);
	}


	@Test
	public void testGetCosts()
	{
		fm = new FlightMap(mapTest, origin, alTest);
		Map<String, Integer> testerCosts = new HashMap<String, Integer>();
		testerCosts.put("B", 200);
		fm.setCosts(testerCosts);

		assertSame(testerCosts, fm.getCosts());	
	}
	
	@Test
	public void testGetAllPaths()
	{
		fm = new FlightMap(mapTest, origin, alTest);
		Map<String, ArrayList<String>> TEST = new HashMap<String, ArrayList<String>>();
		ArrayList<String> TEMP = new ArrayList<String>();
		
		TEMP.add("A");
		TEST.put("A", TEMP);
		TEMP = new ArrayList<String>();
		TEMP.add("A"); TEMP.add("B");
		TEST.put("B", TEMP);
		TEMP = new ArrayList<String>();
		TEMP.add("A"); TEMP.add("C");
		TEST.put("C", TEMP);
		TEMP = new ArrayList<String>();
		TEMP.add("A"); TEMP.add("B"); TEMP.add("X");
		TEST.put("X", TEMP);
		
		assertEquals(TEST, fm.getAllPaths());
	}

	@Test
	public void testBFS()
	{
		fm = new FlightMap(mapTest, origin, alTest);
		FlightMap.BFS(origin, "X");

		ArrayList <String> testPath = new ArrayList<String>();
		testPath.add("A");
		testPath.add("B");
		testPath.add("X");
		ArrayList <String> realPath = FlightMap.path;
		assertEquals(testPath, realPath);

	}
	
	@Test
	public void testCalcCosts()
	{
		fm = new FlightMap(mapTest, origin, alTest);
		FlightMap.BFS(origin, "X");
		FlightMap.calcCosts();
		
		Map<String, ArrayList<String>> TEST = new HashMap<String, ArrayList<String>>();
		ArrayList<String> TEMP = new ArrayList<String>();
		TEMP.add("A");
		TEST.put("A", TEMP);
		TEMP = new ArrayList<String>();
		TEMP.add("A"); TEMP.add("B");
		TEST.put("B", TEMP);
		TEMP = new ArrayList<String>();
		TEMP.add("A"); TEMP.add("C");
		TEST.put("C", TEMP);
		TEMP = new ArrayList<String>();
		TEMP.add("A"); TEMP.add("B"); TEMP.add("X");
		TEST.put("X", TEMP);
		
		Map<String, Integer> costTest = new HashMap<String, Integer>();
		costTest.put("A", 0);
		costTest.put("B", 200);
		costTest.put("C", 150);
		costTest.put("X", 380);
		
		assertEquals(costTest, fm.costs);
		
	}
}
