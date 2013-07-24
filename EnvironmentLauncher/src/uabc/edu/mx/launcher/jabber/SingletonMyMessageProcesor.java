/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uabc.edu.mx.launcher.jabber;

/**
 *
 * @author xhendor
 */
public class SingletonMyMessageProcesor {

    private static MyMessageProcessor uniqInstance;

    private SingletonMyMessageProcesor() {
    }
    /**
     * Return single intance of MessageProcessor
     * @return Single instance of MessageProcessor
     */
    public static synchronized MyMessageProcessor getInstance() {
        if (uniqInstance == null) {
            uniqInstance = new MyMessageProcessor();
        }
        return uniqInstance;
    }
    
}
