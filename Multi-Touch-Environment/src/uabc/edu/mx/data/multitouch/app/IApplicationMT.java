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

/**
 *
 * @author Rosendo R. Sosa.
 */
public interface IApplicationMT {
    /**
 * Initialise the Application. This can be used to load resources.
 */    
public  void init();
/**
 * Start the Application. 
 */  
public  void start();
/**
 * Pause the Application. 
 */  
public  void pause();
/**
 *Resume application after being called pause method.
 */  
public  void resume();
/**
 * Stop and Destroy the Application. 
 */  
public  void stop();

}
