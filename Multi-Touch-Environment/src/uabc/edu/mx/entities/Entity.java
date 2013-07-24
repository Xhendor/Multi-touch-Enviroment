/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uabc.edu.mx.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import uabc.edu.mx.container.Container;
import uabc.edu.mx.entities.listener.TouchEventListener;
import uabc.edu.mx.helper.Helper;
import utils.Size;
import uabc.edu.mx.render.RenderObject;

/**
 *
 * @author xhendor
 */
public abstract class Entity implements RenderObject{

    protected Vector2f position;
    protected Size size;
    private Container container;
    protected String id;
    protected int order;
    private TouchEventListener touchListerer;

    public Entity(Vector2f position,  String id) {


        this.position = position;
        
        this.id = id;
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
    public void setPosition(float x, float y) {
        this.position = new Vector2f(x, y);
    }

    /**
     * @return the container
     */
    public Container getContainer() {
        return container;
    }

    /**
     * @param container the container to set
     */
    public void setContainer(Container container) {
        this.container = container;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void render(GameContainer gc, Graphics gr) {
    }

    @Override
    public void update(GameContainer gc, int delta) {
    }

    /**
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Size size) {
        this.size = size;
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
     * @return the touchListerer
     */
    public TouchEventListener getTouchListerer() {
        return touchListerer;
    }

    /**
     * @param touchListerer the touchListerer to set
     */
    public void addTouchListerer(TouchEventListener touchListerer) {
        
        this.touchListerer = touchListerer;
                Helper.INSTANCE.getEventProcessor().registerListener(touchListerer, this.toString());

    }

    @Override
    public String toString() {
        return this.getId(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
