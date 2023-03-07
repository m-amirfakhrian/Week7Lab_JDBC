package servlets;

import java.io.IOException;
import java.util.List;
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

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService us = new UserService();

        try {
            HttpSession session = request.getSession();
            //String email = (String) session.getAttribute("email");
            List<User> users = us.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            try {
                int id = Integer.parseInt(request.getParameter("noteId"));
                User user = ns.get(id);
                request.setAttribute("selectedUser", user);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        UserService us = new UserService();

        // action must be one of: Add, Edit, Delete
        String action = request.getParameter("action");
        
        String email = (String) session.getAttribute("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String roleName = request.getParameter("roleName");        

        try {
            switch (action) {
                case "add":
                    us.insert(email, firstName, lastName, password, role);
                    break;
                case "update":
                    us.update(email, firstName, lastName, password, role);
                    break;
                case "delete":
                    us.delete(email, firstName, lastName, password, role);
            }
            request.setAttribute("message", action);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            List<User> notes = us.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

}
