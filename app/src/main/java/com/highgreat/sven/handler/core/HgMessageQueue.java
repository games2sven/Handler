package com.highgreat.sven.handler.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class HgMessageQueue {

    //阻塞队列
    BlockingQueue<HgMessage> blockingQueue = new ArrayBlockingQueue<HgMessage>(50);

    public void enqueueMessage(HgMessage message){
        try {
            blockingQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //从消息队列中取出消息
    public HgMessage next() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
