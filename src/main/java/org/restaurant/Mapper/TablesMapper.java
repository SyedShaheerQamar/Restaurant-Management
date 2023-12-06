package org.restaurant.Mapper;

import org.restaurant.Domain.Tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TablesMapper implements iMapper<Tables> {


    private final String ID = "id";
    private final String CAPACITY = "t_capacity";
    private final String AVAILABILITY = "availability";
    private final String R_ID = "r_id";

    @Override
    public List<Tables> resultSetToList(ResultSet rs) throws SQLException {
        List<Tables> tablesList = new ArrayList<>();

        while (rs.next()){
            Tables tables = Tables.builder()
                    .id(rs.getInt(ID))
                    .capacity(rs.getInt(CAPACITY))
                    .availability(rs.getString(AVAILABILITY))
                    .r_id(rs.getInt(R_ID))
                    .build();

            tablesList.add(tables);
        }

        return tablesList;
    }

    @Override
    public Tables resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Tables.builder()
                    .id(rs.getInt(ID))
                    .capacity(rs.getInt(CAPACITY))
                    .availability(rs.getString(AVAILABILITY))
                    .r_id(rs.getInt(R_ID))
                    .build();
        }
        return null;
    }
}
