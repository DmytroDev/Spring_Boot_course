<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="content">
    <div class="upload-title">
        <p>Upload</p>
    </div>
    <div class="container">
        <form method="post" action="/view/upload" class="upload-form" id="upload-form" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">Application name</label>
                <input type="text" name="name" class="form-control" id="name" placeholder="Name">
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <input type="text" name="description" class="form-control" id="description" placeholder="Description">
            </div>
            <div class="form-group">
                <label for="packageName">Package</label>
                <input type="text" name="packageName" class="form-control" id="packageName" placeholder="Package">
            </div>
            <div class="form-group">
                <label for="categoryName">Category</label>
                <input type="text" name="categoryName" class="form-control" id="categoryName" placeholder="Category">
            </div>
            <div class="form-group">
                <label for="file">File input (ZIP-archive)</label>
                <input type="file" name="file" id="file">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>

        </form>
    </div>
</div>

