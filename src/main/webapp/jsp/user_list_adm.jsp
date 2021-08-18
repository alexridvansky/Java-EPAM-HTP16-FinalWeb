<%@ taglib prefix="ex" uri="custom_tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>Print out all users page</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<main class="flex-shrink-0">
    <div class="container-fluid" style="padding: 72px 0px 0;">


        <c:if test="${requestScope.users == null}">
            <jsp:forward page="${abs}/controller?command=show_user_list_admin"/>
        </c:if>

        <table class="table table-dark table-striped table-hover">
            <thead class="sticky-top" style="padding: 72px 0px 0;">
            <tr>
                <th></th>
                <th class="th-sm"><input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for logins.."></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th>${id}</th>
                <th>${login}</th>
                <th>${ads}</th>
                <th>${email}</th>
                <th>${phone}</th>
                <th>${reg_date}</th>
                <th>${role}</th>
                <th>${state}</th>
            </tr>
            </thead>
            <tbody id="myTable">
            <jsp:useBean id="users" scope="request" type="java.util.List"/>
            <c:forEach items="${users}" var="user">

            <tr>
                <td>${user.getUserId()}</td>
                <td>${user.getLogin()}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.role == 'USER'}">
                            <ex:AdCounter userId="${user.getUserId()}"/>
                        </c:when>
                    </c:choose>
                </td>
                <td>${user.getEmail()}</td>
                <td>${user.getPhone()}</td>
                <td>${user.getRegDate()}</td>
                <td>
                    <div class="dropdown">
                        <c:choose>
                            <c:when test="${user.getRole() eq 'ROOT'}">
                                <button class="w-100 btn btn-danger btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getRole()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item active disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=ROOT">ROOT</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=MODERATOR">MODERATOR</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=USER">USER</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=GUEST">GUEST</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getRole() eq 'MODERATOR'}">
                                <button class="w-100 btn btn-warning btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getRole()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=ROOT">ROOT</a>
                                    </li>
                                    <li><a class="dropdown-item active disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=MODERATOR">MODERATOR</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=USER">USER</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=GUEST">GUEST</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getRole() eq 'USER'}">
                                <button class="w-100 btn btn-info btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getRole()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=ROOT">ROOT</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=MODERATOR">MODERATOR</a>
                                    </li>
                                    <li><a class="dropdown-item active disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=USER">USER</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=GUEST">GUEST</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getRole() eq 'GUEST'}">
                                <button class="w-100 btn btn-secondary btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getRole()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=ROOT">ROOT</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=MODERATOR">MODERATOR</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=USER">USER</a>
                                    </li>
                                    <li><a class="dropdown-item active disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_role&username=${user.getLogin()}&new_role=GUEST">GUEST</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </td>
                <td>
                    <div class="dropdown">
                        <c:choose>
                            <c:when test="${user.getState() == 'ENABLED' and user.getRole() != 'ROOT'}">
                                <button class="w-100 btn btn-success btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getState()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item active disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=CONFIRM">CONFIRM</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=BANNED">BANNED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=DISABLED">DISABLED</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getState() == 'ENABLED' && user.getRole() == 'ROOT'}">
                                <button class="w-100 btn btn-success btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getState()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=CONFIRM">CONFIRM</a>
                                    </li>
                                    <li><a class="dropdown-item active disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=BANNED">BANNED</a>
                                    </li>
                                    <li><a class="dropdown-item disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=DISABLED">DISABLED</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getState() eq 'CONFIRM'}">
                                <button class="w-100 btn btn-warning btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getState()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item active disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=CONFIRM">CONFIRM</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=BANNED">BANNED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=DISABLED">DISABLED</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getState() eq 'BANNED'}">
                                <button class="w-100 btn btn-danger btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getState()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=CONFIRM">CONFIRM</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item active disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=BANNED">BANNED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=DISABLED">DISABLED</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.getState() eq 'DISABLED'}">
                                <button class="w-100 btn btn-secondary btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${user.getState()}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=CONFIRM">CONFIRM</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=BANNED">BANNED</a>
                                    </li>
                                    <li><a class="dropdown-item active disabled"
                                           href="${pageContext.request.contextPath}/controller?command=change_user_state&username=${user.getLogin()}&new_state=DISABLED">DISABLED</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </td>
            </tr>
            </c:forEach>

        </table>
        <br/>

        <c:choose>
            <c:when test="${users.size() > 0}">
                ${users.size()} ${rec_found}<br/>
            </c:when>
            <c:otherwise>
                ${no_rec_found}<br/>
            </c:otherwise>
        </c:choose>
    </div>
</main>


<%@include file="fragment/footer.jspf" %>

</body>
</html>
