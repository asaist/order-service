<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>3D timeline</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://track.samsmu.ru/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://track.samsmu.ru/css/styles.css">
    <link rel="stylesheet" href="https://track.samsmu.ru/css/select2.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="/css/select2.min.css">
  </head>
  <body>
    <div class="container-fluid my-2">
      <div>
        <div class="d-flex justify-content-center">
          <h5>Редактирование трека "Назание трека"</h5>                
        </div>
          
        <div>          
          <div class="modal-body">               

            <select class="form-control" name="institute" size="1" onclick="trackSignature()">
              <option selected="" disabled="">${track.universityName}</option>
              <#list universities as university>
                <option value="${university.universityId}">${university.universityName}</option>
              </#list>
            </select>

            <div class="my-4">
              <p><b>Автор образовательной лекции</b></p>
              <div>
                <div class="form-check mb-10">
                  <input class="form-check-input" type="checkbox" name="is-employeeEditTrack"  onclick="authorSamGMU()">
                  <label class="form-check-label">Автор является сотрудником СамГМУ</label>
                </div>
                <div class="hidden" id="authorSamGMU">
                  <select class="js-data-example-ajax form-control mb-10" name="author" onchange="selectAuthorLecture(this.value)" id="selectEditTrack"></select>
                </div>

                <div id="authorWorld">
                  <input class="form-control mb-10" type="text" id="inputNameAuthorEditTrack"  placeholder="Введите ФИО автора"></input>
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
                  <table class="table table-hover editable" id="tableAuthorEditTrack" name="authorsLec">
                    <thead>
                    <tr>
                      <th class="nameTd">ФИО автора</th>
                      <th class="text-center regTd">Должность, степень, звание</th>
                      <th class="text-center checkTd">Руководитель трека</th>
                      <th class="text-center docTd">Документы</th>
                      <th class="text-center removeTd"><i class="fas fa-trash-alt"></i></th>
                    </tr>
                    </thead>

                  </table>
                </div>
              </div>
            </div>

            <div class="my-4">
              <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Название образовательного трека</b><br>
              <textarea class="form-control" name="track-name" cols="80" rows="1" placeholder="Введите название образовательного трека">${track.trackName}</textarea>
            </div>

            <div class="my-4">
              <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Аннотация образовательного трека</b><br>
              <textarea class="form-control" id="track-annotation" cols="80" rows="5" placeholder="Заполните аннотацию, выделите #ключевые слова#" onclick="trackCheck()">${track.annotation}</textarea>
            </div>
            <div class="my-4">
              <i class="fas fa-angle-double-right text-primary mr-10"></i><b>Дополнительные ключевые слова</b> <br>
              <textarea class="form-control" name="module-annotation" cols="80" rows="2" placeholder="Заполните список с ключевыми словами, не включенных в аннотацию, разделив их ;">${track.keyWords}</textarea>
            </div>                  
          </div>
          <div class="modal-footer">                
            <button type="button" class="btn btn-primary" onclick="location.href='track_up.html';">Сохранить</button>
          </div>
        </div> 
        
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/pdfjs-dist@3.8.162/build/pdf.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <script src="/js/jquery-3.7.0.min.js"></script>
    <script src="/js/select2.min.js"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
    <script src="https://track.samsmu.ru/js/index.js"></script>
    <script src="/js/index.js"></script>
    <script src="/js/editTrack.js"></script>
    <script>
      getAuthorsForEditTrack(${track.id});

    </script>
  </body>
</html>