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

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.*;
import uabc.edu.mx.data.session.User;
import uabc.edu.mx.gui.LoginForm;
import uabc.edu.mx.message.Message;
import uabc.edu.mx.message.MessageType;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class FacadeJabber implements IFacadeJabber {

    XMPPConnection xmpp;

    public FacadeJabber(User user,String server) {

    login(user, server);
    }

  
    @Override
    public void login(User user,String server) {
        try {
           ConnectionConfiguration config = new ConnectionConfiguration(server);
                    xmpp = new XMPPConnection(server);
                    xmpp.connect();
                    User userx = user;
                    xmpp.login(user.getuName(), user.getuPassword());
                    
                    if (xmpp.isAuthenticated()) {
                          SingletonMyMessageProcesor.getInstance().notifyMessage(new Message("", LoginForm.class.getName(), this.toString(), MessageType.MESSAGE_LOGIN_OK));
                    
                          xmpp.getChatManager().addChatListener(new XMPPChatListener());
                    } else {
                       SingletonMyMessageProcesor.getInstance().notifyMessage(new Message("", LoginForm.class.getName(),this.toString(), MessageType.MESSAGE_LOGIN_ERROR));
                        xmpp.disconnect();
                    }
        } catch (XMPPException ex) {
SingletonMyMessageProcesor.getInstance().notifyMessage(new Message("", LoginForm.class.getName(),this.toString(), MessageType.MESSAGE_LOGIN_ERROR));        }
    }

    @Override
    public void logout() {
        xmpp.disconnect();
    }

    @Override
    public ArrayList getRoster() {

        Roster roster = xmpp.getRoster();
        ArrayList<RosterEntry> rosters = new ArrayList<RosterEntry>(roster.getEntries());
        return rosters;
    }

    @Override
    public void sendMessage( String msg, String to) {
        try {
            Chat chat= xmpp.getChatManager().createChat(to, new XMPPMsgListener());
           org.jivesoftware.smack.packet.Message msgr= new org.jivesoftware.smack.packet.Message(to, org.jivesoftware.smack.packet.Message.Type.chat);
            msgr.setBody(msg);
            chat.sendMessage(msgr);
        } catch (XMPPException ex) {
            Logger.getLogger(FacadeJabber.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

    
    
    
    
    
    @Override
    public String toString() {
        return this.getClass().getName(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
