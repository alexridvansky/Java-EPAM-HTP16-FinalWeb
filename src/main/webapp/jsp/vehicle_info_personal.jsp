<%--@elvariable id="vehicle" type="by.spetr.web.model.dto.VehicleFullDto"--%>
<%--@elvariable id="photo" type="java.util.List"--%>
<%--<%@ taglib prefix="cl" uri="http://cloudinary.com/jsp/taglib" %>--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>${vehicle.modelYear} ${vehicle.make} ${vehicle.model}</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<main>
    <section class="py-5 text-center container">
        <div id="carouselExampleIndicators" class="carousel mt-4 slide col-lg-6 col-md-8 mx-auto"
             data-bs-ride="carousel">
            <div class="carousel-inner">
                <c:forEach items="${vehicle.album}" var="photo" varStatus="loop">
                    <c:choose>
                        <c:when test="${loop.index == 0}">
                            <div class="carousel-item active">
                                <img src="${photo}" class="d-block w-100" alt="...">
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="carousel-item">
                                <img src="${photo}" class="d-block w-100" alt="...">
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                    data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                    data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

        <div class="row">
            <div class="col-lg-6 col-md-8 mx-auto">
                <hr class="col-12 text-white">
                    <div class="col-12 text-white">
                        <h2 class="text-white">${vehicle.modelYear} ${vehicle.make} ${vehicle.model}
                            <c:choose>
                                <c:when test="${vehicle.displacement > 0}">
                                    <fmt:formatNumber type="number" minFractionDigits="1" maxFractionDigits="1"
                                                      value="${vehicle.displacement/1000}"/>
                                </c:when>
                                <c:otherwise>

                                </c:otherwise>
                            </c:choose>
                             - ${vehicle.color}
                             - $<fmt:formatNumber value="${vehicle.price}" type="number" maxFractionDigits="0"/>
                        </h2>
                    </div>
                <hr class="col-12 text-white">
                <div class="row mb-3 text-white">
                    <div class="col-sm-4 p-3 themed-grid-col">
                        <h2>
                            <c:choose> <%--  powertrain field icon  --%>
                                <c:when test="${vehicle.powertrain == 'GASOLINE'}">
                                    <i class="fas fa-gas-pump"></i>
                                </c:when>
                                <c:when test="${vehicle.powertrain == 'DIESEL'}">
                                    <i class="fas fa-tractor"></i>
                                </c:when>
                                <c:when test="${vehicle.powertrain == 'ELECTRIC'}">
                                    <i class="fas fa-charging-station"></i>
                                </c:when>
                            </c:choose>
                        </h2>
                        ${powertrain}: ${vehicle.powertrain}</div>
                    <div class="col-sm-4 p-3 themed-grid-col">
                        <h2>
                            <i class="fas fa-cogs"></i>
                        </h2>
                        ${transmission}: ${vehicle.transmission}</div>
                    <div class="col-sm-4 p-3 themed-grid-col">
                        <h2>
                            <i class="fas fa-exchange-alt"></i>
                        </h2>
                        ${drive}: ${vehicle.drive}</div>
                    <div class="col-sm-4 p-3 themed-grid-col">
                        <c:choose>
                            <c:when test="${vehicle.displacement > 0}">
                                <h2>
                                    <i class="fas fa-oil-can"></i>
                                </h2>
                                ${displacement}:
                                <fmt:formatNumber type="number" minFractionDigits="1" maxFractionDigits="1"
                                                  value="${vehicle.displacement/1000}"/>L
                            </c:when>
                            <c:otherwise>
                                <h2>
                                    <i class="fas fa-car-battery"></i>
                                </h2>
                                ${displacement}:
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-sm-4 p-3 themed-grid-col">
                        <h2>
                            <i class="fas fa-horse"></i>
                        </h2>
                        ${power}: ${vehicle.power}${hp}
                    </div>
                    <div class="col-sm-4 p-3 themed-grid-col">
                        <h2>
                            <i class="fas fa-route"></i>
                        </h2>
                        <c:choose>
                            <c:when test="${vehicle.mileage > 999}">
                                ${mileage}:
                                <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="0"
                                                  value="${vehicle.mileage/1000}"/>K
                            </c:when>
                            <c:otherwise>
                                ${mileage}: ${vehicle.mileage} km
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <hr class="col-12 text-white">
                <h4 class="fw-light text-white">${v_options}</h4>
                <hr class="col-12 text-white">
                <div class="row mb-3 text-white">
                    <c:forEach items="${vehicle.optionList}" var="vehicleOption">
                        <div class="col-sm-4 themed-grid-col text-start">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" checked>
                            <label class="form-check-label" for="flexCheckDefault">
                                    ${vehicleOption.description}
                            </label>

                        </div>
                    </c:forEach>
                </div>
                <!-- comments section-->
                <c:choose>
                    <c:when test="${vehicle.comment.length() > 0}">
                        <hr class="col-12 text-white">
                        <h4 class="fw-light text-white">${comments}</h4>
                        <hr class="col-12 text-white">
                        <h6 class="fw-light text-white">${vehicle.comment}</h6>
                    </c:when>
                </c:choose>

                <hr class="col-12 text-white">
                <h4 class="fw-light text-white">${info}</h4>
                <hr class="col-12 text-white">
                <h4 class="fw-light text-white">${phone}: ${vehicle.ownerPhone} </h4>
                <h4 class="fw-light text-white">${ads_full}: <ex:AdCounter userId="${vehicle.ownerId}"/></h4>
                <h4 class="fw-light text-white">${lot_id}:
                    #<fmt:formatNumber type="number" minIntegerDigits="7" groupingUsed="false" value="${vehicle.id}"/>
                </h4>
                <h4 class="fw-light text-white">${creation_date}: ${vehicle.dateCreated}</h4>
            </div>
        </div>

    </section>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>
