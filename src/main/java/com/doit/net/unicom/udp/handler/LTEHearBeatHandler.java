package com.doit.net.unicom.udp.handler;

import com.doit.net.unicom.udp.base.UnicomHandler;
import com.doit.net.unicom.udp.base.UnicomMessage;
import com.doit.net.unicom.udp.bean.UnicomHearBeatBean;

/**
 * Created by wiker on 2016/4/14.
 */
public class LTEHearBeatHandler implements UnicomHandler {

    @Override
    public void handler(UnicomMessage msg) {

        UnicomHearBeatBean bean = new UnicomHearBeatBean(msg.messageType,msg.messageCode,msg.messageContent);
        bean.setSocketAddress(msg.getSocketAddress());
//        System.out.println("serialNumber:"+bean.getDeviceSerialNumber());
//        System.out.println("Frequency:"+bean.getDownFrequency());
//        System.out.println("PCI:"+bean.getPci());
//        System.out.println("RF State:"+bean.getRfState());
//        System.out.println("TAC:"+bean.getTac());

    }
}
