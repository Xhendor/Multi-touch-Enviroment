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
package uabc.edu.mx.jabber;

import java.util.ArrayList;
import uabc.edu.mx.jabber.user.Moderator;
import uabc.edu.mx.jabber.user.MultiTouchTable;
import uabc.edu.mx.jabber.user.Participant;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class ActiveAccountsManager {

    private Moderator moderator;
    private ArrayList<MultiTouchTable> multiTouchTables;
    private ArrayList<Participant> activeUsers;

    public ActiveAccountsManager() {
        activeUsers = new ArrayList<Participant>();
        multiTouchTables = new ArrayList<MultiTouchTable>();

    }

    public ActiveAccountsManager(Moderator moderator) {
        this.moderator = moderator;
        activeUsers = new ArrayList<Participant>();
        multiTouchTables = new ArrayList<MultiTouchTable>();

    }

    public ActiveAccountsManager(Moderator moderator, ArrayList<Participant> activeUsers) {
        this.moderator = moderator;
        this.activeUsers = new ArrayList<Participant>(activeUsers);
        multiTouchTables = new ArrayList<MultiTouchTable>();

    }

    /**
     * @return the moderator
     */
    public Moderator getModerator() {
        return moderator;
    }

    public boolean addActiveUser(Participant activeUser) {

        if (!this.activeUsers.contains(activeUser)) {
            this.activeUsers.add(activeUser);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeActiveUser(Participant activeUser) {

        if (this.activeUsers.contains(activeUser)) {
            this.activeUsers.remove(activeUser);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean addMultiTouchTable(MultiTouchTable activeUser) {

        if (!this.multiTouchTables.contains(activeUser)) {
            this.getMultiTouchTables().add(activeUser);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeMultiTouchTable(MultiTouchTable activeUser) {

        if (this.getMultiTouchTables().contains(activeUser)) {
            this.getMultiTouchTables().remove(activeUser);
            return true;
        } else {
            return false;
        }
    }

    public int getNumberOfActiveUsers() {
        return activeUsers.size();
    }

    public int getNumberOfMultiTouchTable(){
    return getMultiTouchTables().size();
    }
    /**
     * @param moderator the moderator to set
     */
    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }

    /**
     * @return the activeUsers
     */
    public ArrayList<Participant> getActiveUsers() {
        return activeUsers;
    }

    /**
     * @param activeUsers the activeUsers to set
     */
    public void setActiveUsers(ArrayList<Participant> activeUsers) {
        this.activeUsers = activeUsers;
    }

    /**
     * @return the multiTouchTables
     */
    public ArrayList<MultiTouchTable> getMultiTouchTables() {
        return multiTouchTables;
    }

    /**
     * @param multiTouchTables the multiTouchTables to set
     */
    public void setMultiTouchTables(ArrayList<MultiTouchTable> multiTouchTables) {
        this.multiTouchTables = multiTouchTables;
    }
}
