package org.restaurant.Service;

import org.restaurant.DAO.TableDAO;
import org.restaurant.Domain.Tables;

import java.util.List;

public class TableService {

    private final TableDAO dao = new TableDAO();

    public void insertValuesIntoTable(Integer cap, String avail){
        Tables tables = Tables.builder()
                .capacity(cap)
                .availability(avail)
                .r_id(1)
                .build();

        dao.insert(tables);
    }

    public void updateAllValues(Integer id, Integer cap, String avail){
        Tables tables = Tables.builder()
                .capacity(cap)
                .availability(avail)
                .build();

        dao.update(tables, Long.valueOf(id));
    }

    public void deleteById(Integer id){
        dao.deleteById(Long.valueOf(id));
    }

    public String[][] getAllValuesOfTable(){
        List<Tables> tablesList = dao.getAll();
        return convertValuesToJTable(tablesList, 4);
    }

    public String[][] searchByName(String name){
        List<Tables> tablesList = dao.searchByName(name);
        return convertValuesToJTable(tablesList, 4);
    }

    public String[][] convertValuesToJTable(List<Tables> tablesList, int columnSize){
        String[][] data = new String[tablesList.size()][columnSize];

        for(int i=0; i<tablesList.size(); i++){
            data[i][0] = String.valueOf(tablesList.get(i).getId());
            data[i][1]= String.valueOf(tablesList.get(i).getCapacity());
            data[i][2] = tablesList.get(i).getAvailability();
            data[i][3] = "Shaheen Shinwari";
        }

        return data;
    }

}
