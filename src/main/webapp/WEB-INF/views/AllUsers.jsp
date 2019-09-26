<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop3.model.User>"/>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 18.09.2019
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>AllUsers</title>
</head>
<body>
HELLO MATES, WELCOME TO THE ALL USERS PAGE
<table border="1">
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Name</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.id}" />
            </td>
            <td>
                <c:out value="${user.login}" />
            </td>
            <td>
                <c:out value="${user.name}" />
            </td>
            <td>
                <a href="/Internetshop1_war_exploded/servlet/deleteUser?user.id=${user.id}">Delete</a>
        </tr>
        </tr>
    </c:forEach>
</table>
</body>
</html>
