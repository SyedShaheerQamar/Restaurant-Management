package org.restaurant.UI;

import org.restaurant.Domain.Userr;
import org.restaurant.Service.AuthenticationService;

import javax.swing.*;
import java.awt.*;

public class LoginUI {

    private final AuthenticationService authenticationService = new AuthenticationService();

    public LoginUI(){
        JFrame frame = new JFrame("LOGIN");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        JLabel emaillb = new JLabel("Email");
        JTextField emailtf = new JTextField(15);

        JLabel passlb = new JLabel("Password");
        JTextField passtf = new JTextField(15);

        JButton loginBtn = new JButton("LOGIN");
        JButton backBtn = new JButton("BACK");

        loginBtn.addActionListener(b->{
            Userr userr = authenticationService.getEmailAndPass(emailtf.getText(), passtf.getText());

            if (userr == null){
                JOptionPane.showMessageDialog(frame, "Invalid Credentials!!!");
            }
            else if(userr.getU_type().equalsIgnoreCase("staff")){
                frame.dispose();
                new StaffHomeUI();
            }
            else if(userr.getU_type().equalsIgnoreCase("admin")){
                frame.dispose();
                new AdminHomeUI();
            }
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new SelectUserUI();
        });

        frame.add(emaillb);
        frame.add(emailtf);
        frame.add(passlb);
        frame.add(passtf);
        frame.add(loginBtn);
        frame.add(backBtn);

        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

}
