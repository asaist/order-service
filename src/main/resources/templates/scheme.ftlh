<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
  <head>
    <meta charset="UTF-8">
    <title>3D timeline</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
<#--    <link rel="stylesheet" href="https://track.samsmu.ru/css/bootstrap.min.css">-->
<#--    <link rel="stylesheet" href="https://track.samsmu.ru/css/styles.css">-->
<#--    <link rel="stylesheet" href="https://track.samsmu.ru/css/select2.min.css">-->
    <link rel="stylesheet" href="https://dev.track.samsmu.ru/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://dev.track.samsmu.ru/static/css/styles.css">
    <link rel="stylesheet" href="https://dev.track.samsmu.ru/static/css/select2.min.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/styles.css">
    <link rel="stylesheet" href="../css/select2.min.css">

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
        <#if !profileTab>
        <ul class="nav nav-pills" id="myTab" role="tablist">
            <li class="nav-item" role="presentation">
              <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Трек</button>
            </li>

            <li class="nav-item hidden" role="presentation" id="moduleHideSeries">
              <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Модуль</button>
            </li>
            <li class="nav-item hidden" role="presentation">
              <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Лекция</button>
            </li>

<#--          <li class="nav-item" role="presentation">-->
<#--            <button class="nav-link px-4" id="structure-tab" onclick="location.href='table_track';" type="button" aria-selected="false">Структура</button>-->
<#--          </li>-->
        </ul>
        <#elseif !moduleFlag>
        <ul class="nav nav-pills" id="myTab" role="tablist">
          <li class="nav-item hidden" role="presentation">
            <button class="nav-link" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="false">Трек</button>
          </li>
          <li class="nav-item" role="presentation" id="moduleHideSeries">
            <button class="nav-link active" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="true">Модуль</button>
          </li>
          <li class="nav-item hidden" role="presentation">
            <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Лекция</button>
          </li>
        </ul>
        <#elseif !lectureFlag>
          <ul class="nav nav-pills" id="myTab" role="tablist">
            <li class="nav-item hidden" role="presentation">
              <button class="nav-link" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="false">Трек</button>
            </li>
            <li class="nav-item hidden" role="presentation" id="moduleHideSeries">
              <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Модуль</button>
            </li>
            <li class="nav-item" role="presentation">
              <button class="nav-link active" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="true">Лекция</button>
            </li>
          </ul>
        <#else>
        <ul class="nav nav-pills" id="myTab" role="tablist">
<#--          <li class="nav-item" role="presentation">-->
<#--            <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Трек</button>-->
<#--          </li>-->
          <li class="nav-item" role="presentation" id="moduleHideSeries">
            <button class="nav-link active" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Курс лекций</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Лекция</button>
          </li>
        </ul>
        </#if>









        <div class="applicationBodySchema">         
          <div class="tab-content" id="myTabContent">

           <!-- Заявка на Трек -->

<#--            <div class="tab-pane fade <#if !moduleFlag || !lectureFlag> <#else>show active</#if>" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">-->
<#--              <form method="post" action="/addTrack" id="createTrack" enctype="multipart/form-data">-->
<#--                <div>-->
<#--                  <#if profileTab>-->
<#--                    <div class="d-flex justify-content-center">-->
<#--                      <h5 class="modal-title">Заявка на создание трека</h5>-->
<#--                    </div>-->
<#--                  <#else>-->
<#--                    <div class="d-flex justify-content-center">-->
<#--                      <h5 class="modal-title">Редактирование трека</h5>-->
<#--                    </div>-->
<#--                  </#if>-->
<#--                  <div class="modal-body">-->

<#--                    <select class="form-control editable" name="university">-->
<#--                      <#if profileTab>-->
<#--                        <option selected="" disabled="">Выберите институт</option>-->
<#--                      <#else>-->
<#--                        <option selected="" disabled="" value="${track.getUniversity().universityId}">${track.universityName}</option>-->
<#--                      </#if>-->
<#--                      <#list universities as university>-->
<#--                        <option value="${university.universityId}">${university.universityName}</option>-->
<#--                      </#list>-->
<#--                    </select>-->


<#--                    <div class="my-4">-->
<#--                      <p><i class="fas fa-angle-double-right text-primary mr-10"></i><b>Руководитель образовательного трека</b></p>-->
<#--                      <div>-->
<#--                        <div class="form-check mb-10">-->
<#--                          <input class="form-check-input" type="checkbox" name="is-emsloyee"  onclick="authorSamGMU()">-->
<#--                          <label class="form-check-label">Автор является сотрудником СамГМУ</label>-->
<#--                        </div>-->
<#--                        <div class="hidden" id="authorSamGMU">-->
<#--                          <select class="js-data-example-ajax form-control mb-10" name="author" onclick="addManTrack()" onchange="selectAuthor(this.value)" id="select"></select>-->
<#--                        </div>-->

<#--                        <div id="authorWorld">-->
<#--                          <input class="form-control mb-10" type="text" id="inputNameAuthor"  placeholder="Введите ФИО автора"></input>-->
<#--                          <div class="d-flex mb-10">-->
<#--                            <select class="form-control w-75 mr-10" id="regalia" size="1" onclick="selectReg(this.value)">-->
<#--                              <option  disabled>Выберите регалии</option>-->
<#--                              <option>Профессор</option>-->
<#--                              <option>К.М.Н.</option>-->
<#--                              <option>Доцент</option>-->
<#--                              <option>Зав. кафедрой</option>-->
<#--                            </select>-->
<#--                          </div>-->

<#--                          <div class="d-flex" id="addRegalii">-->
<#--                          </div>-->

<#--                          <button type="button" class="btn btn-primary mt-3" id="createAuthorButton">-->
<#--                            <i class="fas fa-plus"></i>-->
<#--                          </button>-->
<#--                        </div>-->

<#--                        <div>-->
<#--                          <table class="table table-hover editable" id="tableAuthor" name="authors">-->
<#--                            <tr>-->
<#--                              <th class="nameTd">ФИО автора</th>-->
<#--                              <th class="text-center regTd">Должность, степень, звание</th>-->
<#--                              <th class="text-center checkTd">Руководитель трека</th>-->
<#--                              <th class="text-center docTd">Документы</th>-->
<#--                              <th class="text-center removeTd"><i class="fas fa-trash-alt"></i></th>-->
<#--                            </tr>-->
<#--                          </table>-->
<#--                        </div>-->
<#--                      </div>-->
<#--                    </div>-->

<#--                    <div class="my-4">-->
<#--                      <b>Название образовательного трека</b><br>-->
<#--                      <#if profileTab>-->
<#--                        <textarea class="form-control editable" id="trackInput" name="trackName" cols="80" rows="1" oninput="updateTrackHeading()" placeholder="Введите название образовательного трека"></textarea>-->
<#--                      <#else>-->
<#--                        <textarea class="form-control editable" id="trackInput" name="trackName" cols="80" rows="1" oninput="updateTrackHeading()" placeholder="Введите название образовательного трека">${track.trackName}</textarea>-->
<#--                      </#if>-->
<#--                    </div>-->

