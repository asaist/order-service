<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by first word in track name">
                <button class="btn btn-primary mt-2" type="submit">Search</button>
            </form>
        </div>
        <button class="btn btn-icon btn-sm mx-2" type="button"  data-toggle="modal" data-target="#addTrackModal"><i class="fas fa-plus"></i></button>
    </div>
    <a class="btn btn-primary mt-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Track
    </a>
    <div class="collapse mt-2" id="collapseExample">
        <div class="form-group">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group mt-2">
                    <input type="text" class="form-control" name="trackName" placeholder="Track name" />
                </div>
                <div class="form-group mt-2">
                    <input type="text" class="form-control" name="annotation" placeholder="Track annotation" />
                </div>
<#--                <div class="form-group" mt-2>-->
<#--                    <div class="customFile">-->
<#--                        <input type="file" name="file" id="customFile">-->
<#--                        <label class="custom-file-label" for="customFile">Choose file</label>-->
<#--                    </div>-->
<#--                </div>-->
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
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
        <th>trackName</th>
        <th>authorName</th>
    </tr>
    </thead>
    <tbody>
    <#list tracks as track>
        <tr>
            <td>${track.id}</td>
            <td>${track.trackName}</td>
            <td>${track.authorName}</td>
<#--            <div>-->
<#--                <#if track.fileName??>-->
<#--                    <img src="/img/${track.fileName}">-->
<#--                </#if>-->
<#--            </div>-->
        </tr>
    <#else>
        No tracks
    </#list>
    </tbody>
    </table>
</@c.page>