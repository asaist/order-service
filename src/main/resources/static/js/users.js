class Author {
    constructor(id, fullName, degree, isSupervisor, passport) {
        this.id = id;
        this.fullName = fullName;
        this.degree = degree;
        this.isSupervisor = isSupervisor;
        this.passport = passport;
    }
}
var authors = [];
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

    var value = e.value;
    var text = e.options[e.selectedIndex].text;
    var id = e.options[e.selectedIndex].id;
    var index = e.options[e.selectedIndex];
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

    const preview = document.createElement('div');
    preview.classList.add('preview');
    preview.setAttribute('id', 'pdfContainer');

    const open = document.createElement('button');
    open.classList.add('btn');
    open.textContent = 'Открыть'

    passportInput.classList.add('form-control', 'mr-10', 'w-75');
    passportInput.setAttribute('type', 'file');

    passportInput.insertAdjacentElement('afterend', preview);
    passportInput.insertAdjacentElement('afterend', open);

    const triggerInput = () => passportInput.click();

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
                passportInput.insertAdjacentHTML('afterend', `<img src="${ev.target.result}" />`)
                const pdfData = ev.target.result;
                // const iframe = document.createElement('iframe');
                // iframe.src = pdfData;
                // iframe.width = '100%';
                // iframe.height = '500px';
                // document.body.appendChild(iframe);
                const pdfContainer = document.getElementById('pdfContainer');

                PDFObject.embed(pdfData, "#pdfContainer");
            }

            reader.readAsDataURL(file);
        })
        if (fileList.length > 0) {
            // Если выбран хотя бы один файл
            const file = fileList[0]; // Берем первый файл из списка
            author.passport = file; // Присваиваем файл свойству passport объекта author
        }
    });
    open.addEventListener('click', triggerInput);
    trDocPassport.append(passportInput);

    // author.passport = passportInput;

    let btnUpload = document.createElement('button');
    btnUpload.classList.add('btn', 'btn-primary', 'ml-10');
    btnUpload.setAttribute('type', 'button');
    btnUpload.setAttribute('id', author.id);
    btnUpload.setAttribute('fullName', author.fullName);
    btnUpload.setAttribute('passport', author.passport);
    // btnUpload.setAttribute('onclick', 'sendAuthorDocs(this)');
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
    trDocDiplom.append(diplomInput);
    let btnUploadDip = document.createElement('button');
    btnUploadDip.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadDip.setAttribute('type', 'button');
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
    trDocRank.append(rankInput);
    let btnUploadRank = document.createElement('button');
    btnUploadRank.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadRank.setAttribute('type', 'button');
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
    trDocDegree.append(degreeInput);
    let btnUploadDegree = document.createElement('button');
    btnUploadDegree.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadDegree.setAttribute('type', 'button');
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
    trDocCriminal.append(criminalInput);
    let btnUploadCriminal = document.createElement('button');
    btnUploadCriminal.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadCriminal.setAttribute('type', 'button');
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
    trDocHealth.append(healthInput);
    let btnUploadHealth = document.createElement('button');
    btnUploadHealth.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadHealth.setAttribute('type', 'button');
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
    trDocExperience.append(experienceInput);
    let btnUploadExperience = document.createElement('button');
    btnUploadExperience.classList.add('btn', 'btn-primary', 'ml-10');
    btnUploadExperience.setAttribute('type', 'button');
    trDocExperience.append(btnUploadExperience);
    let uploadIconExperience = document.createElement('i');
    uploadIconExperience.classList.add('fas', 'fa-file-upload');
    btnUploadExperience.append(uploadIconExperience);
    //
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
    console.log(formData.get('passport'));
    let response = await fetch('/api/author/docs', {
        method: 'POST',
        body: formData
    });
    let result = await response.json();
    alert(result.message);
}