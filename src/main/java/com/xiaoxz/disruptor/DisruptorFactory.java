package com.xiaoxz.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/29
 * @Modified by :
 **/
public class DisruptorFactory implements EventFactory<LongData> {

    @Override
    public LongData newInstance() {
        return new LongData();
    }
}
