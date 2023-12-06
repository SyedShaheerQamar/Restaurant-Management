package org.restaurant.Mapper;

import org.restaurant.Domain.Bill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillMapper implements iMapper<Bill>{

    private final String TOTAL_BILL = "customerBill";

    @Override
    public List<Bill> resultSetToList(ResultSet rs) throws SQLException {
        List<Bill> bills = new ArrayList<>();

        while (rs.next()){
            Bill bill = Bill.builder()
                    .total_bill(rs.getInt(TOTAL_BILL))
                    .build();

            bills.add(bill);
        }

        return bills;
    }

    @Override
    public Bill resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Bill.builder()
                    .total_bill(rs.getInt(TOTAL_BILL))
                    .build();
        }
        return null;
    }
}
