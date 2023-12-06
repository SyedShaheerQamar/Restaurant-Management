package org.restaurant.UI;

import org.restaurant.Service.TakeOrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TakeOrderUI {

    private final TakeOrderService takeOrderService = new TakeOrderService();

    public TakeOrderUI(){
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout(30, 20));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40));
        JTextField searchtf = new JTextField(40);
        searchPanel.add(searchtf);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        String[][] data = takeOrderService.getAllValuesOfCustomerOrder();
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
                String[][] data = takeOrderService.searchByName(searchtf.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, columns);
                jTable.setModel(dtm);
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));

        JButton addBtn = new JButton("ADD");
        JButton deleteBtn = new JButton("DELETE");
        JButton backBtn = new JButton("BACK");

        btnPanel.add(addBtn);
//        btnPanel.add(deleteBtn);
        btnPanel.add(backBtn);

        addBtn.addActionListener(b->{
            String[] val = takeOrderService.customerDropDown();
            if(val.length == 0){
                JOptionPane.showMessageDialog(frame, "No Customer!!!");
            }
            else {
                new AddTakeOrderUI();
                frame.dispose();
            }
        });

        deleteBtn.addActionListener(b->{
            Integer val = jTable.getSelectedRow();

            if(val == -1){
                JOptionPane.showMessageDialog(frame, "Select a row to delete!!!");
            }
            else {
                String m = (String) jTable.getValueAt(val, 0);
                String c = (String) jTable.getValueAt(val, 1);
                String u = (String) jTable.getValueAt(val, 2);
                takeOrderService.deleteById(m ,c, u);
                frame.dispose();
                new ReservationUI();
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
}
