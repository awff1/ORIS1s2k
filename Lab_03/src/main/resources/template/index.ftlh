<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Тест Freemarker</title>
    <meta name="description" content="Тест Freemarker">
    <link rel="stylesheet" href="/lab_03/css/styles.css">
</head>
<body>
<h1>Тест Freemarker</h1>
<form method='post' action='/lab_03/test'>
    <div>
        <label>Фамилия:</label>
        <input type='text' name='surname' value='${surname}'></input>
    </div>
    <div>
        <label>Имя:</label>
        <input type='text' name='name' value='${name}'></input>
    </div>
    <div>
        <label>Роль:</label>
        <select name="role">
            <option value="1">Студент</option>
            <option value="2">Преподаватель</option>
            <option value="3">Сущность</option>
        </select>
    </div>
    <div class="div_style">
        <label>Принимаю условия соглашения</label>
        <input type="checkbox" name="commit">
    </div>
    <fieldset>
        <div>
            <label>Плохое</label>
            <input type="radio" name="mood" value="bad" checked>
        </div>
        <div>
            <label>Хорошее</label>
            <input type="radio" name="mood" value="good">
        </div>
        <div>
            <label>Пойдёт</label>
            <input type="radio" name="mood" value="ok">
        </div>
    </fieldset>
    <div>
        <input type='submit' value="Отправить">
    </div>
</form>
</body>