<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Тест аутентификации</title>
    <meta name="description" content="Тест аутентификации">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        .body { font-family: Arial, sans-serif; background-color: #f4f6f8; display: flex; flex-direction: column; justify-content: start; align-items: center; height: 100vh; }
        .title { text-align: center; margin-bottom: 20px; color: #333; }
        .div-style { margin-top: 70px; }
        .username { color: #007bff; font-weight: bold; }
    </style>
</head>
<body class="body">

<div class="div-style">
    <h1 class="title">Поздравляем <span class="username">${user!}</span>, вы свой!</h1>
</div>

</body>
</html>