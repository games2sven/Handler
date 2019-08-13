package com.highgreat.sven.handler.core;

public class HgLooper {

    static final ThreadLocal<HgLooper> sThreadLocal = new ThreadLocal<HgLooper>();
    final HgMessageQueue mQueue;

    private HgLooper(){
        mQueue = new HgMessageQueue();
    }

    //从全局ThreadLocal中存取唯一的looper对象
    public static void prepare(){
        if(sThreadLocal.get() != null){
            throw new RuntimeException("Only one HgLooper may be created per thread");
        }
        sThreadLocal.set(new HgLooper());
    }

    public static HgLooper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        final HgLooper me = myLooper();
        if (me == null) {
            throw new RuntimeException("No HgLooper; HgLooper.prepare() wasn't called on this thread.");
        }
        final HgMessageQueue queue = me.mQueue;
        for (;;) {
            HgMessage msg = queue.next(); // might block
            if (msg == null) {
                return;
            }
            msg.target.dispatchMessage(msg);
        }

    }
}
