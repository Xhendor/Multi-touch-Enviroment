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
package uabc.edu.mx.entities.processor;

import uabc.edu.mx.entities.Entity;
import uabc.edu.mx.entities.listener.TouchEvent;
import uabc.edu.mx.entities.listener.TouchEventListener;

/**
 *
 * @author Rosendo R. Sosa.
 */
interface IEventProcessor {

    public void notifyMessage(TouchEvent msg);

    public void notifyMessage(Object body, TouchEventListener to, TouchEventListener from, Enum type,Entity source);

    public boolean registerListener(TouchEventListener obj, String name);

    public boolean removeListener(String name);

    public TouchEventListener getListener(String name);
}
