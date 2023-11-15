
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

$(document).ready(function() {
	console.log("In Da select: " + localStorage.authorization);
	$(".js-data-example-ajax").select2({
		ajax: {
			url: "https://dev.track.samsmu.ru/public/home/api/author/authors",
			// url: "http://localhost:8081/public/home/api/author/authors",
			type: 'GET',
			dataType: 'json',
			delay: 250,
			headers: {
				"Authorization": localStorage.authorization
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

document.addEventListener("DOMContentLoaded", function() {
	// Получаем ссылку на пункт "Profile" и контейнер <div container fluid>
	var profileLink = document.querySelector("#profile-tab");
	var container = document.querySelector("div[container]");

	// Обрабатываем клик по ссылке "Profile"
	profileLink.addEventListener("click", function(event) {
		event.preventDefault(); // Предотвращаем переход по ссылке

		// Удаляем класс "active" у других пунктов навигации, если есть
		var navItems = document.querySelectorAll(".nav-item");
		for (var i = 0; i < navItems.length; i++) {
			navItems[i].classList.remove("active");
		}

		// Добавляем класс "active" к пункту "Profile"
		profileLink.parentNode.classList.add("active");

		// Удаляем атрибут "hidden" из контейнера <div container fluid>
		container.removeAttribute("hidden");
	});
});