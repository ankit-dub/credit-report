package com.example.demo;

import com.lowagie.text.DocumentException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.*;
import java.util.ArrayList;

public class DemoApplication {
	public static void main(String[] args) throws IOException, DocumentException {
		ArrayList<LoanRestructure> loanList= new ArrayList<LoanRestructure>();
		for(int i=0;i<50;i++){
			loanList.add(new LoanRestructure("2400","4564","13","26/7/2019"));
		}
		DemoApplication thymeleaf2Pdf = new DemoApplication();
		String html = thymeleaf2Pdf.parse(loanList);
		thymeleaf2Pdf.generatePdfFromHtml(html);
	}

	public void generatePdfFromHtml(String html) throws IOException, DocumentException {
		String file = "src/main/resources/thymeleaf.pdf";
		OutputStream outputStream = new FileOutputStream(file);

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		renderer.layout();
		renderer.createPDF(outputStream);

		outputStream.close();
	}

	private String parse(ArrayList<LoanRestructure> loanList) {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		Context context = new Context();
		context.setVariable("to", "MoneyTap");
		context.setVariable("loanList",loanList);
		return templateEngine.process("thymeleaf_template", context);
	}
}

