package uabc.xmpp.entities;
// Generated 24-may-2013 19:23:52 by Hibernate Tools 3.2.1.GA



/**
 * OfVcard generated by hbm2java
 */
public class OfVcard  implements java.io.Serializable {


     private String username;
     private String vcard;

    public OfVcard() {
    }

    public OfVcard(String username, String vcard) {
       this.username = username;
       this.vcard = vcard;
    }
   
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getVcard() {
        return this.vcard;
    }
    
    public void setVcard(String vcard) {
        this.vcard = vcard;
    }




}


