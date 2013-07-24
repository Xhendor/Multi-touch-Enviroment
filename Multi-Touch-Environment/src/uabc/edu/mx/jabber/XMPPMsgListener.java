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

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;
import uabc.edu.mx.helper.Helper;
import uabc.edu.mx.message.MessageType;
import uabc.edu.mx.message.processor.MessageProcessor;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class XMPPMsgListener implements MessageListener{

    
    
    @Override
    public void processMessage(Chat chat, Message msg) {

    
        System.out.println("MessageIn:"+msg.getBody());
        Helper.INSTANCE.getMessageProcessor().notifyMessage(msg.getBody(), MessageProcessor.class.toString(), this, MessageType.MESSAGE_XMPP_MSG_IN);
    
    }
    
}
