
<h3 style="text-indent: 20px; text-align: center;">Федеральное государственное<br/> бюджетное образовательное<br/> учреждение высшего образования<br/>«Самарский государственный<br/> медицинский университет</h3>
<br>
<br>
<br>
<br>
<a href='${lecture.link}' target='_blank' style="text-indent: 20px; text-align: center;">Задание на заполнение лекции ${lecture.lectureModuleName}
<p style="text-indent: 20px; text-align: justify;">В рамках подготовки заявки на курс лекций курс лекций о железе, Вам поступило задание на заполнение карточки лекции на тему: <b>«${lecture.lectureModuleName}»</b>
	<div style="display: flex; justify-content: center;">
		<table style="border: 1px solid grey">
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
	</div>
</a>
