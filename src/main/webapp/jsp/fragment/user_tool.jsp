<%@ taglib prefix="ex" uri="custom_tags" %>

<!-- Language bar below -->
<li class="nav-item dropdown">
    <%@include file="lng_bar.jspf" %>
</li>

<li class="nav-item dropdown">

    <button class="btn btn-dark dropdown-toggle" type="button" id="dropdownMenuButton1"
            data-bs-toggle="dropdown" aria-expanded="false">
        <i class="fas fa-user-cog"></i>
        <c:choose>
            <c:when test="${sessionScope.user.login == 'root'}">
                #${sessionScope.user.login}
            </c:when>
            <c:otherwise>
                @${sessionScope.user.login}
            </c:otherwise>
        </c:choose>
    </button>

    <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton1">
        <c:choose>
            <c:when test="${sessionScope.user.role == 'USER'}">
                <li>
                    <a href="#" class="dropdown-item disabled text-dark">
                            ${state}: ${sessionScope.user.state}<br/>
                            ${ads}: <ex:AdCounter userId="${sessionScope.user.userId}"/>
                    </a>
                </li>
            </c:when>
            <c:when test="${sessionScope.user.role == 'ROOT' || sessionScope.user.role == 'MODERATOR'}">
                <li>
                    <a href="#" class="dropdown-item disabled text-dark">
                            ${role}: ${sessionScope.user.role}
                    </a>
                </li>
            </c:when>
        </c:choose>
        <li>
            <hr class="dropdown-divider">
        </li>
        <li>
            <a href="${abs}/controller?command=log_out" class="dropdown-item">
                <i class="fas fa-sign-out-alt"></i>${log_out}
            </a>
        </li>

    </ul>

</li>
<!-- Info menu button -->
<li class="nav-item">
    <button class="btn btn-dark me-2" type="button" data-bs-toggle="modal" data-bs-target="#aboutModal">
        <i class="fas fa-info-circle"></i>
        ${about}
    </button>
</li>
