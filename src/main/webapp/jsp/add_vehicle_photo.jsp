<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>${vhc_create_title}</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<main>
    <section class="py-5 text-center text-white container">
        <div class="row py-lg-5">
            <div class="col-lg-8 col-md-8 mx-auto">
                <h2 class="fw-light text-white">${vhl_add_photo_title}</h2>
                <p class="lead text-muted mt-3">${vhl_add_photo_promo}</p>
            </div>

            <div class="text-secondary text-start col-md-7 col-lg-8 mx-auto">

            </div>
        </div>
    </section>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>