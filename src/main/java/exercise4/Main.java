package main.java.exercise4;

        import java.io.IOException;
        import java.security.*;
        import java.security.cert.CertificateException;

public class Main {

    public static void main(String[] args) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, SignatureException, InvalidKeyException, IOException {

        PdfGenerator pdfGenerator = new PdfGenerator();
        PdfSigner pdfSigner = new PdfSigner();

        pdfSigner.signPDF("UsageReport.pdf");
        System.out.println(pdfSigner.verifyPDF("UsageReport.pdf") ? "[*] PDF is valid ...." : "[!] PDF is not valid ....");
    }
}