<#--                    <div class="my-4">-->
<#--                      <b>Аннотация образовательного трека</b><br>-->
<#--                      <#if profileTab>-->
<#--                        <textarea class="form-control editable" name="trackAnnotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#" onclick="trackSignature()"></textarea>-->
<#--                      <#else>-->
<#--                        <textarea class="form-control editable" name="trackAnnotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#" onclick="trackSignature()">${track.annotation}</textarea>-->
<#--                      </#if>-->
<#--                    </div>-->
<#--                    <div class="my-4">-->
<#--                      <b>Дополнительные ключевые слова</b> <br>-->
<#--                      <#if profileTab>-->
<#--                        <textarea class="form-control editable" name="trackKeyWords" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив их ;" onclick="trackCheck()"></textarea>-->
<#--                      <#else>-->
<#--                        <textarea class="form-control editable" name="trackKeyWords" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив их ;" onclick="trackCheck()">${track.keyWords}</textarea>-->
<#--                      </#if>-->
<#--                    </div>-->
<#--                  </div>-->

<#--                </div>-->
<#--                <div class="tab-pane fade show active h-100" id="pills-network-wired" role="tabpanel" aria-labelledby="pills-network-wired-tab" tabindex="0">-->
<#--                  <div class="my-3 rounded p-3" style="border: 1px solid #e9e9e9; background: #e9e9e9;">-->
<#--                    <div class="d-flex justify-content-end">-->
<#--                      <button type="button" class="btn btn-sm rounded px-3" style="border: 1px solid #e9e9e9" data-bs-toggle="modal" data-bs-target="#addContentModal"><i class="fas fa-plus"></i></button>-->
<#--                    </div>-->
<#--                    <div class="d-flex justify-content-center">-->
<#--                      <h5 id="trackHeading" class="text-center m-0 p-0 flex-nowrap w-75">Образовательный трек</h5>-->
<#--                    </div>-->
<#--                  </div>-->

<#--                  <div class="modal fade" id="addContentModal" tabindex="-1" aria-labelledby="addContentModalLabel" aria-hidden="true">-->
<#--                    <div class="modal-dialog">-->
<#--                      <div class="modal-content">-->
<#--                        <div class="modal-header">-->
<#--                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>-->
<#--                        </div>-->
<#--                        <div class="modal-body">-->
<#--                          <div class="d-flex justify-content-center mb-3"><h5>Выберите тип добавляемого контента</h5></div>-->
<#--                          <div class="d-flex justify-content-center">-->

<#--&lt;#&ndash;                            <button type="button" class="btn btn-primary mx-2" onclick="addModuleScheme()" data-bs-dismiss="modal">Модуль</button>&ndash;&gt;-->
<#--                            <button type="button" class="btn btn-primary m-2" onclick="addSeriesBegin()" data-bs-dismiss="modal">Серия</button>-->
<#--                            <button type="button" class="btn btn-primary m-2" onclick="addLectureModalBegin()" data-bs-dismiss="modal">Лекция</button>-->
<#--&lt;#&ndash;                            <button type="button" class="btn btn-primary mx-2" onclick="addLectureScheme()" data-bs-dismiss="modal">Лекция</button>&ndash;&gt;-->
<#--                          </div>-->
<#--                        </div>-->
<#--                      </div>-->
<#--                    </div>-->
<#--                  </div>-->

<#--                  <div class="d-flex rowScheme" id="rowScheme" style="position:relative;">-->
<#--                    <div id="rowModule" class="d-flex flex-wrap">-->
<#--&lt;#&ndash;                      <div class="mx-3">&ndash;&gt;-->
<#--&lt;#&ndash;                        <div class="bg-primary rounded p-3 d-flex">&ndash;&gt;-->
<#--&lt;#&ndash;                          <div class="col-11">&ndash;&gt;-->
<#--&lt;#&ndash;                            <p class="text-center text-white fs16 pb-0 mb-0">Модуль 1</p>&ndash;&gt;-->
<#--&lt;#&ndash;                          </div>&ndash;&gt;-->
<#--&lt;#&ndash;                          <div class="col-1">&ndash;&gt;-->
<#--&lt;#&ndash;                            <button type="button" class="btn btn-sm rounded"><i class="fas fa-plus text-white" onclick="addLecInModule(this)"></i></button>&ndash;&gt;-->
<#--&lt;#&ndash;                          </div>&ndash;&gt;-->
<#--&lt;#&ndash;                        </div>&ndash;&gt;-->
<#--&lt;#&ndash;                        <div class="d-flex justify-content-between my-2 rowLecInModule">&ndash;&gt;-->
<#--&lt;#&ndash;                          <div class="bg-success m-3 p-3 rounded lectureBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 1</p></div>&ndash;&gt;-->
<#--&lt;#&ndash;                          <div class="bg-success m-3 p-3 rounded lectureBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 2</p></div>&ndash;&gt;-->
<#--&lt;#&ndash;                          <div class="bg-success m-3 p-3 rounded lectureBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 3</p></div>&ndash;&gt;-->
<#--&lt;#&ndash;                        </div>&ndash;&gt;-->
<#--&lt;#&ndash;                      </div>&ndash;&gt;-->
<#--                    </div>-->

<#--                    <div class="d-flex mx-2 flex-wrap" id="colLec">-->

<#--&lt;#&ndash;                      <div class="bg-success mx-2 p-3 mb-1 rounded lectureBlockScheme lecBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 1 Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p></div>&ndash;&gt;-->
<#--&lt;#&ndash;                      <div class="bg-success mx-2 p-3 mb-1 rounded lectureBlockScheme lecBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 2thgyluiuhgfghj sdafhkl;dsfatryiltrfdcvbnjkiuytf</p></div>&ndash;&gt;-->
<#--&lt;#&ndash;                      <div class="bg-success mx-2 p-3 mb-1 rounded lectureBlockScheme lecBlockScheme"><p class="text-center text-white m-0 p-0 text-truncate lectureBlockSchemeText" onmouseover="HintShowbyTamara(this)" onmouseout="HintHidebyTamara(this)">Лекция 3 Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod</p></div>&ndash;&gt;-->


<#--                    </div>-->
<#--                  </div>-->
<#--                  <div class="modal-footer">-->
<#--                    <button type="submit" id="submitButton" class="btn btn-primary">Добавить трек</button>-->
<#--                    <button type="submit" id="editTrack" class="btn btn-primary hidden">Редактировать трек</button>-->
<#--                    <button type="button" id="sendForApproval" class="btn btn-primary hidden" onclick="sendForApprovalFunc()">Отправить на согласование</button>-->
<#--                  </div>-->
<#--                </div>-->
<#--              </form>-->
<#--            </div>-->
<#--            <!-- Заявка на Модуль всплывающая для дерева &ndash;&gt;-->
<#--            <div class="modal-wrapper">-->
<#--              <div class="lectureModal shadow hidden" id="addSeriesModal" name="modalSeries">-->
<#--                <div class="modal-header">-->
<#--                  <h5>Добавить Модуль</h5>-->
<#--                  <button type="button" class="btn-close" onclick="btnClose()"></button>-->
<#--                </div>-->
<#--                <div class="modal-content">-->
<#--                  <div class="modal-body">-->

