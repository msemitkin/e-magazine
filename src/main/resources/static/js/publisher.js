function renderAddPublisherForm(selector) {
    const path = '/html/add-publisher-form.html';
    $(selector).load(path, function () {
        $(".publisher-form").attr("id", "add-publisher-form");
    });
}

function renderPublishersList(selector) {
    $.get("/api/publishers/", publishers => {
        let table = `
            <h1 class="display-4 text-center">Publishers</h1>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Action</th>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                    </tr>    
                </thead>
                <tbody>
                    ${getPublishersRows(publishers)}
                </tbody>
            </table>
        `;
        $(selector).html(table);
    });
}

function addPublisher(publisherName, renderPublishersSelector) {
    const uri = `/api/publishers`;
    const data = {
        name: publisherName
    };

    $.ajax({
        type: "POST",
        url: uri,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: () => renderPublishersList(renderPublishersSelector),
        error: xhr => handleFormErrors(xhr)
    });
}

function getPublishersRows(publishers) {
    let html = '';
    publishers.forEach(publisher => {
        html += `
            <tr >
                <td>
                    <div class="btn btn-info d-inline-block publisher" id="${publisher.id}">Open</div>
                    <div class="btn btn-warning d-inline-block get-edit-publisher-form" id="${publisher.id}">Edit</div>
                    <div class="btn btn-danger d-inline-block delete-publisher" id="${publisher.id}">Delete</div>
                </td>
                <td>${publisher.id}</td>
                <td>${publisher.name}</td>
            </tr>
        `;
    });
    return html;
}

function renderEditPublisherForm(publisherId, selector) {
    const uri = `/api/publishers/${publisherId}`;
    $(selector).load("/html/add-publisher-form.html", function () {
        $.get(uri, function (publisher) {
            $(".publisher-form")
                .attr("id", "edit-publisher-form")
                .append(`<input type="hidden" name="publisherId" id="publisherId" value='${publisherId}'>`);
            $("#publisher-name").val(publisher.name);
        });
    });
}

function editPublisher(publisherId, publisherName, renderPublishersListSelector) {
    const uri = `/api/publishers/${publisherId}`;
    const data = {
        name: publisherName
    };
    $.ajax({
        type: "PUT",
        url: uri,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: () => renderPublishersList(renderPublishersListSelector),
        error: (xhr) => handleFormErrors(xhr)
    });
}

function deletePublisher(publisherId) {
    console.log(publisherId);
    const uri = `/api/publishers/${publisherId}`;
    $.ajax({
        type: "DELETE",
        url: uri,
        success: () => renderPublishersList("#content")
    });
}

function renderPublisher(publisherId, selector) {
    $.get(`/api/publishers/${publisherId}`, publisher => {
        renderPublisherNavBar(publisher, selector);
        $(selector).append($('<div>').addClass("publisher-content"));
        renderMagazines(publisherId, ".publisher-content");
    });
}

function renderPublisherNavBar(publisher, selector) {
    let html = `
            <h1 class="display-4 text-center">${publisher.name}</h1>
            <ul class="text-center list-inline py-3">
            <li class="list-inline-item">
              <button type="button" class="btn btn-secondary get-magazines" id="${publisher.id}">Magazines</button>
            </li>
            <li class="list-inline-item">
              <button type="button" class="btn btn-secondary add-magazine" id="${publisher.id}">Add magazine</button>
            </li>
          </ul>
        `;
    $(selector).html(html);
}