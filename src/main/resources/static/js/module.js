

let moduleAuthors = [];
let savedSeries = null;
var module = null;

// изменить "редактировать" на "сохранить" у submit button для модуля
document.addEventListener("keyup", function(event) {
  let editModule = document.getElementById('editModuleM');
  if (!editModule.classList.contains('hidden')){
  let inputValue = event.target.value; // Получаем значение поля ввода
  console.log(inputValue);
  // Проверяем, является ли целевой элемент полем ввода
  if (event.target.classList.contains("editable")) {
    // Меняем название кнопки в зависимости от значения поля ввода
    console.log('является');
    editTrack.textContent = "Сохранить"
    }
  }
});


// gantt.config.date_format = "%Y-%m-%d %H:%i";
// gantt.init("gantt_here");
// console.log(module);
// const data = [
//     {
//         id: 1,
//         text: module.seriesName,
//         start_date: null,
//         duration: null,
//         parent: 0,
//         progress: 0,
//         open: true
//     }
// ];
//
// for (let i = 0; i < lectures.length; i++) {
//     data.push({
//         id: i + 2, // увеличиваем id на 2, чтобы избежать конфликтов с уже имеющимися данными
//         text: lectures[i].lectureModuleName,
//         start_date: null,
//         duration: null,
//         parent: 1, // указываем id родительского элемента (в данном случае, 1)
//         progress: 0,
//         open: true
//     });
// }
// console.log(data);
// gantt.parse({
//     data
// });


// Listners for Lecture List that hide Lecture panel
const inputFieldsCourse = document.querySelectorAll('#profile-tab-pane input, #profile-tab-pane select, #profile-tab-pane textarea');
inputFieldsCourse.forEach(input => {
    input.addEventListener('input', event => {
        document.getElementById('contact-tab').classList.add('hidden');

    });
});

// Listners for Lecture panel that hide Lecture List panel
const inputFieldsLecture = document.querySelectorAll('#contact-tab-pane input, #contact-tab-pane select, #contact-tab-pane textarea');
inputFieldsLecture.forEach(input => {
    input.addEventListener('input', event => {
        document.getElementById('profile-tab').classList.add('hidden');

    });
});


async function sendForApprovalCourse(processType, el) {
    console.log("Серия: " + savedSeries);
    console.log("Лекция: " + savedLecture);
    try {
        const response = await fetch(`https://dev.track.samsmu.ru/${processType}?savedSeries=${savedSeries}&savedLecture=${savedLecture}`, {
        // const response = await fetch(`http://localhost:8081/${processType}?savedSeries=${savedSeries}`, {
            method: 'POST',
            body: JSON.stringify(savedSeries),
            headers: {
                'Content-Type': 'application/json',
                "Authorization": localStorage.authorization
            }
        });
        const data = await response.json();
        const newSeriesId = data.savedSeries;
        // Используйте полученный ID нового трека для нужных действий на фронтенде
        console.log('ID отправленного курса на согласование:', newSeriesId);
    } catch(error) {
        console.error('Ошибка при отправке запроса:', error);
    }
    console.log("До: " + lectures);
    lectures[lectures.length - 1].id = savedLecture;
    console.log("После: " + lectures);
    let button = document.getElementById("sendForExecution");
    button.disabled = true;                 // Делаем кнопку неактивной
    button.style.backgroundColor = "gray";

    let button2 = document.getElementById("editModuleM");
    button2.disabled = true;                 // Делаем кнопку неактивной
    button2.style.backgroundColor = "gray";

    el.classList.add('hidden');
    el.disabled = true;
}


function updateTrackHeadingModuleSeries() {
    let trackInput = document.getElementById("trackModuleSeries");
    let selectedOption = trackInput.options[trackInput.selectedIndex];
    let trackName = selectedOption.text;
    let moduleInput = document.getElementById("moduleSeriesName");
    let trackHeadingInLectureModuleSeries = document.getElementById("trackHeadingInLectureModuleSeries");
    let moduleHeadingInLectureModuleSeries = document.getElementById("moduleHeadingInLectureModuleSeries");
    let moduleHeading = document.getElementById("moduleHeading");

    // Обновление содержимого заголовка с использованием значения из textarea
    // trackHeadingInLectureModuleSeries.innerText = 'Образовательный трек «' + trackName + '» электронной образовательной среды СамГМУ»';
    moduleHeadingInLectureModuleSeries.innerText = 'Образовательный модуль «' + moduleInput.value + '» ';
    moduleHeading.innerText = 'Образовательный модуль «' + moduleInput.value + '» электронной образовательной среды СамГМУ»';
}

