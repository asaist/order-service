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
      <h3>Доступные треки</h3>

      <form class="d-flex my-2">
        <form method="get" action="/table_track" class="form-inline">
          <input type="text" name="filter" class="form-control ml-2 w-50" value="${filter?ifExists}" placeholder="Введите название трека">
          <button class="btn btn-icon btn-sm mx-2" type="submit" ><i class="fas fa-search"></i></button>
        </form>
        <button class="btn btn-icon btn-sm mx-2" type="button"  data-toggle="modal" data-target="#addTrackModal"><i class="fas fa-plus"></i></button>        

        <!-- Modal -->
        <div class="modal fade" id="addTrackModal" tabindex="-1" role="dialog" aria-labelledby="addTrackModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="addTrackModalLabel">Заявка на создание трека</h5>
                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">                

                <select class="form-control" name="institute" size="1">
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
                  <textarea class="form-control" name="track-annotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#"></textarea>
                </div>
                <div class="my-4">
                  <b>Дополнительные ключевые слова</b> <br>
                  <textarea class="form-control" name="module-annotation" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив их ;"></textarea>
                </div>                  
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Отменить</button>
                <button type="button" class="btn btn-primary">Добавить трек</button>
              </div>
            </div>
          </div>
        </div>
      </form>      



      <table class="table table-bordered">
        <tr class="th-table">
          <th class="th-table" scope="col">№</th>
          <th class="th-table" scope="col">Название трека</th>
          <th class="th-table" scope="col">Руководитель трека</th>
          <th class="th-table" scope="col">Аннотация трека</th>
          <th class="th-table" scope="col">Эксперт</th>
          <th class="th-table" scope="col">Дата создания</th>
          <th class="th-table" scope="col"></th>
        </tr>
        <#list tracks as track>
          <tr class="cursor-pointer">

            <th scope="row" onclick="window.location='table-track/${track.id}'">${track.id}</th>
            <td  onclick="window.location='table-track/${track.id}'">${track.trackName}</td>
            <td  onclick="window.location='table-track/${track.id}'">${track.authorName}</td>
            <td  onclick="window.location='table-track/${track.id}'">${track.annotation}</td>
            <td  onclick="window.location='table-track/${track.id}'"></td>
            <td  onclick="window.location='table-track/${track.id}'">${track.createDate}</td>
            <td>
              <i class="fas fa-edit mr-10 status text-secondary"></i>
              <i class="fas fa-users mr-10 status text-secondary" id="targetAudienceUsers" onclick="targetAudience()"></i>
              <i class="fas fa-check-circle mr-10 status text-secondary"></i>
              <button type="button" class="btn btn-icon btn-sm mx-2" data-bs-toggle="modal" data-bs-target="#deleteTrackModal">
                <i class="fas fa-trash-alt text-secondary"></i>
              </button>
            </td>
          </tr>
        </#list>
      </table>

      <div class="modal fade" id="deleteTrackModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">

            <div class="modal-body">
              <div class="d-flex justify-content-end">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
              </div>
              <h5 class="text-center my-4">Вы уверены, что хотите удалить трек?</h5>
              <div class="d-flex justify-content-center mt-10">              
                <button type="button" class="btn btn-primary mx-2">Удалить</button>
                <button type="button" class="btn btn-outline-secondary mx-2">Отмена</button>
              </div>
            </div>            
          </div>
        </div>
      </div>

      <div class="hidden" id="targetAudienceBlock">
        <span class="bg-white mx-3 px-2" style="font-size: 14px;"><b>Целевая аудитория</b></span>
        <div class="interruptedСircuit p-3">
          <span class="badge rounded-pill bg-success" id="badge"><i class="fas fa-plus-circle mx-2"></i></span>
          <span class="badge rounded-pill bg-primary" id="badge">Студенты<i class="far fa-times-circle ml-10 closeBadge" id="closeBadge"></i></span>
          <span class="badge rounded-pill bg-primary">Ординаторы<i class="far fa-times-circle ml-10 closeBadge"></i></span>
          <span class="badge rounded-pill bg-primary">Аспиранты<i class="far fa-times-circle ml-10 closeBadge"></i></span>
          <span class="badge rounded-pill bg-primary">Врач терапевт<i class="far fa-times-circle ml-10 closeBadge"></i></span>
        </div>
      </div>

      <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> 
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script> 
      <script>
      function authorSamGMU() {
        let authorSamGMU = document.getElementById('authorSamGMU');
        authorSamGMU.classList.toggle("hidden");
        let authorWorld = document.getElementById('authorWorld');
        authorWorld.classList.toggle("hidden");
      }

      function targetAudience() {
        let targetAudienceBlock = document.getElementById('targetAudienceBlock');
        targetAudienceBlock.classList.toggle("hidden");
        let targetAudienceUsers = document.getElementById('targetAudienceUsers');
        targetAudienceUsers.classList.toggle("text-primary");
      }
    </script>     
  </body>  
</html>