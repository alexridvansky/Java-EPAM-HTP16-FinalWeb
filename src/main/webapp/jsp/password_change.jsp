<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>${reg_title}</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-8 col-md-8 mx-auto">
                <h2 class="fw-light text-white">${pass_change_title}</h2>
                <p class="lead text-muted">${pass_change_promo}</p>
            </div>

            <div class="text-secondary text-start col-md-7 col-lg-8 mx-auto">
                <form class="needs-validation" action="${abs}/controller" method="post" novalidate autocomplete="off">
                    <input type="hidden" name="command" value="password_change">

                    <div class="row g-3">

                        <div class="col-12">
                            <label for="old_pass" class="form-label">${old_pass}</label>
                            <div class="input-group has-validation">
                                <input type="password" class="form-control" id="old_pass" name="old_pass"
                                       placeholder="${old_pass}" autocomplete="off"
                                       pattern="${password_regexp}">
                                <div class="invalid-feedback">
                                    ${old_pass_req}
                                </div>
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="new_pass" class="form-label">${new_pass}</label>
                            <input type="password" class="form-control" id="new_pass" name="new_pass"
                                   placeholder="${new_pass}" value="" required autocomplete="off" pattern="${password_regexp}">
                            <div class="invalid-feedback">
                                ${new_pass_req}
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="new_pass_repeat" class="form-label">${new_pass_repeat}</label>
                            <input type="password" class="form-control" id="new_pass_repeat" name="new_pass_repeat"
                                   placeholder="${new_pass_repeat}" value="" required autocomplete="off" pattern="${password_regexp}">
                            <div class="invalid-feedback">
                                ${passrep_req}
                            </div>
                        </div>

                    </div>

                    <hr class="my-4">
                    <button class="w-100 align-content-lg-center btn btn-primary btn-lg" type="submit">${change}</button>

                </form>

            </div>

        </div>

    </section>

</main>

<script>

</script>

<%@include file="fragment/footer.jspf" %>

</body>
</html>
