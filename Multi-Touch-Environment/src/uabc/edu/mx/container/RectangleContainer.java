/*
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
package uabc.edu.mx.container;

import utils.Size;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import uabc.edu.mx.entities.Entity;
import uabc.edu.mx.entities.ImageRenderEntity;
import org.newdawn.slick.geom.MyRectangle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;
import uabc.edu.mx.entities.PointerEntity;
import uabc.edu.mx.entities.ShapeRenderEntity;
import uabc.edu.mx.entities.listener.TouchEvent;
import uabc.edu.mx.entities.listener.TouchEvents;
import utils.Chronometer;

/**
 *
 * @author xhendor
 */
public class RectangleContainer extends Container {

    private volatile boolean rotate = false;
    private volatile float lastDistance2 = -1f;
    private float ammountToScale1 = .95f;
    private float ammountToScale2 = 1.031f;
    private int INCREMENTO = 0;
    private float name = 4;
    protected final RectangleContainer context;

    public RectangleContainer(ArrayList<Entity> entities, Vector2f position, Size size, String id) {
        super(entities, position, size, id);

        setToShow(new MyRectangle(position.x, position.y, size.getWidth(), size.getHeight()));;
        context = this;
    }

    @Override
    public void render(GameContainer gc, Graphics gr) {

        gr.setColor(Color.white);

        getToShow().setLocation(position.x, position.y);
        getToShow().setCenterX(position.x + size.getWidth() / 2);
        getToShow().setCenterY(position.y + size.getHeight() / 2);
        gr.fill(getToShow());
        gr.setColor(Color.black);
        gr.draw(getToShow());

        synchronized (entities) {
            for (Entity entity : entities) {


                entity.render(gc, gr);
            }
        }
        
                gr.flush();

    }

