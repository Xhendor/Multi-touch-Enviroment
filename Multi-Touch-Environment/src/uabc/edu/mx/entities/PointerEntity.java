/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uabc.edu.mx.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author xhendor
 */
public class PointerEntity extends Entity {
    private Ellipse toShow;
    private boolean created=true;
    private boolean moved=false;

    public PointerEntity(Vector2f position, String id) {
        super(position,id);

    }

    @Override
    public void render(GameContainer gc, Graphics gr) {

        setToShow(new Ellipse(getPosition().x, getPosition().y, 10,10));
        gr.setColor(Color.black);
        gr.draw(getToShow());
    }

    @Override
    public void update(GameContainer gc, int delta) {

        if(toShow!=null&&getPosition()!=null)    {
        toShow.setX(getPosition().x);
            toShow.setY( getPosition().y);}
    }

    /**
     * @return the toShow
     */
    public Ellipse getToShow() {
        if(toShow==null){
                setToShow(new Ellipse(getPosition().x, getPosition().y, 10,10));

        }
        return toShow;
    }

    /**
     * @param toShow the toShow to set
     */
    public void setToShow(Ellipse toShow) {
        this.toShow = toShow;
    }

    /**
     * @return the created
     */
    public boolean isCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(boolean created) {
        this.created = created;
    }

    @Override
    public void setPosition(float x, float y) {
        moved=true;
        super.setPosition(x, y); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the moved
     */
    public boolean isMoved() {
        return moved;
    }



}
