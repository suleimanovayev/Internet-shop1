<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your Order</title>
</head>
<body>
Bucket
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="item" items="${listItem}">
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
            <a href="/Internetshop1_war_exploded/servlet/deleteItem?item.id=${item.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    <a href="/Internetshop1_war_exploded/servlet/completeOrder">CompleteOrder</a>
</table>
</body>
</html>
