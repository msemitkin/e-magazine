function renderPublishersList(selector) {
    $.get("/api/publishers/", publishers => {
        let table = `
            <table class="table table-striped">
                <thead>
                    <tr>
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

function getPublishersRows(publishers) {
    let html = '';
    publishers.forEach(publisher => {
        html += `
            <tr class="publisher" id="${publisher.id}">
                <th scope="row">${publisher.id}</th>
                <td>${publisher.name}</td>
            </tr>
        `;
    });
    return html;
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
            <h1 class="display-3 text-center">${publisher.name}</h1>
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