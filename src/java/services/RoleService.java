package services;

import dataaccess.RoleDB;
import java.util.ArrayList;
import java.util.List;
import models.Role;

/**
 *
 * @author Majid
 */
public class RoleService {
    

    public String getRoleName(int roleID) throws Exception {
        RoleDB roleDB = new RoleDB();
        String roleName = roleDB.roleName(roleID);   
        return roleName;
    }

    public int getRoleID(String roleName) throws Exception {
        RoleDB roleDB = new RoleDB();
        int roleID = roleDB.roleID(roleName);   
        return roleID;
    }

}
