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
                    <jsp:forward page="${abs}/controller?command=show_vehicle_list_personal"/>
                </c:if>

                <!-- preview card -->
                <%--@elvariable id="vehicle" type="by.spetr.web.model.dto.VehiclePreviewDto"--%>
                <c:if test="${vehicles.size() == 0}">
                    <div class="alert alert-light w-100 text-center" id="message"><b>${no_rec_found}</b></div>
                </c:if>
                <c:forEach items="${vehicles}" var="vehicle">
                    <div class="col">
                        <div class="card shadow-sm">
                            <c:choose>
                                <c:when test="${vehicle.previewImagePath != null}">
                                    <img src="${vehicle.previewImagePath}"
                                         onclick="location.href='${abs}/controller?command=show_vehicle_info_personal&vehicle_id=${vehicle.id}';"
                                         style="cursor: pointer; border-radius: 0.25rem;"/>
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
                            <div class="card-body
                            <c:choose>
                                <c:when test="${vehicle.state == 'DISABLED'}">
                                    bg-secondary
                                </c:when>
                                <c:when test="${vehicle.state == 'ENABLED'}">

                                </c:when>
                                <c:when test="${vehicle.state == 'MODERATION'}">
                                    bg-danger
                                </c:when>
                                <c:otherwise>
                                    bg-secondary
                                </c:otherwise>
                            </c:choose>">
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
                                    <div class="">
                                        <c:choose>
                                            <c:when test="${vehicle.state == 'ENABLED'}">
                                                <a href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=DISABLED"
                                                   type="button" class="btn btn-sm btn-secondary">${disable}
                                                </a>
                                            </c:when>
                                            <c:when test="${vehicle.state == 'DISABLED'}">
                                                <a href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=ENABLED"
                                                   type="button" class="btn btn-sm btn-success">${enable}
                                                </a>
                                            </c:when>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${vehicle.state == 'MODERATION'}">
                                            </c:when>
                                            <c:otherwise>

                                                <!-- Edit button-->
                                                <a href="${abs}/controller?command=edit_vehicle&vehicle_id=${vehicle.id}"
                                                   type="button" class="btn btn-sm btn-warning">${edit}
                                                </a>

                                                <!-- Add photo button-->
                                                <button type="button" class="btn btn-sm btn-warning"
                                                        data-bs-toggle="modal"
                                                        data-bs-target="#addPhoto${vehicle.id}">
                                                    +<i class="fas fa-camera"></i>
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

                                                <!-- Delete button-->
                                                <button type="button" class="btn btn-sm btn-danger"
                                                        data-bs-toggle="modal" data-bs-target="#deleteModal${vehicle.id}">
                                                        ${delete}
                                                </button>

                                                <!-- Modal deleting confirmation dialog -->
                                                <div class="modal fade" id="deleteModal${vehicle.id}" tabindex="-1"
                                                     aria-labelledby="deleteModalLabel${vehicle.id}" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title"
                                                                    id="deleteModalLabel${vehicle.id}">${delete_title}</h5>
                                                                <button type="button" class="btn-close"
                                                                        data-bs-dismiss="modal"
                                                                        aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                    ${delete_description}
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-sm btn-secondary"
                                                                        data-bs-dismiss="modal">${cancel}</button>
                                                                <a href="${abs}/controller?command=change_vehicle_state&vehicle_id=${vehicle.id}&vehicle_state=ARCHIVED"
                                                                   type="button" class="btn btn-sm btn-danger">${delete}
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:otherwise>
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
        </div>
    </div>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>
