package com.guava.test.component.nuonuo;

import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
public class Observer implements java.util.Observer {

    /**
     * 业务执行方法
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Observed observed = (Observed)o;
        String result=observed.getResult();
        System.out.println("当前执行。。。。，返回"+result);
    }
}
