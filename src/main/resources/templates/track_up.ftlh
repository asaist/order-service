<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>3D timeline</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./styles.css">
    <link rel="stylesheet" href="main.css">   
  </head>
  <body>
    <div class="container-fluid p-3"> 
      <h3>Образовательный трек "${track.trackName}"</h3>

      <div>
        <h5>Аннотация к образовательному треку</h5>
        <p>${track.annotation}</p>
        <p class="text-primary">#Lorem #ipsum #dolor #sitamet</p>
        <div class="d-flex"><h5 class="mr-10">Целевая аудитория:</h5>студенты ИКМ, ординаторы</div>
        <div class="d-flex"><h5 class="mr-10">Руководитель трека:</h5>
          <button type="button" class="btn btn-icon" data-toggle="modal" data-target="#personModal">
            <span class="text-primary">${track.authorName}</span>
          </button>
          <div class="modal fade" id="personModal" tabindex="-1" role="dialog" aria-labelledby="personModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="personModalLabel">Сведения о персоне</h5>
                  <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <h3>Иванов Иван Иванович</h3>
                  <div class="my-2">
                    <span class="mr-10"><b>Должность</b></span>
                    Зав.кафедрой
                  </div>
                  <div class="my-2">
                    <span class="mr-10"><b>Ученое звание</b></span>
                    Профессор
                  </div>
                  <div class="my-2">
                    <span class="mr-10"><b>Ученая степень:</b></span>
                    д.м.н.
                  </div>
                  <div class="my-2">
                    <span class="mr-10"><b>Место работы:</b></span>
                    Университет им.Сеченова, кафедра ортопедии
                  </div>
                  <div class="my-2">
                    <span class="mb-10"><b>Документы:</b></span><br>
                    <div class="d-flex">
                      <div class="mr-10  text-center">
                        <i class="fas fa-file-pdf fa-lg"></i><br>
                        <span style="font-size: 8px;">Диплом</span>
                      </div>
                      <div class="mr-10 text-center">
                        <i class="fas fa-file-pdf fa-lg"></i><br>
                        <span style="font-size: 8px;">Сертификат</span>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Отменить</button>
                <button type="button" class="btn btn-primary">Редактировать</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="d-flex"><h5 class="mr-10">Эксперт:</h5>Петров Петр Петрович</div>
      </div>

      <div class="my-4">
        <div class="d-flex mb-20">
          <h5 class="mr-10">Содержание трека</h5>
          <button type="button" class="btn btn-outline-primary ml-10" data-bs-toggle="modal" data-bs-target="#beginModal"><i class="fas fa-plus"></i></button>
        </div>
        <table class="table table-bordered">
          <tr class="th-table">
            <th class="th-table" scope="col">№</th>
            <th class="th-table" scope="col">Название модуля</th>
            <th class="th-table" scope="col">Авторская группа</th>
            <th class="th-table" scope="col">Аннотация</th>
            <th class="th-table" scope="col">Ключевые слова</th>
            <th class="th-table" scope="col">Целевая аудитория</th>
            <th class="th-table" scope="col">Прочие метаданные по разделам</th>
            <th class="th-table" scope="col">Подсъемки</th>
            <th class="th-table" scope="col"></th>
          </tr>
          <#list track.series as series>
          <tr class="cursor-pointer">
            <th scope="row" onclick="window.location='viewModule.html'">${series.id}</th>
            <td onclick="window.location='viewModule.html'">${series.seriesName}</td>
            <td onclick="window.location='viewModule.html'">Иванов И.И.</td>
            <td onclick="window.location='viewModule.html'">Аннотация к образовательному модулю 1</td>
            <td onclick="window.location='viewModule.html'">Ключевые слова к модулю 1: Перечень;</td>
            <td onclick="window.location='viewModule.html'">Целевая аудитория к модулю 1: Перечень;</td>
            <td onclick="window.location='viewModule.html'">Описание по осям: Перечень; Компетенции: Перечень; Перечень;</td>
            <td onclick="window.location='viewModule.html'">Перечень неоходимых подсъемок</td>
            <td>
              <button type="button" class="btn btn-icon" data-bs-toggle="modal" data-bs-target="">
                <i class="fas fa-edit"></i>
              </button>
            </td>
          </tr>
          </#list>
        </table>

        
        <table class="table table-bordered">
          <tr class="th-table-lecture">
            <th class="th-table-lecture" scope="col">№</th>
            <th class="th-table-lecture" scope="col">Тема лекции</th>
            <th class="th-table-lecture" scope="col">Авторская группа</th>
            <th class="th-table-lecture" scope="col">Аннотация</th>
            <th class="th-table-lecture" scope="col">Ключевые слова</th>
            <th class="th-table-lecture" scope="col">Целевая аудитория</th>
            <th class="th-table-lecture" scope="col">Прочие метаданные по разделам</th>
            <th class="th-table-lecture" scope="col">Подсъемки</th>
            <th class="th-table-lecture" scope="col"></th>
          </tr>
          <#list track.lectures as lecture>
          <tr class="cursor-pointer">
            <th scope="row" onclick="window.location='viewModule.html'">${lecture.id}</th>
            <td onclick="window.location='viewModule.html'">${lecture.lectureName}</td>
            <td onclick="window.location='viewModule.html'">${lecture.authorName}</td>
            <td onclick="window.location='viewModule.html'">${lecture.annotation}</td>
            <td onclick="window.location='viewModule.html'">Ключевые слова к лекции 1: Перечень;</td>
            <td onclick="window.location='viewModule.html'">Целевая аудитория к лекции 1: Перечень;</td>
            <td onclick="window.location='viewModule.html'">Описание по осям: Перечень; Компетенции: Перечень; Перечень;</td>
            <td onclick="window.location='viewModule.html'">Перечень неоходимых подсъемок</td>
            <td>
              <button type="button" class="btn btn-icon" data-bs-toggle="modal" data-bs-target="#editModuleModal">
                <i class="fas fa-edit"></i>
              </button>
            </td>
          </tr>
          </#list>
        </table>

        <div class="modal fade" id="editModuleModal" tabindex="-1" aria-labelledby="editModuleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
              <div class="modal-header">
                <h1 class="modal-title fs-5" id="editModuleModalLabel">Заполнение карточки лекции</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
              </div>
              <div class="modal-body">
                <select class="form-control" name="institute" size="1">
                  <option selected="" disabled="">Выберите модуль</option>
                  <option value="">Модуль 1</option>
                  <option value="">Модуль 2</option>
                  <option value="">Модуль 3</option>
                </select>

                <select class="form-control" name="institute" size="1">
                  <option selected="" disabled="">Выберите лекцию</option>
                  <option value="">Лекция 1</option>
                  <option value="">Лекция 2</option>
                  <option value="">Лекция 3</option>
                </select>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary">Сохранить изменения</button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="d-flex mt-4">
        <div class="seriesLine">
          <h6 id="series"></h6>
          <div class="d-flex" id="trackLinSeries">                       
          </div>          
        </div>

        <div class="mx-2">
          <h6 id="lecture"><i class="fas fa-eye hidden text-primary" id="faListLec" onclick="allLecture()"></i></h6>
          <div class="d-flex" id="trackLineLecture">                       
          </div>
        </div>

        <div class="wrapperPlusBlock wrapperPlusBlockBegin" onclick="beginModal()">
          <div class="plusBlock">
            <i class="fas fa-plus"></i>
          </div>
        </div>
      </div>

      <div class="mt-5 hidden" id="listlecture"> 
        <h6>Список лекций</h6>
        <div class="d-flex">          
          <div class="empty-block" title="Добавьте недостающую лекцию" onclick="addLectures()">
            <i class="fas fa-question"></i>
          </div>
          <div class="wrapperPlusBlock" title="Добавьте новую лекцию" onclick="addLecture()">
            <div class="plusBlock">
              <i class="fas fa-plus"></i>
            </div>
          </div>           
        </div>
      </div>
     
      <div class="overlay hidden" id="overlayViewLectureModal"></div>  
      <div class="modal-wrapper">  
        <div class="hidden shadow lectureModal" id="viewLectureModal">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header mb-3 d-flex justify-content-center">
                <h3 id="nameLecture">Ожирение</h3>                  
              </div>                  
              <div class="modal-body" id="nameLectureBody"> 
                  <h5>Аннотация</h5>
                  <div id="annotation"></div>
                  <p>Какой-то текст</p>
                  <h5><i class="fas fa-video text-primary"></i>&nbsp; Ссылка на видео</h5>
                  <p class="text-primary">http://</p>
              </div>
              <div class="modal-footer">
                 Кнопка сохранить
                <button type="button" class="btn btn-primary" id="saveViewLecture" onclick="btnClose()">ОК</button>                  
              </div>
            </div>
          </div>
        </div>  
      </div>              

      
      <div class="modal fade" id="beginModal" aria-hidden="true" aria-labelledby="beginModalLabel" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">            
              <div class="modal-body"> 
                  <div class="d-flex justify-content-end">
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button> 
                  </div>
                  <h3 class="text-center"><i class="fas fa-exclamation-circle text-primary"></i></h3> 
                  <h5 class="text-center">Для наполнения трека выберите тип добавляемого контента</h5>
                <div class="d-flex justify-content-center">             
                  <button type="button" class="btn btn-primary m-2" data-bs-toggle="modal" data-bs-target="#addModalModule">Модуль</button>                
                  <button type="button" class="btn btn-primary m-2" data-bs-toggle="modal" data-bs-target="#addModalLecture">Лекция</button>
                </div>
              </div>
            </div>
          </div>        
      </div>

      <div class="modal fade" id="addModalModule" tabindex="-1" aria-labelledby="addModalModuleLabel" aria-hidden="true" style="z-index: 10000">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
              <div class="modal-header">
                <h5>Добавить модуль</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>                
              </div>                  
              <div class="modal-body"> 
                  <div class="mb-10">                    
                    <select class="form-control" class="form-control" name="track" size="1">
                      <option select class="form-control"ed="" disabled="">Выберите образовательный трек</option>
                      <option value="Трек 1">Трек 1</option>
                      <option value="Трек 2">Трек 2</option>
                      <option value="Трек 3">Трек 3</option>
                      <option value="Трек 4">Трек 4</option>
                    </select class="form-control">
                  </div>
                  <div class="mb-10">
                    <input type="text" class="form-control mb-2" id="headSeries" placeholder="Название образовательного модуля">                    
                  </div>
                  <div class="my-4">
                  <p><b>Автор образовательного модуля</b></p>
                  <div>
                    <div class="form-check mb-10">
                      <input class="form-check-input" type="checkbox" name="is-emsloyee" onclick="authorSamGMU()">
                      <label class="form-check-label">Автор является сотрудником СамГМУ</label>
                    </div>
                    <div class="hidden" id="authorSamGMU">
                      <select class="form-control mb-20" name="author">
                        <option>Выберите автора</option>
                        <option value="Иванов">Иванов</option>
                        <option value="Петров">Петров</option>
                        <option value="Сидоров">Сидоров</option>
                        <option value="Толстой">Толстой</option>
                      </select class="form-control">
                      <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Добавить автора</button>  
                    </div>
                    <div id="authorWorld">
                      <textarea class="form-control mb-10" name="module-name" cols="80" rows="1" placeholder="Введите ФИО автора"></textarea>
                      <div class="d-flex mb-10">
                        <select class="form-control w-50 mr-10" name="regalia" size="1">
                          <option select class="form-control"ed="" disabled="">Выберите регалии</option>
                          <option value="Профессор">Профессор</option>
                          <option value="К.М.Н.">К.М.Н.</option>
                          <option value="Доцент">Доцент</option>
                          <option value="Зав. кафедрой">Зав. кафедрой</option>
                        </select class="form-control">
                        <button type="button" class="btn btn-primary">
                          <i class="fas fa-plus"></i>
                        </button>
                      </div>
                      <div class="accordion mb-10" id="accordionDocsPerson">
                        <div class="accordion-item">
                          <h2 class="accordion-header" id="headingOne">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                              <b>Добавление документов автора</b>
                            </button>
                          </h2>
                          <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionDocsPerson">
                            <div class="accordion-body"> 
                              <div class="mb-10">                             
                                <b>Загрузите паспорт</b><br>
                                <div class="d-flex">
                                  <input class="form-control mr-10" type="file" id="pasport" name="pasport">
                                  <button type="button" class="btn btn-primary ml-10"><i class="fas fa-file-upload"></i></button>
                                </div>
                              </div>
                              <div class="mb-10"> 
                                <b>Загрузите диплом о в/о"</b><br>
                                <div class="d-flex">
                                  <input class="form-control mr-10" type="file" id="diploma1" name="diploma1">
                                  <button type="button" class="btn btn-primary ml-10"><i class="fas fa-file-upload"></i></button>
                                </div>
                              </div>
                              <div class="mb-10"> 
                                <b>Загрузите диплом о научном звании</b><br>
                                <div class="d-flex">
                                  <input class="form-control mr-10" type="file" id="diploma2" name="diploma2">
                                  <button type="button" class="btn btn-primary ml-10"><i class="fas fa-file-upload"></i></button>
                                </div>
                              </div>
                              <div class="mb-10"> 
                                <b>Загрузите диплом о научной степени</b><br>
                                <div class="d-flex">
                                  <input class="form-control mr-10" type="file" id="diploma3" name="diploma3">
                                  <button type="button" class="btn btn-primary ml-10"><i class="fas fa-file-upload"></i></button>
                                </div>
                              </div>
                              <div class="mb-10"> 
                                <b>Загрузите справку об отсутствии судимости</b><br>
                                <div class="d-flex">
                                  <input class="form-control mr-10" type="file" id="criminal" name="criminal">
                                  <button type="button" class="btn btn-primary ml-10"><i class="fas fa-file-upload"></i></button>
                                </div>
                              </div>
                              <div class="mb-10"> 
                                <b>Загрузите справку о состоянии здоровья</b><br>
                                <div class="d-flex">
                                  <input class="form-control mr-10" type="file" id="health" name="health">
                                  <button type="button" class="btn btn-primary ml-10"><i class="fas fa-file-upload"></i></button>
                                </div>
                              </div>
                              <div class="mb-20">      
                                <b>Загрузите последнюю страницу трудовой книжки</b><br>
                                <div class="d-flex">
                                  <input class="form-control mr-10" type="file" id="workbook" name="workbook">
                                  <button type="button" class="btn btn-primary ml-10"><i class="fas fa-file-upload"></i></button>
                                </div>
                              </div>                            
                            </div>
                          </div>                          
                        </div>                           
                      </div> 
                      <button type="button" class="btn btn-outline-primary">Добавить автора</button>                     
                    </div>
                  </div>
                </div>

                  <div class="my-4">
                    <b>Аннотация/содержание образовательного Модуля</b><br>
                    <textarea class="form-control" name="track-annotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>
                  </div>

                  <div class="mt-10 mb-10">
                    <b>Дополнительные ключеные слова</b><br>
                    <textarea class="form-control" name="module-annotation" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;"></textarea>
                  </div>

                  <div class="mt-10 mb-10">
                    <b>Целевая аудитория</b><br>
                    <select class="form-control" class="form-control" name="listners" size="1">
                      <option select class="form-control"ed="" disabled="">Выберите целевую аудиторию</option>
                      <option value="Студент 1-3 курса">Студент 1-3 курса</option>
                      <option value="Студент 4- курса">Студент 4- курса</option>
                      <option value="Студент 1-6 курса">Студент 1-6 курса</option>
                      <option value="Ординатор">Ординатор</option>
                      <option value="Аспирант">Аспирант</option>
                      <option value="Врач-кардиолог">Врач-кардиолог</option>
                      <option value="Врач-терапевт">Врач-терапевт</option>
                      <option value="Врач-и т.п.">Врач-и т.п.</option>
                    </select class="form-control">
                  </div>

                  <div class="mt-10 mb-10">
                    <b>Ось МКБ</b><br>
                    <select class="form-control" name="mkb" size="1">
                      <option select class="form-control"ed="" disabled="">Выберите значение</option>
                      <option value="Вентряная оспа">Вентряная оспа</option>
                      <option value="Воспаление">Воспаление</option>
                      <option value="Ожирение">Ожирение</option>
                      <option value="ИБС">ИБС</option>
                    </select class="form-control">
                  </div>

                  <div class="mt-10 mb-10">
                    <b>Ось Дисциплин/учебный план</b><br>
                    <select class="form-control" name="discipline" size="1">
                      <option select class="form-control"ed="" disabled="">Выберите дисциплину</option>
                      <option value="Общая анатомия">Общая анатомия</option>
                      <option value="Биохимия">Биохимия</option>
                      <option value="Диетолония">Диетолония</option>
                      <option value="Фармакология">Фармакология</option>
                    </select class="form-control">
                  </div>
                  <div class="mt-10 mb-10">
                    <b>Ось Локализации</b><br>
                    <select class="form-control" name="atlas" size="1">
                      <option select class="form-control"ed="" disabled="">Выберите локализацию</option>
                      <option value="Мозг">Мозг</option>
                      <option value="Пищеватительная система">Пищеватительная система</option>
                      <option value="Сердце">Сердце</option>
                      <option value="Опорнодвигательная система">Опорнодвигательная система</option>
                    </select class="form-control">
                  </div>    
                  <div class="mt-10 mb-10">
                    <b>Ось Внешних факторов</b><br>
                    <select class="form-control" name="exfacts" size="1">
                      <option select class="form-control"ed="" disabled="">Выберите фактор</option>
                      <option value="Солнце">Солнце</option>
                      <option value="Атмосферное давление">Атмосферное давление</option>
                      <option value="Автомобиль">Автомобиль</option>
                      <option value="Воздействие радиации">Воздействие радиации</option>
                    </select class="form-control">
                  </div>
                  <div class="mt-10 mb-10">
                    <b>Компетенции</b><br>
                    <div class="d-flex">
                      <select class="form-control w-75 mr-10" name="kompetetion" size="1">
                        <option select class="form-control"ed="" disabled="">Выберите компетению</option>
                        <option value="Шить">Шить</option>
                        <option value="Брать кровь">Брать кровь</option>
                        <option value="Писать">Писать</option>
                        <option value="Проводить аускультацию">Проводить аускультацию</option>
                      </select class="form-control">
                      <button type="button" class="btn btn-primary ml-10"><i class="fas fa-plus"></i></button>
                    </div>
                  </div>

                  <div class="form-check mt-10 mb-10">
                      <input class="form-check-input" type="checkbox" name="is-subsurvey" onclick="subshooting()">
                      <label class="form-check-label">Подсъёмки (при наличии)</label>
                    </div>

                  <div class="mt-10">                    
                    <textarea id="subsurvey" class="hidden form-control" cols="50" rows="2" placeholder="Краткое описание подсъёмки"></textarea>
                  </div>

              </div>
              <div class="modal-footer">                
                <button type="button" class="btn btn-primary" id="saveLecture" onclick="saveSeries()">Добавить модуль</button>
              </div>
            </div>
          </div>        
     </div> 

      <div class="modal fade" id="addModalLecture" tabindex="-1" aria-labelledby="addModalLectureLabel" aria-hidden="true" style="z-index: 10000">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
              <div class="modal-header">
                <h5>Добавить лекцию</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button> 
              </div>                  
              <div class="modal-body"> 
                  <input type="text" class="form-control mb-2" id="headLecture" placeholder="Название лекции">
                  <textarea class="form-control mb-2" placeholder="Аннотация к лекции"></textarea>
                  <input type="text" class="form-control mb-2" id="" placeholder="Ссылка на видео">
              </div>
              <div class="modal-footer">
                 Кнопка сохранить
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="saveLecture" onclick="saveLecture()">Сохранить</button>
              </div>
            </div>
          </div>
        
      </div> 

      <div class="modal-wrapper"> 
        <div class="lectureModal shadow hidden" id="allLecture">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5>Доступные лекции</h5>
                <button type="button" class="btn-close" onclick="btnClose()"></button>
              </div>                  
              <div class="modal-body"> 
                  <div>dsfhtjykljhgfd</div>
                  <div>dsfhtjykljhgfd</div>
                  <div>dsfhtjykljhgfd</div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="btnClose()">Ок</button>
              </div>
            </div>
          </div>
        </div>
      </div> 

    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> 
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script> 
    
    <script src="index.js"></script>   
    <script>
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
    </script>  
  </body>  
</html>