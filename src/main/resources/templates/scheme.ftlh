<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
  <head>
    <meta charset="UTF-8">
    <title>3D timeline</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <link rel="stylesheet" href="https://track.samsmu.ru/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://track.samsmu.ru/css/styles.css">
    <link rel="stylesheet" href="https://track.samsmu.ru/css/select2.min.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/select2.min.css">

<#--    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />-->
<#--    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>-->


    <link rel="shortcut icon" href="#">
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
              <form method="post" action="/addTrack" id="createTrack" enctype="multipart/form-data">
                <div>
                  <div class="d-flex justify-content-center">
                    <h5 class="modal-title">Заявка на создание трека</h5>
                  </div>
                  <div class="modal-body">

                    <select class="form-control editable" name="university">
                      <option selected="" disabled="">Выберите институт</option>
                      <#list universities as university>
                        <option value="${university.universityId}">${university.universityName}</option>
                      </#list>
                    </select>


                    <div class="my-4">
                      <p><i class="fas fa-angle-double-right text-primary mr-10"></i><b>Руководитель образовательного трека</b></p>
                      <div>
                        <div class="form-check mb-10">
                          <input class="form-check-input" type="checkbox" name="is-emsloyee"  onclick="authorSamGMU()">
                          <label class="form-check-label">Автор является сотрудником СамГМУ</label>
                        </div>
                        <div class="hidden" id="authorSamGMU">
                          <select class="js-data-example-ajax form-control mb-10" name="author" onchange="selectAuthor(this.value)" id="select"></select>
<#--                          <button type="button" class="btn btn-primary" onclick="addManTrack()"><i class="fas fa-plus"></i></button>-->
                        </div>

                        <div id="authorWorld">
                          <input class="form-control mb-10" type="text" id="inputNameAuthor"  placeholder="Введите ФИО автора"></input>
                          <div class="d-flex mb-10">
                            <select class="form-control w-75 mr-10" id="regalia" size="1" onclick="selectReg(this.value)">
                              <option  disabled>Выберите регалии</option>
                              <option>Профессор</option>
                              <option>К.М.Н.</option>
                              <option>Доцент</option>
                              <option>Зав. кафедрой</option>
                            </select>
                          </div>

                          <div class="d-flex" id="addRegalii">
                          </div>

                          <button type="button" class="btn btn-primary mt-3" id="createAuthorButton">
                            <i class="fas fa-plus"></i>
                          </button>
                        </div>

                        <div>
                          <table class="table table-hover editable" id="tableAuthor" name="authors">
                            <tr>
                              <th class="nameTd">ФИО автора</th>
                              <th class="text-center regTd">Должность, степень, звание</th>
                              <th class="text-center checkTd">Руководитель трека</th>
                              <th class="text-center docTd">Документы</th>
                              <th class="text-center removeTd"><i class="fas fa-trash-alt"></i></th>
                            </tr>
                          </table>
                        </div>
                      </div>
                    </div>

                    <div class="my-4">
                      <b>Название образовательного трека</b><br>
                      <textarea class="form-control editable" id="trackInput" name="trackName" cols="80" rows="1" oninput="updateTrackHeading()" placeholder="Введите название образовательного трека"></textarea>
                    </div>

                    <div class="my-4">
                      <b>Аннотация образовательного трека</b><br>
                      <textarea class="form-control editable" name="trackAnnotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#" onclick="trackCheck()"></textarea>
                    </div>
                    <div class="my-4">
                      <b>Дополнительные ключевые слова</b> <br>
                      <textarea class="form-control editable" name="trackKeyWords" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив их ;"></textarea>
                    </div>
                  </div>

                </div>
                <div class="tab-pane fade show active h-100" id="pills-network-wired" role="tabpanel" aria-labelledby="pills-network-wired-tab" tabindex="0">
                  <div class="my-3 rounded p-3" style="border: 1px solid #e9e9e9; background: #e9e9e9;">
                    <div class="d-flex justify-content-end">
                      <button type="button" class="btn btn-sm rounded px-3" style="border: 1px solid #e9e9e9" data-bs-toggle="modal" data-bs-target="#addContentModal"><i class="fas fa-plus"></i></button>
                    </div>
                    <div class="d-flex justify-content-center">
                      <h5 id="trackHeading" class="text-center m-0 p-0 flex-nowrap w-75">Образовательный трек</h5>
                    </div>
                  </div>

                  <div class="modal fade" id="addContentModal" tabindex="-1" aria-labelledby="addContentModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
                        </div>
                        <div class="modal-body">
                          <div class="d-flex justify-content-center mb-3"><h5>Выберите тип добавляемого контента</h5></div>
                          <div class="d-flex justify-content-center">

