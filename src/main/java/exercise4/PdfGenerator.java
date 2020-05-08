package main.java.exercise4;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfGenerator {

    private int availableCoresToJVM = Runtime.getRuntime().availableProcessors();
    private String freeMemoryAvailableToJVM = String.valueOf(Runtime.getRuntime().freeMemory())+" Byte";
    private long maxMemoryTemp = Runtime.getRuntime().maxMemory();
    private String maxMemory = (maxMemoryTemp == Long.MAX_VALUE ? "no limit" : String.valueOf(maxMemoryTemp));
    private String totoalMemoryAvailableToJVM = String.valueOf(Runtime.getRuntime().totalMemory());
    private File[] roots = File.listRoots();
    private String currentDateAndTime = "";

    public PdfGenerator(){
        //generatePdfFileWithiText();
        generatePdfFileWithPdfBox();
    }

    private void generatePdfFileWithPdfBox(){
        try(PDDocument doc = new PDDocument()){
            PDPage pdPage = new PDPage();
            doc.addPage(pdPage);

            try(PDPageContentStream cont = new PDPageContentStream(doc, pdPage)){
                cont.beginText();

                cont.setFont(PDType1Font.COURIER, 16);
                cont.setLeading(14.5f);

                cont.newLineAtOffset(25, 700);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                currentDateAndTime = dtf.format(now);


                cont.showText("    ------------ "+currentDateAndTime+" ------------ ");
                cont.newLine();
                cont.newLine();
                cont.newLine();

                cont.showText("Available Cores For JVM: "+availableCoresToJVM);
                cont.newLine();
                cont.newLine();

                cont.showText("Free Memory Available To JVM: "+freeMemoryAvailableToJVM);
                cont.newLine();
                cont.newLine();

                cont.showText("Max Memory: "+maxMemory);
                cont.newLine();
                cont.newLine();

                cont.showText("Totoal Memory Available To JVM: "+totoalMemoryAvailableToJVM);
                cont.newLine();
                cont.newLine();
                cont.newLine();
                cont.newLine();

                for (File root : roots) {
                    cont.showText("Drive: "+root.getAbsolutePath());
                    cont.newLine();
                    cont.newLine();

                    cont.showText("|_____ Total Space: "+String.valueOf(root.getTotalSpace())+" Byte");
                    cont.newLine();
                    cont.newLine();

                    cont.showText("|_____ Free Space: "+String.valueOf(root.getFreeSpace())+" Byte");
                    cont.newLine();
                    cont.newLine();

                    cont.showText("-----------------------------------------------------");
                    cont.newLine();
                    cont.newLine();
                }

                cont.newLine();
                cont.newLine();
                cont.showText("                --- End Of Report ---");
                cont.endText();
            }

            doc.save("UsageReport.pdf");
            //doc.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



//    public void generatePdfFileWithiText() {
//        try{
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream("UsageReport.pdf"));
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//            LocalDateTime now = LocalDateTime.now();
//            currentDateAndTime = dtf.format(now);
//
//            document.open();
//            addMetaData(document);
//
//            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
//            Chunk chunkDate = new Chunk("    ------------ "+currentDateAndTime+" ------------ ", font);
//            Chunk chunkCores = new Chunk("Available Cores For JVM: "+availableCoresToJVM, font);
//            Chunk chunkFreeMemory = new Chunk("Free Memory Available To JVM: "+freeMemoryAvailableToJVM, font);
//            Chunk chunkMaxMemory = new Chunk("Max Memory: "+maxMemory, font);
//            Chunk chunkTotoalMemoryAvailable = new Chunk("Totoal Memory Available To JVM: "+totoalMemoryAvailableToJVM, font);
//            Chunk chunkFileSystemRoot = new Chunk("Max Memory: "+maxMemory, font);
//
//            document.add(chunkDate);
//            document.add(new Paragraph("\n"));
//            document.add(new Paragraph("\n"));
//            document.add(chunkCores);
//            document.add(new Paragraph("\n"));
//            document.add(chunkFreeMemory);
//            document.add(new Paragraph("\n"));
//            document.add(chunkMaxMemory);
//            document.add(new Paragraph("\n"));
//            document.add(chunkTotoalMemoryAvailable);
//            document.add(new Paragraph("\n"));
//            document.add(chunkFileSystemRoot);
//            document.add(new Paragraph("\n"));
//            document.add(new Paragraph("\n"));
//
//            for (File root : roots) {
//                document.add(new Chunk("Drive: "+root.getAbsolutePath(), font)) ;
//                document.add(new Paragraph("\n"));
//                document.add(new Chunk("|_____ Total Space: "+String.valueOf(root.getTotalSpace())+" Byte", font)) ;
//                document.add(new Paragraph("\n"));
//                document.add(new Chunk("|_____ Free Space: "+String.valueOf(root.getFreeSpace())+" Byte", font)) ;
//                document.add(new Paragraph("\n"));
//                document.add(new Chunk("-----------------------------------------------------", font));
//                document.add(new Paragraph("\n"));
//                document.add(new Paragraph("\n"));
//            }
//
//            document.add(new Chunk("                --- End Of Report ---", font));
//
//            document.close();
//
//            System.out.println("[*] Report File generated Successfully ......");
//        }
//        catch(FileNotFoundException | DocumentException e){
//            System.out.print(e);
//        }
//
//    }
//
//
//    private void addMetaData(Document document) {
//        document.addTitle("Report Generated By iTextPDF");
//        document.addSubject("Usage Report");
//        document.addKeywords("Usage, Report, iText");
//        document.addAuthor("Hadi Assalem");
//        document.addCreator("Hadi Assalem");
//    }






}
