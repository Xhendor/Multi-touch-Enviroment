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
package test;

import uabc.edu.mx.entities.RectangleRenderEntity;
import uabc.edu.mx.entities.listener.TouchEvent;
import uabc.edu.mx.entities.listener.TouchEventListener;
import uabc.edu.mx.helper.Helper;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class NewClass {

    public static void main(String[] args) {


        RectangleRenderEntity rect = new RectangleRenderEntity(null, null, null);
        rect.addTouchListerer(new TouchEventListener() {
            @Override
            public void eventReciever(TouchEvent msg) {
                switch (msg.getType()) {

                    case MESSAGE_ONE_FINGER_EVENT:
        
                        break;

                    case MESSAGE_THREE_FINGER_DOUBLE_TAP:

                        break;

                }

            }
        });
    }
}
