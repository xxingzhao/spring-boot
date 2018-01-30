package com.xiaoxz.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/29
 * @Modified by :
 **/
public class DisruptorMain {


    public static void main(String[] args){
        DisruptorFactory disruptorFactory = new DisruptorFactory();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Disruptor<LongData> disruptor = new Disruptor<LongData>(disruptorFactory, 1024, executorService,
                ProducerType.MULTI, new BlockingWaitStrategy());

        disruptor.handleEventsWithWorkerPool(new Consumer(), new Consumer(), new Consumer());
        disruptor.start();

        RingBuffer<LongData> ringBuffer = disruptor.getRingBuffer();
        DisProducer disProducer = new DisProducer(ringBuffer);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            disProducer.push("value" + i);
        }
        long end  = System.currentTimeMillis();

        System.out.println("total times" + (end - start) / 1000);
        disruptor.shutdown();
        executorService.shutdown();
    }
}
