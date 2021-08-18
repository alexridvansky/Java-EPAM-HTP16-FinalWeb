<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>${main_title}</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<main>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h2 class="fw-light text-white">${main_greeting}</h2>
                <p class="lead text-muted">${main_promotion}</p>
            </div>
        </div>
        <div class="row py-lg-5">
            <p class="lead text-muted">${main_step_0}</p>
            <br/>
            <p class="lead text-muted">${main_step_1}</p>
            <div>
                <img src="${abs}/img/car1.jpg" class="rounded"/>
            </div>
        </div>
        <div class="row py-lg-5">
            <p class="lead text-muted">${main_step_2}</p>
            <div>
                <img src="${abs}/img/car2.jpg" class="rounded"/>
            </div>
        </div>
        <div class="row py-lg-5">
            <p class="lead text-muted">${main_step_3}</p>
            <div>
                <img src="${abs}/img/car3.jpg" class="rounded"/>
            </div>
        </div>
        <div class="row py-lg-5">
            <p class="lead text-muted">${main_step_4_1} <a href="${pageContext.request.contextPath}/controller?command=go_to_sign_up_page">${main_step_4_2}</a> ${main_step_4_3} </p>
            <div>
                <img src="${abs}/img/car4.jpg" class="rounded"/>
            </div>
        </div>
    </section>

</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>

<script src="${abs}/js/history.js" type="text/javascript"></script>
