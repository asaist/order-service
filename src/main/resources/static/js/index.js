
function getCurrentDateTime() {
	let currentDate = new Date();
	let year = currentDate.getFullYear();
	let month = ("0" + (currentDate.getMonth() + 1)).slice(-2); // добавляем ведущий ноль, если месяц < 10
	let day = ("0" + currentDate.getDate()).slice(-2); // добавляем ведущий ноль, если день < 10
	let hours = ("0" + currentDate.getHours()).slice(-2); // добавляем ведущий ноль, если час < 10
	let minutes = ("0" + currentDate.getMinutes()).slice(-2); // добавляем ведущий ноль, если минута < 10

	return year + "-" + month + "-" + day + " " + hours + ":" + minutes;
}

function addDaysToCurrentDateTime(days) {
	var currentDateTime = new Date(getCurrentDateTime());
	currentDateTime.setDate(currentDateTime.getDate() + days);

	var year = currentDateTime.getFullYear();
	var month = ("0" + (currentDateTime.getMonth() + 1)).slice(-2);
	var day = ("0" + currentDateTime.getDate()).slice(-2);
	var hours = ("0" + currentDateTime.getHours()).slice(-2);
	var minutes = ("0" + currentDateTime.getMinutes()).slice(-2);

	return year + "-" + month + "-" + day + " " + hours + ":" + minutes;
}

function authorSamGMU() {
	let authorSamGMU = document.getElementById('authorSamGMU');
	authorSamGMU.classList.toggle("hidden");
	let authorWorld = document.getElementById('authorWorld');
	authorWorld.classList.toggle("hidden");
	let createAuthorButton = document.getElementById('createAuthorButton');
	createAuthorButton.classList.toggle("hidden");
}

function authorSamGMULecture() {
	let authorSamGMULecture = document.getElementById('authorSamGMULecture');
	authorSamGMULecture.classList.toggle("hidden");
	let authorWorldLecture = document.getElementById('authorWorldLecture');
	authorWorldLecture.classList.toggle("hidden");
	let createAuthorButtonLecture = document.getElementById('createAuthorButtonLecture');
	createAuthorButtonLecture.classList.toggle("hidden");
}

function showDoc(el) {
	var tbl = el.parentElement.parentElement;
	tbl.rows[1].classList.toggle('hidden');
	let caretIcon = el.querySelector('.fa-caret-down, .fa-caret-up');
	if (caretIcon.classList.contains('fa-caret-down')) {
		caretIcon.classList.remove('fa-caret-down');
		caretIcon.classList.add('fa-caret-up');
	} else if (caretIcon.classList.contains('fa-caret-up')) {
		caretIcon.classList.remove('fa-caret-up');
		caretIcon.classList.add('fa-caret-down');
	}
}

$(document).ready(function() {
	console.log("In Da select: " + window.token);
	$(".js-data-example-ajax").select2({
		ajax: {
			url: "https://dev.track.samsmu.ru/public/home/api/author/authors",
			// url: "http://localhost:8081/public/home/api/author/authors",
			type: 'GET',
			dataType: 'json',
			delay: 250,
			headers: {
				"Authorization": window.token
			},
			data: function (params) {
				return {
					q: params.term, // search term
					page: params.page
				};
			},
			processResults: function (data) {
				let authors = data.map(function (author) {
					return {id: author.id, text: author.lastName + ' ' + author.firstName + ' ' + author.middleName,
						academicDegreeName: author.academicDegreeName, passportDB: author.passportPdf, diplomaDB: author.diplomaPdf,
						diplomaScienceRankDB: author.diplomaScienceRankPdf, diplomaScienceDegreeDB: author.diplomaScienceDegreePdf,
						noCriminalRecordDB: author.noCriminalRecordPdf, healthStatusDB: author.healthStatusPdf,
						employmentBookDB: author.employmentBookPdf
					};
				});
				return {
					results: authors
				};
			},
			transport: function (params, success, failure) {
				let $request = $.ajax(params);
				$request.then(success);
				$request.fail(failure);
				return $request;
			}
		},
		placeholder: 'Выберите автора',
		minimumInputLength: 1,
		templateResult: function (author) {
			return author.text;
		},
		templateSelection: function (author) {
			return author.text;
		}
	});
});

function seriesClick() {
	let listlecture = document.getElementById('listlecture');
	listlecture.classList.toggle('hidden');

	let fasArrow = document.getElementsByClassName('fasIcon');
	for (let elemFas of fasArrow) {
		if (listlecture.getElementsByClassName('hidden').length) {		
			fasArrow.classList.remove('fa-caret-down');
			fasArrow.classList.add('fa-caret-up');
		}	
		else {
			fasArrow.classList.add('fa-caret-down');
			fasArrow.classList.remove('fa-caret-up');	
		}
	}
}

function showOverlay() {
	let viewOverlay = document.getElementById('overlayViewLectureModal'); 
    viewOverlay.classList.remove('hidden');
}

function viewLectureModal() {
	let viewLectureModal = document.getElementById('viewLectureModal');
	viewLectureModal.classList.remove('hidden');
	showOverlay();
}

function btnClose() {
	let viewLectureModal = document.getElementsByClassName('lectureModal');
		for (var vlec of viewLectureModal) {
			vlec.classList.add('hidden');
		}
	document.getElementById('tableAuthorModal').innerHTML = '';
	document.getElementById('lectureModalNameModule').value = "";
	document.getElementById('lectureModalAnnotationModule').value = "";
	document.getElementById('lectureModalKeyWordsModule').value = "";
	document.getElementById('is-emsloyeelecture').checked = false;
	// $('#selectAuthorToModal').val('').trigger('change');

    // let viewOverlay = document.getElementById('overlayViewLectureModal');
    // viewOverlay.classList.add('hidden');
}

