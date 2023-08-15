
//чтобы текст в поле наименование трека переносился в шапку дерева
function updateTrackHeading() {
    var trackInput = document.getElementById("trackInput");
    var trackHeading = document.getElementById("trackHeading");
    var trackHeadingInModule = document.getElementById("trackHeadingInModule");
    let trackHeadingInLecture = document.getElementById("trackHeadingInLecture");
    let trackHeadingInLectureOutOfModule = document.getElementById("trackHeadingInLectureOutOfModule");
    let trackHeadingOfModule = document.getElementById("trackModuleSeries");
    let trackHeadingOfModuleInLectureModal = document.getElementById("trackModuleSeries");



    // Обновление содержимого заголовка с использованием значения из textarea
    trackHeading.innerText = 'Образовательный трек «' + trackInput.value + '» электронной образовательной среды СамГМУ»';
    trackHeadingInModule.innerText = 'Образовательный трек «' + trackInput.value + '» электронной образовательной среды СамГМУ»';
    trackHeadingInLecture.innerText = 'Образовательный трек «' + trackInput.value + '» электронной образовательной среды СамГМУ»';
    trackHeadingInLectureOutOfModule.innerText = 'Образовательный трек «' + trackInput.value + '» электронной образовательной среды СамГМУ»';
    trackHeadingOfModuleInLectureModal.innerText = 'Образовательный модуль «' + trackHeadingOfModule.value + '» электронной образовательной среды СамГМУ»';
}
function updateModuleHeading() {
    var trackInput = document.getElementById("trackInput");
    var trackHeading = document.getElementById("trackHeading");
    var trackHeadingInModule = document.getElementById("trackHeadingInModule");

    // Обновление содержимого заголовка с использованием значения из textarea
    trackHeading.innerText = 'Образовательный трек «' + trackInput.value + '» электронной образовательной среды СамГМУ»';
    trackHeadingInModule.innerText = 'Образовательный трек «' + trackInput.value + '» электронной образовательной среды СамГМУ»';
}

function authorSamGMU() {
    let authorSamGMU = document.getElementById('authorSamGMU');
    authorSamGMU.classList.toggle("hidden");
    let authorWorld = document.getElementById('authorWorld');
    authorWorld.classList.toggle("hidden");
}
function subshooting() {
    let subsurvey = document.getElementById('subsurvey');
    subsurvey.classList.toggle("hidden");
}

var newModuleSchemeNumber = 0;
class Module {
    constructor(id, moduleNameModal, moduleModalAnnotation, moduleModalKeyWords) {
        this.id = id;
        this.moduleNameModal = moduleNameModal;
        this.moduleModalAnnotation = moduleModalAnnotation;
        this.moduleModalKeyWords = moduleModalKeyWords;
    }
}
class Lecture {
    constructor(id, moduleId, lectureModuleName, lectureModuleAnnotation, lectureModuleKeyWords) {
        this.id = id;
        this.moduleId = moduleId;
        this.lectureModuleName = lectureModuleName;
        this.lectureModuleAnnotation = lectureModuleAnnotation;
        this.lectureModuleKeyWords = lectureModuleKeyWords;
    }
}
var modules = [];
var lectures = [];
const dataModal = {

};
dataModal.modules = modules;
dataModal.lectures = lectures;
let moduleModalId = 1;
let lectureModalId = 1;
// Добавление модуля
function addModuleScheme() {

    // начиняем модули в датасет
    let moduleNameModal = document.getElementById('headSeries').value;
    let moduleModalAnnotation = document.getElementById('moduleModalAnnotation').value;
    let moduleModalKeyWords = document.getElementById('moduleModalKeyWords').value;


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
    modName.setAttribute('index', (modules.length).toString());
    modName.setAttribute('moduleModalId', moduleModalId);
    modName.setAttribute('moduleNameModal', moduleNameModal);
    modName.setAttribute('moduleModalAnnotation', moduleModalAnnotation);
    modName.setAttribute('moduleModalKeyWords', moduleModalKeyWords);
    modName.textContent = moduleNameModal;
    col11.append(modName);

    let col1 = document.createElement('div');
    col1.classList.add('col-2');
    bgPrimary.append(col1);

    let button = document.createElement('button');
    button.classList.add('btn', 'btn-sm', 'rounded');
    button.setAttribute('type', 'button')
    button.setAttribute('onclick', 'addLectureInModuleBegin(this)');
    button.setAttribute('moduleNameModal', moduleNameModal);
    button.setAttribute('moduleModalId', moduleModalId);
    button.setAttribute('id', 'moduleModalId_' + moduleModalId);
    col1.append(button);

    let module = new Module(moduleModalId, moduleNameModal, moduleModalAnnotation, moduleModalKeyWords);
    modules.push(module);
    moduleModalId = moduleModalId + 1;

    let icon = document.createElement('i');
    icon.classList.add('fas', 'fa-plus', 'text-white');
    button.append(icon);

    let rowLecInModule = document.createElement('div');
    rowLecInModule.classList.add('d-flex', 'justify-content-between', 'my-2', 'rowLecInModule');
    rowLecInModule.textContent = '';
    newModuleScheme.append(rowLecInModule);
}

