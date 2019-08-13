package com.highgreat.sven.handler.core;

public class HgHandler {

    private HgLooper mLooper;
    private HgMessageQueue mQueue;

    public HgHandler(){

        mLooper = HgLooper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException(
                    "Can't create handler inside thread " + Thread.currentThread()
                            + " that has not called Looper.prepare()");
        }
        mQueue = mLooper.mQueue;
    }

    public void handleMessage(HgMessage msg){

    };

    public void sendMessage(HgMessage msg){
        enqueueMessage(msg);
    }

    private void enqueueMessage(HgMessage msg){
        //赋值当前消息
        msg.target = this;
        //使用
        mQueue.enqueueMessage(msg);
    }


    public void dispatchMessage(HgMessage msg) {
        handleMessage(msg);
    }


}
