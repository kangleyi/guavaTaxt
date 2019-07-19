package com.guava.test.component.ocr;

public class FactoryProducer {

    enum FactoryBeanEnum{
        BAIDU,HUAWEI
    }

    public static InvoiceDtoAbstractFactory getFactory(FactoryBeanEnum factoryBeanEnum){
        switch (factoryBeanEnum){
            case BAIDU:return new BaiduInvoiceFactory();
            case HUAWEI:return new HuaweiInvoiceFactory();
            default:return null;
        }
    }
}
