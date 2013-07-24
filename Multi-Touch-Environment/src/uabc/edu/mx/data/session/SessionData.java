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
package uabc.edu.mx.data.session;

import java.util.ArrayList;
import uabc.edu.mx.data.multitouch.app.AppState;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class SessionData {

    private String appToRun = new String();
    private String tableMTID = new String();
    private ArrayList<String> usersToAssign = new ArrayList<String>();
    private AppState state;
    private String currentModerator = new String();

    /**
     * @return the appToRun
     */
    public String getAppToRun() {
        return appToRun;
    }

    /**
     * @param appToRun the appToRun to set
     */
    public void setAppToRun(String appToRun) {
        this.appToRun = appToRun;
    }

    /**
     * @return the usersToAssign
     */
    public ArrayList<String> getUsersToAssign() {
        return usersToAssign;
    }

    /**
     * @param usersToAssign the usersToAssign to set
     */
    public void setUsersToAssign(ArrayList<String> usersToAssign) {
        this.usersToAssign = usersToAssign;
    }

    /**
     * @return the tableMTID
     */
    public String getTableMTID() {
        return tableMTID;
    }

    /**
     * @param tableMTID the tableMTID to set
     */
    public void setTableMTID(String tableMTID) {
        this.tableMTID = tableMTID;
    }

    /**
     * @return the state
     */
    public AppState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(AppState state) {
        this.state = state;
    }

    /**
     * @return the currentModerator
     */
    public String getCurrentModerator() {
        return currentModerator;
    }

    /**
     * @param currentModerator the currentModerator to set
     */
    public void setCurrentModerator(String currentModerator) {
        this.currentModerator = currentModerator;
    }
}
