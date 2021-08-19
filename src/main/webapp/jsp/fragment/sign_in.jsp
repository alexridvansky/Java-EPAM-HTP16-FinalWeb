<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<button class="btn btn-dark me-2" type="button" id="dropdownMenuButton1"
        data-bs-toggle="dropdown" aria-expanded="false">
    <i class="fas fa-sign-in-alt"></i>
    ${sign_in}
</button>

<form class="dropdown-menu px-3 py-3" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="sign_in">
    <div class="mb-3">
        <input type="text" class="form-control" name="username" id="username_id" placeholder="username">
    </div>
    <div class="mb-3">
        <input type="password" class="form-control" name="password" id="password_id" placeholder="password">
    </div>

    <button type="submit" class="btn btn-primary">
        <i class="fas fa-sign-in-alt"></i>
        ${sign_in}
    </button>

    <div class="dropdown-divider"></div>

    <div class="pb-3">
        <a href="${pageContext.request.contextPath}/controller?command=go_to_sign_up_page" type="button"
           class="btn btn-primary">
            <i class="fas fa-plus"></i>
            ${sign_up}
        </a>
    </div>
</form>