
//чтобы текст в поле наименование трека переносился в шапку дерева
function updateTrackHeading() {
    var trackInput = document.getElementById("trackInput");
    var trackHeading = document.getElementById("trackHeading");
    var trackHeadingInModule = document.getElementById("trackHeadingInModule");
    let trackHeadingInLecture = document.getElementById("trackHeadingInLecture");


    // Обновление содержимого заголовка с использованием значения из textarea
    trackHeading.innerText = 'Образовательный трек «' + trackInput.value + '» электронной образовательной среды СамГМУ»';
    trackHeadingInModule.innerText = 'Образовательный трек «' + trackInput.value + '» электронной образовательной среды СамГМУ»';
    trackHeadingInLecture.innerText = 'Образовательный трек «' + trackInput.value + '» электронной образовательной среды СамГМУ»';
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
    col11.classList.add('col-11');
    bgPrimary.append(col11);

    let p = document.createElement('p');
    p.classList.add('text-center', 'text-white', 'fs16', 'pb-0', 'mb-0');
    // p.textContent = 'новый модуль' + ' ' + 'id=' + newModuleScheme.id;
    p.textContent = moduleNameModal;

    col11.append(p);

    let col1 = document.createElement('div');
    col1.classList.add('col-1');
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
    rowLecInModule.textContent = newModuleScheme.id;
    newModuleScheme.append(rowLecInModule);
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
    let lecName = document.createElement('p');
    lecName.classList.add('text-white', 'text-center', 'm-0', 'p-0', 'text-truncate', 'lectureBlockSchemeText');
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

//удаление лекции из модуля
$(document).on('click', '.lectureBlockScheme', function() {
    // let text = this.innerHTML;
    let button = this.querySelector('button');
    let index = button.getAttribute('index');
    // let lecturemodalid = button.getAttribute('lecturemodalid');
    // lectures.filter(lecture => lecture.id !== lecturemodalid);
    // let index = lectures.findIndex(lecture => lecture.id === lecturemodalid);
    if (index !== -1) {
        delete lectures[index];
    }
    $(this).remove();
});

//Добавление лекции вне модуля
function addLectureScheme() {
    let newLecScheme = document.createElement('div');
    newLecScheme.setAttribute("onmouseover","HintShowbyTamara(this)");
    newLecScheme.setAttribute("onmouseout","HintHidebyTamara(this)");
    newLecScheme.classList.add('bg-success','mx-2', 'p-3', 'mb-1', 'rounded','lectureBlockScheme', 'lecBlockScheme');
    const colLec = document.getElementById('colLec');
    colLec.append(newLecScheme);
    let p = document.createElement('p');
    p.classList.add('text-center', 'text-white', 'm-0', 'p-0', 'text-truncate', 'lectureBlockSchemeText');
    newLecScheme.append(p);
    p.textContent = 'Новая лекция';

}
//Всплывающие подсказки для лекций
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