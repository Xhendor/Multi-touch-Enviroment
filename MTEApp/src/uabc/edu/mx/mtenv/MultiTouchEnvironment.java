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
package uabc.edu.mx.mtenv;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import uabc.edu.mx.helper.Helper;
import uabc.edu.mx.message.listener.MessageListener;
import uabc.edu.mx.message.Message;
import uabc.edu.mx.message.MessageType;
import uabc.edu.mx.world.TouchWorld;
import utils.Constants;

/**
 *
 * @author xhendor
 */
public class MultiTouchEnvironment extends BasicGame implements MessageListener {

    private AppGameContainer app;
    private TouchWorld touchWorld;
    private GameContainer gc;

    public MultiTouchEnvironment(String title) {
        super(title);
        Helper.INSTANCE.getMessageProcessor().registerListener((MessageListener) this, MultiTouchEnvironment.class.getName());

    }

    public void begin() {
        try {
            app = new AppGameContainer(this);
            app.setDisplayMode(Constants.WIDTH, Constants.HEIGHT, Constants.FULLSCREEN);
            app.setAlwaysRender(true);
           
            app.setUpdateOnlyWhenVisible(true);
            app.start();

        } catch (SlickException ex) {
            Logger.getLogger(MultiTouchEnvironment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        touchWorld = new TouchWorld();
        this.gc = gc;
        gc.setShowFPS(false);
        gc.setVSync(true);

        gc.setMaximumLogicUpdateInterval(900);
        Helper.INSTANCE.getMessageProcessor().setTouchWorld(touchWorld);
        Helper.INSTANCE.getMessageProcessor().notifyMessage("CONNECT_TUIO", this, this, MessageType.MESSAGE_REQUEST_TUIO_SERVER);    

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if (touchWorld != null) {
            touchWorld.update(gc, i);

        }
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            gc.setFullscreen(!gc.isFullscreen());
        }

    }

    @Override
    public void render(GameContainer gc, Graphics gr) throws SlickException {
        gr.setAntiAlias(true);

        if (touchWorld != null) {
            touchWorld.render(gc, gr);
        
        }
        
        
       

    }

    @Override
    public void messageReciever(Message msg) {

        switch (msg.getType()) {
            case MAIN_APP_STOP:

                //gc.exit();
                break;
            case MESSAGE_CONNECTED_TUIO_SERVER:

                System.out.println(msg.getBody());
                break;
            case MESSAGE_NOT_FOUND_TUIO_SERVER:
                JOptionPane.showMessageDialog(null,msg.getBody());       
                System.exit(0);
                break;
        }

    }

    @Override
    public final String toString() {
        return this.getClass().getName();
    }
}