let editElForMod;
function editMod(el){
    addSeries(el);
    let saveButton = document.getElementById('saveModule');
    saveButton.classList.add('hidden');
    let editButton = document.getElementById('editModule');
    editButton.classList.remove('hidden');

    let lectureModuleNameElement = document.getElementById('headSeries');
    lectureModuleNameElement.value = el.getAttribute('moduleNameModal');
    let lectureModuleAnnotationElement = document.getElementById('moduleModalAnnotation');
    lectureModuleAnnotationElement.value = el.getAttribute('moduleModalAnnotation');
    let lectureModuleKeyWordsElement = document.getElementById('moduleModalKeyWords');
    lectureModuleKeyWordsElement.value = el.getAttribute('moduleModalKeyWords');
    editElForMod = el;
}

function saveEditModule(el){
    let moduleModalId = el.getAttribute('moduleModalId');
    let index = el.getAttribute('index');

    let moduleNameModal = document.getElementById('headSeries').value;
    let moduleModalAnnotation = document.getElementById('moduleModalAnnotation').value;
    let moduleModalKeyWords = document.getElementById('moduleModalKeyWords').value;
    let module = new Module(moduleModalId, moduleNameModal, moduleModalAnnotation, moduleModalKeyWords);
    modules [index] = module;
    el.setAttribute('moduleNameModal', moduleNameModal);
    el.setAttribute('moduleModalAnnotation', moduleModalAnnotation);
    el.setAttribute('moduleModalKeyWords', moduleModalKeyWords);
    el.textContent = moduleNameModal;
    document.getElementById('headSeries').value = "";
    document.getElementById('moduleModalAnnotation').value = "";
    document.getElementById('moduleModalKeyWords').value = "";
    let saveButton = document.getElementById('saveModule');
    saveButton.classList.remove('hidden');
    let editButton = document.getElementById('editModule');
    editButton.classList.add('hidden');
    btnClose();
}



//

