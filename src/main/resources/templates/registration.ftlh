<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
добавить нового пользователя
${message?ifExists}
<@l.login "/registration" />
</@c.page>