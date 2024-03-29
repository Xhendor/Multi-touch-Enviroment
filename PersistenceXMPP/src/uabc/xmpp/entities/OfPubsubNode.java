package uabc.xmpp.entities;
// Generated 24-may-2013 19:23:52 by Hibernate Tools 3.2.1.GA



/**
 * OfPubsubNode generated by hbm2java
 */
public class OfPubsubNode  implements java.io.Serializable {


     private OfPubsubNodeId id;
     private byte leaf;
     private String creationDate;
     private String modificationDate;
     private String parent;
     private byte deliverPayloads;
     private Integer maxPayloadSize;
     private Byte persistItems;
     private Integer maxItems;
     private byte notifyConfigChanges;
     private byte notifyDelete;
     private byte notifyRetract;
     private byte presenceBased;
     private byte sendItemSubscribe;
     private String publisherModel;
     private byte subscriptionEnabled;
     private byte configSubscription;
     private String accessModel;
     private String payloadType;
     private String bodyXslt;
     private String dataformXslt;
     private String creator;
     private String description;
     private String language;
     private String name;
     private String replyPolicy;
     private String associationPolicy;
     private Integer maxLeafNodes;

    public OfPubsubNode() {
    }

	
    public OfPubsubNode(OfPubsubNodeId id, byte leaf, String creationDate, String modificationDate, byte deliverPayloads, byte notifyConfigChanges, byte notifyDelete, byte notifyRetract, byte presenceBased, byte sendItemSubscribe, String publisherModel, byte subscriptionEnabled, byte configSubscription, String accessModel, String creator) {
        this.id = id;
        this.leaf = leaf;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.deliverPayloads = deliverPayloads;
        this.notifyConfigChanges = notifyConfigChanges;
        this.notifyDelete = notifyDelete;
        this.notifyRetract = notifyRetract;
        this.presenceBased = presenceBased;
        this.sendItemSubscribe = sendItemSubscribe;
        this.publisherModel = publisherModel;
        this.subscriptionEnabled = subscriptionEnabled;
        this.configSubscription = configSubscription;
        this.accessModel = accessModel;
        this.creator = creator;
    }
    public OfPubsubNode(OfPubsubNodeId id, byte leaf, String creationDate, String modificationDate, String parent, byte deliverPayloads, Integer maxPayloadSize, Byte persistItems, Integer maxItems, byte notifyConfigChanges, byte notifyDelete, byte notifyRetract, byte presenceBased, byte sendItemSubscribe, String publisherModel, byte subscriptionEnabled, byte configSubscription, String accessModel, String payloadType, String bodyXslt, String dataformXslt, String creator, String description, String language, String name, String replyPolicy, String associationPolicy, Integer maxLeafNodes) {
       this.id = id;
       this.leaf = leaf;
       this.creationDate = creationDate;
       this.modificationDate = modificationDate;
       this.parent = parent;
       this.deliverPayloads = deliverPayloads;
       this.maxPayloadSize = maxPayloadSize;
       this.persistItems = persistItems;
       this.maxItems = maxItems;
       this.notifyConfigChanges = notifyConfigChanges;
       this.notifyDelete = notifyDelete;
       this.notifyRetract = notifyRetract;
       this.presenceBased = presenceBased;
       this.sendItemSubscribe = sendItemSubscribe;
       this.publisherModel = publisherModel;
       this.subscriptionEnabled = subscriptionEnabled;
       this.configSubscription = configSubscription;
       this.accessModel = accessModel;
       this.payloadType = payloadType;
       this.bodyXslt = bodyXslt;
       this.dataformXslt = dataformXslt;
       this.creator = creator;
       this.description = description;
       this.language = language;
       this.name = name;
       this.replyPolicy = replyPolicy;
       this.associationPolicy = associationPolicy;
       this.maxLeafNodes = maxLeafNodes;
    }
   
    public OfPubsubNodeId getId() {
        return this.id;
    }
    
    public void setId(OfPubsubNodeId id) {
        this.id = id;
    }
    public byte getLeaf() {
        return this.leaf;
    }
    