<#--                            <button type="button" class="btn btn-primary mx-2" onclick="addModuleScheme()" data-bs-dismiss="modal">Модуль</button>-->
                            <button type="button" class="btn btn-primary m-2" onclick="addSeriesBegin()" data-bs-dismiss="modal">Серия</button>
                            <button type="button" class="btn btn-primary m-2" onclick="addLectureModalBegin()" data-bs-dismiss="modal">Лекция</button>
<#--                            <button type="button" class="btn btn-primary mx-2" onclick="addLectureScheme()" data-bs-dismiss="modal">Лекция</button>-->
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="d-flex rowScheme" id="rowScheme" style="position:relative;">
                    <div id="rowModule" class="d-flex flex-wrap">
<#--                      <div class="mx-3">-->
<#--                        <div class="bg-primary rounded p-3 d-flex">-->
<#--                          <div class="col-11">-->
<#--                            <p class="text-center text-white fs16 pb-0 mb-0">Модуль 1</p>-->
<#--                          </div>-->
<#--                          <div class="col-1">-->
<#--                            <button type="button" class="btn btn-sm rounded"><i class="fas fa-plus text-white" onclick="addLecInModule(this)"></i></button>-->
<#--                          </div>-->
<#--                        </div>-->
<#--                        <div class="d-flex justify-content-between my-2 rowLecInModule">-->
<#--                          <div class="bg-success m-3 p-3 rounded lectureBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 1</p></div>-->
<#--                          <div class="bg-success m-3 p-3 rounded lectureBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 2</p></div>-->
<#--                          <div class="bg-success m-3 p-3 rounded lectureBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 3</p></div>-->
<#--                        </div>-->
<#--                      </div>-->
                    </div>

                    <div class="d-flex mx-2 flex-wrap" id="colLec">

