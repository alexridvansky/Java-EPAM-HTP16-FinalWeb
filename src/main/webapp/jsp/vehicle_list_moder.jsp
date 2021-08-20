<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>Ads page</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<link rel="stylesheet" href="${abs}/css/album_style.css">

<main>

    <div class="album py-5 bg-dark">
        <div class="container">

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3 mt-3">

                <%--@elvariable id="vehicles" type="java.util.List"--%>
                <c:if test="${vehicles == null}">
                    <jsp:forward page="${abs}/controller?command=show_vehicle_list_moder"/>
                </c:if>
                <c:if test="${vehicles.size() == 0}">
                    <div class="alert alert-light w-100 text-center" id="message"><b>${no_rec_found}</b></div>
                </c:if>
                <!-- preview card -->
                <%--@elvariable id="vehicle" type="by.spetr.web.model.dto.VehiclePreviewDto"--%>
                <c:forEach items="${vehicles}" var="vehicle">
                    <div class="col">
                        <div class="card shadow-sm"
                             onclick="location.href='${abs}/controller?command=show_vehicle_info&vehicle_id=${vehicle.id}';"
                             style="cursor: pointer;">
                            <c:choose>
                                <c:when test="${vehicle.previewImagePath != null}">
                                    <style>
                                        img {
                                            border-radius: 0.25rem;
                                        }
                                    </style>
                                    <img src="${vehicle.previewImagePath}"/>
                                </c:when>
                                <c:otherwise>
                                    <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                                         xmlns="http://www.w3.org/2000/svg" role="img"
                                         aria-label="Placeholder: Thumbnail"
                                         preserveAspectRatio="xMidYMid slice" focusable="false"><title>
                                        Placeholder</title>
                                        <rect width="100%" height="100%" fill="#55595c"/>
                                        <text x="50%" y="8%" fill="#eceeef" dy=".3em">
                                            <c:choose>
                                                <c:when test="${vehicle.displacement > 0}">
                                                    ${vehicle.modelYear} ${vehicle.make} ${vehicle.model}
                                                    <fmt:formatNumber type="number" minFractionDigits="1"
                                                                      maxFractionDigits="1"
                                                                      value="${vehicle.displacement/1000}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    ${vehicle.modelYear} ${vehicle.make} ${vehicle.model}
                                                </c:otherwise>
                                            </c:choose>
                                        </text>
                                    </svg>
                                </c:otherwise>
                            </c:choose>

                            <!-- card body -->

                            <div class="card-body">
                                <p class="card-text">
                                    <strong>
                                        <c:choose>
                                            <c:when test="${vehicle.displacement > 0}">
                                                ${vehicle.modelYear} ${vehicle.make} ${vehicle.model}
                                                <fmt:formatNumber type="number" minFractionDigits="1"
                                                                  maxFractionDigits="1"
                                                                  value="${vehicle.displacement/1000}"/>
                                            </c:when>
                                            <c:otherwise>
                                                ${vehicle.modelYear} ${vehicle.make} ${vehicle.model}
                                            </c:otherwise>
                                        </c:choose>
                                    </strong><br/>
                                        ${vehicle.powertrain}
                                        ${vehicle.transmission}
                                        ${vehicle.drive}
                                </p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <c:choose>
                                            <c:when test="${sessionScope.user.role == 'MODERATOR' || sessionScope.user.role == 'ROOT'}">
                                                <a href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=MODERATION"
                                                   type="button" class="btn btn-sm btn-danger">${block}
                                                </a>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <strong class="text text-dark">$<fmt:formatNumber
                                            type="number" minFractionDigits="0" maxFractionDigits="0"
                                            value="${vehicle.price}"/></strong>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- Pagination element -->
            <nav aria-label="Ad navigation">
                <ul class="pagination mt-2">
                    <li class="page-item ${pageable.firstPage ? 'disabled' : ''}">
                        <%--@elvariable id="pageable" type="by.spetr.web.controller.command.Page"--%>
                        <a class="page-link"
                           href="${abs}/controller?command=show_vehicle_list_public&page=${pageable.currentPage - 1}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach var="i" begin="1" end="${pageable.pageCount()}">
                        <li class="page-item ${pageable.currentPage == i ? 'active': ''}">
                            <a class="page-link" href="${abs}/controller?command=show_vehicle_list_public&page=${i}">${i}</a>
                        </li>
                    </c:forEach>

                    <li class="page-item ${pageable.lastPage ? 'disabled' : ''}">
                        <a class="page-link"
                           href="${abs}/controller?command=show_vehicle_list_public&page=${pageable.currentPage + 1}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>
