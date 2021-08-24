<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>


<form class="needs-validation col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3"
      action="${pageContext.request.contextPath}/controller" method="post" novalidate>
    <input type="hidden" name="command" value="sign_in">

    <div class="container">
        <div class="row">
            <div class="col col-md-auto px-0 nav-item dropdown">
                <!--  Language bar below  -->
                <%@include file="lng_bar.jspf" %>
            </div>
            <div class="col col-md-auto px-0">
                <input type="text" class="form-control" name="username" id="username_id" placeholder="username"
                       pattern="${username_regexp}" required>
            </div>
            <div class="col col-md-auto px-0">
                <input type="password" class="form-control" name="password" id="password_id" placeholder="password"
                       pattern="${password_regexp}" required>
            </div>
            <div class="col col-md-auto px-0">
                <button type="submit" class="btn btn-close-white">
                    <i class="fas fa-sign-in-alt"></i>
                    <%--                    ${sign_in}--%>
                </button>
            </div>
            <div class="col col-md-auto px-0">
                <a href="${pageContext.request.contextPath}/controller?command=go_to_sign_up_page" type="button"
                   class="btn btn-close-white w-100">
                    <i class="fas fa-plus"></i>
                    ${sign_up}
                </a>
            </div>
            <div class="col col-md-auto px-0">
                <a href="${pageContext.request.contextPath}/controller?command=go_to_recovery_password_page"
                   type="button"
                   class="btn btn-close-white">
                    <i class="fas fa-ambulance"></i>
                    ${forget}
                </a>
            </div>
            <div class="col col-md-auto px-0">
                <!-- Info menu button -->
                <button class="btn btn-dark me-2" type="button" data-bs-toggle="modal" data-bs-target="#aboutModal">
                    <i class="fas fa-info-circle"></i>
                    ${about}
                    </li>
            </div>
        </div>
    </div>
</form>

<%--<button class="btn btn-dark me-2" type="button" id="dropdownMenuButton1"--%>
<%--        data-bs-toggle="dropdown" aria-expanded="false">--%>
<%--    <i class="fas fa-sign-in-alt"></i>--%>
<%--    ${sign_in}--%>
<%--</button>--%>

<%--<form class="dropdown-menu needs-validation px-3 py-3" action="${pageContext.request.contextPath}/controller"--%>
<%--      method="post" novalidate>--%>
<%--    <input type="hidden" name="command" value="sign_in">--%>
<%--    <div class="mb-3">--%>
<%--        <input type="text" class="form-control" name="username" id="username_id" placeholder="username"--%>
<%--               pattern="^[a-zA-Z0-9_]{4,16}$" required>--%>
<%--    </div>--%>
<%--    <div class="mb-3">--%>
<%--        <input type="password" class="form-control" name="password" id="password_id" placeholder="password"--%>
<%--               pattern="^.{4,18}$" required>--%>
<%--    </div>--%>

<%--    <button type="submit" class="btn btn-primary w-100">--%>
<%--        <i class="fas fa-sign-in-alt"></i>--%>
<%--        ${sign_in}--%>
<%--    </button>--%>

<%--    <div>--%>
<%--        <a href="${pageContext.request.contextPath}/controller?command=go_to_recovery_password_page" type="button"--%>
<%--           class="btn btn-primary mt-2 w-100">--%>
<%--            <i class="fas fa-ambulance"></i>--%>
<%--            ${forget}--%>
<%--        </a>--%>
<%--    </div>--%>

<%--    <div class="dropdown-divider"></div>--%>

<%--    <div>--%>
<%--        <a href="${pageContext.request.contextPath}/controller?command=go_to_sign_up_page" type="button"--%>
<%--           class="btn btn-primary w-100">--%>
<%--            <i class="fas fa-plus"></i>--%>
<%--            ${sign_up}--%>
<%--        </a>--%>
<%--    </div>--%>
<%--</form>--%>