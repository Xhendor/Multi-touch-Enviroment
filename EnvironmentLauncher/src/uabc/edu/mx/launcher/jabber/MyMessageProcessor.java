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
package uabc.edu.mx.launcher.jabber;

import com.google.gson.Gson;
import uabc.edu.mx.data.session.User;
import uabc.edu.mx.message.Message;
import java.util.HashMap;
import uabc.edu.mx.data.session.SessionData;
import uabc.edu.mx.gui.ApplicationLaunchedMenu;
import uabc.edu.mx.gui.ConfiguratorForm;
import uabc.edu.mx.gui.LoginForm;
import uabc.edu.mx.message.listener.MessageListener;
import uabc.edu.mx.message.MessageType;
import uabc.edu.mx.message.processor.IMessageProcessor;
import uabc.edu.mx.data.multitouch.app.AppState;
import uabc.edu.mx.tuio.TuioClient;
import uabc.edu.mx.world.TouchWorld;
import utils.Constants;

/**
 *
 * @author xhendor
 */
public class MyMessageProcessor implements IMessageProcessor {

    private HashMap<String, Object> registredListeners = new HashMap<String, Object>();
    private TuioClient clientTUIO;
    private TouchWorld touchWorld;
    private String server;
    private HashMap<String, FacadeJabber> clientsJabber = new HashMap<String, FacadeJabber>();
    private Gson gson;
    private SessionData data;
    private String msg_to_send;

    public MyMessageProcessor() {

        init();
    }

