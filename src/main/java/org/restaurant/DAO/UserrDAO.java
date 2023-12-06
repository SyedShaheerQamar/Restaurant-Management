package org.restaurant.DAO;

import org.restaurant.Domain.Menu;
import org.restaurant.Domain.Userr;
import org.restaurant.Mapper.UserrMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.restaurant.DAO.SqlQueryConstant.*;

public class UserrDAO extends BaseDAO implements iCrud<Userr>{

    private final UserrMapper userrMapper = new UserrMapper();

    @Override
    public void insert(Userr obj){
        try{
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_USERR);
            ps.setString(1, obj.getU_name());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getPass());
            ps.setString(4, obj.getU_type());
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Userr> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_USERR);

            return userrMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Userr getById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(GET_BY_ID_USERR);
            ps.setInt(1, Math.toIntExact(id));

            ResultSet rs = ps.executeQuery();
            return userrMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Userr obj, Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_VALUES_OF_USERR);
            ps.setString(1, obj.getU_name());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getPass());
            ps.setString(4, obj.getU_type());
            ps.setInt(5, Integer.valueOf(String.valueOf(id)));
            ps.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id){
        try{
            PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID_USERR);
            ps.setInt(1, Math.toIntExact(id));
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Userr getEmailAndPass(String email, String pass){
        try{
            PreparedStatement ps = conn.prepareStatement(GET_EMAIL_AND_PASS_USERR);
            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            return userrMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Userr> searchByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from userr where u_type like '%"+name+"%';"
            );

            ResultSet rs = ps.executeQuery();
            return userrMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Userr> getAllAdmin(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from userr where u_type = 'admin';"
            );

            return userrMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Userr> getAllStaff(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from userr where u_type = 'staff';"
            );

            return userrMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Userr getIdFromUser(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(GET_ID_FROM_USERR);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            return userrMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
