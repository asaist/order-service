<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<meta charset="UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache">
	<title>Служебная записка</title>
</head>
<body>
<h3 style="text-align: center;">Служебная записка</h3>
<p style="text-align: center;">Просим рассмотреть план межвузовского образовательного трека для студентов и клинических ординаторов <b>«${moduleDto.moduleNameModal}»</b>
   в рамках создания 3D архитектуры медицинского знания электронной образовательной среды СамГМУ</p>
<p>${moduleDto.moduleModalAnnotation}</p>
<p>Темы	для лекций курса: «${moduleDto.moduleNameModal}»</p>
<table style="border: 1px solid grey; margin: 0 auto; text-align: center">
	<thead>
	<tr style="border: 1px solid grey">
		<th style="border: 1px solid grey">№</th>
		<th style="border: 1px solid grey">Наименование лекции</th>
		<th style="border: 1px solid grey">ФИО лектора</th>
		<th style="border: 1px solid grey">Кол-во видео-роликов</th>
		<th style="border: 1px solid grey">Подсъёмки</th>

	</tr>
	</thead>
	<tbody>
	<#list lectures as lecture>
		<tr style="border: 1px solid grey">
			<td style="border: 1px solid grey">${lecture.id}</td>
			<td style="border: 1px solid grey">${lecture.lectureModuleName}</td>
			<td style="border: 1px solid grey">${lecture.supervisor}</td>
			<td style="border: 1px solid grey">1</td>
			<td style="border: 1px solid grey">-</td>
		</tr>
	</#list>
	</tbody>
</table>
<p><b>Список ключевых слов: </b></p>
<p>${moduleDto.moduleModalKeyWords}</p>
<p><b>Базовый ролик (навигатор)</b></p>
<p>Данный образовательный трек <b>«${moduleDto.moduleNameModal}»</b> разработан
   в рамках создания 3D архитектуры медицинского знания электронной образовательной среды СамГМУ</p>
<p>изложены современные подходы к диагностике, лечению и
	профилактике.  Данный образовательный трек  предназначен для
	студентов медицинского ВУЗа, </p>
<p>а также для ординаторов, обучающихся по
	специальностям терапия, эндокринология, кардиология, ревматология
	лабораторная диагностика. Курс  состоит из ${lectures?size} лекций - видеороликов.</p>
<p><b>Общая матрица компетенций</b> (ко всему образовательному курсу <b>«${moduleDto.moduleNameModal}»</b>)</p>
<table  style="border: 1px solid grey">
	<tr style="border: 1px solid grey">
		<th style="border: 1px solid grey">Вы узнаете</th>
		<td style="border: 1px solid grey">${moduleDto.moduleLearnCompetenceOne}</td>
	</tr>
	<tr style="border: 1px solid grey">
			<th style="border: 1px solid grey">Вы поймете</th>
			<td style="border: 1px solid grey">${moduleDto.moduleLearnCompetenceTwo}</td>
	</tr>
	<tr style="border: 1px solid grey">
			<th style="border: 1px solid grey">Вы научитесь</th>
			<td style="border: 1px solid grey">${moduleDto.moduleLearnCompetenceThree}</td>
	</tr>
	<tr style="border: 1px solid grey">
			<th style="border: 1px solid grey">Вы сможете
				делать это
				самостоятельно</th>
		<td style="border: 1px solid grey">${moduleDto.moduleLearnCompetenceFour}</td>
	</tr>

</table>
<p><b>Ключевые слова: </b></p>
<p>${moduleDto.moduleModalKeyWords}</p>

<#list lectures as lecture>
	<p><b>Аннотация  к лекции ${lecture.lectureModuleName}</b></p>
	<p>${lecture.lectureModuleAnnotation}</p>
	<p><b>Матрица компетенций</b> к лекции «${lecture.lectureModuleName}»</p>

	<table style="border: 1px solid grey">
		<tr style="border: 1px solid grey">
			<th style="border: 1px solid grey">Вы узнаете</th>
			<td style="border: 1px solid grey">${lecture.learnCompetenceOne}</td>
		</tr>
		<tr style="border: 1px solid grey">
			<th style="border: 1px solid grey">Вы поймете</th>
			<td style="border: 1px solid grey">${lecture.learnCompetenceTwo}</td>
		</tr>
		<tr style="border: 1px solid grey">
			<th style="border: 1px solid grey">Вы научитесь</th>
			<td style="border: 1px solid grey">${lecture.learnCompetenceThree}</td>
		</tr>
		<tr style="border: 1px solid grey">
			<th style="border: 1px solid grey">Вы сможете
				делать это
				самостоятельно</th>
			<td style="border: 1px solid grey">${lecture.learnCompetenceFour}</td>
		</tr>

	</table>
	<p><b>Ключевые слова: </b></p>
	<p>${lecture.lectureModuleKeyWords?ifExists}</p>

</#list>

</body>
</html>