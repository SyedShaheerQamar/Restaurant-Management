package org.restaurant.UI;

import jdk.nashorn.internal.scripts.JO;
import org.restaurant.Service.TakeOrderService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class AddTakeOrderUI {

    private final TakeOrderService takeOrderService = new TakeOrderService();

    public AddTakeOrderUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));

        JLabel customerlb = new JLabel("CUSTOMER");
        String[] data = takeOrderService.customerDropDown();
        if(data.length == 0){
            JOptionPane.showMessageDialog(frame, "No Customer Available!!!");
        }
        JComboBox customerBox = new JComboBox<>(data);
        customerBox.setPreferredSize(new Dimension(130, 30));

        JLabel stafflb = new JLabel("STAFF");
        String[] data1 = takeOrderService.adminDropDown();
        JComboBox staffBox = new JComboBox<>(data1);
        staffBox.setPreferredSize(new Dimension(130, 30));

        JLabel categorylb = new JLabel("CATEGORY");
        String[] data2 = takeOrderService.menuCategoryDropDown();
        JComboBox categoryBox = new JComboBox<>(data2);
        categoryBox.setPreferredSize(new Dimension(130, 30));

        JButton selectBtn = new JButton("SELECT");
        selectBtn.setPreferredSize(new Dimension(250, 30));

        JLabel menulb = new JLabel("MENU");
        String[] data3 = {};
        JComboBox menuBox = new JComboBox<>(data3);
        menuBox.setPreferredSize(new Dimension(150, 30));

        selectBtn.addActionListener(b->{
            String cat = (String) categoryBox.getSelectedItem();
            menuBox.removeAllItems();

            String[] data4 = takeOrderService.menuItemDropDown(cat);
            for(int i=0;i <data4.length; i++){
                menuBox.addItem(data4[i]);
            }

        });

        JLabel pricelb = new JLabel("PRICE");
        JTextField pricetf = new JTextField(15);

        JButton addBtn = new JButton("ADD");
        JButton backBtn = new JButton("BACK");

        addBtn.addActionListener(b->{
            String menu = (String) menuBox.getSelectedItem();
            String[] m_value = menu.split(",");
            Integer m_id = Integer.valueOf(m_value[0]);

            String customer = (String) customerBox.getSelectedItem();
            String[] c_value = customer.split(",");
            Integer c_id = Integer.valueOf(c_value[0]);

            String staff = (String) staffBox.getSelectedItem();
            String[] s_value = staff.split(",");
            Integer s_id = Integer.valueOf(s_value[0]);

            LocalDate now = LocalDate.now();

            takeOrderService.insertValuesIntoCustomerOrder(m_id, c_id, s_id, Integer.valueOf(pricetf.getText()), String.valueOf(now));
            takeOrderService.customerStatus(c_id);
            takeOrderService.updateCustomerBillStatus(c_id);
            JOptionPane.showMessageDialog(frame, "Order Added of " + c_value[1]);
            frame.dispose();
            new TakeOrderUI();

        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new TakeOrderUI();
        });

        frame.add(customerlb);
        frame.add(customerBox);
        frame.add(stafflb);
        frame.add(staffBox);
        frame.add(categorylb);
        frame.add(categoryBox);
        frame.add(selectBtn);
        frame.add(menulb);
        frame.add(menuBox);
        frame.add(pricelb);
        frame.add(pricetf);
        frame.add(addBtn);
        frame.add(backBtn);

        frame.setSize(300, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
