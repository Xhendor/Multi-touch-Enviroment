package uabc.xmpp.entities;
// Generated 24-may-2013 19:23:52 by Hibernate Tools 3.2.1.GA



/**
 * OfPubsubAffiliation generated by hbm2java
 */
public class OfPubsubAffiliation  implements java.io.Serializable {


     private OfPubsubAffiliationId id;
     private String affiliation;

    public OfPubsubAffiliation() {
    }

    public OfPubsubAffiliation(OfPubsubAffiliationId id, String affiliation) {
       this.id = id;
       this.affiliation = affiliation;
    }
   
    public OfPubsubAffiliationId getId() {
        return this.id;
    }
    
    public void setId(OfPubsubAffiliationId id) {
        this.id = id;
    }
    public String getAffiliation() {
        return this.affiliation;
    }
    
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }




}


