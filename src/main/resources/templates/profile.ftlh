<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 class="lk-title">Личный кабинет</h1>
    <hr>
    <br>
    <div class="main__profile__text">
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Электронная почта: <b>${user.email}</b>
        <br>
        Номер телефона: <b>${user.phoneNumber}</b>
        <br>
        <div class="admin-buttons">
            <br>
            <#if user.isAdmin()>
                <a class="btn btn-dark mb-3" style="text-align: center; width: 30%"
                   href="/admin">
                    Панель администратора
                </a><br>
            </#if>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 20%"
               href="/my/products">
                Мои игры
            </a>
        </div>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" style="text-align: center; width: 50%" class="btn btn-danger mb-2">
                    Выйти из аккаунта
                </button>
            </form>
    </div>
</@t.template>