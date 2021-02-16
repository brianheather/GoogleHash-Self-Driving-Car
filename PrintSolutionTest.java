package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrintSolutionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Vehicle());
		vehicles.add(new Vehicle());
		vehicles.add(new Vehicle());
		
		vehicles.get(0).addRouteToTheEnd(new Route(1,4,2,5,1,7,0));
		
		vehicles.get(1).addRouteToTheEnd(new Route(4,4,2,4,1,7,1));
		vehicles.get(1).addRouteToTheEnd(new Route(1,4,3,5,1,3,2));
		
		vehicles.get(2).addRouteToTheEnd(new Route(1,1,2,1,1,7,3));
		vehicles.get(2).addRouteToTheEnd(new Route(9,4,2,2,1,7,4));
		vehicles.get(2).addRouteToTheEnd(new Route(3,5,2,5,2,7,5));
		
		vehicles.add(1, new Vehicle());
		
		printSubmission(vehicles);
	}

	public static void printSubmission(ArrayList<Vehicle> vehicles)
	{
		try
		{
			File outputFile = new File("OUTPUT.txt");
			if(outputFile.createNewFile())
			{
				System.out.println("New file was created");
			} else
			{
				System.out.println("File already existed, the submission have been overwritten.");
			}
			
			PrintWriter outputWriter = new PrintWriter(outputFile);
			for(Vehicle vehicle : vehicles)
			{
				outputWriter.print(vehicle.getRouteQuantity()); //pass there a number of rides taken by the car
				for(Route route : vehicle.getRouteList())
				{
					outputWriter.print(" " + route.getPos());
				}
				outputWriter.print("\n");
			}
			
			
			outputWriter.close();
		} catch(IOException e)
		{
			System.out.println("Error occured during printing the solution");
			e.printStackTrace();
		}
		
		
	}
}
