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
package uabc.edu.mx.apploader;


/*
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
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import uabc.edu.mx.data.multitouch.app.ApplicationMT;
import static utils.OSValidator.*;
import utils.Tools;
/**
 *
 * @author Rosendo R. Sosa.
 */
public class AppLoader {

    private boolean isValid = false;
    private boolean isOpen = false;
    private JarFile jarFile;
    private URL[] urls;
    private URLClassLoader cl;
    private int countOfApps = 0;
    private int limitOfApps = 1;
    private String appName;

    public boolean openJar(String nameJar) throws Exception {
        String pathToJar = null;

        if (isWindows()) {
            pathToJar = "/Users/" + System.getProperty("user.name")
                    + "/SparkleShare/multitouch apps/Applications/" + nameJar;
            System.out.printf("Sistema operativo [%s]  host compatible..", OS);

        } else if (isMac()) {

            pathToJar = "/Users/" + System.getProperty("user.name")
                    + "/SparkleShare/multitouch apps/Applications/" + nameJar;

            System.out.printf("Sistema operativo [%s] host compatible..", OS);

        } else if (isUnix()) {
            System.err.printf("Sistema operativo [%s] host no compatible actualmente..", OS);
            return false;
        } else if (isSolaris()) {
            System.err.printf("Sistema operativo [%s] host no compatible actualmente..", OS);

            return false;
        } else {
            System.err.printf("Sistema operativo [%s] host no compatible actualmente..", OS);
            return false;
        }


        if (pathToJar != null) {
            try {
                
                jarFile = new JarFile(pathToJar);

                urls = new URL[1];
                try {
                    URL url = new URL("jar:file:" + pathToJar + "!/");
                    urls[0] = url;
                } catch (MalformedURLException ex) {
                    return false;
                }

            } catch (IOException ex) {
                System.out.println("Error:[" + ex.getMessage() + "]");
                isOpen=false;
                return false;
            }

            isOpen = true;
            return true;
        } else {

            return false;
        }
    }

    public boolean isValid() {

        isValid = validation();
         File file=new File("/Users/" + System.getProperty("user.name")
                    + "/SparkleShare/multitouch apps/Applications/TestApp");
       
boolean validooo= Tools.extractedFolderIsValid(file);
        System.out.println("DirectorioValido:"+validooo); 
        if (countOfApps > limitOfApps) {
            System.err.println("Solo se permite una Aplicación por Jar");
            isValid = false;
            return false;
        }
        if (countOfApps == 0) {
            System.err.println("El paquete no contiene ninguna Aplicación MT.");
            isValid = false;
            return isValid;
        }
        return isValid;



    }

    private boolean validation() {
        
        if(isOpen){
        countOfApps = 0;
        Enumeration e = jarFile.entries();
        cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = (JarEntry) e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            String className = je.getName().substring(0, je.getName().length() - 6);
            className = className.replace('/', '.');
            try 
            {
                System.out.println("Clase:" +className);
                Class c = cl.loadClass(className);
                Class superClass = c.getSuperclass();
                if (superClass != null) {
                    if (superClass == ApplicationMT.class) {
                        System.err.printf("\nHave a AppMT:[%s]", c.toString());
                        appName = c.getName();
                        countOfApps++;

                    }
                }

            } catch (ClassNotFoundException ex) {
                System.out.println("Error:[" + ex.getMessage() + "]");
                return false;
            }

        }
        }else{
        return false;
        }

        return true;

    }

    public ApplicationMT getInstance() {
        if (isOpen && isValid) {
            try {
                Class classApp = cl.loadClass(appName);

                Constructor[] ctors = classApp.getDeclaredConstructors();
	Constructor ctor = null;
	for (int i = 0; i < ctors.length; i++) {
	    ctor = ctors[i];
	    if (ctor.getGenericParameterTypes().length == 1)
		break;
	}

                
                
                return (ApplicationMT) ctor.newInstance(appName);

            } catch (ClassNotFoundException ex) {
                System.out.println("Error:[" + ex.getMessage() + "]");
                return null;
            } catch (InstantiationException ex) {
                System.out.println("Error:[" + ex.getMessage() + "]");

                return null;
            } catch (IllegalAccessException ex) {
                System.out.println("Error:[" + ex.getMessage() + "]");

                return null;
            }  catch (IllegalArgumentException ex) {
                Logger.getLogger(AppLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(AppLoader.class.getName()).log(Level.SEVERE, null, ex);
            }




        }
        return null;

    }

    public static void main(String[] args) throws Exception {
        AppLoader cosxa = new AppLoader();
        cosxa.openJar("TestApp/TestApp.jar");
        cosxa.isValid();
        ApplicationMT opop = cosxa.getInstance();
        if (opop == null) {
            System.out.println(":O magias");
        }
       
       opop.init();
    }
}

    
    
    

