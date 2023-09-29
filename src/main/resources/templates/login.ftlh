<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center"><b>Авторизация</b></h1>
    <hr>
    <form action="/login" method="post">
        <div class="text-field">
            <label for="email" class="email__label">Электронная почта</label>
            <input placeholder="e-mail..." type="email" class="login__input" id="email" name="username">
        </div>

        <div class="text-field">
            <label for="password" class="password__label">Пароль</label>
            <input placeholder="password..." type="password" class="password__input" id="password" name="password">
        </div>
        <#if RequestParameters.error??>
            <div>
                <p>Неверная почта или пароль!</p>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="login-button">Войти</button>
    </form>
    <hr>
    <div style="margin-top: 10px">
        <h2>Нет аккаунта? <a href="/registration">Зарегистрируйся!</a></h2>
    </div>
</@t.template>