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
          <button class="btn btn-outline-primary btn-sm mx-2" type="button" data-tooltip="Добавить трек" onclick="location.href='/';"><i class="fas fa-plus mx-1"></i></button>
        </form>
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
        <#list series as ser>
          <tr class="cursor-pointer">

            <th scope="row" onclick="window.location='table_track/${ser.id}'">${ser.id}</th>
            <td  onclick="window.location='table_track/${ser.id}'">${ser.seriesName}</td>
            <td  onclick="window.location='table_track/${ser.id}'">${ser.getSupervisor1()?ifExists}</td>
            <td  onclick="window.location='table_track/${ser.id}'">${ser.annotation}</td>
            <td  onclick="window.location='table_track/${ser.id}'"></td>
            <td  onclick="window.location='table_track/${ser.id}'">${ser.createDate}</td>
            <td>
              <button type="button" class="btn btn-icon btn-sm"  data-tooltip-right="Редактировать трек">
                <i class="fas fa-edit status text-secondary" onclick="location.href='/module/${ser.id}'; getAuthorsForEditModule(${ser.id});"></i>
              </button>
<#--              <i class="fas fa-users mr-10 status text-secondary" id="targetAudienceUsers" onclick="targetAudience()"></i>-->
<#--              <i class="fas fa-check-circle mr-10 status text-secondary"></i>-->
<#--              <button type="button" id="deleteTrack" track="${ser.id}" onclick="deleteTrack(${ser.id})" class="btn btn-icon btn-sm mx-2">-->
<#--                <i class="fas fa-trash-alt text-secondary"></i>-->
<#--              </button>-->
            </td>
          </tr>
        </#list>
      </table>

<#--      <div class="modal fade" id="deleteTrackModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">-->
<#--        <div class="modal-dialog">-->
<#--          <div class="modal-content">-->

<#--            <div class="modal-body">-->
<#--              <div class="d-flex justify-content-end">-->
<#--                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>-->
<#--              </div>-->
<#--              <h5 class="text-center my-4">Вы уверены, что хотите удалить трек?</h5>-->
<#--              <div class="d-flex justify-content-center mt-10">-->
<#--                <button type="button" id="deleteTrack" class="btn btn-primary mx-2">Удалить</button>-->
<#--                <button type="button" class="btn btn-outline-secondary mx-2">Отмена</button>-->
<#--              </div>-->
<#--            </div>-->
<#--          </div>-->
<#--        </div>-->
<#--      </div>-->

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
      <script src="https://track.samsmu.ru/js/track.js"></script>

      <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/select2.min.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/popper.min.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/bootstrap.min.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/bootstrap.bundle.min.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/index.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/track.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/users.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/module.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/lecture.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/editTrack.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/editModule.js"></script>
      <script src="https://dev.track.samsmu.ru/static/js/editLecture.js"></script>

      <script src="./js/jquery-3.7.0.min.js"></script>
      <script src="./js/select2.min.js"></script>
      <script src="./js/popper.min.js"></script>
      <script src="./js/bootstrap.min.js"></script>
      <script src="./js/bootstrap.bundle.min.js"></script>
      <script src="./js/index.js"></script>
      <script src="./js/track.js"></script>
      <script src="./js/users.js"></script>
      <script src="./js/module.js"></script>
      <script src="./js/lecture.js"></script>
      <script src="./js/editTrack.js"></script>
      <script src="./js/editModule.js"></script>
      <script src="./js/editLecture.js"></script>

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