package com.highgreat.sven.handler;

import android.os.Handler;

import com.highgreat.sven.handler.core.HgHandler;
import com.highgreat.sven.handler.core.HgLooper;
import com.highgreat.sven.handler.core.HgMessage;

import org.junit.Test;

public class HGActivityThread {

    @Test
    public void main(){

        HgLooper.prepare();

        final HgHandler hgHandler = new HgHandler(){
            @Override
            public void handleMessage(HgMessage msg) {
                super.handleMessage(msg);
                System.out.println(msg.obj.toString());
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                HgMessage message = new HgMessage();
                message.what = 1;
                message.obj = "Hello World！";
                hgHandler.sendMessage(message);
            }
        }).start();

        //获取消息
        HgLooper.loop();

    }

}
