<%--
  Created by IntelliJ IDEA.
  User: spetr
  Date: 6/24/2021
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>

<!DOCTYPE html>
<html>
<head>
    <title>CarSales - register new user</title>
    <link rel="stylesheet" href="${abs_path}/css/main_style.css">
</head>
<body>
<h3><%= "new user registration form" %></h3>
<form action="${abs_path}/controller" method="get">
    <input type="hidden" name="command" value="go_to_sign_up_result_page">
    <input type="text" name="username"/> username (4-16 char length, starts from letter)
    <br>
    <input type="password" name="password"/> enter password
    <br>
    <input type="password" name="password_again"/> reenter password
    <br>
    <input type="email" name="email"/> email
    <br>
    <input type="text" name="phone"/> phone number (<12 symbols)
    <br>
    <input type="submit" value="create">
</form>
<br/>
