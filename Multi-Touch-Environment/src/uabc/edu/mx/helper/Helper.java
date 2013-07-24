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
package uabc.edu.mx.helper;

import uabc.edu.mx.apploader.AppLoader;
import uabc.edu.mx.entities.processor.EventProcessor;
import uabc.edu.mx.jabber.ActiveAccountsManager;
import uabc.edu.mx.message.processor.MessageProcessor;

/**
 *
 * @author Rosendo R. Sosa.
 */
public enum Helper {

    INSTANCE;
    
    private final MessageProcessor mp = new MessageProcessor();

    public MessageProcessor getMessageProcessor() {

        return mp;
    }
    private final ActiveAccountsManager aam = new ActiveAccountsManager();

    
    public ActiveAccountsManager getActiveAccountsManager() {

        return aam;
    }
    private final EventProcessor evtp = new EventProcessor();

    
    public EventProcessor getEventProcessor() {

        return evtp;
    }

    private final AppLoader appLoader = new AppLoader();

    
    public AppLoader getAppLoader() {

        return appLoader;
    }
}
