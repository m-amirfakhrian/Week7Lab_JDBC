package models;

import java.io.Serializable;

/**
 *
 * @author Majid
 */
public class Role implements Serializable {
    private int roleID;
    private String roleName;

    public Role(int roleID, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int role_id) {
        this.roleID = role_id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" + "role_id=" + roleID + ", role_name=" + roleName + '}';
    }
    
    
    
    
    
    
    
}