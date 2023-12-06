package org.restaurant.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface iMapper <T>{

    List<T> resultSetToList(ResultSet rs) throws SQLException;

    T resultSetTObject(ResultSet rs) throws SQLException;

}
