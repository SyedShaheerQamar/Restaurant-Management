package org.restaurant.Mapper;

import org.restaurant.Domain.CustomerOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderMapper implements iMapper<CustomerOrder>{

    private final String M_ID = "m_id";
    private final String C_ID = "c_id";
    private final String U_ID = "u_id";
    private final String ITEM_PRICE = "item_price";
    private final String DATE = "c_date";

    @Override
    public List<CustomerOrder> resultSetToList(ResultSet rs) throws SQLException {
        List<CustomerOrder> customerOrders = new ArrayList<>();

        while (rs.next()){
            CustomerOrder customerOrder = CustomerOrder.builder()
                    .m_id(rs.getInt(M_ID))
                    .c_id(rs.getInt(C_ID))
                    .u_id(rs.getInt(U_ID))
                    .item_price(rs.getInt(ITEM_PRICE))
                    .c_date(rs.getString(DATE))
                    .build();

            customerOrders.add(customerOrder);
        }

        return customerOrders;
    }

    @Override
    public CustomerOrder resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return CustomerOrder.builder()
                    .m_id(rs.getInt(M_ID))
                    .c_id(rs.getInt(C_ID))
                    .u_id(rs.getInt(U_ID))
                    .item_price(rs.getInt(ITEM_PRICE))
                    .c_date(rs.getString(DATE))
                    .build();
        }
        return null;
    }
}
