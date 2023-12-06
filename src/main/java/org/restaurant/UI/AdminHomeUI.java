package org.restaurant.UI;

import javax.swing.*;
import java.awt.*;

public class AdminHomeUI {

    public AdminHomeUI(){
        JFrame frame = new JFrame("ADMIN-HOME PAGE");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 70));

        JButton tableBtn = new JButton("TABLE");
        JButton menuBtn = new JButton("MENU");
        JButton staffBtn = new JButton("STAFF");
        JButton reservationBtn = new JButton("RESERVATION");
        JButton takeOrderBtn = new JButton("TAKE ORDER");
        JButton orderCompletionBtn = new JButton("ORDER COMPLETION");
        JButton reportBtn = new JButton("DAILY REPORT");
        JButton profitBtn = new JButton("PROFIT");
        JButton backBtn = new JButton("BACK");

        tableBtn.setPreferredSize(new Dimension(150, 50));
        menuBtn.setPreferredSize(new Dimension(150, 50));
        staffBtn.setPreferredSize(new Dimension(150, 50));
        reservationBtn.setPreferredSize(new Dimension(150, 50));
        takeOrderBtn.setPreferredSize(new Dimension(150, 50));
        orderCompletionBtn.setPreferredSize(new Dimension(170, 50));
        reportBtn.setPreferredSize(new Dimension(150, 50));
        profitBtn.setPreferredSize(new Dimension(150, 50));
        backBtn.setPreferredSize(new Dimension(150, 50));

        tableBtn.addActionListener(b->{
            frame.dispose();
            new TableUI();
        });

        menuBtn.addActionListener(b->{
            frame.dispose();
            new MenuUI();
        });

        staffBtn.addActionListener(b->{
            frame.dispose();
            new StaffUI();
        });

        reservationBtn.addActionListener(b->{
            frame.dispose();
            new ReservationUI();
        });

        takeOrderBtn.addActionListener(b->{
            frame.dispose();
            new TakeOrderUI();
        });

        orderCompletionBtn.addActionListener(b->{
            frame.dispose();
            new AOrderCompletionUI();
        });

        reportBtn.addActionListener(b->{
            frame.dispose();
            new ADailyReportUI();
        });

        profitBtn.addActionListener(b->{
            frame.dispose();
            new ProfitUI();
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new LoginUI();
        });

        frame.add(tableBtn);
        frame.add(menuBtn);
        frame.add(staffBtn);
        frame.add(reservationBtn);
        frame.add(takeOrderBtn);
        frame.add(orderCompletionBtn);
        frame.add(reportBtn);
        frame.add(profitBtn);
        frame.add(backBtn);

        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

}
