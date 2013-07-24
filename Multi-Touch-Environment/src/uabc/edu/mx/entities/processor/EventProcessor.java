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

import java.util.HashMap;
import uabc.edu.mx.entities.Entity;
import uabc.edu.mx.entities.listener.TouchEvent;
import uabc.edu.mx.entities.listener.TouchEventListener;
import uabc.edu.mx.entities.listener.TouchEvents;

import utils.Constants;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class EventProcessor implements IEventProcessor {

    private TouchEventListener evtListener;
    private HashMap<String, TouchEventListener> registredListeners = new HashMap<String, TouchEventListener>();

    @Override
    public void notifyMessage(TouchEvent msg) {

        switch (msg.getType()) {
            case MESSAGE_ONE_FINGER_EVENT:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
            case MESSAGE_TWO_FINGERS_EVENT:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
            case MESSAGE_THREE_FINGERS_EVENT:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
            case MESSAGE_ONE_FINGER_TAP:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
            case MESSAGE_THREE_FINGER_TAP:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
            case MESSAGE_TWO_FINGER_TAP:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
            case MESSAGE_ONE_FINGER_DOUBLE_TAP:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
            case MESSAGE_ONE_FINGER_LONG_TAP:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
            case MESSAGE_THREE_FINGER_DOUBLE_TAP:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
            case MESSAGE_THREE_FINGER_LONG_TAP:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
            case MESSAGE_TWO_FINGER_DOUBLE_TAP:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
            case MESSAGE_TWO_FINGER_LONG_TAP:
                evtListener = (TouchEventListener) getListener(msg.getSource().toString());
                evtListener.eventReciever(msg);
                break;
        }

    }

    @Override
    public void notifyMessage(Object body, TouchEventListener to, TouchEventListener from, Enum type,Entity source) {

        TouchEvent msg = new TouchEvent(body, to, from, (TouchEvents) type,source);
        notifyMessage(msg);
    }

    @Override
    public boolean registerListener(TouchEventListener obj, String name) {
        System.out.println("Registrado:" + name);
        Object respose = registredListeners.put(name, obj);
        if (respose == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public boolean removeListener(String name) {
        Object respose = registredListeners.remove(name);
        if (respose == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public TouchEventListener getListener(String name) {
        TouchEventListener respose = (TouchEventListener) registredListeners.get(name);
        if (respose == null) {
            if (Constants.DEBUG_MODE) {
                System.out.println("Listener Not Found");
            }
            return null;
        } else {
            if (Constants.DEBUG_MODE) {
                System.out.println("ListenerFound");
            }
            return respose;
        }
    }
}
