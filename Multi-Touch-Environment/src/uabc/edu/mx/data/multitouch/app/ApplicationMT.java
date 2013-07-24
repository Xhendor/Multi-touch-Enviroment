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

import java.util.HashMap;
import uabc.edu.mx.entities.PointerEntity;
import uabc.edu.mx.message.listener.MessageListener;
import uabc.edu.mx.render.RenderObject;
import uabc.edu.mx.tuio.TuioCursor;

/**
 *The main class that should be implemented by any Application being developed
 * using the Multi-Touch Environment.
 * 
 * @author Rosendo R. Sosa.
 */
public abstract class ApplicationMT implements IApplicationMT,MessageListener,RenderObject{
    
    /**
     * Is true after init() method, indicates that the application is ready to run. 
     */
    public boolean isCreated;
    /**
     * Is true after start() method, indicates that the application is running. 
     */
    public boolean isRunning;
    /**
     * Is true after stop() method, indicates that the application was stopped. 
     */
    public boolean isStopped;
    /**
     * Is true after pause() method, indicates that the application was paused. 
     */
    public boolean isIdle;
    
    /**
     * Identifier of Application 
     */
    protected String appID;

    public ApplicationMT(String appID) {
        
    }

  

    @Override
    public void pause() {
    if(isRunning){    
    isIdle=true;
    
    }
    }

    @Override
    public void init() {
    isCreated=true;
    }

    @Override
    public void resume() {
    
    if(isRunning){
    isIdle=false;
    }
    
    }

    @Override
    public void start() {
    isRunning=true;
    isStopped=false;
    }

    @Override
    public void stop() {
     isRunning=false;
     isIdle=false;
    isStopped=true;
    }
    
    abstract public void collision(HashMap<TuioCursor, PointerEntity> pointers);

    abstract public void removePointers(PointerEntity toRemove);

    @Override
    public String toString() {
        return this.appID; 
    }
    
    
    
    
    


   
    
}
