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
    // let viewOverlay = document.getElementById('overlayViewLectureModal');
    // viewOverlay.classList.add('hidden');
}

function addLecture() {
 	let addLectureModal = document.getElementById('addLectureModal');
	addLectureModal.classList.remove('hidden');
	// showOverlay();
}

function addSeries() {
	let addSeries = document.getElementsByName('modalSeries');
		for (let series of addSeries) {
			series.classList.remove('hidden'); 
		}

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

var lectureBlockNumber = 0;

function saveLecture() {
	btnClose();

	let trackLineLecture = document.getElementById('trackLineLecture');	
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

    let lectureBlockIn = document.createElement('div'); // добавляем внутренний светло-синий блок
	lectureBlockIn.classList.add('lectureBlockIn');
	lectureBlock.append(lectureBlockIn);

	lectureBlockNumber++; // Присваиваем id
    lectureBlock.id = "lecture_" + lectureBlockNumber;

	lectureBlockIn.classList.add('numberBlockCenter')
	lectureBlockIn.append(lectureBlockNumber);

	document.getElementById('headLecture').value = "";	
}

function saveSeries() {
	btnClose();

	let trackLinSeries = document.getElementById('trackLinSeries');	
		if (trackLinSeries.innerHTML.trim().length == 0) {
			const seriesElem = document.createTextNode('Серии');
			series.append(seriesElem);
		}

	let seriesBlock = document.createElement('div');
	seriesBlock.classList.add('seriesBlock');
	trackLinSeries.append(seriesBlock);

	seriesBlock.setAttribute('onclick', 'seriesClick()');

	let arrowLectureBlock = document.createElement('div');
	arrowLectureBlock.classList.add('arrowLectureBlock');
	seriesBlock.append(arrowLectureBlock);

	let caret = document.createElement('i');
	caret.classList.add('fas', 'fa-caret-down', 'fasIcon');
	arrowLectureBlock.append(caret);

	document.getElementById('headSeries').value = "";
	document.getElementById('headSeries').value = "";
}

function allLecture() {
	showOverlay();
	let allLecture = document.getElementById('allLecture');
	allLecture.classList.remove('hidden');
}