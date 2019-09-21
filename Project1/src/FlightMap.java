import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class FlightMap {
	
	public static Map<String, ArrayList<Pair>> map = new HashMap<String, ArrayList<Pair>>();
	public static String origin = "";
	public static ArrayList<String> cities = new ArrayList<String>();
	public static Map<String, String> finalMap = new HashMap<String, String>();
	public static ArrayList<String> path = new ArrayList<String>();
	public static Map<String, ArrayList<String>> allPaths = new HashMap<String, ArrayList<String>>();
	public static Map<String, Integer> costs = new HashMap<String, Integer>();


	public FlightMap(Map<String, ArrayList<Pair>> map, String origin, ArrayList<String> cities){
		FlightMap.map = map;
		FlightMap.origin = origin;
		FlightMap.cities = cities;
		
		//FindPaths();
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
		//calcCosts(allPaths);

	}
	
	public static Boolean BFS(String start, String end)
	{
		path = new ArrayList<String>();
		int numCities = cities.size();
		ArrayList<String> visited = new ArrayList<String>();
		if(start.equals(end))
		{
			path.add(start);
			return true;
		}
		Queue <String> queue = new LinkedList<>();	
		//we're starting off with "start" and saying we've visited it
		visited.add(start);
		//we're going to add start to the queue
		queue.add(start);
		while(!queue.isEmpty())
		{
			//we want to examine the first thing in the queue, which is our start node
			String currentCity = queue.peek();
			queue.remove();

			//NOW let's get the adj list for this thing

			ArrayList<Pair> adjList = map.get(currentCity);
			if(!(adjList == null))
			{

				for(int i =0; i < adjList.size(); i++ )
				{
					String newCity = adjList.get(i).getCity();
					if(newCity.equals(end))
					{
						finalMap.put(newCity, currentCity);
						ArrayList<String> FINAL = new ArrayList<>();
						String node = end;
						while(node != null) {
							FINAL.add(node);
							node = finalMap.get(node);
						}
						Collections.reverse(FINAL);
						path = FINAL;
						return true;
					}

					if(!visited.contains(newCity))
					{
						visited.add(newCity);
						queue.add(newCity);
						finalMap.put(newCity, currentCity);
					}
				}
			}
		}
		return false;
	}
	
	public Map<String, Integer> getCosts()
	{
		return costs;
	}
	
	public void setCosts(Map<String, Integer> costsIn)
	{
		costs = costsIn;
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