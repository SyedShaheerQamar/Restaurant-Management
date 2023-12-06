package org.restaurant.UI;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import jdk.nashorn.internal.scripts.JO;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.restaurant.DAO.ReservationDAO;
import org.restaurant.DAO.TableDAO;
import org.restaurant.Domain.Reservation;
import org.restaurant.Domain.Tables;
import org.restaurant.Service.ReservationService;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

public class AddReservationUI {

    static Integer count = 0;
    static String FILE;
    private final ReservationService reservationService = new ReservationService();

    public AddReservationUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 40));

        JLabel datelb = new JLabel("ENTER DATE");

        UtilDateModel model = new UtilDateModel();
        model.setDate(model.getYear(), model.getMonth(), model.getDay());
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        JLabel hourlb = new JLabel("ENTER TIME");
        String[] data = {"17","18","19","20","21","22","23"};
        JComboBox hourBox = new JComboBox<>(data);
        hourBox.setPreferredSize(new Dimension(60, 30));

        String[] data2 = {"00", "15", "30", "45"};
        JComboBox minBox = new JComboBox<>(data2);
        minBox.setPreferredSize(new Dimension(60, 30));

        JLabel tablelb = new JLabel("SELECT TABLE NO.");
        String[] data4 = reservationService.getTableValue();
        JComboBox tabBox = new JComboBox<>(data4);
        tabBox.setPreferredSize(new Dimension(180, 30));

        JLabel cuslb = new JLabel("SELECT CUSTOMER");
        String[] data3 = reservationService.getCustomerValues();
        JComboBox cusBox = new JComboBox<>(data3);
        cusBox.setPreferredSize(new Dimension(100, 30));

        JButton cusBtn = new JButton("ADD");

        JButton addBtn = new JButton("ADD");
        JButton backBtn = new JButton("BACK");
        addBtn.setPreferredSize(new Dimension(100, 35));
        backBtn.setPreferredSize(new Dimension(100, 35));

        cusBtn.addActionListener(b->{
            frame.dispose();
            new AddCustomerUI();
        });

        LocalDate now = LocalDate.now();

        addBtn.addActionListener(b->{
            LocalDate date = LocalDate.of(model.getYear(), (model.getMonth()+1), model.getDay());
            String hrs = (String) hourBox.getSelectedItem();
            String mins = (String) minBox.getSelectedItem();

            String time = hrs+":"+mins+":00";
            String date_value = String.valueOf(date);
            LocalTime res_time = LocalTime.parse(time);

            if(date.isBefore(now)){
                JOptionPane.showMessageDialog(frame, "Pick a valid date!!!");
            }
            else if(date.isEqual(now)){
                if(res_time.isBefore(LocalTime.now())){
                    JOptionPane.showMessageDialog(frame, "Pick a valid time");
                }
                else {
                    String cus = (String) cusBox.getSelectedItem();
                    String[] c_value = cus.split(",");
                    Integer c_id = Integer.valueOf(c_value[0]);

                    String table = (String) tabBox.getSelectedItem();
                    String[] t_value = table.split(",");
                    Integer t_id = Integer.valueOf(t_value[0]);

                    TableDAO dao = new TableDAO();
                    Tables tables = dao.getById(t_id.longValue());

                    ReservationDAO rDao = new ReservationDAO();
                    List<Reservation> rList = rDao.getAllByDate();

                    Boolean check = true;

                    for (Reservation r : rList){
                        if(c_id == r.getC_id()){
                            check = false;
                        }
                    }

                    if(check){
                        if(tables.getAvailability().equalsIgnoreCase("open")){
                            reservationService.insertValuesIntoReservation(date_value, time, c_id, t_id);
                            reservationService.updateCustomerStatus(c_id);
                            JOptionPane.showMessageDialog(frame, "Reservation Added!!!");

                            String[] options = {"Yes", "No"};
                            int res = JOptionPane.showOptionDialog(new JFrame(), "Open reservation pdf?", "Reservations",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    options, JOptionPane.YES_OPTION);

                            if (res == JOptionPane.YES_OPTION) {
                                count += 1;
                                FILE = "D:/Java/Reservation" + count + ".pdf";
                                try {
                                    Document doc = new Document();
                                    PdfWriter.getInstance(doc, new FileOutputStream(FILE));
                                    doc.open();
                                    addMetaData(doc);
                                    createTable(doc, c_value[1], Integer.valueOf(t_value[0]), date_value, time);
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

                                frame.dispose();
                                new ReservationUI();
                            } else if (res == JOptionPane.NO_OPTION) {
                                frame.dispose();
                                new ReservationUI();
                            }
                        }
                        else if(tables.getAvailability().equalsIgnoreCase("booked")){
                            ReservationDAO reservationDAO = new ReservationDAO();
                            List<Reservation> reservationList = reservationDAO.getAllByTableId(t_id);
                            Boolean flag = true;

                            for(Reservation rs : reservationList){
                                LocalDate rsDate = LocalDate.parse(rs.getResDate());
                                LocalTime rsTime = LocalTime.parse(rs.getResTime());

                                if(date.isEqual(rsDate) && res_time.equals(rsTime)){
                                    flag = false;
                                }
                            }

                            if(flag) {
                                JOptionPane.showMessageDialog(frame, "Already Booked!!! Generating Token!!!");
                                String token_value = "";
                                Integer token = 0;
                                for(Reservation rs : reservationList){
                                    token_value = String.valueOf(rs.getToken());
                                }

                                if(Integer.valueOf(token_value)>=0){
                                    Integer temp_token = 0;
                                    Integer last_token = 0;
                                    Reservation res = null;

                                    for(Reservation rs : reservationList){
                                        LocalTime rsTime = LocalTime.parse(rs.getResTime());
                                        if(res_time.isBefore(rsTime)){
                                            res = rs;
                                        }
                                        last_token = rs.getToken();
                                    }

    //                                LocalTime rtime = LocalTime.parse(res.getResTime());

                                    if(res == null){
                                        reservationService.insertIntoReservationToken(date_value, time, c_id, t_id, last_token+1);
                                        reservationService.updateCustomerStatus(c_id);
                                        JOptionPane.showMessageDialog(frame, "Reservation Added!!!");
                                        String[] options = {"Yes", "No"};
                                        int res1 = JOptionPane.showOptionDialog(new JFrame(), "Open reservation pdf?", "Reservations",
                                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                                options, JOptionPane.YES_OPTION);

                                        if (res1 == JOptionPane.YES_OPTION) {
                                            count += 1;
                                            FILE = "D:/Java/Reservation" + count + ".pdf";
                                            try {
                                                Document doc = new Document();
                                                PdfWriter.getInstance(doc, new FileOutputStream(FILE));
                                                doc.open();
                                                addMetaData(doc);
                                                createTable(doc, c_value[1], Integer.valueOf(t_value[0]), date_value, time);
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

                                            frame.dispose();
                                            new ReservationUI();
                                        } else if (res1 == JOptionPane.NO_OPTION) {
                                            frame.dispose();
                                            new ReservationUI();
                                        }
                                        frame.dispose();
                                        new ReservationUI();
                                    }
                                    else if(res_time.compareTo(LocalTime.parse(res.getResTime())) >= 1 || res_time.compareTo(LocalTime.parse(res.getResTime())) <= -1){
                                        temp_token = res.getToken();
                                        res.setToken(temp_token + 1);
                                        reservationDAO.update(res, Long.valueOf(res.getId()));
                                        reservationService.insertIntoReservationToken(date_value, time, c_id, t_id, temp_token);
                                        reservationService.updateCustomerStatus(c_id);
                                        JOptionPane.showMessageDialog(frame, "Reservation Added!!!");
                                        String[] options = {"Yes", "No"};
                                        int res2 = JOptionPane.showOptionDialog(new JFrame(), "Open reservation pdf?", "Reservations",
                                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                                options, JOptionPane.YES_OPTION);

                                        if (res2 == JOptionPane.YES_OPTION) {
                                            count += 1;
                                            FILE = "D:/Java/Reservation" + count + ".pdf";
                                            try {
                                                Document doc = new Document();
                                                PdfWriter.getInstance(doc, new FileOutputStream(FILE));
                                                doc.open();
                                                addMetaData(doc);
                                                createTable(doc, c_value[1], Integer.valueOf(t_value[0]), date_value, time);
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

                                            frame.dispose();
                                            new ReservationUI();
                                        } else if (res2 == JOptionPane.NO_OPTION) {
                                            frame.dispose();
                                            new ReservationUI();
                                        }
                                        frame.dispose();
                                        new ReservationUI();
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(frame, "Select a diff time!!");
                                    }
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(frame, "Already Reserved!!! Select another date or time!!!");
                            }
                        }

                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Customer already has a reservation for today!!!");
                    }
                }
            }
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new ReservationUI();
        });

        frame.add(datelb);
        frame.add(datePicker);
        frame.add(hourlb);
        frame.add(hourBox);
        frame.add(minBox);
        frame.add(tablelb);
        frame.add(tabBox);
        frame.add(cuslb);
        frame.add(cusBox);
        frame.add(cusBtn);
        frame.add(addBtn);
        frame.add(backBtn);

        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private static void addMetaData(Document document) {
        document.addTitle("Reservation PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private static void createTable(Document document, String cus, Integer table, String date, String time) throws DocumentException {
        Anchor anchor = new Anchor("Reservation of : "+cus);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph nePara = new Paragraph();
        addEmptyLine(nePara, 1);

        catPart.add(nePara);
        catPart.add(new Paragraph("Table No : "+table));
        catPart.add(nePara);
        catPart.add(new Paragraph("Customer Name : "+cus));
        catPart.add(nePara);
        catPart.add(new Paragraph("Date : "+date));
        catPart.add(nePara);
        catPart.add(new Paragraph("Time : "+time));

        document.add(catPart);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
