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
        <input type="text" class="form-control" name="login" id="exampleDropdownFormEmail2" placeholder="username">
    </div>
    <div class="mb-3">
        <input type="password" class="form-control" name="pass" id="exampleDropdownFormPassword2" placeholder="password">
    </div>
    <div class="mb-3">
        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="dropdownCheck2">
            <label class="form-check-label" for="dropdownCheck2">
                Remember
            </label>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">
        <i class="fas fa-sign-in-alt"></i>
        ${sign_in}
    </button>
    <p style="color:red;">${auth_error}</p>
    <div class="dropdown-divider"></div>
    <div class="pb-3">
        <a href="${pageContext.request.contextPath}/controller?command=go_to_sign_up_page" type="button"
           class="btn btn-primary">
            <i class="fas fa-plus"></i>
            ${sign_up}
        </a>
    </div>
</form>