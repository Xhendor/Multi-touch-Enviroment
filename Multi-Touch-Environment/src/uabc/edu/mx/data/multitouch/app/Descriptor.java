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
public class Descriptor {
    
    
    private String autor=new String();
    private String version=new String();
    private String appName=new String();
    private String description=new String();
    private String goal=new String();
    
    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName the appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the goal
     */
    public String getGoal() {
        return goal;
    }

    /**
     * @param goal the goal to set
     */
    public void setGoal(String goal) {
        this.goal = goal;
    }
    
   
    @Override
    public String toString() {
        return "Autor:["+autor+"]\n"+"Nombre de aplicación:"+appName; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
