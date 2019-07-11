package com.doit.net.unicom.udp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.doit.net.unicom.udp.exception.UnicomException;
import com.doit.net.unicom.udp.utils.PrintUtils;

/**
 * Created by wiker on 2016/3/15.
 */
public class UnicomServerManager {

    private final static Logger log = LoggerFactory.getLogger(UnicomServerManager.class);

    private static DatagramSocket datagramSocket;


    private static int PORT = 5071;
    private static int SEND_PORT = 5070;
    private static final int TIME_OUT = 3*60*1000;

    public static boolean isStarted = false;
//    public static IStartError startError;

    public static void startListener(int port) throws UnicomException{
        try {
        	PORT = port;
            datagramSocket = new DatagramSocket(PORT);
            datagramSocket.setSoTimeout(TIME_OUT);
            datagramSocket.setReceiveBufferSize(1024*20000);
            isStarted = true;
            new UnicomReceiverThread().start();
            new UnicomWorkThread().start();
            new UnicomSenderThread().start();
            if(TEST_MODEL){
            	new TestThread().start();
            }
            log.info("UDP server (Unicom) started on port:"+PORT);
        } catch (SocketException e) {
            log.error("UDPServer (Unicom) 启动异常",e);
            throw new UnicomException("UDPServer (Unicom) 启动异常，端口："+PORT,e);
//            if(startError != null){
//                startError.error("UDPServer (FDD LTE) 启动异常，端口："+PORT,e);
//                startError = null;
//            }
        }
    }
    
    public static void startListener() throws UnicomException{
    	startListener(PORT);
    }
    
    private static boolean TEST_MODEL = false;
    public static void openTestModel() {
		TEST_MODEL = true;
	}
    
    /**
     * 设置Hex打印开关
     * @param isTrace
     */
    public static void setHexTrace(boolean isTrace){
    	PrintUtils.isDebug = isTrace;
    }


    /**
     * 获取DatagramSocket
     * @return
     */
    public static DatagramSocket getDatagramSocket() throws Exception{
        if(datagramSocket == null){
            startListener();
        }
        return datagramSocket;
    }

    /**
     * 接收数据包，该方法会造成线程阻塞
     * @return
     * @throws Exception
     * @throws IOException
     */
    public static DatagramPacket receive(DatagramPacket packet) throws Exception {
        try {
            datagramSocket.receive(packet);
            return packet;
        } catch (Exception e) {
            throw e;
        }
    }

    public static void stop(){
        try {
            isStarted = false;
            if(datagramSocket.isClosed() == false)
                datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将响应包发送给请求端
     * @param packet
     * @throws IOException
     */
    public static void send(DatagramPacket packet) {
        try {
            datagramSocket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static int getSendPort() {
		return SEND_PORT;
	}

	public static void setSendPort(int port) {
		SEND_PORT = port;
	}
    
    

}
