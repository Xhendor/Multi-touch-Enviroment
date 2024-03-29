package uabc.xmpp.entities;
// Generated 24-may-2013 19:23:52 by Hibernate Tools 3.2.1.GA



/**
 * OfRosterGroupsId generated by hbm2java
 */
public class OfRosterGroupsId  implements java.io.Serializable {


     private long rosterId;
     private byte rank;

    public OfRosterGroupsId() {
    }

    public OfRosterGroupsId(long rosterId, byte rank) {
       this.rosterId = rosterId;
       this.rank = rank;
    }
   
    public long getRosterId() {
        return this.rosterId;
    }
    
    public void setRosterId(long rosterId) {
        this.rosterId = rosterId;
    }
    public byte getRank() {
        return this.rank;
    }
    
    public void setRank(byte rank) {
        this.rank = rank;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OfRosterGroupsId) ) return false;
		 OfRosterGroupsId castOther = ( OfRosterGroupsId ) other; 
         
		 return (this.getRosterId()==castOther.getRosterId())
 && (this.getRank()==castOther.getRank());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getRosterId();
         result = 37 * result + this.getRank();
         return result;
   }   


}


