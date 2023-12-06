package org.restaurant.Service;

import org.restaurant.DAO.BillDAO;
import org.restaurant.DAO.CustomerDAO;
import org.restaurant.DAO.CustomerOrderDAO;
import org.restaurant.DAO.MenuDAO;
import org.restaurant.Domain.Bill;
import org.restaurant.Domain.Customer;
import org.restaurant.Domain.CustomerOrder;
import org.restaurant.Domain.Menu;

import java.util.List;

public class OrderCompletionService {

    private final CustomerDAO dao = new CustomerDAO();

    public String[][] searchByName(String name){
        List<Customer> customerList = dao.searchByName(name);
        return convertToJTable(customerList, 5);
    }

    public List<CustomerOrder> getAllItems(Integer id){
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
        List<CustomerOrder> customerOrderList = customerOrderDAO.getAllItemsOfCustomer(id);
        return customerOrderList;
    }

    public String getMenuName(Integer id){
        MenuDAO menuDAO = new MenuDAO();
        Menu menu = menuDAO.getNameFromId(id);
        return menu.getItem_name();
    }

    public void customerOrderCompletion(Integer id){
        Customer customer = Customer.builder()
                .status("complete")
                .build();

        dao.updateStatus(customer, id);
    }

    public String[][] getAllCustomers(){
        List<Customer> customerList = dao.getAllCustomerWithOrder();
        return convertToJTable(customerList, 5);
    }

    public Integer getCustomerBill(Integer id){
        BillDAO billDAO = new BillDAO();
        Bill bill = billDAO.getById(Long.valueOf(id));

        return bill.getTotal_bill();
    }

    public void getCustomerBillStatus(Integer c_id){
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.updateCustomerBillStatus(c_id);
    }

    public String[][] convertToJTable(List<Customer> customerList, int columnSize){
        String[][] data = new String[customerList.size()][columnSize];

        for(int i=0; i<customerList.size(); i++){
            data[i][0] = String.valueOf(customerList.get(i).getId());
            data[i][1] = customerList.get(i).getName();
            data[i][2] = customerList.get(i).getEmail();
            data[i][3] = customerList.get(i).getPhone();
            data[i][4] = customerList.get(i).getStatus();
        }

        return data;
    }

}
