<%--@elvariable id="e" type="java.lang.Exception"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="../fragment/meta.jsp" %>

    <title>Error page</title>

</head>

<%@include file="../fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="../fragment/header.jspf" %>

<main>
    <section class="py-5 mt-3 text-center container">
        <div class="row">
            <div class="col-12">
                <h2 class="fw-light text-danger">${error500}</h2>
                <p class="lead text-danger">${feedback_msg}</p>
            </div>
        </div>
        <div class="row">
            Request from ${pageContext.errorData.requestURI} is failed
            <br>
            Servlet name or type: ${pageContext.errorData.servletName}
            <br>
            Status code: ${pageContext.errorData.statusCode}
            <br>
            Exception: ${pageContext.errorData.throwable}
        </div>
        <div class="col-12">
            <div class="col-12 mt-3">
                <p class="lead text-danger">${e}</p>
            </div>
        </div>
    </section>

</main>

<%@include file="../fragment/footer.jspf" %>

</body>
</html>
