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
package uabc.edu.mx.container;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Vector2f;
import uabc.edu.mx.entities.Entity;
import uabc.edu.mx.entities.ImageRenderEntity;
import uabc.edu.mx.entities.listener.TouchEvent;
import uabc.edu.mx.entities.listener.TouchEvents;
import uabc.edu.mx.jabber.JabberSession;

import uabc.edu.mx.data.multitouch.app.AppState;
import uabc.edu.mx.entities.PointerEntity;
import utils.Chronometer;
import utils.Constants;
import utils.Size;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class UserContainer extends Container {

    private ImageRenderEntity avatar;
    private int currentCorner = -1;
    private float angle = 180;
    private AppState state;
    private UserContainer context;
    private int offset = 0;
    private String[] menu = {"Listo", "Opcion1", "Opcion2"};
    private boolean showMenu = false;
    private float centerX;
    private float centerY;
    private boolean valid;
    private int OFFSET_SPACES;

    public UserContainer(ArrayList<Entity> entities, Vector2f position, Size size, JabberSession session, ImageRenderEntity avatar, String id) {
        super(entities, position, size, id);
        try {
            context = this;
            setToShow(new RoundedRectangle(position.x, position.y, size.width / 2, size.width/ 2, 10));
            this.avatar = avatar;
            
            centerX = position.x + this.getToShow().getWidth() / 2;
            centerY = position.y + size.height / 2;
            avatar.setPosition(centerX + avatar.getToShow().getWidth() / 2, centerY + avatar.getToShow().getHeight() / 2);
        } catch (Exception ex) {
            Logger.getLogger(UserContainer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void add(Entity entity) {
        //   super.add(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GameContainer gc, Graphics gr) {
       if(currentCorner>0){
             //                 drawMenu(gr);

       }
        gr.rotate(this.getToShow().getWidth() / 2 + getToShow().getX(), getToShow().getY() + this.getToShow().getWidth() / 2, angle);
        gr.setColor(Color.white);
        gr.fill(getToShow());
        gr.setColor(Color.black);
        gr.draw(getToShow());

        synchronized (entities) {
            for (Entity entity : entities) {


                entity.render(gc, gr);
            }
        }
        
        if (currentCorner > 0) {

            drawName(gr);
            // drawButton(gr);
        }
        
         gr.resetTransform();
        

        if (avatar != null) {

               gr.rotate(this.getToShow().getWidth() / 2 + getToShow().getX(), getToShow().getY()+this.getToShow().getWidth() / 2, angle);
               

            avatar.render(gc, gr);
              gr.resetTransform();

        }





    }

    @Override
    public void update(GameContainer gc, int delta) {

        if (state != AppState.START&&state != AppState.PAUSE) {
            if (!expulse) {
                OFFSET_SPACES = 8;


                if (this.getToShow().getX() <= OFFSET_SPACES && getToShow().getY()<= OFFSET_SPACES) {

                    if (!Corners.corners.containsKey(new Integer(1))) {

                        float A = 0 - this.getToShow().getWidth() / 4;
                        float B = 0 - this.getToShow().getWidth() / 4 + offset;
                        this.position.set(A, B);
                        angle = 90;
                        Corners.corners.put(new Integer(1), this);
                        currentCorner = 1;


                    }

                } else if (this.getToShow().getX() <= OFFSET_SPACES && this.getToShow().getY() + this.getToShow().getWidth() >= Constants.HEIGHT-OFFSET_SPACES) {

                    if (!Corners.corners.containsKey(new Integer(2))) {
                        angle = 0;
                        // float A = this.getToShow().getX() + (0 - this.getToShow().getX()) - this.getToShow().getWidth() / 2;
                        //   float B = this.getToShow().getY() + (Constants.HEIGHT - this.getToShow().getY()) - this.size.height / 2;
                        float A = 0 - (this.getToShow().getWidth() /4);
                        float B = Constants.HEIGHT - this.getToShow().getWidth()/2-(this.getToShow().getWidth()/4)  - offset;
                        this.position.set(A, B);
                        Corners.corners.put(new Integer(2), this);

                        currentCorner = 2;

                    }


                } else if (this.getToShow().getX() + this.getToShow().getWidth() >= Constants.WIDTH-OFFSET_SPACES && this.getToShow().getY() <= OFFSET_SPACES) {
                    if (!Corners.corners.containsKey(new Integer(4))) {

                        angle = -180;
                        float A = Constants.WIDTH - (this.getToShow().getWidth()-(this.getToShow().getWidth()/4));
                        float B = 0 - this.getToShow().getWidth()/4 + offset;

                        this.position.set(A, B);
                        Corners.corners.put(new Integer(4), this);
                        currentCorner = 4;


                    }

                } else if (this.getToShow().getX() + this.getToShow().getWidth() >= Constants.WIDTH-OFFSET_SPACES && this.getToShow().getY() + +this.getToShow().getHeight()>= Constants.HEIGHT-OFFSET_SPACES) {
                    if (!Corners.corners.containsKey(new Integer(3))) {
                        angle = -90;
                        // float A = this.getToShow().getX() + (Constants.WIDTH - this.getToShow().getX()) - this.getToShow().getWidth() / 2;
                        // float B = this.getToShow().getY() + (Constants.HEIGHT - this.getToShow().getY()) - this.size.height / 2;
                        float A = Constants.WIDTH - (this.getToShow().getWidth()-(this.getToShow().getWidth()/4)  );
                        float B = Constants.HEIGHT - (this.getToShow().getHeight()-(this.getToShow().getWidth()/4)) - offset;
                        this.position.set(A, B);
                        Corners.corners.put(new Integer(3), this);
                        currentCorner = 3;


                    }

                }

            } else if (expulse) {
                if (currentCorner == 1) {
                    this.position.set(10, 10);

                } else if (currentCorner == 2) {
                    this.position.set(100, 500);

                } else if (currentCorner == 3) {
                    this.position.set(Constants.WIDTH - this.getToShow().getWidth() - 50, Constants.HEIGHT - this.getToShow().getWidth() - 50);

                } else if (currentCorner == 4) {
                    this.position.set(Constants.WIDTH - this.getToShow().getWidth() - 50, 50);

                }

                if (currentCorner != -1) {
                    Corners.corners.remove(currentCorner);
                }

                currentCorner = -1;
                expulse = false;
            }


        }
        getToShow().setLocation(position.x, position.y);
        centerX = position.x + this.getToShow().getWidth() / 2;

        centerY = position.y + this.getToShow().getWidth() / 2;
        avatar.setPosition(centerX - avatar.getToShow().getWidth() / 2, centerY - avatar.getToShow().getHeight() /2);

    }

    public void setState(AppState appState) {
        this.state = appState;
    }
    private int click = -1;
    private int INCREMENTO = 0;
    private volatile boolean rotate = false;
    private boolean expulse;

    public ArrayList<PointerEntity> getTouchPoints() {
        return touchPoints;
    }

    public void eventReciever(TouchEvent msg) {
        switch (msg.getType()) {

            case MESSAGE_ONE_FINGER_EVENT:
                if (state != AppState.START) {
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
                            Vector2f last = null;
                            while (runningOneFingerThread) {
                                try {
                                    if (!touchPoints.isEmpty()) {

                                        if (touchPoints.get(0) != null) {
                                            Vector2f current = touchPoints.get(0).getPosition();
                                            last = current;
                                            if (currentCorner == -1) {



                                                if(isValid(getPosition().x + (current.x - lastValX),getPosition().y + (current.y - lastValY))==CollisionWall.NONE) {
                                                    getPosition().set(getPosition().x + (current.x - lastValX), getPosition().y + (current.y - lastValY));

                                                }
                                            }
                                            lastValX = current.x;
                                            lastValY = current.y;
                                        }
                                    }
                                } catch (Exception ex) {
                                }


                            }
                            chrono.stop();
                            if (chrono.getSeconds() <= 0.70) {

                                eventReciever(new TouchEvent(last, context, context, TouchEvents.MESSAGE_ONE_FINGER_TAP,context));

                            }
                        }
                    });
                    oft.start();
                }

                break;
            case MESSAGE_TWO_FINGERS_EVENT:
                rotate = true;
                if (currentCorner > 0) {
                    twft = new Thread(new Runnable() {
                        float lastValX = -1;
                        float lastValY = -1;

                        @Override
                        public void run() {
                            Chronometer chrono = new Chronometer();
                            chrono.start();
                            while (runningTwoFingerThread) {


                                if (chrono.getSeconds() > 0.80) {

                                    expulse = true;
                                }
                            }
                        }
                    });
                    twft.start();
                }
                /*                twft = new Thread(new Runnable() {
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
                 System.out.println("error:" + e.getTouchEvent());
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
                 */




                // twft.start();

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



            case MESSAGE_TWO_FINGER_LONG_TAP:

                break;


            case MESSAGE_ONE_FINGER_TAP:
                if (msg.getBody() != null) {
                    Vector2f pointer = (Vector2f) msg.getBody();
                    if (showMenu) {
                        showMenu = false;
                    } else {
                        showMenu = this.getToShow().contains(pointer.x, pointer.y);

                    }
                    System.out.println("ShowMenu:" + showMenu);
                }

                break;
        }
    }

    private void drawName(Graphics gr){
        gr.setColor(Color.lightGray);
        gr.draw(new RoundedRectangle(getToShow().getX(), getToShow().getY(), getToShow().getWidth(), 30, 10));
        gr.setColor(Color.black);
        gr.drawString(id, getToShow().getCenterX()-avatar.getSize().width/2, getToShow().getY()+10);
    }
    private void drawButton(Graphics gr) {
        /*float incremento = (float) (2 * Math.PI / 4);
        gr.setColor(Color.red);
        for (int i = 0; i < 4; i++) {

            float angulo = incremento * i;

            float xslice = (float) Math.cos(angulo) * (this.getToShow().getWidth() / 2);
            float yslice = (float) Math.sin(angulo) * (this.getToShow().getWidth() / 2);

            gr.drawLine(this.getToShow().getWidth() / 2 + position.x, position.y + this.getToShow().getWidth() / 2, ((this.getToShow().getWidth() / 2) + position.x + xslice), ((this.getToShow().getWidth() / 2 + position.y) + yslice));
            gr.rotate(this.getToShow().getWidth() / 2 + position.x, position.y + this.getToShow().getWidth() / 2, (float) Math.toDegrees(angulo) + 35);
            gr.drawString("Opciones", this.getToShow().getWidth() / 2 + position.x + 35, position.y - 3 + this.getToShow().getWidth() / 2);
            gr.resetTransform();


        }
*/
        
    
    }

    private void drawMenu(Graphics gr) {
        Rectangle rectangale;
        switch (currentCorner) {

            case 1:
                gr.setColor(Color.white);
                rectangale = new Rectangle(0, 0, this.getToShow().getWidth() / 2, Constants.HEIGHT / 2);
                gr.fill(rectangale);
                gr.setColor(Color.black);
                gr.draw(rectangale);
                gr.setColor(Color.black);
                gr.draw(getToShow());
                gr.rotate(rectangale.getCenterX(), rectangale.getCenterY(), 90);
                for (int i = 0; i < menu.length; i++) {
                    String string = menu[i];
                    RoundedRectangle round = new RoundedRectangle(rectangale.getCenterX() - 80, rectangale.getCenterY() - (15 * (i + 1)), 80, (15 * (i + 1)), 40);
                    gr.setColor(Color.black);
                    gr.draw(round);
                    gr.drawString(string, rectangale.getCenterX() - string.length(), rectangale.getCenterY() + (15 * (i + 1)));

                }
                gr.resetTransform();

                break;

            case 2:
                gr.setColor(Color.white);
                rectangale = new Rectangle(0, Constants.HEIGHT - this.getToShow().getWidth() / 2, Constants.HEIGHT / 2, this.getToShow().getWidth() / 2);
                gr.fill(rectangale);
                gr.setColor(Color.black);
                gr.draw(rectangale);
                gr.setColor(Color.black);
                gr.draw(getToShow());
                break;
            case 3:
                gr.setColor(Color.white);
                rectangale = new Rectangle(Constants.WIDTH - this.getToShow().getWidth() / 2, Constants.HEIGHT - Constants.HEIGHT / 2, this.getToShow().getWidth() / 2, Constants.HEIGHT / 2);
                gr.fill(rectangale);
                gr.setColor(Color.black);
                gr.draw(rectangale);
                gr.setColor(Color.black);
                gr.draw(getToShow());
                break;
            case 4:
                gr.setColor(Color.white);
                rectangale = new Rectangle(Constants.WIDTH - Constants.HEIGHT / 2, 0, Constants.HEIGHT / 2, this.getToShow().getWidth() / 2);
                gr.fill(rectangale);
                gr.setColor(Color.black);
                gr.draw(rectangale);
                gr.setColor(Color.black);
                gr.draw(getToShow());
                break;

        }



    }
public enum CollisionWall{
RIGHT,LEFT,DOWN,UP,NONE
}
    public CollisionWall isValid(float nextX,float nextY) {




        float valueX = nextX;
        float valueY = nextY;



        if (valueX <= 0) {
            return CollisionWall.LEFT;
        } else if (valueX + getToShow().getWidth() >= Constants.WIDTH) {
            return CollisionWall.RIGHT;
        } else if ((valueY) <= 0) {
            return CollisionWall.UP;
        }else if (valueY + getToShow().getHeight()>= Constants.HEIGHT) {
            return CollisionWall.DOWN;
        } 

    
    // }
    return CollisionWall.NONE ;
}
}