    @Override
    public void notifyMessage(Message msg) {
        MessageListener msgListener;
        switch (msg.getType()) {

            case MESSAGE_XMPP_REQUEST_SERVER:
                try {
                    server = (String) msg.getBody();
                    this.notifyMessage(new Message("Server Found", msg.getTo(), msg.getFrom(), MessageType.MESSAGE_SERVER_FOUND));

                } catch (Exception ex) {

                    this.notifyMessage(new Message("Server Not Found", msg.getTo(), msg.getFrom(), MessageType.MESSAGE_SEVER_NOT_FOUND));
                }
                break;
            case MESSAGE_SERVER_FOUND:
                msgListener = (MessageListener) getListener(msg.getTo().toString());
                msgListener.messageReciever(msg);
                break;
            case MESSAGE_LOGIN_OK:

                msgListener = (MessageListener) getListener(LoginForm.class.getName());
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

                    if (!clientsJabber.containsKey(user.getuName())) {
                        clientsJabber.put(user.getuName(), new FacadeJabber(user, server));
                    } else {
                        clientsJabber.remove(user.getuName());
                        clientsJabber.put(user.getuName(), new FacadeJabber(user, server));


                    }

                } catch (Exception ex) {
                    this.notifyMessage(new Message(msg.getBody(), msg.getTo(), msg.getFrom(), MessageType.MESSAGE_SEVER_NOT_FOUND));
                }
                break;

            case MESSAGE_CREATE_USER:
                /*if (xmpp.getAccountManager().supportsAccountCreation()) {
                 try {
                 User user = (User) msg.getBody();
                 if (user.getuAttributes() != null) {
                 xmpp.getAccountManager().createAccount(user.getuName(), user.getuPassword(), user.getuAttributes());
                 } else {
                 xmpp.getAccountManager().createAccount(user.getuName(), user.getuPassword());
                 }
                 msgListener = (MessageListener) getListener(msg.getTo().toString());
                 msgListener.messageReciever(new Message("USER CREATED", msg.getTo(), msg.getFrom(), MESSAGE_CREATE_USER_OK));

                 } catch (Exception ex) {
                 msgListener = (MessageListener) getListener(msg.getTo().toString());
                 msgListener.messageReciever(new Message("USER NO CREATED", msg.getTo(), msg.getFrom(), MESSAGE_CREATE_USER_ERROR));
                 }

                 } else {
                 msgListener = (MessageListener) getListener(msg.getTo().toString());
                 msgListener.messageReciever(new Message("USER NO CREATED", msg.getTo(), msg.getFrom(), MESSAGE_CREATE_USER_ERROR));

                 }*/
                break;

            case MESSAGE_XMPP_MSG_IN:
                String dataBody = (String) msg.getBody();

                if (dataBody.startsWith("{\"appToRun\"")) {
                    Gson json = new Gson();
                    SessionData data = json.fromJson(dataBody, SessionData.class);
                    if (data.getState() == AppState.ERROR) {
                    Message nmsg = new Message(data, ConfiguratorForm.class.getName(), msg.getFrom(), MessageType.MESSAGE_SEND_APP_TO_SHOW);
                        msgListener = (MessageListener) getListener(nmsg.getTo().toString());

                        msgListener.messageReciever(nmsg);
                    
                    }else if (data.getState() == AppState.LAUNCH) {
                        Message nmsg = new Message(data, ConfiguratorForm.class.getName(), msg.getFrom(), MessageType.MESSAGE_SEND_APP_TO_SHOW);
                        msgListener = (MessageListener) getListener(nmsg.getTo().toString());

                        msgListener.messageReciever(nmsg);

                    } else if (data.getState() == AppState.START) {

                        Message nmsg = new Message(data, ApplicationLaunchedMenu.class.getName(), msg.getFrom(), MessageType.MESSAGE_START_APP_TO_SHOW);
                        msgListener = (MessageListener) getListener(nmsg.getTo().toString());

                        msgListener.messageReciever(nmsg);
                    } else if (data.getState() == AppState.PAUSE) {

                        Message nmsg = new Message(data, ApplicationLaunchedMenu.class.getName(), msg.getFrom(), MessageType.MESSAGE_PAUSE_APP_TO_SHOW);
                        msgListener = (MessageListener) getListener(nmsg.getTo().toString());

                        msgListener.messageReciever(nmsg);
                    } else if (data.getState() == AppState.RESUME) {

                        Message nmsg = new Message(data, ApplicationLaunchedMenu.class.getName(), msg.getFrom(), MessageType.MESSAGE_RESUME_APP_TO_SHOW);
                        msgListener = (MessageListener) getListener(nmsg.getTo().toString());

                        msgListener.messageReciever(nmsg);
                    } else if (data.getState() == AppState.STOP) {
                           Message nmsg = new Message(data, ApplicationLaunchedMenu.class.getName(), msg.getFrom(), MessageType.MESSAGE_STOP_APP_TO_SHOW);
                        msgListener = (MessageListener) getListener(nmsg.getTo().toString());

                        msgListener.messageReciever(nmsg);
                    }
                }

                break;
            case MESSAGE_SEND_APP_TO_SHOW:

                data = (SessionData) msg.getBody();
                gson = new Gson();
                msg_to_send = gson.toJson(data);
                clientsJabber.get("admin@edumat").sendMessage(msg_to_send, data.getTableMTID());
                break;
            case MESSAGE_START_APP_TO_SHOW:

                data = (SessionData) msg.getBody();
                gson = new Gson();
                msg_to_send = gson.toJson(data);
                clientsJabber.get("admin@edumat").sendMessage(msg_to_send, data.getTableMTID());
                break;

            case MESSAGE_STOP_APP_TO_SHOW:

                data = (SessionData) msg.getBody();
                gson = new Gson();
                msg_to_send = gson.toJson(data);
                clientsJabber.get("admin@edumat").sendMessage(msg_to_send, data.getTableMTID());
                break;
            case MESSAGE_PAUSE_APP_TO_SHOW:

                data = (SessionData) msg.getBody();
                gson = new Gson();
                msg_to_send = gson.toJson(data);
                clientsJabber.get("admin@edumat").sendMessage(msg_to_send, data.getTableMTID());
                break;


            case MESSAGE_RESUME_APP_TO_SHOW:
                data = (SessionData) msg.getBody();
                gson = new Gson();
                msg_to_send = gson.toJson(data);
                clientsJabber.get("admin@edumat").sendMessage(msg_to_send, data.getTableMTID());
                break;

        }

    }

    @Override
    public boolean registerListener(MessageListener obj, String name) {

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

    private void init() {
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
