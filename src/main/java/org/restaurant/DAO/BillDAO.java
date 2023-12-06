package org.restaurant.DAO;

import org.restaurant.Domain.Bill;
import org.restaurant.Mapper.BillMapper;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.restaurant.DAO.SqlQueryConstant.GET_BILL_OF_CUSTOMER_BY_ID;
import static org.restaurant.DAO.SqlQueryConstant.GET_DAILY_BILL;

public class BillDAO extends BaseDAO implements iCrud<Bill>{

    private final BillMapper billMapper = new BillMapper();

    @Override
    public void insert(Bill obj) {

    }

    @Override
    public List<Bill> getAll() {
        return null;
    }

    @Override
    public Bill getById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(GET_BILL_OF_CUSTOMER_BY_ID);
            ps.setInt(1, Math.toIntExact(id));

            ResultSet rs = ps.executeQuery();
            return billMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Bill obj, Long id) {

    }

    @Override
    public void deleteById(Long id) {

    }

    public Bill getDailyReport(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_DAILY_BILL);
            return billMapper.resultSetTObject(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
