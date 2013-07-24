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

/**
 *
 * @author Rosendo R. Sosa.
 */
public interface ITouchEvent {
    

    public Object getBody();

    /**
     * @return the to
     */
    public Object getTo();

    /**
     * @return the from
     */
    public Object getFrom();

    /**
     * @return the type
     */
    public TouchEvents getType();
    
    public Object getSource() ;
}
