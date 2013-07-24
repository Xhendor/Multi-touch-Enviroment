/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uabc.edu.mx.entities;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.MyRectangle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import uabc.edu.mx.data.JPDF;
import uabc.edu.mx.entities.listener.TouchEvent;
import uabc.edu.mx.entities.listener.TouchEvents;
import uabc.edu.mx.entities.listener.TouchEventListener;
import uabc.edu.mx.helper.Helper;
import uabc.edu.mx.message.Message;
import uabc.edu.mx.message.MessageType;

import utils.Chronometer;

/**
 *
 * @author xhendor
 */
public class PDFRenderEntity extends ImageRenderEntity  {

    private final ArrayList<Image> pages;
    private int currentPage = 0;
    private MyRectangle bounds;
    private JPDF jpdf;
    private Vector2f lastP1;
    private Vector2f lastP2;
    private int click = -1;
    final ArrayList<PointerEntity> touchPoints;
    private final PDFRenderEntity context;
    private volatile float lastDistance2 = -1f;
    private float ammountToScale1 = .95f;
    private float ammountToScale2 = 1.031f;
    private int INCREMENTO = 0;
    private volatile boolean rotate = false;
    private float name = 4;
    private float accumAngle = 0;

    public PDFRenderEntity(Vector2f position, JPDF jpdf, String id) {
        super(position, id);
        this.jpdf = jpdf;
        bounds = new MyRectangle(position.x, position.y, jpdf.getPage(0).getWidth()/2, jpdf.getPage(0).getHeight()/2);
        context = this;
        touchPoints = new ArrayList<PointerEntity>();

        pages = jpdf.getAllPages();
        setToShow(jpdf.getPage(currentPage));
    }

    public void next() {

        if (currentPage < pages.size()) {
            currentPage++;
            Image img=pages.get(currentPage + 1);
            img.rotate(accumAngle);
            setToShow(img);
        }


    }

    public void previous() {
        if (currentPage > 0) {
            currentPage--;
            Image img=pages.get(currentPage + 1);
            img.rotate(accumAngle);
            setToShow(img);
        }

    }

    @Override
    public void render(GameContainer gc, Graphics gr) {
        if (jpdf != null) {

            getToShow().draw(position.x, position.y);

        }
    }

