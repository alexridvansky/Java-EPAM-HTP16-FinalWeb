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
            <h2 class="fw-light py-3 text-white">${model_title}</h2>
            <form class="needs-validation row g-3" action="${abs}/controller" method="post" novalidate>
                <input type="hidden" name="command" value="add_new_model">
                <div class="col-12">
                    <p class="lead text-muted">${enter_new_model}</p>
                    <select name="make_id" class="form-control secondList selectFilter mt-3" required>
                        <option value="${last_make}">${select_model}</option>
                        <%--@elvariable id="makes" type="java.util.List"--%>
                        <%--@elvariable id="make" type="by.spetr.web.model.entity.type.VehicleMake"--%>
                        <%--@elvariable id="last_make_id" type="java.lang.Integer"--%>

                        <c:forEach items="${makes}" var="make">
                            <c:choose>
                                <c:when test="${last_make_id == make.makeId}">
                                    <option value="${make.makeId}" selected>${make.value}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${make.makeId}">${make.value}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <div class="invalid-feedback">
                        ${make_sel_req}
                    </div>
                </div>
                <div class="col-12 mt-4">
                    <div class="input-group has-validation">
                        <input type="text" class="form-control" id="model" name="model"
                               placeholder="${vehicle_make}" required pattern="${model_regexp}">
                        <div class="invalid-feedback">
                            ${model_req}
                        </div>
                    </div>
                </div>
                <div class="col-12">
                    <hr class="col-12 text-white">
                </div>
                <div class="col-12">
                    <button class="w-100 py-1 align-content-lg-center btn btn-primary btn-lg"
                            type="submit">${create}</button>
                </div>
            </form>

        </div>

    </section>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>