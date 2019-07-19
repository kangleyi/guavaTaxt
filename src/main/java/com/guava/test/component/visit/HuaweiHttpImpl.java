package com.guava.test.component.visit;

public class HuaweiHttpImpl implements HuaweiVisitor {
    @Override
    public void visit(HuaweiFileHander fileHander) {
        System.out.println("华为fileHander被调用！");
    }

    @Override
    public void visit(HuaweiOcrHander ocrHander) {
        System.out.println("华为ocrHander被调用！");
    }

    @Override
    public void visit(HuaweiHander ocrHander) {
        System.out.println("华为所有接口被调用！");
    }
}
