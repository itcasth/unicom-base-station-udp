package com.doit.net.unicom.udp.bean;

import com.doit.net.unicom.udp.base.UnicomMessage;

/**
 * Created by wiker on 2016/4/14.
 */
public class UnicomHearBeatBean extends UnicomMessage {

    public String[] contents;

    public UnicomHearBeatBean(String messageType, String messageCode, String messageContent) {
        super(messageType, messageCode, messageContent);
        contents = messageContent.split("#");
    }

    public UnicomHearBeatBean() {
    }

    public String getRfState(){
        return contents[0];
    }

    public String getDownFrequency(){
        return contents[1];
    }

    public String getPci(){
        return contents[2];
    }

    public String getTac(){
        return contents[3];
    }

    public String getDeviceSerialNumber(){
        return contents[4];
    }

}
