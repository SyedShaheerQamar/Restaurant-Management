package org.restaurant.Mapper;

import org.restaurant.Domain.Userr;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserrMapper implements iMapper<Userr>{

    private static String ID = "id";
    private static String NAME = "u_name";
    private static String EMAIL = "email";
    private static String PASS = "pass";
    private static String TYPE = "u_type";

    @Override
    public List<Userr> resultSetToList(ResultSet rs) throws SQLException {
        List<Userr> userrList = new ArrayList<>();

        while (rs.next()){
            Userr userr = Userr.builder()
                    .id(rs.getInt(ID))
                    .u_name(rs.getString(NAME))
                    .email(rs.getString(EMAIL))
                    .pass(rs.getString(PASS))
                    .u_type(rs.getString(TYPE))
                    .build();

            userrList.add(userr);
        }

        return userrList;
    }

    @Override
    public Userr resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Userr.builder()
                    .id(rs.getInt(ID))
                    .u_name(rs.getString(NAME))
                    .email(rs.getString(EMAIL))
                    .pass(rs.getString(PASS))
                    .u_type(rs.getString(TYPE))
                    .build();
        }
        return null;
    }
}