<#--                    <div>-->
<#--                      <div class="d-flex justify-content-center">-->
<#--                        <h5>Заявка на создание модуля</h5>-->
<#--                      </div>-->
<#--                      <div class="modal-body">-->
<#--                        <div class="mb-10">-->
<#--                          <p id="trackHeadingInModule" class="m-0 p-0 flex-nowrap w-75">Образовательный трек</p>-->
<#--                        </div>-->

<#--                        <div class="mb-10">-->
<#--                          <input type="text" class="form-control mb-2" id="headSeries" placeholder="Название образовательного модуля">-->
<#--                        </div>-->

<#--                        <div class="my-4">-->
<#--                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Аннотация/содержание образовательного модуля</b><br>-->
<#--                          <textarea class="form-control" id="moduleModalAnnotation" name="track-annotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>-->
<#--                        </div>-->

<#--                        <div class="mt-10 mb-10">-->
<#--                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Дополнительные ключеные слова</b><br>-->
<#--                          <textarea class="form-control" id="moduleModalKeyWords" name="module-annotation" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;"></textarea>-->
<#--                        </div>-->

<#--                      </div>-->
<#--                    </div>-->

<#--                  </div>-->
<#--                  <div class="modal-footer">-->
<#--                    <!-- Кнопка сохранить&ndash;&gt;-->
<#--                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="saveModule" onclick="addModuleScheme(); saveSeries()">Добавить серию</button>-->
<#--                    <button type="button" class="btn btn-primary hidden" data-bs-dismiss="modal" id="editModule">Сохранить серию</button>-->
<#--                  </div>-->
<#--                </div>-->
<#--              </div>-->
<#--            </div>-->
<#--            <!-- Заявка на Лекцию в Модуле всплывающая для дерева &ndash;&gt;-->
<#--            <div class="modal-wrapper">-->
<#--              <div class="lectureModal shadow hidden" id="addLectureInModule">-->
<#--                <div class="modal-header">-->
<#--                  <h5>Добавить лекцию</h5>-->
<#--                  <button type="button" class="btn-close" onclick="btnClose()"></button>-->
<#--                </div>-->
<#--                <div class="modal-content">-->
<#--                  <div class="modal-body">-->

<#--                    <div>-->
<#--                      <div class="d-flex justify-content-center">-->
<#--                        <h5>Заявка на создание лекции</h5>-->
<#--                      </div>-->
<#--                      <div class="modal-body">-->

<#--                        <div class="mb-10">-->
<#--                          <p id="trackHeadingInLecture" class="m-0 p-0 flex-nowrap w-75">Образовательный трек</p>-->
<#--                        </div>-->

<#--                        <div class="mb-10">-->
<#--                          <p id="moduleHeadingInLecture" class="m-0 p-0 flex-nowrap w-75">Образовательный модуль</p>-->
<#--                        </div>-->

<#--                        <div class="mb-10">-->
<#--                          <input type="text" class="form-control mb-2" id="lectureModuleName" placeholder="Название образовательной лекции">-->
<#--                        </div>-->

<#--                        <div class="my-4">-->
<#--                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Аннотация/содержание образовательной лекции</b><br>-->
<#--                          <textarea class="form-control" id="lectureModuleAnnotation" name="track-annotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>-->
<#--                        </div>-->

<#--                        <div class="mt-10 mb-10">-->
<#--                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Дополнительные ключеные слова</b><br>-->
<#--                          <textarea class="form-control" id="lectureModuleKeyWords" name="module-annotation" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;"></textarea>-->
<#--                        </div>-->

<#--                      </div>-->
<#--                    </div>-->

<#--                  </div>-->
<#--                  <div class="modal-footer">-->
<#--                    <!-- Кнопка сохранить&ndash;&gt;-->
<#--                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="saveLectureInModule">Добавить лекцию</button> <!--onclick="addLecInModule()&ndash;&gt;-->
<#--                    <button type="button" class="btn btn-primary hidden" data-bs-dismiss="modal" id="editLectureInModule">Сохранить лекцию</button> <!--onclick="addLecInModule()&ndash;&gt;-->
<#--                  </div>-->
<#--                </div>-->
<#--              </div>-->
<#--            </div>-->
<#--            <!-- Заявка на Лекцию всплывающая для дерева &ndash;&gt;-->
<#--            <div class="modal-wrapper">-->
<#--              <div class="lectureModal shadow hidden" id="addLectureModal">-->
<#--                <div class="modal-header">-->
<#--                  <h5>Добавить лекцию</h5>-->
<#--                  <button type="button" class="btn-close" onclick="btnClose()"></button>-->
<#--                </div>-->
<#--                <div class="modal-content">-->
<#--                  <div class="modal-body">-->

<#--                    <div>-->
<#--                      <div class="d-flex justify-content-center">-->
<#--                        <h5>Заявка на создание лекции</h5>-->
<#--                      </div>-->
<#--                      <div class="modal-body">-->

<#--                        <div class="mb-10">-->
<#--                          <p id="trackHeadingInLectureOutOfModule" class="m-0 p-0 flex-nowrap w-75">Образовательный трек</p>-->
<#--                        </div>-->

<#--                        <div class="mb-10">-->
<#--                          <input type="text" class="form-control mb-2" id="lectureModalName" placeholder="Название образовательной лекции">-->
<#--                        </div>-->

<#--                        <div class="my-4">-->
<#--                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Аннотация/содержание образовательной лекции</b><br>-->
<#--                          <textarea class="form-control" id="lectureModalAnnotation" name="track-annotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>-->
<#--                        </div>-->

<#--                        <div class="mt-10 mb-10">-->
<#--                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Дополнительные ключеные слова</b><br>-->
<#--                          <textarea class="form-control" id="lectureModalKeyWords" name="module-annotation" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;"></textarea>-->
<#--                        </div>-->

<#--                      </div>-->
<#--                    </div>-->

<#--                  </div>-->
<#--                  <div class="modal-footer">-->
<#--                    <!-- Кнопка сохранить&ndash;&gt;-->
<#--                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="saveLectureOut" onclick="addLectureScheme()">Добавить лекцию</button>-->
<#--                    <button type="button" class="btn btn-primary hidden" data-bs-dismiss="modal" id="editLecture">Сохранить лекцию</button>-->
<#--                  </div>-->
<#--                </div>-->
<#--              </div>-->
<#--            </div>-->

            <!-- Заявка на Курс лекций -->
            <div class="tab-pane fade <#if !profileTab || !lectureFlag> <#else>show active</#if>" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
              <form method="post" action="/addSeries" id="createSeries" enctype="multipart/form-data">
              <div>
                <#if moduleFlag>
                <div class="d-flex justify-content-center">
                  <h5>Заявка на создание курса лекций</h5>
                </div>
                <#else>
                <div class="d-flex justify-content-center">
                  <h5>Редактированиее модуля</h5>
                </div>
                </#if>
                <div class="modal-body"> 
                    <div class="mb-10">
                      <select class="form-control" class="form-control" id="trackModuleSeries" onchange="updateTrackHeadingModuleSeries()" name="seriesTrack" size="1">
                        <#if moduleFlag>
                          <option select class="form-control">Выберите шаблон</option>
                        <#else>
