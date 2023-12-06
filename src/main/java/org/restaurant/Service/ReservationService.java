package org.restaurant.Service;

import org.restaurant.DAO.CustomerDAO;
import org.restaurant.DAO.ReservationDAO;
import org.restaurant.DAO.TableDAO;
import org.restaurant.DAO.UserrDAO;
import org.restaurant.Domain.Customer;
import org.restaurant.Domain.Reservation;
import org.restaurant.Domain.Tables;
import org.restaurant.Domain.Userr;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {

    private final ReservationDAO dao = new ReservationDAO();

    public void insertValuesIntoReservation(String date, String time, Integer c_id, Integer t_id){
        Reservation reservation = Reservation.builder()
                .resDate(date)
                .resTime(time)
                .c_id(c_id)
                .t_id(t_id)
                .r_id(1)
                .build();

        dao.insert(reservation);
        TableDAO tableDAO = new TableDAO();
        tableDAO.setTableAvailability(t_id);
    }

    public void insertIntoReservationToken(String date, String time, Integer c_id, Integer t_id, Integer tok){
        Reservation reservation = Reservation.builder()
                .resDate(date)
                .resTime(time)
                .c_id(c_id)
                .t_id(t_id)
                .r_id(1)
                .token(tok)
                .build();

        dao.insertIntoWithToken(reservation);
        TableDAO tableDAO = new TableDAO();
        tableDAO.setTableAvailability(t_id);
    }

    public void updateAllValues(Integer id, String na, String em, String pa, String ty){
        Userr userr = Userr.builder()
                .u_name(na)
                .email(em)
                .pass(pa)
                .u_type(ty)
                .build();

//        dao.update(userr, Long.valueOf(id));
    }

    public String[] getCustomerValues(){
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customerList = customerDAO.getAll();
        return convertIntoComboBoxCustomer(customerList, 3);
    }

    public String[] getTableValue(){
        TableDAO tableDAO = new TableDAO();
        List<Tables> tablesList = tableDAO.getAll();
        String[] data = new String[tablesList.size()];

        int count = 0;
        for(Tables ta : tablesList){
            data[count] = ta.getId()+", Capacity :"+ta.getCapacity()+",  "+ta.getAvailability();
            count++;
        }

        return data;
    }

    public void deleteById(Integer id, Integer t_id){

        dao.deleteById(Long.valueOf(id));
        ReservationDAO reservationDAO = new ReservationDAO();
        List<Reservation> reservationList = reservationDAO.getAllByTableId(t_id);

        if(reservationList.size() == 0){
            TableDAO tableDAO = new TableDAO();
            tableDAO.setTableAvailabilityOpen(t_id);
        }

    }

    public void updateCustomerStatus(Integer id){
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.setCustomerBill(id);
        customerDAO.setCustomerOrder(id);
    }

    public String[][] getAllValuesOfReservation(){
        List<Reservation> reservationList = dao.getAll();
        return convertValuesToJTable(reservationList, 7);
    }

    public String[][] searchByName(String name){
        List<Reservation> reservationList = dao.searchByName(name);
        return convertValuesToJTable(reservationList, 7);
    }

    public String getCustomerName(Integer id){
        CustomerDAO cusDAO = new CustomerDAO();
        Customer customer = cusDAO.getById(Long.valueOf(id));

        return customer.getName();
    }

    public String[][] getAllReservationsForTable(Integer id){
        List<Reservation> reservationList = dao.getAllReservation(id);
        return convertValuesToJTable(reservationList, 7);
    }

    public String[][] getTodaysReservationsForTable(Integer id){
        List<Reservation> reservationList = dao.getTodaysReservation(id);
        return convertValuesToJTable(reservationList, 7);
    }

    public String[][] convertValuesToJTable(List<Reservation> reservationList, int columnSize){
        String[][] data = new String[reservationList.size()][columnSize];
        LocalDate now = LocalDate.now();
        TableDAO tableDAO = new TableDAO();

        for(int i=0; i<reservationList.size(); i++){
            data[i][0] = String.valueOf(reservationList.get(i).getId());
            data[i][1]= reservationList.get(i).getResDate();
            data[i][2] = reservationList.get(i).getResTime();
            data[i][3] = getCustomerName(reservationList.get(i).getC_id());
            data[i][4] = String.valueOf(reservationList.get(i).getT_id());
            data[i][5] = "Shaheen Shinwari";
            data[i][6] = String.valueOf(reservationList.get(i).getToken());

            LocalDate date = LocalDate.parse(data[i][1]);
            Tables tables = tableDAO.getById(Long.valueOf(data[i][4]));
            if(now.isBefore(date) || now.isEqual(date)){
                tables.setAvailability("booked");
                tableDAO.update(tables, Long.valueOf(data[i][4]));
            }
            else {
                tables.setAvailability("Open");
                tableDAO.update(tables, Long.valueOf(data[i][4]));
            }
//            if(now.isAfter(date)){
//                Tables tablesList = tableDAO.getById(Long.valueOf(data[i][4]));
//                if(!tablesList.getAvailability().equalsIgnoreCase("booked")){
//                    Tables tables = tableDAO.getById(Long.valueOf(data[i][4]));
//                    tables.setAvailability("Open");
//                    tableDAO.update(tables, Long.valueOf(data[i][4]));
//                }
//            }
        }

        return data;
    }

    public String[] convertIntoComboBoxCustomer(List<Customer> customerList, int columnSize){
        String[][] data = new String[customerList.size()][columnSize];
        String[] value = new String[customerList.size()];

        for(int i=0; i<customerList.size(); i++){
            data[i][0] = String.valueOf(customerList.get(i).getId());
            data[i][1] = customerList.get(i).getName();

            value[i] = data[i][0] +",  "+ data[i][1];
        }

        return value;
    }

}
