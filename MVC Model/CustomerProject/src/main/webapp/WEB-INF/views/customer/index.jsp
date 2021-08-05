<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/27/2021
  Time: 3:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <title>Customer</title>
</head>
<body>
<center>
  <h1>Customer Management</h1>
  <h2><c:out value="${mess}"></c:out></h2>
  <h2>
    <a href="/customer?action=create">Add New Customer</a>
  </h2>
</center>
<div align="center">
  <table border="1" cellpadding="5">
    <caption><h2>List of Users</h2></caption>
    <%--        <c:if test='${requestScope["message"] != null}'>--%>
    <%--            <span class="message">${requestScope["message"]}</span>--%>
    <%--        </c:if>--%>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Email</th>
      <th>Salary</th>
      <th>Actions</th>
    </tr>
    <c:forEach var="user" items="${customers}">
      <tr>
        <td><c:out value="${user.getId()}"/></td>
        <td><c:out value="${user.getName()}"/></td>
        <td><c:out value="${user.getEmail()}"/></td>
        <td><fmt:formatNumber value="${user.getSalary()}" type="currency" currencySymbol="$" ></fmt:formatNumber></td>
        <td>
          <a href="/customer?action=edit&id=${user.id}">Edit</a>
          <a href="/customer?action=sent&id=${user.id}">Send</a>
          <a href="/customer?action=delete&id=${user.id}">Delete</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>