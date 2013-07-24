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

import java.util.HashMap;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class XMPPChatListener implements ChatManagerListener {

    HashMap<String, Chat> chats = new HashMap<String, Chat>();

    @Override
    public void chatCreated(Chat chat, boolean bln) {


        if (!chats.containsKey(chat.getParticipant())) {
        
        chats.put(chat.getParticipant(), chat);
        chat.addMessageListener(new XMPPMsgListener());
        }

    }
}
