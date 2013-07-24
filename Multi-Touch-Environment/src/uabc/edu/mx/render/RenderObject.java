/*
 *
 * Copyright (C)  2012 Rosendo R. Sosa. .
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
package uabc.edu.mx.render;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 *
 * @author xhendor
 */
public abstract interface RenderObject {

    /**
     * Render the screen here.
     *
     * @param gc The container holing this game
     * @param g The graphics context that can be used to render. However, normal
     * rendering
     */
    public abstract void render(GameContainer gc, Graphics gr);

    /**
     * Update the logic here. No rendering should take place in this method
     * though it won't do any harm.
     *
     * @param gc The container holing this game
     * @param delta The amount of time thats passed since last update in
     * milliseconds
     */
    public abstract void update(GameContainer gc, int delta);
    
    
    
   
}
