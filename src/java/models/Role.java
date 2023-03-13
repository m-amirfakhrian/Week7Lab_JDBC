package models;

import java.io.Serializable;

/**
 *
 * @author Majid
 */
public class Role implements Serializable {
    private int role_id;
    private String role_name;

    public Role(int role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }

    public int getRoleID() {
        return role_id;
    }

    public void setRoleID(int role_id) {
        this.role_id = role_id;
    }

    public String getRoleName() {
        return role_name;
    }

    public void setRoleName(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "Role{" + "role_id=" + role_id + ", role_name=" + role_name + '}';
    }
    
    
    
    
    
    
    
}