function addLectureSchemeModule() {

    let lectureModalName = document.getElementById('lectureModalNameModule').value;
    let lectureModalAnnotation = document.getElementById('lectureModalAnnotationModule').value;
    let lectureModalKeyWords = document.getElementById('lectureModalKeyWordsModule').value;
    // let lectureDaysToFill = document.getElementById('lectureDaysToFill').value;
    let authors = [];
    for (let author of moduleAuthors) {
        if (author.lecture === parseInt(lectureModalId)) {
            authors.push(author)
        }
    }
    let lecture = new Lecture(lectureModalId, null, lectureModalName, lectureModalAnnotation, lectureModalKeyWords, authors, null, "NOT_SENT");
    lectures.push(lecture);
    const seriesName = moduleForm.seriesName.value;
    const data = {
        lectures,
        seriesName
    }
    module = data;
    console.log(module);
    let newLecScheme = document.createElement('div');
    newLecScheme.setAttribute("onmouseover","HintShowbyTamara(this)");
    newLecScheme.setAttribute("onmouseout","HintHidebyTamara(this)");
    newLecScheme.classList.add('bg-success','mx-2', 'p-3', 'mb-1', 'rounded','lectureBlockScheme', 'lecBlockScheme', 'd-flex');
    newLectureSchemeNumber++; // Присваиваем id
    newLecScheme.id = "Lecture_" + newLectureSchemeNumber;
    const colLec = document.getElementById('colLecModule');
    colLec.append(newLecScheme);

    let lecName = document.createElement('button');
    lecName.classList.add('btn', 'btn-sm', 'rounded', 'text-white', 'text-center', 'm-0', 'p-0', 'text-truncate', 'lectureBlockSchemeText');
    lecName.setAttribute('type', 'button');
    lecName.setAttribute('onclick', 'editLecModuleSeries(this)');
    lecName.setAttribute('index', (lectures.length - 1).toString());
    lecName.setAttribute('lectureModuleName', lectureModalName);
    lecName.setAttribute('lectureModuleAnnotation', lectureModalAnnotation);
    lecName.setAttribute('lectureModuleKeyWords', lectureModalKeyWords);
    lecName.setAttribute('lectureModalId', lectureModalId);
    lecName.textContent = lectureModalName;
    newLecScheme.append(lecName);

    let sendForExec = document.createElement('button');
    sendForExec.classList.add('btn', 'btn-sm', 'rounded');
    sendForExec.setAttribute('type', 'button');
    sendForExec.setAttribute('onclick', 'sendForApprovalCourse("sendCourseForExecution", this)');
    let icon1 = document.createElement('i');
    icon1.classList.add('fas', 'fa-arrow-right', 'text-white');
    sendForExec.append(icon1);
    newLecScheme.append(sendForExec);

    //кнопка удаления лекции
    let button = document.createElement('button');
    button.classList.add('btn', 'btn-sm', 'rounded');
    button.setAttribute('type', 'button')
    button.setAttribute('onclick', 'removeLecInModule(this)');
    button.setAttribute('lectureModalId', lectureModalId);
    button.setAttribute('index', (lectures.length - 1).toString());
    newLecScheme.append(button);
    lectureModalId = lectureModalId + 1;
    let icon = document.createElement('i');
    icon.classList.add('fas', 'fa-times', 'text-white');
    button.append(icon);
    document.getElementById('lectureModalNameModule').value = "";
    document.getElementById('lectureModalAnnotationModule').value = "";
    document.getElementById('lectureModalKeyWordsModule').value = "";
    // document.getElementById('lectureDaysToFill').value = "";
    document.getElementById('tableAuthorModal').innerHTML = '';
    document.getElementById("gantt_here").classList.remove("hidden");
    saveSeriesOnServer();
    btnClose();
}

function editLecModuleSeries(el){
    addLectureInModuleSeries(el);
    let saveButton = document.getElementById('saveLectureInModuleSeries');
    saveButton.classList.add('hidden');
    let editButton = document.getElementById('editLectureInModuleSeries');
    editButton.classList.remove('hidden');
    //выводим в диалог сохраненную лекцию
    let lectureModuleNameElement = document.getElementById('lectureModalNameModule');
    lectureModuleNameElement.value = el.getAttribute('lectureModuleName');
    let lectureModuleAnnotationElement = document.getElementById('lectureModalAnnotationModule');
    lectureModuleAnnotationElement.value = el.getAttribute('lectureModuleAnnotation');
    let lectureModuleKeyWordsElement = document.getElementById('lectureModalKeyWordsModule');
    lectureModuleKeyWordsElement.value = el.getAttribute('lectureModuleKeyWords');
    // document.getElementById('lectureDaysToFill').value = el.getAttribute('daysToFill');

    editElForLecOut = el;

    // Вставляем авторов в таблицу, у которых параметр lecture равен 2
    let table = document.getElementById('tableAuthorModal');
    for (let author of moduleAuthors) {
        if (author.lecture === parseInt(el.getAttribute('lecturemodalid'))) {
            selectAuthorModule(author, null, 'tableAuthorModal', false, false);
        }
    }

}

