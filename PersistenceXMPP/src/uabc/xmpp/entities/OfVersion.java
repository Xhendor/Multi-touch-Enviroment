package uabc.xmpp.entities;
// Generated 24-may-2013 19:23:52 by Hibernate Tools 3.2.1.GA



/**
 * OfVersion generated by hbm2java
 */
public class OfVersion  implements java.io.Serializable {


     private String name;
     private int version;

    public OfVersion() {
    }

    public OfVersion(String name) {
       this.name = name;
    }
   
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public int getVersion() {
        return this.version;
    }
    
    public void setVersion(int version) {
        this.version = version;
    }




}


