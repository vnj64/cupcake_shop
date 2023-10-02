<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои ключи</h1>
    <hr>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/product/create" method="post" enctype="multipart/form-data">
               <div class="text-field">
                   <label for="productName" class="product__label"><b>Название игры</b></label>
                   <input type="text" required class="product-field__input" id="productName" name="title" oninput="checkTitleLength()">
                   <div id="titleError" style="color: red; display: none;">Название игры не может быть более 20 символов.</div>
               </div>
               <script>
                   function checkTitleLength() {
                       var titleInput = document.getElementById("productName");
                       var titleError = document.getElementById("titleError");
                       if (titleInput.value.length > 30) {
                           titleInput.setCustomValidity("Название игры не может быть более 20 символов.");
                           titleError.style.display = "block";
                       } else {
                           titleInput.setCustomValidity("");
                           titleError.style.display = "none";
                       }
                   }

                   window.addEventListener("keydown", function (e) {
                       if (e.key === "Enter") {
                           e.preventDefault();
                           checkTitleLength();
                       }
                   });
               </script>
                <div class="form-group" style="position: relative;">
                    <label for="price"><b>Цена ключа</b></label>
                    <div class="input-group mb-3">
                        <input type="number" required class="price-input" id="price" name="price" min="0" onkeydown="handleArrowKeys(event)">
                        <span class="input-group-text">₽</span>
                    </div>
                </div>
            <div class="form-group col-md-2">
                <label for="searchArea"><b>Площадка</b></label>
                <select id="searchArea" required name="area" class="form-control">
                    <option value="Steam">Steam</option>
                    <option value="Origin">Origin</option>
                    <option value="EpicGames">EpicGames</option>
                    <option value="GOG">GOG</option>
                    <option value="Gamersgate">Gamersgate</option>
                    <option value="Battle.net">Battle.net</option>
                </select>
            </div>
            <div class="form-group">
                <label for="description"><b>Описание игры</b></label>
                <textarea class="decription__input" id="description" name="description" rows="4" oninput="checkDescriptionLength()"></textarea>
                <div id="descriptionError" style="color: red; display: none;">Описание игры не может превышать 300 символов.</div>
            </div>
            <script>
                function checkDescriptionLength() {
                    var descriptionInput = document.getElementById("description");
                    var descriptionError = document.getElementById("descriptionError");
                    if (descriptionInput.value.length > 300) {
                        descriptionInput.setCustomValidity("Описание игры не может превышать 300 символов.");
                        descriptionError.style.display = "block";
                    } else {
                        descriptionInput.setCustomValidity("");
                        descriptionError.style.display = "none";
                    }
                }

                // Prevent form submission on Enter key press
                window.addEventListener("keydown", function (e) {
                    if (e.key === "Enter") {
                        e.preventDefault();
                        checkDescriptionLength();
                    }
                });
            </script>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </form>
    </div>
    <div class="card-columns">
        <#list products as product>
            <a href="/product/${product.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">
                    <div class="card-top">
                        <form action="/product/delete/${product.id}" method="POST">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить
                            </button>
                        </form>
                    </div>

                    <div class="card-body">
                        <h5 class="card-title">${product.title}</h5>
                        <p class="card-text">${product.price} ₽</p>
                        <p class="card-key">${product.steamKey}</p>
                    </div>
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