package com.doit.net.unicom.udp.server;

import java.net.DatagramPacket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.doit.net.unicom.udp.base.UnicomMessage;

/**
 * Created by wiker on 16/3/11.
 */
public class UnicomReceiverThread extends Thread {

    private final static Logger log = LoggerFactory.getLogger(UnicomReceiverThread.class);


    private static final int BUFFER_SIZE = 1024;

    
    
    public UnicomReceiverThread() {
		setName("Unicom-Receiver");
	}


	@Override
    public void run() {
        Init();
    }


    public void Init() {

        log.info("Unicom UDP Receiver thread started");

        while(UnicomServerManager.isStarted){

            try {


                byte[] receiveBody = new byte[BUFFER_SIZE];
                DatagramPacket dataPacketBody = new DatagramPacket(receiveBody, receiveBody.length);
                if(dataPacketBody == null) continue;

                UnicomServerManager.receive(dataPacketBody);
                if(dataPacketBody == null) continue;

                //添加到工作线程队列
                UnicomMessage respInfo = new UnicomMessage();
                respInfo.data = dataPacketBody.getData();
                respInfo.setSocketAddress(dataPacketBody.getSocketAddress());
                respInfo.decode();

                log.info("Receive MSG:"+respInfo.toString());
                UnicomWorkThread.push(respInfo);


            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        log.info("Unicom UDP receiver thread finished");
    }


}
