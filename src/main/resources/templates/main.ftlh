<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by first word in track name">
                <button class="btn btn-primary mt-2" type="submit">Search</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary mt-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Track
    </a>
    <div class="collapse mt-2" id="collapseExample">
        <div class="form-group" >
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



    <#list tracks as track>
        <div>
            <b>${track.id}</b>
            <span>${track.trackName}</span>
            <strong>${track.authorName}</strong>
            <div>
                <#if track.fileName??>
                    <img src="/img/${track.fileName}">
                </#if>
            </div>
        </div>
    <#else>
        No tracks
    </#list>
</@c.page>