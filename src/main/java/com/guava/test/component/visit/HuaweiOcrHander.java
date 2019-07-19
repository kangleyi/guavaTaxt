package com.guava.test.component.visit;


/**
 * 增值税发票识别
 */
public class HuaweiOcrHander implements HuaweiHttp{

    @Override
    public void accept(HuaweiVisitor huaweiVisitor) {
        huaweiVisitor.visit(this);
    }
}
