package org.restaurant.DAO;

public class SqlQueryConstant {

    // userr
    public static final String GET_ALL_USERR =
            "select * from userr;";

    public static final String INSERT_INTO_USERR =
            "insert into Userr(u_name, email, pass, u_type) values (?,?,?,?)";

    public static final String UPDATE_VALUES_OF_USERR =
            "update userr set u_name = ?, email = ?, pass = ?, u_type = ? where id = ?";

    public static final String GET_BY_ID_USERR =
            "select * from userr where id = ?";

    public static final String DELETE_BY_ID_USERR =
            "delete from userr where id = ?";

    public static final String GET_EMAIL_AND_PASS_USERR =
            "select * from userr where email=? and pass=?;";

    public static final String GET_ID_FROM_USERR =
            "select * from menu where u_name = ?";

    // tables
    public static final String GET_ALL_TABLES =
            "select * from r_table;";

    public static final String INSERT_INTO_TABLES =
            "insert into r_table(t_capacity, availability, r_id) values (?,?,?)";

    public static final String GET_BY_ID_TABLES =
            "select * from r_table where id = ?";

    public static final String UPDATE_BY_ID_TABLES =
            "update r_table set t_capacity = ? , availability = ? where id = ?";

    public static final String DELETE_BY_ID_TABLES =
            "delete from r_table where id = ?";

    public static final String SET_AVAILABILITY_TABLE_BOOKED =
            "update r_table set availability = 'booked' where id = ?";

    public static final String SET_AVAILABILITY_TABLE_OPEN =
            "update r_table set availability = 'open' where id = ?";

    public static final String GET_ALL_RESERVATION_OF_TABLE =
            "select * from reservation where t_id in (select id from r_table where id = ?);";

    public static final String GET_TODAYS_RESERVATION_OF_TABLE =
            "select * from reservation where res_date = curdate() and t_id in (select id from r_table where id = ?);";

    // menu
    public static final String GET_ALL_MENU =
            "select * from menu;";

    public static final String INSERT_INTO_MENU =
            "insert into menu(item_name, category, r_id, making_cost) values (?,?,?,?)";

    public static final String UPDATE_MENU =
            "update menu set item_name = ?, category = ?, making_cost=? where id = ?";

    public static final String DELETE_BY_ID_MENU =
            "delete from menu where id = ?;";

    public static final String GET_BY_ID_MENU =
            "select * from menu where id = ?;";

    public static final String GET_ALL_MENU_BY_CATEGORY =
            "select * from menu where category = ?;";

    public static final String GET_ID_FROM_MENU =
            "select * from menu where item_name = ?";

    public static final String GET_NAME_FROM_ID =
            "select * from menu where id = ?";

    // reservation
    public static final String GET_ALL_RESERVATIONS =
            "select * from reservation";

    public static final String GET_ALL_RESERVATIONS_BY_DATE =
            "select * from reservation where res_date = curdate()";

    public static final String INSERT_INTO_RESERVATIONS =
            "insert into reservation(res_date, res_time, c_id, t_id, r_id) values (DATE(?),TIME(?),?,?,?)";

    public static final String INSERT_INTO_RESERVATIONS_WITH_TOKEN =
            "insert into reservation(res_date, res_time, c_id, t_id, r_id, token) values (DATE(?),TIME(?),?,?,?,?)";

    public static final String DELETE_BY_ID_RESERVATION =
            "delete from reservation where id = ?";

    public static final String GET_ALL_RESERVATIONS_BY_TABLE_ID =
            "select * from reservation where t_id = ?";

    public static final String UPDATE_ALL_VALUES_OF_RESERVATIONS =
            "update reservation set res_date = ?, res_time = ?, c_id = ?, t_id = ?, r_id = ?, token = ? where id = ?";

    // customer
    public static final String GET_ALL_CUSTOMER =
            "select * from customer";

    public static final String GET_BY_ID_CUSTOMER =
            "select * from customer where id = ?";

    public static final String INSERT_INTO_CUSTOMER =
            "insert into customer(c_name, phone, email, bill) values (?,?,?,?)";

    public static final String UPDATE_STATUS_CUSTOMER =
            "update customer set c_status = ? where id = ?";

    public static final String UPDATE_BILL_STATUS_CUSTOMER =
            "update customer set bill = 'done' where id = ?";

    public static final String SET_BILL_STATUS_CUSTOMER =
            "update customer set bill = 'null' where id = ?";

    public static final String SET_ORDER_STATUS =
            "update customer set c_status = 'null' where id = ?";

    public static final String GET_CUSTOMER_WITH_RESERVATION =
            "select * from customer where bill = 'null' and id in (select c_id from reservation where res_date = curdate());";

    public static final String GET_ID_FROM_CUSTOMER =
            "select * from customer where c_name = ?";

    //customerOrder
    public static final String GET_ALL_CUSTOMER_ORDER =
            "select * from customerOrder where c_date = curdate() and c_id in (select id from customer where c_status = 'given');";

    public static final String INSERT_INTO_CUSTOMER_ORDER =
            "insert into customerOrder(m_id, c_id, u_id, item_price, c_date) values(?,?,?,?,?)";

    public static final String DELETE_BY_ID_CUSTOMER_ORDER =
            "delete from customerOrder where m_id = ? and c_id = ? and u_id = ?";

    public static final String GET_CUSTOMER_WITH_ORDER =
            "select * from customer where c_status = 'given' or c_status =  'complete'  and bill = 'null'";

    public static final String GET_ALL_ITEM_OF_CUSTOMER =
            "select * from customerorder where c_id = ? and c_date = curdate();";

    public static final String GET_DAILY_REPORT =
            "select * from customerorder where c_date = curdate();";

    //bill

    public static final String GET_BILL_OF_CUSTOMER_BY_ID =
            "select sum(item_price) as customerBill from customerorder where c_id = ? and c_date = curdate();";

    public static final String GET_DAILY_BILL =
            "select sum(item_price) as customerBill from customerorder where c_date = curdate();";

    //profit

    public static final String GET_ALL_COUNT =
            "select m_id, count(m_id) as menu_count from customerorder where c_date = curdate() and m_id in (select id from menu) group by m_id;";

}
