<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Добавить новый товар</title>
    <style>
        * {margin: 0; padding: 0; box-sizing: border-box;}
        body { width: 100%; height: 100vh; display: flex; flex-direction: column; align-items: center; justify-content: center; gap: 20px; font-family: Arial, sans-serif; padding: 20px; }
        form { display: flex; flex-direction: column; width: 70%; max-width: 500px; }
        div { width: 100%; }
        label { display: block; margin: 10px 0 5px; }
        input[type=text], input[type=number] { width: 100%; padding: 5px; }
        input[type=submit] { margin: 25px auto 0; padding: 8px 16px; }
        a { display: block; margin: 10px 0; color: #0066cc; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<h1>Добавить новый товар</h1>

<form action="./add" method="post">
    <div>
        <label for="name">Название:</label>
        <input type="text" name="name" id="name" value="${product?if_exists?then(product.name,'')}" required/>
    </div>

    <div>
        <label for="description">Описание:</label>
        <input type="text" name="description" id="description" value="${product?if_exists?then(product.description,'')}" required/>
    </div>

    <div>
        <label for="price">Цена:</label>
        <input type="number" name="price" id="price" step="0.01" value="${product?if_exists?then(product.price,'')}" required/>
    </div>

    <div>
        <label for="quantity">Количество:</label>
        <input type="number" name="quantity" id="quantity" value="${product?if_exists?then(product.quantity,'')}" required/>
    </div>

    <div>
        <label for="manufacturer">Производитель:</label>
        <input type="text" name="manufacturer" id="manufacturer" value="${product?if_exists?then(product.manufacturer,'')}" required/>
    </div>

    <input type="submit" value="Добавить"/>
</form>

<p><a href="./show">Назад к списку товаров</a></p>
</body>
</html>