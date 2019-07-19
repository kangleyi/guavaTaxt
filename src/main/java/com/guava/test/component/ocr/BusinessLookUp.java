package com.guava.test.component.ocr;

public class BusinessLookUp {
    public enum  BusinessEnum{
        HUAWEI,BAIDU
    }

    public OcrService getBusinessService(BusinessEnum serviceType){
        switch (serviceType){
            case HUAWEI:return new HuaweiOcrService();
            case BAIDU:return new BaiduOcrService();
            default:return null;
        }
    }
}
