<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div style="display: flex; margin-bottom: 100px; justify-content: space-between; width: 90vw">
        <form method="get" action="/">
            <div class="container-1">
                <label for="searchWord"><b>Поиск по играм</b></label>
                <input type="text" name="searchWord" class="searchWord" id="searchWord">
                <button type="submit" class="login-button">Поиск</button>
            </div>
        </form>
    </div>



    <div class="products">
        <#list products as product>
            <a href="/product/${product.id}" class="product-link">
                <div class="product">
                    <h5 class="card-title">${product.title}</h5>
                    <p class="card-text">${product.price} ₽</p>
                    <p class="card-key">${product.steamKey}</p>
                    <div class="card-footer">
                        ${product.area}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>