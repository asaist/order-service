<%@ page language="java"
         contentType="text/html"
         pageEncoding="UTF-8"
%>
<%
String JWT ="";
// Для проверки включения JWT в заголовок запроса. Исключаем передачу JWT через сообщение. Запуск автономно из браузера. Проверка перехода на страницу main.jsp с токеном.
//  JWT = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJtQnFVd2k5NVMtQk9PdkdRbVVvaDF2OFoyU3ZRTlZ3NWVUS2VkTzE4WVpNIn0.eyJleHAiOjE2OTQ2Nzc2NjQsImlhdCI6MTY5NDY3NzM2NCwiYXV0aF90aW1lIjoxNjk0Njc3MTIyLCJqdGkiOiI3MjQzYmYxZi05ODVlLTRlN2YtODlkZi0xMzk0MzE2ZDA0YWUiLCJpc3MiOiJodHRwczovL3Nzby5zYW1zbXUucnUvYXV0aC9yZWFsbXMvU0FNR01VIiwiYXVkIjpbInJlc291cmNlTWFuYWdlbWVudCIsInRwb3J0YWwiLCJzYi1vbmxpbmUtcGF5bWVudC1zZXJ2aWNlIiwibWFya2V0cGxhY2UiLCJ1c2VyLXByb2ZpbGUiLCJwcm9jdG9yaW5nX2F1dGgiLCJ1c2VycyIsInJlc291cmNlLW1hbmFnZW1lbnQtamF4cnMtc2VydmVyIiwidXNlcnMtamF4cnMtc2VydmVyIiwiZG9jdW1lbnQtcHJvY2Vzc2luZy1qYXhycy1zZXJ2ZXIiLCJ0dXNlci1wcm9maWxlIiwicG9ydGFsIiwiYWNjb3VudCJdLCJzdWIiOiI4ZTQ5N2Y1ZS1mYTcxLTQyNDYtODYwOS0zMWVjZGNmYmU5NjIiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJwZXJzb25pbmZvIiwic2Vzc2lvbl9zdGF0ZSI6IjdiNjI1YWIyLTdlYTMtNGIyMS1iNGVhLTNjNDBiOTZmZmUyNCIsImFjciI6IjAiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cHM6Ly9pYW0uc2Ftc211LnJ1L1BlcnNvbkluZm8vIiwiaHR0cDovL2xvY2FsaG9zdDo4MDgxL1BlcnNvbkluZm8iXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJyZXNvdXJjZU1hbmFnZW1lbnQiOnsicm9sZXMiOlsicmVzb3VyY2UtbWFuYWdlbWVudC1jb25uZWN0IiwicmVzb3VyY2UtbWFuYWdlbWVudC1zdXBlciJdfSwidHBvcnRhbCI6eyJyb2xlcyI6WyJQZXJzb24iXX0sInNiLW9ubGluZS1wYXltZW50LXNlcnZpY2UiOnsicm9sZXMiOlsic2Itb25saW5lLXBheW1lbnQtdmlldyJdfSwibWFya2V0cGxhY2UiOnsicm9sZXMiOlsiU3R1ZGVudCJdfSwidXNlci1wcm9maWxlIjp7InJvbGVzIjpbIlByb2ZpbGVfUGVyc29uIl19LCJwcm9jdG9yaW5nX2F1dGgiOnsicm9sZXMiOlsicHJvY3RvcmluZ19jbGllbnQiXX0sInVzZXJzIjp7InJvbGVzIjpbInVzZXJzLWNsaWVudC1kYXRhZ3JhbS1tb2RpZmllciIsInVzZXJzLXByb2N0b3IiLCJ1c2Vycy1jbGllbnQiLCJ1c2Vycy1jbGllbnQtcmVhZC1jaGFuZ2VzIiwiY29tbW9uLWRvYy13cml0ZSIsInBlcnNvbnMtY2xpZW50LXJlYWQiLCJ1c2Vycy1jbGllbnQtcmVhZCIsInVzZXJzLWNsaWVudC1wcHMiLCJjb21tb24tZG9jLXJlYWQiLCJ1c2Vycy1jbGllbnQtd3JpdGUiLCJ1c2Vycy1jbGllbnQtd3JpdGUtY2hhbmdlcyIsInVzZXJzLWNsaWVudC12YWxpZGF0b3IiLCJwZXJzb24tcHJvZmlsZS1yZWFkIl19LCJyZXNvdXJjZS1tYW5hZ2VtZW50LWpheHJzLXNlcnZlciI6eyJyb2xlcyI6WyJyZXNvdXJjZS1tYW5hZ2VtZW50LWpheHJzLXNlcnZlci1yZWFkLWNsaWVudCIsInJlc291cmNlLW1hbmFnZW1lbnQtamF4cnMtc2VydmVyLWNsaWVudCIsInJlc291cmNlLW1hbmFnZW1lbnQtamF4cnMtc2VydmVyLXdyaXRlLWNsaWVudCJdfSwidXNlcnMtamF4cnMtc2VydmVyIjp7InJvbGVzIjpbInVzZXJzLWpheHJzLXNlcnZlci1yZWFkLWNsaWVudCIsInVzZXJzLWpheHJzLXNlcnZlci1kYXRhZ3JhbS1tb2RpZmllciIsInVzZXJzLWpheHJzLXNlcnZlci1yZWFkLWNoYW5nZXMiLCJ1c2Vycy1qYXhycy1zZXJ2ZXItd3JpdGUtY2hhbmdlcyIsInVzZXJzLWpheHJzLXNlcnZlci12YWxpZGF0b3IiLCJ1c2Vycy1qYXhycy1zZXJ2ZXItd3JpdGUtY2xpZW50IiwidXNlcnMtamF4cnMtc2VydmVyLWNsaWVudCIsInVzZXJzLWpheHJzLXNlcnZlci1wcHMiXX0sImRvY3VtZW50LXByb2Nlc3NpbmctamF4cnMtc2VydmVyIjp7InJvbGVzIjpbImRvY3VtZW50LXByb2Nlc3NpbmctY2xpZW50LWRhdGFncmFtLW1vZGlmaWVyIiwidW1hX3Byb3RlY3Rpb24iLCJkb2N1bWVudC1wcm9jZXNzaW5nLWNsaWVudC1yZWFkIiwiZG9jdW1lbnQtcHJvY2Vzc2luZy1jbGllbnQiXX0sInR1c2VyLXByb2ZpbGUiOnsicm9sZXMiOlsiUHJvZmlsZV9QZXJzb24iXX0sInBvcnRhbCI6eyJyb2xlcyI6WyJQZXJzb24iXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfSwicGVyc29uaW5mbyI6eyJyb2xlcyI6WyJwZXJzb24taW5mby1kaXZpc2lvbnMtZWRpdCIsInBlcnNvbi1pbmZvLXN1cnZleS1lbXBsb3llZS1hZG1pbiIsInBlcnNvbi1pbmZvLWZvcm0tZGVzaWduZXIiLCJwZXJzb24taW5mby1zdXJ2ZXktc3R1ZGVudC1hZG1pbiIsInBlcnNvbi1pbmZvLW1lbnUtZWRpdCIsInBlcnNvbi1pbmZvLWV2ZW50LWFkbWluIl19fSwic2NvcGUiOiJvcGVuaWQgY3VzdG9tIHByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYmlydGhkYXRlIjoiMTk1OS0xMS0wMyAwMDowMDowMCIsIm5hbWUiOiLQktC70LDQtNC40LzQuNGAINCa0L7QstGI0L7QsiIsInByZWZlcnJlZF91c2VybmFtZSI6InYuaS5rb3ZzaG92IiwibG9jYWxlIjoicnUiLCJtaWRkbGVfbmFtZSI6ItCY0LvQu9Cw0YDQuNC-0L3QvtCy0LjRhyIsImdpdmVuX25hbWUiOiLQktC70LDQtNC40LzQuNGAIiwiZmFtaWx5X25hbWUiOiLQmtC-0LLRiNC-0LIiLCJzbmlscyI6IjAyNC0wNzUtNjYzIDMxIiwiZW1haWwiOiJ2Lmkua292c2hvdkBzYW1zbXUucnUifQ.dDvYU680-phYYs77RvYEqQGXBkBrNBIWCm-kljqpkAQ4jCStn1vredcfun-PtcBnbjPKr3hl03Rbx_c3GRAK35JwFpHcxd5sbrKstgtA9Y_mxJOVC53PSnk2kq7M0dAyTyWvtZlfEuV0deCWmG1L_PqXLA5dCbXeq7-7P3A8RXPR1itXmIfgyS5TpUchjkiN9RMFb_Pmr5rj7vzYIolUkZxlkV-XgjcFiehIx2b9isoGDJbhlgMOSXJlE5PmMsY3heIDUt4zsTNiDouPzeC6bcRhSvUKPRnMNEjl9no5F8XuKR_osN7kxrMXOP70Yh88wu5a7IS8EIgIFbH0jO6iUg";
%>