<#--                          <option select class="form-control" value="${seriesOne.getTrack().id}">${seriesOne.trackName}</option>-->
                        </#if>
                        <#if profileTab>
                          <#list shapes as shape>
                            <option value="${shape.id}">${shape.shapeName}</option>
                          </#list>
                        </#if>

                      </select class="form-control">
                    </div>
                    <#if moduleFlag>
                      <div class="mb-10">
                        <input type="text" class="form-control mb-2" id="moduleSeriesName" oninput="updateTrackHeadingModuleSeries()" name="seriesName" placeholder="Название курса лекций">
                      </div>
                    <#else>
                        <div class="mb-10">
                          <input type="text" class="form-control mb-2" id="moduleSeriesName" oninput="updateTrackHeadingModuleSeries()" name="seriesName" value="${seriesOne.seriesName}">
                      </div>
                    </#if>
                    <div class="my-4">
                    <p><b>Руководитель курса лекций</b></p>
                    <div>
                      <div class="form-check mb-10">
                        <input class="form-check-input" type="checkbox" name="is-emsloyeeModule"  onclick="authorSamGMUModule()">
                        <label class="form-check-label">Руководитель является сотрудником СамГМУ</label>
                      </div>
                      <div class="hidden" id="authorSamGMUModule">
                        <select class="js-data-example-ajax form-control mb-10" name="author" onchange="selectAuthorModule(this.value, 'selectAuthorToModule', 'tableAuthorMod', true, true)" id="selectAuthorToModule"></select>
                      </div>

                      <div id="authorWorldModule">
                        <input class="form-control mb-10" type="text" id="inputNameAuthorModule"  placeholder="Введите ФИО автора"></input>
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

                        <button type="button" class="btn btn-primary mt-3" id="createAuthorButtonModule">
                          <i class="fas fa-plus"></i>
                        </button>
                      </div>

                      <div>
                        <table class="table table-hover editable" id="tableAuthorMod" name="authorsMod">
                          <tr>
                            <th class="nameTd">ФИО руководителя</th>
                            <th class="text-center regTd">Должность, степень, звание</th>
                            <th class="text-center checkTd">Руководитель</th>
                            <th class="text-center docTd">Документы</th>
                            <th class="text-center removeTd"><i class="fas fa-trash-alt"></i></th>
                          </tr>
                        </table>
                      </div>
                    </div>
                    </div>
                    <#if moduleFlag>
                      <div class="my-4">
                        <b>Аннотация/содержание образовательного Модуля</b><br>
                        <textarea class="form-control" name="seriesAnnotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>
                      </div>
                    <#else>
                        <div class="my-4">
                          <b>Аннотация/содержание образовательного Модуля</b><br>
                          <textarea class="form-control" name="seriesAnnotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#">${seriesOne.annotation}</textarea>
                        </div>
                    </#if>
                    <#if moduleFlag>
                        <div class="mt-10 mb-10">
                          <b>Дополнительные ключеные слова</b><br>
                          <textarea class="form-control" name="seriesKeyWords" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;"></textarea>
                        </div>
                    <#else>
                        <div class="mt-10 mb-10">
                          <b>Дополнительные ключеные слова</b><br>
                          <textarea class="form-control" name="seriesKeyWords" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;">${seriesOne.keyWords}</textarea>
                         </div>
                    </#if>
<#--                    <div class="accordion accordion-flush my-4" id="acModuleAdditionalGuide">-->
<#--                      <div class="accordion-item">-->
<#--                        <h2 class="accordion-header">-->
<#--                          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseModule" aria-expanded="false" aria-controls="flush-collapseModule">-->
<#--                            <b>Расширяемый перечень справочников для описания модуля</b>-->
<#--                          </button>-->
<#--                        </h2>-->
<#--                        <div id="flush-collapseModule" class="accordion-collapse collapse" data-bs-parent="#acModuleAdditionalGuide">-->
<#--                          <div class="accordion-body">-->
<#--                            <div class="mt-10 mb-10">-->
<#--                              <b>Добавить справочник для описания</b><br>-->
<#--                              <div class="d-flex">-->
<#--                                <select class="form-control w-75 mr-10" name="">-->
<#--                                  <option select class="form-control"ed="" disabled="">Выберите справочник</option>-->
<#--                                  <option value="Шить">МКБ</option>-->
<#--                                  <option value="Брать кровь">Дисциплина/Учебный план</option>-->
<#--                                </select>-->
<#--                                <button type="button" class="btn btn-primary ml-10">Добавить</button>-->
<#--                              </div>-->
<#--                            </div>-->

<#--                            <div class="mt-10 mb-10">-->
<#--                              <b>Целевая аудитория</b><br>-->
<#--                              <select class="form-control" class="form-control" name="listners" size="1">-->
<#--                                <option select class="form-control"ed="" disabled="">Выберите целевую аудиторию</option>-->
<#--                                <option value="Студент 1-3 курса">Студент 1-3 курса</option>-->
<#--                                <option value="Студент 4- курса">Студент 4- курса</option>-->
<#--                                <option value="Студент 1-6 курса">Студент 1-6 курса</option>-->
<#--                                <option value="Ординатор">Ординатор</option>-->
<#--                                <option value="Аспирант">Аспирант</option>-->
<#--                                <option value="Врач-кардиолог">Врач-кардиолог</option>-->
<#--                                <option value="Врач-терапевт">Врач-терапевт</option>-->
<#--                                <option value="Врач-и т.п.">Врач-и т.п.</option>-->
<#--                              </select class="form-control">-->
<#--                            </div>-->

<#--                            <div class="mt-10 mb-10">-->
<#--                              <b>Ось МКБ</b><br>-->
<#--                              <input type="text" id="myInput" class="form-control mt-10 mb-10" onkeyup="filterOptions()" placeholder="Параметры поиска...">-->
<#--                              <select id="mySelect" class="form-control" name="mkb" onchange="selectMkb('tableMkb')" size="1">-->
<#--                                <option select class="form-control"ed="" disabled="">Выберите значение</option>-->
<#--                                <#if profileTab>-->
<#--                                <#list mkb10 as mkb>-->
<#--                                  <option value="${mkb.id}">${mkb.code} ${mkb.name}</option>-->
<#--                                </#list>-->
<#--                                </#if>-->
<#--                              </select class="form-control">-->

