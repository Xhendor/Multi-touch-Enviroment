package uabc.xmpp.entities;
// Generated 24-may-2013 19:23:52 by Hibernate Tools 3.2.1.GA



/**
 * OfMucAffiliationId generated by hbm2java
 */
public class OfMucAffiliationId  implements java.io.Serializable {


     private long roomId;
     private String jid;

    public OfMucAffiliationId() {
    }

    public OfMucAffiliationId(long roomId, String jid) {
       this.roomId = roomId;
       this.jid = jid;
    }
   
    public long getRoomId() {
        return this.roomId;
    }
    
    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
    public String getJid() {
        return this.jid;
    }
    
    public void setJid(String jid) {
        this.jid = jid;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OfMucAffiliationId) ) return false;
		 OfMucAffiliationId castOther = ( OfMucAffiliationId ) other; 
         
		 return (this.getRoomId()==castOther.getRoomId())
 && ( (this.getJid()==castOther.getJid()) || ( this.getJid()!=null && castOther.getJid()!=null && this.getJid().equals(castOther.getJid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getRoomId();
         result = 37 * result + ( getJid() == null ? 0 : this.getJid().hashCode() );
         return result;
   }   


}


