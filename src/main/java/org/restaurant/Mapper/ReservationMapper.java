package org.restaurant.Mapper;

import org.restaurant.Domain.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationMapper implements iMapper<Reservation>{

    private static final String ID = "id";
    private static final String DATE = "res_date";
    private static final String TIME = "res_time";
    private static final String C_ID = "c_id";
    private static final String T_ID = "t_id";
    private static final String R_ID = "r_id";
    private static final String TOKEN = "token";

    @Override
    public List<Reservation> resultSetToList(ResultSet rs) throws SQLException {
        List<Reservation> reservationList = new ArrayList<>();

        while(rs.next()){
            Reservation reservation = Reservation.builder()
                    .id(rs.getInt(ID))
                    .resDate(rs.getString(DATE))
                    .resTime(rs.getString(TIME))
                    .c_id(rs.getInt(C_ID))
                    .t_id(rs.getInt(T_ID))
                    .r_id(rs.getInt(R_ID))
                    .token(rs.getInt(TOKEN))
                    .build();

            reservationList.add(reservation);
        }

        return reservationList;
    }

    @Override
    public Reservation resultSetTObject(ResultSet rs) throws SQLException {

        if(rs.next()){
            return Reservation.builder()
                    .id(rs.getInt(ID))
                    .resDate(rs.getString(DATE))
                    .resTime(rs.getString(TIME))
                    .c_id(rs.getInt(C_ID))
                    .t_id(rs.getInt(T_ID))
                    .r_id(rs.getInt(R_ID))
                    .token(rs.getInt(TOKEN))
                    .build();
        }
        return null;
    }
}
