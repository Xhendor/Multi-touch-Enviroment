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
package uabc.edu.mx.entities;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.MyRectangle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import uabc.edu.mx.entities.listener.*;
import uabc.edu.mx.helper.Helper;

import utils.Size;

/**
 *
 * @author xhendor
 */
public class RectangleRenderEntity extends ShapeRenderEntity {


    private final ArrayList<PointerEntity> touchPoints;
    private final RectangleRenderEntity context;
    private volatile float lastDistance2 = -1f;
    private float ammountToScale1 = .95f;
    private float ammountToScale2 = 1.031f;

    private float name = 4;

    public RectangleRenderEntity(Vector2f position, Size size, String id) {
        super(position, id);
        setToShow(new MyRectangle(position.x, position.y, size.width, size.height));
        this.setSize(size);
        touchPoints = new ArrayList<PointerEntity>();
        context = this;
    } 

    @Override
    public Vector2f getPosition() {
        return super.getPosition(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GameContainer gc, Graphics gr) {

        if (this.getToShow() != null) {
            gr.setColor(Color.white);

            getToShow().setLocation(position.x, position.y);
            getToShow().setCenterX(position.x + size.getWidth() / 2);
            getToShow().setCenterY(position.y + size.getHeight() / 2);
            gr.fill(getToShow());
            gr.setColor(Color.black);
            gr.draw(getToShow());


        }

    }

    @Override
    public void update(GameContainer gc, int delta) {

        if (lastDistance2 == -1f) {
            lastDistance2 = distance;
        }
        if (lastDistance2 != distance) {

            if (distance < 0) {
                if ((distance >= name || distance <= -name)) {
                    MyRectangle rec = (MyRectangle) getToShow();
                    Polygon coco = (Polygon) rec.transform(Transform.createScaleTransform(ammountToScale2, ammountToScale2));
                    rec.setPuntos(coco.getPoints());
                    lastDistance2 = distance;
                    size.setWidth(rec.getWidth());
                    size.setHeight(rec.getHeight());
                }
            } else if (distance > 0) {
                if ((distance >= name || distance <= -name)) {

                    MyRectangle rec = (MyRectangle) getToShow();
                    Polygon coco = (Polygon) rec.transform(Transform.createScaleTransform(ammountToScale1, ammountToScale1));
                    rec.setPuntos(coco.getPoints());
                    lastDistance2 = distance;
                    size.setWidth(rec.getWidth());
                    size.setHeight(rec.getHeight());
                }
            }

        }
    }

    private void oneFingerThread() {
        setRunningOneFingerThread(true);
        if (getTouchListerer() != null) {
            Helper.INSTANCE.getEventProcessor().notifyMessage("aWildTwoFingersAppears", getTouchListerer(), getTouchListerer(), TouchEvents.MESSAGE_ONE_FINGER_EVENT,this);
        }

    }

    private void twoFingerThread() {
        setRunningTwoFingerThread(true);
        if (getTouchListerer() != null) {

            Helper.INSTANCE.getEventProcessor().notifyMessage("aWildTwoFingersAppears", getTouchListerer(), getTouchListerer(), TouchEvents.MESSAGE_TWO_FINGERS_EVENT,this);

        }

    }

    private void threeFingerThread() {
        setRunningThreeFingerThread(true);
        if (getTouchListerer() != null) {

            Helper.INSTANCE.getEventProcessor().notifyMessage("aWildTwoFingersAppears", getTouchListerer(), getTouchListerer(), TouchEvents.MESSAGE_THREE_FINGERS_EVENT,this);
        }




    }

    public void addPointer(PointerEntity e) {
        System.out.println("ADD!!!");
        int indexToRemove = getTouchPoints().indexOf(e);
        if (indexToRemove == -1) {
            this.getTouchPoints().add(e);
        }

        if (getTouchPoints().size() == 1) {

            oneFingerThread();
        } else if (getTouchPoints().size() == 2) {
            setRunningOneFingerThread(false);
            twoFingerThread();
        } else if (getTouchPoints().size() == 3) {
            threeFingerThread();
        }
    }

    public void removePointer(PointerEntity e) {
        System.out.println("REMOVE!!!");

        int indexToRemove = getTouchPoints().indexOf(e);
        if (indexToRemove > -1) {
            this.getTouchPoints().remove(e);
            if (getTouchPoints().isEmpty()) {
                setRunningOneFingerThread(false);
            } else if (getTouchPoints().size() == 1) {
                setRunningTwoFingerThread(false);
                oneFingerThread();
            } else if (getTouchPoints().size() == 2) {
                setRunningThreeFingerThread(false);
                twoFingerThread();
            } else if (getTouchPoints().size() == 3) {
                threeFingerThread();
            }
        }

    }

    @Override
    public String toString() {
        return this.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the touchPoints
     */
    public ArrayList<PointerEntity> getTouchPoints() {
        return touchPoints;
    }

}
