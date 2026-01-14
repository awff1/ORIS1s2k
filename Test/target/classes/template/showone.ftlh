<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Товар</title>
    <style>
        * {margin: 0; padding: 0; box-sizing: border-box;}
        body { width: 100%; height: 100vh; display: flex; flex-direction: column; align-items: center; justify-content: center; gap: 20px; font-family: Arial, sans-serif; padding: 20px;}
        form { max-width: 500px; width: 70%; display: flex; flex-direction: column; }
        label { display: block; margin: 10px 0 5px; }
        input[type=text], input[type=number] { width: 100%; padding: 5px; }
        input[type=submit] { margin: 25px auto 0; padding: 8px 16px; }
        a { color: #0066cc; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<#if product??>
    <h1>Товар</h1>

    <form action="./showone" method="post">
        <input type="hidden" name="id" value="${product.id}" />

        <label for="name">Название:</label>
        <input type="text" name="name" id="name" value="${product.name}" required/>

        <label for="description">Описание:</label>
        <input type="text" name="description" id="description" value="${product.description}" required/>

        <label for="price">Цена:</label>
        <input type="number" name="price" id="price" step="0.01" value="${product.price}" required/>

        <label for="quantity">Количество:</label>
        <input type="number" name="quantity" id="quantity" value="${product.quantity}" required/>

        <label for="manufacturer">Производитель:</label>
        <input type="text" name="manufacturer" id="manufacturer" value="${product.manufacturer}" required/>

        <input type="submit" value="Сохранить изменения"/>
    </form>

    <p><a href="./show">Назад к списку товаров</a></p>

<#else>
    Продукта с таким ID не существует
</#if>
</body>
</html>