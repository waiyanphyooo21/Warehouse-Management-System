//package com.warehouse.controllers;
//
//import com.lowagie.text.Document;
//import com.lowagie.text.Element;
//import com.lowagie.text.Font;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.Phrase;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//import com.warehouse.beans.Product;
//import com.warehouse.service.ProductService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Controller
//@RequestMapping("/voucher")
//public class VoucherController {
//
//    private final ProductService productService;
//
//    public VoucherController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    // Show list of products as vouchers
//    @GetMapping("/list")
//    public String showVoucherList(Model model, HttpSession session) {
//        if (session.getAttribute("loggedInUser") == null) {
//            return "redirect:/login";
//        }
//        model.addAttribute("products", productService.getAllProducts());
//        return "voucher"; // your JSP page name
//    }
//
//    @GetMapping("/pdf/{id}")
//    public void downloadVoucherPdf(@PathVariable("id") int productId, HttpServletResponse response) throws IOException {
//        Product product = productService.getProductById(productId);
//        if (product == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
//            return;
//        }
//
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=voucher_" + productId + ".pdf");
//
//        try {
//            Document document = new Document();
//            PdfWriter.getInstance(document, response.getOutputStream());
//            document.open();
//
//            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
//            Font labelFont = new Font(Font.HELVETICA, 12, Font.BOLD);
//            Font valueFont = new Font(Font.HELVETICA, 12);
//
//            Paragraph title = new Paragraph("Product Voucher", titleFont);
//            title.setAlignment(Element.ALIGN_CENTER);
//            document.add(title);
//            document.add(new Paragraph(" "));
//
//            PdfPTable table = new PdfPTable(2);
//            table.setWidthPercentage(100);
//
//            table.addCell(new Phrase("Product ID:", labelFont));
//            table.addCell(new Phrase(String.valueOf(product.getProductId()), valueFont));
//
//            table.addCell(new Phrase("Product Name:", labelFont));
//            table.addCell(new Phrase(product.getProductName(), valueFont));
//
//            table.addCell(new Phrase("Category:", labelFont));
//            String categoryName = product.getCategory() != null ? product.getCategory().getCategoryName() : "-";
//            table.addCell(new Phrase(categoryName, valueFont));
//
//            table.addCell(new Phrase("Purchase Price:", labelFont));
//            String purchasePrice = product.getPurchasePrice() != null ? product.getPurchasePrice().toPlainString() : "-";
//            table.addCell(new Phrase(purchasePrice, valueFont));
//
//            table.addCell(new Phrase("Purchase Date:", labelFont));
//            // Assuming your Product bean has getCreatedAt() as purchase date
//            String purchaseDate = product.getCreatedAt() != null ? product.getCreatedAt().toString() : "-";
//            table.addCell(new Phrase(purchaseDate, valueFont));
//
//            document.add(table);
//            document.close();
//
//        } catch (Exception e) {
//            throw new IOException("Failed to generate PDF", e);
//        }
//    }
//
//}

package com.warehouse.controllers;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.warehouse.beans.Product;
import com.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/voucher")
public class VoucherController {

    private final ProductService productService;

    @Autowired
    private ServletContext servletContext;

    public VoucherController(ProductService productService) {
        this.productService = productService;
    }

    // Show list of products as vouchers
    @GetMapping("/list")
    public String showVoucherList(Model model, HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        model.addAttribute("products", productService.getAllProducts());
        return "voucher"; // your JSP page name
    }

    @GetMapping("/pdf/{id}")
    public void downloadVoucherPdf(@PathVariable("id") int productId, HttpServletResponse response) throws IOException {
        Product product = productService.getProductById(productId);
        if (product == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=voucher_" + productId + ".pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            // Add logo image here
            try {
                String logoPath = servletContext.getRealPath("/resources/images/npg.png");
                Image logo = Image.getInstance(logoPath);
                logo.scaleToFit(120, 60);  // Adjust size as needed
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
                document.add(new Paragraph(" "));  // space after logo
            } catch (Exception e) {
                System.err.println("Could not load logo image: " + e.getMessage());
            }

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font labelFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font valueFont = new Font(Font.HELVETICA, 12);

            Paragraph title = new Paragraph("Product Voucher", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            table.addCell(new Phrase("Product ID:", labelFont));
            table.addCell(new Phrase(String.valueOf(product.getProductId()), valueFont));

            table.addCell(new Phrase("Product Name:", labelFont));
            table.addCell(new Phrase(product.getProductName(), valueFont));

            table.addCell(new Phrase("Category:", labelFont));
            String categoryName = product.getCategory() != null ? product.getCategory().getCategoryName() : "-";
            table.addCell(new Phrase(categoryName, valueFont));

            table.addCell(new Phrase("Purchase Price:", labelFont));
            String purchasePrice = product.getPurchasePrice() != null ? product.getPurchasePrice().toPlainString() : "-";
            table.addCell(new Phrase(purchasePrice, valueFont));

//            table.addCell(new Phrase("Purchase Date:", labelFont));
//            // Assuming your Product bean has getCreatedAt() as purchase date
//            String purchaseDate = product.getCreatedAt() != null ? product.getCreatedAt().toString() : "-";
//            table.addCell(new Phrase(purchaseDate, valueFont));
            table.addCell(new Phrase("Purchase Date:", labelFont));
            String purchaseDate = "-";
            if (product.getCreatedAt() != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                purchaseDate = sdf.format(product.getCreatedAt());
            }
            table.addCell(new Phrase(purchaseDate, valueFont));


            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new IOException("Failed to generate PDF", e);
        }
    }

}
