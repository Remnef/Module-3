<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/25/2021
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="container">
    <h1>Customers</h1>
    <form method="post" class="row">
        <div class="form-group col-12 col-sm-12 col-md-6">
            <label>Họ và tên: </label>
            <input type="text" name="name" class="form-control">
        </div>
        <div class="form-group col-12 col-sm-12 col-md-6">
            <label>Địa chỉ: </label>
            <input type="text" name="address" class="form-control">
        </div>
        <div class="form-group col-12 col-sm-12 col-md-6">
            <label>Email: </label>
            <input type="text" name="email" class="form-control">
        </div>
        <div class="form-group col-12 col-sm-12 col-md-6">
            <label>Giới tính: </label>
            <div style="position: relative; top: 7px">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" id="inlineRadio1" value="male">
                    <label class="form-check-label" for="inlineRadio1">Nam</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="gender" id="inlineRadio2" value="famale">
                    <label class="form-check-label" for="inlineRadio2">Nữ</label>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary" >Submit</button>
        <div>
            <c:if test='${requestScope["message"] != null}'>
                <span class="message">${requestScope["message"]}</span>
            </c:if>
        </div>
    </form>
    <%--    <p>--%>
    <%--        <a href="/customers?action=create">Create new customer</a>--%>
    <%--    </p>--%>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Address</th>
            <th scope="col">Gender</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <c:forEach items='${requestScope["users"]}' var="user">
            <tbody>
            <tr>
                <td scope="row">${user.getId()}</td>
                <td><a href="/users?action=view&id=${user.getId()}" style="text-decoration: none;">${user.getName()}</a></td>
                <td>${user.getEmail()}</td>
                <td>${user.getAddress()}</td>
                <td>
                    <c:if test="${user.getGender()}==true">Male</c:if>
                    <c:if test="${user.getGender()}==false">Female</c:if>
                </td>

                <td><a href="/users?action=edit&id=${user.getId()}" class="btn-self btn-edit-self"><i class="fa fa-edit"></i>&nbsp;edit</a></td>
                <td><a href="/users?action=delete&id=${user.getId()}" class="btn-self btn-delete-self"><i class="fa fa-trash"></i>&nbsp;delete</a></td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
</body>
</html>