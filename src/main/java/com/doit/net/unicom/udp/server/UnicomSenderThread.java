package com.doit.net.unicom.udp.server;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.doit.net.unicom.udp.base.UnicomMessage;

/**
 * Created by wiker on 3/14/16.
 */
public class UnicomSenderThread extends Thread {

    private final static Logger log = LoggerFactory.getLogger(UnicomSenderThread.class);

    private static BlockingQueue<UnicomMessage> senderQueue = new LinkedBlockingQueue<UnicomMessage>();




    public UnicomSenderThread() {
		setName("Unicom-Sender");
	}



	@Override
    public void run() {
        log.info("Unicom UDP Sender thread started");
        while(true) try {
            UnicomMessage reqBody = senderQueue.take();
            byte[] bodyBit = reqBody.getBytes();

            log.info(String.format("Send msgType:%s,msgCode:%s,content:%s to %s", reqBody.messageType,reqBody.messageCode, reqBody.messageContent, reqBody.getSocketAddress()));


//            PrintUtils.printHex(bodyBit);
            DatagramPacket dataPacket = new DatagramPacket(bodyBit, bodyBit.length,
                        reqBody.getSocketAddress());
            //测试CODE
//            DatagramPacket dataPacket = new DatagramPacket(body, body.length,
//                    InetAddress.getByName("10.10.10.100"), 6060);

//            DatagramSocket dataSocket = new DatagramSocket(reqBody.getHeader().getInetSocketAddress().getPort());
//            DatagramSocket dataSocket = new DatagramSocket(45677);
            DatagramSocket dataSocket = getSocket();
            if(dataSocket == null){
                log.warn("Not found Unicom socket"+reqBody.getInetSocketAddress().getPort());
                continue;
            }

            dataSocket.send(dataPacket);

        } catch (Exception e) {
            log.error("Send Unicom message error", e);
        }
    }



    public static DatagramSocket getSocket() throws Exception{
        return UnicomServerManager.getDatagramSocket();
    }


    public static void put(UnicomMessage body){
        try {
            log.debug(String.format("添加消息 Type:%s,Code:%s 到发送队列",body.messageType,body.messageCode));
            senderQueue.put(body);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
