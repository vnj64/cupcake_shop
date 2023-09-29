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
                <a href="/profile"><img src="./profile-round-1342-svgrepo-com (1).svg" class="main__header__profile"></img></a>
            <#else>
            <a href="/login">
                <button class="main__header__login" type="button"
                        onclick="window.location.href = '/login'">
                    Войти
                </button>
            </a>
            </#if>
        </header>
        <main class="products">
            <a href="/product/${product.id}">
                <div class="product">

                    <h5 class="product__title">ТАЙТЛ</h5>
                    <!-- ${product.title} -->
                    <p class="product__text">ЦЕНА Р</p>
                    <!-- ${product.price} ₽ -->
                    <div class="product__footer">ГОРОД</div>
                    <!-- ${product.city} -->
                </div>
            </a>
            <a href="/product/${product.id}">
                <div class="product">
                    <#list products as product>
                        <h5 class="product__title">ТАЙТЛ</h5>
                        <!-- ${product.title} -->
                        <p class="product__text">ЦЕНА Р</p>
                        <!-- ${product.price} ₽ -->
                        <div class="product__footer">ГОРОД</div>
                        <!-- ${product.city} -->
                    </#list>
                </div>
            </a>
            <a href="/product/${product.id}">
                <div class="product">

                    <h5 class="product__title">ТАЙТЛ</h5>
                    <!-- ${product.title} -->
                    <p class="product__text">ЦЕНА Р</p>
                    <!-- ${product.price} ₽ -->
                    <div class="product__footer">ГОРОД</div>
                    <!-- ${product.city} -->
                </div>
            </a>
            <a href="/product/${product.id}">
                <div class="product">

                    <h5 class="product__title">ТАЙТЛ</h5>
                    <!-- ${product.title} -->
                    <p class="product__text">ЦЕНА Р</p>
                    <!-- ${product.price} ₽ -->
                    <div class="product__footer">ГОРОД</div>
                    <!-- ${product.city} -->
                </div>
            </a>
            <a href="/product/${product.id}">
                <div class="product">

                    <h5 class="product__title">ТАЙТЛ</h5>
                    <!-- ${product.title} -->
                    <p class="product__text">ЦЕНА Р</p>
                    <!-- ${product.price} ₽ -->
                    <div class="product__footer">ГОРОД</div>
                    <!-- ${product.city} -->
                </div>
            </a>
        </main>
    </div>
    </body>

    </html>
</#macro>
