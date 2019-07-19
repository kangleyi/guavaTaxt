package com.guava.test.component.ocr;



public class BaiduInvoiceFactory extends InvoiceDtoAbstractFactory{
    @Override
    public InvoiceDto getBaiduOcr(Object map) {
        InvoiceDto invoiceDto=new InvoiceDto();
        return invoiceDto;
    }

    @Override
    public InvoiceDto getHuaweiOcr(Object map) {
        return null;
    }
}
