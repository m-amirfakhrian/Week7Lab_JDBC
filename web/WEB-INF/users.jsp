        <%-- 
            Document   : users
            Created on : Mar 6, 2023, 11:08:08 AM
            Author     : Majid
        --%>

        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>The Users</title>
                <link rel="stylesheet" type="text/css" href="style.css">
            </head>
            <body>
      
                
            <c:if test="${users ne null}">
                    <form action="view" method="get">
                        <h2>List of users:</h2>
                    <table border="1">            
                        <tr>
                            <th>Email</th>
                            <th>First Name</th>
                            <th>Last Name</th> 
                            <th>Role</th>                      
                            <th>Edit</th>
                            <th>Delete</th>    
                        </tr>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>
                                    <c:out value = "${user.email}"/>
                                </td>
                                <td>
                                    <c:out value = "${user.firstName}"/>
                                </td>
                                <td>
                                    <c:out value = "${user.lastName}"/>
                                </td>
                                 <td>
                                    <c:out value = "${user.role.roleName}"/>
                                </td>
                                <td>
                                    <form  method="get"> 
                                        <input type="hidden" name="editUser" value="${user.email}">
                                        <input type="submit" value="Edit">
                                        <input type="hidden" name="action" value="showEdit">
                                    </form>
                                </td>
                                <td>
                                    <form method="get">
                                        <input type="hidden" name="deleteUser" value="${user.email}">
                                        <input type="submit" value="Delete">
                                        <input type="hidden" name="action" value="Delete">
                                    </form>
                                </td>
                            </tr> 
                        </c:forEach>
                    </table>
                        <input type="hidden" name="action" value="view">
                    </form>
                </c:if>

                <c:if test="${selectedUser eq null}">
                    <h2>Add a New User</h2>
                    <form action="users" method="post">
                        <table>
                            <tr>
                                <td>
                                    Email:
                                </td>
                                <td>
                                    <input type="text" name="email" value=""><br> 
                                </td>                      
                            </tr>
                            <tr>
                                <td>
                                    First Name:
                                </td>
                                <td>
                                    <input type="text" name="firstName" value=""><br> 
                                </td>                      
                            </tr>
                            <tr>
                                <td>
                                    Last Name:
                                </td>
                                <td>
                                    <input type="text" name="lastName" value=""><br> 
                                </td>                      
                            </tr>
                            <tr>
                                <td>
                                    Password:
                                </td>
                                <td>
                                    <input type="text" name="password" value=""><br> 
                                </td>                      
                            </tr>
                            <tr>
                                <td>
                                    Role:
                                </td>
                                <td>
                                <select name="role">
                                    <option value="1">system admin</option>
                                    <option value="2">regular user</option>                            
                                </select>
                            </td>                      
                            </tr>
                        </table>
                         <input type="submit" value="Add user" >
                        <input type="hidden" name="action" value="add">
                    </form>
                </c:if>
                <c:if test="${selectedUser ne null}">
                    <h2>Edit the Selected User</h2>
                    <form action="users" method="post">
                        <table>
                            <tr>
                                <td>
                                    Email:
                                </td>
                                <td>
                                    <input type="text" name="email" value="${selectedUser.email}"><br> 
                                </td>                      
                            </tr>
                            <tr>
                                <td>
                                    First Name:
                                </td>
                                <td>
                                    <input type="text" name="email" value="${selectedUser.firstName}"><br> 
                                </td>                      
                            </tr>
                            <tr>
                                <td>
                                    Last Name:
                                </td>
                                <td>
                                    <input type="text" name="email" value="${selectedUser.lastName}"><br> 
                                </td>                      
                            </tr>
                            <tr>
                                <td>
                                    Password:
                                </td>
                                <td>
                                    <input type="text" name="email" value="${selectedUser.password}"><br> 
                                </td>                      
                            </tr>
                            <tr>
                                <td>
                                    Role:
                                </td>
                                <td>
                                    <input type="text" name="email" value="${selectedUser.role.role_name}"><br> 
                                </td>                      
                            </tr>
                        </table>
                        <input type="submit" value="Update" >
                        <input type="hidden" name="action" value="add">
                    </form>            

                    <form action="users" method="post">
                        <input type="hidden" name="action" value="Cancel">
                        <input type="hidden" name="email" value="${selectedUser.email}">
                        <input type="submit" value="Cancel">
                    </form>
                    <a href="notes">cancel edit</a>
                </c:if>
            </body> 
        </html>