<#--                      <div class="bg-success mx-2 p-3 mb-1 rounded lectureBlockScheme lecBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 1 Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p></div>-->
<#--                      <div class="bg-success mx-2 p-3 mb-1 rounded lectureBlockScheme lecBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 2thgyluiuhgfghj sdafhkl;dsfatryiltrfdcvbnjkiuytf</p></div>-->
<#--                      <div class="bg-success mx-2 p-3 mb-1 rounded lectureBlockScheme lecBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 3 Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod</p></div>-->


                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="submit" id="submitButton" class="btn btn-primary">Добавить трек</button>
                    <button type="submit" id="editTrack" class="btn btn-primary hidden">Редактировать трек</button>
                  </div>
                </div>
              </form>
            </div>
            <!-- Заявка на Модуль всплывающая для дерева -->
            <div class="modal-wrapper">
              <div class="lectureModal shadow hidden" id="addSeriesModal" name="modalSeries">
                <div class="modal-header">
                  <h5>Добавить Модуль</h5>
                  <button type="button" class="btn-close" onclick="btnClose()"></button>
                </div>
                <div class="modal-content">
                  <div class="modal-body">

                    <div>
                      <div class="d-flex justify-content-center">
                        <h5>Заявка на создание модуля</h5>
                      </div>
                      <div class="modal-body">
                        <div class="mb-10">
                          <p id="trackHeadingInModule" class="m-0 p-0 flex-nowrap w-75">Образовательный трек</p>
                        </div>

                        <div class="mb-10">
                          <input type="text" class="form-control mb-2" id="headSeries" placeholder="Название образовательного модуля">
                        </div>

                        <div class="my-4">
                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Аннотация/содержание образовательного модуля</b><br>
                          <textarea class="form-control" id="moduleModalAnnotation" name="track-annotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>
                        </div>

                        <div class="mt-10 mb-10">
                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Дополнительные ключеные слова</b><br>
                          <textarea class="form-control" id="moduleModalKeyWords" name="module-annotation" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;"></textarea>
                        </div>

                      </div>
                    </div>

                  </div>
                  <div class="modal-footer">
                    <!-- Кнопка сохранить-->
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="saveModule" onclick="addModuleScheme(); saveSeries()">Добавить серию</button>
                    <button type="button" class="btn btn-primary hidden" data-bs-dismiss="modal" id="editModule">Сохранить серию</button>
                  </div>
                </div>
              </div>
            </div>
            <!-- Заявка на Лекцию в Модуле всплывающая для дерева -->
            <div class="modal-wrapper">
              <div class="lectureModal shadow hidden" id="addLectureInModule">
                <div class="modal-header">
                  <h5>Добавить лекцию</h5>
                  <button type="button" class="btn-close" onclick="btnClose()"></button>
                </div>
                <div class="modal-content">
                  <div class="modal-body">

                    <div>
                      <div class="d-flex justify-content-center">
                        <h5>Заявка на создание лекции</h5>
                      </div>
                      <div class="modal-body">

                        <div class="mb-10">
                          <p id="trackHeadingInLecture" class="m-0 p-0 flex-nowrap w-75">Образовательный трек</p>
                        </div>

                        <div class="mb-10">
                          <p id="moduleHeadingInLecture" class="m-0 p-0 flex-nowrap w-75">Образовательный модуль</p>
                        </div>

                        <div class="mb-10">
                          <input type="text" class="form-control mb-2" id="lectureModuleName" placeholder="Название образовательной лекции">
                        </div>

                        <div class="my-4">
                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Аннотация/содержание образовательной лекции</b><br>
                          <textarea class="form-control" id="lectureModuleAnnotation" name="track-annotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>
                        </div>

                        <div class="mt-10 mb-10">
                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Дополнительные ключеные слова</b><br>
                          <textarea class="form-control" id="lectureModuleKeyWords" name="module-annotation" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;"></textarea>
                        </div>

                      </div>
                    </div>

                  </div>
                  <div class="modal-footer">
                    <!-- Кнопка сохранить-->
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="saveLectureInModule">Добавить лекцию</button> <!--onclick="addLecInModule()-->
                    <button type="button" class="btn btn-primary hidden" data-bs-dismiss="modal" id="editLectureInModule">Сохранить лекцию</button> <!--onclick="addLecInModule()-->
                  </div>
                </div>
              </div>
            </div>
          <!-- Заявка на Лекцию всплывающая для дерева -->
            <div class="modal-wrapper">
              <div class="lectureModal shadow hidden" id="addLectureModal">
                <div class="modal-header">
                  <h5>Добавить лекцию</h5>
                  <button type="button" class="btn-close" onclick="btnClose()"></button>
                </div>
                <div class="modal-content">
                  <div class="modal-body">

                    <div>
                      <div class="d-flex justify-content-center">
                        <h5>Заявка на создание лекции</h5>
                      </div>
                      <div class="modal-body">

                        <div class="mb-10">
                          <p id="trackHeadingInLectureOutOfModule" class="m-0 p-0 flex-nowrap w-75">Образовательный трек</p>
                        </div>

                        <div class="mb-10">
                          <input type="text" class="form-control mb-2" id="lectureModalName" placeholder="Название образовательной лекции">
                        </div>

                        <div class="my-4">
                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Аннотация/содержание образовательной лекции</b><br>
                          <textarea class="form-control" id="lectureModalAnnotation" name="track-annotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>
                        </div>

                        <div class="mt-10 mb-10">
                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Дополнительные ключеные слова</b><br>
                          <textarea class="form-control" id="lectureModalKeyWords" name="module-annotation" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;"></textarea>
                        </div>

                      </div>
                    </div>

                  </div>
                  <div class="modal-footer">
                    <!-- Кнопка сохранить-->
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="saveLectureOut" onclick="addLectureScheme()">Добавить лекцию</button>
                    <button type="button" class="btn btn-primary hidden" data-bs-dismiss="modal" id="editLecture">Сохранить лекцию</button>
                  </div>
                </div>
              </div>
            </div>

            <!-- Заявка на Модуль -->
            <div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
              <form method="post" action="/addSeries" enctype="multipart/form-data">
              <div>
                <div class="d-flex justify-content-center">
                  <h5>Заявка на создание модуля</h5>                
                </div>                  
                <div class="modal-body"> 
                    <div class="mb-10">                    
                      <select class="form-control" class="form-control" name="seriesTrack" size="1">
                        <option select class="form-control">Выберите образовательный трек</option>
                        <#list tracks as track>
                          <option value="${track.id}">${track.trackName}</option>
                        </#list>
                      </select class="form-control">
                    </div>
                    <div class="mb-10">
                      <input type="text" class="form-control mb-2" name="seriesName" placeholder="Название образовательного модуля">
                    </div>
                    <div class="my-4">
                    <p><b>Автор образовательного модуля</b></p>
                    <div>
                      <div class="form-check mb-10">
                        <input class="form-check-input" type="checkbox" name="is-emsloyee" onclick="authorSamGMUModule()">
                        <label class="form-check-label">Автор является сотрудником СамГМУ</label>
                      </div>
                      <div class="hidden" id="authorSamGMUModule">
                        <select class="form-control mb-20" name="seriesAuthor">
                          <option>Выберите автора</option>
