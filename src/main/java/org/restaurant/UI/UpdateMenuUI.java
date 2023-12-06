package org.restaurant.UI;

import org.restaurant.DAO.MenuDAO;
import org.restaurant.Domain.Menu;
import org.restaurant.Service.MenuService;

import javax.swing.*;
import java.awt.*;

public class UpdateMenuUI {

    static String cat;
    private final MenuService menuAuthenticationService = new MenuService();
    private final MenuDAO dao = new MenuDAO();

    public UpdateMenuUI(Integer id) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 40));

        Menu menu = dao.getById(Long.valueOf(id));

        JLabel namelb = new JLabel("Update Item Name");
        JTextField nametf = new JTextField(10);
        nametf.setText(menu.getItem_name());

        JLabel availabilitylb = new JLabel("Update Category");
        String[] data2 = menuAuthenticationService.menuCategoryDropDown();
        JComboBox availabilityBox = new JComboBox(data2);
        availabilityBox.setPreferredSize(new Dimension(100, 30));

        JLabel pricelb = new JLabel("Update Item Cost");
        JTextField pricetf = new JTextField(10);
        pricetf.setText(String.valueOf(menu.getCost()));

        JButton addCategoryBtn = new JButton("Add");

        addCategoryBtn.addActionListener(b->{
            JTextField cattxt = new JTextField(10);
            JOptionPane.showMessageDialog(frame, cattxt);
            cat = cattxt.getText();
        });

        JButton updateBtn = new JButton("UPDATE");
        JButton backBtn = new JButton("BACK");

        updateBtn.addActionListener(b-> {
            String avail = (String) availabilityBox.getSelectedItem();

            if (cat == ""){
                menuAuthenticationService.updateAllValues(id, nametf.getText(), avail);
                frame.dispose();
                new MenuUI();
            }
            else {
                menuAuthenticationService.updateAllValues(id, nametf.getText(), cat);
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
        frame.add(updateBtn);
        frame.add(backBtn);

        frame.setSize(380, 350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
