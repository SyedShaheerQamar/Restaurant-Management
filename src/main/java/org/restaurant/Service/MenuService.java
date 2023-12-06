package org.restaurant.Service;

import org.restaurant.DAO.MenuDAO;
import org.restaurant.Domain.Menu;

import java.util.List;
import java.util.stream.Collectors;

public class MenuService {

    private final MenuDAO dao = new MenuDAO();

    public void insertValuesIntoMenu(String name, String cat, Integer price){
        Menu menu = Menu.builder()
                .item_name(name)
                .category(cat)
                .r_id(1)
                .cost(price)
                .build();

        dao.insert(menu);
    }

    public void updateAllValues(Integer id, String name, String cat){
        Menu menu = Menu.builder()
                .item_name(name)
                .category(cat)
                .r_id(1)
                .build();

        dao.update(menu, Long.valueOf(id));
    }

    public void deleteById(Integer id){

        dao.deleteById(Long.valueOf(id));

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

    public String[][] getAllValuesOfMenu(){
        List<Menu> menuList = dao.getAll();
        return convertValuesToJTable(menuList, 5);
    }

    public String[][] searchByName(String name){
        List<Menu> menuList = dao.searchByName(name);
        return convertValuesToJTable(menuList, 5);
    }

    public String[][] convertValuesToJTable(List<Menu> menuList, int columnSize){
        String[][] data = new String[menuList.size()][columnSize];

        for(int i=0; i<menuList.size(); i++){
            data[i][0] = String.valueOf(menuList.get(i).getId());
            data[i][1]= menuList.get(i).getItem_name();
            data[i][2] = menuList.get(i).getCategory();
            data[i][3] = "Shaheen Shinwari";
            data[i][4] = String.valueOf(menuList.get(i).getCost());
        }

        return data;
    }

}
