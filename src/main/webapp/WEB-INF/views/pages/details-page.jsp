<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="content">
    <div class="details-page-div">
        <div class="element-title-img">
            <p class="software-title">${software.name}</p>
            <img class="media-object" src="${pageContext.request.contextPath}/imgController512/getImg/${software.id}" onerror="this.src='${pageContext.request.contextPath}/resources/images/no_image_available.png'">
        </div>
        <div class="element-description">
            <p>${software.description}</p>
            <button id="download-btn" class="download-btn">
                <a id="download-archive-link" class="download-btn-href" href="${pageContext.request.contextPath}/download/archive/${software.id}">Download application</a>
            </button>
            <button id="back-to-index-from-details-btn" class="back-to-index-from-details-btn">
                <a id="back-to-index-from-details-link" class="back-to-index-from-details-href" href="${pageContext.request.contextPath}/view/index">Back</a>
            </button>
        </div>
    </div>
</div>

