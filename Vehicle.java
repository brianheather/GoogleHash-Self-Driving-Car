package application;

import java.util.ArrayList;

public class Vehicle {
	private ArrayList<Route> vehicleRoutes = new ArrayList<Route>();
	
	public void addRouteToTheEnd(Route x)
	{
		vehicleRoutes.add(x);
	}
	
	public Route getRoute(int index)
	{
		return vehicleRoutes.get(index);
	}
	
	public int getRouteQuantity ()
	{
		return vehicleRoutes.size();
	}
	
	public ArrayList<Route> getRouteList ()
	{
		return vehicleRoutes;
	}
	
	public void addRoute(int index, Route x)
	{
		vehicleRoutes.add(index, x);
	}
}
