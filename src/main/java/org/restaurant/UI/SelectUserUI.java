package org.restaurant.UI;

import javax.swing.*;
import java.awt.*;

public class SelectUserUI {

    public SelectUserUI(){
        JFrame frame = new JFrame("SELECT USER");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        JButton adminBtn = new JButton("ADMIN");
        JButton staffBtn = new JButton("STAFF");

        adminBtn.setPreferredSize(new Dimension(120, 60));
        staffBtn.setPreferredSize(new Dimension(120, 60));

        adminBtn.addActionListener(b->{
            frame.dispose();
            new LoginUI();
        });

        staffBtn.addActionListener(b->{
            frame.dispose();
            new LoginUI();
        });

        frame.add(adminBtn);
        frame.add(staffBtn);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
