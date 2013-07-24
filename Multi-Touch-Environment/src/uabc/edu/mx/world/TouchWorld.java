package uabc.edu.mx.world;
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

import com.google.gson.Gson;
import java.awt.Font;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import uabc.edu.mx.render.RenderObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.ResourceLoader;
import uabc.edu.mx.container.Container;
import uabc.edu.mx.container.UserContainer;
import uabc.edu.mx.data.JPDF;
import uabc.edu.mx.data.JSVG;
import uabc.edu.mx.data.session.SessionData;
import uabc.edu.mx.data.session.User;
import uabc.edu.mx.entities.ImageRenderEntity;
import uabc.edu.mx.entities.PDFRenderEntity;
import uabc.edu.mx.entities.PointerEntity;
import uabc.edu.mx.entities.SVGRenderEntity;
import uabc.edu.mx.helper.Helper;
import uabc.edu.mx.message.listener.MessageListener;
import uabc.edu.mx.message.Message;
import uabc.edu.mx.message.MessageType;
import uabc.edu.mx.data.multitouch.app.AppState;
import uabc.edu.mx.data.multitouch.app.ApplicationMT;
import uabc.edu.mx.jabber.user.Participant;
import uabc.edu.mx.tuio.TuioCursor;
import utils.Chronometer;
import utils.Constants;
import utils.OrderArray;
import utils.Size;

/**
 * TouchWorld is the main loop of the Multi-Touch Environment.
 *
 * @author Rosendo R. Sosa Canales
 */
public class TouchWorld implements RenderObject, MessageListener {

    private ApplicationMT sessionApp;
    private ApplicationMT appToLaunch;
    private final OrderArray containers;
    private final HashMap<TuioCursor, PointerEntity> entity;
    private final HashMap<Long, TuioCursor> cursorList;
    private int order;
    private Container onTop;
    private PointerEntity toRemove;
    private Rectangle rectangle;
    private boolean CREATE_IMAGE_FLAG;
    private boolean CREATE_PDF_FLAG;
    private boolean CREATE_SVG_FLAG;
    private ImageRenderEntity imageRender;
    private String path_image;
    private String path_pdf;
    private String path_svg;
    private Object currentSender;
    private PDFRenderEntity pdfRender;
    private SVGRenderEntity svgRender;
    private boolean USER_CONTAINERS;
    private final Ellipse corner1;
    private final Ellipse corner2;
    private final Ellipse corner3;
    private final Ellipse corner4;
    private float rotate;
    private TrueTypeFont font2;
    private boolean mainMenu = true;
    private RoundedRectangle mainMenuRoundRect;
    private RoundedRectangle bttn1;
    private RoundedRectangle bttn2;
    private RoundedRectangle bttn3;
    private RoundedRectangle flip90;
    private RoundedRectangle flip180;
    private RoundedRectangle flip0;
    private RoundedRectangle flipMinus90;
    private RoundedRectangle bttn4;
    private float angle = 0;
    private final Chronometer chrono;
    private boolean modoActividad;
    private boolean modoLibre;
    private Gson gson;
    private String data_json;
    private String[] iconsURLs;

