package uabc.xmpp.entities;
// Generated 24-may-2013 19:23:52 by Hibernate Tools 3.2.1.GA



/**
 * OfPubsubDefaultConfId generated by hbm2java
 */
public class OfPubsubDefaultConfId  implements java.io.Serializable {


     private String serviceId;
     private byte leaf;

    public OfPubsubDefaultConfId() {
    }

    public OfPubsubDefaultConfId(String serviceId, byte leaf) {
       this.serviceId = serviceId;
       this.leaf = leaf;
    }
   
    public String getServiceId() {
        return this.serviceId;
    }
    
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
    public byte getLeaf() {
        return this.leaf;
    }
    
    public void setLeaf(byte leaf) {
        this.leaf = leaf;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OfPubsubDefaultConfId) ) return false;
		 OfPubsubDefaultConfId castOther = ( OfPubsubDefaultConfId ) other; 
         
		 return ( (this.getServiceId()==castOther.getServiceId()) || ( this.getServiceId()!=null && castOther.getServiceId()!=null && this.getServiceId().equals(castOther.getServiceId()) ) )
 && (this.getLeaf()==castOther.getLeaf());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getServiceId() == null ? 0 : this.getServiceId().hashCode() );
         result = 37 * result + this.getLeaf();
         return result;
   }   


}


