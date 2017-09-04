$(function () {

    function getContextPath() {
        return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
    }

    $(document).on("click", "a#category-active-link, a#category-link", function () {
        event.preventDefault();
        $.ajax({
            url: $(this).attr('href'), success: function (result) {
                console.log(this);
                $("#categories-content").html(result);
            }
        });
    });

    $(document).on("click", "a#details-link, a#all-software-link, a#upload-link, a#ribbon-img-link, " +
        "a#back-to-index-page-link, a#back-to-index-from-details-link", function () {
        event.preventDefault();
        $.ajax({
            url: $(this).attr('href'), success: function (result) {
                $("#content").html(result);
            }
        });
    });

    /* upload form with Zip-archive */
    $(document).on("submit", "#upload-form", function (event) {
        var formData = new FormData($('form')[0]);
        event.preventDefault();
        const ctx = getContextPath();
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            url: ctx + "/view/upload",
            data: formData,
            success: function (result) {
                $("#content").html(result);
            },
            error: function() {
                alert('Unable upload form');
            }
        });
    });

});
