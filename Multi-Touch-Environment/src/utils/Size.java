/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author xhendor
 */
public class Size {

    public float width = 0;
    public float height = 0;
    public float radius = 0;
    public float radius1 = 0;
    public float radius2 = 0;

    /**
     * @param width
     * @param height
     */
    public Size(float width, float height) {
        this.height = height;
        this.width = width;
    }

    /**
     * @param radius Radius for Circle
     */
    public Size(float radiusW) {
        this.radius = radiusW;
    }

    /**
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * @return the radius
     */
    public float getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(float radiusW) {
        this.setRadiusW(radiusW);
    }

    /**
     * @param radiusW the radiusW to set
     */
    public void setRadiusW(float radiusW) {
        this.radius = radiusW;
    }

    /**
     * @return the radius1
     */
    public float getRadius1() {
        return radius1;
    }

    /**
     * @param radius1 the radius1 to set
     */
    public void setRadius1(float radius1) {
        this.radius1 = radius1;
    }

    /**
     * @return the radius2
     */
    public float getRadius2() {
        return radius2;
    }

    /**
     * @param radius2 the radius2 to set
     */
    public void setRadius2(float radius2) {
        this.radius2 = radius2;
    }
}
