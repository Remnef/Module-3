<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 7/27/2021
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/customersManage">List All Users</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Customer
                </h2>
            </caption>
            <c:if test="${customer != null}">
                <input type="hidden" name="id" value="<c:out value='${customer.id}'/>"
            </c:if>
            <tr>
                <th>Customer Sent:</th>
                <td>
                    <input  type="text" name="salarysent" size="45"
                            value="<c:out value='${customer.salary}'/>"/>
                </td>
            </tr>
            <tr>
                <th>Customer Receive:</th>
                <td>
                    <input type="number" name="id2" size="45"/>

                </td>
            </tr>
            <tr>
                <th>Amount:</th>
                <td>
                    <input type="number" name="salary" size="15"/>

                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Send"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<%--//validate Backend--%>
<%--// checkTool--%>
<%--if (check){}else(execute)--%>
<%--//page 404--%>

</body>
</html>