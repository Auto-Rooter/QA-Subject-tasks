package main.java.exercise4;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PdfVerifierTest {

    @Test
    public void readingPublicKeyFromFileShouldReturnAProperKey() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream("receiver_keystore.p12"), "hadi1122".toCharArray());
        Certificate certificate = keyStore.getCertificate("receiverKeyPair");

        PublicKey currentPublicKey = certificate.getPublicKey();
        String publicKeyAlgorithm = currentPublicKey.getAlgorithm();
        String publicKeyFormat = currentPublicKey.getFormat();
        String publicKeyEncoding = Arrays.toString(currentPublicKey.getEncoded());

        PublicKey publicKeyToBeCheck =  new PdfSigner().getPublicKey();

        assertEquals(publicKeyAlgorithm, publicKeyToBeCheck.getAlgorithm());
        assertEquals(publicKeyFormat, publicKeyToBeCheck.getFormat());
        assertEquals(publicKeyEncoding, Arrays.toString(publicKeyToBeCheck.getEncoded()));
    }


    @Test
    public void readingPrivateKeyFromFileShouldReturnAProperKey() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(new FileInputStream("sender_keystore.p12"), "hadi1122".toCharArray());
        PrivateKey currentPrivateKey =  (PrivateKey) keyStore.getKey("senderKeyPair", "hadi1122".toCharArray());


        String privateKeyAlgorithm = currentPrivateKey.getAlgorithm();
        String privateKeyFormat = currentPrivateKey.getFormat();
        String privateKeyEncoding = Arrays.toString(currentPrivateKey.getEncoded());

        PrivateKey privateKeyToBeCheck =  new PdfSigner().getPrivateKey();

        assertEquals(privateKeyAlgorithm, privateKeyToBeCheck.getAlgorithm());
        assertEquals(privateKeyFormat, privateKeyToBeCheck.getFormat());
        assertEquals(privateKeyEncoding, Arrays.toString(privateKeyToBeCheck.getEncoded()));
    }

    @Test
    public void generatePdfFileWithPdfBoxShouldCreatePdfFile(){
        new PdfGenerator();
        assertTrue(new File("UsageReport.pdf").exists());
    }

    @Test
    public void signPdfShouldCreateSignatureFile() throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, InvalidKeyException, SignatureException {
        String signatureFileName = "digital_signature";

        new PdfSigner().signPDF("UsageReport.pdf");
        assertTrue(new File(signatureFileName).exists());
    }

    @Test
    public void verifyPdfShouldReturnTrueIfFileIsValid() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, SignatureException, InvalidKeyException, IOException, UnrecoverableKeyException {
        String fileName = "UsageReport.pdf";
        PdfSigner pdfSigner = new PdfSigner();

        pdfSigner.signPDF(fileName);
        assertTrue(pdfSigner.verifyPDF(fileName));
    }
}
