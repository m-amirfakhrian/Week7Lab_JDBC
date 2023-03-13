package services;

import dataaccess.UserDB;
import java.util.ArrayList;
//import java.util.List;
import models.Role;
import models.User;

public class UserService {
    public User getUser(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }
    
    public ArrayList<User> getAllUsers() throws Exception {
        UserDB userDB = new UserDB();
        ArrayList<User> users = userDB.getAllUsers();
        return users;
    }
    
    public void insert(User user) throws Exception {
        //User user = new User(email, firstName, lastName, password, role);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
    
    public void update(User user) throws Exception {
        //User user = new User(email, firstName, lastName, password, role);
        UserDB userDB = new UserDB();
        userDB.update(user);
    }
    
    public void delete(User user) throws Exception {
        //User user = new User();        
        UserDB userDB = new UserDB();
        userDB.delete(user);
    }
    
}
