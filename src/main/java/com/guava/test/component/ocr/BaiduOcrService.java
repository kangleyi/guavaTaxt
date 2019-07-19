package com.guava.test.component.ocr;

import java.util.Map;

public class BaiduOcrService implements OcrService{
    @Override
    public InvoiceDto getOcrDetail(Map map) {
        InvoiceDtoAbstractFactory invoiceDtoAbstractFactory = FactoryProducer.getFactory(FactoryProducer.FactoryBeanEnum.BAIDU);
        return invoiceDtoAbstractFactory.getBaiduOcr(map);
    }
}
