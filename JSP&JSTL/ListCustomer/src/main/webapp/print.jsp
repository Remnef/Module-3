<%@ page import="com.codegym.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/25/2021
  Time: 6:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>$Title$</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
<h1>Danh sách khách hàng</h1>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Stt</th>
        <th scope="col">Tên</th>
        <th scope="col">Ngày sinh</th>
        <th scope="col">Địa chỉ</th>
        <th scope="col">Luong</th>
        <th scope="col">Ảnh</th>
    </tr>
    </thead>
    <%--    <c:out value="${list}"></c:out>--%>
    <c:forEach items="${list}" var="cus">
        <tbody>
        <tr>
            <th scope="row">${cus.id}</th>
            <td>${cus.name}</td>
            <td>${cus.dateOfBirth}</td>
            <td>${cus.address}</td>
            <td><fmt:formatNumber value="${cus.salary}" type="currency" currencySymbol="VND"></fmt:formatNumber></td>
            <td><img src="" alt=""></td>
        </tr>
        </tbody>
    </c:forEach>
</table>
</body>
</html>