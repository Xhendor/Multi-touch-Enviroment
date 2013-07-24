/*
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
package uabc.edu.mx.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.MyRectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import utils.Size;

/**
 *
 * @author xhendor
 */
public class ShapeRenderEntity extends Entity {

    protected Shape toShow;
    protected Color color;
    protected boolean filled;
    protected float ammountOfScale = 0.5f;
    protected volatile float lastDistance = -1;
    protected volatile float distance = 0;
    protected volatile float grade = -1;
    protected volatile float toRotate = 0;
    private volatile boolean runningOneFingerThread = false;
    private volatile boolean runningTwoFingerThread = false;
    private volatile boolean runningThreeFingerThread = false;
    protected Thread oft;
    protected Thread tft;
    protected Thread twft;
    private void updateValues(Graphics gr) {
                gr.setColor(Color.white);
                gr.fill(toShow);
                gr.setColor(Color.black);
                gr.draw(toShow);
    }

    /**
     * @return the ammountOfScale
     */
    public float getAmmountOfScale() {
        return ammountOfScale;
    }

    /**
     * @param ammountOfScale the ammountOfScale to set
     */
    public void setAmmountOfScale(float ammountOfScale) {
        this.ammountOfScale = ammountOfScale;
    }

    /**
     * @return the runningOneFingerThread
     */
    public boolean isRunningOneFingerThread() {
        return runningOneFingerThread;
    }

    /**
     * @param runningOneFingerThread the runningOneFingerThread to set
     */
    public void setRunningOneFingerThread(boolean runningOneFingerThread) {
        this.runningOneFingerThread = runningOneFingerThread;
    }

    /**
     * @return the runningTwoFingerThread
     */
    public boolean isRunningTwoFingerThread() {
        return runningTwoFingerThread;
    }

    /**
     * @param runningTwoFingerThread the runningTwoFingerThread to set
     */
    public void setRunningTwoFingerThread(boolean runningTwoFingerThread) {
        this.runningTwoFingerThread = runningTwoFingerThread;
    }

    /**
     * @return the runningThreeFingerThread
     */
    public boolean isRunningThreeFingerThread() {
        return runningThreeFingerThread;
    }

    /**
     * @param runningThreeFingerThread the runningThreeFingerThread to set
     */
    public void setRunningThreeFingerThread(boolean runningThreeFingerThread) {
        this.runningThreeFingerThread = runningThreeFingerThread;
    }

    public enum TYPE {

        ROUNDRECT, RECT, CIRCULAR, OVAL
    }

    public ShapeRenderEntity(Vector2f position, String id) {
        super(position, id);
    }

    public ShapeRenderEntity(TYPE type, Color color, boolean filled, Vector2f position, Size size, String id) {
        super(position, id);
        setSize(size);
        switch (type) {
            case ROUNDRECT:
                toShow = new RoundedRectangle(position.x, position.y, size.getWidth(), size.getHeight(), 10);
                break;
            case RECT:
                toShow = new MyRectangle(position.x, position.y, size.getWidth(), size.getHeight());

                break;
            case OVAL:
                toShow = new Ellipse(position.x, position.y, size.getWidth() / 2, size.getHeight() / 2);

                break;
            case CIRCULAR:
                toShow = new Circle(position.x, position.y, size.getRadius());
                break;

            default:
                toShow = new Circle(position.x, position.y, size.getRadius());
                break;
        }
        this.color = color;
        this.filled = filled;
    }

    @Override
    public void render(GameContainer gc, Graphics gr) {
        super.render(gc, gr);
        gr.setColor(color);
       
        updateValues(gr);



    }

    @Override
    public void update(GameContainer gc, int delta) {

        super.update(gc, delta);

    }

    /**
     * @return the toShow
     */
    public Shape getToShow() {
        return toShow;
    }

    /**
     * @param toShow the toShow to set
     */
    public void setToShow(Shape toShow) {
        this.toShow = toShow;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the filled
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * @param filled the filled to set
     */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
