$(document).ready(function () {

    $(document).on("click", ".add-publisher", function () {
        renderAddPublisherForm("#content");
    });

    $(document).on("submit", "#add-publisher-form", function () {
        let publisherName = $("#publisher-name").val().trim();
        addPublisher(publisherName, "#content");
    })

    $(document).on("click", ".get-publishers", function () {
        renderPublishersList("#content");
    });

    $(document).on("click", ".get-edit-publisher-form", function () {
        let publisherId = $(this).attr("id");
        renderEditPublisherForm(publisherId, "#content");
    });

    $(document).on("submit", "#edit-publisher-form", function () {
        let publisherId = $("#publisherId").val();
        console.log(publisherId);
        let publisherName = $("#publisher-name").val().trim();
        editPublisher(publisherId, publisherName, "#content");
    });

    $(document).on("click", ".delete-publisher", function () {
        let publisherId = $(this).attr("id");
        deletePublisher(publisherId);
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
        let id = $(this).attr("id");
        alert(`Here must be shown magazine info. Id: ${id}`);
    });

    $(document).on("click", ".add-magazine", function () {
        let publisherId = $(this).attr("id");
        renderAddMagazineForm(publisherId, ".publisher-content");
    });

});