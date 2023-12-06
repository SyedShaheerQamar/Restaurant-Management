package org.restaurant.Service;

import org.restaurant.DAO.UserrDAO;
import org.restaurant.Domain.Userr;

public class AuthenticationService {

    private final UserrDAO dao = new UserrDAO();

    public Userr getEmailAndPass(String email, String pass){
        Userr userr = dao.getEmailAndPass(email, pass);

        if(userr != null){
            return  userr;
        }
        return null;
    }

}
