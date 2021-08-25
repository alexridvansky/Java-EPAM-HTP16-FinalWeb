<%--@elvariable id="form" type="by.spetr.web.model.form.VehicleFullForm"--%>
<%--<%@ taglib prefix="cl" uri="http://cloudinary.com/jsp/taglib" %>--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="h-100">

<head>

    <%@include file="fragment/meta.jsp" %>

    <title>${vhc_create_title}</title>

</head>

<%@include file="fragment/fmt_msg.jsp" %>

<body class="d-flex flex-column h-100 bg-dark">

<%@include file="fragment/header.jspf" %>

<main>
    <section class="py-5 text-center text-white container">
        <div class="row py-lg-5">
            <div class="col-lg-8 col-md-8 mx-auto">
                <h2 class="fw-light text-white">${vhc_create_title}</h2>
                <p class="lead text-muted mt-3">${vhl_create_promo}</p>
            </div>

            <c:if test="${requestScope.makes == null || requestScope.models == null}">
                <jsp:forward page="${abs}/controller?command=show_vehicle_creation_page"/>
            </c:if>

            <div class="text-secondary text-start col-md-7 col-lg-8 mx-auto">

                <form class="needs-validation" action="${abs}/uploadController" method="post"
                      enctype="multipart/form-data" novalidate>
                    <input type="hidden" name="command" value="add_new_vehicle">
                    <hr class="my-4">
                    <div class="col-12">
                        <h3 class="fw-light text-center">${characteristics}</h3>
                    </div>
                    <div class="row g-3">
                        <label class="col-6 mt-3 mb-2">${select_make}
                            <select name="make_id" data-target="secondList"
                                    class="form-select firstList selectFilter"
                                    aria-label="Make select" required>
                                <option value="" selected disabled></option>
                                <%--@elvariable id="make" type="by.spetr.web.model.entity.type.VehicleMake"--%>
                                <c:forEach items="${makes}" var="make">
                                    <option data-ref="${make.value}" value="${make.makeId}">${make.value}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                ${make_sel_req}
                            </div>
                        </label>

                        <label class="col-6 mt-3 mb-2">${select_model}
                            <select name="model_id" class="form-select secondList selectFilter" required>
                                <option value="" selected disabled></option>
                                <c:forEach items="${models}" var="model">
                                    <option data-ref="${model.make.value}" data-belong="${model.make.value}"
                                            value="${model.modelId}">${model.value}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                ${model_sel_req}
                            </div>
                        </label>
                        <label class="col-3 mt-3 mb-2">${select_fuel}
                            <select name="powertrain_id" class="form-select" required>
                                <option value="" selected disabled></option>
                                <%--@elvariable id="powertrain_type" type="by.spetr.web.model.entity.type.VehiclePowertrainType"--%>
                                <c:forEach items="${powertrains}" var="powertrain_type">
                                    <option value="${powertrain_type.powertrainId}">${powertrain_type}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                ${fuel_sel_req}
                            </div>
                        </label>
                        <div class="col-3 mt-3 mb-2">${select_trans}
                            <select name="transmission_id" class="form-select" required aria-label="${select_trans}">
                                <option value="" selected disabled></option>
                                <%--@elvariable id="transmission_type" type="by.spetr.web.model.entity.type.VehicleTransmissionType"--%>
                                <c:forEach items="${transmissions}" var="transmission_type">
                                    <option value="${transmission_type.transmissionId}">${transmission_type}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                ${trans_sel_req}
                            </div>
                        </div>
                        <div class="col-3 mt-3 mb-2">${select_drive}
                            <select name="drive_id" class="form-select" required>
                                <option value="" selected disabled></option>
                                <%--@elvariable id="drive_type" type="by.spetr.web.model.entity.type.VehicleDriveType"--%>
                                <c:forEach items="${drives}" var="drive_type">
                                    <option value="${drive_type.driveId}">${drive_type}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                ${drive_sel_req}
                            </div>
                        </div>
                        <div class="col-3 mt-3 mb-2">${select_color}
                            <select name="color_id" class="form-select" required>
                                <option value="" selected disabled></option>
                                <%--@elvariable id="v_color" type="by.spetr.web.model.entity.type.VehicleColor"--%>
                                <c:forEach items="${colors}" var="v_color">
                                    <option value="${v_color.colorId}">${v_color.value}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                ${color_sel_req}
                            </div>
                        </div>
                        <div class="col-3 mt-3">
                            <label for="model_year" class="form-label mb-0">${enter_model_year}</label>
                            <div class="input-group has-validation">
                                <input type="text" class="form-control" id="model_year" name="model_year" value="${form.modelYear}"
                                       placeholder="2007" required="" pattern="${model_year_regexp}">
                                <div class="invalid-feedback">
                                    ${model_year_req}
                                </div>
                            </div>
                        </div>
                        <div class="col-3 mt-3">
                            <label for="displacement" class="form-label mb-0">${enter_displacement}</label>
                            <div class="input-group has-validation">
                                <input type="text" class="form-control" id="displacement" name="displacement" value="${form.displacement}"
                                       placeholder="3500" required="" pattern="${displacement_regexp}">
                                <div class="invalid-feedback">
                                    ${displacement_req}
                                </div>
                            </div>
                        </div>
                        <div class="col-3 mt-3">
                            <label for="mileage" class="form-label mb-0">${enter_mileage}</label>
                            <div class="input-group has-validation">
                                <input type="text" class="form-control" id="mileage" name="mileage" value="${form.mileage}"
                                       placeholder="125000" required="" pattern="${mileage_regexp}">
                                <div class="invalid-feedback">
                                    ${mileage_req}
                                </div>
                            </div>
                        </div>
                        <div class="col-3 mt-3">
                            <label for="power" class="form-label mb-0">${enter_power}</label>
                            <div class="input-group has-validation">
                                <input type="text" class="form-control" id="power" name="power" value="${form.power}"
                                       placeholder="245" required="" pattern="${power_regexp}">
                                <div class="invalid-feedback">
                                    ${power_req}
                                </div>
                            </div>
                        </div>
                        <hr class="my-4">
                        <div class="col-12 mt-0">
                            <h3 class="fw-light text-center">${select_options_header}</h3>
                        </div>
                        <div class="row ms-0">
                            <c:forEach items="${options}" var="vehicle_option">
                                <div class="col-4 form-check mt-2">
                                    <input class="form-check-input mb-2" type="checkbox"
                                           value="${vehicle_option.optionId}"
                                           id="option_${vehicle_option.optionId}" name="options_set">
                                    <label class="form-check-label mb-2" for="option_${vehicle_option.optionId}">
                                            ${vehicle_option.description}
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                        <hr class="my-4">
                        <div class="col-12 mt-0">
                            <h3 class="fw-light text-center">${description_header}</h3>
                            <p class="lead text-muted text-center mt-3">${description_promo}</p>

                            <label for="exampleFormControlTextarea1"
                                   class="form-label mb-0">${enter_description}
                            </label>
                            <textarea class="form-control" name="description" id="exampleFormControlTextarea1"
                                      rows="8"></textarea>
                        </div>
                        <hr class="my-4">
                        <div class="col-12 mt-0">
                            <h3 class="fw-light text-center">${price_header}</h3>
                            <p class="lead text-muted text-center mt-3">${price_promo}</p>
                        </div>
                        <div class="col-5">
                        </div>
                        <div class="col-2 mt-3 justify-content-center">
                            <label for="price" class="form-label mb-0">${enter_price}</label>
                            <div class="input-group has-validation">
                                <input type="text" class="form-control" id="price" name="price" value=""
                                       placeholder="12500" required="" pattern="${price_regexp}">
                                <div class="invalid-feedback">
                                    ${price_req}
                                </div>
                            </div>
                        </div>
                        <div class="col-5">
                        </div>

                        <hr class="my-4">

                        <div class="col-12 mt-0">
                            <h3 class="fw-light text-center">${photos_header}</h3>
                            <p class="lead text-muted text-center mt-3">${vhl_add_photo_promo}</p>
                        </div>

                        <div class="col-5">
                        </div>
                        <div class="col-7 mt-3 justify-content-center">
                            <label for="photo_file" class="form-label mb-0">${select_photo}</label>
                            <div class="input-group has-validation">
                                <input type="file" id="photo_file" required name="uploadController" multiple/>
                                <div class="invalid-feedback">
                                    ${price_req}
                                </div>
                            </div>
                        </div>
                    </div>
                        <hr class="my-4">

                        <button class="w-100 align-content-lg-center btn btn-primary btn-lg"
                            type="submit">${create}</button>


                </form>
            </div>
        </div>
    </section>
</main>

<%@include file="fragment/footer.jspf" %>

</body>
</html>