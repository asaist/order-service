function getAuthorsForEditTrack(id) {
    // const url = '/authors_track'; // здесь id - идентификатор трека
    return fetch(`/authors_track/${id}`)
    .then(response => response.json())
        .then(data => {
            authors = data.map(author => {
                return {
                    id: author.id,
                    fullName: author.fullName,
                    degree: author.degree,
                    isSupervisor: author.isSupervisor,
                    passport: author.passport,
                    diploma: author.diploma,
                    diplomaScienceRank: author.diplomaScienceRank,
                    diplomaScienceDegree: author.diplomaScienceDegree,
                    noCriminalRecord: author.noCriminalRecord,
                    healthStatus: author.healthStatus,
                    employmentBook: author.employmentBook
                    // Здесь можно добавить другие свойства автора, если они есть в объекте AuthorDto
                };
            });
            // Используйте полученный ID нового трека для нужных действий на фронтенде
            console.log('Авторы трека', authors);
            drawAutorTable(authors);
        })
        .catch(error => {
            console.error('Ошибка при отправке запроса:', error);
        });
}

function drawAutorTable(authors){
    for (const author of authors) {
        const index = authors.indexOf(author);
        let tableAuthor = document.getElementById('tableAuthorEditTrack');

        let divDoc = document.createElement('tr');
        divDoc.classList.add('divDoc');
        tableAuthor.append(divDoc);

        let divDocTd = document.createElement('td');
        divDocTd.setAttribute('colspan', '5');
        divDoc.append(divDocTd);

        let tr1 = document.createElement('table');
        tr1.classList.add('w-100', 'tr1');
        divDocTd.append(tr1);

        let nameTr = document.createElement('tr');
        tr1.append(nameTr);
        let nameTd = document.createElement('td');
        nameTd.classList.add('text-primary');
        nameTd.textContent = author.fullName
        nameTr.append(nameTd);

        let prof = document.createElement('td');
        prof.classList.add('text-center', 'regTd');
        prof.textContent = author.degree;
        nameTr.append(prof);

        let checkTd = document.createElement('td');
        checkTd.classList.add('checkTd');
        nameTr.append(checkTd);
        let radioBtn = document.createElement('input');
        radioBtn.setAttribute('type', 'radio');
        radioBtn.setAttribute('name', 'flexRadioDefault');
        radioBtn.setAttribute('onclick', 'AuthorIsSupervisor(this)');
        radioBtn.setAttribute('id', 'isSupervisor');
        radioBtn.setAttribute('authorIndex', index.toString());
        if (author.isSupervisor === true){
            radioBtn.setAttribute('checked', 'checked');
        }
        radioBtn.classList.add('form-check-input');
        checkTd.append(radioBtn);

        let docTd = document.createElement('td');
        docTd.classList.add('text-center', 'docTd');
        docTd.setAttribute('onclick', 'showDoc(this)');
        nameTr.append(docTd);
        let docTdIcon = document.createElement('i');
        docTdIcon.classList.add('fas', 'fa-file-upload', 'text-primary');

        docTd.append(docTdIcon);

        let removeTd = document.createElement('td');
        removeTd.classList.add('removeTd', 'text-center');
        nameTr.append(removeTd);
        let close = document.createElement('i');
        close.classList.add('fas', 'fa-times', 'text-danger');
        close.setAttribute('id', 'removeAuthorFromTable');
        close.setAttribute('onclick', 'removeAuthorFromArray(this)');
        close.setAttribute('authorIndex', index.toString());
        removeTd.append(close);
    }
}
