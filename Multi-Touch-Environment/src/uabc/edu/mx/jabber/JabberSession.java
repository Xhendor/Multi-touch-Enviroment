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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.*;
import org.jivesoftware.smackx.packet.VCard;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import uabc.edu.mx.data.session.User;
import uabc.edu.mx.helper.Helper;
import uabc.edu.mx.message.Message;
import uabc.edu.mx.message.MessageType;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class JabberSession implements IFacadeJabber {

    private XMPPConnection xmpp;
    private User user;
    private  boolean isApplicationMT;

    public JabberSession() {
    }

    public JabberSession(User user, String server) {
        this.user = user;
        login(user, server);
    }
    public JabberSession(User user, String server,boolean isApplicationMT) {
        this.user = user;
        this.isApplicationMT=isApplicationMT;
        login(user, server);
    }
    @Override
    public void login(User user, String server) {
        try {
            ConnectionConfiguration config = new ConnectionConfiguration(server);
            setXmpp(new XMPPConnection(server));
            getXmpp().connect();
            User userx = user;
            getXmpp().login(user.getuName(), user.getuPassword());

            if (getXmpp().isAuthenticated()) {
                Helper.INSTANCE.getMessageProcessor().notifyMessage(new Message("", "uabc.edu.mx.main.Main", this.toString(), MessageType.MESSAGE_LOGIN_OK));

                if(!isApplicationMT)
                getXmpp().getChatManager().addChatListener(new XMPPChatListener());
            } else {
                Helper.INSTANCE.getMessageProcessor().notifyMessage(new Message("", "uabc.edu.mx.main.Main", this.toString(), MessageType.MESSAGE_LOGIN_ERROR));
                getXmpp().disconnect();
            }
        } catch (XMPPException ex) {
            Helper.INSTANCE.getMessageProcessor().notifyMessage(new Message("", "uabc.edu.mx.main.Main", this.toString(), MessageType.MESSAGE_LOGIN_ERROR));
        }
    }

    @Override
    public void logout() {
        getXmpp().disconnect();
    }

    @Override
    public ArrayList getRoster() {

        Roster roster = getXmpp().getRoster();
        ArrayList<RosterEntry> rosters = new ArrayList<RosterEntry>(roster.getEntries());
        return rosters;
    }

    @Override
    public void sendMessage(String msg, String to) {
        try {
            System.out.println("Enviado:"+msg);
            Chat chat = getXmpp().getChatManager().createChat(to, new XMPPMsgListener());
            org.jivesoftware.smack.packet.Message msgr = new org.jivesoftware.smack.packet.Message(to, org.jivesoftware.smack.packet.Message.Type.chat);
            msgr.setBody(msg);
            chat.sendMessage(msgr);
        } catch (XMPPException ex) {
            Logger.getLogger(JabberSession.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String toString() {
        return this.getClass().getName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Image getAvatar() {
        VCard vCard = new VCard();
        try {
            vCard.load(getXmpp()); // load own VCard

            InputStream myInputStream = new ByteArrayInputStream(vCard.getAvatar());



            Image image = new Image(myInputStream, getUser().getuName(), false);
            return image;


        } catch (XMPPException ex) {
            Logger.getLogger(JabberSession.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SlickException ex) {
            Logger.getLogger(JabberSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) throws XMPPException, IOException {
        ConnectionConfiguration config = new ConnectionConfiguration("192.168.200.102");
        XMPPConnection    xmpp = new XMPPConnection(config);
            xmpp.connect();
            xmpp.login("admin@edumat", "chendo22");
            
            VCard card=new VCard();
            
            
            for (int i = 0; i < 5; i++) {
            
                
                card.load(xmpp,"user"+(i+1)+"@edumat");
                
                card.save(xmpp);
                File files=new File("/Users/xhendor/Desktop/"+(i+1)+".png");
                card.setAvatar(VCard.getBytes(files.toURL()));
                
        }
            

    }

    /**
     * @return the xmpp
     */
    public XMPPConnection getXmpp() {
        return xmpp;
    }

    /**
     * @param xmpp the xmpp to set
     */
    public void setXmpp(XMPPConnection xmpp) {
        this.xmpp = xmpp;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
