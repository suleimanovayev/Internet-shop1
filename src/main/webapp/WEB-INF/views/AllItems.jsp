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
    <title>AllItems</title>
</head>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>PRICE</th>
        <th>ADD</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>
                <c:out value="${item.id}" />
            </td>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
            <td>
                <a href="/Internetshop1_war_exploded/servlet/addItem?item.id=${item.id}">ADD</a>
        </tr>
    </c:forEach>
    <a href="/Internetshop1_war_exploded/servlet/bucket">Bucket</a>
</table>
</body>
</html>
