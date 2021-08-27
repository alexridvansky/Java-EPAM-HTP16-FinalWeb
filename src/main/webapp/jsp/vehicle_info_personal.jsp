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
        <div class="row">
            <div class="col-lg-6 col-md-8 mx-auto">
                <c:forEach items="${vehicle.album}" var="photo" varStatus="loop">

                    <div class="col-auto card bg-dark justify-content-center" style="width: 40rem;">
                        <img src="${photo}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <a href="${abs}/controller?command=update_vehicle_preview&vehicle_id=${vehicle.id}&new_preview=${photo}"
                               type="button" class="btn btn-sm btn-success"><i class="fas fa-check"></i> ${use_photo_as_title}
                            </a>
                                <%--                    <p class="card-text"></p>--%>
                        </div>
                    </div>
                    <%--            <img src="${photo}" class="d-block w-100" alt="...">--%>
                </c:forEach>
                <hr class="col-12 text-white">
            </div>
        </div>


        <!-- Add photo button-->
        <button type="button" class="btn btn-sm btn-warning mt-1"
                data-bs-toggle="modal"
                data-bs-target="#addPhoto${vehicle.id}">
            ${add_photo}<i class="fas fa-camera"></i>
        </button>

        <div class="modal fade" id="addPhoto${vehicle.id}" tabindex="-1"
             aria-labelledby="addPhotoLabel${vehicle.id}" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"
                            id="addPhotoLabel${vehicle.id}">${vhl_add_photo_title}</h5>
                        <button type="button" class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>${vhl_add_photo_promo}</p>

                        <form method="post" action="uploadController" enctype="multipart/form-data">
                            Choose a file: <input type="file" name="uploadController" multiple/>
                            <input type="hidden" name="command" value="upload_vehicle_photo" formenctype="text/plain"/>
                            <input type="hidden" name="vehicle_id" value="${vehicle.id}" formenctype="text/plain"/>
                            <input type="submit" value="Upload" class="btn-sm btn-warning"/>
                        </form>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-bs-dismiss="modal">Close
                        </button>
                    </div>
                </div>
            </div>
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
                        <br>
                        $<fmt:formatNumber value="${vehicle.price}" type="number" maxFractionDigits="0"/>
                        <br>

                        <!-- Price edit button -->
                        <button type="button" class="btn btn-sm btn-warning text-dark" data-bs-toggle="modal"
                                data-bs-target="#exampleModal${vehicle.id}" data-bs-whatever="@mdo">${edit}</button>

                        <!-- Price edit modal -->
                        <div class="modal fade" id="exampleModal${vehicle.id}" tabindex="-1"
                             aria-labelledby="exampleModalLabel${vehicle.id}" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <form class="needs-validation" action="${abs}/controller" method="post" novalidate>
                                        <div class="modal-header">
                                            <h5 class="modal-title text-dark"
                                                id="exampleModalLabel${vehicle.id}">${edit_title}</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="command" value="update_vehicle_price">
                                            <input type="hidden" name="vehicle_id" value="${vehicle.id}">
                                            <div class="mb-3 has-validation">
                                                <label for="recipient-name"
                                                       class="col-form-label text-dark">${enter_price}</label>
                                                <input type="text" class="form-control" id="recipient-name"
                                                       required pattern="${price_regexp}" name="new_price"
                                                       value="${vehicle.price}">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-sm btn-secondary"
                                                    data-bs-dismiss="modal">${close}</button>
                                            <button type="submit" class="btn btn-sm btn-warning">${edit}</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

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

                <!-- Edit button-->
                <button type="button" class="btn btn-sm btn-warning"
                        data-bs-toggle="modal" data-bs-target="#editCommentModal${vehicle.id}">
                    ${edit}
                </button>

                <!-- Modal editing description dialog -->
                <div class="modal fade" id="editCommentModal${vehicle.id}" tabindex="-1"
                     aria-labelledby="editCommentModalLabel${vehicle.id}" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <form action="${abs}/controller" method="post">
                                <input type="hidden" name="command" value="update_vehicle_comment">
                                <input type="hidden" name="vehicle_id" value="${vehicle.id}">
                                <div class="modal-header">
                                    <h5 class="modal-title"
                                        id="editCommentModalLabel${vehicle.id}">${edit_title}</h5>
                                    <button type="button" class="btn-close"
                                            data-bs-dismiss="modal"
                                            aria-label="Close">
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <label for="exampleFormControlTextarea1"
                                           class="form-label mb-0">${enter_description}
                                    </label>
                                    <textarea class="form-control" name="new_description"
                                              id="exampleFormControlTextarea1"
                                              rows="8">${vehicle.comment}</textarea>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-sm btn-secondary"
                                            data-bs-dismiss="modal">${cancel}</button>
                                    <button type="submit" class="btn btn-sm btn-warning"
                                            data-bs-dismiss="modal">${edit}</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

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
