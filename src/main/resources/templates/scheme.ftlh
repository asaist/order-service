<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>3D timeline</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/styles.css">
  </head>
  <body>
    <div class="d-flex">
      <div class="left-schema d-flex align-items-center justify-content-center">       
        <div>
          <div class="d-flex">
            <div data-tooltip="Выберите элемент для создания">
              <div class="d-flex justify-content-center align-items-center rounded-circle p-2 icon-circle bg-primary" id="networkWired">
                <i class="fas fa-network-wired text-center text-white" id="iconNetworkWired"></i>
              </div>
              <div class="d-flex justify-content-center">
                <div class="track-line">
                </div>
              </div>
            </div>
          </div>

          <div class="d-flex">
            <div data-tooltip="Заполните заявку">
              <div class="d-flex justify-content-center align-items-center rounded-circle p-2  icon-circle bg-white" id="signature">
                <i class="fas fa-file-signature text-center" id="iconSignature"></i>
              </div>
              <div class="d-flex justify-content-center">
                <div class="track-line">
                </div>
              </div>
            </div>
          </div>
          
          <div class="d-flex" data-tooltip="Нажмите 'Сохранить' и элемент появится в структуре">
            <div class="d-flex justify-content-center align-items-center rounded-circle p-2  icon-circle bg-white" id="check">
              <i class="fas fa-check text-center" id="iconCheck"></i>
            </div>
          </div>
        </div>
      </div>


      <div class="right-body">        
        <ul class="nav nav-pills" id="myTab" role="tablist">
          <li class="nav-item" role="presentation">
            <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Трек</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Модуль</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Лекция</button>
          </li>          
        </ul>             

        <div class="applicationBodySchema">         
          <div class="tab-content" id="myTabContent">
           <!-- Заявка на Трек -->
            <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">   
              <div>
                <div class="d-flex justify-content-center">
                  <h5 class="modal-title">Заявка на создание трека</h5>                
                </div>
                <div class="modal-body">                

                  <select class="form-control" name="institute" size="1" onclick="trackSignature()">
                    <option selected="" disabled="">Выберите институт</option>
                    <option value="Институт клинической медицины">Институт клинической медицины</option>
                    <option value="Институт профилактической медицины">Институт профилактической медицины</option>
                    <option value="НИИ кардиологии">НИИ кардиологии</option>
                    <option value="Институт эксперементальной медицины">Институт эксперементальной медицины</option>
                  </select>

                  <div class="my-4">
                    <p><b>Руководитель образовательного трека</b></p>
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
                        </select>
                        <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Добавить руководителя трека</button>  
                      </div>
                      <div id="authorWorld">
                        <textarea class="form-control mb-10" name="module-name" cols="80" rows="1" placeholder="Введите ФИО автора"></textarea>
                        <div class="d-flex mb-10">
                          <select class="form-control w-50 mr-10" name="regalia" size="1">
                            <option selected="" disabled="">Выберите регалии</option>
                            <option value="Профессор">Профессор</option>
                            <option value="К.М.Н.">К.М.Н.</option>
                            <option value="Доцент">Доцент</option>
                            <option value="Зав. кафедрой">Зав. кафедрой</option>
                          </select>
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
                                <button type="button" class="btn btn-outline-primary">Добавить руководителя трека</button>
                              </div>
                            </div>
                          </div>   
                        </div>                      
                      </div>
                    </div>
                  </div>

                  <div class="my-4">
                    <b>Название образовательного трека</b><br>
                    <textarea class="form-control" name="track-name" cols="80" rows="1" placeholder="Введите название образовательного трека"></textarea>
                  </div>

                  <div class="my-4">
                    <b>Аннотация образовательного трека</b><br>
                    <textarea class="form-control" id="track-annotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#" onclick="trackCheck()"></textarea>
                  </div>
                  <div class="my-4">
                    <b>Дополнительные ключевые слова</b> <br>
                    <textarea class="form-control" name="module-annotation" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив их ;"></textarea>
                  </div>                  
                </div>
                <div class="modal-footer">                
                  <button type="button" class="btn btn-primary">Добавить трек</button>
                </div>
              </div> 
            </div> 
          <!-- Заявка на Модуль -->
            <div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
              <div>
                <div class="d-flex justify-content-center">
                  <h5>Заявка на создание модуля</h5>                
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
                        <input class="form-check-input" type="checkbox" name="is-emsloyee" onclick="authorSamGMUModule()">
                        <label class="form-check-label">Автор является сотрудником СамГМУ</label>
                      </div>
                      <div class="hidden" id="authorSamGMUModule">
                        <select class="form-control mb-20" name="author">
                          <option>Выберите автора</option>
                          <option value="Иванов">Иванов</option>
                          <option value="Петров">Петров</option>
                          <option value="Сидоров">Сидоров</option>
                          <option value="Толстой">Толстой</option>
                        </select class="form-control">
                        <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Добавить автора</button>  
                      </div>
                      <div id="authorWorldModule">
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

                    <div class="accordion accordion-flush my-4" id="acModuleAdditionalGuide">
                      <div class="accordion-item">
                        <h2 class="accordion-header">
                          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseModule" aria-expanded="false" aria-controls="flush-collapseModule">
                            <b>Расширяемый перечень справочников для описания модуля</b>
                          </button>
                        </h2>
                        <div id="flush-collapseModule" class="accordion-collapse collapse" data-bs-parent="#acModuleAdditionalGuide">
                          <div class="accordion-body">
                            <div class="mt-10 mb-10">
                              <b>Добавить справочник для описания</b><br>
                              <div class="d-flex">
                                <select class="form-control w-75 mr-10" name="">
                                  <option select class="form-control"ed="" disabled="">Выберите справочник</option>
                                  <option value="Шить">МКБ</option>
                                  <option value="Брать кровь">Дисциплина/Учебный план</option>
                                </select>
                                <button type="button" class="btn btn-primary ml-10">Добавить</button>
                              </div>
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
                              <b>Ведение и состояния</b><br>
                              <div class="d-flex">
                                <select class="form-control w-75 mr-10" name="exfacts" size="1">
                                  <option select class="form-control"ed="" disabled="">Выберите этап</option>
                                  <option value="Солнце">Норма</option>
                                  <option value="Атмосферное давление">Этиология</option>
                                  <option value="Автомобиль">Пат.анатомия</option>
                                  <option value="Воздействие радиации">Диагностика</option>
                                  <option value="Атмосферное давление">Лечение</option>
                                  <option value="Автомобиль">Реабилитация</option>
                                </select class="form-control">
                                <button type="button" class="btn btn-primary ml-10"><i class="fas fa-plus"></i></button>
                              </div>
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
                          </div>
                        </div>
                      </div>
                    </div> 

                    <div class="form-check mt-10 mb-10">
                        <input class="form-check-input" type="checkbox" onclick="subshootingModule()">
                        <label class="form-check-label">Подсъёмки (при наличии)</label>
                      </div>

                    <div class="mt-10">                    
                      <textarea id="subsurveyModule" class="hidden form-control" cols="50" rows="2" placeholder="Краткое описание подсъёмки"></textarea>
                    </div>

                </div>
                <div class="modal-footer">                
                  <button type="button" class="btn btn-primary">Добавить модуль</button>
                </div>
              </div>
            </div>
          <!-- Заявка на Лекцию -->
            <div class="tab-pane fade" id="contact-tab-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="0">
              <div>
                <div class="d-flex justify-content-center">
                  <h5>Заявка на создание лекции</h5>                
                </div>                  
                <div class="modal-body"> 
                    <div class="mb-10">                    
                      <select class="form-control" class="form-control" name="track" size="1">
                        <option select class="form-control">Выберите образовательный трек</option>
                        <option value="Трек 1">Трек 1</option>
                        <option value="Трек 2">Трек 2</option>
                        <option value="Трек 3">Трек 3</option>
                        <option value="Трек 4">Трек 4</option>
                      </select class="form-control">
                    </div>

                    <div class="mb-10">                    
                      <select class="form-control" class="form-control" name="" size="1">
                        <option select class="form-control">Выберите образовательный модуль</option>
                        <option value="Трек 1">Модуль 1</option>
                        <option value="Трек 2">Модуль 2</option>
                        <option value="Трек 3">Модуль 3</option>
                        <option value="Трек 4">Модуль 4</option>
                      </select class="form-control">
                    </div>

                    <div class="mb-10">
                      <input type="text" class="form-control mb-2" id="headSeries" placeholder="Название образовательной лекции">                    
                    </div>
                    <div class="my-4">
                    <p><b>Автор образовательной лекции</b></p>
                    <div>
                      <div class="form-check mb-10">
                        <input class="form-check-input" type="checkbox" name="is-emsloyee" onclick="authorSamGMULecture()">
                        <label class="form-check-label">Автор является сотрудником СамГМУ</label>
                      </div>
                      <div class="hidden" id="authorSamGMULecture">
                        <select class="form-control mb-20" name="author">
                          <option>Выберите автора</option>
                          <option value="Иванов">Иванов</option>
                          <option value="Петров">Петров</option>
                          <option value="Сидоров">Сидоров</option>
                          <option value="Толстой">Толстой</option>
                        </select class="form-control">
                        <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Добавить автора</button>  
                      </div>
                      <div id="authorWorldLecture">
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
                        <div class="accordion accordion-flush mb-10" id="accordionDocsPerson">
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

                    <div class="accordion accordion-flush my-4" id="acLectureAdditionalGuide">
                      <div class="accordion-item">
                        <h2 class="accordion-header">
                          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseLecture" aria-expanded="false" aria-controls="flush-collapseLecture">
                            <b>Расширяемый перечень справочников для описания лекций</b>
                          </button>
                        </h2>
                        <div id="flush-collapseLecture" class="accordion-collapse collapse" data-bs-parent="#acLectureAdditionalGuide">
                          <div class="accordion-body">
                            <div class="mt-10 mb-10">
                              <b>Добавить справочник для описания</b><br>
                              <div class="d-flex">
                                <select class="form-control w-75 mr-10" name="">
                                  <option select class="form-control"ed="" disabled="">Выберите справочник</option>
                                  <option value="Шить">МКБ</option>
                                  <option value="Брать кровь">Дисциплина/Учебный план</option>
                                </select>
                                <button type="button" class="btn btn-primary ml-10">Добавить</button>
                              </div>
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
                              <b>Ведение и состояния</b><br>
                              <div class="d-flex">
                                <select class="form-control w-75 mr-10" name="exfacts" size="1">
                                  <option select class="form-control"ed="" disabled="">Выберите этап</option>
                                  <option value="Солнце">Норма</option>
                                  <option value="Атмосферное давление">Этиология</option>
                                  <option value="Автомобиль">Пат.анатомия</option>
                                  <option value="Воздействие радиации">Диагностика</option>
                                  <option value="Атмосферное давление">Лечение</option>
                                  <option value="Автомобиль">Реабилитация</option>
                                </select class="form-control">
                                <button type="button" class="btn btn-primary ml-10"><i class="fas fa-plus"></i></button>
                              </div>
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
                          </div>
                        </div>
                      </div>
                    </div>                    

                    <div class="form-check mt-10 mb-10">
                        <input class="form-check-input" type="checkbox" name="is-subsurvey" onclick="subshootingLecture()">
                        <label class="form-check-label">Подсъёмки (при наличии)</label>
                      </div>

                    <div class="mt-10">                    
                      <textarea id="subsurveyLecture" class="hidden form-control" cols="50" rows="2" placeholder="Краткое описание подсъёмки"></textarea>
                    </div>

                </div>
                <div class="modal-footer">                
                  <button type="button" class="btn btn-primary">Добавить лекцию</button>
                </div>
              </div>
            </div>                  
          </div>
        </div>
      </div>
    </div>

    <script src="js/jquery-3.2.1.slim.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script> 
    <script src="js/bootstrap.bundle.min.js"></script> 
    <script>
      function authorSamGMU() {
        let authorSamGMU = document.getElementById('authorSamGMU');
        authorSamGMU.classList.toggle("hidden");
        let authorWorld = document.getElementById('authorWorld');
        authorWorld.classList.toggle("hidden");
      }
      function authorSamGMUModule() {
        let authorSamGMUModule = document.getElementById('authorSamGMUModule');
        authorSamGMUModule.classList.toggle("hidden");
        let authorWorldModule = document.getElementById('authorWorldModule');
        authorWorldModule.classList.toggle("hidden");
      }
      function authorSamGMULecture() {
        let authorSamGMULecture = document.getElementById('authorSamGMULecture');
        authorSamGMULecture.classList.toggle("hidden");
        let authorWorldLecture = document.getElementById('authorWorldLecture');
        authorWorldLecture.classList.toggle("hidden");
      }

      function targetAudience() {
        let targetAudienceBlock = document.getElementById('targetAudienceBlock');
        targetAudienceBlock.classList.toggle("hidden");
        let targetAudienceUsers = document.getElementById('targetAudienceUsers');
        targetAudienceUsers.classList.toggle("text-primary");
      } 

      function subshootingModule() { 
        let subsurveyModule = document.getElementById('subsurveyModule');
        subsurveyModule.classList.toggle("hidden");
      }  
      function subshootingLecture() { 
        let subsurveyLecture = document.getElementById('subsurveyLecture');
        subsurveyLecture.classList.toggle("hidden");
      }    

      function trackSignature() {
        let networkWired = document.getElementById('networkWired');
        networkWired.classList.remove("bg-primary");
        networkWired.classList.add("bg-white");
        let iconNetworkWired = document.getElementById('iconNetworkWired');
        iconNetworkWired.classList.remove('text-white');
        let signature = document.getElementById('signature');
        signature.classList.remove("bg-white");
        signature.classList.add("bg-primary");
        let iconSignature = document.getElementById('iconSignature');
        iconSignature.classList.add('text-white');
      } 

      function trackCheck() {
         let signature = document.getElementById('signature');
        signature.classList.remove("bg-primary");
        signature.classList.add("bg-white");
        let iconSignature = document.getElementById('iconSignature');
        iconSignature.classList.add('text-white');
      }       
    </script> 

  </body>
</html>