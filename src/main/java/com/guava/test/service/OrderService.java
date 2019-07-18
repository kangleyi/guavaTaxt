package com.guava.test.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

    public int getService(){
        try {
            TimeUnit.SECONDS.sleep(2);
            return 2;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
