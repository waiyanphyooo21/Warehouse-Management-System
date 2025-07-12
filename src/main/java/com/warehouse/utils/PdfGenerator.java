// src/main/java/com/warehouse/utils/PdfGenerator.java
package com.warehouse.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.OutputStream;
import java.math.BigDecimal;

public class PdfGenerator {

    public static void generateVoucherPdf(OutputStream out,
                                          int itemId,
                                          String productName,
                                          String productCode,
                                          String categoryName,
                                          BigDecimal purchasePrice,
                                          BigDecimal salePrice,
                                          String stockLocation) throws Exception {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);
        document.open();

        Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
        Font bold = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font normal = new Font(Font.HELVETICA, 12);

        Paragraph title = new Paragraph("Product Voucher", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" ")); // Empty line

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        table.addCell(new Phrase("Item ID", bold));
        table.addCell(new Phrase(String.valueOf(itemId), normal));

        table.addCell(new Phrase("Product Name", bold));
        table.addCell(new Phrase(productName, normal));

        table.addCell(new Phrase("Product Code", bold));
        table.addCell(new Phrase(productCode, normal));

        table.addCell(new Phrase("Category", bold));
        table.addCell(new Phrase(categoryName, normal));

        table.addCell(new Phrase("Purchase Price", bold));
        table.addCell(new Phrase(purchasePrice.toPlainString(), normal));

        table.addCell(new Phrase("Sale Price", bold));
        table.addCell(new Phrase(salePrice.toPlainString(), normal));

        table.addCell(new Phrase("Stock Location", bold));
        table.addCell(new Phrase(stockLocation, normal));

        document.add(table);
        document.close();
    }
}
