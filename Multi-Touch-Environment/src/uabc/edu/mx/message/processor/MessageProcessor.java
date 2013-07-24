package uabc.edu.mx.message.processor;
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
import uabc.edu.mx.data.session.User;
import uabc.edu.mx.message.Message;
import java.util.HashMap;
import uabc.edu.mx.data.session.SessionData;
import uabc.edu.mx.helper.Helper;
import uabc.edu.mx.jabber.JabberSession;
import uabc.edu.mx.jabber.user.MultiTouchTable;
import uabc.edu.mx.jabber.user.Participant;
import uabc.edu.mx.message.listener.MessageListener;
import uabc.edu.mx.listener.tuio.TUIOListener;
import uabc.edu.mx.message.MessageType;
import uabc.edu.mx.tuio.TuioClient;
import uabc.edu.mx.world.TouchWorld;
import utils.Constants;

/**
 *
 * @author xhendor
 */
public class MessageProcessor implements IMessageProcessor {

    private HashMap<String, Object> registredListeners = new HashMap<String, Object>();
    private TuioClient clientTUIO;
    private TouchWorld touchWorld;
    private String server;


    public MessageProcessor() {

    }

    @Override
    public void notifyMessage(Message msg) {
        MessageListener msgListener;
        switch (msg.getType()) {
            case MESSAGE_REQUEST_TUIO_SERVER:
                clientTUIO = new TuioClient();
                clientTUIO.addTuioListener(new TUIOListener(touchWorld));
                clientTUIO.connect();
                if (clientTUIO.isConnected()) {
                    System.out.println("Connected");
                    this.notifyMessage("TUIO CONNECTED", msg.getTo(), msg.getFrom(), MessageType.MESSAGE_CONNECTED_TUIO_SERVER);
                } else {
                    System.out.println("NotConnected");
                    this.notifyMessage("TUIO NOT_FOUND", msg.getTo(), msg.getFrom(), MessageType.MESSAGE_NOT_FOUND_TUIO_SERVER);
                }
                break;
            case MESSAGE_CONNECTED_TUIO_SERVER:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;
            case MESSAGE_NOT_FOUND_TUIO_SERVER:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;
            case MESSAGE_XMPP_REQUEST_SERVER:
                try {
                    server = (String) msg.getBody();
                    this.notifyMessage(new Message("Server Found", msg.getTo(), msg.getFrom(), MessageType.MESSAGE_SERVER_FOUND));

                } catch (Exception ex) {

                    this.notifyMessage(new Message("Server Not Found", msg.getTo(), msg.getFrom(),MessageType.MESSAGE_SEVER_NOT_FOUND));
                }
                break;
            case MESSAGE_SERVER_FOUND:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;
            case MESSAGE_LOGIN_OK:

                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;
            case MESSAGE_LOGIN_ERROR:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;
            case MESSAGE_SEVER_NOT_FOUND:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;
            case MESSAGE_REQUEST_LOGIN:
                try {
                    User user = (User) msg.getBody();
                    if (Helper.INSTANCE.getActiveAccountsManager().getNumberOfMultiTouchTable()== 0) {
                        MultiTouchTable tableUser=new MultiTouchTable(user.getuName(), user.getuPassword());
                        tableUser.setjSession(new JabberSession(user, server));
                       Helper.INSTANCE.getActiveAccountsManager().addMultiTouchTable(tableUser); 
                    } else {
                        Participant participant=new Participant(user.getuName(), user.getuPassword());
                        
                        if (!Helper.INSTANCE.getActiveAccountsManager().getActiveUsers().contains(participant)) {
                            participant.setjSession(new JabberSession(user, server));
                            Helper.INSTANCE.getActiveAccountsManager().addActiveUser(participant);
                        }
                    }

                } catch (Exception ex) {

                    this.notifyMessage(new Message("Server Not Found", msg.getTo(), msg.getFrom(),MessageType.MESSAGE_SEVER_NOT_FOUND));                }
                break;


            case MESSAGE_XMPP_MSG_IN:
                String dataBody = (String) msg.getBody();
                System.out.println("Data:" + dataBody);
                if (dataBody.startsWith("{\"appToRun\"")) {
                    Gson json = new Gson();
                    SessionData data = json.fromJson(dataBody, SessionData.class);
                    Message nmsg;
                    switch (data.getState()) {

                        case LAUNCH:
                           
                            nmsg = new Message(data, TouchWorld.class.getName(), msg.getFrom(), MessageType.MESSAGE_SEND_APP_TO_SHOW);
                           msgListener = (MessageListener) getListener(TouchWorld.class.getName());
                            msgListener.messageReciever(nmsg);

                            break;
                        case START:
                            nmsg = new Message(data, TouchWorld.class.getName(), msg.getFrom(), MessageType.MESSAGE_START_APP_TO_SHOW);
                            msgListener = (MessageListener) getListener(TouchWorld.class.getName());
                            msgListener.messageReciever(nmsg);

                            break;
                        case PAUSE:
                            nmsg = new Message(data, TouchWorld.class.getName(), msg.getFrom(), MessageType.MESSAGE_PAUSE_APP_TO_SHOW);
                            msgListener = (MessageListener) getListener(TouchWorld.class.getName());
                            msgListener.messageReciever(nmsg);

                            break;
                        case RESUME:
                            nmsg = new Message(data, TouchWorld.class.getName(), msg.getFrom(), MessageType.MESSAGE_RESUME_APP_TO_SHOW);
                           msgListener = (MessageListener) getListener(TouchWorld.class.getName());
                            msgListener.messageReciever(nmsg);

                            break;
                        case STOP:
                            nmsg = new Message(data, TouchWorld.class.getName(), msg.getFrom(), MessageType.MESSAGE_STOP_APP_TO_SHOW);
                             msgListener = (MessageListener) getListener(TouchWorld.class.getName());
                            msgListener.messageReciever(nmsg);
                            
                        case ERROR:
                             nmsg = new Message(data, TouchWorld.class.getName(), msg.getFrom(), MessageType.MESSAGE_ERROR_APP_TO_SHOW);
                             msgListener = (MessageListener) getListener(TouchWorld.class.getName());
                            msgListener.messageReciever(nmsg);
                            
                            break;

                    }


                } else if (dataBody.startsWith("TOP_LEVEL_INIT")) {
                    Message nmsg;
                    nmsg = new Message("", "uabc.edu.mx.main.Main", msg.getFrom(), MessageType.TOP_LEVEL_INIT);
                    msgListener = (MessageListener) getListener("uabc.edu.mx.main.Main");
                    msgListener.messageReciever(nmsg);

                } else if (dataBody.startsWith("MAIN_APP_BEGIN")) {
                    Message nmsg;
                    nmsg = new Message("", "uabc.edu.mx.main.Main", msg.getFrom(), MessageType.MAIN_APP_BEGIN);
                    msgListener = (MessageListener) getListener("uabc.edu.mx.main.Main");
                    msgListener.messageReciever(nmsg);

                } else if (dataBody.startsWith("MAIN_APP_END")) {
                    Message nmsg;
                    nmsg = new Message("", "uabc.edu.mx.main.Main", msg.getFrom(), MessageType.MAIN_APP_STOP);
                    msgListener = (MessageListener) getListener("uabc.edu.mx.main.Main");
                    msgListener.messageReciever(nmsg);
                    msgListener = (MessageListener) getListener("uabc.edu.mx.mtenv.MultiTouchEnvironment");
                    msgListener.messageReciever(nmsg);

                }

                break;
            case MESSAGE_SEND_APP_TO_SHOW:
                msgListener = (MessageListener) getListener(TouchWorld.class.getName());
                msgListener.messageReciever(msg);

                break;



            case MESSAGE_START_APP_TO_SHOW:
                msgListener = (MessageListener) getListener(TouchWorld.class.getName());
                msgListener.messageReciever(msg);

                break;

            case MESSAGE_STOP_APP_TO_SHOW:

                msgListener = (MessageListener) getListener(TouchWorld.class.getName());
                msgListener.messageReciever(msg);
                break;
            case MESSAGE_PAUSE_APP_TO_SHOW:

                msgListener = (MessageListener) getListener(TouchWorld.class.getName());
                msgListener.messageReciever(msg);
                break;


            case MESSAGE_RESUME_APP_TO_SHOW:
                msgListener = (MessageListener) getListener(TouchWorld.class.getName());
                msgListener.messageReciever(msg);

                break;
            case MESSAGE_CREATE_CURSOR:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);

                break;
            case MESSAGE_UPDATE_CURSOR:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;
            case MESSAGE_REMOVE_CURSOR:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;

            case CREATE_IMAGE:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;
                 case CREATE_PDF:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;
                      case CREATE_SVG:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;

        }

    }

    @Override
    public boolean registerListener(MessageListener obj, String name) {
        System.out.println("Registrado:" + name);
        Object respose = registredListeners.put(name, obj);
        if (respose == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean removeListener(String name) {
        Object respose = registredListeners.remove(name);
        if (respose == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public MessageListener getListener(String name) {
        MessageListener respose = (MessageListener) registredListeners.get(name);
        if (respose == null) {
            if (Constants.DEBUG_MODE) {
                System.out.println("Listener Not Found");
            }
            return null;
        } else {
            if (Constants.DEBUG_MODE) {
                System.out.println("ListenerFound");
            }
            return respose;
        }
    }

    @Override
    public void notifyMessage(Object body, Object to, Object from, Enum type) {
        Message msg = new Message(body, to, from, (MessageType) type);
        notifyMessage(msg);

    }

    @Override
    public void setTouchWorld(TouchWorld touchWorld) {
        this.touchWorld = touchWorld;

    }

    public TouchWorld getTouchWorld() {
        return touchWorld;

    }

}
