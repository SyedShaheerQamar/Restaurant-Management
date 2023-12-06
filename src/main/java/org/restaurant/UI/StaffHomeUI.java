package org.restaurant.UI;

import javax.swing.*;
import java.awt.*;

public class StaffHomeUI {

    public StaffHomeUI(){
        JFrame frame = new JFrame("SATFF-HOME PAGE");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 70));

        JButton reservationBtn = new JButton("RESERVATION");
        JButton takeOrderBtn = new JButton("TAKE ORDER");
        JButton orderCompletionBtn = new JButton("ORDER COMPLETION");
        JButton reportBtn = new JButton("DAILY REPORT");
        JButton backBtn = new JButton("BACK");

        reservationBtn.setPreferredSize(new Dimension(150, 50));
        takeOrderBtn.setPreferredSize(new Dimension(150, 50));
        orderCompletionBtn.setPreferredSize(new Dimension(170, 50));
        reportBtn.setPreferredSize(new Dimension(150, 50));
        backBtn.setPreferredSize(new Dimension(150, 50));


        frame.add(reservationBtn);
        frame.add(takeOrderBtn);
        frame.add(orderCompletionBtn);
        frame.add(reportBtn);
        frame.add(backBtn);

        reservationBtn.addActionListener(b->{
            frame.dispose();
            new StaffReservationUI();
        });

        takeOrderBtn.addActionListener(b->{
            frame.dispose();
            new StaffTakeOrderUI();
        });

        orderCompletionBtn.addActionListener(b->{
            frame.dispose();
            new StaffAOrderCompletionUI();
        });

        reportBtn.addActionListener(b->{
            frame.dispose();
            new StaffADailyReportUI();
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new LoginUI();
        });

        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

}
