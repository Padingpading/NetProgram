package com.padingpading.netprogram.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.KeyStore;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author libin
 * @description
 * @date 2021/6/23
 */
public class HandlerSocketServerPool{
    //1、创建一个线程的成员变量对象用于存储一个线程池对象
    private ExecutorService executorService;

    //创建这个累的对象的时候需要初始化线程池对象。
    public HandlerSocketServerPool(int maxThreadNum ,int queueSize){
        executorService = new ThreadPoolExecutor(3,maxThreadNum,120, TimeUnit.SECONDS,new ArrayBlockingQueue<>(queueSize));
    }

    //提供方法提交方法给任务给线程池的任务队列来暂存，等待线程池处理。
    public void submit(Runnable  runnable){
        executorService.submit(runnable);
    }

}
