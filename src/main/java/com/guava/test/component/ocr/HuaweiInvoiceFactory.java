package com.guava.test.component.ocr;



public class HuaweiInvoiceFactory extends InvoiceDtoAbstractFactory{
    @Override
    public InvoiceDto getBaiduOcr(Object map) {
        return null;
    }

    @Override
    public InvoiceDto getHuaweiOcr(Object map) {
        InvoiceDto invoiceDto=new InvoiceDto();
        return invoiceDto;
    }
}