    @Override
    public void update(GameContainer gc, int delta) {
        synchronized (entities) {

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

                        for (Entity entity : entities) {
                            if (entity instanceof ShapeRenderEntity) {
                                ShapeRenderEntity toMachaca = (ShapeRenderEntity) entity;
                                MyRectangle cosa = (MyRectangle) toMachaca.getToShow();
                                Polygon coqsa = (Polygon) toMachaca.getToShow().transform(Transform.createScaleTransform(ammountToScale2, ammountToScale2));
                                cosa.setPuntos(coqsa.getPoints());
                                toMachaca.setToShow(cosa);
                            } else if (entity instanceof ImageRenderEntity) {
                                Image toChange = ((ImageRenderEntity) entity).getToShow().getScaledCopy(ammountToScale2);
                                ((ImageRenderEntity) entity).setToShow(toChange);
                            }

                            entity.update(gc, delta);
                        }
                    }


                } else if (distance > 0) {
                    if ((distance >= name || distance <= -name)) {

                        MyRectangle rec = (MyRectangle) getToShow();
                        Polygon coco = (Polygon) rec.transform(Transform.createScaleTransform(ammountToScale1, ammountToScale1));
                        rec.setPuntos(coco.getPoints());
                        lastDistance2 = distance;
                        size.setWidth(rec.getWidth());
                        size.setHeight(rec.getHeight());
                        for (Entity entity : entities) {
                            if (entity instanceof ShapeRenderEntity) {
                                ShapeRenderEntity toMachaca = (ShapeRenderEntity) entity;
                                MyRectangle cosa = (MyRectangle) toMachaca.getToShow();
                                Polygon coqsa = (Polygon) toMachaca.getToShow().transform(Transform.createScaleTransform(ammountToScale1, ammountToScale1));
                                cosa.setPuntos(coqsa.getPoints());
                                toMachaca.setToShow(cosa);
                            } else if (entity instanceof ImageRenderEntity) {
                                Image toChange = ((ImageRenderEntity) entity).getToShow().getScaledCopy(ammountToScale1);
                                ((ImageRenderEntity) entity).setToShow(toChange);
                            }

                            entity.update(gc, delta);
                        }


                    }
                }

            }


        }
    }

    @Override
    public void addPointer(PointerEntity e) {
        super.addPointer(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Entity entity) {
        if (entity instanceof ImageRenderEntity) {
            MyRectangle current = (MyRectangle) getToShow();
            ImageRenderEntity toCompare = (ImageRenderEntity) entity;

            if (toCompare.getToShow().getWidth() > current.getWidth()) {
                current.setWidth(toCompare.getToShow().getWidth() + resize);
                size.setWidth(toCompare.getToShow().getWidth() + resize);
            }

            if (toCompare.getToShow().getHeight() > current.getHeight()) {
                current.setHeight(toCompare.getToShow().getHeight() + resize);
                size.setHeight(toCompare.getToShow().getHeight() + resize);

            }

        } else if (entity instanceof ShapeRenderEntity) {
            ShapeRenderEntity toCompare = (ShapeRenderEntity) entity;
            MyRectangle current = (MyRectangle) getToShow();

            if (toCompare.getToShow().getWidth() > current.getWidth()) {
                current.setWidth(toCompare.getToShow().getWidth() + resize);
            }

            if (toCompare.getToShow().getHeight() > current.getHeight()) {
                current.setHeight(toCompare.getToShow().getHeight() + resize);

            }

        }
        super.add(entity);
    }
    Vector2f lastP1;
    Vector2f lastP2;
    private int click = -1;

    public void eventReciever(TouchEvent msg) {
        switch (msg.getType()) {

            case MESSAGE_ONE_FINGER_EVENT:
                oft = new Thread(new Runnable() {
                    float lastValX = -1;
                    float lastValY = -1;

                    @Override
                    public void run() {
                        Chronometer chrono = new Chronometer();
                        chrono.start();
                        if (!touchPoints.isEmpty()) {
                            lastValX = touchPoints.get(0).getPosition().x;
                            lastValY = touchPoints.get(0).getPosition().y;
                        }
                        while (runningOneFingerThread) {
                            try {
                                if (!touchPoints.isEmpty()) {

                                    if (touchPoints.get(0) != null) {
                                        Vector2f current = touchPoints.get(0).getPosition();
                                        getPosition().set(getPosition().x + (current.x - lastValX), getPosition().y + (current.y - lastValY));
                                        lastValX = current.x;
                                        lastValY = current.y;
                                    }
                                }
                            } catch (Exception ex) {
                            }


                        }
                        chrono.stop();
                        if (chrono.getSeconds() > 0.075 && chrono.getSeconds() <= 0.090) {
                            eventReciever(new TouchEvent("Click", context, context, TouchEvents.MESSAGE_ONE_FINGER_LONG_TAP,context));
                            click = -1;

                        } else if (chrono.getSeconds() <= 0.075) {
                            if (click == -1) {
                                click++;
                                eventReciever(new TouchEvent("Click", context, context, TouchEvents.MESSAGE_ONE_FINGER_TAP,context));
                            } else {
                                click = -1;
                            }
                        }
                    }
                });
                oft.start();
                break;
            case MESSAGE_TWO_FINGERS_EVENT:
                rotate = true;

                twft = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Chronometer chrono = new Chronometer();
                        chrono.start();
                        while (runningTwoFingerThread) {
                            if (touchPoints.size() == 2) {
                                try {

                                    PointerEntity pp1 = touchPoints.get(0);
                                    PointerEntity pp2 = touchPoints.get(1);
                                    if (pp1 != null && pp2 != null) {
                                        Vector2f p1 = pp1.getPosition();
                                        Vector2f p2 = pp2.getPosition();

                                        float deltaX = p1.x - p2.x;
                                        float deltaY = p1.y - p2.y;
                                        float xPow = (float) Math.pow(deltaX, 2);
                                        float yPow = (float) Math.pow(deltaY, 2);
                                        float toSqr = xPow + yPow;
                                        float sqrResult = (float) Math.sqrt(toSqr);
                                        float m = (p2.y - p1.y) / (p2.x - p1.x);
                                        float newgrade = (float) Math.atan2(deltaX, deltaY);
                                        newgrade = (float) (Math.toDegrees(newgrade));
                                        if (lastP1 != null && lastP2 != null) {

                                            if (lastP1.x != p1.x || lastP1.y != p1.y) {
                                                grade = -1;
                                            }
                                        }

                                        if (grade == -1) {
                                            grade = newgrade;
                                            toRotate = 0;
                                        } else {
                                            toRotate = (newgrade - grade);

                                        }
                                        toRotate = (float) Math.toRadians(toRotate);

                                        if ((newgrade != grade)) {
                                            INCREMENTO = (newgrade >= grade) ? 1 : -1;
                                            if (INCREMENTO == -1) {
                                                synchronized (entities) {
                                                    for (Entity entity : entities) {
                                                        if (entity instanceof ShapeRenderEntity) {
                                                            ShapeRenderEntity toMachaca = (ShapeRenderEntity) entity;
                                                            MyRectangle cosa = (MyRectangle) toMachaca.getToShow();
                                                            Polygon coqsa = (Polygon) toMachaca.getToShow().transform(Transform.createRotateTransform(.03f, toMachaca.getToShow().getCenterX(), toMachaca.getToShow().getCenterY()));
                                                            cosa.setPuntos(coqsa.getPoints());
                                                            toMachaca.setToShow(cosa);
                                                        } else if (entity instanceof ImageRenderEntity) {
                                                            ((ImageRenderEntity) entity).getToShow().rotate(3.2f);
                                                        }



                                                    }
                                                    MyRectangle rec = (MyRectangle) getToShow();
                                                    Polygon coco = (Polygon) getToShow().transform(Transform.createRotateTransform(.03f, rec.getCenterX(), rec.getCenterY()));
                                                    rec.setPuntos(coco.getPoints());
                                                }

                                            } else {


                                                synchronized (entities) {
                                                    for (Entity entity : entities) {

                                                        if (entity instanceof ShapeRenderEntity) {
                                                            ShapeRenderEntity toMachaca = (ShapeRenderEntity) entity;
                                                            MyRectangle cosa = (MyRectangle) toMachaca.getToShow();
                                                            Polygon coqsa = (Polygon) toMachaca.getToShow().transform(Transform.createRotateTransform(-.03f, toMachaca.getToShow().getCenterX(), toMachaca.getToShow().getCenterY()));
                                                            cosa.setPuntos(coqsa.getPoints());
                                                            toMachaca.setToShow(cosa);
                                                        } else if (entity instanceof ImageRenderEntity) {
                                                            ((ImageRenderEntity) entity).getToShow().rotate(-3.2f);
                                                        }

                                                    }
                                                    MyRectangle rec = (MyRectangle) getToShow();
                                                    Polygon coco = (Polygon) getToShow().transform(Transform.createRotateTransform(-.03f, rec.getCenterX(), rec.getCenterY()));
                                                    rec.setPuntos(coco.getPoints());
                                                }
                                            }

                                        } else {
                                            INCREMENTO = 0;
                                        }

                                        if (lastDistance == -1) {
                                            lastDistance = sqrResult;
                                        } else if (lastDistance != sqrResult) {
                                            distance = lastDistance - sqrResult;
                                            lastDistance = sqrResult;
                                        }

                                        lastP1 = p1;
                                        lastP2 = p2;
                                        grade = newgrade;


                                    }
                                } catch (Exception e) {
                                    System.out.println("error:" + e.getMessage());
                                    distance = 0;
                                    grade = -1;
                                    rotate = false;
                                    lastP1 = null;
                                    lastP2 = null;
                                    lastDistance = -1;

                                }
                            }
                        }

                        distance = 0;
                        grade = -1;
                        rotate = false;
                        lastP1 = null;
                        lastP2 = null;
                        lastDistance = -1;

                        chrono.stop();
                        System.out.println("Segundos:" + chrono.getSeconds());

                    }
                });





                twft.start();

                break;
            case MESSAGE_THREE_FINGERS_EVENT:
                tft = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //  System.out.println("TFT INICIADO!!!");
                        while (runningThreeFingerThread) {

                            System.out.println("Tres dedos !!!");
                        }
                        //  System.out.println("TFT TERMINADO!!!");

                    }
                });
                tft.start();
                break;

        }

    }
}
