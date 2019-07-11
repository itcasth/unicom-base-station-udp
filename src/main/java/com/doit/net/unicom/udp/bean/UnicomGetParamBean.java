package com.doit.net.unicom.udp.bean;

import com.doit.net.unicom.udp.base.UnicomMessage;

/**
 * Created by wiker on 2016/4/14.
 */
public class UnicomGetParamBean extends UnicomMessage {

    public String[] contents;

    public UnicomGetParamBean(String messageType, String messageCode, String messageContent) {
        super(messageType, messageCode, messageContent);
        contents = messageContent.split("#");
    }

    public UnicomGetParamBean() {
    }


    public String getPci(){
        return contents[0];
    }

    public String getPlmnID(){
        return contents[1];
    }
    
    public String getMCC(){
        return getPlmnID().substring(0, 3);
    }
    
    public String getMNC(){
    	return getPlmnID().substring(3, 5);
    }

    public String getTAC(){
        return contents[2];
    }
    public String getBand(){
        return contents[3];
    }

    public String getCI(){
        return contents[4];
    }

    public String getDLARFCN(){
        return contents[5];
    }

    public String getULARFCN(){
        return contents[6];
    }

    public String getGSM_ARFCN(){
        return contents[7];
    }

    public String getCDMA_ARFCN(){
        return contents[8];
    }

    public String getCDMA_BABDCLASS(){
        return contents[9];
    }

    public String getBAND_WIDTH(){
        return contents[10];
    }

    public String getPOWER(){
        return contents[11];
    }

    public String getExpelType(){
        return contents[12];
    }




}
