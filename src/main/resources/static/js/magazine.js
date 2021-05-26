function renderAddMagazineForm(publisherId, selector) {
    const path = "/html/add-magazine-form.html";
    $(selector).load(path, () => {
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
                        <th scope="col">#</th><th scope="col">Name</th>
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
        html += `<tr class="magazine" id="${magazine.id}">
                <th scope="row">${magazine.id}</th>
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