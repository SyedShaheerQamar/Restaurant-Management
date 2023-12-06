package org.restaurant.UI;

import org.restaurant.Service.ReservationService;
import org.restaurant.Service.TableService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ShowAllReservationsUI {

    private final ReservationService reservationService = new ReservationService();

    public ShowAllReservationsUI(Integer id){
        JFrame frame = new JFrame();

        frame.setLayout(new BorderLayout(30, 20));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40));
        JTextField searchtf = new JTextField(40);
        searchPanel.add(searchtf);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        String[][] data = reservationService.getAllReservationsForTable(id);
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


        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.WEST);


        frame.setSize(500, 400);
        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }
}
