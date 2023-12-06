package org.restaurant.UI;

import org.restaurant.Service.ReservationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StaffReservationUI {

    private final ReservationService reservationService = new ReservationService();

    public StaffReservationUI(){
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout(30, 20));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40));
        JTextField searchtf = new JTextField(40);
        searchPanel.add(searchtf);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        String[][] data = reservationService.getAllValuesOfReservation();
        String[] columns = {"RES NO.", "DATE", "TIME", "CUSTOMER", "TABLE NO.", "RESTAURANT", "TOKEN"};

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
                String[][] data = reservationService.searchByName(searchtf.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, columns);
                jTable.setModel(dtm);
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));

        JButton addBtn = new JButton("ADD");
        JButton updateBtn = new JButton("UPDATE");
        JButton deleteBtn = new JButton("DELETE");
        JButton backBtn = new JButton("BACK");

        btnPanel.add(addBtn);
//        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(backBtn);

        addBtn.addActionListener(b->{
            new StaffAddReservationUI();
            frame.dispose();
        });

        updateBtn.addActionListener(b->{
            Integer val = jTable.getSelectedRow();

            if(val == -1){
                JOptionPane.showMessageDialog(frame, "Select a row you want to update!!!");
            }
            else {
                String id = (String) jTable.getValueAt(val, 0);
//                new UpdateReservationUI(Integer.valueOf(id));
                frame.dispose();
            }
        });

        deleteBtn.addActionListener(b->{
            Integer val = jTable.getSelectedRow();

            if(val == -1){
                JOptionPane.showMessageDialog(frame, "Select a row to delete!!!");
            }
            else {
                String id = (String) jTable.getValueAt(val, 0);
                String t_id = (String) jTable.getValueAt(val, 4);
                reservationService.deleteById(Integer.valueOf(id), Integer.valueOf(t_id));
                frame.dispose();
                new StaffReservationUI();
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
}
