<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<meta charset="UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
	<link rel="stylesheet" href="https://dev.track.samsmu.ru/static/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://dev.track.samsmu.ru/static/css/styleForAnnotation.css">
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/styleForAnnotation.css">
	<title>Служебная записка</title>
</head>
<body>
<h3>Служебная записка</h3>
<p>Просим рассмотреть план межвузовского образовательного трека для студентов и клинических ординаторов <b>«${reportData.name}»</b>
   в рамках создания 3D архитектуры медицинского знания электронной образовательной среды СамГМУ</p>
<p>${reportData.annotation}</p>
<p>Темы	для лекций курса: «${reportData.name}»</p>
<table>
	<thead>
	<tr>
		<th>№</th>
		<th>Наименование лекции</th>
		<th>ФИО лектора</th>
		<th>Кол-во видео-роликов</th>
		<th>Подсъёмки</th>
		<th></th>
	</tr>
	</thead>
	<tbody>
	<#list lectures as lecture>
		<tr>
			<td>${lecture.id}</td>
			<td>${lecture.lectureModuleName}</td>
			<td>${lecture.supervisor}</td>
			<td>1</td>
			<td>-</td>
		</tr>
	</#list>
	</tbody>
</table>
<p><b>Список ключевых слов: </b></p>
<p>${reportData.keywords}</p>
<p><b>Базовый ролик (навигатор)</b></p>
<p>Данный образовательный трек <b>«${reportData.name}»</b> разработан
   в рамках создания 3D архитектуры медицинского знания электронной образовательной среды СамГМУ</p>
<p>изложены современные подходы к диагностике, лечению и
	профилактике.  Данный образовательный трек  предназначен для
	студентов медицинского ВУЗа, </p>
<p>а также для ординаторов, обучающихся по
	специальностям терапия, эндокринология, кардиология, ревматология
	лабораторная диагностика. Курс  состоит из ${lectures?size} лекций - видеороликов.</p>
<p><b>Общая матрица компетенций</b> (ко всему образовательному курсу <b>«${reportData.name}»</b>)</p>
<table>
	<tr>
		<th>Вы узнаете</th>
		<td>как шить</td>
	</tr>
	<tr>
			<th>Вы поймете</th>
			<td>кто вы</td>
	</tr>
	<tr>
			<th>Вы научитесь</th>
			<td>убегать от волков</td>
	</tr>
	<tr>
			<th>Вы сможете
				делать это
				самостоятельно</th>
		<td>варить борщи</td>
	</tr>

</table>
<p><b>Ключевые слова: </b></p>
<p>${reportData.keywords}</p>

<#list lectures as lecture>
	<p><b>Аннотация  к лекции ${lecture.lectureModuleName}</b></p>
	<p>${lecture.lectureModuleAnnotation}</p>
	<p><b>Матрица компетенций</b> к лекции «${lecture.lectureModuleName}»</p>

	<table>
		<tr>
			<th>Вы узнаете</th>
			<td>как оперировать</td>
		</tr>
		<tr>
			<th>Вы поймете</th>
			<td>что делать</td>
		</tr>
		<tr>
			<th>Вы научитесь</th>
			<td>зашивать</td>
		</tr>
		<tr>
			<th>Вы сможете
				делать это
				самостоятельно</th>
			<td>заполнять отчеты</td>
		</tr>

	</table>
	<p><b>Ключевые слова: </b></p>
	<p>${lecture.lectureModuleKeyWords?ifExists}</p>

</#list>

</body>
</html>