<html>
<head>
  <title>Опрос</title>
  <meta charset="utf-8">
  <meta http-equiv="cache-control" content="no-cache">
<#--  <script src="https://dev.track.samsmu.ru/static/js/"></script>-->
<#--  <script src="https://dev.track.samsmu.ru/static/js/axios/axios.js"></script>-->
  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
  <script>
    var jwtReceived = false;
    var axiosConfig  = {
            headers: {
 //           'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': 'Bearer ',
          }
        }
    ////////////////////////////////////////////////////////////////////////
    //НЕ РАБОТАЕТ ОБРАЩЕНИЕ ИЗ IFRAME ВО ВНЕШНЕЕ ПРИЛОЖЕНИЕ К LOCALSTORAGE//
    ////////////////////////////////////////////////////////////////////////
    // Получаем доступ к объекту window родительского окна
    // const parentWindow = window.parent;
    //
    // // Получаем значение параметра из localStorage родительского окна
    // const authorizationTest = parentWindow.localStorage.getItem('Authorization-Test');
    // console.log("authorizationTest from parent: " + authorizationTest);
    ////////////////////////////////////////////////////////////////////////
    //НЕ РАБОТАЕТ ОБРАЩЕНИЕ ИЗ IFRAME ВО ВНЕШНЕЕ ПРИЛОЖЕНИЕ К LOCALSTORAGE//
    ////////////////////////////////////////////////////////////////////////

    window.addEventListener('message', function(event) {
      axiosConfig.headers.Authorization = event.data;

      jwtReceived = true;
     console.log("appTest JWT -> " + axiosConfig.headers.Authorization);
     localStorage.setItem('authorization', "Bearer " + axiosConfig.headers.Authorization);
     console.log("localStorage JWT -> " + localStorage.authorization);
    });

   var cnt = 0;
  function checkJWT() {
   console.log("" + cnt + ". checkJWT -> " + axiosConfig.headers.Authorization);
    if (!jwtReceived) {
      cnt++;
      console.log("1");
      if (cnt < 10)
        setTimeout(checkJWT, 500);
      else
        document.location.href = "msg.html";
        // document.location.href = "/msg";
    } else {
      /* Вариант 1 - без перезагрузки страницы
            console.log("Before GET -> " + axiosConfig.headers.Authorization);

            axios.get('main.jsp',axiosConfig).then(resp => {
              console.log(resp.data);
      //        document.location.href = "main.jsp";
             document.write(resp.data);
            });
       */
      // Вариант 2 - переадресация c добавлением токена в заголовок
        console.log("2");
      // pgLoadToken('scheme');
      pgLoadToken('/public/home');
    }
  }

    function pgLoadToken(url) {
      var xhr = new XMLHttpRequest();
      console.log(url);
      console.log("Bearer " + axiosConfig.headers.Authorization);
      xhr.open('GET', url);
      xhr.onreadystatechange = handler;
      xhr.responseType = 'blob';
      xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
      xhr.setRequestHeader("Access-Control-Allow-Headers", "*");
      xhr.setRequestHeader("Authorization", "Bearer " + axiosConfig.headers.Authorization);
      xhr.send();
    }
    function handler() {
      if (this.readyState === this.DONE) {
 //       console.log("handler status = " + this.status);
        if (this.status === 200) {
          // this.response is a Blob, because we set responseType above
          var data_url = URL.createObjectURL(this.response);
//          console.log("handler data_url = " + data_url);

          document.location.href = data_url;
        } else {
 //         console.log("handler url = main.jsp");
 //          document.location.href = "scheme.ftlh";
          document.location.href = "https://dev.track.samsmu.ru/public/home";
        }
      }
    }

  function afterLoad() {
    if (!jwtReceived) {
  // Для проверки включения JWT в заголовок запроса. Исключаем передачу JWT через сообщение. Запуск автономно из браузера. Проверка перехода на страницу main.jsp с токеном.
  //      axiosConfig.headers.Authorization = '<%=JWT%>';
  //      jwtReceived = true;
        console.log("afterLoad");
        console.log("Bearer " + axiosConfig.headers.Authorization);
      setTimeout(checkJWT, 500);
    } else checkJWT();
  }

  </script>
</head>
<body onload="afterLoad()">
Стартовая страница. Получение токена. Максимальное время ожидания 5 сек.
</body>
</html>