function editLecInModuleSeries(el){
    let index = el.getAttribute('index');
    let lectureModalId = el.getAttribute('lectureModalId');
    let lectureModuleName = document.getElementById('lectureModalNameModule').value;
    let lectureModuleAnnotation = document.getElementById('lectureModalAnnotationModule').value;
    let lectureModuleKeyWords = document.getElementById('lectureModalKeyWordsModule').value;
    // let lectureDaysToFill = document.getElementById('lectureDaysToFill').value;
    let authors = [];
    for (let author of moduleAuthors) {
        if (author.lecture === parseInt(lectureModalId)) {
            authors.push(author)
        }
    }
    let lecture = new Lecture(lectureModalId, null, lectureModuleName, lectureModuleAnnotation, lectureModuleKeyWords, authors, null, "NOT_SENT");
    lectures [index] = lecture;
    // let div = el.parentNode;
    el.setAttribute('lectureModuleName', lectureModuleName);
    el.setAttribute('lectureModuleAnnotation', lectureModuleAnnotation);
    el.setAttribute('lectureModuleKeyWords', lectureModuleKeyWords);
    // el.setAttribute('lectureDaysToFill', lectureDaysToFill);
    el.textContent = lectureModuleName;
    document.getElementById('lectureModalNameModule').value = "";
    document.getElementById('lectureModalAnnotationModule').value = "";
    document.getElementById('lectureModalKeyWordsModule').value = "";
    // document.getElementById('lectureDaysToFill').value = "";
    let saveButton = document.getElementById('saveLectureInModuleSeries');
    saveButton.classList.remove('hidden');
    let editButton = document.getElementById('editLectureInModuleSeries');
    editButton.classList.add('hidden');
    btnClose();
}



const moduleForm = document.querySelector('#createSeries');
moduleForm.addEventListener('submit', (event) => {
    event.preventDefault(); // остановить стандартное поведение формы
    savedSeries();
});

function  saveSeriesOnServer(){
    const track = moduleForm.seriesTrack.value;
    const seriesName = moduleForm.seriesName.value;
    const seriesAnnotation = moduleForm.seriesAnnotation.value;
    const seriesKeyWords = moduleForm.seriesKeyWords.value;

    const data = {
        lectures,
        moduleAuthors,
        mkbs,
        track,
        seriesName,
        seriesAnnotation,
        seriesKeyWords,
    };
    module = data;
    if (savedSeries) {
        data.seriesId = savedSeries;
    }
    console.log(data);
    console.log(module);

    fetch("https://dev.track.samsmu.ru/addSeries", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.authorization
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            savedSeries = data.id;
            savedLecture = data.lectureId;
            // Используйте полученный ID нового трека для нужных действий на фронтенде
            console.log('ID нового модуля:', savedSeries);
            console.log('ID новой лекции:', savedLecture);
        })
        .catch(error => {
            console.error('Ошибка при отправке запроса:', error);
        });
    console.log("До: " + lectures);
    lectures[lectures.length - 1].id = savedLecture;
    console.log("После: " + lectures);
    let addModule = document.getElementById('saveModuleM');
    addModule.classList.add("hidden");
    let editModule = document.getElementById('editModuleM');
    editModule.classList.remove("hidden");
    // let sendForApproval = document.getElementById('sendForApproval');
    // sendForApproval.classList.remove("hidden");
    document.getElementById('sendForExecution').classList.remove("hidden");
}

// function handleSubmit(event) {
//     // Отменяем стандартное поведение формы
//     event.preventDefault();
//
//     // Очищаем поля формы
//     document.getElementById("createTrack").reset();
//
//     // Другие действия по обработке отправки формы...
// }

