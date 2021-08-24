<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>Print out all users page</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<main class="flex-shrink-0">
    <div class="container-fluid" style="padding: 72px 0px 0">

        <link rel="stylesheet" href="${abs}/css/vehicles_table_link.css">
        <c:if test="${requestScope.vehicles == null}">
            <jsp:forward page="${abs}/controller?command=show_vehicle_list_admin"/>
        </c:if>

        <jsp:useBean id="vehicles" scope="request" type="java.util.List"/>

        <table class="table table-dark table-striped table-hover">
            <tbody>
            <tr>
                <th>${id}</th>
                <th>${select_make}</th>
                <th>${select_model}</th>
                <th>${model_year}</th>
                <th>${mileage}</th>
                <th>${color}</th>
                <th>${price}</th>
                <th>${powertrain}</th>
                <th>${transmission}</th>
                <th>${drive}</th>
                <th>${displacement}</th>
                <th>${power}</th>
                <th>${creation_date}</th>
                <th>${owner}</th>
                <th>${state}</th>
            </tr>
            <%--@elvariable id="vehicle" type="by.spetr.web.model.dto.VehicleFullDto"--%>
            <c:forEach items="${vehicles}" var="vehicle">
                <tr>
                    <td><a href="${abs}/controller?command=show_vehicle_info&vehicle_id=${vehicle.id}"
                           class="rowlink text-white" style="text-decoration: none">${vehicle.id}</a></td>
                    <td>${vehicle.make}</td>
                    <td>${vehicle.model}</td>
                    <td>${vehicle.modelYear}</td>
                    <td>${vehicle.mileage}</td>
                    <td>${vehicle.color}</td>
                    <td>$<fmt:formatNumber
                            type="number" minFractionDigits="0" maxFractionDigits="0"
                            value="${vehicle.price}"/></td>
                    <c:choose> <%--  powertrain field icon  --%>
                        <c:when test="${vehicle.powertrain == 'GASOLINE'}">
                            <td><i class="fas fa-gas-pump"></i>${vehicle.powertrain}</td>
                        </c:when>
                        <c:when test="${vehicle.powertrain == 'DIESEL'}">
                            <td><i class="fas fa-tractor"></i>${vehicle.powertrain}</td>
                        </c:when>
                        <c:when test="${vehicle.powertrain == 'ELECTRIC'}">
                            <td><i class="fas fa-charging-station"></i>${vehicle.powertrain}</td>
                        </c:when>
                    </c:choose>
                    <td>${vehicle.transmission}</td>
                    <td>${vehicle.drive}</td>
                    <c:choose>
                        <c:when test="${vehicle.displacement > 0}">
                            <td><fmt:formatNumber type="number" minFractionDigits="1" maxFractionDigits="1"
                                                  value="${vehicle.displacement/1000}"/> L
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                    <td>${vehicle.power}${hp}</td>
                    <td>${vehicle.dateCreated}</td>
                    <td>${vehicle.owner}</td>
                    <td>
                        <c:choose>
                            <c:when test="${vehicle.state == 'MODERATION'}">
                                <button class="w-100 btn btn-warning btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${vehicle.state}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item active disabled"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=MODERATION">MODERATION</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=DISABLED">DISABLED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=ARCHIVED">ARCHIVED</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${vehicle.state == 'ENABLED'}">
                                <button class="w-100 btn btn-success btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${vehicle.state}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=MODERATION">MODERATION</a>
                                    </li>
                                    <li><a class="dropdown-item active disabled"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=DISABLED">DISABLED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=ARCHIVED">ARCHIVED</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${vehicle.state == 'DISABLED'}">
                                <button class="w-100 btn btn-danger btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${vehicle.state}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=MODERATION">MODERATION</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item active disabled"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=DISABLED">DISABLED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=ARCHIVED">ARCHIVED</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${vehicle.state == 'ARCHIVED'}">
                                <button class="w-100 btn btn-secondary btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton2"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                        ${vehicle.state}
                                </button>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=MODERATION">MODERATION</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=ENABLED">ENABLED</a>
                                    </li>
                                    <li><a class="dropdown-item"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=DISABLED">DISABLED</a>
                                    </li>
                                    <li><a class="dropdown-item active disabled"
                                           href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=ARCHIVED">ARCHIVED</a>
                                    </li>
                                </ul>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br/>

        <c:choose>
            <c:when test="${vehicles.size() > 0}">
                ${vehicles.size()} ${entries}<br/>
            </c:when>
            <c:otherwise>
                ${no_entries}<br/>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>
