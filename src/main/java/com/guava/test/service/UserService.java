package com.guava.test.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    public int getService(){
        try {
            TimeUnit.SECONDS.sleep(1);
            return 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