<#--                              <div>-->
<#--                                <table class="table table-hover editable" id="tableMkb" name="tableMkb">-->
<#--                                  <tr>-->
<#--                                    <th class="text-center regTd">Наименование</th>-->
<#--                                    <th class="text-center removeTd"><i class="fas fa-trash-alt"></i></th>-->
<#--                                  </tr>-->

<#--&lt;#&ndash;                                  <tbody>&ndash;&gt;-->
<#--&lt;#&ndash;                                  <#list seriesOne.mkbs as mkb>&ndash;&gt;-->
<#--&lt;#&ndash;                                    <tr>&ndash;&gt;-->
<#--&lt;#&ndash;                                      <td>${mkb.value}</td>&ndash;&gt;-->
<#--&lt;#&ndash;                                      <th class="text-center removeTd"><i class="fas fa-trash-alt"></i></th>&ndash;&gt;-->
<#--&lt;#&ndash;                                    </tr>&ndash;&gt;-->
<#--&lt;#&ndash;                                  </#list>&ndash;&gt;-->
<#--&lt;#&ndash;                                  </tbody>&ndash;&gt;-->
<#--                                </table>-->
<#--                              </div>-->

<#--                            </div>-->



<#--                            <div class="mt-10 mb-10">-->
<#--                              <b>Ось Дисциплин/учебный план</b><br>-->
<#--                              <select class="form-control" name="discipline" size="1">-->
<#--                                <option select class="form-control"ed="" disabled="">Выберите дисциплину</option>-->
<#--                                <option value="Общая анатомия">Общая анатомия</option>-->
<#--                                <option value="Биохимия">Биохимия</option>-->
<#--                                <option value="Диетолония">Диетолония</option>-->
<#--                                <option value="Фармакология">Фармакология</option>-->
<#--                              </select class="form-control">-->
<#--                            </div>-->
<#--                            <div class="mt-10 mb-10">-->
<#--                              <b>Ось Локализации</b><br>-->
<#--                              <select class="form-control" name="atlas" size="1">-->
<#--                                <option select class="form-control"ed="" disabled="">Выберите локализацию</option>-->
<#--                                <option value="Мозг">Мозг</option>-->
<#--                                <option value="Пищеватительная система">Пищеватительная система</option>-->
<#--                                <option value="Сердце">Сердце</option>-->
<#--                                <option value="Опорнодвигательная система">Опорнодвигательная система</option>-->
<#--                              </select class="form-control">-->
<#--                            </div>    -->
<#--                            <div class="mt-10 mb-10">-->
<#--                              <b>Ось Внешних факторов</b><br>-->
<#--                              <select class="form-control" name="exfacts" size="1">-->
<#--                                <option select class="form-control"ed="" disabled="">Выберите фактор</option>-->
<#--                                <option value="Солнце">Солнце</option>-->
<#--                                <option value="Атмосферное давление">Атмосферное давление</option>-->
<#--                                <option value="Автомобиль">Автомобиль</option>-->
<#--                                <option value="Воздействие радиации">Воздействие радиации</option>-->
<#--                              </select class="form-control">-->
<#--                            </div>-->
<#--                            <div class="mt-10 mb-10">-->
<#--                              <b>Ведение и состояния</b><br>-->
<#--                              <div class="d-flex">-->
<#--                                <select class="form-control w-75 mr-10" name="exfacts" size="1">-->
<#--                                  <option select class="form-control"ed="" disabled="">Выберите этап</option>-->
<#--                                  <option value="Солнце">Норма</option>-->
<#--                                  <option value="Атмосферное давление">Этиология</option>-->
<#--                                  <option value="Автомобиль">Пат.анатомия</option>-->
<#--                                  <option value="Воздействие радиации">Диагностика</option>-->
<#--                                  <option value="Атмосферное давление">Лечение</option>-->
<#--                                  <option value="Автомобиль">Реабилитация</option>-->
<#--                                </select class="form-control">-->
<#--                                <button type="button" class="btn btn-primary ml-10"><i class="fas fa-plus"></i></button>-->
<#--                              </div>-->
<#--                            </div>-->
<#--                            <div class="mt-10 mb-10">-->
<#--                              <b>Компетенции</b><br>-->
<#--                              <div class="d-flex">-->
<#--                                <select class="form-control w-75 mr-10" name="kompetetion" size="1">-->
<#--                                  <option select class="form-control"ed="" disabled="">Выберите компетению</option>-->
<#--                                  <option value="Шить">Шить</option>-->
<#--                                  <option value="Брать кровь">Брать кровь</option>-->
<#--                                  <option value="Писать">Писать</option>-->
<#--                                  <option value="Проводить аускультацию">Проводить аускультацию</option>-->
<#--                                </select class="form-control">-->
<#--                                <button type="button" class="btn btn-primary ml-10"><i class="fas fa-plus"></i></button>-->
<#--                              </div>-->
<#--                            </div>-->
<#--                          </div>-->
<#--                        </div>-->
<#--                      </div>-->
<#--                    </div> -->

<#--                    <div class="form-check mt-10 mb-10">-->
<#--                        <input class="form-check-input" type="checkbox" onclick="subshootingModule()">-->
<#--                        <label class="form-check-label">Подсъёмки (при наличии)</label>-->
<#--                      </div>-->

<#--                    <div class="mt-10">                    -->
<#--                      <textarea id="subsurveyModule" class="hidden form-control" cols="50" rows="2" placeholder="Краткое описание подсъёмки"></textarea>-->
<#--                    </div>-->

                </div>
                <div class="tab-pane fade show active h-100" id="pills-network-wired" role="tabpanel" aria-labelledby="pills-network-wired-tab" tabindex="0">
                  <div class="my-3 rounded p-3" style="border: 1px solid #e9e9e9; background: #e9e9e9;">
                    <div class="d-flex justify-content-end">
                      <button type="button" class="btn btn-sm rounded px-3" onclick="addLectureModalModuleBegin()" style="border: 1px solid #e9e9e9" data-bs-toggle="modal" data-bs-target="#addLectureModalBegin"><i class="fas fa-plus"></i></button>
