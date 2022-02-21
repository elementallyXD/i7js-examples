package com.itextpdf.samples.sandbox.typography.malayalam;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.forms.fields.TextFormFieldBuilder;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.licensing.base.LicenseKey;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MalayalamTextFormField {

    public static final String DEST = "./target/sandbox/typography/MalayalamTextFormField.pdf";
    public static final String FONTS_FOLDER = "./src/main/resources/font/";

    public static void main(String[] args) throws Exception {

        // Load the license file to use typography features
        try (FileInputStream license = new FileInputStream(System.getenv("ITEXT7_LICENSEKEY")
                + "/itextkey-typography.json")) {
            LicenseKey.loadLicenseFile(license);
        }

        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new MalayalamTextFormField().createPDF(DEST);
    }

    public void createPDF(String dest) throws IOException {

        // Create a pdf document along with a Document (default root layout element) instance
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(dest));
        Document document = new Document(pdfDocument);

        // സാേങ്കതിക
        String fieldValue = "\u0D38\u0D3E\u0D47\u0D19\u0D4D\u0D15\u0D24\u0D3F\u0D15";

        String fieldName = "Field name";

        PdfAcroForm form = PdfAcroForm.getAcroForm(document.getPdfDocument(), true);

        PdfFont font = PdfFontFactory.createFont(FONTS_FOLDER + "NotoSansMalayalam-Regular.ttf",
                PdfEncodings.IDENTITY_H);

        // Embed entire font without any subsetting. Please note that without subset it's impossible to edit a form field
        // with the predefined font
        font.setSubset(false);

        // Create a form field and set some of the properties
        PdfFormField formField = new TextFormFieldBuilder(document.getPdfDocument(), fieldName)
                .setWidgetRectangle(new Rectangle(50, 750, 160, 25)).createText();
        formField
                .setValue(fieldValue)
                .setBorderWidth(2)
                .setFont(font)
                .setFontSize(10)
                .setJustification(1);

        form.addField(formField);

        document.close();
    }
}


