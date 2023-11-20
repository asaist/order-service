<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<meta charset="UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache">
	<title>Служебная записка</title>
</head>
<body>
<h3 style="text-align: center;">Служебная записка</h3>
<br>
<p style="text-indent: 20px; text-align: justify;">Просим рассмотреть план межвузовского образовательного трека для студентов и клинических ординаторов <b>«${moduleDto.moduleNameModal}»</b>
   в рамках создания 3D архитектуры медицинского знания электронной образовательной среды СамГМУ</p>
<p style="text-indent: 20px; text-align: justify;">${moduleDto.moduleModalAnnotation}</p>
<p style="margin: 20px;">Темы для лекций курса: «${moduleDto.moduleNameModal}»</p>
<br>
<table style="border: 1px solid grey;">
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
			<td style="border: 1px solid grey">${lecture?index + 1}</td>
			<td style="border: 1px solid grey">${lecture.lectureModuleName}</td>
			<td style="border: 1px solid grey">${lecture.supervisor}</td>
			<td style="border: 1px solid grey">1</td>
			<td style="border: 1px solid grey">-</td>
		</tr>
	</#list>
	</tbody>
</table>
<br>
<p><b>Список ключевых слов: </b></p>
<br>
<p style="text-align: justify;">${moduleDto.moduleModalKeyWords}</p>
<br>
<p style="margin: 20px;"><b>1. Базовый ролик (навигатор)</b></p>
<br>
<p style="text-indent: 20px; text-align: justify;">Данный образовательный трек <b>«${moduleDto.moduleNameModal}»</b> разработан
   в рамках создания 3D архитектуры медицинского знания электронной образовательной среды СамГМУ</p>
<p>изложены современные подходы к диагностике, лечению и
	профилактике.  Данный образовательный трек  предназначен для
	студентов медицинского ВУЗа, </p>
<p>а также для ординаторов, обучающихся по
	специальностям терапия, эндокринология, кардиология, ревматология
	лабораторная диагностика. Курс  состоит из ${lectures?size} лекций - видеороликов.</p>
<br>
<p style="text-indent: 20px;"><b>Общая матрица компетенций</b> (ко всему образовательному курсу <b>«${moduleDto.moduleNameModal}»</b>)</p>
<br>
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
<br>
<p style="text-indent: 20px;"><b>Ключевые слова: </b></p>
<p>${moduleDto.moduleModalKeyWords}</p>
<br>
<#list lectures as lecture>

	<p style="text-indent: 20px; text-align: justify;"><b>${lecture?index + 2}. Аннотация  к лекции ${lecture.lectureModuleName}</b></p>
	<p style="text-indent: 20px; text-align: justify;">${lecture.lectureModuleAnnotation}</p>
	<p style="text-indent: 20px; text-align: justify;"><b>Матрица компетенций</b> к лекции «${lecture.lectureModuleName}»</p>

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
	<p style="margin: 20px;"><b>Ключевые слова: </b></p>
	<p style="margin: 20px; text-align: justify;">${lecture.lectureModuleKeyWords?ifExists}</p>
	<br>
	<br>
	<br>

</#list>

</body>
</html>