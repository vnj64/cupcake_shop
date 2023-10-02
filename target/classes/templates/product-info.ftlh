<#import "blocks/template.ftlh" as t>
<@t.template user>
    <#if product.id == recordCount>
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <a class="carousel-control-prev" href="/product/${product.id-1}" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous ←</span>
            </a>
        </div>
    <#elseif product.id == 1>
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <a class="carousel-control-prev" href="/product/${product.id+1}" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Next →</span>
            </a>
        </div>
    <#else>
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <a class="carousel-control-prev" href="/product/${product.id-1}" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous ←</span>
            </a>
            <a class="carousel-control-prev" href="/product/${product.id+1}" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Next →</span>
            </a>
        </div>
    </#if>
    <hr>
    <p><h1 class="card-title">${product.title} | ${product.price} ₽</h1></p>
    <p class="card-text">${product.description}</p>
    <p class="card-key">Ключ: <b>${product.steamKey}</b></p>
    <p class="phone__number">Telegram: <b>${authorProduct.phoneNumber}</b></p>

    <hr>
    <p style="text-align: center">
        <a class="profile__link" href="/user/${authorProduct.id}">
            <img src="/static/images/avatar.png" alt="mdo" width="32" height="32" class="rounded-circle"> ${authorProduct.name}
        </a>
    </p>

</@t.template>