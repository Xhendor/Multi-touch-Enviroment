/*
 * 

 * Copyright (C)  2013 Rosendo R. Sosa. .
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package uabc.edu.mx.data.multitouch.app;

import java.net.URL;
import java.util.ArrayList;
import org.newdawn.slick.util.ResourceLocation;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class AppResources {
    
    private static ArrayList<String> resources = new ArrayList<String>();
    private static ArrayList locations = new ArrayList();

    public void addResource(String resourceName){
        
        resources.add(resourceName);
    
    }
    
    
    public int indexOf(String resource){
        return resources.indexOf(resource);
    }
    
         /**
	 * Get a resource as a URL
	 * 
	 * @param ref The reference to the resource to retrieve
	 * @return A URL from which the resource can be read
	 */
	public  URL getResource(String ref) {

		URL url = null;
		
		for (int i=0;i<locations.size();i++) {
			ResourceLocation location = (ResourceLocation) locations.get(i);
			url = location.getResource(ref);
			if (url != null) {
				break;
			}
		}
		
		if (url == null)
		{
			throw new RuntimeException("Resource not found: "+ref);
		}
			
		return url;
	}
   /**
	 * Add a location that will be searched for resources
	 * 
	 * @param location The location that will be searched for resoruces
	 */
	public  void addResourceLocation(ResourceLocation location) {
		locations.add(location);
	}
        
    /**
	 * Check if a resource is available from any given resource loader
	 * 
	 * @param ref A reference to the resource that should be checked
	 * @return True if the resource can be located
	 */
	public  boolean resourceExists(String ref) {
		URL url;
		
		for (int i=0;i<locations.size();i++) {
			ResourceLocation location = (ResourceLocation) locations.get(i);
			url = location.getResource(ref);
			if (url != null) {
				return true;
			}
		}
		
		return false;
	}

    public void update() {

    
    
    
    
    }


    
}
