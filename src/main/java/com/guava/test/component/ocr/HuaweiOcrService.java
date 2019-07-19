package com.guava.test.component.ocr;

import java.util.Map;

public class HuaweiOcrService implements OcrService{
    @Override
    public InvoiceDto getOcrDetail(Map map) {
        InvoiceDtoAbstractFactory invoiceDtoAbstractFactory = FactoryProducer.getFactory(FactoryProducer.FactoryBeanEnum.HUAWEI);
        return invoiceDtoAbstractFactory.getHuaweiOcr(map);
    }
}
