
<#macro template user>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/static/css/style.css">
        <title>steamkey</title>
    </head>

    <body>
    <div class="main__wrapper">
        <header class="main__header">
            <a class="main__header__link" href="/">SteamKey</a>
            <#if user.email??>
                <a href="/profile"><img alt="Профиль" src="/static/images/white_profile.png" class="main__header__profile"></img></a>
            <#else>
                <a href="/login">
                    <button class="main__header__login" type="button"
                            onclick="window.location.href = '/login'">
                        Войти
                    </button>
                </a>
            </#if>
        </header>
        <div class="products">
            <#nested>
        </div>
    </div>
    </body>

    </html>
</#macro>