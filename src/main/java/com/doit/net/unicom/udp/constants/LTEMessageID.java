package com.doit.net.unicom.udp.constants;

/**
 * Created by wiker on 2016/4/15.
 */
public enum LTEMessageID {
    ;
    private String messageType;
    private String messageCode;

    LTEMessageID(String messageType,String messageCode){
        this.messageType = messageType;
        this.messageCode = messageCode;
    }

    public static LTEMessageID getMessage(String messageType,String messageCode){
        for(LTEMessageID m:LTEMessageID.values()){
            if(m.getMessageType().equals(messageType) && m.getMessageCode().equals(messageCode)){
                return m;
            }
        }
        return null;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
