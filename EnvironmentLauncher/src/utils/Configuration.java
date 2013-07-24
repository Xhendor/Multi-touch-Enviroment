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

import java.util.ArrayList;
import java.util.HashMap;
import uabc.edu.mx.data.session.SessionData;
import uabc.edu.mx.gui.DynamicTree;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class Configuration {

    //public static String server = "148.231.90.242";
   public static String server = "192.168.200.102";
    public static HashMap<String, ArrayList<String>> configToLaunch = new HashMap<String, ArrayList<String>>();
    public static String currentModerator = new String();
    public static SessionData sessionDataToSend = new SessionData();
    public static DynamicTree dynamicTreeTables;
    public static DynamicTree dynamicTreeUsers;
}