    public void setLeaf(byte leaf) {
        this.leaf = leaf;
    }
    public String getCreationDate() {
        return this.creationDate;
    }
    
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    public String getModificationDate() {
        return this.modificationDate;
    }
    
    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }
    public String getParent() {
        return this.parent;
    }
    
    public void setParent(String parent) {
        this.parent = parent;
    }
    public byte getDeliverPayloads() {
        return this.deliverPayloads;
    }
    
    public void setDeliverPayloads(byte deliverPayloads) {
        this.deliverPayloads = deliverPayloads;
    }
    public Integer getMaxPayloadSize() {
        return this.maxPayloadSize;
    }
    
    public void setMaxPayloadSize(Integer maxPayloadSize) {
        this.maxPayloadSize = maxPayloadSize;
    }
    public Byte getPersistItems() {
        return this.persistItems;
    }
    
    public void setPersistItems(Byte persistItems) {
        this.persistItems = persistItems;
    }
    public Integer getMaxItems() {
        return this.maxItems;
    }
    
    public void setMaxItems(Integer maxItems) {
        this.maxItems = maxItems;
    }
    public byte getNotifyConfigChanges() {
        return this.notifyConfigChanges;
    }
    
    public void setNotifyConfigChanges(byte notifyConfigChanges) {
        this.notifyConfigChanges = notifyConfigChanges;
    }
    public byte getNotifyDelete() {
        return this.notifyDelete;
    }
    
    public void setNotifyDelete(byte notifyDelete) {
        this.notifyDelete = notifyDelete;
    }
    public byte getNotifyRetract() {
        return this.notifyRetract;
    }
    
    public void setNotifyRetract(byte notifyRetract) {
        this.notifyRetract = notifyRetract;
    }
    public byte getPresenceBased() {
        return this.presenceBased;
    }
    
    public void setPresenceBased(byte presenceBased) {
        this.presenceBased = presenceBased;
    }
    public byte getSendItemSubscribe() {
        return this.sendItemSubscribe;
    }
    
    public void setSendItemSubscribe(byte sendItemSubscribe) {
        this.sendItemSubscribe = sendItemSubscribe;
    }
    public String getPublisherModel() {
        return this.publisherModel;
    }
    
    public void setPublisherModel(String publisherModel) {
        this.publisherModel = publisherModel;
    }
    public byte getSubscriptionEnabled() {
        return this.subscriptionEnabled;
    }
    
    public void setSubscriptionEnabled(byte subscriptionEnabled) {
        this.subscriptionEnabled = subscriptionEnabled;
    }
    public byte getConfigSubscription() {
        return this.configSubscription;
    }
    
    public void setConfigSubscription(byte configSubscription) {
        this.configSubscription = configSubscription;
    }
    public String getAccessModel() {
        return this.accessModel;
    }
    
    public void setAccessModel(String accessModel) {
        this.accessModel = accessModel;
    }
    public String getPayloadType() {
        return this.payloadType;
    }
    
    public void setPayloadType(String payloadType) {
        this.payloadType = payloadType;
    }
    public String getBodyXslt() {
        return this.bodyXslt;
    }
    
    public void setBodyXslt(String bodyXslt) {
        this.bodyXslt = bodyXslt;
    }
    public String getDataformXslt() {
        return this.dataformXslt;
    }
    
    public void setDataformXslt(String dataformXslt) {
        this.dataformXslt = dataformXslt;
    }
    public String getCreator() {
        return this.creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getReplyPolicy() {
        return this.replyPolicy;
    }
    
    public void setReplyPolicy(String replyPolicy) {
        this.replyPolicy = replyPolicy;
    }
    public String getAssociationPolicy() {
        return this.associationPolicy;
    }
    
    public void setAssociationPolicy(String associationPolicy) {
        this.associationPolicy = associationPolicy;
    }
    public Integer getMaxLeafNodes() {
        return this.maxLeafNodes;
    }
    
    public void setMaxLeafNodes(Integer maxLeafNodes) {
        this.maxLeafNodes = maxLeafNodes;
    }




}


