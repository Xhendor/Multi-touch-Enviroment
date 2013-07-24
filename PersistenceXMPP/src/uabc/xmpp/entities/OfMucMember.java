package uabc.xmpp.entities;
// Generated 24-may-2013 19:23:52 by Hibernate Tools 3.2.1.GA



/**
 * OfMucMember generated by hbm2java
 */
public class OfMucMember  implements java.io.Serializable {


     private OfMucMemberId id;
     private String nickname;
     private String firstName;
     private String lastName;
     private String url;
     private String email;
     private String faqentry;

    public OfMucMember() {
    }

	
    public OfMucMember(OfMucMemberId id) {
        this.id = id;
    }
    public OfMucMember(OfMucMemberId id, String nickname, String firstName, String lastName, String url, String email, String faqentry) {
       this.id = id;
       this.nickname = nickname;
       this.firstName = firstName;
       this.lastName = lastName;
       this.url = url;
       this.email = email;
       this.faqentry = faqentry;
    }
   
    public OfMucMemberId getId() {
        return this.id;
    }
    
    public void setId(OfMucMemberId id) {
        this.id = id;
    }
    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFaqentry() {
        return this.faqentry;
    }
    
    public void setFaqentry(String faqentry) {
        this.faqentry = faqentry;
    }




}


