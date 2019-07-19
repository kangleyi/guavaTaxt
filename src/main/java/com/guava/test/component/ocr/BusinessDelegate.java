package com.guava.test.component.ocr;

import java.util.Map;

public class BusinessDelegate {

    private BusinessLookUp lookupService = new BusinessLookUp();

    private OcrService businessService;

    private BusinessLookUp.BusinessEnum serviceType;

    public void setServiceType(BusinessLookUp.BusinessEnum serviceType){
        this.serviceType = serviceType;
    }

    public InvoiceDto doTask(Map map){
        businessService = lookupService.getBusinessService(serviceType);
        return businessService.getOcrDetail(map);
    }
}
