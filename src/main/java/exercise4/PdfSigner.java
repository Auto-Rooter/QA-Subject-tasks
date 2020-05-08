package main.java.exercise4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

public class PdfSigner {
    private static final String STORE_TYPE = "PKCS12";
    private static final char[] PASSWORD = "hadi1122".toCharArray();
    private static final String SENDER_KEYSTORE = "sender_keystore.p12";
    private static final String SENDER_ALIAS = "senderKeyPair";

    public static final String SIGNING_ALGORITHM = "SHA256withRSA";

    private static final String RECEIVER_KEYSTORE = "receiver_keystore.p12";
    private static final String RECEIVER_ALIAS = "receiverKeyPair";


    public PrivateKey getPrivateKey() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance(STORE_TYPE);
        keyStore.load(new FileInputStream(SENDER_KEYSTORE), PASSWORD);
        return (PrivateKey) keyStore.getKey(SENDER_ALIAS, PASSWORD);
    }

    public PublicKey getPublicKey() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance(STORE_TYPE);
        keyStore.load(new FileInputStream(RECEIVER_KEYSTORE), PASSWORD);
        Certificate certificate = keyStore.getCertificate(RECEIVER_ALIAS);
        return certificate.getPublicKey();

    }

    public void signPDF(String fileName) throws UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, InvalidKeyException, SignatureException {
        PrivateKey privateKey = getPrivateKey();

        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initSign(privateKey);

        byte[] pdfToByte = Files.readAllBytes(Paths.get(fileName));

        signature.update(pdfToByte);

        byte[] digitalSignature = signature.sign();

        Files.write(Paths.get("digital_signature"), digitalSignature);
    }

    public Boolean verifyPDF(String fileName) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, InvalidKeyException, SignatureException {
        PublicKey publicKey = getPublicKey();

        byte[] signature = Files.readAllBytes(Paths.get("digital_signature"));

        Signature signatureFromFile = Signature.getInstance(SIGNING_ALGORITHM);
        signatureFromFile.initVerify(publicKey);

        byte[] pdfAsByte = Files.readAllBytes(Paths.get(fileName));

        signatureFromFile.update(pdfAsByte);

        return signatureFromFile.verify(signature) ;
    }
}
