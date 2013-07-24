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
package uabc.edu.mx.message.processor;

import uabc.edu.mx.message.listener.MessageListener;
import uabc.edu.mx.message.Message;
import uabc.edu.mx.world.TouchWorld;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xhendor
 */
public interface IMessageProcessor {

    public void notifyMessage(Message msg);
    
    public void notifyMessage(Object body, Object to, Object from, Enum type);

    public boolean registerListener(MessageListener obj, String name);

    public boolean removeListener(String name);

    public MessageListener getListener(String name);
     
    public void setTouchWorld(TouchWorld touchWorld);
}
