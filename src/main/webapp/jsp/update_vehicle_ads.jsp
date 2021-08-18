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

                <c:if test="${vehicles == null}">
                    <jsp:forward page="${abs}/controller?command=show_vehicle_list_public"/>
                </c:if>

<%--                <jsp:useBean id="vehicles" scope="application" type="java.util.List"/>--%>

                <c:forEach items="${vehicles}" var="vehicle">
                    <div class="col">
                        <div class="card shadow-sm">
                            <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                                 xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail"
                                 preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                                <rect width="100%" height="100%" fill="#55595c"/>
                                <text x="50%" y="8%" fill="#eceeef" dy=".3em">
                                    <c:choose>
                                        <c:when test="${vehicle.displacement > 0}">
                                            ${vehicle.modelYear} ${vehicle.make} ${vehicle.model}
                                                <fmt:formatNumber type="number" minFractionDigits="1" maxFractionDigits="1"
                                                                  value="${vehicle.displacement/1000}"/>
                                        </c:when>
                                        <c:otherwise>
                                            ${vehicle.modelYear} ${vehicle.make} ${vehicle.model}
                                        </c:otherwise>
                                    </c:choose>
                                </text>
                            </svg>

                            <div class="card-body">
                                <p class="card-text">This is a wider card with supporting text below as a natural
                                    lead-in to
                                    additional content. This content is a little bit longer.</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="btn-group">
                                        <a href="${abs}/controller?command=show_vehicle_info&vehicle_id=${vehicle.getId()}"
                                           class="btn btn-sm btn-outline-secondary">View</a>
<%--                                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
                                    </div>
                                    <strong class="text text-dark">$<fmt:formatNumber
                                            type="number" minFractionDigits="0" maxFractionDigits="0"
                                            value="${vehicle.getPrice()}"/></strong>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</main>
<%@include file="fragment/footer.jspf" %>

</body>
</html>
