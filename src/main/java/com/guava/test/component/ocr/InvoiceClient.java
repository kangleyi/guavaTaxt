package com.guava.test.component.ocr;

import java.util.Map;

public class InvoiceClient {

    BusinessDelegate businessService;

    public InvoiceClient(BusinessDelegate businessService){
        this.businessService  = businessService;
    }

    public InvoiceDto doTask(Map map){
       return businessService.doTask(map);
    }
}
