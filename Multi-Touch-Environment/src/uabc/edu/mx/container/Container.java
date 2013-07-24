/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uabc.edu.mx.container;

import utils.Size;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import uabc.edu.mx.entities.Entity;
import uabc.edu.mx.entities.PointerEntity;

/**
 *
 * @author xhendor
 */
public abstract class Container extends Entity{

    private volatile Shape toShow;
    protected final ArrayList<Entity> entities;
    protected String id;
    protected final ArrayList<PointerEntity> touchPoints;
    protected final Vector2f position;
    protected final Size size;
    protected final int resize = 10;
    protected int order;
    protected boolean onTop = false;
    protected volatile boolean runningOneFingerThread = false;
    protected volatile boolean runningTwoFingerThread = false;
    protected volatile boolean runningThreeFingerThread = false;
    protected Thread oft;
    protected Thread tft;
    protected Thread twft;
    protected volatile float lastDistance = -1;
    protected volatile float distance = 0;
    protected volatile float grade = -1;
    protected volatile float toRotate = 0;


    /**
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * @return the onTop
     */
    public boolean isOnTop() {
        return onTop;
    }

    /**
     * @param onTop the onTop to set
     */
    public void setOnTop(boolean onTop) {
        this.onTop = onTop;
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
            runningTwoFingerThread = false;
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


    public Container(ArrayList<Entity> entities, Vector2f position, Size size, String id) {
                super(position, id);

        if(entities==null){
        this.entities=new ArrayList<Entity>();
        }else{
        this.entities = entities;
        }
        this.position = position;
        this.size = size;
        this.id = id;
        this.touchPoints = new ArrayList<PointerEntity>();
        //Helper.INSTANCE.getMessageProcessor().registerListener((MessageListener) this, this.toString());

    }

    public void add(Entity entity) {

        entity.setContainer(this);
        this.entities.add(entity);
    }

    public Entity get(String id) {
        synchronized (entities) {
            for (Entity entity : entities) {
                if (entity.getId().equalsIgnoreCase(id)) {
                    return entity;
                }
            }
        }
        return null;
    }

    public boolean remove(String id) {
        synchronized (entities) {
            for (Entity entity : entities) {
                if (entity.getId().equalsIgnoreCase(id)) {
                    entities.remove(entity);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void render(GameContainer gc, Graphics gr) {

        gr.flush();

        synchronized (entities) {
            for (Entity entity : entities) {

                if ((entity.getSize().getWidth() + entity.getPosition().x) > this.getSize().getWidth()) {
                    entity.setPosition(entity.getPosition().x - ((entity.getSize().getWidth() + entity.getPosition().x) - this.getSize().getWidth()), entity.getPosition().y - ((entity.getSize().getHeight() + entity.getPosition().y) - this.getSize().getHeight()));
                }
                entity.render(gc, gr);
            }
        }


    }

    @Override
    public void update(GameContainer gc, int delta) {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the position
     */
    public Vector2f getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Vector2f position) {
        this.position.set(position);
    }

    public int compareTo(Container z) {
        int res = 0;
        if (getOrder() > z.getOrder()) {
            res = 1;
        } else if (getOrder() < z.getOrder()) {
            res = -1;
        }
        return res;
    }

    private void oneFingerThread() {
        runningOneFingerThread = true;
        //Helper.INSTANCE.getEventProcessor().notifyMessage("aWildTwoFingersAppears", this, this, TouchEvents.MESSAGE_ONE_FINGER_EVENT);
    }

    private void twoFingerThread() {
        runningTwoFingerThread = true;
        //Helper.INSTANCE.getEventProcessor().notifyMessage("aWildTwoFingersAppears", this, this, TouchEvents.MESSAGE_TWO_FINGERS_EVENT);
    }

    private void threeFingerThread() {
        runningThreeFingerThread = true;
        //Helper.INSTANCE.getEventProcessor().notifyMessage("aWildTwoFingersAppears", this, this, TouchEvents.MESSAGE_THREE_FINGERS_EVENT);
    }

    @Override
    public String toString() {
        return this.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean containsPoint(Vector2f point) {

        return getToShow().contains(point.x, point.y);
    }

    public boolean containsShape(Shape toCheck) {

        return getToShow().contains(toCheck);
    }

    public boolean intersect(Shape toCheck) {
        return getToShow().intersects(toCheck);
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
}