// Добавление лекции в модуль
function addLecInModule(el) {

    // начиняем лекции в датасет
    var divElement = document.getElementById("addLectureInModule");
    var moduleModalId = divElement.getAttribute("moduleModalId");
    var moduleNameModal = divElement.getAttribute("moduleNameModal");


// Выводим значение в консоль для проверки
    console.log(moduleNameModal);
    let lectureModuleName = document.getElementById('lectureModuleName').value;
    let lectureModuleAnnotation = document.getElementById('lectureModuleAnnotation').value;
    let lectureModuleKeyWords = document.getElementById('lectureModuleKeyWords').value;
    let lecture = new Lecture(lectureModalId, moduleModalId, lectureModuleName, lectureModuleAnnotation, lectureModuleKeyWords);
    lectures.push(lecture);
    lectureModalId = lectureModalId + 1;

    let newLecInScheme = document.createElement('div');
    newLecInScheme.setAttribute("onmouseover","HintShowbyTamara(this)");
    newLecInScheme.setAttribute("onmouseout","HintHidebyTamara(this)");
    newLecInScheme.classList.add('bg-success','m-3', 'p-3', 'rounded','lectureBlockScheme', 'd-flex');
    el.parentElement.parentElement.nextElementSibling.append(newLecInScheme);
    let lecName = document.createElement('button');
    lecName.classList.add('btn', 'btn-sm', 'rounded', 'text-white', 'text-center', 'm-0', 'p-0', 'text-truncate', 'lectureBlockSchemeText');
    lecName.setAttribute('type', 'button');
    lecName.setAttribute('onclick', 'editLec(this)');
    lecName.setAttribute('index', (lectures.length - 1).toString());
    lecName.setAttribute('lectureModuleName', lectureModuleName);
    lecName.setAttribute('lectureModuleAnnotation', lectureModuleAnnotation);
    lecName.setAttribute('lectureModuleKeyWords', lectureModuleKeyWords);
    lecName.setAttribute('lectureModalId', (lectureModalId -1).toString());
    lecName.setAttribute('moduleModalId', moduleModalId);
    lecName.setAttribute('moduleNameModal', moduleNameModal);
    lecName.textContent = lectureModuleName;
    newLecInScheme.append(lecName);

    //кнопка удаления лекции
    let button = document.createElement('button');
    button.classList.add('btn', 'btn-sm', 'rounded');
    button.setAttribute('type', 'button')
    button.setAttribute('onclick', 'removeLecInModule(this)');
    button.setAttribute('lectureModalId', lectureModalId);
    button.setAttribute('index', (lectures.length - 1).toString());

    newLecInScheme.append(button);
    let icon = document.createElement('i');
    icon.classList.add('fas', 'fa-times', 'text-white');
    button.append(icon);
    document.getElementById('lectureModuleName').value = "";
    document.getElementById('lectureModuleAnnotation').value = "";
    document.getElementById('lectureModuleKeyWords').value = "";
    btnClose();
}
var editElForLecInMod;
function editLec(el){
    addLectureInModule(el);
    let saveButton = document.getElementById('saveLectureInModule');
    saveButton.classList.add('hidden');
    let editButton = document.getElementById('editLectureInModule');
    editButton.classList.remove('hidden');
    //выводим в диалог сохраненную лекцию
    let lectureModuleNameElement = document.getElementById('lectureModuleName');
    lectureModuleNameElement.value = el.getAttribute('lectureModuleName');
    let lectureModuleAnnotationElement = document.getElementById('lectureModuleAnnotation');
    lectureModuleAnnotationElement.value = el.getAttribute('lectureModuleAnnotation');
    let lectureModuleKeyWordsElement = document.getElementById('lectureModuleKeyWords');
    lectureModuleKeyWordsElement.value = el.getAttribute('lectureModuleKeyWords');
    editElForLecInMod = el;
}
function editLecInModule(el){
    let moduleModalId = el.getAttribute('moduleModalId');
    let lectureModalId = el.getAttribute('lectureModalId');
    let index = el.getAttribute('index');

    let lectureModuleName = document.getElementById('lectureModuleName').value;
    let lectureModuleAnnotation = document.getElementById('lectureModuleAnnotation').value;
    let lectureModuleKeyWords = document.getElementById('lectureModuleKeyWords').value;
    let lecture = new Lecture(lectureModalId, moduleModalId, lectureModuleName, lectureModuleAnnotation, lectureModuleKeyWords);
    lectures [index] = lecture;
    // let div = el.parentNode;
    el.setAttribute('lectureModuleName', lectureModuleName);
    el.setAttribute('lectureModuleAnnotation', lectureModuleAnnotation);
    el.setAttribute('lectureModuleKeyWords', lectureModuleKeyWords);
    el.textContent = lectureModuleName;
    document.getElementById('lectureModuleName').value = "";
    document.getElementById('lectureModuleAnnotation').value = "";
    document.getElementById('lectureModuleKeyWords').value = "";
    let saveButton = document.getElementById('saveLectureInModule');
    saveButton.classList.remove('hidden');
    let editButton = document.getElementById('editLectureInModule');
    editButton.classList.add('hidden');
    btnClose();
}
//удаление лекции из модуля
function removeLecInModule(el){
    let index = el.getAttribute('index');
    if (index !== -1) {
        delete lectures[index];
    }
    let div = el.parentNode;
    div.remove();
}
// $(document).on('click', '.lectureBlockScheme button', function(event) {
//     event.stopPropagation();
//     // let text = this.innerHTML;
//     let button = this;
//     let index = button.getAttribute('index');
//     // let lecturemodalid = button.getAttribute('lecturemodalid');
//     // lectures.filter(lecture => lecture.id !== lecturemodalid);
//     // let index = lectures.findIndex(lecture => lecture.id === lecturemodalid);
//     if (index !== -1) {
//         delete lectures[index];
//     }
//     $(this).remove();
// });

