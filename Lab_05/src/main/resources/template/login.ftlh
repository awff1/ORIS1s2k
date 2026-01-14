<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Логин</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        .body { font-family: Arial, sans-serif; background-color: #f4f6f8; display: flex; flex-direction: column; justify-content: center; align-items: center; height: 100vh; }
        .title { text-align: center; margin-bottom: 20px; color: #333; }
        .div-error { color: red; margin-bottom: 15px; text-align: center; }
        .form { background-color: #fff; padding: 30px 25px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); display: flex; flex-direction: column; width: 320px; }
        .form-div { margin-bottom: 15px; display: flex; flex-direction: column; }
        .form-label { margin-bottom: 5px; font-weight: bold; color: #555; }
        .form-input { padding: 8px 10px; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; }
        .form-input--submit { background-color: #007bff; border: none; color: white; font-size: 16px; border-radius: 4px; padding: 10px 0; cursor: pointer; transition: 0.3s; }
        .form-input--submit:hover { background-color: #0056b3; }
        .link { text-align: center; margin-top: 10px; color: #007bff; text-decoration: none; }
        .link:hover { text-decoration: underline; }
    </style>
</head>
<body class="body">

<h1 class="title">Вход</h1>

<div class="div-error">${errormessage!}</div>

<form class="form" action="/lab_05/usercheck" method="post">
    <div class="form-div">
        <label class="form-label">Имя пользователя</label>
        <input class="form-input form-input--text" type="text" name="username" required>
    </div>

    <div class="form-div">
        <label class="form-label">Пароль</label>
        <input class="form-input form-input--password" type="password" name="password" required>
    </div>

    <input class="form-input form-input--submit" type="submit" value="Вход">
</form>

<a class="link" href="/lab_05/register">Регистрация</a>

</body>
</html>