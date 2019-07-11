package com.doit.net.unicom.udp.handler;

import com.doit.net.unicom.udp.base.UnicomHandler;
import com.doit.net.unicom.udp.base.UnicomMessage;

/**
 * Created by wiker on 2016/4/14.
 */
public class WCDMAHeartHandler implements UnicomHandler {

    public static int times=0;
    @Override
    public void handler(UnicomMessage msg) {

    	times ++;
        if(times>5){
            times = 0;
            WCDMAMessageCreator.createGetParam(msg.getRemoteAddr(),msg.getRemotePort());
        }
        
    }
}
