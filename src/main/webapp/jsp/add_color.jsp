<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>${make_title}</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<link rel="stylesheet" href="${abs}/css/alert.css">

<main>
    <section class="py-5 mt-2 text-center text-white container">
        <div class="col-lg-8 col-md-8 mx-auto">
            <h2 class="fw-light py-3 text-white">${color_title}</h2>
            <form class="needs-validation row g-3" action="${abs}/controller" method="post" novalidate>
                <input type="hidden" name="command" value="add_new_color">

                <div class="col-12 mt-4">
                    <p class="lead text-muted">${enter_new_color}</p>

                    <div class="input-group has-validation">
                        <input type="text" class="form-control" id="color" name="color"
                               placeholder="${color}" required pattern="${color_regexp}">
                        <div class="invalid-feedback">
                            ${color_sel_req}
                        </div>
                    </div>
                </div>

                <div class="col-12">
                    <button class="w-100 py-1 align-content-lg-center btn btn-primary btn-lg"
                            type="submit">${create}</button>
                </div>
            </form>

            <div class="col-12">
                <hr class="col-12 text-white">
            </div>

            <div class="col-12">
                <p class="lead text-muted">${new_color_check}</p>
                <select name="" class="form-control secondList selectFilter mt-3">
                    <option value="" selected disabled>${select_color}</option>
                    <%--@elvariable id="colors" type="java.util.List"--%>
                    <%--@elvariable id="color_entry" type="by.spetr.web.model.entity.type.VehicleColor"--%>
                    <c:forEach items="${colors}" var="color_entry">
                        <option value="${color_entry.colorId}">${color_entry.value}</option>
                    </c:forEach>
                </select>

                <div class="invalid-feedback">
                    ${color_sel_req}
                </div>
            </div>

        </div>

    </section>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>