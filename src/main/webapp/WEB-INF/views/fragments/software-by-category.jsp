<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="categories-list">

    <ul class="media-list">
        <c:if test="${!empty softwareList}">
            <c:forEach items="${softwareList}" var="software">

                <li class="media">
                    <div class="media-left">
                        <a id="details-link" href="${pageContext.request.contextPath}/view/details/${software.id}">
                            <img class="media-object" src="${pageContext.request.contextPath}/imgController128/getImg/${software.id}" onerror="this.src='${pageContext.request.contextPath}/resources/images/no_image_available.png'">
                        </a>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">${software.name}</h4>
                    </div>
                </li>
                <hr>

            </c:forEach>
        </c:if>
    </ul>
</div>
