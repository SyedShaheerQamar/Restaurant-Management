package org.restaurant.UI;

import jdk.nashorn.internal.scripts.JO;
import org.restaurant.Domain.CustomerOrder;
import org.restaurant.Service.OrderCompletionService;
import org.restaurant.Service.TakeOrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class AOrderCompletionUI {

    private final OrderCompletionService orderCompletionService = new OrderCompletionService();

    public AOrderCompletionUI(){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout(30, 20));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40));
        JTextField searchtf = new JTextField(40);
        searchPanel.add(searchtf);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        String[][] data = orderCompletionService.getAllCustomers();
        String[] columns = {"ID", "NAME", "PHONE", "EMAIL", "STATUS"};

        DefaultTableModel dtm = new DefaultTableModel(data, columns);

        JTable jTable = new JTable(dtm);
        JScrollPane sp = new JScrollPane(jTable);
        sp.setPreferredSize(new Dimension(450, 150));
        tablePanel.add(sp);

        searchtf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String[][] data = orderCompletionService.searchByName(searchtf.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, columns);
                jTable.setModel(dtm);
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));

        JButton addBtn = new JButton("COMPLETE ORDER");
        JButton billBtn = new JButton("GENERATE BILL");
        JButton backBtn = new JButton("BACK");

        btnPanel.add(addBtn);
        btnPanel.add(billBtn);
        btnPanel.add(backBtn);

        addBtn.addActionListener(b->{
            Integer val = jTable.getSelectedRow();
            if(val == -1){
                JOptionPane.showMessageDialog(frame, "Select a customer first!!!");
            }
            else {
                String id = (String) jTable.getValueAt(val, 0);
                orderCompletionService.customerOrderCompletion(Integer.valueOf(id));
                JOptionPane.showMessageDialog(frame, "Order Completed!!!");
                frame.dispose();
                new AOrderCompletionUI();
            }
        });

        billBtn.addActionListener(b->{
            Integer row = jTable.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(frame, "Select a customer for bill!!!");
            }
            else {
                String status = (String) jTable.getValueAt(row, 4);
                if(status.equalsIgnoreCase("complete")){
                    String id = (String) jTable.getValueAt(row, 0);
                    List<CustomerOrder> customerOrderList = orderCompletionService.getAllItems(Integer.valueOf(id));

                    for(int i=0; i<customerOrderList.size(); i++){
                        JOptionPane.showMessageDialog(frame,
                                orderCompletionService.getMenuName(customerOrderList.get(i).getM_id())
                                        +"  Price : "+customerOrderList.get(i).getItem_price());
                    }

                    String name = (String) jTable.getValueAt(row, 1);
                    JOptionPane.showMessageDialog(frame, "The total bill of "+name+" is : "+ orderCompletionService.getCustomerBill(Integer.valueOf(id)));
                    orderCompletionService.getCustomerBillStatus(Integer.valueOf(id));
                    frame.dispose();
                    new AOrderCompletionUI();

                }
                else {
                    JOptionPane.showMessageDialog(frame, "Order Not Completed!!!");
                }
            }
        });

        backBtn.addActionListener(b->{
            frame.dispose();
            new AdminHomeUI();
        });

        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.WEST);
        frame.add(btnPanel, BorderLayout.EAST);

        frame.setSize(900, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
