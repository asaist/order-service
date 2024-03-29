function getAuthorsForEditTrack(id) {
    // const url = '/authors_track'; // здесь id - идентификатор трека
    return fetch(`/authors_track/${id}`)
    .then(response => response.json())
        .then(data => {

            authors = data.authors.map(author => {
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
            lectures = data.lectures.map(lecture => {
                return {
                    id: lecture.id,
                    moduleId: lecture.moduleId,
                    lectureModuleName: lecture.lectureModuleName,
                    lectureModuleAnnotation: lecture.lectureModuleAnnotation
                };
            });
            modules = data.modules.map(module => {
                return {
                    id: module.id,
                    moduleNameModal: module.moduleNameModal,
                    moduleModalAnnotation: module.moduleModalAnnotation
                };
            });
            // Используйте полученный ID нового трека для нужных действий на фронтенде
            console.log('Авторы трека', authors);
            savedTrack = id;
            drawAuthorTable(authors);
            drawTrackScheme(modules, lectures);
        })
        .catch(error => {
            console.error('Ошибка при отправке запроса:', error);
        });
}

function showDoc1(el) {
    var tbl = el.parentElement.parentElement;
    tbl.rows[1].classList.toggle('hidden');
}

function drawTrackScheme(modules, lectures){
    let indexMod = 0;
    let indexLec = 0;


    for (const module of modules) {

        let newModuleScheme = document.createElement('div');
        newModuleScheme.classList.add('mx-3');
        newModuleSchemeNumber++; // Присваиваем id
        newModuleScheme.id = "module_" + newModuleSchemeNumber;
        let rowModule = document.getElementById('rowModule');
        rowModule.append(newModuleScheme);

        let bgPrimary = document.createElement('div');
        bgPrimary.classList.add('bg-primary','rounded','d-flex', 'p-3');
        newModuleScheme.append(bgPrimary);

        let col11 = document.createElement('div');
        col11.classList.add('col-10');
        bgPrimary.append(col11);

        let modName = document.createElement('button');
        modName.classList.add('btn', 'btn-sm', 'rounded', 'text-white', 'text-center', 'fs16', 'pb-0', 'mb-0');
        modName.setAttribute('type', 'button');
        modName.setAttribute('onclick', 'editMod(this)');
        modName.setAttribute('index', indexMod.toString());
        modName.setAttribute('moduleModalId', moduleModalId);
        modName.setAttribute('moduleNameModal', module.moduleNameModal);
        modName.setAttribute('moduleModalAnnotation', module.moduleModalAnnotation);
        modName.setAttribute('moduleModalKeyWords', module.moduleModalKeyWords);
        modName.textContent = module.moduleNameModal;
        col11.append(modName);

        let col1 = document.createElement('div');
        col1.classList.add('col-2');
        bgPrimary.append(col1);

        let button = document.createElement('button');
        button.classList.add('btn', 'btn-sm', 'rounded');
        button.setAttribute('type', 'button')
        button.setAttribute('onclick', 'addLectureInModuleBegin(this)');
        button.setAttribute('moduleNameModal', module.moduleNameModal);
        button.setAttribute('moduleModalId', moduleModalId);
        button.setAttribute('id', 'moduleModalId_' + moduleModalId);
        col1.append(button);

        module.moduleModalId = moduleModalId;
        moduleModalId = moduleModalId + 1;

        let icon = document.createElement('i');
        icon.classList.add('fas', 'fa-plus', 'text-white');
        button.append(icon);

        let rowLecInModule = document.createElement('div');
        rowLecInModule.classList.add('d-flex', 'justify-content-between', 'my-2', 'rowLecInModule');
        rowLecInModule.textContent = '';
        newModuleScheme.append(rowLecInModule);
        indexMod++;
        for (const lecture of lectures) {
            if (lecture.moduleId === module.id){
                let lectureModuleName = lecture.lectureModuleName;
                let lectureModuleAnnotation = lecture.lectureModuleAnnotation;
                let lectureModuleKeyWords = lecture.lectureModuleKeyWords;
                lectureModalId = lectureModalId + 1;

                let newLecInScheme = document.createElement('div');
                newLecInScheme.setAttribute("onmouseover","HintShowbyTamara(this)");
                newLecInScheme.setAttribute("onmouseout","HintHidebyTamara(this)");
                newLecInScheme.classList.add('bg-success','m-3', 'p-3', 'rounded','lectureBlockScheme', 'd-flex');
                rowLecInModule.append(newLecInScheme);
                let lecName = document.createElement('button');
                lecName.classList.add('btn', 'btn-sm', 'rounded', 'text-white', 'text-center', 'm-0', 'p-0', 'text-truncate', 'lectureBlockSchemeText');
                lecName.setAttribute('type', 'button');
                lecName.setAttribute('onclick', 'editLec(this)');
                lecName.setAttribute('index', indexLec.toString());
                lecName.setAttribute('lectureModuleName', lectureModuleName);
                lecName.setAttribute('lectureModuleAnnotation', lectureModuleAnnotation);
                lecName.setAttribute('lectureModuleKeyWords', lectureModuleKeyWords);
                lecName.setAttribute('lectureModalId', (lectureModalId -1).toString());
                lecName.setAttribute('moduleModalId', moduleModalId);
                lecName.setAttribute('moduleNameModal', module.moduleNameModal);
                lecName.textContent = lectureModuleName;
                newLecInScheme.append(lecName);

                //кнопка удаления лекции
                let button = document.createElement('button');
                button.classList.add('btn', 'btn-sm', 'rounded');
                button.setAttribute('type', 'button')
                button.setAttribute('onclick', 'removeLecInModule(this)');
                button.setAttribute('lectureModalId', lectureModalId);
                button.setAttribute('index', indexLec.toString());

                newLecInScheme.append(button);
                let icon = document.createElement('i');
                icon.classList.add('fas', 'fa-times', 'text-white');
                button.append(icon);
                document.getElementById('lectureModuleName').value = "";
                document.getElementById('lectureModuleAnnotation').value = "";
                document.getElementById('lectureModuleKeyWords').value = "";
                btnClose();
            }
        }
    }
    for (const lecture of lectures ){
        if (lecture.moduleId === null){

            let lectureModalName = lecture.lectureModuleName;
            let lectureModalAnnotation = lecture.lectureModuleAnnotation;
            let lectureModalKeyWords = lecture.lectureModuleKeyWords;

            let newLecScheme = document.createElement('div');
            newLecScheme.setAttribute("onmouseover","HintShowbyTamara(this)");
            newLecScheme.setAttribute("onmouseout","HintHidebyTamara(this)");
            newLecScheme.classList.add('bg-success','mx-2', 'p-3', 'mb-1', 'rounded','lectureBlockScheme', 'lecBlockScheme', 'd-flex');
            newLectureSchemeNumber++; // Присваиваем id
            newLecScheme.id = "Lecture_" + newLectureSchemeNumber;
            const colLec = document.getElementById('colLec');
            colLec.append(newLecScheme);

            let lecName = document.createElement('button');
            lecName.classList.add('btn', 'btn-sm', 'rounded', 'text-white', 'text-center', 'm-0', 'p-0', 'text-truncate', 'lectureBlockSchemeText');
            lecName.setAttribute('type', 'button');
            lecName.setAttribute('onclick', 'editLecOut(this)');
            lecName.setAttribute('index', indexLec.toString());
            lecName.setAttribute('lectureModuleName', lectureModalName);
            lecName.setAttribute('lectureModuleAnnotation', lectureModalAnnotation);
            lecName.setAttribute('lectureModuleKeyWords', lectureModalKeyWords);
            lecName.setAttribute('lectureModalId', lectureModalId);
            lecName.textContent = lectureModalName;
            newLecScheme.append(lecName);

            //кнопка удаления лекции
            let button = document.createElement('button');
            button.classList.add('btn', 'btn-sm', 'rounded');
            button.setAttribute('type', 'button')
            button.setAttribute('onclick', 'removeLecInModule(this)');
            button.setAttribute('lectureModalId', lectureModalId);
            button.setAttribute('index', indexLec .toString());
            newLecScheme.append(button);
            lectureModalId = lectureModalId + 1;
            let icon = document.createElement('i');
            icon.classList.add('fas', 'fa-times', 'text-white');
            button.append(icon);
            document.getElementById('lectureModalName').value = "";
            document.getElementById('lectureModalAnnotation').value = "";
            document.getElementById('lectureModalKeyWords').value = "";
            btnClose();
        }
    }
}

function drawAuthorTable(authors){
    for (const author of authors) {
        const index = authors.indexOf(author);
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
        docTd.setAttribute('onclick', 'showDoc1(this)');
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

        let passportDB = author.passport;
        let diplomaDB = author.diploma;
        let diplomaScienceRankDB = author.diplomaScienceRank;
        let diplomaScienceDegreeDB = author.diplomaScienceDegree;
        let noCriminalRecordDB = author.noCriminalRecord;
        let healthStatusDB = author.healthStatus;
        let employmentBookDB = author.employmentBook;

        //Строка для добавления документов (скрытая)

        let trDoc = document.createElement('tr');
        trDoc.classList.add('hidden','trDoc', 'mt-10');
        tr1.append(trDoc);

        let tdDoc = document.createElement('td');
        tdDoc.setAttribute('colspan','5');
        trDoc.append(tdDoc);
        let divViewDocPassport = document.createElement('div');
        tdDoc.append(divViewDocPassport);
        if (passportDB != null){
            let btnPassDB = document.createElement('button');
            // btnPassDB.classList.add('btn', 'btn-primary', 'ml-10');
            btnPassDB.classList.add('fas', 'fa-download', 'text-primary', 'mr-10');
            btnPassDB.setAttribute('type', 'button');
            btnPassDB.addEventListener('click', function() {
                fetch(`/pdf/${passportDB}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/pdf'
                    }
                })
                    .then(response => response.blob())
                    .then(blob => {
                        const url = URL.createObjectURL(blob);
                        btnPassDB.insertAdjacentHTML('afterend', `<iframe id="passportFrame" src="${url}" frameborder="0" height="500px" width="100%" />`);
                        passport.classList.remove('hidden');
                        closePasFrame.classList.remove('hidden');
                    })
                    .catch(error => console.error('Ошибка:', error));
            });
            btnPassDB.textContent = 'Открыть паспорт';
            divViewDocPassport.append(btnPassDB);
        }
        // closePasFrame.classList.add('fas', 'fa-times', 'text-danger', 'hidden');
        let closePasFrame = document.createElement('button');
        closePasFrame.setAttribute('type', 'button');
        closePasFrame.classList.add('ml-10', 'hidden');
        closePasFrame.textContent = 'Закрыть паспорт';

        closePasFrame.addEventListener('click', (event) => {
            let iframe = document.getElementById("passportFrame");
            iframe.style.display = 'none';
            closePasFrame.classList.add('hidden');
            passport.classList.add('hidden');
        });
        divViewDocPassport.append(closePasFrame);
        let passport = document.createElement('div');
        if (passportDB === null){
            passport.classList.add('mb-10');
        } else {
            passport.classList.add('mb-10', 'hidden');
        }

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
                    passportInput.insertAdjacentHTML('afterend', `<iframe src="${ev.target.result}" frameborder="0" height="500px" width="100%" />`);
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
        btnUpload.setAttribute('onclick', 'sendAuthorDocs(this)');
        btnUpload.addEventListener('click', function() {
            sendAuthorDocs(author);
        });
        trDocPassport.append(btnUpload);
        let uploadIcon = document.createElement('i');
        uploadIcon.classList.add('fas', 'fa-file-upload');
        btnUpload.append(uploadIcon);

        let divViewDip = document.createElement('div');
        tdDoc.append(divViewDip);

        if (diplomaDB != null){
            let btnDipDB = document.createElement('button');
            // btnDipDB.classList.add('btn', 'btn-primary', 'ml-10');
            btnDipDB.classList.add('fas', 'fa-download', 'text-primary', 'mr-10');
            btnDipDB.setAttribute('type', 'button');
            btnDipDB.addEventListener('click', function() {
                fetch(`/pdf/${diplomaDB}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/pdf'
                    }
                })
                    .then(response => response.blob())
                    .then(blob => {
                        const url = URL.createObjectURL(blob);
                        btnDipDB.insertAdjacentHTML('afterend', `<iframe src="${url}" id="diplomaFrame" frameborder="0" height="500px" width="100%" />`);
                        diplom.classList.remove('hidden');
                        closeDipFrame.classList.remove('hidden');
                    })
                    .catch(error => console.error('Ошибка:', error));
            });
            btnDipDB.textContent = 'Открыть диплом';
            divViewDip.append(btnDipDB);
        }
        let closeDipFrame = document.createElement('button');
        closeDipFrame.setAttribute('type', 'button');
        closeDipFrame.classList.add('ml-10', 'hidden');
        closeDipFrame.textContent = 'Закрыть диплом';

        closeDipFrame.addEventListener('click', (event) => {
            let iframe = document.getElementById("diplomaFrame");
            iframe.style.display = 'none';
            closeDipFrame.classList.add('hidden');
            diplom.classList.add('hidden');
        });
        divViewDip.append(closeDipFrame);
        let diplom = document.createElement('div');
        if (diplomaDB === null){
            diplom.classList.add('mb-10');
        } else {
            diplom.classList.add('mb-10', 'hidden');
        }
        divViewDip.append(diplom);
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
    }
}
