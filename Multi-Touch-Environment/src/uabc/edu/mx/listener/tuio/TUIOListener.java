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
package uabc.edu.mx.listener.tuio;

import uabc.edu.mx.helper.Helper;
import uabc.edu.mx.message.IMessage;
import uabc.edu.mx.message.MessageType;
import uabc.edu.mx.tuio.TuioCursor;
import uabc.edu.mx.tuio.TuioListener;
import uabc.edu.mx.tuio.TuioObject;
import uabc.edu.mx.tuio.TuioTime;

/**
 *
 * @author xhendor
 */
public class TUIOListener implements TuioListener {

    public TUIOListener(Object touchWorld) {
        this.touchWorld = touchWorld;
    }

    public TUIOListener() {
    }
    
    private Object touchWorld;

    @Override
    public void addTuioObject(TuioObject tobj) {
    }

    @Override
    public void updateTuioObject(TuioObject tobj) {
    }

    @Override
    public void removeTuioObject(TuioObject tobj) {
    }

    @Override
    public void addTuioCursor(TuioCursor tcur) {

        if (touchWorld != null) {
            Helper.INSTANCE.getMessageProcessor().notifyMessage(tcur, touchWorld.getClass().getName(), null, MessageType.MESSAGE_CREATE_CURSOR);
        }
    }

    @Override
    public void updateTuioCursor(TuioCursor tcur) {

        if (touchWorld != null) {
            Helper.INSTANCE.getMessageProcessor().notifyMessage(tcur, touchWorld.getClass().getName(), null, MessageType.MESSAGE_UPDATE_CURSOR);
        }
    }

    @Override
    public void removeTuioCursor(TuioCursor tcur) {
        if (touchWorld != null) {
            Helper.INSTANCE.getMessageProcessor().notifyMessage(tcur, touchWorld.getClass().getName(), null, MessageType.MESSAGE_REMOVE_CURSOR);
        }
    }

    @Override
    public void refresh(TuioTime ftime) {
    }

    /**
     * @return the touchWorld
     */
    public Object getTouchWorld() {
        return touchWorld;
    }

    /**
     * @param touchWorld the touchWorld to set
     */
    public void setTouchWorld(Object touchWorld) {
        this.touchWorld = touchWorld;
    }
}
