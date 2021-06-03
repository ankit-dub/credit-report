package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DemoApplication {
    private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) throws IOException, DocumentException {

        DemoApplication thymeleaf2Pdf = new DemoApplication();
        Map<String, Object> map = thymeleaf2Pdf.parseJson();
        String html = thymeleaf2Pdf.parseThymleaf(map);
        thymeleaf2Pdf.generatePdfFromHtml(html);
    }

    public void generatePdfFromHtml(String html) throws IOException, DocumentException {
        String file = "src/main/resources/cibilReport.pdf";
        OutputStream outputStream = new FileOutputStream(file);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

    private String parseThymleaf(Map<String, Object> map) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        ObjectMapper mapper = new ObjectMapper();
        ArrayList scoreList = (ArrayList) map.get("Score");
        Score score = mapper.convertValue(scoreList.get(0), new TypeReference<Score>() {
        });

        ArrayList customers = (ArrayList) map.get("Consumer Information");
        Customer customer = mapper.convertValue(customers.get(0), new TypeReference<Customer>() {
        });

        ArrayList<Address> addressList = mapper.convertValue(map.get("Address"), new TypeReference<ArrayList<Address>>() {
        });

        ArrayList<Identification> identifications = mapper.convertValue(map.get("Identification"), new TypeReference<ArrayList<Identification>>() {
        });

        ArrayList<Email> emails = mapper.convertValue(map.get("Email Address"), new TypeReference<ArrayList<Email>>() {
        });

        ArrayList<Enquiry> enquiries = mapper.convertValue(map.get("Inquiry"), new TypeReference<ArrayList<Enquiry>>() {
        });

        Context context = new Context();
        context.setVariable("score", score.getScore());
        context.setVariable("addressList", addressList);
        context.setVariable("identifications", identifications);
        context.setVariable("emails", emails);
        context.setVariable("enquiries", enquiries);
        context.setVariable("customer", customer);
        return templateEngine.process("cibil_report", context);
    }

    private Map<String, Object> parseJson() {
        String JSON_FILE = "details1.json";
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(JSON_FILE);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Map<String, Object> map = mapper.readValue(inputStream, new TypeReference<HashMap<String, Object>>() {
            });
            LOG.info("Parsed successfully");
            return map;
        } catch (IOException e) {
            LOG.error("Unable to parse");
        }
        return null;
    }
}

