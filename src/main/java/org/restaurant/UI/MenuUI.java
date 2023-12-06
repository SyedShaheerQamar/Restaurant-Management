package org.restaurant.UI;

import org.restaurant.Service.MenuService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuUI {

    private final MenuService menuAuthenticationService = new MenuService();

    public MenuUI(){
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout(30, 20));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40));
        JTextField searchtf = new JTextField(40);
        searchPanel.add(searchtf);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        String[][] data = menuAuthenticationService.getAllValuesOfMenu();
        String[] columns = {"ITEM NO.", "NAME", "CATEGORY", "RESTAURANT", "M_COST"};

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
                String[][] data = menuAuthenticationService.searchByName(searchtf.getText());
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

        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(backBtn);

        addBtn.addActionListener(b->{
            new AddMenuUI();
            frame.dispose();
        });

        updateBtn.addActionListener(b->{
            Integer val = jTable.getSelectedRow();

            if(val == -1){
                JOptionPane.showMessageDialog(frame, "Select a row you want to update!!!");
            }
            else {
                String id = (String) jTable.getValueAt(val, 0);
                new UpdateMenuUI(Integer.valueOf(id));
                frame.dispose();
            }
        });

        deleteBtn.addActionListener(b->{
            Integer val = jTable.getSelectedRow();
            String id = (String) jTable.getValueAt(val, 0);

            if(val == -1){
                JOptionPane.showMessageDialog(frame, "Select a row to delete!!!");
            }
            else {
                menuAuthenticationService.deleteById(Integer.valueOf(id));
                frame.dispose();
                new MenuUI();
            }
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new AdminHomeUI();
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
