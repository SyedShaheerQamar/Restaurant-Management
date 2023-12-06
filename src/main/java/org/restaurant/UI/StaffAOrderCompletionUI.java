package org.restaurant.UI;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.restaurant.Domain.CustomerOrder;
import org.restaurant.Service.OrderCompletionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class StaffAOrderCompletionUI {

    private static Integer count = 0;
    private static String FILE;
    private final OrderCompletionService orderCompletionService = new OrderCompletionService();

    public StaffAOrderCompletionUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout(30, 20));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40));
        JTextField searchtf = new JTextField(40);
        searchPanel.add(searchtf);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        String[][] data = orderCompletionService.getAllCustomers();
        String[] columns = {"ID", "NAME", "PHONE", "EMAIL", "STATUS"};

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
                String[][] data = orderCompletionService.searchByName(searchtf.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, columns);
                jTable.setModel(dtm);
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

        JButton addBtn = new JButton("COMPLETE ORDER");
        JButton billBtn = new JButton("GENERATE BILL");
        JButton backBtn = new JButton("BACK");

        btnPanel.add(addBtn);
        btnPanel.add(billBtn);
        btnPanel.add(backBtn);

        addBtn.addActionListener(b->{
            Integer val = jTable.getSelectedRow();
            if(val == -1){
                JOptionPane.showMessageDialog(frame, "Select a customer first!!!");
            }
            else {
                String id = (String) jTable.getValueAt(val, 0);
                orderCompletionService.customerOrderCompletion(Integer.valueOf(id));
                JOptionPane.showMessageDialog(frame, "Order Completed!!!");
                frame.dispose();
                new StaffAOrderCompletionUI();
            }
        });

        billBtn.addActionListener(b->{
            Integer row = jTable.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(frame, "Select a customer for bill!!!");
            }
            else {
                String status = (String) jTable.getValueAt(row, 4);
                if(status.equalsIgnoreCase("complete")){
                    String id = (String) jTable.getValueAt(row, 0);
                    List<CustomerOrder> customerOrderList = orderCompletionService.getAllItems(Integer.valueOf(id));

                    for(int i=0; i<customerOrderList.size(); i++){
                        JOptionPane.showMessageDialog(frame,
                                orderCompletionService.getMenuName(customerOrderList.get(i).getM_id())
                                        +"  Price : "+customerOrderList.get(i).getItem_price());
                    }

                    String name = (String) jTable.getValueAt(row, 1);
                    String bill = "The total bill of "+name+" is : "+ orderCompletionService.getCustomerBill(Integer.valueOf(id));
                    JOptionPane.showMessageDialog(frame, bill);
                    orderCompletionService.getCustomerBillStatus(Integer.valueOf(id));
                    frame.dispose();
                    new StaffAOrderCompletionUI();

                    count += 1;
                    FILE = "D:/Java/Bill" + count + ".pdf";

                    try {
                        Document doc = new Document();
                        PdfWriter.getInstance(doc, new FileOutputStream(FILE));
                        doc.open();
//                        addMetaData(doc);
                        createTable(doc, jTable, bill, customerOrderList, orderCompletionService);
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
                else {
                    JOptionPane.showMessageDialog(frame, "Order Not Completed!!!");
                }
            }
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new StaffHomeUI();
        });

        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.WEST);
        frame.add(btnPanel, BorderLayout.EAST);

        frame.setSize(900, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private static void addMetaData(Document document) {
        document.addTitle("Bill");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private static void createTable(Document document, JTable jTable, String res, List<CustomerOrder> customerOrderList,OrderCompletionService orderCompletionService) throws DocumentException {
        Anchor anchor = new Anchor("Bill of Customer : "+jTable.getValueAt(0, 1));
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph Para = new Paragraph();
//        Para.add("The report is from "+adate+" till "+ddate);

        Paragraph newLine = new Paragraph();
        addEmptyLine(newLine, 1);

        for(int i=0; i<customerOrderList.size(); i++){
            Para.add(orderCompletionService.getMenuName(customerOrderList.get(i).getM_id())
                    +"  Price : "+customerOrderList.get(i).getItem_price());
            Para.add(newLine);
        }


        catPart.add(newLine);
        catPart.add(Para);
        catPart.add(newLine);
        catPart.add(new Paragraph(res));

        document.add(catPart);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
