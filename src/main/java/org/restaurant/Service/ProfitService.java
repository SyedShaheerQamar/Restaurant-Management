package org.restaurant.Service;

import org.restaurant.DAO.*;
import org.restaurant.Domain.*;

import java.util.List;

public class ProfitService {

    private final CustomerOrderDAO dao = new CustomerOrderDAO();

    public String getMenuName(Integer id){
        MenuDAO mdao = new MenuDAO();
        Menu menu = mdao.getById(Long.valueOf(id));

        return menu.getItem_name();
    }

    public Integer getTotalMakingCost(){
        MenuDAO menuDAO = new MenuDAO();
        ProfitDAO profitDAO = new ProfitDAO();
        List<Profit> profitList = profitDAO.getAll();

        Integer sum = 0;
        for(int i=0; i<profitList.size(); i++){
            Menu menu = menuDAO.getById(Long.valueOf(profitList.get(i).getM_id()));
            sum += profitList.get(i).getCount() * menu.getCost();
        }

        return sum;

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

    public Integer getDailyReportBill(){
        BillDAO billDAO = new BillDAO();
        Bill bill = billDAO.getDailyReport();
        return bill.getTotal_bill();
    }

    public String[][] getAllValuesOfCustomerOrder(){
        List<CustomerOrder> customerOrderList = dao.getDailyReport();
        return convertValuesToJTable(customerOrderList, 5);
    }

    public String[][] searchByName(String name){
        List<CustomerOrder> customerOrderList = dao.searchByNameDailyReport(name);
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
