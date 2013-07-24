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
package utils;

/**
 *
 * @author Rosendo R. Sosa.
 */
public enum PathOfApplications {
    
    
  WINDOWS_PATH("/Users/" + System.getProperty("user.name")
                    + "/SparkleShare/multitouch apps/Applications/"),
  MACOSX_OATH("/Users/" + System.getProperty("user.name")
                    + "/SparkleShare/multitouch apps/Applications/");
    PathOfApplications (String i)
    {
        this.type = i;
    }

    private String type;

    public String getPath(Class currentClass)
    {
        return type+currentClass.getSimpleName()+"/resources/";
    }  
    
}
