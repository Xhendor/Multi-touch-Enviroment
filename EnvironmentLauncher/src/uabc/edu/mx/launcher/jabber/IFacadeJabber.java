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
package uabc.edu.mx.launcher.jabber;

import uabc.edu.mx.data.session.User;
import java.util.ArrayList;

/**
 *
 * @author Rosendo R. Sosa.
 */
interface IFacadeJabber {

    public void login(User user,String server);

    public void logout();

    public ArrayList getRoster();
    
    public void sendMessage(String msg,String to);
    
    
    
    
}
