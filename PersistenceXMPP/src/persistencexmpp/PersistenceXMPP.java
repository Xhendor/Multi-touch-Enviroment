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
package persistencexmpp;

import common.integration.ServiceLocator;
import java.util.List;
import uabc.xmpp.entities.OfUser;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class PersistenceXMPP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
       
        
        ServiceLocator.getInstance().setTipo(uabc.xmpp.entities.OfUser.class);
        List<OfUser> users=   ServiceLocator.getInstance().findAll();
        for (OfUser ofUser : users) {
            System.out.println("Name:"+ofUser.getName());
            System.out.println("Online:");
        }
        

        
        
    }
}
