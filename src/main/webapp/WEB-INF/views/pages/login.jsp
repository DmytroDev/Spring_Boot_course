<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>

<div id="content">

    <sec:authorize access="!isAuthenticated()">
        <c:if test="${not empty error}">
            <div class="error-div">${error}</div>
        </c:if>
    </sec:authorize>

    <form class="login-form" name='loginForm'
          action="<c:url value='/j_spring_security_check'/>" method='POST'>

        <div class="imgcontainer">
            <img src="${pageContext.request.contextPath}/resources/images/img_avatar2.png" alt="Avatar" class="avatar">
        </div>
        <div class="login-container">
            <label><b>Username</b></label>
            <input class="form-field" type="text" placeholder="Enter Username" name="j_username" required>

            <label><b>Password</b></label>
            <input class="form-field" type="password" placeholder="Enter Password" name="j_password" required>

            <button id="login-btn" class="login-btn" type="submit">Login</button>

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>

        </div>
    </form>
</div>