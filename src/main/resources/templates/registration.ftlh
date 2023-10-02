<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Регистрация</h1>
    <hr>
    <form action="/registration" method="post">
        <div class="form-group">
            <label for="name" class="email__label">Имя пользователя</label>
            <input type="text" class="login__input" required id="name" name="name">
        </div>
        <div class="form-group">
            <label for="email" class="email__label">Электронная почта</label>
            <input type="email" class="login__input" id="email" required name="email">
        </div>
        <div class="form-group">
            <label for="phoneNumber" class="email__label">Номер телефона</label>
            <input type="text" class="login__input" id="phoneNumber" required name="phoneNumber">
        </div>
        <div class="form-group">
            <label for="password" class="password__label">Пароль</label>
            <input type="password" class="password__input" id="password" required name="password">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="login-button">Зарегистрироваться</button>
    </form>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
</@t.template>