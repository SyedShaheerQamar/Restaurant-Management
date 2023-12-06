package org.restaurant.Service;

import org.restaurant.DAO.*;
import org.restaurant.Domain.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TakeOrderService {

    private final CustomerOrderDAO dao = new CustomerOrderDAO();

    public void insertValuesIntoCustomerOrder(Integer m_id, Integer c_id, Integer s_id, Integer price, String date){
        CustomerOrder customerOrder = CustomerOrder.builder()
                .m_id(m_id)
                .c_id(c_id)
                .u_id(s_id)
                .item_price(price)
                .c_date(date)
                .build();

        dao.insert(customerOrder);
    }

    public void updateAllValues(Integer id, Integer cap, String avail){
        Tables tables = Tables.builder()
                .capacity(cap)
                .availability(avail)
                .build();

//        dao.update(tables, Long.valueOf(id));
    }

    public void deleteById(String m, String c, String u){
        MenuDAO mdao = new MenuDAO();
        Menu menu = mdao.getIdFromName(m);
        CustomerDAO cdao = new CustomerDAO();
        Customer customer = cdao.getIdFromCustomer(c);
        UserrDAO udao = new UserrDAO();
        Userr userr = udao.getIdFromUser(u);

        dao.deleteByAllId(menu.getId(), customer.getId(), userr.getId());
    }

    public String getMenuName(Integer id){
        MenuDAO mdao = new MenuDAO();
        Menu menu = mdao.getById(Long.valueOf(id));

        return menu.getItem_name();
    }

    public String getCustomerName(Integer id){
        CustomerDAO cdao = new CustomerDAO();
        Customer customer = cdao.getById(Long.valueOf(id));

        return customer.getName();
    }

    public String getStaffName(Integer id){
        UserrDAO udao = new UserrDAO();
        Userr userr = udao.getById(Long.valueOf(id));

        return userr.getU_name();
    }

    public String[] menuItemDropDown(String name){
        MenuDAO menuDAO = new MenuDAO();
        List<Menu> menuList = menuDAO.getMenuItemAccordingToCategory(name);
        String[] data = new String[menuList.size()];

        Integer count = 0;
        for(Menu m : menuList){
            data[count] = m.getId()+",  "+m.getItem_name();
            count++;
        }

        return data;
    }

    public void updateCustomerBillStatus(Integer c_id){
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.setCustomerBill(c_id);
    }

    public String[] customerDropDown(){
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customerList = customerDAO.getCustomerByReservation();
        String[] data = new String[customerList.size()];

//        LocalTime now = LocalTime.now();
        Integer count = 0;
        for(Customer m : customerList){
            data[count] = m.getId()+",  "+m.getName();
            count++;
        }

        return data;
    }

    public String[] adminDropDown(){
        UserrDAO userrDAO = new UserrDAO();
        List<Userr> userrList = userrDAO.getAllAdmin();
        String[] data = new String[userrList.size()];

        Integer count = 0;
        for(Userr m : userrList){
            data[count] = m.getId()+",  "+m.getU_name();
            count++;
        }

        return data;
    }

    public String[] staffDropDown(){
        UserrDAO userrDAO = new UserrDAO();
        List<Userr> userrList = userrDAO.getAllStaff();
        String[] data = new String[userrList.size()];

        Integer count = 0;
        for(Userr m : userrList){
            data[count] = m.getId()+",  "+m.getU_name();
            count++;
        }

        return data;
    }

    public String[] menuCategoryDropDown(){
        MenuDAO menuDAO = new MenuDAO();
        List<Menu> menuList = menuDAO.getAll();

        List<String> category = menuList.stream()
                        .map(Menu::getCategory)
                                .collect(Collectors.toList());

        List<String> categoryName = category.stream()
                .distinct()
                .collect(Collectors.toList());

        String[] data = new String[categoryName.size()];
        int count = 0;

        for(String st : categoryName){
            data[count] = st;
            count++;
        }

        return data;
    }

    public void customerStatus(Integer c_id){
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = Customer.builder()
                .status("given")
                .build();

        customerDAO.updateStatus(customer, c_id);
    }

    public String[][] getAllValuesOfCustomerOrder(){
        List<CustomerOrder> customerOrderList = dao.getAll();
        return convertValuesToJTable(customerOrderList, 5);
    }

    public String[][] searchByName(String name){
        List<CustomerOrder> customerOrderList = dao.searchByName(name);
        return convertValuesToJTable(customerOrderList, 5);
    }

    public String[][] convertValuesToJTable(List<CustomerOrder> customerOrderList, int columnSize){
        String[][] data = new String[customerOrderList.size()][columnSize];

        for(int i=0; i<customerOrderList.size(); i++){
            data[i][0] = getMenuName(customerOrderList.get(i).getM_id());
            data[i][1]= getCustomerName(customerOrderList.get(i).getC_id());
            data[i][2] = getStaffName(customerOrderList.get(i).getU_id());
            data[i][3] = String.valueOf(customerOrderList.get(i).getItem_price());
            data[i][4] = customerOrderList.get(i).getC_date();
        }

        return data;
    }

}
