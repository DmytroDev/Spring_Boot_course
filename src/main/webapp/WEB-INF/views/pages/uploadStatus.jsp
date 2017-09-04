<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="content">
    <div class="upload-status-page-div">
        <h1 class="upload-status-title">Upload Status:</h1>
        <h2 class="upload-status-msg">${msg}</h2>
        <div class="back-to-index-div">
            <button id="back-to-index-page-btn" class="back-to-index-page-btn">
                <a id="back-to-index-page-link" class="back-to-index-page-href" href="${pageContext.request.contextPath}/view/index">Back</a>
            </button>
        </div>
    </div>
</div>