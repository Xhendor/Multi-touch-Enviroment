package uabc.edu.mx.message;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xhendor
 */
public class Message implements IMessage {

    private Object body;
    private Object to;
    private Object from;
    private MessageType type;

    public Message(Object body, Object to, Object from, MessageType type) {
        this.body = body;
        this.to = to;
        this.from = from;
        this.type = type;
    }

    
    /**
     * @return the body
     */
    @Override
    public Object getBody() {
        return body;
    }

    

    /**
     * @return the to
     */
    @Override
    public Object getTo() {
        return to;
    }


    /**
     * @return the from
     */
    @Override
    public Object getFrom() {
        return from;
    }

   
    /**
     * @return the type
     */
    @Override
    public MessageType getType() {
        return type;
    }

    
}
