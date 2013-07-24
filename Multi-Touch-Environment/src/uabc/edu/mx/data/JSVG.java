package uabc.edu.mx.data;
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
import java.awt.Color;
import java.awt.Paint;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import utils.SVGRasterizer;
import utils.Tools;

/**
 *
 * @author rosendorsc
 */
public final class JSVG {

    private SVGRasterizer svgRaster;
    private Image slickImage;

    public JSVG() {
    }

    public JSVG(String filePath) {

        loadSVG(filePath);
    }

    /**
     * Load SVG file from String path
     * @param filePath  string path of SVG file
     */
    private void loadSVG(String filePath) {  
        try {
            svgRaster = new SVGRasterizer(new File(filePath).toURI().toURL());
            Paint paint = new Color(0, true);
            svgRaster.setBackgroundColor(paint);
            
            setSlickImage(Tools.toSlickImage(Tools.toImage(svgRaster.createBufferedImage())));
        } catch (Exception ex) {
            Logger.getLogger(JSVG.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /**
     * @return the SVGImage to Slick Image
     */
    public Image getSlickImage() {
        return slickImage;
    }

    
    private void setSlickImage(Image slickImage) {
        this.slickImage = slickImage;
    }
}
