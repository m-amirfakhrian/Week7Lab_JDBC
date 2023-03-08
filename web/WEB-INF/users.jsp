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
    </head>
    <body>

        <h1>Manage Users</h1>
        <p>
            <c:if test="${message eq 'add'}">User created</c:if>
            <c:if test="${message eq 'update'}">User Edited</c:if>
            <c:if test="${message eq 'delete'}">User deleted</c:if>
            <c:if test="${message eq 'error'}">Sorry, something went wrong.</c:if>
            </p>
            <ul>
                
            <c:forEach items="${user}" var="user">
                <li><a href="users?action=view&amp;email=${user.email}">${user.firstName}</a><br></li>
            </c:forEach>
        </ul>
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
                            <input type="text" name="email" value=""><br> 
                        </td>                      
                    </tr>
                    <tr>
                        <td>
                            Last Name:
                        </td>
                        <td>
                            <input type="text" name="email" value=""><br> 
                        </td>                      
                    </tr>
                    <tr>
                        <td>
                            Password:
                        </td>
                        <td>
                            <input type="text" name="email" value=""><br> 
                        </td>                      
                    </tr>
                    <tr>
                        <td>
                            Role:
                        </td>
                        <td>
                            <input type="text" name="email" value=""><br> 
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
                            <input type="text" name="email" value="${selectedUser.role}"><br> 
                        </td>                      
                    </tr>
                </table>
                <input type="submit" value="Update" >
                <input type="hidden" name="action" value="add">
            </form>            

            <form action="users" method="post">
                <input type="hidden" name="action" value="Cancel">
                <input type="hidden" name="noteId" value="${selectedUser.email}">
                <input type="submit" value="Cancel">
            </form>
            <a href="notes">cancel edit</a>
        </c:if>
    </body> 
</html>
