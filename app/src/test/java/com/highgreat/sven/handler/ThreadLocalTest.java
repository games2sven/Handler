package com.highgreat.sven.handler;

import org.junit.Test;

public class ThreadLocalTest {

    @Test
    public void test(){
        //创建一个本地线程（主线程）
        final ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                //重写初始化方法，默认返回null
                return "Sven";
            }
        };

        System.out.println("主线程threadLocal：" + threadLocal.get());  //Sven

        //---------------------------------------------------thread-1
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //从ThreadLocalMap key=thread-1得值 ？ 没有  就走初始化方法
                String value1 = threadLocal.get();
                System.out.println("thread-1：" + value1);  //Sven

                threadLocal.set("大卫老师");
                String value2 = threadLocal.get();
                System.out.println("thread-1：" + value2);  //大卫老师

                //避免大量无意义得内存占用
                threadLocal.remove();
            }
        });
        thread.start();

        //---------------------------------------------------thread-2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //从ThreadLocalMap key=thread-1得值 ？ 没有  就走初始化方法
                String value1 = threadLocal.get();
                System.out.println("thread-2：" + value1);  //Sven

                threadLocal.set("zhou");
                String value2 = threadLocal.get();
                System.out.println("thread-2：" + value2);  //zhou

                //避免大量无意义得内存占用
                threadLocal.remove();
            }
        });
        thread2.start();
    }

}
