package com.doit.net.unicom.udp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.doit.net.unicom.udp.base.UnicomMessage;

/**
 * 数据回调管理类
 * @author wiker
 *
 */
public class UnicomServiceManager {
	
	private final static Logger log = LoggerFactory.getLogger(UnicomServiceManager.class);

	private static Map<String,List<IHandlerFinish>> callList = new HashMap<String,List<IHandlerFinish>>();
	
	public static synchronized void addCallBack(String messageId,IHandlerFinish i){
        log.info("register listener :"+messageId+",class:"+i.getClass().getName());
        if(callList.containsKey(messageId)){
        	callList.get(messageId).add(i);
        }else{
        	List<IHandlerFinish> list = new ArrayList<IHandlerFinish>();
        	list.add(i);
        	callList.put(messageId,list);
        }
    }

    public static synchronized void removeCallBack(String messageId){
        log.info("remove listener messageId:"+messageId);
        callList.remove(messageId);
    }

    public static void handlerFinish(UnicomMessage message){
    	log.debug("call handler finish id:"+message.getMessageID());
    	if(callList.containsKey(message.getMessageID())){
    		for(IHandlerFinish i : callList.get(message.getMessageID())){
    			if(i == null) continue;
    	        i.workFinish(message);
    		}
    	}
    }
}