<#--                          <#list authors as author>-->
<#--                            <option value="${author.authorId}">${author.lastName} ${author.firstName} ${author.middleName}</option>-->
<#--                          </#list>-->
                        </select>
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
                      <textarea class="form-control" name="seriesAnnotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>
                    </div>

                    <div class="mt-10 mb-10">
                      <b>Дополнительные ключеные слова</b><br>
                      <textarea class="form-control" name="seriesKeyWords" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;"></textarea>
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
                              <input type="text" id="myInput" class="form-control mt-10 mb-10" onkeyup="filterOptions()" placeholder="Параметры поиска...">
                              <select id="mySelect" class="form-control" name="mkb" size="1">
                                <option select class="form-control"ed="" disabled="">Выберите значение</option>
                                <#list mkb10 as mkb>
                                  <option value="${mkb.id}">${mkb.code} ${mkb.name} </option>
                                </#list>
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
                  <button type="submit" class="btn btn-primary">Добавить модуль</button>
                </div>
              </div>
              </form>
            </div>
            <!-- Заявка на Лекцию -->
            <div class="tab-pane fade" id="contact-tab-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="0">
              <form method="post" action="/addLecture" enctype="multipart/form-data">
              <div>

                  <div class="d-flex justify-content-center">
                    <h5>Заявка на создание лекции</h5>
                  </div>
                  <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Добавить лекцию</button>
                  </div>

              </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <script src="https://track.samsmu.ru/js/select2.min.js"></script>
    <script src="https://track.samsmu.ru/js/popper.min.js"></script>
    <script src="https://track.samsmu.ru/js/bootstrap.min.js"></script>
    <script src="https://track.samsmu.ru/js/bootstrap.bundle.min.js"></script>
    <script src="https://track.samsmu.ru/js/index.js"></script>
    <script src="https://track.samsmu.ru/js/track.js"></script>
    <script src="https://track.samsmu.ru/js/users.js"></script>
    <script src="https://track.samsmu.ru/js/pdfobject.min.js"></script>
    <script src="https://track.samsmu.ru/js/pdf.js"></script>
    <script src="https://track.samsmu.ru/js/pdf.worker.js"></script>
<#--    <script src="https://track.samsmu.ru/js/app.js" type="module"></script>-->
    <script src="js/jquery-3.7.0.min.js"></script>
    <script src="js/select2.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/index.js"></script>
    <script src="js/track.js"></script>
    <script src="js/users.js"></script>
    <script src="js/pdfobject.min.js"></script>
    <script src="js/pdf.js"></script>
    <script src="js/pdf.worker.js"></script>
