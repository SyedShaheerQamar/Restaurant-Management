package org.restaurant.DAO;

import org.restaurant.Domain.Reservation;
import org.restaurant.Domain.Tables;
import org.restaurant.Mapper.ReservationMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.restaurant.DAO.SqlQueryConstant.*;

public class ReservationDAO extends BaseDAO implements iCrud<Reservation>{

    private final ReservationMapper reservationMapper = new ReservationMapper();

    @Override
    public void insert(Reservation obj) {
        try{
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_RESERVATIONS);
            ps.setString(1, obj.getResDate());
            ps.setString(2, obj.getResTime());
            ps.setInt(3, obj.getC_id());
            ps.setInt(4, obj.getT_id());
            ps.setInt(5, obj.getR_id());
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reservation> getAll() {
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_RESERVATIONS);

            return reservationMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Reservation getById(Long id) {
        return null;
    }

    @Override
    public void update(Reservation obj, Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(UPDATE_ALL_VALUES_OF_RESERVATIONS);
            ps.setString(1, obj.getResDate());
            ps.setString(2, obj.getResTime());
            ps.setInt(3, obj.getC_id());
            ps.setInt(4, obj.getT_id());
            ps.setInt(5, obj.getR_id());
            ps.setInt(6, obj.getToken());
            ps.setInt(7, Math.toIntExact(id));
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try{
            PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID_RESERVATION);
            ps.setInt(1, Math.toIntExact(id));
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Reservation> searchByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "select * from reservation where c_id in (select id from customer where c_name like '%"+name+"%');"
            );

            ResultSet rs = ps.executeQuery();
            return reservationMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Reservation> getAllByTableId(Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(GET_ALL_RESERVATIONS_BY_TABLE_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            return reservationMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void insertIntoWithToken(Reservation obj){
        try{
            PreparedStatement ps = conn.prepareStatement(INSERT_INTO_RESERVATIONS_WITH_TOKEN);
            ps.setString(1, obj.getResDate());
            ps.setString(2, obj.getResTime());
            ps.setInt(3, obj.getC_id());
            ps.setInt(4, obj.getT_id());
            ps.setInt(5, obj.getR_id());
            ps.setInt(6, obj.getToken());
            ps.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Reservation> getAllByDate(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_RESERVATIONS_BY_DATE);

            return reservationMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Reservation> getAllReservation(Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(GET_ALL_RESERVATION_OF_TABLE);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            return reservationMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Reservation> getTodaysReservation(Integer id){
        try{
            PreparedStatement ps = conn.prepareStatement(GET_TODAYS_RESERVATION_OF_TABLE);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            return reservationMapper.resultSetToList(rs);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
