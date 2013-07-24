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
package utils;

import com.google.gson.Gson;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.*;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.geom.*;

public class Tools {

    private static int rgb;
    private static final int DEFAULT_IMAGE_TYPE = BufferedImage.TYPE_INT_RGB;

    /**
     * Convierte java.awt.Image a java.awt.image.BufferedImage
     *
     * @param image Imagen a convertir
     * @return Imagen en BufferedImage
     * @see BufferedImage
     * @see Image
     */
    public static BufferedImage toBufferImage(Image image) {
        return bufferImage(image, DEFAULT_IMAGE_TYPE);
    }

    /**
     *
     * Convierte java.awt.Image a java.awt.image.BufferedImage
     *
     * @param image Imagen a convertir
     * @param type Tipo de imagen
     * @see Image
     * @see BufferedImage
     */
    private static BufferedImage bufferImage(Image image, int type) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(image, null, null);
        // waitForImage(bufferedImage);
        return bufferedImage;
    }

    /**
     * Convierte java.awt.Image a org.newdawn.slick.Image
     *
     * @param image Imagen a convertir
     * @return Imagen convertida
     * @see org.newdawn.slick.Image
     * @see Image
     */
    public static org.newdawn.slick.Image toSlickImage(java.awt.Image image) {
        BufferedImage b = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        b.createGraphics().drawImage(image, 0, 0, null);
        ImageBuffer buf = new ImageBuffer(b.getWidth(), b.getHeight());
        int x, y;
        for (y = 0; y < b.getHeight(); y++) {
            for (x = 0; x < b.getWidth(); x++) {
                rgb = b.getRGB(x, y);
                buf.setRGBA(x, y, (rgb >> 16) & 0xff, (rgb >> 8) & 0xff, rgb & 0xff, (rgb >> 24) & 0xff);
            }
        }
        return buf.getImage();
    }

    /**
     * Convierte BufferedImage a java.awt.Image
     *
     * @param image Imagen a convertir
     * @return Imagen convertida
     * @see Image
     * @see BufferedImage
     */
    public static Image toImage(BufferedImage image) {
        return Toolkit.getDefaultToolkit().createImage(image.getSource());
    }

    /**
     * Convierte java.awt.Image a org.newdawn.slick.Image
     *
     * @param image Imagen a convertir
     * @return Imagen convertida
     * @see org.newdawn.slick.Image
     * @see Image
     */
    public static org.newdawn.slick.Image toSlickImageBufferImage(BufferedImage image) {
        BufferedImage b = image;
        ImageBuffer buf = new ImageBuffer(b.getWidth(), b.getHeight());
        int x, y;
        for (y = 0; y < b.getHeight(); y++) {
            for (x = 0; x < b.getWidth(); x++) {
                rgb = b.getRGB(x, y);
                buf.setRGBA(x, y, (rgb >> 16) & 0xff, (rgb >> 8) & 0xff, rgb & 0xff, (rgb >> 24) & 0xff);
            }
        }
        return buf.getImage();
    }

    /**
     * Creates a
     * <code>Polygon</code> from the parameters passed in, rotating and scaling
     * accordingly. Rotation origin is the centre of the bounds of
     * <code>position</code> &
     * <code>size</code>.
     */
    public static Polygon toPolygon(float points[], Vector2f position, Size size, float rotation, float scale) {
        Polygon polygon = new Polygon(points);
        Vector2f centre = new Vector2f(position.x + size.getWidth() / 2.0f, position.y + size.getHeight() / 2.0f);
        Double rotationd = new Double(Math.toRadians(rotation));
        Transform rotateTransform = Transform.createRotateTransform(rotationd.floatValue(), centre.x, centre.y);
        Transform scaleTransform = Transform.createScaleTransform(scale, scale);
        polygon = (Polygon) polygon.transform(rotateTransform);
        polygon = (Polygon) polygon.transform(scaleTransform);
        return polygon;
    }

    /*
     * 
     * for (i=0; i<polySides; i++) {
     if ((polyY[i]< y && polyY[j]>=y
     ||   polyY[j]< y && polyY[i]>=y)
     &&  (polyX[i]<=x || polyX[j]<=x)) {
     oddNodes^=(polyX[i]+(y-polyY[i])/(polyY[j]-polyY[i])*(polyX[j]-polyX[i])<x); }
     j=i; }

     return oddNodes; }
     * 
     */
    public static boolean pointInPolygon(ArrayList<Vector2f> points, Vector2f toCompare) {
        int j = 4 - 1;
        boolean oddNodes = false;
        for (int i = 0; i < points.size(); i++) {
            if ((points.get(i).y < toCompare.y && points.get(j).y >= toCompare.y
                    || points.get(j).y < toCompare.y && points.get(i).y >= toCompare.y)
                    && (points.get(i).x <= toCompare.x || points.get(j).x <= toCompare.x)) {

                oddNodes ^= (points.get(i).x + (toCompare.y - points.get(i).y) / (points.get(j).y - points.get(i).y) * (points.get(j).x - points.get(i).x) < toCompare.x);
                if (oddNodes) {
                    return true;
                }
            }

        }

        return oddNodes;
    }

    /**
     * Adds the specified path to the java library path
     *     
* @param pathToAdd the path to add
     * @throws Exception
     */
    public static void addLibraryPath(String pathToAdd) throws Exception {
        final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
        usrPathsField.setAccessible(true);

        //get array of paths
        final String[] paths = (String[]) usrPathsField.get(null);

        //check if the path to add is already present
        for (String path : paths) {
            if (path.equals(pathToAdd)) {
                return;
            }
        }

        //add the new path
        final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
        newPaths[newPaths.length - 1] = pathToAdd;
        usrPathsField.set(null, newPaths);
    }

    /**
     * Unzip file
     *
     * @param zipFile input zip file
     * @param output zip file output folder
     */
    public static void unZipIt(String zipFile, String outputFolder) throws IOException {

        System.out.println(zipFile);
        int BUFFER = 2048;
        File file = new File(zipFile);

        ZipFile zip = new ZipFile(file);
        String newPath = outputFolder;

        new File(newPath).mkdir();
        Enumeration zipFileEntries = zip.entries();

        // Process each entry
        while (zipFileEntries.hasMoreElements()) {
            // grab a zip file entry
            ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
            String currentEntry = entry.getName();
            File destFile = new File(newPath, currentEntry);
            //destFile = new File(newPath, destFile.getName());
            File destinationParent = destFile.getParentFile();

            // create the parent directory structure if needed
            destinationParent.mkdirs();

            if (!entry.isDirectory() && !destFile.isHidden() && destFile.canRead()) {
                BufferedInputStream is = new BufferedInputStream(zip
                        .getInputStream(entry));
                int currentByte;
                // establish buffer for writing file
                byte data[] = new byte[BUFFER];

                // write the current file to disk
                FileOutputStream fos = new FileOutputStream(destFile);
                BufferedOutputStream dest = new BufferedOutputStream(fos,
                        BUFFER);

                // read and write until last byte is encountered
                while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, currentByte);
                }
                dest.flush();
                dest.close();
                is.close();
            }
        }

    }

    /**
     * Delete a file
     *
     * @param fileToDelete path of file to delete
     */
    public static void deleteFile(String fileToDelete) {

        File file = new File(fileToDelete);

        if (file.delete()) {
            System.out.println(file.getName() + " is deleted!");
        } else {
            System.out.println("Delete operation is failed.");
        }




    }

    /**
     * Copy a file
     *
     * @param fileToCopy path of file to copy
     * @param pathToCopy path to copy file
     * @param pathToPaste path to paste file
     *
     */
    public static void copyFile(String fileToCopy, String pathToCopy, String pathToPaste) {

        InputStream inStream;
        OutputStream outStream;

        try {

            File afile = new File(pathToCopy + fileToCopy);
            File bfile = new File(pathToPaste + pathToCopy);

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        } catch (IOException e) {
            System.err.println("Error:[" + e.getMessage() + "]");
        }






    }

    public static void deleteFolder(String filePath)
            throws IOException {
        File file = new File(filePath);

        //make sure directory exists
        if (!file.exists()) {

            System.out.println("Directory does not exist.");

        } else {
            if (file.isDirectory()) {

                //directory is empty, then delete it
                if (file.list().length == 0) {

                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());

                } else {

                    //list all the directory contents
                    String files[] = file.list();

                    for (String temp : files) {
                        //construct the file structure
                        File fileDelete = new File(file, temp);

                        //recursive delete
                        deleteFolder(fileDelete.getPath());
                    }

                    //check the directory again, if empty then delete it
                    if (file.list().length == 0) {
                        file.delete();
                        System.out.println("Directory is deleted : "
                                + file.getAbsolutePath());
                    }
                }

            } else {
                //if file, then delete it
                file.delete();
                System.out.println("File is deleted : " + file.getAbsolutePath());
            }
        }
    }

    private static void copyFolder(File src, File dest)
            throws IOException {

        if (src.isDirectory()) {

            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
                System.out.println("Directory copied from "
                        + src + "  to " + dest);
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile, destFile);
            }

        } else {
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes 
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            System.out.println("File copied from " + src + " to " + dest);
        }
    }

    /**
     * Copy a Folder
     *
     * @param pathToCopy path of folder
     * @param pathPaste path to paste the folder
     */
    public static void copyFolder(String pathToCopy, String pathToPaste) {
        File srcFolder = new File(pathToCopy);
        File destFolder = new File(pathToPaste);

        //make sure source exists
        if (!srcFolder.exists()) {

            System.out.println("Directory does not exist.");

        } else {

            try {
                copyFolder(srcFolder, destFolder);
            } catch (IOException e) {
                System.err.println("Error:[" + e.getMessage() + "]");
            }

            System.out.println("Done");

        }

    }

    /**
     * Return Object of JSON File.
     *
     * @param classToReturn return object class
     * @param filePath path of file .json
     */
    public static Object objectFromJsonFile(String filePath, Class classToReturn) throws FileNotFoundException {

        Gson gson = new Gson();
        BufferedReader br = null;

        br = new BufferedReader(
                new FileReader(filePath));

        //convert the json string back to object
        Object obj = gson.fromJson(br, classToReturn);
        System.out.println("Generado");

        return obj;

    }
    private static final int BUFFER_SIZE = 4096;

    private static void extractFile(ZipInputStream in, File outdir, String name) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outdir, name)));
        int count = -1;
        while ((count = in.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }
        out.close();
    }

    private static void mkdirs(File outdir, String path) {
        File d = new File(outdir, path);
        if (!d.exists()) {
            d.mkdirs();
        }
    }

    private static String dirpart(String name) {
        int s = name.lastIndexOf(File.separatorChar);
        return s == -1 ? null : name.substring(0, s);
    }

    /**
     * *
     * Extract zipfile to outdir with complete directory structure
     *
     * @param zipfile Input .zip file
     * @param outdir Output directory
     */
    public static boolean extract(File zipfile, String outdirPath) {
        try {
            File outdir = new File(outdirPath);
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipfile));
            ZipEntry entry;
            String name, dir;
            int numberOfValidDirectorys = 0;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                if (entry.isDirectory()) {

                    mkdirs(outdir, name);
                    continue;
                }
                /* this part is necessary because file entry can come before
                 * directory entry where is file located
                 * i.e.:
                 *   /foo/foo.txt
                 *   /foo/
                 */
                dir = dirpart(name);
                if (dir != null) {
                    mkdirs(outdir, dir);
                }

                extractFile(zin, outdir, name);
            }


            zin.close();
        } catch (IOException e) {
            return false;
        }


        return true;
    }

    public static boolean extractedFolderIsValid(File folderPath) {


        String[] folderValids = {"activities", "lib", "resources"};
        String[] filesValids = {"leeme.txt", "logo_large.png", "logo_small.png", folderPath.getName() + ".jar", folderPath.getName() + ".jar"};
        File[] filesToCompare = folderPath.listFiles();
        int numberOfValidFolders = 0;
        int numberOfValidFiles = 0;
        for (int i = 0; i < filesToCompare.length; i++) {
            File fileToComapre = filesToCompare[i];

            if (fileToComapre.isDirectory() && !fileToComapre.isHidden()) {
                for (int j = 0; j < folderValids.length; j++) {
                    String folder = folderValids[j];
                    if (fileToComapre.getName().equals(folder)) {
                        numberOfValidFolders++;
                    }

                }
            } else {
                if (!fileToComapre.isHidden()) {
                    for (int j = 0; j < filesValids.length; j++) {
                        String folder = filesValids[j];
                        if (fileToComapre.getName().equals(folder)) {
                            numberOfValidFiles++;
                        }

                    }
                }

            }
        }

        if (numberOfValidFiles == filesValids.length && numberOfValidFolders == folderValids.length) {


            return true;
        }




        return false;

    }
}