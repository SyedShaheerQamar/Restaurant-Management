package org.restaurant.Service;

import org.restaurant.DAO.UserrDAO;
import org.restaurant.Domain.Userr;

import java.util.List;

public class StaffService {

    private final UserrDAO dao = new UserrDAO();

    public void insertValuesIntoTable(String na, String em, String pa, String ty){
        Userr userr = Userr.builder()
                .u_name(na)
                .email(em)
                .pass(pa)
                .u_type(ty)
                .build();

        dao.insert(userr);
    }

    public void updateAllValues(Integer id, String na, String em, String pa, String ty){
        Userr userr = Userr.builder()
                .u_name(na)
                .email(em)
                .pass(pa)
                .u_type(ty)
                .build();

        dao.update(userr, Long.valueOf(id));
    }

    public void deleteById(Integer id){

        dao.deleteById(Long.valueOf(id));

    }

    public String[][] getAllValuesOfUserr(){
        List<Userr> userrList = dao.getAll();
        return convertValuesToJTable(userrList, 5);
    }

    public String[][] searchByName(String name){
        List<Userr> userrList = dao.searchByName(name);
        return convertValuesToJTable(userrList, 5);
    }

    public String[][] convertValuesToJTable(List<Userr> userrList, int columnSize){
        String[][] data = new String[userrList.size()][columnSize];

        for(int i=0; i<userrList.size(); i++){
            data[i][0] = String.valueOf(userrList.get(i).getId());
            data[i][1]= userrList.get(i).getU_name();
            data[i][2] = userrList.get(i).getEmail();
            data[i][3] = userrList.get(i).getPass();
            data[i][4] = userrList.get(i).getU_type();
        }

        return data;
    }

}
