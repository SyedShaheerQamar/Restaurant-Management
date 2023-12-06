package org.restaurant.UI;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.restaurant.Service.ProfitService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfitUI {

    static Integer count = 0;
    static String FILE;
    private final ProfitService profitService = new ProfitService();

    public ProfitUI(){
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout(30, 20));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40));
        JTextField searchtf = new JTextField(40);
        searchPanel.add(searchtf);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        String[][] data = profitService.getAllValuesOfCustomerOrder();
        String[] columns = {"MENU", "CUSTOMER", "STAFF", "ITEM PRICE", "ORDER DATE"};

        DefaultTableModel dtm = new DefaultTableModel(data, columns);

        JTable jTable = new JTable(dtm);
        JScrollPane sp = new JScrollPane(jTable);
        sp.setPreferredSize(new Dimension(450, 150));
        tablePanel.add(sp);

        searchtf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String[][] data = profitService.searchByName(searchtf.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, columns);
                jTable.setModel(dtm);
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));

        JButton addBtn = new JButton("FIND PROFIT");
        JButton backBtn = new JButton("BACK");

        btnPanel.add(addBtn);
        btnPanel.add(backBtn);

        addBtn.addActionListener(b->{
            if(jTable.getRowCount() == 0){
                JOptionPane.showMessageDialog(frame, "No report available!!!");
            }
            else {
                Integer cost = profitService.getTotalMakingCost();
                Integer total = profitService.getDailyReportBill();
                Integer profit = total - cost;
                String rev = "The total daily revenue is : " + total;
                String making = "The total cost of making menu item is : "+cost;
                String prf = "The total profit of today is : "+profit;
                JOptionPane.showMessageDialog(frame, rev);
                JOptionPane.showMessageDialog(frame, making);
                JOptionPane.showMessageDialog(frame, prf);

                count += 1;
                FILE = "D:/Java/ProfitReport" + count + ".pdf";
                try {
                    Document doc = new Document();
                    PdfWriter.getInstance(doc, new FileOutputStream(FILE));
                    doc.open();
                    addMetaData(doc);
                    createTable(doc, jTable, rev, making, prf);
                    doc.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                File file = new File(FILE);
                if (file.toString().endsWith(".pdf")) {
                    try {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.open(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new AdminHomeUI();
        });

        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.WEST);
        frame.add(btnPanel, BorderLayout.EAST);

        frame.setSize(850, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
    private static void addMetaData(Document document) {
        document.addTitle("Monthly Report");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private static void createTable(Document document, JTable jTable, String res, String mak, String prf) throws DocumentException {
        Anchor anchor = new Anchor("Profit Report of Restaurant : Shaheen Shinwari");
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph Para = new Paragraph();
        Para.add("The report is of "+jTable.getValueAt(0, 4));

        Paragraph nePara = new Paragraph();
        addEmptyLine(nePara, 1);

        PdfPTable table = new PdfPTable(4);

        PdfPCell c1 = new PdfPCell(new Phrase("MENU"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("CUSTOMER"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("STAFF"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("PRICE"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);
        table.setHeaderRows(1);

        for(int i=0; i<jTable.getRowCount(); i++){
            table.addCell((String) jTable.getValueAt(i, 0));
            table.addCell((String) jTable.getValueAt(i, 1));
            table.addCell((String) jTable.getValueAt(i, 2));
            table.addCell((String) jTable.getValueAt(i, 3));
        }

        catPart.add(Para);
        catPart.add(nePara);
        catPart.add(table);
        catPart.add(nePara);
        catPart.add(new Paragraph(res));
        catPart.add(nePara);
        catPart.add(new Paragraph(mak));
        catPart.add(nePara);
        catPart.add(new Paragraph(prf));

        document.add(catPart);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