<#--    <script src="js/app.js" type="module"></script>-->

    <script>

      //изменить "редактировать" на "сохранить" у submit button для трека

      document.addEventListener("keyup", function(event) {
        let editTrack = document.getElementById('editTrack');
        if (!editTrack.classList.contains('hidden')){
        let inputValue = event.target.value; // Получаем значение поля ввода
        console.log(inputValue);
        // Проверяем, является ли целевой элемент полем ввода
        if (event.target.classList.contains("editable")) {
          // Меняем название кнопки в зависимости от значения поля ввода
          console.log('является');
          editTrack.textContent = "Сохранить трек"
          }
        }
      });

      function authorSamGMU() {
        let authorSamGMU = document.getElementById('authorSamGMU');
        authorSamGMU.classList.toggle("hidden");
        let authorWorld = document.getElementById('authorWorld');
        authorWorld.classList.toggle("hidden");
        let createAuthorButton = document.getElementById('createAuthorButton');
        createAuthorButton.classList.toggle("hidden");
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

      function addManTrack() {
        document.getElementById('select').options[0].selected = true; //очищаем селект
      }

      $(document).on('click', '.fa-times', function() {
        $(this).closest('.divDoc').remove();
        // let authorId = $(this).getAttribute("authorindex");
        // console.log(authorId);
      });

      $(document).ready(function() {
        $(".js-data-example-ajax").select2({
          ajax: {
            // url: "https://track.samsmu.ru/api/author/authors",
            url: "http://localhost:8081/api/author/authors",
            type: 'GET',
            dataType: 'json',
            delay: 250,
            data: function (params) {
              return {
                q: params.term, // search term
                page: params.page
              };
            },
            processResults: function (data) {
              var authors = data.map(function (author) {
                return {id: author.id, text: author.lastName + ' ' + author.firstName + ' ' + author.middleName, academicDegreeName: author.academicDegreeName};
              });
              return {
                results: authors
              };
            },
            transport: function (params, success, failure) {
              var $request = $.ajax(params);
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


      const createAuthorButton = document.querySelector('#createAuthorButton');
      createAuthorButton.addEventListener('click', inputNameAuthor);

      const pageSize = 10;
      const options = document.querySelectorAll('#select');
      const pages = [];
      for (let i = 0; i < options.length; i += pageSize) {
        pages.push(Array.from(options).slice(i, i + pageSize));
      }
      console.log(options.length);
      let savedTrack = null;
      const form = document.querySelector('#createTrack');
      form.addEventListener('submit', (event) => {
        event.preventDefault(); // остановить стандартное поведение формы
        const university = form.university.value;
        const trackName = form.trackName.value;
        const trackAnnotation = form.trackAnnotation.value;
        const trackKeyWords = form.trackKeyWords.value;
        console.log(university);

        const data = {
          authors,
          university,
          trackName,
          trackAnnotation,
          trackKeyWords,
          modules,
          lectures
        };
        if (savedTrack) {
          data.trackId = savedTrack;
        }
        // let formData = new FormData();
        // formData.append('passportFile', authors[0].passport);

        // data.passportFile = formData;
        console.log(data);

        fetch("/addTrack2", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(data)
        })
                .then(response => response.json())
                .then(data => {
                  const newTrackId = data.id;
                  savedTrack = data.id;
                  // Используйте полученный ID нового трека для нужных действий на фронтенде
                  console.log('ID нового трека:', newTrackId);
                })
                .catch(error => {
                  console.error('Ошибка при отправке запроса:', error);
                });
        let addTrack = document.getElementById('submitButton');
        addTrack.classList.add("hidden");
        let editTrack = document.getElementById('editTrack');
        editTrack.classList.remove("hidden");
      });

      function handleSubmit(event) {
        // Отменяем стандартное поведение формы
        event.preventDefault();

        // Очищаем поля формы
        document.getElementById("createTrack").reset();

        // Другие действия по обработке отправки формы...
      }


      function inputNameAuthor() {
        let tableAuthor = document.getElementById('tableAuthor');

        let divDoc = document.createElement('tr');
        divDoc.classList.add('divDoc');
        tableAuthor.append(divDoc);

        let divDocTd = document.createElement('td');
        divDocTd.setAttribute('colspan', '5');
        divDoc.append(divDocTd);

        let tr1 = document.createElement('table');
        tr1.classList.add('w-100', 'tr1');
        divDocTd.append(tr1);

        let inputNameAuthor = document.createElement('tr');
        tr1.append(inputNameAuthor);
        let inputNameAuthorTd = document.createElement('td');

        let x = document.getElementById('inputNameAuthor').value;
        inputNameAuthorTd.innerHTML = x;
        inputNameAuthorTd.classList.add('text-primary', 'nameTd');
        inputNameAuthor.append(inputNameAuthorTd);
        document.getElementById('inputNameAuthor').value = ' ';
        console.log(inputNameAuthorTd.textContent);

        let prof = document.createElement('td');
        let regalia = document.getElementById('regalia').value;
        prof.innerHTML = regalia;
        prof.classList.add('regTd', 'text-center');
        inputNameAuthor.append(prof);
        console.log(regalia);
        let author = new Author(null, inputNameAuthorTd.textContent, regalia, false);
        authors.push(author);
        console.log(authors);

        let checkTd = document.createElement('td');
        checkTd.classList.add('checkTd');
        inputNameAuthor.append(checkTd);
        let radioBtn = document.createElement('input');
        radioBtn.setAttribute('type','radio');
        radioBtn.setAttribute('name','flexRadioDefault');
        radioBtn.setAttribute('onclick','AuthorIsSupervisor(this)');
        radioBtn.setAttribute('id','isSupervisor');
        radioBtn.setAttribute('authorIndex', (authors.length - 1).toString());
        radioBtn.classList.add('form-check-input');
        checkTd.append(radioBtn);

        let docTd = document.createElement('td');
        docTd.setAttribute('onclick','showDoc(this)');
        docTd.classList.add('text-center', 'docTd');
        inputNameAuthor.append(docTd);
        let docTdIcon = document.createElement('i');
        docTdIcon.classList.add('fas', 'fa-file-upload', 'text-primary');
        docTd.append(docTdIcon);

        let removeTd = document.createElement('td');
        removeTd.classList.add('removeTd', 'text-center');
        inputNameAuthor.append(removeTd);
        let close = document.createElement('i');
        close.classList.add('fas', 'fa-times', 'text-danger');
        close.setAttribute('id', 'removeAuthorFromTable');
        close.setAttribute('onclick','removeAuthorFromArray(this)');
        close.setAttribute('authorIndex', (authors.length - 1).toString());
        removeTd.append(close);

        //Строка для добавления документов (скрытая)
        let trDoc = document.createElement('tr');
        trDoc.classList.add('hidden', 'trDoc','mt-10');
        trDoc.setAttribute('id', 'trDoc');
        tr1.append(trDoc);

        let tdDoc = document.createElement('td');
        tdDoc.setAttribute('colspan','5');
        trDoc.append(tdDoc);

        let passport = document.createElement('div');
        passport.classList.add('mb-10');
        tdDoc.append(passport);
        let passportLabel = document.createElement('b');
        passportLabel.textContent = 'Загрузите паспорт';
        passport.append(passportLabel);
        let br = document.createElement('br');
        passportLabel.append(br);
        let trDocPassport = document.createElement('div');
        trDocPassport.classList.add('d-flex');
        passport.append(trDocPassport);

        let passportInput = document.createElement('input');
        passportInput.classList.add('form-control', 'mr-10', 'w-75');
        passportInput.setAttribute('type', 'file');
        trDocPassport.append(passportInput);

        let btnUpload = document.createElement('button');
        btnUpload.classList.add('btn', 'btn-primary', 'ml-10');
        btnUpload.setAttribute('type', 'button');
        trDocPassport.append(btnUpload);
        let uploadIcon = document.createElement('i');
        uploadIcon.classList.add('fas', 'fa-file-upload');
        btnUpload.append(uploadIcon);

        let diplom = document.createElement('div');
        diplom.classList.add('mb-10');
        tdDoc.append(diplom);
        let diplomLabel = document.createElement('b');
        diplomLabel.textContent = 'Загрузите диплом о в/о';
        diplom.append(diplomLabel);
        let brDip = document.createElement('br');
        diplomLabel.append(brDip);
        let trDocDiplom = document.createElement('div');
        trDocDiplom.classList.add('d-flex');
        diplom.append(trDocDiplom);
        let diplomInput = document.createElement('input');
        diplomInput.classList.add('form-control', 'mr-10', 'w-75');
        diplomInput.setAttribute('type', 'file');
        trDocDiplom.append(diplomInput);
        let btnUploadDip = document.createElement('button');
        btnUploadDip.classList.add('btn', 'btn-primary', 'ml-10');
        btnUploadDip.setAttribute('type', 'button');
        trDocDiplom.append(btnUploadDip);
        let uploadIconDip = document.createElement('i');
        uploadIconDip.classList.add('fas', 'fa-file-upload');
        btnUploadDip.append(uploadIconDip);

        let rank = document.createElement('div');
        rank.classList.add('mb-10');
        tdDoc.append(rank);
        let rankLabel = document.createElement('b');
        rankLabel.textContent = 'Загрузите диплом о научном звании';
        rank.append(rankLabel);
        let brRank = document.createElement('br');
        rankLabel.append(brRank);
        let trDocRank = document.createElement('div');
        trDocRank.classList.add('d-flex');
        rank.append(trDocRank);
        let rankInput = document.createElement('input');
        rankInput.classList.add('form-control', 'mr-10', 'w-75');
        rankInput.setAttribute('type', 'file');
        trDocRank.append(rankInput);
        let btnUploadRank = document.createElement('button');
        btnUploadRank.classList.add('btn', 'btn-primary', 'ml-10');
        btnUploadRank.setAttribute('type', 'button');
        trDocRank.append(btnUploadRank);
        let uploadIconRank = document.createElement('i');
        uploadIconRank.classList.add('fas', 'fa-file-upload');
        btnUploadRank.append(uploadIconRank);

        let degree = document.createElement('div');
        degree.classList.add('mb-10');
        tdDoc.append(degree);
        let degreeLabel = document.createElement('b');
        degreeLabel.textContent = 'Загрузите диплом о научной степени';
        degree.append(degreeLabel);
        let brDegree = document.createElement('br');
        degreeLabel.append(brDegree);
        let trDocDegree = document.createElement('div');
        trDocDegree.classList.add('d-flex');
        degree.append(trDocDegree);
        let degreeInput = document.createElement('input');
        degreeInput.classList.add('form-control', 'mr-10', 'w-75');
        degreeInput.setAttribute('type', 'file');
        trDocDegree.append(degreeInput);
        let btnUploadDegree = document.createElement('button');
        btnUploadDegree.classList.add('btn', 'btn-primary', 'ml-10');
        btnUploadDegree.setAttribute('type', 'button');
        trDocDegree.append(btnUploadDegree);
        let uploadIconDegree = document.createElement('i');
        uploadIconDegree.classList.add('fas', 'fa-file-upload');
        btnUploadDegree.append(uploadIconDegree);

        let criminal = document.createElement('div');
        criminal.classList.add('mb-10');
        tdDoc.append(criminal);
        let criminalLabel = document.createElement('b');
        criminalLabel.textContent = 'Загрузите справку об отсутствии судимости';
        criminal.append(criminalLabel);
        let brCriminal = document.createElement('br');
        criminalLabel.append(brCriminal);
        let trDocCriminal = document.createElement('div');
        trDocCriminal.classList.add('d-flex');
        criminal.append(trDocCriminal);
        let criminalInput = document.createElement('input');
        criminalInput.classList.add('form-control', 'mr-10', 'w-75');
        criminalInput.setAttribute('type', 'file');
        trDocCriminal.append(criminalInput);
        let btnUploadCriminal = document.createElement('button');
        btnUploadCriminal.classList.add('btn', 'btn-primary', 'ml-10');
        btnUploadCriminal.setAttribute('type', 'button');
        trDocCriminal.append(btnUploadCriminal);
        let uploadIconCriminal = document.createElement('i');
        uploadIconCriminal.classList.add('fas', 'fa-file-upload');
        btnUploadCriminal.append(uploadIconCriminal);

        let health = document.createElement('div');
        health.classList.add('mb-10');
        tdDoc.append(health);
        let healthLabel = document.createElement('b');
        healthLabel.textContent = 'Загрузите справку о состоянии здоровья';
        health.append(healthLabel);
        let brHealth = document.createElement('br');
        healthLabel.append(brHealth);
        let trDocHealth = document.createElement('div');
        trDocHealth.classList.add('d-flex');
        health.append(trDocHealth);
        let healthInput = document.createElement('input');
        healthInput.classList.add('form-control', 'mr-10', 'w-75');
        healthInput.setAttribute('type', 'file');
        trDocHealth.append(healthInput);
        let btnUploadHealth = document.createElement('button');
        btnUploadHealth.classList.add('btn', 'btn-primary', 'ml-10');
        btnUploadHealth.setAttribute('type', 'button');
        trDocHealth.append(btnUploadHealth);
        let uploadIconHealth = document.createElement('i');
        uploadIconHealth.classList.add('fas', 'fa-file-upload');
        btnUploadHealth.append(uploadIconHealth);

        let experience = document.createElement('div');
        experience.classList.add('mb-10');
        tdDoc.append(experience);
        let experienceLabel = document.createElement('b');
        experienceLabel.textContent = 'Загрузите последнюю страницу трудовой книжки';
        experience.append(experienceLabel);
        let brExperience = document.createElement('br');
        experienceLabel.append(brExperience);
        let trDocExperience = document.createElement('div');
        trDocExperience.classList.add('d-flex');
        experience.append(trDocExperience);
        let experienceInput = document.createElement('input');
        experienceInput.classList.add('form-control', 'mr-10', 'w-75');
        experienceInput.setAttribute('type', 'file');
        trDocExperience.append(experienceInput);
        let btnUploadExperience = document.createElement('button');
        btnUploadExperience.classList.add('btn', 'btn-primary', 'ml-10');
        btnUploadExperience.setAttribute('type', 'button');
        trDocExperience.append(btnUploadExperience);
        let uploadIconExperience = document.createElement('i');
        uploadIconExperience.classList.add('fas', 'fa-file-upload');
        btnUploadExperience.append(uploadIconExperience);
        //

        document.getElementById('regalia').options[0].selected = true; //очищаем селект
        let badge = document.querySelectorAll('.badgeProf');
        for( let i = 0; i < badge.length; i++ ){
          badge[i].outerHTML = "";
        }

      }


      function selectReg(el) {
        let addRegalii = document.getElementById('addRegalii');
        let value = regalia.value;
        let text = regalia.options[regalia.selectedIndex].text;
        let nameProf = document.createElement('div');
        nameProf.classList.add('text-white', 'my-3', 'mr-10', 'badge', 'bg-primary', 'd-flex', 'rounded-pill', 'badgeProf')
        nameProf.textContent = text;
        addRegalii.append(nameProf);
        let closeIcon = document.createElement('i');
        closeIcon.classList.add('far', 'fa-times-circle', 'ml-10', 'closeBadge','text-white', 'cursor-pointer');
        nameProf.append(closeIcon);
      }

      function showDoc(el) {
        var tbl = el.parentElement.parentElement;
        tbl.rows[1].classList.toggle('hidden');
      }



      function removeAuthorFromArray(el) {
        var index = el.getAttribute("authorIndex");
        console.log(index, 'на удаление индекс')
        if (index !== -1) {
          authors.splice(index, 1);
        }
      }
      // const buttonAuthorIsSupervisor = document.querySelector('#isSupervisor');
      function AuthorIsSupervisor(el) {
        // Предполагая, что у вас есть доступ к данных автора, например, через data-атрибуты
        var authorId = el.getAttribute("authorindex");
        console.log(authorId, 'функция AuthorIsSupervisor');
        // Вызываем функцию removeAuthor() с передачей идентификатора автора для удаления
        setAuthorSupervisor(authorId);
        // Обновляем интерфейс пользователя, чтобы отразить изменения, например, перерисовываем таблицу
        // redrawTable();
      };


      function setAuthorSupervisor(authorId) {
        console.log(authorId, 'authorId');
        for (let i = 0; i < authors.length; i++) {
          if (i === parseInt(authorId)) {
            authors[i].isSupervisor = true;
            console.log(authors[i].isSupervisor, 'isSupervisor');
          } else {
            authors[i].isSupervisor = false;
          }
        }
      }



      //удаление текущего элемента
      $(document).on('click', '.badgeProf', function() {
        $(this).remove();
      });

    </script>
<#--    Фильтр для МКБ-->
    <script>
      function filterOptions() {
        // Получаем элементы
        var input, filter, select, option, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        select = document.getElementById("mySelect");
        option = select.getElementsByTagName("option");

        // Проходим по всем опциям и скрываем те, которые не соответствуют поиску
        for (i = 0; i < option.length; i++) {
          txtValue = option[i].textContent || option[i].innerText;
          if (txtValue.toUpperCase().indexOf(filter) > -1) {
            option[i].style.display = "";
          } else {
            option[i].style.display = "none";
          }
        }
      }
    </script>

    <script>
      function filterOptions() {
        // Получаем элементы
        var input, filter, select, option, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        select = document.getElementById("mySelect");
        option = select.getElementsByTagName("option");

        // Проходим по всем опциям и скрываем те, которые не соответствуют поиску
        for (i = 0; i < option.length; i++) {
          txtValue = option[i].textContent || option[i].innerText;
          if (txtValue.toUpperCase().indexOf(filter) > -1) {
            option[i].style.display = "";
          } else {
            option[i].style.display = "none";
          }
        }
      }
    </script>

  </body>
</html>