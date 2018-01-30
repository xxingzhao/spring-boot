package com.xiaoxz.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/29
 * @Modified by :
 **/
public class Consumer implements WorkHandler<LongData> {


    @Override
    public void onEvent(LongData longData) throws Exception {
        String value = longData.getValue();
        System.out.println(Thread.currentThread().getName() + " from distruptor " + value);
      //  Thread.sleep(1000);
    }
}
