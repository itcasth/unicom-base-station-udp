package com.doit.net.unicom.udp.handler;

import com.doit.net.unicom.udp.base.UnicomMessage;
import com.doit.net.unicom.udp.constants.LTEConstants;
import com.doit.net.unicom.udp.server.UnicomSenderThread;
import com.doit.net.unicom.udp.utils.MiniStringUtils;

/**
 * Created by wiker on 2016/4/15.
 */
public class WCDMAMessageCreator {



    public static void createReboot(String ip,int port){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = "22";
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createSetPower(String ip,int port,int dbm){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = "12";
        message.messageContent = "SETPOWER "+dbm;
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createGetParam(String ip,int port){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= "09";
        message.messageCode = "01";
        message.encode();
        UnicomSenderThread.put(message);
    }


    public static void createSetParam(String ip,int port,String mcc,String mnc,String lac,String arfcno,String psc,String rac,String cellId){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= LTEConstants.MSG_TYPE.SET;
        message.messageCode = "01";
        StringBuilder sb = new StringBuilder();
        if(MiniStringUtils.isNotBlank(mcc)){
            sb.append("MCC:"+mcc+"@");
        }
        if(MiniStringUtils.isNotBlank(mnc)){
            sb.append("MNC:"+mnc+"@");
        }
        if(MiniStringUtils.isNotBlank(lac)){
            sb.append("LAC:"+lac+"@");
        }
        if(MiniStringUtils.isNotBlank(arfcno)){
            sb.append("ARFCNo:"+arfcno+"@");
        }
        if(MiniStringUtils.isNotBlank(psc)){
            sb.append("PSC:"+psc+"@");
        }
        if(MiniStringUtils.isNotBlank(rac)){
            sb.append("RAC:"+rac+"@");
        }
        if(MiniStringUtils.isNotBlank(cellId)){
            sb.append("CELLID:"+cellId+"@");
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


    public static void createRFState(String ip,int port,boolean isOpen){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= "00";
        if(isOpen){
            message.messageCode = "01";
        }else{
            message.messageCode = "04";
        }
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createExpelToGsm(String ip,int port,String ncc,String bcc,String arfcn){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= "06";
        message.messageCode = "14";
        message.messageContent = ncc+bcc+"#"+arfcn+"#";
        message.encode();
        UnicomSenderThread.put(message);
    }

    public static void createExpelType(String ip,int port,String type){
        createRequest(ip,port,"06","16",type);
    }

    public static void createLocStopReq(String ip,int port){
        createRequest(ip,port,"07","09",null);
    }


    public static void createAgainLocStartReq(String ip,int port){
        createRequest(ip,port,"07","15",null);
    }

    private static boolean isStartedLoc = false;
    public static void createLocStartReq(String ip,int port,String imsi,int rptTimes,int retryTimes,int useType,int fcnSwitch,int pilotFreq){
//        if(isStartedLoc){
//            createAgainLocStartReq(ip,port);
//            return;
//        }
//        isStartedLoc = true;
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= "07";
        message.messageCode = "07";
        if(useType != 1){
            if(fcnSwitch == 0) {
                message.messageContent = imsi + "#" + rptTimes + "#" + retryTimes + "#" + useType + "#" + fcnSwitch;
            }else{
                message.messageContent = imsi+"#"+rptTimes+"#"+retryTimes+"#"+useType+"#"+fcnSwitch+"#"+pilotFreq;
            }
        }else{
            message.messageContent = imsi+"#"+rptTimes+"#"+retryTimes+"#"+useType;
        }
        message.encode();
        UnicomSenderThread.put(message);
    }

    private static void createRequest(String ip,int port,String msgType,String msgCode,String messageContent){
        UnicomMessage message = new UnicomMessage();
        message.setSocketAddress(ip,port);
        message.messageType= msgType;
        message.messageCode = msgCode;
        message.messageContent = messageContent;
        message.encode();
        UnicomSenderThread.put(message);
    }

}