<#--                      <button type="button" class="btn btn-primary m-2" onclick="addLectureModalBegin()" data-bs-dismiss="modal">Лекция</button>-->
                    </div>
                    <div class="d-flex justify-content-center">
                      <h5 id="moduleHeading" class="text-center m-0 p-0 flex-nowrap w-75">Курс лекций</h5>
                    </div>
                  </div>

                  <div class="d-flex rowScheme" id="rowSchemeModuleSeries" style="position:relative;">
                    <div id="rowModuleSeries" class="d-flex flex-wrap">

                    </div>

                    <div class="d-flex mx-2 flex-wrap" id="colLecModule">

                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="submit" id="saveModuleM" class="btn btn-primary">Добавить курс лекций</button>
                    <button type="submit" id="editModuleM" class="btn btn-primary hidden">Редактировать курс лекций</button>
                    <button type="button" id="sendForApproval" class="btn btn-primary hidden" onclick="sendForApprovalCourse('sendCourse')">Отправить на согласование</button>
                    <button type="button" id="sendForExecution" class="btn btn-primary hidden" onclick="sendForApprovalCourse('sendCourseForExecution')">Отправить на исполнение</button>
                  </div>
                </div>
              </div>
              </form>
            </div>

            <!-- Заявка на Лекцию всплывающая для дерева модуля -->
            <div class="modal-wrapper">
              <div class="lectureModal shadow hidden" id="addLectureModalModule">
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
                          <p id="trackHeadingInLectureModuleSeries" class="m-0 p-0 flex-nowrap w-75">Образовательный трек</p>
                        </div>

                        <div class="mb-10">
                          <p id="moduleHeadingInLectureModuleSeries" class="m-0 p-0 flex-nowrap w-75">Образовательный модуль</p>
                        </div>

                        <div class="my-4">
                          <p><b>Авторы лекции</b></p>
                          <div>
                            <div class="form-check mb-10">
                              <input class="form-check-input" type="checkbox" name="is-emsloyeeModule"  onclick="authorSamGMUModal()">
                              <label class="form-check-label">Автор является сотрудником СамГМУ</label>
                            </div>
                            <div class="hidden" id="authorSamGMUModal">
                              <select class="js-data-example-ajax form-control mb-10" name="author" onchange="selectAuthorModule(this.value, 'selectAuthorToModal', 'tableAuthorModal', false, true)" id="selectAuthorToModal"></select>
                            </div>

                            <div id="authorWorldModal">
                              <input class="form-control mb-10" type="text" id="inputNameAuthorModal"  placeholder="Введите ФИО автора"></input>
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

                              <button type="button" class="btn btn-primary mt-3" id="createAuthorButtonModal">
                                <i class="fas fa-plus"></i>
                              </button>
                            </div>

                            <div>
                              <table class="table table-hover editable" id="tableAuthorModal" name="authorsModal">
                                <tr>
                                  <th class="nameTd">ФИО руководителя</th>
                                  <th class="text-center regTd">Должность, степень, звание</th>
                                  <th class="text-center checkTd">Руководитель</th>
                                  <th class="text-center docTd">Документы</th>
                                  <th class="text-center removeTd"><i class="fas fa-trash-alt"></i></th>
                                </tr>
                              </table>
                            </div>
                          </div>
                        </div>

                        <div class="mb-10">
                          <input type="text" class="form-control mb-2" id="lectureModalNameModule" placeholder="Название образовательной лекции">
                        </div>

                        <div class="my-4">
                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Аннотация/содержание образовательной лекции</b><br>
                          <textarea class="form-control" id="lectureModalAnnotationModule" name="track-annotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>
                        </div>

                        <div class="mt-10 mb-10">
                          <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Дополнительные ключеные слова</b><br>
                          <textarea class="form-control" id="lectureModalKeyWordsModule" name="module-annotation" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;"></textarea>
                        </div>

                      </div>
                    </div>

                  </div>
                  <div class="modal-footer">
                    <!-- Кнопка сохранить-->
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="saveLectureInModuleSeries" onclick="addLectureSchemeModule()">Добавить лекцию</button>
                    <button type="button" class="btn btn-primary hidden" data-bs-dismiss="modal" id="editLectureInModuleSeries">Сохранить лекцию</button>
                  </div>
                </div>
              </div>
            </div>

            <!-- Заявка на Лекцию -->
            <div class="tab-pane fade <#if !lectureFlag>show active</#if>" id="contact-tab-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="0">
              <form method="post" action="/addLecture" id="createLecture" enctype="multipart/form-data">
              <div>
                <#if lectureFlag>
                <div class="d-flex justify-content-center">
                  <h5>Заявка на создание лекции</h5>
                </div>
                <#else>
                <div class="d-flex justify-content-center">
                  <h5>Редактирование лекции</h5>
                </div>
                </#if>
                <div class="modal-body">
                  <div class="mb-10">
                    <form id="trackForm" method="get" enctype="multipart/form-data">
                      <select  id="track" class="form-control" name="lectureTrack" size="1">
                        <#if lectureFlag>
                        <option select class="form-control">Выберите образовательный трек</option>
                        <#else>
                        <option select class="form-control" value="${lecture.getShape().id}">${lecture.getShapeName()}</option>
                        </#if>
                        <#list tracks as track>
                          <option value="${track.id}">${track.trackName}</option>
                        </#list>
                      </select class="form-control">
                    </form>
                  </div>

                  <div class="mb-10">
                    <select  id="series" class="form-control" name="lectureSeries" size="1">
                      <#if lectureFlag>
                      <option select class="form-control">Выберите образовательный модуль</option>
                      <#else>
                      <option select class="form-control" value="${lecture.getSeries().id}">${lecture.getSeriesName()}</option>
                      </#if>
                      <#list series as series>
                        <option value="${series.id}">${series.seriesName}</option>
                      </#list>
                    </select class="form-control">
                  </div>
                  <#if lectureFlag>
                  <div class="mb-10">
                    <input type="text" class="form-control mb-2" name="lectureName" placeholder="Название образовательной лекции">
                  </div>
                  <#else>
                  <div class="mb-10">
                    <input type="text" class="form-control mb-2" name="lectureName" placeholder="Название образовательной лекции" value="${lecture.lectureName}">
                  </div>
                  </#if>
                  <div class="my-4">
                    <p><b>Автор образовательной лекции</b></p>
                    <div>
                      <div class="form-check mb-10">
                        <input class="form-check-input" type="checkbox" name="is-emsloyeelecture"  onclick="authorSamGMULecture()">
                        <label class="form-check-label">Автор является сотрудником СамГМУ</label>
                      </div>
                      <div class="hidden" id="authorSamGMULecture">
                        <select class="js-data-example-ajax form-control mb-10" name="author" onchange="selectAuthorLecture(this.value)" id="selectAuthorToLecture"></select>
                      </div>

                      <div id="authorWorldLecture">
                        <input class="form-control mb-10" type="text" id="inputNameAuthorLecture"  placeholder="Введите ФИО автора"></input>
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

                        <button type="button" class="btn btn-primary mt-3" id="createAuthorButtonLecture">
                          <i class="fas fa-plus"></i>
                        </button>
                      </div>

                      <div>
                        <table class="table table-hover editable" id="tableAuthorLec" name="authorsLec">
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
                  <#if lectureFlag>
                        <div class="my-4">
                          <b>Аннотация/содержание образовательного Модуля</b><br>
                          <textarea class="form-control" name="lectureAnnotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>
                        </div>
                  <#else>
                  <div class="my-4">
                    <b>Аннотация/содержание образовательного Модуля</b><br>
                    <textarea class="form-control" name="lectureAnnotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#">${lecture.annotation}</textarea>
                  </div>
                  </#if>
                  <#if lectureFlag>
                        <div class="mt-10 mb-10">
                          <b>Дополнительные ключеные слова</b><br>
                          <textarea class="form-control" name="lectureKeyWords" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;"></textarea>
                        </div>
                  <#else>
                  <div class="mt-10 mb-10">
                    <b>Дополнительные ключеные слова</b><br>
                    <textarea class="form-control" name="lectureKeyWords" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив ;">${lecture.keyWords}</textarea>
                  </div>
                  </#if>
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
                                      <#list spravochnik as spr>
                                        <option value="${spr.id}">${spr.name}</option>
                                      </#list>
                                    </select>
                                    <button type="button" class="btn btn-primary ml-10">Добавить</button>
                                  </div>
                                </div>

                                <div class="mt-10 mb-10 hidden">
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
                                  <input type="text" id="myInputLec" class="form-control mt-10 mb-10" onkeyup="filterOptionsLec('myInputLec','mySelectLec')" placeholder="Параметры поиска...">
                                  <select id="mySelectLec" class="form-control" name="mkb" onchange="selectMkbLec('mySelectLec', 'tableMkbLec', 'mkb')" size="1">
                                    <option select class="form-control"ed="" disabled="">Выберите значение</option>
                                    <#if profileTab>
                                      <#list mkb10 as mkb>
                                        <option value="${mkb.id}">${mkb.code} ${mkb.name}</option>
                                      </#list>
                                    </#if>
                                  </select class="form-control">

                                  <div>
                                    <table class="table table-hover editable" id="tableMkbLec" name="tableMkbLec">
                                      <tr>
                                        <th class="text-center regTd">Наименование</th>
                                        <th class="text-center removeTd"><i class="fas fa-trash-alt"></i></th>
                                      </tr>

                                    </table>
                                  </div>

                                </div>


                                <div class="mt-10 mb-10">
                                  <b>Ось Дисциплин/учебный план</b><br>
                                  <input type="text" id="myInputLecDis" class="form-control mt-10 mb-10" onkeyup="filterOptionsLec('myInputLecDis', 'mySelectLecDis')" placeholder="Параметры поиска...">
                                  <select id="mySelectLecDis" class="form-control" name="discipline" onchange="selectMkbLec('mySelectLecDis', 'tableDis', 'dis')" size="1">
                                    <option select class="form-control"ed="" disabled="">Выберите дисциплину</option>
                                    <#list discipline as dis>
                                      <option value="${dis.id}">${dis.code} ${dis.name}</option>
                                    </#list>
                                  </select class="form-control">

                                  <div>
                                    <table class="table table-hover editable" id="tableDis" name="tableDis">
                                      <tr>
                                        <th class="text-center regTd">Наименование</th>
                                        <th class="text-center removeTd"><i class="fas fa-trash-alt"></i></th>
                                      </tr>

                                    </table>
                                  </div>

                                </div>
                                <div class="mt-10 mb-10">
                                  <b>Ось Локализации</b><br>
                                  <input type="text" id="myInputLecLoc" class="form-control mt-10 mb-10" onkeyup="filterOptionsLec('myInputLecLoc', 'mySelectLecLoc')" placeholder="Параметры поиска...">
                                  <select id="mySelectLecLoc" class="form-control" name="atlas" onchange="selectMkbLec('mySelectLecLoc', 'tableLoc', 'loc')" size="1">
                                    <option select class="form-control"ed="" disabled="">Выберите локализацию</option>
                                    <#list localization as loc>
                                      <option value="${loc.id}">${loc.code} ${loc.name}</option>
                                    </#list>
                                  </select class="form-control">

                                  <div>
                                    <table class="table table-hover editable" id="tableLoc" name="tableLoc">
                                      <tr>
                                        <th class="text-center regTd">Наименование</th>
                                        <th class="text-center removeTd"><i class="fas fa-trash-alt"></i></th>
                                      </tr>

                                    </table>
                                  </div>


                                </div>
                                <div class="mt-10 mb-10 hidden">
                                  <b>Ось Внешних факторов</b><br>
                                  <select class="form-control" name="exfacts" size="1">
                                    <option select class="form-control"ed="" disabled="">Выберите фактор</option>
                                    <option value="Солнце">Солнце</option>
                                    <option value="Атмосферное давление">Атмосферное давление</option>
                                    <option value="Автомобиль">Автомобиль</option>
                                    <option value="Воздействие радиации">Воздействие радиации</option>
                                  </select class="form-control">
                                </div>
                                <div class="mt-10 mb-10 hidden">
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
                                <div class="mt-10 mb-10 hidden">
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
                  <button type="submit" id="saveLectureL" class="btn btn-primary">Добавить лекцию</button>
                  <button type="submit" id="editLectureL" class="btn btn-primary hidden">Редактировать лекцию</button>
                </div>



                    </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Модальное окно для просмотра паспорта автора-->
    <div class="hidden modalShowDocs" id="modalShowDocs">
      <div class="modal-header">
        <button type="button" class="btn"><i class="fas fa-times" onclick="closeModal()"></i></button>
      </div>
      <div id="modalShowDocsBody">
      </div>
    </div>
    <div class="overlay hidden" id="overlay"></div>
    </div>


    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<#--    <script src="https://track.samsmu.ru/js/select2.min.js"></script>-->
