package org.restaurant.UI;

import org.restaurant.Service.TableService;

import javax.swing.*;
import java.awt.*;

public class AddTableUI {

    private final TableService tableService = new TableService();

    public AddTableUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 40));

        JLabel capacitylb = new JLabel("Add Capacity");
        Integer[] data = {2, 3, 5, 8 ,10, 15};
        JComboBox capacityBox = new JComboBox(data);
        capacityBox.setPreferredSize(new Dimension(70, 30));

        JLabel availabilitylb = new JLabel("Add Availability");
        String[] data2 = {"Open", "Booked"};
        JComboBox availabilityBox = new JComboBox(data2);
        availabilityBox.setPreferredSize(new Dimension(70, 30));

        JButton addBtn = new JButton("ADD");
        JButton backBtn = new JButton("BACK");

        addBtn.addActionListener(b->{
            Integer val = (Integer) capacityBox.getSelectedItem();
            String avail = (String) availabilityBox.getSelectedItem();

            tableService.insertValuesIntoTable(val, avail);
            frame.dispose();
            new TableUI();
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new TableUI();
        });

        frame.add(capacitylb);
        frame.add(capacityBox);
        frame.add(availabilitylb);
        frame.add(availabilityBox);
        frame.add(addBtn);
        frame.add(backBtn);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
