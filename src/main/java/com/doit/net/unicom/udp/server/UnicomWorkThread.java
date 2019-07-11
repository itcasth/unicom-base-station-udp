package com.doit.net.unicom.udp.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.doit.net.unicom.udp.base.UnicomHandler;
import com.doit.net.unicom.udp.base.UnicomMessage;
import com.doit.net.unicom.udp.handler.LTECommonHandler;
import com.doit.net.unicom.udp.handler.LTEGetParamHandler;
import com.doit.net.unicom.udp.handler.LTEHearBeatHandler;
import com.doit.net.unicom.udp.handler.LTERebootHandler;
import com.doit.net.unicom.udp.handler.LTEReportHandler;
import com.doit.net.unicom.udp.handler.LTESetParamHandler;
import com.doit.net.unicom.udp.handler.LTESetPowerHandler;
import com.doit.net.unicom.udp.service.UnicomServiceManager;

/**
 * Created by wiker on 3/14/16.
 */
public class UnicomWorkThread extends Thread {

    private static final Logger log = LoggerFactory.getLogger(UnicomWorkThread.class);

    private static BlockingQueue<UnicomMessage> workQueue = new LinkedBlockingQueue<UnicomMessage>();

    private static Map<String,UnicomHandler> handlerMap = new HashMap<String,UnicomHandler>();

    
    
    public UnicomWorkThread() {
		setName("Unicom-Work");
	}

	@Override
    public void run() {
//        initHandler();
        log.info("Unicom UDP Server Work thread started");
        while(UnicomServerManager.isStarted){
            try {

                UnicomMessage packet = workQueue.take();

                //TODO 根据消息type和code处理消息
                String msgId = packet.messageType+"-"+packet.messageCode;
                
                UnicomServiceManager.handlerFinish(packet);
                
                //全部统一处理Handler
                /*
                UnicomHandler handler = handlerMap.get(msgId);
                if(handler == null){
                    log.warn(msgId+",未找到对应的消息Handler");
                    continue;
                }
                handler.handler(packet);
				*/

            } catch (Exception e) {
                log.error("",e);
                continue;
            }
        }
    }

    public static void push(UnicomMessage respInfo){
        try {
            log.debug(String.format("Add Unicom work queue,Src:%s,Type:%s,Code:%s",respInfo.getInetSocketAddress().toString(),respInfo.messageType,respInfo.messageCode));
            workQueue.put(respInfo);
        } catch (Exception e) {
            log.error("",e);
        }
    }

    public static void initHandler(){
        log.info("Init Unicom message handler");
        LTECommonHandler commonHandler = new LTECommonHandler();
        handlerMap.put("03-24",new LTEReportHandler());
        handlerMap.put("03-12",new LTEHearBeatHandler());
        handlerMap.put("02-08",new LTERebootHandler());
        handlerMap.put("03-04",new LTEGetParamHandler());
        handlerMap.put("03-02",new LTESetParamHandler());
        handlerMap.put("03-15",new LTESetPowerHandler());
        handlerMap.put("03-18",commonHandler);   //关停发射回应
        handlerMap.put("03-20",commonHandler);   //关停发射回应

        handlerMap.put("03-01",commonHandler);  //设定特种基站工作参数请求
        handlerMap.put("09-02",commonHandler);  //获取设备运行参数回应
        handlerMap.put("03-09",commonHandler);  //基站状态上报
        handlerMap.put("00-02",commonHandler);  //开启发射回应
        handlerMap.put("00-05",commonHandler);  //关停发射回应
        handlerMap.put("03-13",commonHandler);  //基站功率设定回应
        handlerMap.put("03-23",commonHandler);  //基站系统重启动接收回应
        handlerMap.put("09-04",commonHandler);  //基站系统重启动接收回应
        handlerMap.put("03-10",commonHandler);  //设置驱赶到2G的信息回应
        handlerMap.put("03-28",commonHandler);  //设置驱赶至cdma参数回应
        handlerMap.put("03-30",commonHandler);  //设置驱赶类型回应
        handlerMap.put("06-17",commonHandler);  //设置基站驱赶模式的信息回应
        handlerMap.put("06-15",commonHandler);  //设置驱赶到2G的信息回应
        handlerMap.put("07-08",commonHandler);  //6.20	定位请求信息的回应
        handlerMap.put("07-10",commonHandler);  //6.21	控制端下发停止定位请求信息
        handlerMap.put("07-11",commonHandler);  //6.23	基站上报定位信息
        handlerMap.put("07-12",commonHandler);  //6.24	基站上报定位失败信息
        handlerMap.put("07-16",commonHandler);  //6.26	再定位请求信息回应
        handlerMap.put("07-17",commonHandler);  //6.26	再定位请求信息回应
        handlerMap.put("03-51",commonHandler);  //6.26	再定位请求信息回应
        handlerMap.put("03-52",commonHandler);  //6.26	再定位请求信息回应
        handlerMap.put("03-54",commonHandler);  //6.26	再定位请求信息回应
    }
}
