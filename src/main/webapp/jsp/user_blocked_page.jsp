<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>${user_blocked_title}</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<main>
    <section class="py-5 mt-2 text-center text-white container">
        <div class="col-lg-8 col-md-8 mx-auto">
            <h2 class="fw-light py-3 text-white">${user_blocked_title}</h2>

            <div class="col-12 mt-4">
                <p class="lead text-muted">${user_blocked_promo}</p>
            </div>

        </div>

    </section>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>