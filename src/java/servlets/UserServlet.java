package servlets;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.UserService;
import services.RoleService;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService us = new UserService();

        try {
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            //String email = (String) session.getAttribute("email");
            ArrayList<User> users = us.getAllUsers();
            request.setAttribute("users", users);            
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            try {
                String email = request.getParameter("email");
                User user = us.getUser(email);
                Role role = user.getRole();
                request.setAttribute("selectedUser", user);                
                request.setAttribute("roleName", role.getRoleName());
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            
            HttpSession session = request.getSession();
            
            UserService us = new UserService();
            RoleService rs = new RoleService();
            ArrayList<User> users = us.getAllUsers();
            // action must be one of: Add, Edit, Delete
            String action = request.getParameter("action");
            
            String email = (String) session.getAttribute("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");            
            String roleName = request.getParameter("roleName");
                       
            String message="";
            int roleID = rs.getRoleID(roleName);
            
            Role role = new Role(roleID, roleName);
            User user = new User(email, firstName, lastName, password, role);
            try {
                switch (action) {
                    case "add":
                        if (email.equals(null) || email.equals("")
                                || firstName.equals(null) || firstName.equals("")
                                || lastName.equals(null) || lastName.equals("")
                                || password.equals(null) || password.equals("")) {
                            message = "At least one field did not correctly filled! Please fill out the form completely.";
                            request.setAttribute("message", message);
                        }else if (!users.isEmpty()) {
                            boolean alreadyExist = false;
                            int i = 0;
                            while(i < users.size()&& alreadyExist==false){                                
                                if (email.equals(users.get(i++).getEmail())) {
                                    message = "The email already registered!!";
                                    request.setAttribute("message", message);
                                    alreadyExist = true;
                                }
                            }
                            if (alreadyExist == false) {
                                us.insert(user);
                                message = "New user added.";
                                request.setAttribute("message", message);
                            }
                        }else{
                            us.insert(user);
                            message = "New user added.";
                            request.setAttribute("message", message);
                        }                        
                        break;
                    case "update":
                        us.update(user);
                        message = "User updated.";
                        request.setAttribute("message", message);
                        break;
                    case "delete":
                        us.delete(user);
                        message = "User deleted.";
                        request.setAttribute("message", message);
                }
                //request.setAttribute("message", action);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "error");
            }

            try {
                users = us.getAllUsers();
                request.setAttribute("users", users);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "error");
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
