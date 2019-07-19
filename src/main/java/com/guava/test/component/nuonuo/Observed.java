package com.guava.test.component.nuonuo;

import java.util.Observable;

/**
 * 被观察者对象
 */
public class Observed extends Observable {

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        notifyObservers();//通知观察者对象已被修改
    }
}
