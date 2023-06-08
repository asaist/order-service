<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/lectures" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by lecture name">
                <button class="btn btn-primary mt-2" type="submit">Search</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary mt-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Lecture
    </a>
    <div class="collapse mt-2" id="collapseExample">
        <div class="form-group" >
            <form method="post" enctype="multipart/form-data">
                <div class="form-group mt-2">
                    <input type="text" class="form-control" name="lectureName" placeholder="Lecture name" />
                </div>
                <div class="form-group mt-2">
                    <input type="text" class="form-control" name="annotation" placeholder="Lecture annotation" />
                </div>
                <div class="form-group mt-2">
                    <input type="text" class="form-control" name="videoReference" placeholder="Lecture videoReference" />
                </div>
                <select class="form-select mt-2" data-live-search="true" name="track">
                    <option selected>Choose track</option>
                    <#list tracks as track>
                        <option value="${track.id}">${track.trackName}</option>
                    </#list>
                </select>
<#--                <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                <div class="form-group mt-2">
                    <button type="submit" class="btn btn-primary ml-2">Add</button>
                </div>
            </form>
        </div>
    </div>

    <table class="table">
    <thead>
    <tr>
        <th>id</th>
        <th>lectureName</th>
        <th>authorName</th>
        <th>annotation</th>
        <th>videoReference</th>
    </tr>
    </thead>
    <tbody>
    <#list lectures as lecture>
        <tr>
            <td>${lecture.id}</td>
            <td>${lecture.lectureName}</td>
            <td>${lecture.authorName}</td>
            <td>${lecture.annotation}</td>
            <td>${lecture.videoReference}</td>
        </tr>
    <#else>
        No lectures
    </#list>
</@c.page>