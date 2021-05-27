function renderPublications(id, selector) {
    $.get(`/api/magazines/${id}/publications`, function (publications) {
        $(selector).html(`
              <h1 class="display-5 text-center">Publications</h1>
              <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Action</th>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Key words</th>
                    </tr>    
                </thead>
                <tbody>
                    ${getPublicationsRows(publications)}
                </tbody>
              </table>
        `);
    });
}

function getPublicationsRows(publications) {
    let html = '';
    publications.forEach(publication => {
        html += `
            <tr>
                <td>
                    <div class="btn btn-warning d-inline-block get-edit-publication-form" id="${publication.id}">Edit</div>
                    <div class="btn btn-danger d-inline-block delete-publication" id="${publication.id}">Delete</div>
                </td>
                <td>${publication.id}</td>
                <td>${publication.name}</td>
                <td>${publication.keyWords}</td>
            </tr>
            `;
    });
    return html;
}