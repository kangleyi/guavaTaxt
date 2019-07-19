package com.guava.test.component.visit;

public class HuaweiFileHander implements HuaweiHttp{

    @Override
    public void accept(HuaweiVisitor huaweiVisitor) {
        huaweiVisitor.visit(this);
    }
}
