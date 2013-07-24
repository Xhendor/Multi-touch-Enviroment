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
package uabc.edu.mx.entities.listener;

import uabc.edu.mx.entities.Entity;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class TouchEvent implements ITouchEvent{
      private Object body;
    private Object to;
    private Object from;
    private TouchEvents type;
    private Entity source;

    public TouchEvent(Object body, Object to, Object from, TouchEvents type,Entity source) {
        this.body = body;
        this.to = to;
        this.from = from;
        this.type = type;
        this.source=source;
    }

    
    /**
     * @return the to
     */
    @Override
    public Object getTo() {
        return to;
    }


    /**
     * @return the from
     */
    @Override
    public Object getFrom() {
        return from;
    }

   
    /**
     * @return the type
     */
    @Override
    public TouchEvents getType() {
        return type;
    }

    /**
     * @return the body
     */
    @Override
    public Object getBody() {
        return body;
    }  

    /**
     * @return the source
     */
    public Entity getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(Entity source) {
        this.source = source;
    }
}