//Добавление лекции вне модуля
var newLectureSchemeNumber = 0;
function addLectureScheme() {

    let lectureModalName = document.getElementById('lectureModalName').value;
    let lectureModalAnnotation = document.getElementById('lectureModalAnnotation').value;
    let lectureModalKeyWords = document.getElementById('lectureModalKeyWords').value;
    let lecture = new Lecture(lectureModalId, null, lectureModalName, lectureModalAnnotation, lectureModalKeyWords);
    lectures.push(lecture);


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
    lecName.setAttribute('index', (lectures.length - 1).toString());
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
    button.setAttribute('index', (lectures.length - 1).toString());
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

//редактирование лекции вне модуля
let editElForLecOut;
function editLecOut(el){
    addLecture(el);
    let saveButton = document.getElementById('saveLectureOut');
    saveButton.classList.add('hidden');
    let editButton = document.getElementById('editLecture');
    editButton.classList.remove('hidden');
    //выводим в диалог сохраненную лекцию
    let lectureModuleNameElement = document.getElementById('lectureModalName');
    lectureModuleNameElement.value = el.getAttribute('lectureModuleName');
    let lectureModuleAnnotationElement = document.getElementById('lectureModalAnnotation');
    lectureModuleAnnotationElement.value = el.getAttribute('lectureModuleAnnotation');
    let lectureModuleKeyWordsElement = document.getElementById('lectureModalKeyWords');
    lectureModuleKeyWordsElement.value = el.getAttribute('lectureModuleKeyWords');
    editElForLecOut = el;
}
function editLecOutOf(el){
    let index = el.getAttribute('index');
    let lectureModalId = el.getAttribute('lectureModalId');
    let lectureModuleName = document.getElementById('lectureModalName').value;
    let lectureModuleAnnotation = document.getElementById('lectureModalAnnotation').value;
    let lectureModuleKeyWords = document.getElementById('lectureModalKeyWords').value;
    let lecture = new Lecture(lectureModalId, null, lectureModuleName, lectureModuleAnnotation, lectureModuleKeyWords);
    lectures [index] = lecture;
    // let div = el.parentNode;
    el.setAttribute('lectureModuleName', lectureModuleName);
    el.setAttribute('lectureModuleAnnotation', lectureModuleAnnotation);
    el.setAttribute('lectureModuleKeyWords', lectureModuleKeyWords);
    el.textContent = lectureModuleName;
    document.getElementById('lectureModalName').value = "";
    document.getElementById('lectureModalAnnotation').value = "";
    document.getElementById('lectureModalKeyWords').value = "";
    let saveButton = document.getElementById('saveLectureOut');
    saveButton.classList.remove('hidden');
    let editButton = document.getElementById('editLecture');
    editButton.classList.add('hidden');
    btnClose();
}
function HintShowbyTamara(el) {    // для каждого из зеленых прямоугольников
    lectureBlockSchemeTextHint = document.createElement("div");
    lectureBlockSchemeTextHint.classList.add("lectureBlockSchemeTextHint", "shadow", "text-wrap"); // созданному диву добавили классы (что бы он стал подсказкой)
    lectureBlockSchemeTextHint.setAttribute('id','lectureBlockSchemeTextHint'); //добавили идентификатор блоку с подсказкой
    el.append(lectureBlockSchemeTextHint); // пытаюсь вставить подсказку в массив зеленых блоков, обращаясь к элементу по индексу
    lectureBlockSchemeTextHint.textContent = (el.textContent);   // содержимое подсказки
}

function HintHidebyTamara(el){
    $(el).find('#lectureBlockSchemeTextHint').remove();
}

function deleteTrack(id){
    const url = '/track/' + id; // здесь id - идентификатор трека

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

