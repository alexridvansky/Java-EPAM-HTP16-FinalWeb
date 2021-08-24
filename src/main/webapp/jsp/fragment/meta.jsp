<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

<c:set var="abs">${pageContext.request.contextPath}</c:set>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="theme-color" content="#7952b3">
<link rel="icon" type="image/png" href="${abs}/ico/site_logo.png">

<link rel="stylesheet" href="${abs}/css/bootstrap.min.css">
<link rel="stylesheet" href="${abs}/css/sticky-footer-navbar.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css"
      integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="GUEST">


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization/locale" var="localization"/>
<fmt:setBundle basename="regexp" var="regexp"/>
