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
                <li><a href="notes?action=view&amp;noteId=${note.noteId}">${note.title}</a><br></li>
            </c:forEach>
        </ul>
        <c:if test="${selectedUser eq null}">
            <h2>Add a New User</h2>
            <form action="users" method="post">
                Email: <input type="text" name="email" value=""><br>
                First Name: <input type="text" name="firstName" value=""><br>
                Last Name: <input type="text" name="lastName" value=""><br>
                Password: <input type="text" name="password" value=""><br>
                Role: <input type="text" name="role" value=""><br> 
                 <input type="submit" value="Add user" >
                <input type="hidden" name="action" value="add">
            </form>
        </c:if>
        <c:if test="${selectedUser ne null}">
            <h2>Edit the Selected User</h2>
            <form action="users" method="post">
                Email: <input type="text" name="email" value="${selectedUser.email}"><br>
                First Name: <input type="text" name="firstName" value="${selectedUser.firstName}"><br>
                Last Name: <input type="text" name="lastName" value="${selectedUser.lastName}"><br>
                Password: <input type="text" name="password" value="${selectedUser.password}"><br>
                Role: <input type="text" name="role" value=""><br> 
                 <input type="submit" value="Update" >
                <input type="hidden" name="action" value="add">
            </form>            

            <form action="users" method="post">
                <input type="hidden" name="action" value="Cancel">
                <input type="hidden" name="noteId" value="${selectedNote.noteId}">
                <input type="submit" value="Cancel">
            </form>
            <a href="notes">cancel edit</a>
        </c:if>
    </body> 
</html>
