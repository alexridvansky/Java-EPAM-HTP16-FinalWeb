<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="abs">${pageContext.request.contextPath}</c:set>

<!DOCTYPE html>
<html>
<body>
<c:redirect url="${abs}/controller?command=go_to_main_page"/>
<%--<jsp:forward page="${abs}/controller?command=go_to_main_page"/>--%>
</body>
</html>