<#--    <script src="https://track.samsmu.ru/js/popper.min.js"></script>-->
<#--    <script src="https://track.samsmu.ru/js/bootstrap.min.js"></script>-->
<#--    <script src="https://track.samsmu.ru/js/bootstrap.bundle.min.js"></script>-->
<#--    <script src="https://track.samsmu.ru/js/index.js"></script>-->
<#--    <script src="https://track.samsmu.ru/js/track.js"></script>-->
<#--    <script src="https://track.samsmu.ru/js/users.js"></script>-->
<#--    <script src="https://track.samsmu.ru/js/module.js"></script>-->
<#--    <script src="https://track.samsmu.ru/js/lecture.js"></script>-->
<#--    <script src="https://track.samsmu.ru/js/editTrack.js"></script>-->
<#--    <script src="https://track.samsmu.ru/js/editModule.js"></script>-->
<#--    <script src="https://track.samsmu.ru/js/editLecture.js"></script>-->

<#--    <script src="https://dev.track.samsmu.ru/static/js/select2.min.js"></script>-->
<#--    <script src="https://dev.track.samsmu.ru/static/js/popper.min.js"></script>-->
<#--    <script src="https://dev.track.samsmu.ru/static/js/bootstrap.min.js"></script>-->
<#--    <script src="https://dev.track.samsmu.ru/static/js/bootstrap.bundle.min.js"></script>-->
<#--    <script src="https://dev.track.samsmu.ru/static/js/index.js"></script>-->
<#--    <script src="https://dev.track.samsmu.ru/static/js/track.js"></script>-->
<#--    <script src="https://dev.track.samsmu.ru/static/js/users.js"></script>-->
<#--    <script src="https://dev.track.samsmu.ru/static/js/module.js"></script>-->
<#--    <script src="https://dev.track.samsmu.ru/static/js/lecture.js"></script>-->
<#--    <script src="https://dev.track.samsmu.ru/static/js/editTrack.js"></script>-->
<#--    <script src="https://dev.track.samsmu.ru/static/js/editModule.js"></script>-->
<#--    <script src="https://dev.track.samsmu.ru/static/js/editLecture.js"></script>-->

    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <script src="../js/jquery-3.7.0.min.js"></script>
    <script src="../js/select2.min.js"></script>
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap.bundle.min.js"></script>
    <script src="../js/index.js"></script>
    <script src="../js/track.js"></script>
    <script src="../js/users.js"></script>
    <script src="../js/module.js"></script>
    <script src="../js/lecture.js"></script>
    <script src="../js/editTrack.js"></script>
    <script src="../js/editModule.js"></script>
    <script src="../js/editLecture.js"></script>

    <script>
      function closeModal() {
        let modalShowDocs = document.getElementById('modalShowDocs');
        modalShowDocs.classList.add('hidden');
        let overlay = document.getElementById('overlay');
        overlay.classList.add('hidden');
      }
    </script>



    <script>

      var jwtReceived = false;
      var axiosConfig  = {
        headers: {
          //           'Content-Type': 'application/json;charset=UTF-8',
          'Authorization': 'Bearer ',
        }
      }

      window.addEventListener('message', function(event) {
        axiosConfig.headers.Authorization = event.data;

        jwtReceived = true;
        console.log("appTest JWT -> " + axiosConfig.headers.Authorization);
        localStorage.setItem('authorization', "Bearer " + axiosConfig.headers.Authorization);
        console.log("localStorage JWT -> " + localStorage.authorization);
      });

      class Mkb {
        constructor(value, track, module, lecture) {
          this.value = value;
          this.track = track;
          this.module = module;
          this.lecture = lecture;
        }
      }
      let mkbs = [];
      let diss = [];
      let locs = [];
      function selectMkb(el) {
        let e = document.getElementById("mySelect");
        let value = e.value;
        let text = e.options[e.selectedIndex].text;
        //let select = document.getElementById("select");
        //let value = select.value;
        let mkb = new Mkb(text, null, savedSeries, null);
        mkbs.push(mkb);

        let tableAuthor = document.getElementById(el);

        let divDoc = document.createElement('tr');
        divDoc.classList.add('divDoc');
        tableAuthor.append(divDoc);

        let divDocTd = document.createElement('td');
        divDocTd.setAttribute('colspan', '5');
        divDoc.append(divDocTd);

        let tr1 = document.createElement('table');
        tr1.classList.add('w-100', 'tr1');
        divDocTd.append(tr1);

        let nameTr = document.createElement('tr');
        tr1.append(nameTr);
        let nameTd = document.createElement('td');
        nameTd.classList.add('text-primary', 'nameTd');
        nameTd.textContent = text;
        nameTr.append(nameTd);

        let removeTd = document.createElement('td');
        removeTd.classList.add('removeTd', 'text-center');
        nameTr.append(removeTd);
        let close = document.createElement('i');
        close.classList.add('fas', 'fa-times', 'text-danger');
        removeTd.append(close);
      }

      <#if !profileTab>
        var trackId123 = real(${track.id})
      <#else>
        var trackId123 = 0;
      </#if>

      var flag = true;
      flag = ${profileTab?c}

      <#if !profileTab>
        document.addEventListener("DOMContentLoaded", function(event) {
          // ваш код для выполнения после загрузки страницы
          getAuthorsForEditTrack(trackId123);
        });
      </#if>

      <#if !moduleFlag>
      var seriesOneId123 = real(${seriesOne.id})
      <#else>
      var seriesOneId123 = 0;
      </#if>

      <#if !moduleFlag>
      document.addEventListener("DOMContentLoaded", function(event) {
        // ваш код для выполнения после загрузки страницы
        getAuthorsForEditModule(seriesOneId123);
      });
      </#if>

      <#if !lectureFlag>
      var lectureId123 = real(${lecture.id})
      <#else>
      var lectureId123 = 0;
      </#if>

      <#if !lectureFlag>
      document.addEventListener("DOMContentLoaded", function(event) {
        // ваш код для выполнения после загрузки страницы
        getAuthorsForEditLecture(lectureId123);
      });
      </#if>

      function real(string){
        if (string === undefined){
          return '';
        } else if (string === null) {
          return '';
        } else {
          return string;
        }
      }

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


      function authorSamGMUModule() {
        let authorSamGMUModule = document.getElementById('authorSamGMUModule');
        authorSamGMUModule.classList.toggle("hidden");
        let authorWorldModule = document.getElementById('authorWorldModule');
        authorWorldModule.classList.toggle("hidden");
        let createAuthorButtonModule = document.getElementById('createAuthorButtonModule');
        createAuthorButtonModule.classList.toggle("hidden");
      }

      function authorSamGMUModal() {
        let authorSamGMUModal = document.getElementById('authorSamGMUModal');
        authorSamGMUModal.classList.toggle("hidden");
        let authorWorldModal = document.getElementById('authorWorldModal');
        authorWorldModal.classList.toggle("hidden");
        let createAuthorButtonModal = document.getElementById('createAuthorButtonModal');
        createAuthorButtonModal.classList.toggle("hidden");
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
        iconSignature.classList.remove('text-white');
        let check = document.getElementById('check');
        check.classList.remove("bg-white");
        check.classList.add("bg-success");
        let iconCheck = document.getElementById('iconCheck');
        iconCheck.classList.add('text-white');
        iconCheck.style.transform = 'scale(1.3)';
      }

      function addManTrack() {
        document.getElementById('select').options[0].selected = true; //очищаем селект
        $('#selectAuthorToModule').val(null).trigger('change');
      }

      $(document).on('click', '.fa-times', function() {
        $(this).closest('.divDoc').remove();
        // let authorId = $(this).getAttribute("authorindex");
        // console.log(authorId);
      });

      const createAuthorButton = document.querySelector('#createAuthorButton');
      createAuthorButton.addEventListener('click', inputNameAuthor);
      const createAuthorButtonModule = document.querySelector('#createAuthorButtonModule');
      createAuthorButtonModule.addEventListener('click', inputNameAuthorModule);
      const createAuthorButtonLecture = document.querySelector('#createAuthorButtonLecture');
      createAuthorButtonLecture.addEventListener('click', inputNameAuthorLecture);

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
        console.log(data);

        fetch("https://dev.track.samsmu.ru/addTrack2", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.authorization
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
        let sendForApproval = document.getElementById('sendForApproval');
        sendForApproval.classList.remove("hidden");
      });

      function handleSubmit(event) {
        // Отменяем стандартное поведение формы
        event.preventDefault();

        // Очищаем поля формы
        document.getElementById("createTrack").reset();

        // Другие действия по обработке отправки формы...
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
      function filterOptionsLec(myInput, mySelect) {
        // Получаем элементы
        var input, filter, select, option, i, txtValue;
        input = document.getElementById(myInput);
        filter = input.value.toUpperCase();
        select = document.getElementById(mySelect);
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