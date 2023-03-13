package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.User;
import models.Role;

/**
 *
 * @author Majid
 */
public class RoleDB {

    public String roleName(int roleID) throws Exception {
               
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();        
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        String roleName="";     
        String sqlRole = "SELECT role_name FROM role WHERE role_id=?";
        
        try {     
            ps2 = con.prepareStatement(sqlRole);            
            rs2 = ps2.executeQuery();
            ps2.setInt(1, roleID);
            roleName = rs2.getString(1);
        } finally { 
            DBUtil.closeResultSet(rs2);
            DBUtil.closePreparedStatement(ps2);            
            cp.freeConnection(con);
        }
        return roleName;
    }
    
    
    public int roleID(String roleName) throws Exception {
               
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();        
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        int role_ID = 0;     
        String sqlRole = "SELECT role_id FROM role WHERE role_name=?";
        
        try {     
            ps2 = con.prepareStatement(sqlRole);            
            rs2 = ps2.executeQuery();
            ps2.setString(1, roleName);
            role_ID = rs2.getInt(1);
        } finally { 
            DBUtil.closeResultSet(rs2);
            DBUtil.closePreparedStatement(ps2);            
            cp.freeConnection(con);
        }
        return role_ID;
    }
}