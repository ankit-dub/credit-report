package com.example.demo;

import com.lowagie.text.DocumentException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AccountOpening {
    public static void main(String[] args) throws IOException, DocumentException {

        AccountOpening thymeleaf2Pdf = new AccountOpening();
        String html = thymeleaf2Pdf.parseThymleaf();
        thymeleaf2Pdf.generatePdfFromHtml(html);
    }

    public void generatePdfFromHtml(String html) throws IOException, DocumentException {
        String file = "src/main/resources/accOpeningPdf.pdf";
        OutputStream outputStream = new FileOutputStream(file);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

    private String parseThymleaf() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Accnt account = new Accnt("ankit", "product", "SKY BRANCH", "100007249823", "mode", "7890987");
        Customer customer = new Customer("38/2663A,AVITTAMPILLY HOUSE\n" +
                "FREEMASONS LANE, FREEMASONS HALL, NEAR GOVT.GUEST\n" +
                "HOUSE, WEST HILLWEST HILL S.O-CALICUT, Kerala 673005","2,50,000 Rs - 5,00,000 Rs","AIQPA0153L");
        Context context = new Context();
        context.setVariable("applicationNo", "245899748949999");
        context.setVariable("account", account);
        context.setVariable("customer",customer);
        context.setVariable("nominee_address", "38/2663A,AVITTAMPILLY HOUSE\n" +
                "FREEMASONS LANE, FREEMASONS HALL, NEAR GOVT.GUEST\n" +
                "HOUSE, WEST HILLWEST HILL S.O-CALICUT, Kerala 673005");
        context.setVariable("nominee_relationship", "1234567");
        context.setVariable("nominee_dateOfBirth", "1234567");
        context.setVariable("nominee_name", "1234567");
        context.setVariable("nominee_required", "1234567");
        context.setVariable("date", ZonedDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd,yyyy, hh:mm a")));
        return templateEngine.process("templates/AccOpeningForm", context);
    }

}
