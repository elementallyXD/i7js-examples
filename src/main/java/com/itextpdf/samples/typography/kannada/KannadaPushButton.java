/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */

package com.itextpdf.samples.typography.kannada;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.licensekey.LicenseKey;

import java.io.File;
import java.io.IOException;

public class KannadaPushButton {

    public static final String DEST = "./target/samples/typography/KannadaPushButton.pdf";
    public static final String FONTS_FOLDER = "./src/main/java/com/itextpdf/samples/typography/kannada/resources/";

    public static void main(String[] args) throws Exception {

        // Load the license file to use typography features
        LicenseKey.loadLicenseFile(System.getenv("ITEXT7_LICENSEKEY") + "/itextkey-typography.xml");

        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new KannadaPushButton().createPDF(DEST);
    }

    public void createPDF(String dest) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(dest));

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, true);

        PdfFont font = PdfFontFactory.createFont(FONTS_FOLDER + "NotoSansKannada-Regular.ttf",
                PdfEncodings.IDENTITY_H);

        // Embed entire font without any subsetting. Please note that without subset it's impossible to edit a form field
        // with the predefined font
        font.setSubset(false);

        // ನನ್ನನ್ನು ತಳ್ಳು
        String text = "\u0CA8\u0CA8\u0CCD\u0CA8\u0CA8\u0CCD\u0CA8\u0CC1\u0020\u0CA4\u0CB3\u0CCD\u0CB3\u0CC1";

        Rectangle rect = new Rectangle(50, 650, 120, 25);

        // Create a button for the form field, set its font and size
        PdfButtonFormField pushButton = PdfFormField.createPushButton(pdfDocument, rect, "Name", text);
        pushButton
                .setFont(font)
                .setFontSize(10);

        // Add the button to the form
        form.addField(pushButton);

        pdfDocument.close();
    }
}
