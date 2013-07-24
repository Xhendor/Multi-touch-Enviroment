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
package uabc.edu.mx.message;

/**
 *
 * @author Rosendo R. Sosa.
 */
public enum MessageType {

    MAIN_APP_STOP,
    MAIN_APP_BEGIN,
    TOP_LEVEL_INIT,
    MESSAGE_SERVER_FOUND,
    MESSAGE_XMPP_REQUEST_SERVER,
    MESSAGE_LOGIN_OK,
    MESSAGE_LOGIN_ERROR,
    MESSAGE_SEVER_NOT_FOUND,
    MESSAGE_REQUEST_LOGIN,
    MESSAGE_CREATE_USER,
    MESSAGE_CREATE_USER_OK,
    MESSAGE_CREATE_USER_ERROR,
    MESSAGE_CREATE_CURSOR,
    MESSAGE_UPDATE_CURSOR,
    MESSAGE_REMOVE_CURSOR,
    MESSAGE_REQUEST_TUIO_SERVER,
    MESSAGE_CONNECTED_TUIO_SERVER,
    MESSAGE_NOT_FOUND_TUIO_SERVER,
    MESSAGE_SEND_APP_TO_SHOW,
    MESSAGE_START_APP_TO_SHOW,
    MESSAGE_PAUSE_APP_TO_SHOW,
    MESSAGE_RESUME_APP_TO_SHOW,
    MESSAGE_STOP_APP_TO_SHOW,
    MESSAGE_XMPP_MSG_IN, 
    CREATE_IMAGE,
    CREATE_PDF,
    CREATE_SVG
,   MESSAGE_ERROR_APP_TO_SHOW}
