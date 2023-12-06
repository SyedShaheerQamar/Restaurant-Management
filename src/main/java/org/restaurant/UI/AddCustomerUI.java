package org.restaurant.UI;

import org.restaurant.DAO.CustomerDAO;
import org.restaurant.DAO.UserrDAO;
import org.restaurant.Domain.Customer;
import org.restaurant.Domain.Userr;
import org.restaurant.Service.CustomerService;
import org.restaurant.Service.StaffService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddCustomerUI {

    private final CustomerService customerService = new CustomerService();
    private final CustomerDAO dao = new CustomerDAO();

    public AddCustomerUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 40));

        JLabel namelb = new JLabel("Enter Name");
        JTextField nametf = new JTextField(15);

        JLabel phonelb = new JLabel("Enter Phone");
        JTextField phonetf = new JTextField(15);

        JLabel emaillb = new JLabel("Enter Email");
        JTextField emailtf = new JTextField(15);

        JButton addBtn = new JButton("ADD");
        JButton backBtn = new JButton("BACK");
        addBtn.setPreferredSize(new Dimension(80, 30));
        backBtn.setPreferredSize(new Dimension(80, 30));

        addBtn.addActionListener(b->{
            String name = nametf.getText();
            String phone = phonetf.getText();
            String email = emailtf.getText();

            List<Customer> customerList = dao.getAll();

            Boolean flag = true;

            for(Customer cus : customerList){
                if(email.equalsIgnoreCase(cus.getEmail())){
                    flag = false;
                    JOptionPane.showMessageDialog(frame, "Email Already Taken!!!");
                }
            }

            if(flag){
                customerService.insertIntoCustomer(name, phone, email);
                frame.dispose();
                new AddReservationUI();
            }else {
                JOptionPane.showMessageDialog(frame, "Try changing email!!!");
            }

        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new AddReservationUI();
        });

        frame.add(namelb);
        frame.add(nametf);
        frame.add(phonelb);
        frame.add(phonetf);
        frame.add(emaillb);
        frame.add(emailtf);
        frame.add(addBtn);
        frame.add(backBtn);

        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
