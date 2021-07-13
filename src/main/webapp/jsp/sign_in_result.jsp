<%--
  Created by IntelliJ IDEA.
  User: spetr
  Date: 7/5/2021
  Time: 12:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>SignIn result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table_style.css">
</head>
<body>
<jsp:include page="header_admin.jsp"/>
<table>
    <tr>
        <th>id</th>
        <th>login</th>
        <th>role</th>
        <th>state</th>
        <th>e-mail</th>
        <th>phone</th>
        <th>registration date</th>
    </tr>
    <tr>
        <td>${user.getUserId()}</td>
        <td>${user.getLogin()}</td>
        <td>${user.getRole()}</td>
        <td>${user.getState()}</td>
        <td>${user.getEmail()}</td>
        <td>${user.getPhone()}</td>
        <td>${user.getRegistrationDate()}</td>
    </tr>
</table>
<jsp:include page="footer.jsp"/>
</body>
</html>
