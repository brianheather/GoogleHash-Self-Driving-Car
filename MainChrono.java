package application;

import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
//import java.util.Arrays;

public class MainChrono {

	// the Integer is start time
	static HashMap<Integer, ArrayList<Route>> priority1Unsorted = new HashMap<>();
	static HashMap<Integer, ArrayList<Route>> priority2Unsorted = new HashMap<>();

	static ArrayList<Integer> priority1Sorted = new ArrayList<>();
	static ArrayList<Integer> priority2Sorted = new ArrayList<>();
	// Priority2, can never get there in time
	// Ignore is not needed since it's ignored
	static CarChrono[] vehicles;

	public static void main(String[] args) {
		readFile();
		sortDeMaps(); // chronological order
		// printerRoutes();

		addRoutes();

		writeToFile();
		System.out.println("\nDone");
	}

	public static void writeToFile() 
	{
		try 
		{
			File outputFile = new File("OUTPUT.txt");
			if (outputFile.createNewFile()) 
			{
				System.out.println("New file was created");
			} 
			else 
			{
				System.out.println("File already existed, the submission have been overwritten.");
			}

			PrintWriter outputWriter = new PrintWriter(outputFile);
			
			for (CarChrono elemV : vehicles) 
			{
				ArrayList<Route> data = elemV.getRoutes();
				data.remove(0);
				outputWriter.print(data.size());
				for (Route elemR : data) 
				{
					outputWriter.print(" " + elemR.getPos());
				}
				outputWriter.print("\n");
			}
			outputWriter.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Error occured during printing the solution");
			e.printStackTrace();
		}
	}

	public static void addRoutes() {

		for (Integer elemI : priority1Sorted) {
			ArrayList<Route> data = priority1Unsorted.get(elemI);
			for (Route elemR : data) {
				for (int i = 0; i < vehicles.length; i++) {
					if (vehicles[i].appendRoute(elemR)) {
						break;
					}
				}
			}
		}

		for (Integer elemI : priority2Sorted) {
			ArrayList<Route> data = priority2Unsorted.get(elemI);
			for (Route elemR : data) {
				for (int i = 0; i < vehicles.length; i++) {
					if (vehicles[i].insertRoute(elemR)) {
						break;
					}
				}
			}
		}
	}

	public static int calDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y2 - y1);
	}

	public static void readFile() {
		Scanner info = null;
		try {
			File file;
			file = new File("e_high_bonus.IN"); 		//50234 points
			//file = new File("d_metropolis.IN"); 		//129988 points
			//file = new File("c_no_hurry.IN");			//93095 points
			//file = new File("b_should_be_easy.IN"); 	//16139 points
			//file = new File("a_example.IN");
			info = new Scanner(file);

			String dataT = info.nextLine();
			String[] data = dataT.split(" ");

			vehicles = new CarChrono[Integer.parseInt(data[2])];
			for (int i = 0; i < vehicles.length; i++) {
				vehicles[i] = new CarChrono();
			}

			int counter = 0;
			while (info.hasNextLine()) {
				dataT = info.nextLine();
				data = dataT.split(" ");

				int x1 = Integer.parseInt(data[0]);
				int y1 = Integer.parseInt(data[1]);
				int x2 = Integer.parseInt(data[2]);
				int y2 = Integer.parseInt(data[3]);
				int t1 = Integer.parseInt(data[4]);
				int t2 = Integer.parseInt(data[5]);

				int dist = calDist(x1, y1, x2, y2);

				if (x1 + y1 <= t1 && dist <= t2 - t1) {
					if (priority1Unsorted.containsKey(t1)) {
						priority1Unsorted.get(t1).add(new Route(x1, y1, x2, y2, t1, t1 + dist, counter));
					} else {
						ArrayList<Route> temp = new ArrayList<>();
						temp.add(new Route(x1, y1, x2, y2, t1, t2, counter));
						priority1Unsorted.put(t1, temp);
					}
				} else if (dist <= t2 - t1) {
					if (priority2Unsorted.containsKey(t1)) {
						priority2Unsorted.get(t1).add(new Route(x1, y1, x2, y2, t1, t2, dist, counter));
					} else {
						ArrayList<Route> temp = new ArrayList<>();
						temp.add(new Route(x1, y1, x2, y2, t1, t2, dist, counter));
						priority2Unsorted.put(t1, temp);
					}
				} else {
					System.out.println(counter + ") Cannot complete in time");
				}
				counter++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} finally {
			info.close();
		}
	}

	public static void sortDeMaps() {
		for (Integer elem : priority1Unsorted.keySet()) {
			priority1Sorted.add(elem);
		}
		Collections.sort(priority1Sorted);
		for (Integer elem : priority2Unsorted.keySet()) {
			priority2Sorted.add(elem);
		}
		Collections.sort(priority2Sorted);
	}

	public static void printerRoutes() {
		System.out.println("Priority 1");
		for (Integer elem : priority1Sorted) {
			System.out.println(elem); // Distance
			ArrayList<Route> data = priority1Unsorted.get(elem);

			for (Route i : data) {
				for (int j : i.getData()) {
					System.out.print(j + " ");
				}
				System.out.println();
			}
			System.out.println("=============");
		}

		System.out.println("\nPriority 2");
		for (Integer elem : priority2Sorted) {
			System.out.println(elem);
			ArrayList<Route> data = priority2Unsorted.get(elem);

			for (Route i : data) {
				for (int j : i.getData()) {
					System.out.print(j + " ");
				}
				System.out.println();
			}
			System.out.println("=============");
		}
	}
}
