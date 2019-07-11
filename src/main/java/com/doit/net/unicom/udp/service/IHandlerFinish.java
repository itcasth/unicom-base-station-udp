package com.doit.net.unicom.udp.service;

import com.doit.net.unicom.udp.base.UnicomMessage;

/**
 * Created by wiker on 2016/3/18.
 */
public interface IHandlerFinish<T extends UnicomMessage> {

    void workFinish(T body);
}
