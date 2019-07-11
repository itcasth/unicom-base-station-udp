package com.doit.net.unicom.udp.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.doit.net.unicom.udp.utils.MiniStringUtils;

/**
 * Created by wiker on 2016/4/14.
 */
public class UnicomMessage extends BaseHeader {

    private final static Logger log = LoggerFactory.getLogger(UnicomMessage.class);

    public String messageType;
    public String messageCode;
    public String messageContent;

    public UnicomMessage(){}

    public UnicomMessage(String messageType, String messageCode, String messageContent) {
        this.messageType = messageType;
        this.messageCode = messageCode;
        this.messageContent = messageContent;
    }

    @Override
    public Object decode() {
        try {
            messageType = readString(2);
            messageCode = readString(2);
            messageContent = readString(data.length-pos);
        } catch (Exception e) {
            log.error("",e);
        }
        return this;
    }

    @Override
    public byte[] encode() {
        try {
            writeString(messageType,2);
            writeString(messageCode,2);
            messageContent = MiniStringUtils.defaultString(messageContent);
            writeString(messageContent,messageContent.getBytes("GBK").length);
        } catch (Exception e) {
            log.error("",e);
        }
        return getBytes();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type:"+messageType+",");
        sb.append("Code:"+messageCode+",");
        sb.append("Content:"+messageContent);
        return sb.toString();
    }

    public String getMessageID(){
        return messageType+"-"+messageCode;
    }

}
