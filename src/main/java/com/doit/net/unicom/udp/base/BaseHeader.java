package com.doit.net.unicom.udp.base;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

/**
 * Created by wiker on 2016/4/14.
 */
public class BaseHeader extends UDPCoder {

    private SocketAddress socketAddress;

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public InetSocketAddress getInetSocketAddress(){
        return (InetSocketAddress)socketAddress;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    public void setSocketAddress(String ip,int port){
        try {
            socketAddress = new InetSocketAddress(InetAddress.getByName(ip),port);
        } catch (UnknownHostException e) {
        }
    }

    public String getRemoteAddr(){
        return getInetSocketAddress().getAddress().getHostAddress();
    }

    public int getRemotePort(){
        return getInetSocketAddress().getPort();
    }

    @Override
    public Object decode() {
        return null;
    }

    @Override
    public byte[] encode() {
        return new byte[0];
    }
}
