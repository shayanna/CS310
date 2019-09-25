import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class FlightMap {

	public static Map<String, ArrayList<Pair>> map = new HashMap<String, ArrayList<Pair>>(); //Stores paths with costs
	public static String origin = ""; //Stores origin city
	public static ArrayList<String> cities = new ArrayList<String>(); //Stores cities
	public static Map<String, String> finalMap = new HashMap<String, String>(); //Stores the final chosen map
	public static ArrayList<String> path = new ArrayList<String>(); //Stores temporary paths for each string
	public static Map<String, ArrayList<String>> allPaths = new HashMap<String, ArrayList<String>>(); //Stores adjacency list
	public static Map<String, Integer> costs = new HashMap<String, Integer>(); //stores costs to various destinations


	//Constructor
	public FlightMap(Map<String, ArrayList<Pair>> map, String origin, ArrayList<String> cities){
		FlightMap.map = map;
		FlightMap.origin = origin;
		FlightMap.cities = cities;

		//Calling the method that performs all path finding operations
		FindPaths();
	}

	//METHOD OBJECTIVE: Facilitate path findings by iterating through each list
		//and calling BFS and compiling paths to each city from the origin
	public static void FindPaths()
	{
		//NOW everything should be initialized and you want to find all the possible paths by iterating through each city
		for(int i =0; i < cities.size(); i++)
		{
			//calls BFS on our origin and each city in the graph
			BFS(origin, cities.get(i));
			//adds the destination city and the potential path
			allPaths.put(cities.get(i), path);
		}
		//calculates the cost using allPaths
		calcCosts();

	}

	//METHOD OBJECTIVE: BFS function that finds the path from start to end
	public static Boolean BFS(String start, String end)
	{
		path = new ArrayList<String>(); //Re-initialize path from previous value
		int numCities = cities.size(); //obtain total number of cities
		ArrayList<String> visited = new ArrayList<String>(); //visited cities
		if(start.equals(end)) //already found the destination
		{
			path.add(start); //add it to the path
			return true; //return our path
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
			queue.remove(); //remove that from the queue because we're taking a look at it

			//Now let's get the adj list for city
			ArrayList<Pair> adjList = map.get(currentCity);
			if(!(adjList == null))
			{

				for(int i =0; i < adjList.size(); i++ )
				{
					String newCity = adjList.get(i).getCity();
					if(newCity.equals(end)) //check if our new city is our final destination. if so, we're done!
					{
						finalMap.put(newCity, currentCity); //add it to our final map
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
					//now we're checking to see if the city has been visited, and if not, we will add it to queue, visited & path
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

	//METHOD OBJECTIVE: returns the cost Map
	public Map<String, Integer> getCosts()
	{
		return costs;
	}

	//METHOD OBJECTIVE: sets the cost Map
	public void setCosts(Map<String, Integer> costsIn)
	{
		costs = costsIn;
	}

	//METHOD OBJECTIVE: Given the various paths, the costs will be calculated 
	public static void calcCosts()
	{
		//iterate through all the paths you have now found
		for (Entry<String, ArrayList<String>> entry : allPaths.entrySet())
		{
			int totalCost = 0;
			String destination = entry.getKey();
			//this is the path for the above destination
			ArrayList<String> temp = entry.getValue();
			if(temp.size() > 0)
			{
				String previous = temp.get(0);
				for(int i = 1; i < temp.size(); i++)
				{
					//look up this pair in the adjacency map to find the cost
					ArrayList<Pair> pairs = map.get(previous);
					for(int j =0; j < pairs.size(); j++)
					{
						//add the cost to the total cost
						if(pairs.get(j).getCity().equals(temp.get(i)))
						{
							totalCost = totalCost + pairs.get(j).getCost();
						}
					}
					previous = temp.get(i);
				}
			}
			//add results to the map
			costs.put(destination, totalCost);

		}

	}
	
	//METHOD OBJECTIVE: returns the AllPaths ArrayList
	public Map<String, ArrayList<String>> getAllPaths()
	{
		return allPaths;
	}	

}

//This class allows for reference to a city and it's associated cost as a node(pair).
//It's used when storing paths in a Map for each destination.
class Pair {

	public String city = "";
	public Integer cost;

	//Constructor
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