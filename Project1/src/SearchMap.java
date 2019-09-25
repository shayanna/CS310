import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SearchMap {

	public static String origin = ""; //Track the origin city.
	public static int numCities = 0; //Track the various number of cities available.


	public static void main(String [] args)
	{

		Map<String, ArrayList<Pair>> info = new HashMap<String, ArrayList<Pair>>(); //This will store destinations & costs.
		ArrayList<String> cities = new ArrayList<String>(); //This will store all unique cities.
		String fileName = "inputfile.txt"; //This is the variable for the input file.
		String line = null; //Temporary to read in lines.

		try {

			FileReader fileReader = 
					new FileReader(fileName);
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);

			//SET ORIGIN CITY
			origin = bufferedReader.readLine();

			//READ IN ALL DESTINATIONS
			while((line = bufferedReader.readLine()) != null) {
				String[] splited = line.split("\\s+");
				String K = splited[0];
				String V = splited[1];
				int result = Integer.parseInt(splited[2]);	

				//Add unique cities to array list. 
				if(!cities.contains(K)) {cities.add(K);}
				if(!cities.contains(V)){cities.add(V);}
				if(info.containsKey(K)){
					ArrayList<Pair> temp = info.get(K);
					temp.add(new Pair(V, result));
					info.put(splited[0], temp);
				}
				else {
					//If the city doesn't already exist, we add it.
					ArrayList<Pair> temp = new ArrayList<Pair>();
					temp.add(new Pair(splited[1], result));
					info.put(splited[0], temp);
				}

			}   

			bufferedReader.close(); 
			numCities = cities.size(); 
			//printMap(info);
			//This will initialize the map and calculate the various paths & costs.
			FlightMap map = new FlightMap(info, origin, cities);
			//Retrieval of the costs map.
			Map<String, Integer> costFinal = map.getCosts();
			//Retrieve allPaths map.
			Map<String, ArrayList<String>> pathsFinal = map.getAllPaths();
			//Write to file.
			writeToFile(costFinal, pathsFinal);


		}
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '" + 
							fileName + "'");                
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading file '" 
							+ fileName + "'");                  
		}

	}
	
	//METHOD OBJECTIVE: Write data to the file.
	public static void writeToFile(Map<String, Integer> costs, Map<String, ArrayList<String>> paths)
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("outputfile.txt");
			Formatter fmt = new Formatter(); 
			//Format the file appropriately.
			writer.println(fmt.format("%10s %25s %20s", "Destination", "Flight Route From " + origin, "Total Cost"));
			for (Entry<String, Integer> entry : costs.entrySet())
			{
				fmt = new Formatter();
				String destination = entry.getKey(); //destination city
				int currentCost = entry.getValue(); //cost of getting to this city 
				if(currentCost != 0)
				{
					ArrayList<String> temp = paths.get(destination); //path array in list form
					//turn this path into a string
					StringBuilder sb = new StringBuilder();
					//Append the path together.
					for (String s : temp)
					{
						sb.append(s);
						sb.append(", ");
					}
					String finalDestPath = sb.toString();
					writer.print(fmt.format("%10s %25s %20s", destination, finalDestPath, currentCost));
					writer.println();
				}

			}
			writer.close();



		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
