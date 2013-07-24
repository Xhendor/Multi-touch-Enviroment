/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uabc.edu.mx.entities;

import org.newdawn.slick.geom.Vector2f;
import uabc.edu.mx.data.JSVG;

/**
 *
 * @author xhendor
 */
public class SVGRenderEntity extends ImageRenderEntity{
private JSVG jsvg;
    public SVGRenderEntity(Vector2f position,JSVG jsvg,  String id) {
        super(position,  id);
        this.jsvg=jsvg;
        setToShow(jsvg.getSlickImage());
    }
    
    
    
}
