<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">Список авторов</a></span>

    </div>

    <form method="post">
        <input type="text" name="trackName" placeholder="Track name" />
        <input type="text" name="annotation" placeholder="Track annotation" />
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">add</button>
    </form>
    <div>Список треков</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter}">
        <button type="submit">Найти</button>
    </form>

    <#list tracks as track>
    <div>
        <b>${track.id}</b>
        <span>${track.trackName}</span>
        <strong>${track.authorName}</strong>
    </div>
    <#else>
    No tracks
    </#list>
</@c.page>