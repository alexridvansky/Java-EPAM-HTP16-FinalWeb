<%--@elvariable id="reg_data" type="by.spetr.web.model.form.UserRegForm"--%>
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
                <h2 class="fw-light text-white">${reg_greeting}</h2>
                <p class="lead text-muted">${reg_promotion}</p>
            </div>

            <div class="text-secondary text-start col-md-7 col-lg-8 mx-auto">
                <form class="needs-validation" action="${abs}/controller" method="post" novalidate autocomplete="off">
                    <input type="hidden" name="command" value="create_user">

                    <div class="row g-3">

                        <div class="col-12">
                            <label for="reg_username" class="form-label">${login}</label>
                            <div class="input-group has-validation">
                                <input type="text" class="form-control" id="reg_username" name="reg_username"
                                       value="${reg_data.login}" placeholder="${login}" autocomplete="off"
                                       pattern="${username_regexp}">
                                <div class="invalid-feedback">
                                    ${username_req}
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <label for="reg_password" class="form-label">${pass}</label>
                            <input type="password" class="form-control" id="reg_password" name="password"
                                   placeholder="${pass}" value="" required autocomplete="off" pattern="${password_regexp}">
                            <div class="invalid-feedback">
                                ${pass_req}
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <label for="reg_password_again" class="form-label">${pass_rep}</label>
                            <input type="password" class="form-control" id="reg_password_again" name="password_again"
                                   placeholder="${pass_rep}" value="" required autocomplete="off" pattern="${password_regexp}">
                            <div class="invalid-feedback">
                                ${passrep_req}
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="email" class="form-label">${email}</label>
                            <input type="email" class="form-control" id="email" name="email"
                                   placeholder="you@example.com" value="${reg_data.email}" required=""
                                   pattern="${email_regexp}">
                            <div class="invalid-feedback">
                                ${email_req}
                            </div>
                        </div>

                        <div class="col-12">
                            <label for="phone" class="form-label">${phone}</label>
                            <input type="text" class="form-control" id="phone" name="phone" value="${reg_data.phone}"
                                   placeholder="+375xxxxxxxxx" required="" pattern="${phone_regexp}">
                            <div class="invalid-feedback">
                                ${phone_req}
                            </div>
                        </div>

                        <div class="col-12">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" required="">
                            <label class="form-check-label" for="flexCheckDefault">
                                ${terms}
                            </label>
                            <div class="invalid-feedback">
                                ${terms_req}
                            </div>
                        </div>

                    </div>

                    <hr class="my-4">
                    <button class="w-100 align-content-lg-center btn btn-primary btn-lg" type="submit">${create}</button>

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
