<%--
  Created by IntelliJ IDEA.
  User: spetr
  Date: 6/24/2021
  Time: 1:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="by.spetr.web.util.BCrypt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>

<html>
    <head>
        <title>CarSales - login page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_style.css">
        <style>
            div.d1 {
                width: 250px;
                height: 150px;
                padding: 40px;
                border: 2px solid white;
            }
        </style>
    </head>
<body>
<jsp:include page="header_admin.jsp"/>

<br/>
<div class="d1">
    <form action="${abs_path}/controller" method="post">
        <input type="hidden" name="command" value="go_to_sign_in_result_page">
        username<input type="text" name="login" value=""/>
        <br/>
        password<input type="password" name="pass" value=""/>
        <input type="submit" value="Sign in"/>
        <%!
            String toUpper(String s) {
                return s.toUpperCase();
            }
        %>
        <%
            String pass = request.getParameter("pass");
            String passHash = BCrypt.hashpw(pass, BCrypt.gensalt());
            request.setAttribute("pass", passHash);
            String passResult = request.getParameter("pass");
        %>
    </form>
    <br>

    <form action="${abs_path}/controller" method="get">
        First time here?
        <input type="hidden" name="command" value="go_to_sign_up_page">
        <input type="submit" value="Sign up"/>
    </form>

    <form action="${abs_path}/controller" method="get">
        Print out users list
        <input type="hidden" name="command" value="print_out_all_users">
        <input type="submit" value="print">
    </form>

</div>

language: <%= request.getLocale() %> <br/>
browser: <%= request.getHeader("User-Agent")%>

<br/>


<br/>
<a>Don't want to get into trouble? </a>
<a href="http://geely.by">Run away</a>
<br/>
<jsp:include page="footer.jsp"/>
</body>
</html>

<script type="text/javascript">
    function openPage(pageURL)
    {
        window.location.href = pageURL;
    }
</script>
