/*
 * Copyright (C) 2012 rosendorsc
 *
 * This program is free software; you can redistribute it and/or
 *
 * 
 * 
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 */
package uabc.edu.mx.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import utils.Size;

/**
 *
 * @author rosendorsc
 */
public class ImageRenderEntity extends Entity {

    private Image toShow;
    private float zoom = 1 * 0.10f;
    private float rotate;
protected float ammountOfScale = 0.5f;
    protected volatile float lastDistance = -1;
    protected volatile float distance = 0;
    protected volatile float grade = -1;
    protected volatile float toRotate = 0;
    protected volatile boolean runningOneFingerThread = false;
    protected volatile boolean runningTwoFingerThread = false;
    protected volatile boolean runningThreeFingerThread = false;
    protected Thread oft;
    protected Thread tft;
    protected Thread twft;
    public ImageRenderEntity(Vector2f position, Image toShow, String id) {
        super(position, id);
        this.toShow = toShow;
        this.setSize(new Size(toShow.getWidth(), toShow.getHeight()));
    }

    public ImageRenderEntity(Vector2f position, String id) {
        super(position, id);

    }
    public void zoomOut() {
        zoom++;
    }

    public void zoomIn() {
        if (zoom > 1) {
            zoom--;

        }
    }

    public void rotateToLeft() {

        rotate++;
        getToShow().setRotation(rotate * 0.10f);


    }

    public void rotateToRight() {

        rotate--;
        getToShow().setRotation(rotate * 0.10f);

    }

    @Override
    public void render(GameContainer gc, Graphics gr) {
                if (getToShow() != null) {
            if (getContainer() != null) {
                gr.drawImage(getToShow(),  getContainer().getToShow().getCenterX(), getContainer().getToShow().getCenterY());
            } else {
                gr.drawImage(getToShow(), getPosition().x, getPosition().y);
            }
        }
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (getToShow() != null) {
            getToShow().setCenterOfRotation(toShow.getWidth()/2, toShow.getHeight()/2);
        }
    }
    
    

    /**
     * @return the toShow
     */
    public Image getToShow() {
        return toShow;
    }

    public void setToShow(Image toReplace) {
        toShow = toReplace;
    }
}
