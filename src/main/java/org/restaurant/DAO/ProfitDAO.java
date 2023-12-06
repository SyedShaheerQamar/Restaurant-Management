package org.restaurant.DAO;

import org.restaurant.Domain.Profit;
import org.restaurant.Mapper.ProfitMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.restaurant.DAO.SqlQueryConstant.GET_ALL_COUNT;

public class ProfitDAO extends BaseDAO implements iCrud<Profit>{

    private final ProfitMapper profitMapper = new ProfitMapper();

    @Override
    public void insert(Profit obj) {

    }

    @Override
    public List<Profit> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_COUNT);

            return profitMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Profit getById(Long id) {
        return null;
    }

    @Override
    public void update(Profit obj, Long id) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
