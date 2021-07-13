<%--
  Created by IntelliJ IDEA.
  User: spetr
  Date: 7/4/2021
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Print out all users page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_style.css">

</head>

<body>
<jsp:include page="header_admin.jsp"/>
<h2>

</h2>

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
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.getUserId()}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getRole()}</td>
            <td>${user.getState()}</td>
            <td>${user.getEmail()}</td>
            <td>${user.getPhone()}</td>
            <td>${user.getRegistrationDate()}</td>
        </tr>
    </c:forEach>

</table>
<br/>

${users.size()} records been found<br/>

</body>
</html>
