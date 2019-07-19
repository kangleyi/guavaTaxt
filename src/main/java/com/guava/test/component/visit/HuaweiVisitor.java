package com.guava.test.component.visit;

public interface HuaweiVisitor {

    public void visit(HuaweiFileHander fileHander);

    public void visit(HuaweiOcrHander ocrHander);

    public void visit(HuaweiHander ocrHander);
}
