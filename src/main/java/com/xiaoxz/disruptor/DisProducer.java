package com.xiaoxz.disruptor;

import com.lmax.disruptor.RingBuffer;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/29
 * @Modified by :
 **/
public class DisProducer {

    private final RingBuffer<LongData> ringBuffer;

    public DisProducer(RingBuffer<LongData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void push(String value) {
        long sequcense = ringBuffer.next();
        try{
            LongData longData = ringBuffer.get(sequcense);
            longData.setValue(value);
        } catch(Exception e) {

        } finally {
            ringBuffer.publish(sequcense);
        }
    }
}
