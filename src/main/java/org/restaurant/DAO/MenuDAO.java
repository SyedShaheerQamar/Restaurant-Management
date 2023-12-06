package org.restaurant.DAO;

import org.restaurant.Domain.Menu;
import org.restaurant.Mapper.MenuMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.restaurant.DAO.SqlQueryConstant.*;

public class MenuDAO extends BaseDAO implements iCrud<Menu>{

    private final MenuMapper menuMapper = new MenuMapper();

    @Override
    public void insert(Menu obj) {
        try{
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_MENU);
            ps.setString(1, obj.getItem_name());
            ps.setString(2, obj.getCategory());
            ps.setInt(3, obj.getR_id());
            ps.setInt(4, obj.getCost());
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Menu> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_MENU);

            return menuMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Menu getById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(GET_BY_ID_MENU);
            ps.setInt(1, Math.toIntExact(id));

            ResultSet rs = ps.executeQuery();
            return menuMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Menu obj, Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_MENU);
            ps.setString(1, obj.getItem_name());
            ps.setString(2, obj.getCategory());
            ps.setInt(3, obj.getCost());
            ps.setInt(4, Math.toIntExact(id));
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID_MENU);
            ps.setInt(1, Math.toIntExact(id));
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Menu> searchByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from menu where item_name like '%"+name+"%';"
            );

            ResultSet rs = ps.executeQuery();
            return menuMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Menu> getMenuItemAccordingToCategory(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(GET_ALL_MENU_BY_CATEGORY);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            return menuMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Menu getIdFromName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(GET_ID_FROM_MENU);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            return menuMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Menu getNameFromId(Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(GET_NAME_FROM_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            return menuMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
