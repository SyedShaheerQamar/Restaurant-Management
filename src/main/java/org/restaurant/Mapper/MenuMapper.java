package org.restaurant.Mapper;

import org.restaurant.Domain.Menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuMapper implements iMapper<Menu>{

    private final String ID = "id";
    private final String ITEM_NAME = "item_name";
    private final String CATEGORY = "category";
    private final String R_ID = "r_id";
    private final String COST = "making_cost";

    @Override
    public List<Menu> resultSetToList(ResultSet rs) throws SQLException {
        List<Menu> menuList = new ArrayList<>();

        while(rs.next()){
            Menu menu = Menu.builder()
                    .id(rs.getInt(ID))
                    .item_name(rs.getString(ITEM_NAME))
                    .category(rs.getString(CATEGORY))
                    .r_id(rs.getInt(R_ID))
                    .cost(rs.getInt(COST))
                    .build();

            menuList.add(menu);
        }

        return menuList;
    }

    @Override
    public Menu resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Menu.builder()
                    .id(rs.getInt(ID))
                    .item_name(rs.getString(ITEM_NAME))
                    .category(rs.getString(CATEGORY))
                    .r_id(rs.getInt(R_ID))
                    .cost(rs.getInt(COST))
                    .build();
        }
        return null;
    }
}
