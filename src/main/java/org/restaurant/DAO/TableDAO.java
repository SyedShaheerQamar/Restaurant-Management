package org.restaurant.DAO;

import org.restaurant.Domain.Tables;
import org.restaurant.Mapper.TablesMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.restaurant.DAO.SqlQueryConstant.*;

public class TableDAO extends  BaseDAO implements iCrud<Tables> {

    private final TablesMapper tablesMapper = new TablesMapper();

    @Override
    public void insert(Tables obj){
        try{
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_TABLES);
            ps.setInt(1, obj.getCapacity());
            ps.setString(2, obj.getAvailability());
            ps.setInt(3, obj.getR_id());
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tables> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_TABLES);

            return tablesMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tables getById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(GET_BY_ID_TABLES);
            ps.setInt(1, Math.toIntExact(id));

            ResultSet rs = ps.executeQuery();
            return tablesMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Tables obj, Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_BY_ID_TABLES);
            ps.setInt(1, obj.getCapacity());
            ps.setString(2, obj.getAvailability());
            ps.setInt(3, Math.toIntExact(id));
            ps.executeUpdate();
        }
        catch (SQLException  e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id){
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID_TABLES);
            ps.setInt(1, Math.toIntExact(id));
            ps.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Tables> searchByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from r_table where availability like '%"+name+"%';"
            );

            ResultSet rs = ps.executeQuery();
            return tablesMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void setTableAvailability(Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(SET_AVAILABILITY_TABLE_BOOKED);
            ps.setInt(1, id);
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void setTableAvailabilityOpen(Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(SET_AVAILABILITY_TABLE_OPEN);
            ps.setInt(1, id);
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
