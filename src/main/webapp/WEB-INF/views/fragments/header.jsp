<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="site-header" class="site-header">
    <div id="header-top" class="header-top">
        <div class="header-msg">SoftwareStore
        </div>
        <sec:authorize access="isAuthenticated()">
            <div id="username-div" class="header-right-part">
                Welcome,
            <span>
                <sec:authentication property="principal.username"/>
            </span>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <div class="navigation-div">
                <ul class="main-menu">

                    <sec:authorize access="hasRole('ROLE_DEVELOPER')">
                        <li><a id="upload-link" href="${pageContext.request.contextPath}/view/upload">Upload</a></li>
                    </sec:authorize>

                    <li><a id="all-software-link" href="${pageContext.request.contextPath}/view/index">Home</a></li>

                    <sec:authorize access="isAuthenticated()">
                        <li><a id="logout-link" href="${pageContext.request.contextPath}/view/logout">Logout</a></li>
                    </sec:authorize>
                </ul>
            </div>
        </sec:authorize>
    </div>
    <sec:authorize access="isAuthenticated()">
        <div class="header-bottom">
            <div>
                <p class="header-title">Most Popular</p>
            </div>
            <div class="header-ribbon">
                <c:if test="${!empty softwareList}">
                    <c:forEach items="${softwareList}" var="software">
                        <a id="ribbon-img-link" href="${pageContext.request.contextPath}/view/details/${software.id}">
                            <img src="${pageContext.request.contextPath}/imgController128/getImg/${software.id}"
                                 onerror="this.src='${pageContext.request.contextPath}/resources/images/no_image_available.png'"/>
                        </a>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </sec:authorize>
</div>