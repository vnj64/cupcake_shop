<#import "blocks/template.ftlh" as t>
<@t.template user>
    <div style="display: flex; margin-bottom: 100px; justify-content: space-between; width: 90vw">

    <form method="get" action="/">
        <div style="display: flex">
            <div style="text-align: center">Поиск игр</div>
            <div class="form-group col-md-2">
                <label for="searchArea">Площадка</label>
                <select id="searchArea" required name="area" class="form-control">
                    <option value="Steam">Steam</option>
                    <option value="Origin">Origin</option>
                    <option value="EpicGames">EpicGames</option>
                    <option value="GOG">GOG</option>
                    <option value="Gamersgate">Gamersgate</option>
                    <option value="Battle.net">Battle.net</option>
                </select>
            </div>
        </div>
    </form>
        <div class="box">
            <div class="container-1">
                <label for="searchWord">Поиск по ключам</label>
                <input class="searchWord" type="text" name="searchWord" id="searchWord" placeholder="Search..."/>
            </div>
            <button type="submit" class="login-button">Поиск</button>
        </div>
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