package org.restaurant.UI;

import org.restaurant.Service.MenuService;

import javax.swing.*;
import java.awt.*;

public class AddMenuUI {

    static String cat = "";
    private final MenuService menuAuthenticationService = new MenuService();

    public AddMenuUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 40));

        JLabel namelb = new JLabel("Add Item Name");
        JTextField nametf = new JTextField(10);


        JLabel availabilitylb = new JLabel("Add Category");
        String[] data2 = menuAuthenticationService.menuCategoryDropDown();
        JComboBox availabilityBox = new JComboBox(data2);
        availabilityBox.setPreferredSize(new Dimension(100, 30));

        JLabel pricelb = new JLabel("Add Item Cost");
        JTextField pricetf = new JTextField(10);

        JButton addCategoryBtn = new JButton("Add");

        addCategoryBtn.addActionListener(b->{
            JTextField cattxt = new JTextField(10);
            JOptionPane.showMessageDialog(frame, cattxt);
            cat = cattxt.getText();
        });
        JButton addBtn = new JButton("ADD");
        JButton backBtn = new JButton("BACK");

        addBtn.addActionListener(b->{
            String avail = (String) availabilityBox.getSelectedItem();
            System.out.println(cat);

            if(cat == ""){
                menuAuthenticationService.insertValuesIntoMenu(nametf.getText(), avail, Integer.valueOf(pricetf.getText()));
                frame.dispose();
                new MenuUI();
            }
            else {
                menuAuthenticationService.insertValuesIntoMenu(nametf.getText(), cat, Integer.valueOf(pricetf.getText()));
                frame.dispose();
                new MenuUI();
            }

        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new MenuUI();
        });

        frame.add(namelb);
        frame.add(nametf);
        frame.add(availabilitylb);
        frame.add(availabilityBox);
        frame.add(addCategoryBtn);
        frame.add(pricelb);
        frame.add(pricetf);
        frame.add(addBtn);
        frame.add(backBtn);

        frame.setSize(350, 350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
