<%--
  Created by IntelliJ IDEA.
  User: spetr
  Date: 7/9/2021
  Time: 12:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUpResult</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_style.css">
</head>
<body>
Username valid? ${isUsernameValid} <br>
Username free? ${isUsernameFree} <br>
Passwords equal? ${isPasswordsEqual} <br>
Password valid? ${isPasswordValid} <br>
Email valid? ${isEmailValid} <br>
Email free? ${isEmailFree} <br>
Phone valid? ${isPhoneValid} <br>
Phone free? ${isPhoneFree} <br><br>
New user created? ${isUserCreated}<br><br>
</body>
</html>
