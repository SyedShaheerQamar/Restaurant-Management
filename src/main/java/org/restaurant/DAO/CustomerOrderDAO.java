package org.restaurant.DAO;

import org.restaurant.Domain.CustomerOrder;
import org.restaurant.Mapper.CustomerOrderMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.restaurant.DAO.SqlQueryConstant.*;

public class CustomerOrderDAO extends BaseDAO implements iCrud<CustomerOrder>{

    private final CustomerOrderMapper customerOrderMapper = new CustomerOrderMapper();

    @Override
    public void insert(CustomerOrder obj) {
        try{
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_CUSTOMER_ORDER);
            ps.setInt(1, obj.getM_id());
            ps.setInt(2, obj.getC_id());
            ps.setInt(3, obj.getU_id());
            ps.setInt(4, obj.getItem_price());
            ps.setString(5, obj.getC_date());
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CustomerOrder> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_CUSTOMER_ORDER);

            return customerOrderMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public CustomerOrder getById(Long id) {
        return null;
    }

    @Override
    public void update(CustomerOrder obj, Long id) {

    }

    @Override
    public void deleteById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID_CUSTOMER_ORDER);
            ps.setInt(1, Math.toIntExact(id));
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteByAllId(Integer mid, Integer cid, Integer uid){
        try{
            PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID_CUSTOMER_ORDER);
            ps.setInt(1, mid);
            ps.setInt(2, cid);
            ps.setInt(3, uid);
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<CustomerOrder> searchByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from customerorder where c_id in (select id from customer where c_name like '%"+name+"%');"
            );

            ResultSet rs = ps.executeQuery();
            return customerOrderMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<CustomerOrder> getAllItemsOfCustomer(Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(GET_ALL_ITEM_OF_CUSTOMER);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            return customerOrderMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<CustomerOrder> getDailyReport(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_DAILY_REPORT);
            return customerOrderMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<CustomerOrder> searchByNameDailyReport(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from customerorder where c_date = curdate() and c_id in (select id from customer where c_name like '%"+name+"%');"
            );

            ResultSet rs = ps.executeQuery();
            return customerOrderMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
