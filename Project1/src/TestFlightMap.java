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
	public void constructorTest() { //this method tests the constructor using a sample
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
		
		//Add the other pair to the map
		pairTest = new ArrayList<Pair>();
		pairTest.add(new Pair("X", 180)); 
		mapTest.put("B", pairTest);

		//Testing
		fm = new FlightMap(mapTest, origin, alTest);
		assertEquals("testing origin", origin, FlightMap.origin);
		assertEquals("testing map",mapTest, FlightMap.map);
		assertEquals("testing cities",alTest, FlightMap.cities);
	}


	@Test
	public void testGetCosts() //Tests the Get Costs method
	{
		//Creates a new object
		fm = new FlightMap(mapTest, origin, alTest);
		Map<String, Integer> testerCosts = new HashMap<String, Integer>();
		testerCosts.put("B", 200);
		fm.setCosts(testerCosts); //sets the cost to later check it

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
	public void testBFS() //Test the BFS method
	{
		//Creates object using methods
		fm = new FlightMap(mapTest, origin, alTest);
		FlightMap.BFS(origin, "X");

		//creating the TestPath
		ArrayList <String> testPath = new ArrayList<String>();
		testPath.add("A");
		testPath.add("B");
		testPath.add("X");
		ArrayList <String> realPath = FlightMap.path;
		//Checking if boths paths are equal
		assertEquals(testPath, realPath);

	}
	
	@Test
	public void testCalcCosts() //Tests Calc Costs
	{
		fm = new FlightMap(mapTest, origin, alTest);
		FlightMap.BFS(origin, "X");
		FlightMap.calcCosts();
		
		//Created a sample instance to test functionality
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
		
		//Builds testing map
		Map<String, Integer> costTest = new HashMap<String, Integer>();
		costTest.put("A", 0);
		costTest.put("B", 200);
		costTest.put("C", 150);
		costTest.put("X", 380);
		
		assertEquals(costTest, fm.costs);
		
	}
}
