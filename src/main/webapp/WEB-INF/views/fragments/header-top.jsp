<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="header-msg">SoftwareStore</div>
<div id="username-div" class="header-right-part">
    Welcome,
            <span>
                <sec:authentication property="principal.username"/>
            </span>
</div>
<div class="navigation-div">
    <ul class="main-menu">

        <sec:authorize access="hasRole('ROLE_DEVELOPER')">
            <li><a id="upload-link" href="/view/upload">Upload</a></li>
        </sec:authorize>
        <li><a id="all-software-link" href="/view/index">Home</a></li>

        <sec:authorize access="isAuthenticated()">
            <li><a id="logout-link" href="/view/logout">Logout</a></li>
        </sec:authorize>

    </ul>
    <sec:authorize access="!isAuthenticated()">
        <p>Вы не авторизованы</p>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <p>Ваш логин: <sec:authentication property="principal.username" /></p>
    </sec:authorize>
</div>