function selectAuthorModule(el, selectId, tableId, supervisor, addToAuthorsList) {
    let e = document.getElementById(selectId);

    let selectData = $('#' + selectId).select2('data');
    let selectedIndex = $('#' + selectId).prop('selectedIndex');
    console.log(selectData, "selectData");
    console.log(selectedIndex, "индекс selectData");
    // var academicDegreeName = selectData[selectedIndex].academicDegreeName; // дает nextInt вместо того, в котором лежит data


    let value;
    let text;

    if (Author.prototype.isPrototypeOf(el)){
         value = el.id;
         text = el.fullName;
    } else {
         value = e.value;
         text = e.options[e.selectedIndex].text;
    }

    let academicDegreeName = null;
    let passportDB = null;
    let diplomaDB = null;
    let diplomaScienceRankDB = null;
    let diplomaScienceDegreeDB = null;
    let noCriminalRecordDB = null;
    let healthStatusDB = null;
    let employmentBookDB = null;

    if (selectId != null) {
         academicDegreeName = selectData[0].academicDegreeName;
         passportDB = selectData[0].passportDB;
         diplomaDB = selectData[0].diplomaDB;
         diplomaScienceRankDB = selectData[0].diplomaScienceRankDB;
         diplomaScienceDegreeDB = selectData[0].diplomaScienceDegreeDB;
         noCriminalRecordDB = selectData[0].noCriminalRecordDB;
         healthStatusDB = selectData[0].healthStatusDB;
         employmentBookDB = selectData[0].employmentBookDB;
         console.log(academicDegreeName, "уч.степень");
    }
    // let id = e.options[e.selectedIndex].id;
    // let index = e.options[e.selectedIndex];
    // console.log(index, "индекс HTML");
    // let select = document.getElementById("select");
    // let value = select.value;
    let tableAuthor = document.getElementById(tableId);

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

    console.log(text);
    // console.log(id);
    console.log(value);

    let author = new Author(value, text, academicDegreeName, supervisor.toString(), supervisor ? null : lectureModalId);

    // console.log(e.value.replace(/\s/g, ""));
    if (addToAuthorsList) {
        moduleAuthors.push(author);
    }
    console.log(moduleAuthors);
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
    radioBtn.setAttribute('authorIndex', (moduleAuthors.length - 1).toString());
    radioBtn.classList.add('form-check-input');
    if (supervisor){
        radioBtn.checked = true;
    } else {
        radioBtn.checked = false;
    }

    checkTd.append(radioBtn);

    let docTd = document.createElement('td');
    docTd.classList.add('text-center', 'docTd');
    docTd.setAttribute('onclick','showDoc(this)');
    nameTr.append(docTd);
    let docTdIcon = document.createElement('i');
    docTdIcon.classList.add('fas', 'fa-caret-down', 'text-primary');

    docTd.append(docTdIcon);

    let removeTd = document.createElement('td');
    removeTd.classList.add('removeTd', 'text-center');
    nameTr.append(removeTd);
    let close = document.createElement('i');
    close.classList.add('fas', 'fa-times', 'text-danger');
    close.setAttribute('id', 'removeAuthorFromTable');
    close.setAttribute('onclick','removeAuthorFromArray(this)');
    close.setAttribute('authorIndex', (moduleAuthors.length - 1).toString());
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
            fetch(`https://dev.track.samsmu.ru/pdf/${passportDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf',
                    "Authorization": localStorage.authorization
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
            fetch(`https://dev.track.samsmu.ru/pdf/${diplomaDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf',
                    "Authorization": localStorage.authorization
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

    let divViewDocRank = document.createElement('div');
    tdDoc.append(divViewDocRank);

    let rank = document.createElement('div');
    rank.classList.add('mb-10');

    divViewDocRank.append(rank);
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
    rankInput.setAttribute('id', 'file-input');
    // passportInput.setAttribute('multiple placeholder', '1');
    // let modalShowDocsBody = document.getElementById('modalShowDocsBody');

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
            author.diplomaScienceRank = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocRank.append(rankInput);

    let btnUploadRank = document.createElement('button');
    btnUploadRank.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadRank.setAttribute('type', 'button');
    btnUploadRank.setAttribute('id', author.id);
    btnUploadRank.setAttribute('fullName', author.fullName);
    btnUploadRank.setAttribute('passport', author.diplomaScienceRank);
    // btnUpload.setAttribute('onclick', 'sendAuthorDocs(this)');
    btnUploadRank.addEventListener('click', function() {
        sendAuthorDocs(author);
        sendAuthorDocsUploadIcon(this);
    });
    trDocRank.append(btnUploadRank);

    let uploadIconRank = document.createElement('i');
    uploadIconRank.classList.add('fas', 'fa-file-upload');
    /* uploadIcon.setAttribute('onclick', 'sendAuthorDocsUploadIcon(this)');*/
    btnUploadRank.append(uploadIconRank);

    // Просмотр загруженного документа
    let btnViewDocRank = document.createElement('button');
    btnViewDocRank.classList.add('btn', 'ml-10', 'btn-outline-primary', 'hidden');
    btnViewDocRank.setAttribute('type', 'button');
    btnViewDocRank.setAttribute('onclick', 'buttonShowPas()');
    console.log("создали кнопку просмотра");
    trDocRank.append(btnViewDocRank);

    let viewIconRank = document.createElement('i');
    viewIconRank.classList.add('fas', 'fa-eye');
    btnViewDocRank.append(viewIconRank);

    // Кнопка удаления загруженного документа
    let btnDeleteDocRank = document.createElement('button');
    btnDeleteDocRank.classList.add('btn', 'ml-10', 'hidden');
    btnDeleteDocRank.setAttribute('type', 'button');
    btnDeleteDocRank.setAttribute('onclick', 'deleteDoc(this)');
    btnDeleteDocRank.setAttribute('document', 'diplomaScienceRankDB');
    btnDeleteDocRank.setAttribute('author', author.id.toString());
    if (diplomaScienceRankDB != null){
        btnDeleteDocRank.setAttribute('filename', diplomaScienceRankDB.toString());
    }


    //trDocPassport.append(btnDeleteDoc);
    console.log("создали кнопку удаления диплома");
    trDocRank.insertAdjacentElement('beforeEnd', btnDeleteDocRank);
    let deleteIconRank = document.createElement('i');
    deleteIconRank.classList.add('fas', 'fa-times', 'text-danger');
    btnDeleteDocRank.append(deleteIconRank);


    if (diplomaScienceRankDB != null){

        btnViewDocRank.classList.remove('hidden');

        btnViewDocRank.addEventListener('click', function() {
            fetch(`https://dev.track.samsmu.ru/pdf/${diplomaScienceRankDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf',
                    "Authorization": localStorage.authorization
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    btnViewDocRank.classList.remove('hidden');
                    const url = URL.createObjectURL(blob);
                    // let elemInput = document.getElementById('xyz');
                    // elemInput.classList.add('hidden');
                    let elem = document.getElementById('passportFrame');
                    if (elem === null) {
                        modalShowDocsBody.insertAdjacentHTML('afterend', `<iframe id="passportFrame" src="${url}" frameborder="0" height="500px" width="100%" />`);
                    } else {
                        elem.src = `${url}`;
                    }

                    rank.classList.remove('hidden');
                    // closePasFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnDeleteDocRank.classList.remove('hidden');
    }
    //

    let divViewDegree = document.createElement('div');
    tdDoc.append(divViewDegree);

    let degree = document.createElement('div');
    degree.classList.add('mb-10');

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
    degreeInput.setAttribute('id', 'file-input');
    // passportInput.setAttribute('multiple placeholder', '1');
    // let modalShowDocsBody = document.getElementById('modalShowDocsBody');

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
            author.diplomaScienceDegree = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocDegree.append(degreeInput);

    let btnUploadDegree = document.createElement('button');
    btnUploadDegree.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadDegree.setAttribute('type', 'button');
    btnUploadDegree.setAttribute('id', author.id);
    btnUploadDegree.setAttribute('fullName', author.fullName);
    btnUploadDegree.setAttribute('passport', author.diplomaScienceDegree);
    // btnUpload.setAttribute('onclick', 'sendAuthorDocs(this)');
    btnUploadDegree.addEventListener('click', function() {
        sendAuthorDocs(author);
        sendAuthorDocsUploadIcon(this);
    });
    trDocDegree.append(btnUploadDegree);

    let uploadIconDegree = document.createElement('i');
    uploadIconDegree.classList.add('fas', 'fa-file-upload');
    /* uploadIcon.setAttribute('onclick', 'sendAuthorDocsUploadIcon(this)');*/
    btnUploadDegree.append(uploadIconDegree);

    // Просмотр загруженного документа
    let btnViewDocDegree = document.createElement('button');
    btnViewDocDegree.classList.add('btn', 'ml-10', 'btn-outline-primary', 'hidden');
    btnViewDocDegree.setAttribute('type', 'button');
    btnViewDocDegree.setAttribute('onclick', 'buttonShowPas()');
    console.log("создали кнопку просмотра");
    trDocDegree.append(btnViewDocDegree);

    let viewIconDegree = document.createElement('i');
    viewIconDegree.classList.add('fas', 'fa-eye');
    btnViewDocDegree.append(viewIconDegree);

    // Кнопка удаления загруженного документа
    let btnDeleteDocDegree = document.createElement('button');
    btnDeleteDocDegree.classList.add('btn', 'ml-10', 'hidden');
    btnDeleteDocDegree.setAttribute('type', 'button');
    btnDeleteDocDegree.setAttribute('onclick', 'deleteDoc(this)');
    btnDeleteDocDegree.setAttribute('document', 'diplomaScienceDegreeDB');
    btnDeleteDocDegree.setAttribute('author', author.id.toString());
    if (diplomaScienceDegreeDB != null){
        btnDeleteDocDegree.setAttribute('filename', diplomaScienceDegreeDB.toString());
    }


    //trDocPassport.append(btnDeleteDoc);
    console.log("создали кнопку удаления диплома");
    trDocDegree.insertAdjacentElement('beforeEnd', btnDeleteDocDegree);
    let deleteIconDegree = document.createElement('i');
    deleteIconDegree.classList.add('fas', 'fa-times', 'text-danger');
    btnDeleteDocDegree.append(deleteIconDegree);



    if (diplomaScienceDegreeDB != null){

        btnViewDocDegree.classList.remove('hidden');

        btnViewDocDegree.addEventListener('click', function() {
            fetch(`https://dev.track.samsmu.ru/pdf/${diplomaScienceDegreeDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf',
                    "Authorization": localStorage.authorization
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    btnViewDocDegree.classList.remove('hidden');
                    const url = URL.createObjectURL(blob);
                    // let elemInput = document.getElementById('xyz');
                    // elemInput.classList.add('hidden');
                    let elem = document.getElementById('passportFrame');
                    if (elem === null) {
                        modalShowDocsBody.insertAdjacentHTML('afterend', `<iframe id="passportFrame" src="${url}" frameborder="0" height="500px" width="100%" />`);
                    } else {
                        elem.src = `${url}`;
                    }

                    degree.classList.remove('hidden');
                    // closePasFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnDeleteDocDegree.classList.remove('hidden');
    }
    //

    let divViewCriminal = document.createElement('div');
    tdDoc.append(divViewCriminal);

    let criminal = document.createElement('div');
    criminal.classList.add('mb-10');

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
    criminalInput.setAttribute('id', 'file-input');
    // passportInput.setAttribute('multiple placeholder', '1');
    // let modalShowDocsBody = document.getElementById('modalShowDocsBody');

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
            author.noCriminalRecord = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocCriminal.append(criminalInput);

    let btnUploadCriminal = document.createElement('button');
    btnUploadCriminal.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadCriminal.setAttribute('type', 'button');
    btnUploadCriminal.setAttribute('id', author.id);
    btnUploadCriminal.setAttribute('fullName', author.fullName);
    btnUploadCriminal.setAttribute('passport', author.diploma);
    // btnUpload.setAttribute('onclick', 'sendAuthorDocs(this)');
    btnUploadCriminal.addEventListener('click', function() {
        sendAuthorDocs(author);
        sendAuthorDocsUploadIcon(this);
    });
    trDocCriminal.append(btnUploadCriminal);

    let uploadIconCriminal = document.createElement('i');
    uploadIconCriminal.classList.add('fas', 'fa-file-upload');
    /* uploadIcon.setAttribute('onclick', 'sendAuthorDocsUploadIcon(this)');*/
    btnUploadCriminal.append(uploadIconCriminal);

    // Просмотр загруженного документа
    let btnViewDocCriminal = document.createElement('button');
    btnViewDocCriminal.classList.add('btn', 'ml-10', 'btn-outline-primary', 'hidden');
    btnViewDocCriminal.setAttribute('type', 'button');
    btnViewDocCriminal.setAttribute('onclick', 'buttonShowPas()');
    console.log("создали кнопку просмотра");
    trDocCriminal.append(btnViewDocCriminal);

    let viewIconCriminal = document.createElement('i');
    viewIconCriminal.classList.add('fas', 'fa-eye');
    btnViewDocCriminal.append(viewIconCriminal);

    // Кнопка удаления загруженного документа
    let btnDeleteDocCriminal = document.createElement('button');
    btnDeleteDocCriminal.classList.add('btn', 'ml-10', 'hidden');
    btnDeleteDocCriminal.setAttribute('type', 'button');
    btnDeleteDocCriminal.setAttribute('onclick', 'deleteDoc(this)');
    btnDeleteDocCriminal.setAttribute('document', 'noCriminalRecordDB');
    btnDeleteDocCriminal.setAttribute('author', author.id.toString());
    if (noCriminalRecordDB != null){
        btnDeleteDocCriminal.setAttribute('filename', noCriminalRecordDB.toString());
    }


    //trDocPassport.append(btnDeleteDoc);
    console.log("создали кнопку удаления диплома");
    trDocCriminal.insertAdjacentElement('beforeEnd', btnDeleteDocCriminal);
    let deleteIconCriminal = document.createElement('i');
    deleteIconCriminal.classList.add('fas', 'fa-times', 'text-danger');
    btnDeleteDocCriminal.append(deleteIconCriminal);

    if (noCriminalRecordDB != null){

        btnViewDocCriminal.classList.remove('hidden');

        btnViewDocCriminal.addEventListener('click', function() {
            fetch(`https://dev.track.samsmu.ru/pdf/${noCriminalRecordDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf',
                    "Authorization": localStorage.authorization
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    btnViewDocCriminal.classList.remove('hidden');
                    const url = URL.createObjectURL(blob);
                    // let elemInput = document.getElementById('xyz');
                    // elemInput.classList.add('hidden');
                    let elem = document.getElementById('passportFrame');
                    if (elem === null) {
                        modalShowDocsBody.insertAdjacentHTML('afterend', `<iframe id="passportFrame" src="${url}" frameborder="0" height="500px" width="100%" />`);
                    } else {
                        elem.src = `${url}`;
                    }

                    criminal.classList.remove('hidden');
                    // closePasFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnDeleteDocCriminal.classList.remove('hidden');
    }
    //

    let divViewHealth = document.createElement('div');
    tdDoc.append(divViewHealth);

    let health = document.createElement('div');
    health.classList.add('mb-10');

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
    healthInput.setAttribute('id', 'file-input');
    // passportInput.setAttribute('multiple placeholder', '1');
    // let modalShowDocsBody = document.getElementById('modalShowDocsBody');

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
            author.healthStatus = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocHealth.append(healthInput);

    let btnUploadHealth = document.createElement('button');
    btnUploadHealth.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadHealth.setAttribute('type', 'button');
    btnUploadHealth.setAttribute('id', author.id);
    btnUploadHealth.setAttribute('fullName', author.fullName);
    btnUploadHealth.setAttribute('passport', author.healthStatus);
    // btnUpload.setAttribute('onclick', 'sendAuthorDocs(this)');
    btnUploadHealth.addEventListener('click', function() {
        sendAuthorDocs(author);
        sendAuthorDocsUploadIcon(this);
    });
    trDocHealth.append(btnUploadHealth);

    let uploadIconHealth = document.createElement('i');
    uploadIconHealth.classList.add('fas', 'fa-file-upload');
    /* uploadIcon.setAttribute('onclick', 'sendAuthorDocsUploadIcon(this)');*/
    btnUploadHealth.append(uploadIconHealth);

    // Просмотр загруженного документа
    let btnViewDocCHealth = document.createElement('button');
    btnViewDocCHealth.classList.add('btn', 'ml-10', 'btn-outline-primary', 'hidden');
    btnViewDocCHealth.setAttribute('type', 'button');
    btnViewDocCHealth.setAttribute('onclick', 'buttonShowPas()');
    console.log("создали кнопку просмотра");
    trDocHealth.append(btnViewDocCHealth);

    let viewIconHealth = document.createElement('i');
    viewIconHealth.classList.add('fas', 'fa-eye');
    btnViewDocCHealth.append(viewIconHealth);

    // Кнопка удаления загруженного документа
    let btnDeleteDocHealth = document.createElement('button');
    btnDeleteDocHealth.classList.add('btn', 'ml-10', 'hidden');
    btnDeleteDocHealth.setAttribute('type', 'button');
    btnDeleteDocHealth.setAttribute('onclick', 'deleteDoc(this)');
    btnDeleteDocHealth.setAttribute('document', 'healthStatusDB');
    btnDeleteDocHealth.setAttribute('author', author.id.toString());
    if (healthStatusDB != null){
        btnDeleteDocHealth.setAttribute('filename', healthStatusDB.toString());
    }


    //trDocPassport.append(btnDeleteDoc);
    console.log("создали кнопку удаления статуса здоровья");
    trDocHealth.insertAdjacentElement('beforeEnd', btnDeleteDocHealth);
    let deleteIconHealth = document.createElement('i');
    deleteIconHealth.classList.add('fas', 'fa-times', 'text-danger');
    btnDeleteDocHealth.append(deleteIconHealth);


    if (healthStatusDB != null){

        btnViewDocCHealth.classList.remove('hidden');

        btnViewDocCHealth.addEventListener('click', function() {
            fetch(`https://dev.track.samsmu.ru/pdf/${healthStatusDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf',
                    "Authorization": localStorage.authorization
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    btnViewDocCHealth.classList.remove('hidden');
                    const url = URL.createObjectURL(blob);
                    // let elemInput = document.getElementById('xyz');
                    // elemInput.classList.add('hidden');
                    let elem = document.getElementById('passportFrame');
                    if (elem === null) {
                        modalShowDocsBody.insertAdjacentHTML('afterend', `<iframe id="passportFrame" src="${url}" frameborder="0" height="500px" width="100%" />`);
                    } else {
                        elem.src = `${url}`;
                    }

                    health.classList.remove('hidden');
                    // closePasFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnDeleteDocHealth.classList.remove('hidden');
    }
    //

    let divViewEmp = document.createElement('div');
    tdDoc.append(divViewEmp);

    let experience = document.createElement('div');
    experience.classList.add('mb-10');

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
    experienceInput.setAttribute('id', 'file-input');
    // passportInput.setAttribute('multiple placeholder', '1');
    // let modalShowDocsBody = document.getElementById('modalShowDocsBody');

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
            author.employmentBook = file; // Присваиваем файл свойству passport объекта author
        }
    });
    trDocExperience.append(experienceInput);

    let btnUploadExperience = document.createElement('button');
    btnUploadExperience.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadExperience.setAttribute('type', 'button');
    btnUploadExperience.setAttribute('id', author.id);
    btnUploadExperience.setAttribute('fullName', author.fullName);
    btnUploadExperience.setAttribute('passport', author.employmentBook);
    // btnUpload.setAttribute('onclick', 'sendAuthorDocs(this)');
    btnUploadExperience.addEventListener('click', function() {
        sendAuthorDocs(author);
        sendAuthorDocsUploadIcon(this);
    });
    trDocExperience.append(btnUploadExperience);

    let uploadIconExperience = document.createElement('i');
    uploadIconExperience.classList.add('fas', 'fa-file-upload');
    /* uploadIcon.setAttribute('onclick', 'sendAuthorDocsUploadIcon(this)');*/
    btnUploadExperience.append(uploadIconExperience);

    // Просмотр загруженного документа
    let btnViewDocExperience = document.createElement('button');
    btnViewDocExperience.classList.add('btn', 'ml-10', 'btn-outline-primary', 'hidden');
    btnViewDocExperience.setAttribute('type', 'button');
    btnViewDocExperience.setAttribute('onclick', 'buttonShowPas()');
    console.log("создали кнопку просмотра");
    trDocExperience.append(btnViewDocExperience);

    let viewIconExperience = document.createElement('i');
    viewIconExperience.classList.add('fas', 'fa-eye');
    btnViewDocExperience.append(viewIconExperience);

    // Кнопка удаления загруженного документа
    let btnDeleteDocExperience = document.createElement('button');
    btnDeleteDocExperience.classList.add('btn', 'ml-10', 'hidden');
    btnDeleteDocExperience.setAttribute('type', 'button');
    btnDeleteDocExperience.setAttribute('onclick', 'deleteDoc(this)');
    btnDeleteDocExperience.setAttribute('document', 'employmentBookDB');
    btnDeleteDocExperience.setAttribute('author', author.id.toString());
    if (employmentBookDB != null){
        btnDeleteDocExperience.setAttribute('filename', employmentBookDB.toString());
    }


    //trDocPassport.append(btnDeleteDoc);
    console.log("создали кнопку удаления статуса здоровья");
    trDocExperience.insertAdjacentElement('beforeEnd', btnDeleteDocExperience);
    let deleteIconExperience = document.createElement('i');
    deleteIconExperience.classList.add('fas', 'fa-times', 'text-danger');
    btnDeleteDocExperience.append(deleteIconExperience);


    if (employmentBookDB != null){

        btnViewDocExperience.classList.remove('hidden');

        btnViewDocExperience.addEventListener('click', function() {
            fetch(`https://dev.track.samsmu.ru/pdf/${employmentBookDB}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/pdf',
                    "Authorization": localStorage.authorization
                }
            })
                .then(response => response.blob())
                .then(blob => {
                    btnViewDocExperience.classList.remove('hidden');
                    const url = URL.createObjectURL(blob);
                    // let elemInput = document.getElementById('xyz');
                    // elemInput.classList.add('hidden');
                    let elem = document.getElementById('passportFrame');
                    if (elem === null) {
                        modalShowDocsBody.insertAdjacentHTML('afterend', `<iframe id="passportFrame" src="${url}" frameborder="0" height="500px" width="100%" />`);
                    } else {
                        elem.src = `${url}`;
                    }

                    experience.classList.remove('hidden');
                    // closePasFrame.classList.remove('hidden');
                })
                .catch(error => console.error('Ошибка:', error));
        });
        btnDeleteDocExperience.classList.remove('hidden');
    }
    //

    document.getElementById('regalia').options[0].selected = true; //очищаем селект
    let badge = document.querySelectorAll('.badgeProf');
    for( let i = 0; i < badge.length; i++ ){
        badge[i].outerHTML = "";
    }

}

function deleteModule(id){
    const url = '/module/' + id; // здесь id - идентификатор трека

    fetch(url, {
        method: 'DELETE',
    })
        .then(response => {
            // обработка успешного ответа
        })
        .catch(error => {
            // обработка ошибки
        });
}
