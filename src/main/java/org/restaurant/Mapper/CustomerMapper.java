package org.restaurant.Mapper;

import org.restaurant.Domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements iMapper<Customer>{

    private final String ID = "id";
    private final String NAME = "c_name";
    private final String PHONE = "phone";
    private final String EMAIL = "email";
    private final String STATUS = "c_status";
    private final String BILL = "bill";

    @Override
    public List<Customer> resultSetToList(ResultSet rs) throws SQLException {
        List<Customer> customerList = new ArrayList<>();

        while (rs.next()){
            Customer customer = Customer.builder()
                    .id(rs.getInt(ID))
                    .name(rs.getString(NAME))
                    .phone(rs.getString(PHONE))
                    .email(rs.getString(EMAIL))
                    .status(rs.getString(STATUS))
                    .bill(rs.getString(BILL))
                    .build();

            customerList.add(customer);
        }

        return customerList;
    }

    @Override
    public Customer resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Customer.builder()
                    .id(rs.getInt(ID))
                    .name(rs.getString(NAME))
                    .phone(rs.getString(PHONE))
                    .email(rs.getString(EMAIL))
                    .status(rs.getString(STATUS))
                    .bill(rs.getString(BILL))
                    .build();
        }
        return null;
    }
}
