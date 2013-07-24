package uabc.edu.mx.message;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xhendor
 */
public interface IMessage {

  

    public Object getBody();

    /**
     * @return the to
     */
    public Object getTo();

    /**
     * @return the from
     */
    public Object getFrom();

    /**
     * @return the type
     */
    public MessageType getType();
}
