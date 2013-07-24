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
package uabc.edu.mx.data.session;

import java.awt.Image;
import java.util.Map;
import uabc.edu.mx.jabber.JabberSession;

/**
 *
 * @author xhendor
 */
public class User {

    private String uName;
    private String uPassword;
    private Image uImage;
    private String uFBName;
    private String uTwitterName;
    private Map<String, String> uAttributes;
    
    private JabberSession jSession;

    public User(String uName, String uPassword) {
        this.uName = uName;
        this.uPassword = uPassword;
    }

    public User(String uName, String uPassword, Map<String, String> uAttributes) {
        this.uName = uName;
        this.uPassword = uPassword;
        this.uAttributes = uAttributes;
    }

    /**
     * @return the uName
     */
    public String getuName() {
        return uName;
    }

    /**
     * @param uName the uName to set
     */
    public void setuName(String uName) {
        this.uName = uName;
    }

    /**
     * @return the uPassword
     */
    public String getuPassword() {
        return uPassword;
    }

    /**
     * @param uPassword the uPassword to set
     */
    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    /**
     * @return the uImage
     */
    public Image getuImage() {
        return uImage;
    }

    /**
     * @param uImage the uImage to set
     */
    public void setuImage(Image uImage) {
        this.uImage = uImage;
    }

    /**
     * @return the uFBName
     */
    public String getuFBName() {
        return uFBName;
    }

    /**
     * @param uFBName the uFBName to set
     */
    public void setuFBName(String uFBName) {
        this.uFBName = uFBName;
    }

    /**
     * @return the uTwitterName
     */
    public String getuTwitterName() {
        return uTwitterName;
    }

    /**
     * @param uTwitterName the uTwitterName to set
     */
    public void setuTwitterName(String uTwitterName) {
        this.uTwitterName = uTwitterName;
    }

    /**
     * @return the uAttributes
     */
    public Map<String, String> getuAttributes() {
        return uAttributes;
    }

    /**
     * @param uAttributes the uAttributes to set
     */
    public void setuAttributes(Map<String, String> uAttributes) {
        this.uAttributes = uAttributes;
    }

    /**
     * @return the jSession
     */
    public JabberSession getjSession() {
        return jSession;
    }

    /**
     * @param jSession the jSession to set
     */
    public void setjSession(JabberSession jSession) {
        this.jSession = jSession;
    }
}
