<%--
  Created by IntelliJ IDEA.
  User: ngominhhieu
  Date: 18/02/2021
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Quan ly con nguoi</h1>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>YearOfBirth</td>
        <td>Address</td>
    </tr>
    <c:forEach items="${humanList}" var="human">
        <tr>
            <td>${human.getId()}</td>
            <td>${human.getName()}</td>
            <td>${human.getYearOfBirth()}</td>
            <td>${human.getAddress()}</td>
            <td><a href="human?action=edit&id=${human.getId()}">edit</a> </td>
            <td><a href="human?action=delete&id=${human.getId()}">delete</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
