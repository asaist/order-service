<#--<#include  "security.ftlh">-->
<#import "login.ftlh" as l>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Track</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/scheme">Схема</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/table_track">Доступные треки</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/main">Tracks</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/lectures">Lecture List</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/user">User list</a>
                </li>
            </ul>
<#--            <div class="navbar-text">${name}</div>-->
            <@l.logout />
        </div>
    </div>
</nav>