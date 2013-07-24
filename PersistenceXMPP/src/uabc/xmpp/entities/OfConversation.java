package uabc.xmpp.entities;
// Generated 24-may-2013 19:23:52 by Hibernate Tools 3.2.1.GA



/**
 * OfConversation generated by hbm2java
 */
public class OfConversation  implements java.io.Serializable {


     private long conversationId;
     private String room;
     private byte isExternal;
     private long startDate;
     private long lastActivity;
     private int messageCount;

    public OfConversation() {
    }

	
    public OfConversation(long conversationId, byte isExternal, long startDate, long lastActivity, int messageCount) {
        this.conversationId = conversationId;
        this.isExternal = isExternal;
        this.startDate = startDate;
        this.lastActivity = lastActivity;
        this.messageCount = messageCount;
    }
    public OfConversation(long conversationId, String room, byte isExternal, long startDate, long lastActivity, int messageCount) {
       this.conversationId = conversationId;
       this.room = room;
       this.isExternal = isExternal;
       this.startDate = startDate;
       this.lastActivity = lastActivity;
       this.messageCount = messageCount;
    }
   
    public long getConversationId() {
        return this.conversationId;
    }
    
    public void setConversationId(long conversationId) {
        this.conversationId = conversationId;
    }
    public String getRoom() {
        return this.room;
    }
    
    public void setRoom(String room) {
        this.room = room;
    }
    public byte getIsExternal() {
        return this.isExternal;
    }
    
    public void setIsExternal(byte isExternal) {
        this.isExternal = isExternal;
    }
    public long getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
    public long getLastActivity() {
        return this.lastActivity;
    }
    
    public void setLastActivity(long lastActivity) {
        this.lastActivity = lastActivity;
    }
    public int getMessageCount() {
        return this.messageCount;
    }
    
    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }




}

