package com.guava.test.controller;

import com.google.common.util.concurrent.*;
import com.guava.test.service.GoodsService;
import com.guava.test.service.OrderService;
import com.guava.test.service.UserService;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@RestController
public class HellowController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    /**
     *  传统方式
     */
    @RequestMapping("/hello")
    public Map hello(){
        long start=System.currentTimeMillis();
        int rst1=goodsService.getService();
        int rst2=orderService.getService();
        int rst3=userService.getService();
        HashMap hashMap=new HashMap();
        hashMap.put("result",rst1+rst2+rst3);
        hashMap.put("time","总耗时"+(System.currentTimeMillis()-start)+"ms！");
        return hashMap;
    }

    /**
     * 启用thread
     * 缺点，直接调用get方法会阻塞当前线程，直至任务线程执行完成，因此当多任务共用线程池时，会出现等待时间不定
     * @return
     */
    @RequestMapping("/hello2")
    public Map hello2(){
        long start=System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(5,10,100, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
        Future<Integer> rst3=threadPoolExecutor.submit(()->goodsService.getService());
        Future<Integer> rst2=threadPoolExecutor.submit(()->orderService.getService());
        Future<Integer> rst1=threadPoolExecutor.submit(()->userService.getService());
        HashMap hashMap=new HashMap();
        try {
            hashMap.put("result", rst1.get() + rst2.get() + rst3.get());
        }catch (Exception e){
            e.printStackTrace();
        }
        hashMap.put("time","总耗时"+(System.currentTimeMillis()-start)+"ms！");
        return hashMap;
    }

    /**
     * 启用thread
     *
     * @return
     */
    @RequestMapping("/hello3")
    public Map hello3(){
        long start=System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(5,10,100, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
        Future<Integer> rst1=threadPoolExecutor.submit(()->goodsService.getService());
        Future<Integer> rst2=threadPoolExecutor.submit(()->orderService.getService());
        Future<Integer> rst3=threadPoolExecutor.submit(()->userService.getService());
        HashMap hashMap=new HashMap();
        do{
            try{
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (
                rst1.isDone() && rst2.isDone() && rst3.isDone()
        );
        try {
            hashMap.put("result", rst1.get() + rst2.get() + rst3.get());
        }catch (Exception e){
            e.printStackTrace();
        }
        hashMap.put("time","总耗时"+(System.currentTimeMillis()-start)+"ms！");
        return hashMap;
    }

    /**
     * 启用guava 处理线程
     * 线程执行结束后进行正常的回调，通过guava方法执行
     * @return
     */
    @RequestMapping("/hello4")
    public Map hello4(){
        long start=System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(5,10,100, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());

        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(threadPoolExecutor);

        final ListenableFuture<Integer> rst1=executorService.submit(()->goodsService.getService());
        final ListenableFuture<Integer> rst2=executorService.submit(()->orderService.getService());
        final ListenableFuture<Integer> rst3=executorService.submit(()->userService.getService());

        final ListenableFuture<List<Integer>> listenableFuture=Futures.successfulAsList(rst1,rst2,rst3);

        Futures.addCallback(listenableFuture, new FutureCallback<List<Integer>>() {
            @Override
            public void onSuccess(@Nullable List<Integer> v) {
                int a=0;
                for(int z:v){
                    a+=z;
                }
                System.out.println("运算结果为："+a);
                System.out.println("回调：https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd"+a+""+a+""+a);
                System.out.println("耗时："+(System.currentTimeMillis()-start));
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        }, executorService);
        HashMap hashMap=new HashMap();
        hashMap.put("time","总耗时"+(System.currentTimeMillis()-start)+"ms！");
        return hashMap;
    }

}
