package uabc.edu.mx.data;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.newdawn.slick.Image;
import utils.Tools;

/*
 * Copyright (C) 2012 rosendorsc
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 */
/**
 * @author rosendorsc
 */
public final class JPDF {

    private PDDocument pdf;
    private java.awt.Image imagenPDF;
    private FileInputStream fis;
    private File file;
    private int pagenum;
    private int totalPages;
    private List<PDPage> pageList;
    

    public JPDF() {
    }

    public JPDF(String filePath) {


        loadPDF(filePath);

    }
    
    /**
     * Carga el archivo PDF.
     *
     * @param filePath Ruta del archivo PDF.
     *
     * @return ArrayList con paginas en Image.
     */
    private void loadPDF(String filePath) {
        try {
            file = new File(filePath);
            fis = new FileInputStream(file);
            FileChannel fc = fis.getChannel();
            ByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            System.err.println("Buff:" + bb.toString());
            pdf = PDDocument.load(fis);
            totalPages = pdf.getNumberOfPages();
            pageList=pdf.getDocumentCatalog().getAllPages();
        } catch (Exception ex) {
            Logger.getLogger(JPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Regresa un Image de la pagina indicada.
     *
     * @param numPage Numero de pagina.
     * @return Pagina en Image.
     * @see Image
     */
    public Image getPage(int numPage) {
        Image rtrnImage = null;
        try {
            PDPage page = pageList.get(numPage);
            
            imagenPDF = page.convertToImage(BufferedImage.TYPE_INT_RGB,72/2);
            rtrnImage = Tools.toSlickImage(imagenPDF);
            rtrnImage.setName("Page:" + numPage);
        } catch (Exception e) {

            System.err.println(e.getMessage());
            return rtrnImage;

        }

        return rtrnImage;

    }

    /**
     * Regresa un arreglo con paginas del archivo PDF.
     *
     * @return ArrayList con paginas en Image.
     * @see ArrayList
     * @see Image
     */
    public ArrayList<Image> getAllPages() {

        ArrayList<Image> pages = new ArrayList<Image>(this.getTotalPages());




        for (int i = 0; i < this.getTotalPages(); i++) {
            Image image = getPage(i + 1);

            pages.add(image);

        }
        return pages;



    }

    /**
     * Regresa el total de paginas del archivo PDF.
     *
     * @return the totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }
}
