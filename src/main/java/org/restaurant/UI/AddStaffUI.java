package org.restaurant.UI;

import org.restaurant.DAO.UserrDAO;
import org.restaurant.Domain.Userr;
import org.restaurant.Service.StaffService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddStaffUI {

    private final StaffService staffAuthenticationService = new StaffService();
    private final UserrDAO dao = new UserrDAO();

    public AddStaffUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 40));

        JLabel namelb = new JLabel("Enter Name");
        JTextField nametf = new JTextField(15);

        JLabel emaillb = new JLabel("Enter Email");
        JTextField emailtf = new JTextField(15);

        JLabel passlb = new JLabel("Enter Pass");
        JTextField passtf = new JTextField(15);

        JLabel typelb = new JLabel("Enter Type");
        String[] data = {"Admin", "Staff"};
        JComboBox typeBox = new JComboBox(data);
        typeBox.setPreferredSize(new Dimension(100, 30));

        JButton addBtn = new JButton("ADD");
        JButton backBtn = new JButton("BACK");
        addBtn.setPreferredSize(new Dimension(80, 30));
        backBtn.setPreferredSize(new Dimension(80, 30));

        addBtn.addActionListener(b->{
            String name = nametf.getText();
            String email = emailtf.getText();
            String pass = passtf.getText();
            String type = (String) typeBox.getSelectedItem();

            List<Userr> userrList = dao.getAll();

            Boolean flag = true;

            for(Userr us : userrList){
                if(email.equalsIgnoreCase(us.getEmail())){
                    flag = false;
                    JOptionPane.showMessageDialog(frame, "Email Already Taken!!!");
                }
            }

            if(flag){
                staffAuthenticationService.insertValuesIntoTable(name, email, pass, type);

                frame.dispose();
                new StaffUI();
            }else {
                JOptionPane.showMessageDialog(frame, "Try changing email!!!");
            }

        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new StaffUI();
        });

        frame.add(namelb);
        frame.add(nametf);
        frame.add(emaillb);
        frame.add(emailtf);
        frame.add(passlb);
        frame.add(passtf);
        frame.add(typelb);
        frame.add(typeBox);
        frame.add(addBtn);
        frame.add(backBtn);

        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
