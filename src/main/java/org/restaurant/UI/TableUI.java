package org.restaurant.UI;

import org.restaurant.Service.ReservationService;
import org.restaurant.Service.TableService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TableUI {

    private final TableService tableService = new TableService();

    public TableUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout(30, 20));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40));
        JTextField searchtf = new JTextField(40);
        searchPanel.add(searchtf);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        String[][] data = tableService.getAllValuesOfTable();
        String[] columns = {"TABLE NO.", "CAPACITY", "AVAILABILITY", "RESTAURANT"};

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
                String[][] data = tableService.searchByName(searchtf.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, columns);
                jTable.setModel(dtm);
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

        JButton addBtn = new JButton("ADD");
        JButton updateBtn = new JButton("UPDATE");
        JButton deleteBtn = new JButton("DELETE");
        JButton backBtn = new JButton("BACK");
        JButton showBtn = new JButton("SHOW RESERVATION");

        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(backBtn);
        btnPanel.add(showBtn);

        addBtn.addActionListener(b->{
            new AddTableUI();
            frame.dispose();
        });

        updateBtn.addActionListener(b->{
            Integer val = jTable.getSelectedRow();

            if(val == -1){
                JOptionPane.showMessageDialog(frame, "Select a row you want to update!!!");
            }
            else {
                String id = (String) jTable.getValueAt(val, 0);
                new UpdateTableUI(Integer.valueOf(id));
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
                tableService.deleteById(Integer.valueOf(id));
                frame.dispose();
                new TableUI();
            }
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new AdminHomeUI();
        });

        showBtn.addActionListener(b->{
            Integer val = jTable.getSelectedRow();

            if(val == -1){
                JOptionPane.showMessageDialog(frame, "Select a row!!!");
            }
            else {
                String id = (String) jTable.getValueAt(val, 0);
                String status = (String) jTable.getValueAt(val, 2);

                String[] options = {"Show All Reservation", "Show Today's Reservation"};
                int res = JOptionPane.showOptionDialog(new JFrame(), "Which would you like to see?", "Table Reservations",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        options, JOptionPane.YES_OPTION);

                ReservationService reservationService = new ReservationService();
                String[][] op1 = reservationService.getAllReservationsForTable(Integer.valueOf(id));
                String[][] op2 = reservationService.getTodaysReservationsForTable(Integer.valueOf(id));

                if (res == JOptionPane.YES_OPTION) {
                    if(op1.length == 0){
                        JOptionPane.showMessageDialog(frame, "No Reservations!!!");
                    }
                    else {
                        new ShowAllReservationsUI(Integer.valueOf(id));
                    }
                } else if (res == JOptionPane.NO_OPTION) {
                    if(op2.length == 0){
                        JOptionPane.showMessageDialog(frame, "No Reservations For Today!!!");
                    }
                    else {
                        new ShowTodaysReservationsUI(Integer.valueOf(id));
                    }
                }

            }
        });

        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.WEST);
        frame.add(btnPanel, BorderLayout.EAST);

        frame.setSize(1000, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
