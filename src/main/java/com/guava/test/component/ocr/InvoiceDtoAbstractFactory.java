package com.guava.test.component.ocr;


public abstract class InvoiceDtoAbstractFactory {
    public abstract InvoiceDto getBaiduOcr(Object map);

    public abstract InvoiceDto getHuaweiOcr(Object map);
}
