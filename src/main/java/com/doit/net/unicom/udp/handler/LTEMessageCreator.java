package com.doit.net.unicom.udp.handler;

import com.doit.net.unicom.udp.base.UnicomMessage;
import com.doit.net.unicom.udp.constants.LTEConstants;
import com.doit.net.unicom.udp.server.UnicomSenderThread;
import com.doit.net.unicom.udp.utils.MiniStringUtils;

/**
 * Created by wiker on 2016/4/15.
 */
public class LTEMessageCreator {


    public static void createRestartCell(String ip,int port){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = LTEConstants.MSG_CODE.RESTART_CELL;
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createReboot(String ip,int port){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = LTEConstants.MSG_CODE.REBOOT_STATION;
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createReset(String ip,int port){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = LTEConstants.MSG_CODE.RESET_STATION;
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetPower(String ip,int port,int dbm){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = LTEConstants.MSG_CODE.SET_STATION_POWER;
        message.messageContent = "SETPOWER "+dbm;
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createGetParam(String ip,int port){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = LTEConstants.MSG_CODE.GET_PARAM;
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetGoto2G(String ip,int port,String arfcn){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = LTEConstants.MSG_CODE.SET_TO_2G;
        message.messageContent = arfcn;
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetGotoCDMA(String ip,int port,String arfcn,String bandClass){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = LTEConstants.MSG_CODE.SET_TO_CDMA;
        message.messageContent = arfcn+"#"+bandClass+"#";
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetGotoType(String ip,int port,String gotoType){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = LTEConstants.MSG_CODE.SET_TO_TYPE;
        message.messageContent = gotoType;
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetAutoChangeLAC(String ip,int port,int minute){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = LTEConstants.MSG_CODE.SET_AUTO_LAC;
        message.messageContent = String.valueOf(minute);
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetTime(String ip,int port,String time){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = LTEConstants.MSG_CODE.SET_TIME;
        message.messageContent = time;
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetMinRec(String ip,int port,int min){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = "45";
        message.messageContent = String.valueOf(min);
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetToFcn(String ip,int port,String type,String fcn){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = "47";
        message.messageContent = type+"#"+fcn+"#";
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetPci(String ip,int port,String[] pcis){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = "49";
        message.messageContent=pcis.length+"#";
        for(int i=0;i<pcis.length;i++){
            String pci = pcis[i];
            message.messageContent+=pci+i+"#";
        }
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetParam(String ip,int port,String mcc,String mnc,String tac,String arfcno,String pci,String band,String ci,String bdWitth){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = LTEConstants.MSG_CODE.SET_STATION;
        StringBuilder sb = new StringBuilder();
        if(MiniStringUtils.isNotBlank(mcc)){
            sb.append("MCC:"+mcc+"@");
        }
        if(MiniStringUtils.isNotBlank(mnc)){
            sb.append("MNC:"+mnc+"@");
        }
        if(MiniStringUtils.isNotBlank(tac)){
            sb.append("TAC:"+tac+"@");
        }
        if(MiniStringUtils.isNotBlank(arfcno)){
            sb.append("ARFCNO:"+arfcno+"@");
        }
        if(MiniStringUtils.isNotBlank(pci)){
            sb.append("PCI:"+pci+"@");
        }
        if(MiniStringUtils.isNotBlank(band)){
            sb.append("BAND:"+band+"@");
        }
        if(MiniStringUtils.isNotBlank(ci)){
            sb.append("CI:"+ci+"@");
        }
        if(MiniStringUtils.isNotBlank(bdWitth)){
            sb.append("BDWIDTH:"+bdWitth+"@");
        }



//        sb.append(mcc+"@");
//        sb.append(mnc+"@");
//        sb.append(tac+"@");
//        sb.append(arfcno+"@");
//        sb.append(pci+"@");
//        sb.append(band+"@");
//        sb.append(ci+"@");
//        sb.append(bdWitth+"@");
        message.messageContent = sb.toString();
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetIp(String ip,int port,String ipAddr,String mask,String gateway){
        getMsg(ip,port,LTEConstants.MSG_TYPE.SET,LTEConstants.MSG_CODE.SET_STATION_IP,ipAddr+"#"+mask+"#"+gateway+"#");
    }
    
    public static void createStartLocate(String ip,int port,String imsi){
    	getMsg(ip,port,"03","50",imsi);
    }
    
    public static void createStopLocate(String ip,int port){
    	getMsg(ip,port,"03","53","");
    }
    
    private static void getMsg(String ip,int port,String msgType,String msgCode,String content){
    	UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= msgType;
        message.messageCode = msgCode;
        message.messageContent = content;
        message.encode();

        UnicomSenderThread.put(message);
    }

    public static void createRFState(String ip,int port,boolean isOpen){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        if(isOpen){
            message.messageCode = LTEConstants.MSG_CODE.OPEN_RF;
        }else{
            message.messageCode = LTEConstants.MSG_CODE.CLOSE_RF;
        }
        message.encode();
        UnicomSenderThread.put(message);
    }

}
