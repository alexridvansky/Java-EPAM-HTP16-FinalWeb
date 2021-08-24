<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>${confirm_title}</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<main>
    <section class="py-5 mt-2 text-center text-white container">
        <div class="col-lg-8 col-md-8 mx-auto">
            <h2 class="fw-light py-3 text-white">${confirm_title}</h2>

            <div class="col-12 mt-4">
                <p class="lead text-muted">${confirm_promo}</p>
                <p class="lead mt-5">
                    <a class="btn btn-lg btn-secondary fw-bold border-white bg-white text-dark disabled">${conf_code}</a>
                </p>
            </div>

        </div>

    </section>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>