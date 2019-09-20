import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FlightMap {
	
	public static Map<String, ArrayList<Pair>> map = new HashMap<String, ArrayList<Pair>>();
	public static String origin = "";
	public static ArrayList<String> cities = new ArrayList<String>();
	public static Map<String, String> finalMap = new HashMap<String, String>();
	public static ArrayList<String> path = new ArrayList<String>();
	public static Map<String, ArrayList<String>> allPaths = new HashMap<String, ArrayList<String>>();
	public static Map<String, Integer> costs = new HashMap<String, Integer>();


	public FlightMap(Map<String, ArrayList<Pair>> map, String origin, ArrayList<String> cities){
		this.map = map;
		this.origin = origin;
		this.cities = cities;
		
		FindPaths();
	}
	
	public static void FindPaths()
	{
		//NOW everything should be initialized and you want to find all the possible paths
		for(int i =0; i < cities.size(); i++)
		{
			BFS(origin, cities.get(i));
			allPaths.put(cities.get(i), path);
		}
		//printMap(allPaths);
		calcCosts(allPaths);

	}

}

class Pair {

	public String city = "";
	public Integer cost;

	public Pair(String city, Integer cost)
	{
		this.city = city;
		this.cost = cost;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	} 


}