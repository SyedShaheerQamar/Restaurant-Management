package org.restaurant.Mapper;

import org.restaurant.Domain.Profit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfitMapper implements iMapper<Profit>{

    private final String ID = "m_id";
    private final String COUNT = "menu_count";

    @Override
    public List<Profit> resultSetToList(ResultSet rs) throws SQLException {
        List<Profit> profitList = new ArrayList<>();

        while (rs.next()){
            Profit profit = Profit.builder()
                    .m_id(rs.getInt(ID))
                    .count(rs.getInt(COUNT))
                    .build();

            profitList.add(profit);
        }

        return profitList;
    }

    @Override
    public Profit resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Profit.builder()
                    .m_id(rs.getInt(ID))
                    .count(rs.getInt(COUNT))
                    .build();
        }
        return null;
    }
}
