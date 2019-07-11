package com.doit.net.unicom.udp.constants;

/**
 * Created by wiker on 2016/4/14.
 */
public class LTEConstants {

    public static class MSG_TYPE{
        public final static String SET = "03";
    }

    public static class MSG_CODE{
        public final static String SET_STATION = "01";
        public final static String OPEN_RF = "17";
        public final static String CLOSE_RF = "19";
        public final static String SET_STATION_POWER = "15";
        public final static String STATION_HEART_BEAT = "12";
        public final static String SET_STATION_IP = "05";
        public final static String REBOOT_STATION = "07";
        public final static String REBOOT_STATION_ACK = "08";
        public final static String RESET_STATION = "13";
        public final static String STATION_REPORT = "24";
        public final static String GET_PARAM = "03";
        public final static String SET_TO_2G = "09";
        public final static String SET_TO_CDMA = "27";
        public final static String SET_TO_TYPE = "29";
        public final static String RESTART_CELL = "25";
        public final static String SET_AUTO_LAC = "40";
        public final static String SET_TIME = "42";
    }
}
