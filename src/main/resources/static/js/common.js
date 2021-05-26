function handleFormErrors(xhr) {
    let jsonResponse = JSON.parse(xhr.responseText);
    let errors = jsonResponse.errors;
    for (let key in errors) {
        if (errors.hasOwnProperty(key)) {
            alert(errors[key]);
        }
    }
}