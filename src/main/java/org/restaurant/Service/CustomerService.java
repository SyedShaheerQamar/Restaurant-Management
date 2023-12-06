package org.restaurant.Service;

import org.restaurant.DAO.CustomerDAO;
import org.restaurant.Domain.Customer;

public class CustomerService {

    private final CustomerDAO dao = new CustomerDAO();

    public void insertIntoCustomer(String name, String phone, String email){
        Customer customer = Customer.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .bill("null")
                .build();

        dao.insert(customer);
    }

}
