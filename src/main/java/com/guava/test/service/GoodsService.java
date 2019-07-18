package com.guava.test.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class GoodsService {
    public int getService(){
        try {
            TimeUnit.SECONDS.sleep(3);
            return 3;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
