package org.restaurant.DAO;

import org.restaurant.Domain.Customer;
import org.restaurant.Mapper.CustomerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.restaurant.DAO.SqlQueryConstant.*;

public class CustomerDAO extends BaseDAO implements iCrud<Customer>{

    private final CustomerMapper customerMapper = new CustomerMapper();

    @Override
    public void insert(Customer obj) {
        try{
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_CUSTOMER);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getPhone());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getBill());
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public List<Customer> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_CUSTOMER);

            return customerMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(GET_BY_ID_CUSTOMER);
            ps.setInt(1, Math.toIntExact(id));

            ResultSet rs = ps.executeQuery();
            return customerMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer obj, Long id) {

    }

    @Override
    public void deleteById(Long id) {

    }

    public void updateStatus(Customer obj, Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_STATUS_CUSTOMER);
            ps.setString(1, obj.getStatus());
            ps.setInt(2, id);
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getAllCustomerWithOrder(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_CUSTOMER_WITH_ORDER);

            return customerMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Customer> searchByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from customer where c_name like '%"+name+"%' and c_status = 'given' or c_status =  'complete';"
            );

            ResultSet rs = ps.executeQuery();
            return customerMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getCustomerByReservation(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_CUSTOMER_WITH_RESERVATION);

            return customerMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Customer getIdFromCustomer(String name){
        try {
            PreparedStatement ps = conn.prepareStatement(GET_ID_FROM_CUSTOMER);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();
            return customerMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateCustomerBillStatus(Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_BILL_STATUS_CUSTOMER);
            ps.setInt(1, id);
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void setCustomerBill(Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(SET_BILL_STATUS_CUSTOMER);
            ps.setInt(1, id);
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void setCustomerOrder(Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(SET_ORDER_STATUS);
            ps.setInt(1, id);
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
