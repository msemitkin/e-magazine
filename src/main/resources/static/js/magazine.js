function renderAddMagazineForm(publisherId, selector) {
    const path = "/html/add-magazine-form.html";
    $(selector).load(path, () => {
        $(".magazine-form").attr("id", "add-magazine-form")
        $("#publisher-id").val(publisherId);
        $.get("/api/categories", categories =>
            categories.forEach(category =>
                $(".category-select").append(`<option value="${category.id}">${category.value}</option>`)));
    });
}

function renderMagazines(publisherId, selector) {
    $.get(`/api/publishers/${publisherId}/magazines`, magazines => {
        let table = `
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Action</th>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                    </tr>
                </thead>
                <tbody>
                    ${getMagazinesRows(magazines)}
                </tbody>
            </table>
        `;
        $(selector).html(table);
    });
}

function getMagazinesRows(magazines) {
    let html = '';
    magazines.forEach(magazine => {
        html += `<tr>
                    <td>
                        <div class="btn btn-info d-inline-block magazine" id="${magazine.id}">Open</div>
                        <div class="btn btn-warning d-inline-block get-edit-magazine-form" id="${magazine.id}">Edit</div>
                        <div class="btn btn-danger d-inline-block delete-magazine" id="${magazine.id}">Delete</div>
                    </td>
                    <td>${magazine.id}</td>
                    <td>${magazine.name}</td>
                    <td>${magazine.category}</td>
                 </tr>`
    });
    return html;
}

function addMagazine(publisherId, magazineName, categoryId, renderMagazinesListSelector) {
    const uri = `/api/publishers/${publisherId}/magazines`;
    const data = {
        name: magazineName,
        categoryId: categoryId
    };

    $.ajax({
        type: "POST",
        url: uri,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: () => renderMagazines(publisherId, renderMagazinesListSelector),
        error: xhr => handleFormErrors(xhr)
    });
}

function editMagazine(magazineId, magazineName, categoryId) {
    const data = {
        name: magazineName,
        categoryId: categoryId
    }
    $.ajax({
        type: "PUT",
        url: `/api/magazines/${magazineId}`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: () => renderMagazine(magazineId, ".publisher-content"),
        error: (xhr) => handleFormErrors(xhr)
    });
}

function deleteMagazine(id) {
    const uri = `/api/magazines/${id}`;
    $.ajax({
        type: "DELETE",
        url: uri,
        success: () => renderPublishersList("#content")
    });
}


function renderMagazine(id, selector) {
    $.get(`/api/magazines/${id}`, function (magazine) {
        renderMagazineHeader(magazine, selector);
        renderPublications(magazine.id, ".magazine-content");
    });
}

function renderMagazineHeader(magazine, selector) {
    $(selector).html(`
        <div class="row">
              <div class="col-3" style="background: azure">
                <h1 class="display-5 text-center">Magazine</h1>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="name">Name:</label>
                    <p class="font-weight-normal" id="name">${magazine.name}</p>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="category">Category:</label>
                    <p class="font-weight-normal" id="category">${magazine.category}</p>
                </div>
              </div>
              <div class="col" style="background: aliceblue">
                <ul class="text-center list-inline py-3">
                    <li class="list-inline-item">
                      <button type="button" class="btn btn-secondary get-publications" id="${magazine.id}">Publications</button>
                    </li>
                    <li class="list-inline-item">
                      <button type="button" class="btn btn-secondary get-add-publication-form" id="${magazine.id}">Add publication</button>
                    </li>
                </ul>
                <div class="magazine-content">
                </div>
              </div>
        </div>    
    `);
}

function renderEditMagazineForm(magazineId, selector) {
    $.get(`/api/magazines/${magazineId}`, function (magazine) {
        $.get(`/api/categories`, function (categories) {
            $(selector).load("/html/add-magazine-form.html", function () {
                $(".magazine-form").attr("id", "edit-magazine-form")
                    .append(`<input type="hidden" name="magazineId" id="magazineId" value='${magazineId}'>`);
                $("#magazine-name").val(magazine.name);
                categories.forEach(category => {
                    if (category.value.localeCompare(magazine.category) === 0) {
                        console.log("true");
                        $(".category-select").append(`<option value="${category.id}" selected>${category.value}</option>`)
                    } else {
                        $(".category-select").append(`<option value="${category.id}">${category.value}</option>`);
                        console.log("false");
                    }
                });
            });
        });
    });
}
