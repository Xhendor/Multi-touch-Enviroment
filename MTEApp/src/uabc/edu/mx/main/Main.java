
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
package uabc.edu.mx.main;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import uabc.edu.mx.mtenv.MultiTouchEnvironment;
import org.newdawn.slick.opengl.renderer.Renderer;
import uabc.edu.mx.data.session.User;
import uabc.edu.mx.helper.Helper;
import uabc.edu.mx.message.listener.MessageListener;
import uabc.edu.mx.message.Message;
import uabc.edu.mx.message.MessageType;
import static utils.Tools.*;

/**
 *
 * @author xhendor
 */
public class Main implements MessageListener {

    private MultiTouchEnvironment mtEnv;
    private InetAddress IP;
    private Thread process;

    public Main() {
        Helper.INSTANCE.getMessageProcessor().registerListener((MessageListener) this, this.toString());
       //Helper.INSTANCE.getMessageProcessor().notifyMessage("148.231.90.242", this, this, MessageType.MESSAGE_XMPP_REQUEST_SERVER);
        Helper.INSTANCE.getMessageProcessor().notifyMessage("192.168.200.102", this.toString(), this.toString(), MessageType.MESSAGE_XMPP_REQUEST_SERVER);
        Helper.INSTANCE.getMessageProcessor().notifyMessage(new User("mesa1@edumat", "edumat"), this, this, MessageType.MESSAGE_REQUEST_LOGIN);
        Message msg = new Message("", "", "", MessageType.MAIN_APP_BEGIN);
        this.messageReciever(msg);
    }

    public static void main(String[] args) {
        try {
            addLibraryPath("./lib");
            Renderer.setRenderer(Renderer.VERTEX_ARRAY_RENDERER);
            Main main = new Main();
            createAndShowGUI();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void messageReciever(Message msg) {
        System.out.println("MessageIn");
        switch (msg.getType()) {
            case MESSAGE_NOT_FOUND_TUIO_SERVER:
                
                break;
                
            case MESSAGE_SEVER_NOT_FOUND:
                JOptionPane.showMessageDialog(null,msg.getBody());       
                System.exit(0);
                break;    
            case MESSAGE_LOGIN_ERROR:
                System.out.println("No loggeado");
                JOptionPane.showMessageDialog(null,"No se encontro servidor Jabber");
                break;
            case MESSAGE_LOGIN_OK:
          System.out.println("Loggeado, Usuarios Conectados:"+(Helper.INSTANCE.getActiveAccountsManager().getNumberOfActiveUsers()+1));

                break;
            case MAIN_APP_BEGIN:
                try {
                    IP = InetAddress.getLocalHost();

                    mtEnv = new MultiTouchEnvironment(IP.getHostAddress());
                } catch (UnknownHostException ex) {
                    System.out.println("Error:" + ex);
                }
                process = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mtEnv.begin();
                        } catch (Exception e) {
                        }

                    }
                });
                process.start();
      
                break;
            case MAIN_APP_STOP:
                process = null;
                mtEnv = null;
                break;

        }

    }

    private static void createAndShowGUI() {
        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("/images/mticon.png", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a popup menu components
        MenuItem aboutItem = new MenuItem("About");

        trayIcon.setImageAutoSize(true);

        MenuItem exitItem = new MenuItem("Exit");
        //Add components to popup menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(exitItem);
         popup.addSeparator();

        trayIcon.setPopupMenu(popup);
        trayIcon.setToolTip("Multi-Touch Environment");

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This dialog box is run from System Tray");
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
                JLabel label = new JLabel();
                panel.add(new JLabel(new ImageIcon(createImage("/images/uabc_logo.png", ""))));
                panel.add(new JLabel(new ImageIcon(createImage("/images/edumat_logo.png", ""))));
                panel.add(new JLabel(new ImageIcon(createImage("/images/remiam_logo.png", ""))));


                JOptionPane.showMessageDialog(null, panel, "About", JOptionPane.PLAIN_MESSAGE);
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
    }

    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = Main.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName(); //To change body of generated methods, choose Tools | Templates.
    }
}
