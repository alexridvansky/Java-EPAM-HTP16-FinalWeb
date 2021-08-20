<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>${pass_rec_title}</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<main>
    <section class="py-5 mt-2 text-center text-white container">
        <div class="col-lg-8 col-md-8 mx-auto">
            <h2 class="fw-light py-3 text-white">${pass_rec_title}</h2>
            <form class="needs-validation row g-3" action="${abs}/controller" method="post" novalidate>
                <input type="hidden" name="command" value="password_recovery">

                <div class="col-12 mt-4">
                    <p class="lead text-muted">${pass_rec_promo}</p>

                    <div class="input-group has-validation">
                        <input type="text" class="form-control" id="username" name="username"
                               placeholder="${login}" required pattern="^[a-zA-Z0-9_]{4,16}$">
                        <div class="invalid-feedback">
                            ${username_req}
                        </div>
                    </div>
                </div>

                <div class="col-12">
                    <button class="w-100 py-1 align-content-lg-center btn btn-primary btn-lg"
                            type="submit">${recover}</button>
                </div>
            </form>

        </div>

    </section>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>