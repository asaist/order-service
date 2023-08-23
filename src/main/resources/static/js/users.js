class Author {
    constructor(id, fullName, degree, isSupervisor, passport, diploma, diplomaScienceDegree, diplomaScienceRank, noCriminalRecord, healthStatus, employmentBook) {
        this.id = id;
        this.fullName = fullName;
        this.degree = degree;
        this.isSupervisor = isSupervisor;
        this.passport = passport;
        this.diploma = diploma;
        this.diplomaScienceRank = diplomaScienceRank;
        this.diplomaScienceDegree = diplomaScienceDegree;
        this.noCriminalRecord = noCriminalRecord;
        this.healthStatus = healthStatus;
        this.employmentBook = employmentBook;
    }
}
let authors = [];
// var authorsFromDB = [];
function selectAuthor(el) {
    var e = document.getElementById("select");

    var selectData = $('#select').select2('data');
    var selectedIndex = $('#select').prop('selectedIndex');
    console.log(selectData, "selectData");
    console.log(selectedIndex, "индекс selectData");
    // var academicDegreeName = selectData[selectedIndex].academicDegreeName; // дает nextInt вместо того, в котором лежит data
    var academicDegreeName = selectData[0].academicDegreeName;
    console.log(academicDegreeName, "уч.степень");

    let value = e.value;
    let text = e.options[e.selectedIndex].text;
    let passportDB = selectData[0].passportDB;
    let diplomaDB = selectData[0].diplomaDB;
    let diplomaScienceRankDB = selectData[0].diplomaScienceRankDB;
    let diplomaScienceDegreeDB = selectData[0].diplomaScienceDegreeDB;
    let noCriminalRecordDB = selectData[0].noCriminalRecordDB;
    let healthStatusDB = selectData[0].healthStatusDB;
    let employmentBookDB = selectData[0].employmentBookDB;
    let id = e.options[e.selectedIndex].id;
    let index = e.options[e.selectedIndex];
    console.log(index, "индекс HTML");
    // let select = document.getElementById("select");
    // let value = select.value;
    let tableAuthor = document.getElementById('tableAuthor');

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
    nameTd.textContent = text;
    nameTr.append(nameTd);

    console.log(academicDegreeName, "ученая степень");
    console.log(text);
    console.log(id);
    console.log(value);
    let author = new Author(value, text, academicDegreeName, false);
    console.log(e.value.replace(/\s/g, ""));
    authors.push(author);
    console.log(authors);
    author.passport = null;
    author.diploma = null;
    author.diplomaScienceRank = null;
    author.diplomaScienceDegree = null;
    author.noCriminalRecord = null;
    author.healthStatus = null;
    author.employmentBook = null;
    let prof = document.createElement('td');
    prof.classList.add('text-center','regTd');
    prof.innerHTML = academicDegreeName;
    nameTr.append(prof);

    let checkTd = document.createElement('td');
    checkTd.classList.add('checkTd');
    nameTr.append(checkTd);
    let radioBtn = document.createElement('input');
    radioBtn.setAttribute('type','radio');
    radioBtn.setAttribute('name','flexRadioDefault');
    radioBtn.setAttribute('onclick','AuthorIsSupervisor(this)');
    radioBtn.setAttribute('id','isSupervisor');
    radioBtn.setAttribute('authorIndex', (authors.length - 1).toString());
    radioBtn.classList.add('form-check-input');
    checkTd.append(radioBtn);

    let docTd = document.createElement('td');
    docTd.classList.add('text-center', 'docTd');
    docTd.setAttribute('onclick','showDoc(this)');
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
    close.setAttribute('onclick','removeAuthorFromArray(this)');
    close.setAttribute('authorIndex', (authors.length - 1).toString());
    removeTd.append(close);

    //Строка для добавления документов (скрытая)

    let trDoc = document.createElement('tr');
    trDoc.classList.add('hidden','trDoc', 'mt-10');
    tr1.append(trDoc);

    let tdDoc = document.createElement('td');
    tdDoc.setAttribute('colspan','5');
    trDoc.append(tdDoc);
    let divViewDocPassport = document.createElement('div');
    tdDoc.append(divViewDocPassport);

    let passport = document.createElement('div');
    passport.classList.add('mb-10');

    divViewDocPassport.append(passport);
    let passportLabel = document.createElement('b');
    passportLabel.textContent = 'Загрузите паспорт';
    passport.append(passportLabel);
    let br = document.createElement('br');
    passportLabel.append(br);
    let trDocPassport = document.createElement('div');
    trDocPassport.classList.add('d-flex');
    passport.append(trDocPassport);

    let passportInput = document.createElement('input');
    passportInput.classList.add('form-control', 'mr-10', 'w-75');
    passportInput.setAttribute('type', 'file');
    passportInput.setAttribute('id', 'file-input');
    // passportInput.setAttribute('multiple placeholder', '1');
    let modalShowDocsBody = document.getElementById('modalShowDocsBody');

    passportInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                let elemDB = document.getElementById('passportFrame');
                if (elemDB != null){
                    elemDB.classList.add('hidden');
                }
                let elem = document.getElementById('xyz');
                if (elem === null) {
                    modalShowDocsBody.insertAdjacentHTML('beforeEnd', `<iframe id="xyz" src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`);
                }
                else {
                    elem.src=`${ev.target.result}`;
                }
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.passport = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocPassport.append(passportInput);

    let btnUpload = document.createElement('button');
    btnUpload.classList.add('btn', 'btn-primary', 'ml-10');
    btnUpload.setAttribute('type', 'button');
    btnUpload.setAttribute('id', author.id);
    btnUpload.setAttribute('fullName', author.fullName);
    btnUpload.setAttribute('passport', author.passport);
    // btnUpload.setAttribute('onclick', 'sendAuthorDocs(this)');
    btnUpload.addEventListener('click', function() {
        sendAuthorDocs(author);
        sendAuthorDocsUploadIcon(this);
    });
    trDocPassport.append(btnUpload);

    let uploadIcon = document.createElement('i');
    uploadIcon.classList.add('fas', 'fa-file-upload');
   /* uploadIcon.setAttribute('onclick', 'sendAuthorDocsUploadIcon(this)');*/
    btnUpload.append(uploadIcon);

    // Просмотр загруженного документа
    let btnViewDoc = document.createElement('button');
    btnViewDoc.classList.add('btn', 'ml-10', 'btn-outline-primary', 'hidden');
    btnViewDoc.setAttribute('type', 'button');
    btnViewDoc.setAttribute('onclick', 'buttonShowPas()');
    console.log("создали кнопку просмотра");
    trDocPassport.append(btnViewDoc);

    let viewIcon = document.createElement('i');
    viewIcon.classList.add('fas', 'fa-eye');
    btnViewDoc.append(viewIcon);

    // Кнопка удаления загруженного документа
    let btnDeleteDoc = document.createElement('button');
    btnDeleteDoc.classList.add('btn', 'ml-10', 'hidden');
    btnDeleteDoc.setAttribute('type', 'button');
    btnDeleteDoc.setAttribute('onclick', 'deleteDoc(this)');
    btnDeleteDoc.setAttribute('document', 'passportDB');
    btnDeleteDoc.setAttribute('author', author.id.toString());
    if (passportDB != null){
        btnDeleteDoc.setAttribute('filename', passportDB.toString());
    }


    //trDocPassport.append(btnDeleteDoc);
    console.log("создали кнопку удаления123");
    trDocPassport.insertAdjacentElement('beforeEnd', btnDeleteDoc);
    let deleteIcon = document.createElement('i');
    deleteIcon.classList.add('fas', 'fa-times', 'text-danger');
    btnDeleteDoc.append(deleteIcon);

    if (passportDB != null){

        btnViewDoc.classList.remove('hidden');

        btnViewDoc.addEventListener('click', function() {
            fetch(`/pdf/${passportDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf'
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    btnViewDoc.classList.remove('hidden');
                    const url = URL.createObjectURL(blob);
                    // let elemInput = document.getElementById('xyz');
                    // elemInput.classList.add('hidden');
                    let elem = document.getElementById('passportFrame');
                    if (elem === null) {
                        modalShowDocsBody.insertAdjacentHTML('afterend', `<iframe id="passportFrame" src="${url}" frameborder="0" height="500px" width="100%" />`);
                    } else {
                        elem.src = `${url}`;
                    }

                    passport.classList.remove('hidden');
                    // closePasFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnDeleteDoc.classList.remove('hidden');
    }
    //


    let divViewDocDiplom = document.createElement('div');
    tdDoc.append(divViewDocDiplom);

    let diplom = document.createElement('div');
    diplom.classList.add('mb-10');

    divViewDocDiplom.append(diplom);
    let diplomLabel = document.createElement('b');
    diplomLabel.textContent = 'Загрузите диплом';
    diplom.append(diplomLabel);
    let brDip = document.createElement('br');
    diplomLabel.append(brDip);
    let trDocDiplom = document.createElement('div');
    trDocDiplom.classList.add('d-flex');
    diplom.append(trDocDiplom);

    let diplomInput = document.createElement('input');
    diplomInput.classList.add('form-control', 'mr-10', 'w-75');
    diplomInput.setAttribute('type', 'file');
    diplomInput.setAttribute('id', 'file-input');
    // passportInput.setAttribute('multiple placeholder', '1');
    // let modalShowDocsBody = document.getElementById('modalShowDocsBody');

    diplomInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                let elemDB = document.getElementById('passportFrame');
                if (elemDB != null){
                    elemDB.classList.add('hidden');
                }
                let elem = document.getElementById('xyz');
                if (elem === null) {
                    modalShowDocsBody.insertAdjacentHTML('beforeEnd', `<iframe id="xyz" src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`);
                }
                else {
                    elem.src=`${ev.target.result}`;
                }
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.diploma = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocDiplom.append(diplomInput);

    let btnUploadDip = document.createElement('button');
    btnUploadDip.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadDip.setAttribute('type', 'button');
    btnUploadDip.setAttribute('id', author.id);
    btnUploadDip.setAttribute('fullName', author.fullName);
    btnUploadDip.setAttribute('passport', author.diploma);
    // btnUpload.setAttribute('onclick', 'sendAuthorDocs(this)');
    btnUploadDip.addEventListener('click', function() {
        sendAuthorDocs(author);
        sendAuthorDocsUploadIcon(this);
    });
    trDocDiplom.append(btnUploadDip);

    let uploadIconDip = document.createElement('i');
    uploadIconDip.classList.add('fas', 'fa-file-upload');
    /* uploadIcon.setAttribute('onclick', 'sendAuthorDocsUploadIcon(this)');*/
    btnUploadDip.append(uploadIconDip);

    // Просмотр загруженного документа
    let btnViewDocDip = document.createElement('button');
    btnViewDocDip.classList.add('btn', 'ml-10', 'btn-outline-primary', 'hidden');
    btnViewDocDip.setAttribute('type', 'button');
    btnViewDocDip.setAttribute('onclick', 'buttonShowPas()');
    console.log("создали кнопку просмотра");
    trDocDiplom.append(btnViewDocDip);

    let viewIconDip = document.createElement('i');
    viewIconDip.classList.add('fas', 'fa-eye');
    btnViewDocDip.append(viewIconDip);

    // Кнопка удаления загруженного документа
    let btnDeleteDocDip = document.createElement('button');
    btnDeleteDocDip.classList.add('btn', 'ml-10', 'hidden');
    btnDeleteDocDip.setAttribute('type', 'button');
    btnDeleteDocDip.setAttribute('onclick', 'deleteDoc(this)');
    btnDeleteDocDip.setAttribute('document', 'diplomaDB');
    btnDeleteDocDip.setAttribute('author', author.id.toString());
    if (diplomaDB != null){
        btnDeleteDocDip.setAttribute('filename', diplomaDB.toString());
    }


    //trDocPassport.append(btnDeleteDoc);
    console.log("создали кнопку удаления диплома");
    trDocDiplom.insertAdjacentElement('beforeEnd', btnDeleteDocDip);
    let deleteIconDip = document.createElement('i');
    deleteIconDip.classList.add('fas', 'fa-times', 'text-danger');
    btnDeleteDocDip.append(deleteIconDip);

    if (diplomaDB != null){

        btnViewDocDip.classList.remove('hidden');

        btnViewDocDip.addEventListener('click', function() {
            fetch(`/pdf/${diplomaDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf'
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    btnViewDocDip.classList.remove('hidden');
                    const url = URL.createObjectURL(blob);
                    // let elemInput = document.getElementById('xyz');
                    // elemInput.classList.add('hidden');
                    let elem = document.getElementById('passportFrame');
                    if (elem === null) {
                        modalShowDocsBody.insertAdjacentHTML('afterend', `<iframe id="passportFrame" src="${url}" frameborder="0" height="500px" width="100%" />`);
                    } else {
                        elem.src = `${url}`;
                    }

                    diplom.classList.remove('hidden');
                    // closePasFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnDeleteDocDip.classList.remove('hidden');
    }
    //

    let divViewRank = document.createElement('div');
    tdDoc.append(divViewRank);

    if (diplomaScienceRankDB != null){
        let btnRnkDB = document.createElement('button');
        // btnRnkDB.classList.add('btn', 'btn-primary', 'ml-10');
        btnRnkDB.classList.add('fas', 'fa-download', 'text-primary', 'mr-10');
        btnRnkDB.setAttribute('type', 'button');
        btnRnkDB.addEventListener('click', function() {
            fetch(`/pdf/${diplomaScienceRankDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf'
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    const url = URL.createObjectURL(blob);
                    btnRnkDB.insertAdjacentHTML('afterend', `<iframe src="${url}" id="rankFrame" frameborder="0" height="500px" width="100%" />`);
                    rank.classList.remove('hidden');
                    closeRankFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnRnkDB.textContent = 'Открыть диплом о научном звании';
        divViewRank.append(btnRnkDB);
    }
    let closeRankFrame = document.createElement('button');
    closeRankFrame.setAttribute('type', 'button');
    closeRankFrame.classList.add('ml-10', 'hidden');
    closeRankFrame.textContent = 'Закрыть диплом о научном звании';

    closeRankFrame.addEventListener('click', (event) => {
        let iframe = document.getElementById("rankFrame");
        iframe.style.display = 'none';
        closeRankFrame.classList.add('hidden');
        rank.classList.add('hidden');
    });
    divViewRank.append(closeRankFrame);
    let rank = document.createElement('div');
    if (diplomaScienceRankDB === null){
        rank.classList.add('mb-10');
    } else {
        rank.classList.add('mb-10', 'hidden');
    }
    divViewRank.append(rank);
    let rankLabel = document.createElement('b');
    rankLabel.textContent = 'Загрузите диплом о научном звании';
    rank.append(rankLabel);
    let brRank = document.createElement('br');
    rankLabel.append(brRank);
    let trDocRank = document.createElement('div');
    trDocRank.classList.add('d-flex');
    rank.append(trDocRank);
    let rankInput = document.createElement('input');
    rankInput.classList.add('form-control', 'mr-10', 'w-75');
    rankInput.setAttribute('type', 'file');
    rankInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                rankInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.diplomaScienceRank = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocRank.append(rankInput);
    let btnUploadRank = document.createElement('button');
    btnUploadRank.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadRank.setAttribute('type', 'button');
    btnUploadRank.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocRank.append(btnUploadRank);
    let uploadIconRank = document.createElement('i');
    uploadIconRank.classList.add('fas', 'fa-file-upload');
    btnUploadRank.append(uploadIconRank);

    let divViewDegree = document.createElement('div');
    tdDoc.append(divViewDegree);

    if (diplomaScienceDegreeDB != null){
        let btnDegreeDB = document.createElement('button');
        // btnDegreeDB.classList.add('btn', 'btn-primary', 'ml-10');
        btnDegreeDB.classList.add('fas', 'fa-download', 'text-primary', 'mr-10');
        btnDegreeDB.setAttribute('type', 'button');
        btnDegreeDB.addEventListener('click', function() {
            fetch(`/pdf/${diplomaScienceDegreeDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf'
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    const url = URL.createObjectURL(blob);
                    btnDegreeDB.insertAdjacentHTML('afterend', `<iframe src="${url}" id="degreeFrame" frameborder="0" height="500px" width="100%" />`);
                    degree.classList.remove('hidden');
                    closeDegreeFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnDegreeDB.textContent = 'Открыть диплом о научной степени';
        divViewDegree.append(btnDegreeDB);
    }
    let closeDegreeFrame = document.createElement('button');
    closeDegreeFrame.setAttribute('type', 'button');
    closeDegreeFrame.classList.add('ml-10', 'hidden');
    closeDegreeFrame.textContent = 'Закрыть диплом о научной степени';

    closeDegreeFrame.addEventListener('click', (event) => {
        let iframe = document.getElementById("degreeFrame");
        iframe.style.display = 'none';
        closeDegreeFrame.classList.add('hidden');
        degree.classList.add('hidden');
    });
    divViewDegree.append(closeDegreeFrame);
    let degree = document.createElement('div');
    if (diplomaScienceDegreeDB === null){
        degree.classList.add('mb-10');
    } else {
        degree.classList.add('mb-10', 'hidden');
    }
    divViewDegree.append(degree);
    let degreeLabel = document.createElement('b');
    degreeLabel.textContent = 'Загрузите диплом о научной степени';
    degree.append(degreeLabel);
    let brDegree = document.createElement('br');
    degreeLabel.append(brDegree);
    let trDocDegree = document.createElement('div');
    trDocDegree.classList.add('d-flex');
    degree.append(trDocDegree);
    let degreeInput = document.createElement('input');
    degreeInput.classList.add('form-control', 'mr-10', 'w-75');
    degreeInput.setAttribute('type', 'file');
    degreeInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                degreeInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.diplomaScienceDegree = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocDegree.append(degreeInput);
    let btnUploadDegree = document.createElement('button');
    btnUploadDegree.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadDegree.setAttribute('type', 'button');
    btnUploadDegree.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocDegree.append(btnUploadDegree);
    let uploadIconDegree = document.createElement('i');
    uploadIconDegree.classList.add('fas', 'fa-file-upload');
    btnUploadDegree.append(uploadIconDegree);

    let divViewCriminal = document.createElement('div');
    tdDoc.append(divViewCriminal);

    if (noCriminalRecordDB != null){
        let btnCriminalDB = document.createElement('button');
        // btnCriminalDB.classList.add('btn', 'btn-primary', 'ml-10');
        btnCriminalDB.classList.add('fas', 'fa-download', 'text-primary', 'mr-10');
        btnCriminalDB.setAttribute('type', 'button');
        btnCriminalDB.addEventListener('click', function() {
            fetch(`/pdf/${noCriminalRecordDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf'
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    const url = URL.createObjectURL(blob);
                    btnCriminalDB.insertAdjacentHTML('afterend', `<iframe src="${url}" frameborder="0" id="criminalFrame" height="500px" width="100%" />`);
                    criminal.classList.remove('hidden');
                    closeCriminalFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnCriminalDB.textContent = 'Открыть справку об отсутствии судимости';
        divViewCriminal.append(btnCriminalDB);
    }
    let closeCriminalFrame = document.createElement('button');
    closeCriminalFrame.setAttribute('type', 'button');
    closeCriminalFrame.classList.add('ml-10', 'hidden');
    closeCriminalFrame.textContent = 'Закрыть справку об отсутствии судимости';

    closeCriminalFrame.addEventListener('click', (event) => {
        let iframe = document.getElementById("criminalFrame");
        iframe.style.display = 'none';
        closeCriminalFrame.classList.add('hidden');
        criminal.classList.add('hidden');
    });
    divViewCriminal.append(closeCriminalFrame);
    let criminal = document.createElement('div');
    if (noCriminalRecordDB === null){
        criminal.classList.add('mb-10');
    } else {
        criminal.classList.add('mb-10', 'hidden');
    }
    divViewCriminal.append(criminal);
    let criminalLabel = document.createElement('b');
    criminalLabel.textContent = 'Загрузите справку об отсутствии судимости';
    criminal.append(criminalLabel);
    let brCriminal = document.createElement('br');
    criminalLabel.append(brCriminal);
    let trDocCriminal = document.createElement('div');
    trDocCriminal.classList.add('d-flex');
    criminal.append(trDocCriminal);
    let criminalInput = document.createElement('input');
    criminalInput.classList.add('form-control', 'mr-10', 'w-75');
    criminalInput.setAttribute('type', 'file');
    criminalInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                criminalInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.noCriminalRecord = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocCriminal.append(criminalInput);
    let btnUploadCriminal = document.createElement('button');
    btnUploadCriminal.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadCriminal.setAttribute('type', 'button');
    btnUploadCriminal.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocCriminal.append(btnUploadCriminal);
    let uploadIconCriminal = document.createElement('i');
    uploadIconCriminal.classList.add('fas', 'fa-file-upload');
    btnUploadCriminal.append(uploadIconCriminal);

    let divViewHealth = document.createElement('div');
    tdDoc.append(divViewHealth);

    if (healthStatusDB != null){
        let btnHealthDB = document.createElement('button');
        // btnHealthDB.classList.add('btn', 'btn-primary', 'ml-10');
        btnHealthDB.classList.add('fas', 'fa-download', 'text-primary', 'mr-10');
        btnHealthDB.setAttribute('type', 'button');
        btnHealthDB.addEventListener('click', function() {
            fetch(`/pdf/${healthStatusDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf'
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    const url = URL.createObjectURL(blob);
                    btnHealthDB.insertAdjacentHTML('afterend', `<iframe src="${url}" id="healthFrame" frameborder="0" height="500px" width="100%" />`);
                    health.classList.remove('hidden');
                    closeHealthFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnHealthDB.textContent = 'Открыть справку о состоянии здоровья';
        divViewHealth.append(btnHealthDB);
    }
    let closeHealthFrame = document.createElement('button');
    closeHealthFrame.setAttribute('type', 'button');
    closeHealthFrame.classList.add('ml-10', 'hidden');
    closeHealthFrame.textContent = 'Закрыть справку о состоянии здоровья';

    closeHealthFrame.addEventListener('click', (event) => {
        let iframe = document.getElementById("healthFrame");
        iframe.style.display = 'none';
        closeHealthFrame.classList.add('hidden');
        health.classList.add('hidden');
    });
    divViewHealth.append(closeHealthFrame);
    let health = document.createElement('div');
    if (healthStatusDB === null){
        health.classList.add('mb-10');
    } else {
        health.classList.add('mb-10', 'hidden');
    }
    divViewHealth.append(health);
    let healthLabel = document.createElement('b');
    healthLabel.textContent = 'Загрузите справку о состоянии здоровья';
    health.append(healthLabel);
    let brHealth = document.createElement('br');
    healthLabel.append(brHealth);
    let trDocHealth = document.createElement('div');
    trDocHealth.classList.add('d-flex');
    health.append(trDocHealth);
    let healthInput = document.createElement('input');
    healthInput.classList.add('form-control', 'mr-10', 'w-75');
    healthInput.setAttribute('type', 'file');
    healthInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                healthInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.healthStatus = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocHealth.append(healthInput);
    let btnUploadHealth = document.createElement('button');
    btnUploadHealth.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadHealth.setAttribute('type', 'button');
    btnUploadHealth.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocHealth.append(btnUploadHealth);
    let uploadIconHealth = document.createElement('i');
    uploadIconHealth.classList.add('fas', 'fa-file-upload');
    btnUploadHealth.append(uploadIconHealth);

    let divViewEmp = document.createElement('div');
    tdDoc.append(divViewEmp);

    if (employmentBookDB != null){
        let btnExpDB = document.createElement('button');
        // btnExpDB.classList.add('btn', 'btn-primary', 'ml-10');
        btnExpDB.classList.add('fas', 'fa-download', 'text-primary', 'mr-10');
        btnExpDB.setAttribute('type', 'button');
        btnExpDB.addEventListener('click', function() {
            fetch(`/pdf/${employmentBookDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf'
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    const url = URL.createObjectURL(blob);
                    btnExpDB.insertAdjacentHTML('afterend', `<iframe src="${url}" frameborder="0" id="expFrame" height="500px" width="100%" />`);
                    experience.classList.remove('hidden');
                    closeEmpFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnExpDB.textContent = 'Открыть последнюю страницу трудовой книжки';
        divViewEmp.append(btnExpDB);
    }
    let closeEmpFrame = document.createElement('button');
    closeEmpFrame.setAttribute('type', 'button');
    closeEmpFrame.classList.add('ml-10', 'hidden');
    closeEmpFrame.textContent = 'Закрыть последнюю страницу трудовой книжки';

    closeEmpFrame.addEventListener('click', (event) => {
        let iframe = document.getElementById("expFrame");
        iframe.style.display = 'none';
        closeEmpFrame.classList.add('hidden');
        experience.classList.add('hidden');
    });
    divViewEmp.append(closeEmpFrame);
    let experience = document.createElement('div');
    if (employmentBookDB === null){
        experience.classList.add('mb-10');
    } else {
        experience.classList.add('mb-10', 'hidden');
    }
    divViewEmp.append(experience);
    let experienceLabel = document.createElement('b');
    experienceLabel.textContent = 'Загрузите последнюю страницу трудовой книжки';
    experience.append(experienceLabel);
    let brExperience = document.createElement('br');
    experienceLabel.append(brExperience);
    let trDocExperience = document.createElement('div');
    trDocExperience.classList.add('d-flex');
    experience.append(trDocExperience);
    let experienceInput = document.createElement('input');
    experienceInput.classList.add('form-control', 'mr-10', 'w-75');
    experienceInput.setAttribute('type', 'file');
    experienceInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                experienceInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.employmentBook = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocExperience.append(experienceInput);
    let btnUploadExperience = document.createElement('button');
    btnUploadExperience.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadExperience.setAttribute('type', 'button');
    btnUploadExperience.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocExperience.append(btnUploadExperience);
    let uploadIconExperience = document.createElement('i');
    uploadIconExperience.classList.add('fas', 'fa-file-upload');
    btnUploadExperience.append(uploadIconExperience);
    //
}
function inputNameAuthor() {
    let tableAuthor = document.getElementById('tableAuthor');

    let divDoc = document.createElement('tr');
    divDoc.classList.add('divDoc');
    tableAuthor.append(divDoc);

    let divDocTd = document.createElement('td');
    divDocTd.setAttribute('colspan', '5');
    divDoc.append(divDocTd);

    let tr1 = document.createElement('table');
    tr1.classList.add('w-100', 'tr1');
    divDocTd.append(tr1);

    let inputNameAuthor = document.createElement('tr');
    tr1.append(inputNameAuthor);
    let inputNameAuthorTd = document.createElement('td');

    let x = document.getElementById('inputNameAuthor').value;
    inputNameAuthorTd.innerHTML = x;
    inputNameAuthorTd.classList.add('text-primary', 'nameTd');
    inputNameAuthor.append(inputNameAuthorTd);
    document.getElementById('inputNameAuthor').value = ' ';
    console.log(inputNameAuthorTd.textContent);

    let prof = document.createElement('td');
    let regalia = document.getElementById('regalia').value;
    prof.innerHTML = regalia;
    prof.classList.add('regTd', 'text-center');
    inputNameAuthor.append(prof);
    console.log(regalia);
    let author = new Author(null, inputNameAuthorTd.textContent, regalia, false);
    authors.push(author);
    console.log(authors);

    let checkTd = document.createElement('td');
    checkTd.classList.add('checkTd');
    inputNameAuthor.append(checkTd);
    let radioBtn = document.createElement('input');
    radioBtn.setAttribute('type','radio');
    radioBtn.setAttribute('name','flexRadioDefault');
    radioBtn.setAttribute('onclick','AuthorIsSupervisor(this)');
    radioBtn.setAttribute('id','isSupervisor');
    radioBtn.setAttribute('authorIndex', (authors.length - 1).toString());
    radioBtn.classList.add('form-check-input');
    checkTd.append(radioBtn);

    let docTd = document.createElement('td');
    docTd.setAttribute('onclick','showDoc(this)');
    docTd.classList.add('text-center', 'docTd');
    inputNameAuthor.append(docTd);
    let docTdIcon = document.createElement('i');
    docTdIcon.classList.add('fas', 'fa-file-upload', 'text-primary');
    docTd.append(docTdIcon);

    let removeTd = document.createElement('td');
    removeTd.classList.add('removeTd', 'text-center');
    inputNameAuthor.append(removeTd);
    let close = document.createElement('i');
    close.classList.add('fas', 'fa-times', 'text-danger');
    close.setAttribute('id', 'removeAuthorFromTable');
    close.setAttribute('onclick','removeAuthorFromArray(this)');
    close.setAttribute('authorIndex', (authors.length - 1).toString());
    removeTd.append(close);

    //Строка для добавления документов (скрытая)
    let trDoc = document.createElement('tr');
    trDoc.classList.add('hidden', 'trDoc','mt-10');
    trDoc.setAttribute('id', 'trDoc');
    tr1.append(trDoc);

    let tdDoc = document.createElement('td');
    tdDoc.setAttribute('colspan','5');
    trDoc.append(tdDoc);

    let passport = document.createElement('div');
    passport.classList.add('mb-10');
    tdDoc.append(passport);
    let passportLabel = document.createElement('b');
    passportLabel.textContent = 'Загрузите паспорт';
    passport.append(passportLabel);
    let br = document.createElement('br');
    passportLabel.append(br);
    let trDocPassport = document.createElement('div');
    trDocPassport.classList.add('d-flex');
    passport.append(trDocPassport);

    let passportInput = document.createElement('input');
    passportInput.classList.add('form-control', 'mr-10', 'w-75');
    passportInput.setAttribute('type', 'file');
    passportInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                passportInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.passport = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocPassport.append(passportInput);

    let btnUpload = document.createElement('button');
    btnUpload.classList.add('btn', 'btn-primary', 'ml-10');
    btnUpload.setAttribute('type', 'button');
    btnUpload.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocPassport.append(btnUpload);
    let uploadIcon = document.createElement('i');
    uploadIcon.classList.add('fas', 'fa-file-upload');
    btnUpload.append(uploadIcon);

    let diplom = document.createElement('div');
    diplom.classList.add('mb-10');
    tdDoc.append(diplom);
    let diplomLabel = document.createElement('b');
    diplomLabel.textContent = 'Загрузите диплом о в/о';
    diplom.append(diplomLabel);
    let brDip = document.createElement('br');
    diplomLabel.append(brDip);
    let trDocDiplom = document.createElement('div');
    trDocDiplom.classList.add('d-flex');
    diplom.append(trDocDiplom);
    let diplomInput = document.createElement('input');
    diplomInput.classList.add('form-control', 'mr-10', 'w-75');
    diplomInput.setAttribute('type', 'file');
    diplomInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                diplomInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.diploma = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocDiplom.append(diplomInput);
    let btnUploadDip = document.createElement('button');
    btnUploadDip.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadDip.setAttribute('type', 'button');
    btnUploadDip.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocDiplom.append(btnUploadDip);
    let uploadIconDip = document.createElement('i');
    uploadIconDip.classList.add('fas', 'fa-file-upload');
    btnUploadDip.append(uploadIconDip);

    let rank = document.createElement('div');
    rank.classList.add('mb-10');
    tdDoc.append(rank);
    let rankLabel = document.createElement('b');
    rankLabel.textContent = 'Загрузите диплом о научном звании';
    rank.append(rankLabel);
    let brRank = document.createElement('br');
    rankLabel.append(brRank);
    let trDocRank = document.createElement('div');
    trDocRank.classList.add('d-flex');
    rank.append(trDocRank);
    let rankInput = document.createElement('input');
    rankInput.classList.add('form-control', 'mr-10', 'w-75');
    rankInput.setAttribute('type', 'file');
    rankInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                rankInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.diplomaScienceRank = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocRank.append(rankInput);
    let btnUploadRank = document.createElement('button');
    btnUploadRank.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadRank.setAttribute('type', 'button');
    btnUploadRank.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocRank.append(btnUploadRank);
    let uploadIconRank = document.createElement('i');
    uploadIconRank.classList.add('fas', 'fa-file-upload');
    btnUploadRank.append(uploadIconRank);

    let degree = document.createElement('div');
    degree.classList.add('mb-10');
    tdDoc.append(degree);
    let degreeLabel = document.createElement('b');
    degreeLabel.textContent = 'Загрузите диплом о научной степени';
    degree.append(degreeLabel);
    let brDegree = document.createElement('br');
    degreeLabel.append(brDegree);
    let trDocDegree = document.createElement('div');
    trDocDegree.classList.add('d-flex');
    degree.append(trDocDegree);
    let degreeInput = document.createElement('input');
    degreeInput.classList.add('form-control', 'mr-10', 'w-75');
    degreeInput.setAttribute('type', 'file');
    degreeInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                degreeInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.diplomaScienceDegree = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocDegree.append(degreeInput);
    let btnUploadDegree = document.createElement('button');
    btnUploadDegree.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadDegree.setAttribute('type', 'button');
    btnUploadDegree.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocDegree.append(btnUploadDegree);
    let uploadIconDegree = document.createElement('i');
    uploadIconDegree.classList.add('fas', 'fa-file-upload');
    btnUploadDegree.append(uploadIconDegree);

    let criminal = document.createElement('div');
    criminal.classList.add('mb-10');
    tdDoc.append(criminal);
    let criminalLabel = document.createElement('b');
    criminalLabel.textContent = 'Загрузите справку об отсутствии судимости';
    criminal.append(criminalLabel);
    let brCriminal = document.createElement('br');
    criminalLabel.append(brCriminal);
    let trDocCriminal = document.createElement('div');
    trDocCriminal.classList.add('d-flex');
    criminal.append(trDocCriminal);
    let criminalInput = document.createElement('input');
    criminalInput.classList.add('form-control', 'mr-10', 'w-75');
    criminalInput.setAttribute('type', 'file');
    criminalInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                criminalInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.noCriminalRecord = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocCriminal.append(criminalInput);
    let btnUploadCriminal = document.createElement('button');
    btnUploadCriminal.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadCriminal.setAttribute('type', 'button');
    btnUploadCriminal.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocCriminal.append(btnUploadCriminal);
    let uploadIconCriminal = document.createElement('i');
    uploadIconCriminal.classList.add('fas', 'fa-file-upload');
    btnUploadCriminal.append(uploadIconCriminal);

    let health = document.createElement('div');
    health.classList.add('mb-10');
    tdDoc.append(health);
    let healthLabel = document.createElement('b');
    healthLabel.textContent = 'Загрузите справку о состоянии здоровья';
    health.append(healthLabel);
    let brHealth = document.createElement('br');
    healthLabel.append(brHealth);
    let trDocHealth = document.createElement('div');
    trDocHealth.classList.add('d-flex');
    health.append(trDocHealth);
    let healthInput = document.createElement('input');
    healthInput.classList.add('form-control', 'mr-10', 'w-75');
    healthInput.setAttribute('type', 'file');
    healthInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                healthInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.healthStatus = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocHealth.append(healthInput);
    let btnUploadHealth = document.createElement('button');
    btnUploadHealth.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadHealth.setAttribute('type', 'button');
    btnUploadHealth.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocHealth.append(btnUploadHealth);
    let uploadIconHealth = document.createElement('i');
    uploadIconHealth.classList.add('fas', 'fa-file-upload');
    btnUploadHealth.append(uploadIconHealth);

    let experience = document.createElement('div');
    experience.classList.add('mb-10');
    tdDoc.append(experience);
    let experienceLabel = document.createElement('b');
    experienceLabel.textContent = 'Загрузите последнюю страницу трудовой книжки';
    experience.append(experienceLabel);
    let brExperience = document.createElement('br');
    experienceLabel.append(brExperience);
    let trDocExperience = document.createElement('div');
    trDocExperience.classList.add('d-flex');
    experience.append(trDocExperience);
    let experienceInput = document.createElement('input');
    experienceInput.classList.add('form-control', 'mr-10', 'w-75');
    experienceInput.setAttribute('type', 'file');
    experienceInput.addEventListener('change', function(event) {
        const fileList = event.target.files; // Получаем список выбранных файлов

        if(!fileList.length){
            return;
        }
        const files = Array.from(event.target.files);
        console.log(files, 'файлы');
        files.forEach(file => {
            const reader = new FileReader();
            reader.onload = ev => {
                console.log(ev.target.result);
                experienceInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`)
            }
            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.employmentBook = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocExperience.append(experienceInput);
    let btnUploadExperience = document.createElement('button');
    btnUploadExperience.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadExperience.setAttribute('type', 'button');
    btnUploadExperience.addEventListener('click', function() {
        sendAuthorDocs(author);
    });
    trDocExperience.append(btnUploadExperience);
    let uploadIconExperience = document.createElement('i');
    uploadIconExperience.classList.add('fas', 'fa-file-upload');
    btnUploadExperience.append(uploadIconExperience);
    //

    document.getElementById('regalia').options[0].selected = true; //очищаем селект
    let badge = document.querySelectorAll('.badgeProf');
    for( let i = 0; i < badge.length; i++ ){
        badge[i].outerHTML = "";
    }

}

// Добавляем файлы в FormData
//     var files = document.getElementById('fileInput').files;
//     for ( i = 0; i < files.length; i++) {
//         formData.append('files', files[i]);
//     }



async function sendAuthorDocs(author) {
    let formData = new FormData();
    // formData.append('id', el.getAttribute('id'));
    // formData.append('fullName', el.getAttribute('fullName'));
    // formData.append('passport', authors[0].passport);

    formData.append('id', author.id);
    formData.append('fullName', author.fullName);
    formData.append('passport', author.passport);
    formData.append('diploma', author.diploma);
    formData.append('diplomaScienceRank', author.diplomaScienceRank);
    formData.append('diplomaScienceDegree', author.diplomaScienceDegree);
    formData.append('noCriminalRecord', author.noCriminalRecord);
    formData.append('healthStatus', author.healthStatus);
    formData.append('employmentBook', author.employmentBook);
    console.log(formData.get('passport'));
    let response = await fetch('/api/author/docs', {
        method: 'POST',
        body: formData
    }) .then(response => response.json())
        .then(data => {
            const newAuthorId = data;
            author.id = newAuthorId;
            // savedTrack = data;
            // Используйте полученный ID нового трека для нужных действий на фронтенде
            console.log('ID нового автора:', newAuthorId);
        })
        .catch(error => {
            console.error('Ошибка при отправке запроса:', error);
        });
}

function displayPdfAsImage(pdfFile) {
    const fileReader = new FileReader();

    fileReader.onload = function() {
        const typedArray = new Uint8Array(this.result);

        // Загрузка PDF-файла
        pdfjsLib.getDocument(typedArray).promise.then((pdf) => {
            // Получение первой страницы PDF-файла
            pdf.getPage(1).then((page) => {
                const scale = 1.5;
                const viewport = page.getViewport({ scale });

                // Создание элемента canvas для отрисовки страницы
                const canvas = document.createElement('canvas');
                const context = canvas.getContext('2d');
                canvas.width = viewport.width;
                canvas.height = viewport.height;

                // Отрисовка страницы на canvas
                page.render({
                    canvasContext: context,
                    viewport: viewport
                })
                    .promise.then(() => {
                    // Создание элемента img и отображение картинки
                    const img = document.createElement('img');
                    img.src = canvas.toDataURL();
                    document.body.appendChild(img);
                });
            });
        });
    };
    function showAuthorsDoc(el){

    }
}

    function buttonShowPas(){
        let modalShowDocs = document.getElementById('modalShowDocs');
        modalShowDocs.classList.remove('hidden');
        let overlay = document.getElementById('overlay');
        overlay.classList.remove('hidden');
        // files.forEach(file => {
        //     const reader = new FileReader();
        //     reader.onload = ev => {
        //         console.log(ev.target.result);
        //         passportInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`);
        //     }
        //
        //     reader.readAsDataURL(file);
        // })
    }
    function sendAuthorDocsUploadIcon(el) {
       let btnViewDoc = el.nextElementSibling;
        btnViewDoc.classList.remove('hidden');
        let btnDeleteDoc = btnViewDoc.nextElementSibling;
        btnDeleteDoc.classList.remove('hidden');
    }
    function deleteDoc(el) {
        event.stopPropagation();
        let deleteDoc = el.previousElementSibling;
        deleteDoc.classList.add('hidden');
        el.classList.add('hidden');

        let id = el.getAttribute('author');
        let document = el.getAttribute('document');
        let filename = el.getAttribute('filename');

        fetch(`/pdf/${id}/${document}/${filename}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/pdf'
            }
        }).then(response => {
            if (response.ok) {
                // Документ успешно удален
            } else {
                // Возникла ошибка при удалении документа
            }
        }).catch(error => {
            // Возникла ошибка при выполнении запроса
        });


    }