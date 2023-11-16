<a href="${lecture.link}">
<h3>Федеральное государственное бюджетное<br/>образовательное учреждение высшего образования<br/>«Самарский государственный медицинский университет</h3>
<br>
<br>
<br>
<br>
<p>В рамках подготовки заявки на курс лекций курс лекций о железе, <br/>Вам поступило задание на заполнение карточки лекции на тему: <br/><b>«${lecture.lectureModuleName}»</b>
<table  style="border: 1px solid grey">
	<tr style="border: 1px solid grey">
		<th style="border: 1px solid grey">Аннотация</th>
		<td style="border: 1px solid grey">${lecture.lectureModuleAnnotation}</td>
	</tr>
	<tr style="border: 1px solid grey">
			<th style="border: 1px solid grey">Ключевые слова</th>
			<td style="border: 1px solid grey">${lecture.lectureModuleKeyWords?ifExists}</td>
	</tr>
	<tr style="border: 1px solid grey">
			<th style="border: 1px solid grey">Авторы</th>
			<td style="border: 1px solid grey">${lecture.authorsNames}</td>
	</tr>
	<tr style="border: 1px solid grey">
			<th style="border: 1px solid grey">Руководитель</th>
		<td style="border: 1px solid grey">${lecture.supervisor}</td>
	</tr>
	<tr style="border: 1px solid grey">
		<th style="border: 1px solid grey">Календарных дней на заполнение</th>
		<td style="border: 1px solid grey">${lecture.daysToFill}</td>
	</tr>
</table>
</a>
