$(document).ready(function () {

    $(document).on("click", ".add-publisher", function () {
        alert("Here must be a form to add a new publisher");
    });

    $(document).on("click", ".get-publishers", function () {
        renderPublishersList("#content");
    });

    $(document).on("submit", "#add-magazine-form", function () {
        let magazineName = $("#magazine-name").val().trim();
        let categoryId = $("#category-id").val();
        let publisherId = $("#publisher-id").val();
        addMagazine(publisherId, magazineName, categoryId, ".publisher-content");
    });

    $(document).on("click", ".publisher", function () {
        let id = $(this).attr('id');
        renderPublisher(id, "#content");
    });

    $(document).on("click", ".get-magazines", function () {
        let publisherId = $(this).attr("id");
        renderMagazines(publisherId, ".publisher-content");
    });

    $(document).on("click", ".magazine", function () {
        alert("Here must be shown magazine info");
    });

    $(document).on("click", ".add-magazine", function () {
        let publisherId = $(this).attr("id");
        renderAddMagazineForm(publisherId, ".publisher-content");
    });

});