    public TouchWorld() throws SlickException {
        this.containers = new OrderArray();
        this.entity = new HashMap<TuioCursor, PointerEntity>();
        this.cursorList = new HashMap<Long, TuioCursor>();
        corner1 = new Ellipse(0, 0, 60, 60);
        corner2 = new Ellipse(0, 768, 60, 60);
        corner3 = new Ellipse(1024, 768, 60, 60);
        corner4 = new Ellipse(1024, 0, 60, 60);
        mainMenuRoundRect = new RoundedRectangle(Constants.WIDTH / 2 - 500 / 2, Constants.HEIGHT / 2 - 400 / 2, 500, 338, 10);
        bttn1 = new RoundedRectangle(mainMenuRoundRect.getX() + (5), mainMenuRoundRect.getY() + (5), 490, 80, 10);
        bttn2 = new RoundedRectangle(mainMenuRoundRect.getX() + (5), mainMenuRoundRect.getY() + (82 * 1), 490, 80, 10);
        bttn3 = new RoundedRectangle(mainMenuRoundRect.getX() + (5), mainMenuRoundRect.getY() + (82 * 2), 490, 80, 10);
        bttn4 = new RoundedRectangle(mainMenuRoundRect.getX() + (5), mainMenuRoundRect.getY() + (82 * 3), 490, 80, 10);
        flip90 = new RoundedRectangle(mainMenuRoundRect.getX() - 60, mainMenuRoundRect.getY() + 10, 200, 280, 10);
        flip180 = new RoundedRectangle(mainMenuRoundRect.getX() + 500 - 140, mainMenuRoundRect.getY() + 10, 200, 280, 10);
        flip0 = new RoundedRectangle(rotate, rotate, rotate, rotate, rotate);
        flipMinus90 = new RoundedRectangle(rotate, rotate, rotate, rotate, rotate);
        chrono = new Chronometer();
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("fonts/doc.ttf");

            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(30f); // set font size
            font2 = new TrueTypeFont(awtFont2, true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Helper.INSTANCE.getMessageProcessor().registerListener(this, this.getClass().getName());
    }

    @Override
    public void render(GameContainer gc, Graphics gr) {
        gr.setColor(Color.gray);
        gr.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        gr.setColor(Color.blue);
        gr.fill(corner1);
        gr.setColor(Color.black);

        gr.draw(corner1);
        gr.rotate(20, 0, 90);
        font2.drawString(35, -18, "1", Color.black);
        gr.resetTransform();
        gr.setColor(Color.green);
        gr.fill(corner2);
        gr.setColor(Color.black);
        gr.draw(corner2);
        font2.drawString(15, Constants.HEIGHT - 35, "2", Color.black);
        gr.setColor(Color.yellow);
        gr.fill(corner3);
        gr.setColor(Color.black);
        gr.draw(corner3);
        gr.rotate(Constants.WIDTH - 16, Constants.HEIGHT - 16, -90);
        font2.drawString(Constants.WIDTH - 16, Constants.HEIGHT - 45, "3", Color.black);
        gr.resetTransform();
        gr.setColor(Color.red);
        gr.fill(corner4);
        gr.setColor(Color.black);
        gr.draw(corner4);
        gr.rotate(Constants.WIDTH - 16, 16, -180);
        font2.drawString(Constants.WIDTH - 20, 3, "4", Color.black);
        gr.resetTransform();
        gr.setColor(Color.gray);
        if (sessionApp != null) {
            sessionApp.render(gc, gr);
        }
        if (appToLaunch != null) {
            appToLaunch.render(gc, gr);
        }
        if (!containers.isEmpty()) {
            synchronized (containers) {
                for (Container container : containers) {
                    if (container instanceof UserContainer) {
                    }
                    container.render(gc, gr);
                }
            }

        }
        if (modoActividad && appToLaunch == null) {
            gr.setColor(Color.white);
            gr.fill(bttn1);
            gr.setColor(Color.black);
            font2.drawString(bttn1.getCenterX() - font2.getWidth("Modo Actividad") / 2, bttn1.getCenterY() - font2.getHeight() / 2, "Modo Actividad", Color.black);

        }
        if (modoLibre) {
        }
        if (mainMenu) {


            gr.setColor(Color.lightGray);
            gr.fill(mainMenuRoundRect);
            gr.setColor(Color.black);
            gr.draw(mainMenuRoundRect);
            gr.setColor(Color.lightGray);
            gr.fill(bttn1);
            gr.setColor(Color.black);
            gr.draw(bttn1);
            gr.setColor(Color.lightGray);
            gr.fill(bttn2);
            gr.setColor(Color.black);
            gr.draw(bttn2);
            gr.setColor(Color.lightGray);
            gr.fill(bttn3);
            gr.setColor(Color.black);
            gr.draw(bttn3);
            gr.setColor(Color.lightGray);
            gr.fill(bttn4);
            gr.setColor(Color.black);
            gr.draw(bttn4);
            font2.drawString(bttn1.getCenterX() - font2.getWidth("Cargar Aplicacion") / 2, bttn1.getCenterY() - font2.getHeight() / 2, "Cargar Aplicacion", Color.blue);
            font2.drawString(bttn3.getCenterX() - font2.getWidth("Modo Actividad") / 2, bttn3.getCenterY() - font2.getHeight() / 2, "Modo Actividad", Color.blue);
            font2.drawString(bttn4.getCenterX() - font2.getWidth("Salir") / 2, bttn4.getCenterY() - font2.getHeight() / 2, "Salir", Color.blue);
            font2.drawString(bttn2.getCenterX() - font2.getWidth("Modo Libre") / 2, bttn2.getCenterY() - font2.getHeight() / 2, "Modo Libre", Color.blue);




        }


        if (iconsURLs != null) {
            for (int i = 0; i < iconsURLs.length; i++) {
                try {

                    Image imagen = new Image("/Users/" + System.getProperty("user.name")
                            + "/SparkleShare/multitouch apps/Applications/" + iconsURLs[i] + "/logo_large.png");
                    gr.drawImage(imagen, Constants.WIDTH / 2 + (60 * i), Constants.HEIGHT / 2);
                } catch (SlickException ex) {
                    Logger.getLogger(TouchWorld.class.getName()).log(Level.SEVERE, null, ex);
                }

            }


        }
        synchronized (entity) {

            for (Map.Entry<TuioCursor, PointerEntity> entry : entity.entrySet()) {
                PointerEntity pointerEntity = entry.getValue();
                pointerEntity.render(gc, gr);

            }

        }


        if (rectangle != null) {
            gr.draw(rectangle);
        }
    }
    private Object currentSender_pdf;
    private Object currentSender_svg;

    @Override
    public void update(GameContainer gc, int delta) {

        if (!containers.isEmpty()) {

            synchronized (containers) {
                for (Container container : containers) {
                    container.update(gc, delta);
                }
            }

        }

        if (sessionApp != null) {
            sessionApp.update(gc, delta);
        }
        if (appToLaunch != null) {
            appToLaunch.update(gc, delta);
        }




        if (CREATE_IMAGE_FLAG) {
            drawImage();
        }
        if (CREATE_SVG_FLAG) {
            drawSVG();
        }
        if (CREATE_PDF_FLAG) {
            drawPDF();
        }
        drawUserContainers();

        synchronized (entity) {
            for (Map.Entry<TuioCursor, PointerEntity> entry : entity.entrySet()) {
                PointerEntity pointerEntity = entry.getValue();
                pointerEntity.update(gc, delta);

            }
        }

        if (gc.getInput().isKeyPressed(Input.KEY_K)) {

            rotate--;
            System.err.println(rotate);
        }
    }

    @Override
    public void messageReciever(Message msg) {
        TuioCursor tcur;
        switch (msg.getType()) {

            case MESSAGE_START_APP_TO_SHOW:
                if (modoActividad) {
                    if (appToLaunch != null) {
                        SessionData data_start = (SessionData) msg.getBody();

                        appToLaunch.start();
                        synchronized (containers) {
                            for (Container container : containers) {
                                if (container instanceof UserContainer) {
                                    UserContainer uc = (UserContainer) container;
                                    uc.setState(AppState.START);
                                }
                            }
                        }

                        gson = new Gson();
                        data_json = gson.toJson(data_start);
                        Helper.INSTANCE.getActiveAccountsManager().getMultiTouchTables().get(0).getjSession().sendMessage(data_json, data_start.getCurrentModerator());
                    }
                    System.out.println("START_APP");
                }
                break;
            case MESSAGE_STOP_APP_TO_SHOW:
                SessionData data_stop = (SessionData) msg.getBody();

                if (modoActividad) {
                    if (appToLaunch != null) {
                        containers.clear();
                    }
                    gson = new Gson();
                    data_json = gson.toJson(data_stop);
                        Helper.INSTANCE.getActiveAccountsManager().getMultiTouchTables().get(0).getjSession().sendMessage(data_json, data_stop.getCurrentModerator());
                    appToLaunch = null;
                    mainMenu = true;
                    modoActividad = false;
                    System.out.println("STOP_APP");
                }
                break;
            case MESSAGE_PAUSE_APP_TO_SHOW:
                if (modoActividad) {
                    SessionData data_pause = (SessionData) msg.getBody();
                    if (appToLaunch != null) {
                        appToLaunch.pause();
                        synchronized (containers) {
                            for (Container container : containers) {
                                if (container instanceof UserContainer) {
                                    UserContainer uc = (UserContainer) container;
                                    uc.setState(AppState.PAUSE);
                                }
                            }
                        }
                    }
                    gson = new Gson();
                    data_json = gson.toJson(data_pause);
                        Helper.INSTANCE.getActiveAccountsManager().getMultiTouchTables().get(0).getjSession().sendMessage(data_json, data_pause.getCurrentModerator());
                    System.out.println("PAUSE_APP");
                }
                break;
            case MESSAGE_RESUME_APP_TO_SHOW:
                SessionData data_resume = (SessionData) msg.getBody();
                if (modoActividad) {

                    if (appToLaunch != null) {
                        appToLaunch.resume();
                        synchronized (containers) {
                            for (Container container : containers) {
                                if (container instanceof UserContainer) {
                                    UserContainer uc = (UserContainer) container;
                                    uc.setState(AppState.RESUME);
                                }
                            }
                        }
                    }
                    gson = new Gson();
                    data_json = gson.toJson(data_resume);
                        Helper.INSTANCE.getActiveAccountsManager().getMultiTouchTables().get(0).getjSession().sendMessage(data_json, data_resume.getCurrentModerator());
                    System.out.println("RESUME_APP");
                }
                break;

            case MESSAGE_SEND_APP_TO_SHOW:
                SessionData data = (SessionData) msg.getBody();
                if (modoActividad) {

                    ArrayList<String> users = data.getUsersToAssign();

                    for (int i = 0; i < users.size(); i++) {
                        String object = users.get(i);
                        Helper.INSTANCE.getMessageProcessor().notifyMessage(new User(object + "@edumat", "edumat"), null, this, MessageType.MESSAGE_REQUEST_LOGIN);
                    }
                    USER_CONTAINERS = true;

                    try {
                        Helper.INSTANCE.getAppLoader().openJar(data.getAppToRun());
                        if (Helper.INSTANCE.getAppLoader()
                                .isValid()) {

                            appToLaunch = Helper.INSTANCE.getAppLoader()
                                    .getInstance();
                            appToLaunch.init();
                            gson = new Gson();
                            data_json = gson.toJson(data);
                        Helper.INSTANCE.getActiveAccountsManager().getMultiTouchTables().get(0).getjSession().sendMessage(data_json, data.getCurrentModerator());


                        }else{
                        
                            System.out.println("App No VALIDA");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(TouchWorld.class.getName()).log(Level.SEVERE, null, ex);
                    }




                    synchronized (containers) {
                        for (Container container : containers) {
                            if (container instanceof UserContainer) {
                                UserContainer uc = (UserContainer) container;
                                uc.setState(AppState.LAUNCH);
                            }
                        }
                    }
                } else {
                    gson = new Gson();
                    data.setState(AppState.ERROR);
                    data_json = gson.toJson(data);
                        Helper.INSTANCE.getActiveAccountsManager().getMultiTouchTables().get(0).getjSession().sendMessage(data_json, data.getCurrentModerator());
                }
                break;

            case MESSAGE_CREATE_CURSOR:
                tcur = (TuioCursor) msg.getBody();
                this.cursorList.put(Long.valueOf(tcur.getCursorID()), tcur);
                tcur.setCreated(true);
                if (Constants.DEBUG_MODE) {
                    System.out.println("Create");
                }
                synchronized (entity) {

                    PointerEntity entity_ = new PointerEntity(new Vector2f(tcur.getScreenX(Constants.WIDTH), tcur.getScreenY(Constants.HEIGHT)), "" + tcur.getUid());
                    entity.put(tcur, entity_);
                }
                collision();

                break;
            case MESSAGE_UPDATE_CURSOR:
                synchronized (entity) {
                    tcur = (TuioCursor) msg.getBody();

                    PointerEntity e = entity.get(tcur);
                    if (e.getId().equals("" + tcur.getUid())) {
                        e.setPosition(tcur.getScreenX(Constants.WIDTH), tcur.getScreenY(Constants.HEIGHT));
                        tcur.setCreated(false);
                        e.setCreated(false);
                        break;
                    }

                    if (Constants.DEBUG_MODE) {
                        System.out.println("Update");
                    }
                }
                break;
            case MESSAGE_REMOVE_CURSOR:
                tcur = (TuioCursor) msg.getBody();

                synchronized (entity) {
                    toRemove = entity.remove(tcur);
                }

                for (int i = 0; i < containers.size(); i++) {
                    Container container = containers.get(i);
                    container.removePointer(toRemove);
                }

                if (appToLaunch != null) {
                    appToLaunch.removePointers(toRemove);
                }

                this.cursorList.remove(Long.valueOf(tcur.getCursorID()));
                if (Constants.DEBUG_MODE) {
                    System.out.println("Remove");
                }

                break;
            case CREATE_IMAGE:

                path_image = (String) msg.getBody();
                currentSender = msg.getFrom();
                CREATE_IMAGE_FLAG = true;
                break;
            case CREATE_PDF:

                path_pdf = (String) msg.getBody();
                currentSender_pdf = msg.getFrom();
                CREATE_PDF_FLAG = true;

                break;
            case CREATE_SVG:
                path_svg = (String) msg.getBody();
                currentSender_svg = msg.getFrom();
                CREATE_SVG_FLAG = true;

                break;

        }

    }

    /**
     * Add container to TouchWorld
     *
     * @param container container to add
     *
     */
    public void add(Container container) {
        synchronized (containers) {
            container.setOrder(containers.size() + 1);
            onTop = container;
            this.containers.add(container);
        }
    }

    /**
     * get container in TouchWorld
     *
     * @param id ID of container
     *
     */
    public Container get(String id) {
        synchronized (containers) {
            for (Container container : containers) {
                if (container.getId().equalsIgnoreCase(id)) {
                    return container;
                }
            }
        }
        return null;
    }

    /**
     * remove container in TouchWorld
     *
     * @param id ID of container
     *
     */
    public boolean remove(String id) {
        synchronized (containers) {
            for (Container container : containers) {
                if (container.getId().equalsIgnoreCase(id)) {
                    containers.remove(container);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public final String toString() {
        return this.getClass().getName();
    }

    /**
     *
     * Method to detect collision with Containers.
     */
    private void collision() {
        if (!mainMenu) {


            OrderArray toShowTop = new OrderArray();
            ArrayList<PointerEntity> pointers = new ArrayList<PointerEntity>();
            if (!containers.isEmpty()) {
                synchronized (entity) {
                    for (Iterator<Container> it = containers.iterator(); it.hasNext();) {
                        Container container = it.next();
                        for (Map.Entry<TuioCursor, PointerEntity> entry : entity.entrySet()) {
                            TuioCursor tuioCursor = entry.getKey();
                            PointerEntity e = entry.getValue();
                            if (e.isCreated()) {
                                if (e.getToShow() != null) {
                                    if (container.containsPoint(e.getPosition())) {
                                        toShowTop.add(container);
                                        pointers.add(e);
                                    }
                                }
                            }

                        }

                    }



                    Container temp;

                    if (!toShowTop.isEmpty()) {

                        for (int i = 0; i < toShowTop.size(); i++) {

                            for (int j = 0; j < toShowTop.size() - 1; j++) {

                                if (toShowTop.get(j).compareTo(toShowTop.get(j + 1)) > 0) {
                                    temp = toShowTop.get(j);
                                    toShowTop.set(j, toShowTop.get(j + 1));
                                    toShowTop.set(j + 1, temp);
                                }
                            }
                        }

                        containers.interchange(onTop, toShowTop.get(toShowTop.size() - 1));
                        onTop = toShowTop.get(toShowTop.size() - 1);
                        for (PointerEntity e : pointers) {



                            if (e.getToShow() != null) {
                                if (onTop.containsPoint(e.getPosition())) {

                                    onTop.addPointer(e);
                                }
                            }
                        }

                    }


                    if (appToLaunch != null) {
                        synchronized (entity) {



                            appToLaunch.collision(entity);
                        }
                    }


                }
            }
        } else {

            synchronized (entity) {

                for (Map.Entry<TuioCursor, PointerEntity> entry : entity.entrySet()) {
                    TuioCursor tuioCursor = entry.getKey();
                    PointerEntity e = entry.getValue();
                    if (e.isCreated()) {
                        if (e.getToShow() != null) {

                            if (bttn1.contains(e.getToShow().getX(), e.getToShow().getY())) {
                                System.out.println("Modo App");
                                mainMenu = false;
                                File files = new File("/Users/" + System.getProperty("user.name")
                                        + "/SparkleShare/multitouch apps/Applications/");

                                String[] list = files.list(new FilenameFilter() {
                                    @Override
                                    public boolean accept(File file, String string) {
                                        if (!string.startsWith(".")) {
                                            if (file.isDirectory() && !file.isHidden()) {
                                                return true;
                                            }
                                        }

                                        return false;
                                    }
                                });


                                iconsURLs = list;

                            } else if (bttn2.contains(e.getToShow().getX(), e.getToShow().getY())) {
                                mainMenu = false;
                                modoLibre = true;

                                System.out.println("Modo libre");
                            } else if (bttn3.contains(e.getToShow().getX(), e.getToShow().getY())) {
                                System.out.println("Modo Actividad");
                                modoActividad = true;
                                mainMenu = false;


                            } else if (bttn4.contains(e.getToShow().getX(), e.getToShow().getY())) {
                                System.out.println("Salir");

                                System.exit(0);
                            }
                        }
                    }
                }

            }
        }

    }

    /**
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }

    private void drawImage() {
        if (CREATE_IMAGE_FLAG) {

            if (path_image != null && currentSender != null) {
                try {
                    imageRender = new ImageRenderEntity(new Vector2f(500, 500), new Image(path_image), path_image);

                    Helper.INSTANCE.getMessageProcessor().notifyMessage(imageRender, currentSender.getClass().getName(), this, MessageType.CREATE_IMAGE);
                } catch (SlickException ex) {
                    Logger.getLogger(TouchWorld.class.getName()).log(Level.SEVERE, null, ex);
                }

            }


            CREATE_IMAGE_FLAG = false;
            currentSender = null;

        }



    }

    private void drawSVG() {
        if (CREATE_SVG_FLAG) {
            if (path_svg != null && currentSender_svg != null) {
                CREATE_SVG_FLAG = false;

                svgRender = new SVGRenderEntity(new Vector2f(0, 0), new JSVG(path_svg), path_svg);
                Helper.INSTANCE.getMessageProcessor().notifyMessage(svgRender, currentSender_svg.getClass().getName(), this, MessageType.CREATE_SVG);
                currentSender_svg = null;


            }

        }
    }

    private void drawPDF() {
        if (CREATE_PDF_FLAG) {
            if (path_pdf != null && currentSender_pdf != null) {
                CREATE_PDF_FLAG = false;

                pdfRender = new PDFRenderEntity(new Vector2f(0, 0), new JPDF(path_pdf), path_pdf);
                Helper.INSTANCE.getMessageProcessor().notifyMessage(pdfRender, currentSender_pdf.getClass().getName(), this, MessageType.CREATE_PDF);

                currentSender_pdf = null;

            }

        }
    }

    private void drawUserContainers() {
        if (USER_CONTAINERS) {
            
            for (Iterator<Participant> it = Helper.INSTANCE.getActiveAccountsManager().getActiveUsers().iterator(); it.hasNext();) {
                Participant participant = it.next();
                
                Image imagenAvatarTest = participant.getjSession().getAvatar();
                imagenAvatarTest = imagenAvatarTest.getScaledCopy(70, 70);
                UserContainer avatarCOntainer = new UserContainer(null, new Vector2f(100, 100), new Size(400, 400), participant.getjSession(), new ImageRenderEntity(new Vector2f((0f), (0f)), imagenAvatarTest, imagenAvatarTest.getName()), imagenAvatarTest.getResourceReference());
                this.add(avatarCOntainer);
            }




            USER_CONTAINERS = false;
        }
    }
}