function addLecture() {
 	let addLectureModal = document.getElementById('addLectureModal');
	addLectureModal.classList.remove('hidden');
	// showOverlay();
}

function addLectureInModuleSeries() {
	let addLectureModal = document.getElementById('addLectureModalModule');
	addLectureModal.classList.remove('hidden');
	// showOverlay();
}

function addLectureInModule(el) {
	let addLectureModal = document.getElementById('addLectureInModule');
	addLectureModal.classList.remove('hidden');
	let moduleModalId = el.getAttribute('moduleModalId');
	let moduleNameModal = el.getAttribute('moduleNameModal');
	addLectureModal.setAttribute("moduleModalId", moduleModalId);
	addLectureModal.setAttribute("moduleNameModal", moduleNameModal);
	let moduleHeadingInLectureElement = document.getElementById('moduleHeadingInLecture');
	moduleHeadingInLectureElement.textContent = 'Образовательный модуль «' + moduleNameModal + '» электронной образовательной среды СамГМУ»';
	// document.getElementById('moduleHeadingInLecture').value = el.getAttribute("moduleNameModal");
}

function addSeries(el) {
	let addSeries = document.getElementsByName('modalSeries');
		for (let series of addSeries) {
			series.classList.remove('hidden'); 
		}
	// let moduleModalId = el.getAttribute('moduleModalId');
	// let moduleNameModal = el.getAttribute('moduleNameModal');
	// addSeries.setAttribute("moduleModalId", moduleModalId);
	// addSeries.setAttribute("moduleNameModal", moduleNameModal);

	// showOverlay();
}

function beginModal() {
	let beginModal = document.getElementById('beginModal');
	beginModal.classList.remove('hidden');
	showOverlay();
}
function addSeriesBegin() {
	btnClose();
	addSeries();
}

function addLectureModalBegin() {
	btnClose();
	addLecture();
}

function addLectureModalModuleBegin() {
	btnClose();
	addLectureInModuleSeries();
}


var saveElForLecInMod;
function addLectureInModuleBegin(el) {
	btnClose();
	addLectureInModule(el);

	saveElForLecInMod = el;
}
function addLectureInModuleN() {

}

// let saveLectureInModule= document.getElementById('saveLectureInModule');
// saveLectureInModule.setAttribute('onclick', 'addLecInModule(saveElForLecInMod)');

// let editLectureInModule= document.getElementById('editLectureInModule');
// editLectureInModule.setAttribute('onclick', 'editLecInModule(editElForLecInMod)');

// let editLectureOut= document.getElementById('editLecture');
// editLectureOut.setAttribute('onclick', 'editLecOutOf(editElForLecOut)');

// let editModuleEl= document.getElementById('editModule');
// editModuleEl.setAttribute('onclick', 'saveEditModule(editElForMod)');

let editLectureModuleSeries= document.getElementById('editLectureInModuleSeries');
editLectureModuleSeries.setAttribute('onclick', 'editLecInModuleSeries(editElForLecOut)');

var lectureBlockNumber = 0;


function saveLecture() {

	// let trackLineLecture = document.getElementById('addLectureInModule');
	if (trackLineLecture.innerHTML.trim().length == 0) {
		let faListLec = document.getElementById('faListLec');
		faListLec.classList.remove('hidden');
		const lectureElem = document.createTextNode('Лекции');
		lecture.append(lectureElem);
	}

	let lectureBlock = document.createElement('div');
    lectureBlock.classList.add('lectureBlock');
    trackLineLecture.append(lectureBlock);

    lectureBlock.setAttribute('onclick', 'viewLectureModal()');

    // let lectureBlockIn = document.createElement('div'); // добавляем внутренний светло-синий блок
	// lectureBlockIn.classList.add('lectureBlockIn');
	// lectureBlock.append(lectureBlockIn);

	// lectureBlockNumber++; // Присваиваем id
    // lectureBlock.id = "lecture_" + lectureBlockNumber;
	//
	// lectureBlockIn.classList.add('numberBlockCenter')
	// lectureBlockIn.append(lectureBlockNumber);

	document.getElementById('headLecture').value = "";
	btnClose();
}

function saveSeries() {
	btnClose();

	let trackLinSeries = document.getElementById('addSeriesModal');
		if (trackLinSeries.innerHTML.trim().length == 0) {
			const seriesElem = document.createTextNode('Серии');
			series.append(seriesElem);
		}

	// let seriesBlock = document.createElement('div');
	// seriesBlock.classList.add('seriesBlock');
	// trackLinSeries.append(seriesBlock);
	//
	// seriesBlock.setAttribute('onclick', 'seriesClick()');


	// let arrowLectureBlock = document.createElement('div');
	// arrowLectureBlock.classList.add('arrowLectureBlock');
	// seriesBlock.append(arrowLectureBlock);
	//
	// let caret = document.createElement('i');
	// caret.classList.add('fas', 'fa-caret-down', 'fasIcon');
	// arrowLectureBlock.append(caret);
	document.getElementById('headSeries').value = "";
	document.getElementById('moduleModalAnnotation').value = "";
	document.getElementById('moduleModalKeyWords').value = "";
}

function allLecture() {
	showOverlay();
	let allLecture = document.getElementById('allLecture');
	allLecture.classList.remove('hidden');
}