<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="abs_path">${pageContext.request.contextPath}</c:set>

<!DOCTYPE html>
<html>
    <body>
    ${abs_path}
        <jsp:forward page="/jsp/sign_in.jsp"/>
    <%--
        <jsp:forward page="/WEB-INF/jsp/sign_in.jsp"/>
        --%>
    </body>
</html>