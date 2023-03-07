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
public class UserDB {

    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        
        String sql = "SELECT * FROM user";
        String sqlRole = "SELECT role_name FROM role WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);            
            rs = ps.executeQuery();
            while (rs.next()) {                
                String email = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String password = rs.getString(4);
                int roleID = rs.getInt(5);
                
                    ps2 = con.prepareStatement(sqlRole);            
                    rs2 = ps.executeQuery();
                    ps2.setInt(1, roleID);
                    String roleName = rs2.getString(1);
                    Role role = new Role(roleID,roleName);
                    User user = new User(email, firstName, lastName, password, role);
                    users.add(user);    
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            
            DBUtil.closeResultSet(rs2);
            DBUtil.closePreparedStatement(ps2);
            
            cp.freeConnection(con);
        }

        return users;
    }

    public User get(String email) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps2 = null;
        ResultSet rs2 = null;
        String sql = "SELECT * FROM user WHERE email=?";
        String sqlRole = "SELECT role_name FROM role WHERE role_id=?";
        User user = new User();
        
        try {
            ps = con.prepareStatement(sql);            
            rs = ps.executeQuery();
            if (rs.next()) {                
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String password = rs.getString(4);
                int roleID = rs.getInt(5);
                
                    ps2 = con.prepareStatement(sqlRole);            
                    rs2 = ps.executeQuery();
                    ps2.setInt(1, roleID);
                    String roleName = rs2.getString(1);
                    Role role = new Role(roleID,roleName);
                    user = new User(email, firstName, lastName, password, role);                     
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            
            DBUtil.closeResultSet(rs2);
            DBUtil.closePreparedStatement(ps2);
            
            cp.freeConnection(con);
        }
         return user;
    }

    public void insert(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO user (email, firstName, lastName, password, roleID) VALUES (?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole().getRole_name());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void update(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE user SET firstName=?, lastName=?, password=?, role=? WHERE email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRole().getRole_id());
            ps.setString(5, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM user WHERE email=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

}