    public boolean intersect(Shape shape) {

        return bounds.intersects(shape);
    }

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);

    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (lastDistance2 == -1f) {
            lastDistance2 = distance;
        }
        if (lastDistance2 != distance) {

            if (distance < 0) {
                if ((distance >= name || distance <= -name)) {
                    MyRectangle rec = (MyRectangle) bounds;
                    Polygon coco = (Polygon) rec.transform(Transform.createScaleTransform(ammountToScale2, ammountToScale2));
                    rec.setPuntos(coco.getPoints());
                    getToShow().draw(bounds.getX(), bounds.getY(), ammountToScale2);

                    lastDistance2 = distance;
                    size.setWidth(rec.getWidth());
                    size.setHeight(rec.getHeight());
                }
            } else if (distance > 0) {
                if ((distance >= name || distance <= -name)) {

                    MyRectangle rec = (MyRectangle) bounds;
                    Polygon coco = (Polygon) rec.transform(Transform.createScaleTransform(ammountToScale1, ammountToScale1));
                    rec.setPuntos(coco.getPoints());
                    lastDistance2 = distance;
                    size.setWidth(rec.getWidth());
                    size.setHeight(rec.getHeight());
                    getToShow().draw(bounds.getX(), bounds.getY(), ammountToScale1);

                }
            }

        }
    }

    private void oneFingerThread() {
        runningOneFingerThread = true;
        Helper.INSTANCE.getMessageProcessor().notifyMessage("aWildTwoFingersAppears", this, this, TouchEvents.MESSAGE_ONE_FINGER_EVENT);


    }

    private void twoFingerThread() {
        runningTwoFingerThread = true;
        Helper.INSTANCE.getMessageProcessor().notifyMessage("aWildTwoFingersAppears", this, this, TouchEvents.MESSAGE_TWO_FINGERS_EVENT);



    }

    private void threeFingerThread() {
        runningThreeFingerThread = true;
        Helper.INSTANCE.getMessageProcessor().notifyMessage("aWildTwoFingersAppears", this, this, TouchEvents.MESSAGE_THREE_FINGERS_EVENT);




    }

    public void addPointer(PointerEntity e) {
        int indexToRemove = touchPoints.indexOf(e);
        if (indexToRemove == -1) {
            // synchronized (touchPoints) {
            this.touchPoints.add(e);
        }

        if (touchPoints.size() == 1) {

            oneFingerThread();
        } else if (touchPoints.size() == 2) {
            runningOneFingerThread = false;
            twoFingerThread();
        } else if (touchPoints.size() == 3) {
            threeFingerThread();
        }
        // }
    }

    public void removePointer(PointerEntity e) {

        int indexToRemove = touchPoints.indexOf(e);
        if (indexToRemove > -1) {
            this.touchPoints.remove(e);
            if (touchPoints.isEmpty()) {
                runningOneFingerThread = false;
            } else if (touchPoints.size() == 1) {
                runningTwoFingerThread = false;
                oneFingerThread();
            } else if (touchPoints.size() == 2) {
                runningThreeFingerThread = false;
                twoFingerThread();
            } else if (touchPoints.size() == 3) {
                threeFingerThread();
            }
        }

    }

    @Override
    public String toString() {
        return this.getId(); //To change body of generated methods, choose Tools | Templates.
    }

   /* @Override
    public void eventReciever(TouchEvent msg) {
        switch (msg.getType()) {
            case MESSAGE_THREE_FINGER_TAP:

                next();
                break;
            case MESSAGE_TWO_FINGER_TAP:

                previous();
                break;

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
                        if (chrono.getSeconds() <= 0.8) {
                            if (click == -1) {
                                click++;
                                eventReciever(new TouchEvent("Click", context, context, TouchEvents.MESSAGE_ONE_FINGER_TAP));
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
                                                accumAngle += Math.toDegrees(0.03);
                                                MyRectangle rec = (MyRectangle) bounds;
                                                Polygon coco = (Polygon) bounds.transform(Transform.createRotateTransform(.03f, rec.getCenterX(), rec.getCenterY()));
                                                rec.setPuntos(coco.getPoints());
                                                getToShow().rotate((float) Math.toDegrees(0.03));

                                            } else {

                                                accumAngle += Math.toDegrees(-0.03);


                                                MyRectangle rec = (MyRectangle) bounds;
                                                Polygon coco = (Polygon) bounds.transform(Transform.createRotateTransform(-.03f, rec.getCenterX(), rec.getCenterY()));
                                                rec.setPuntos(coco.getPoints());
                                                getToShow().rotate((float) Math.toDegrees(-0.03));

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
                        chrono.stop();
                        if (chrono.getSeconds() <= 0.8) {
                            if (click == -1) {
                                click++;
                                eventReciever(new TouchEvent("Click", context, context, TouchEvents.MESSAGE_TWO_FINGER_TAP));
                            } else {
                                click = -1;
                            }
                        }


                    }
                });





                twft.start();

                break;
            case MESSAGE_THREE_FINGERS_EVENT:
                tft = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Chronometer chrono = new Chronometer();
                        chrono.start();
                        //  System.out.println("TFT INICIADO!!!");
                        while (runningThreeFingerThread) {
                        }
                        if (chrono.getSeconds() <= 0.8) {
                            if (click == -1) {
                                click++;
                                eventReciever(new TouchEvent("Click", context, context, TouchEvents.MESSAGE_THREE_FINGER_TAP));
                            } else {
                                click = -1;
                            }
                        }
                    }
                });
                tft.start();
                break;

        }
    }*/
}
