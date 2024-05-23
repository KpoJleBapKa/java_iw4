<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Список Кафедр</title>
</head>
<body>
<h1>Список Кафедр</h1>
<!-- Відображення списку кафедр -->
<ul>
    <#list departments as department>
        <li>
            Кафедра: <a href="/departments/${department.id}">${department.name}</a> (Заголовок: ${department.title}), Телефон: ${department.phone}, Аудиторія: ${department.classroom}
            <form action="/departments/delete/${department.id}" method="post" style="display:inline;">
                <input type="submit" value="Видалити">
            </form>
        </li>
    </#list>
</ul>
<!-- Форма для додавання нової кафедри -->
<h2>Додати нову кафедру</h2>
<form action="/departments/create" method="post" accept-charset="UTF-8">
    <div>
        <label>Назва кафедри:</label>
        <input type="text" name="name" required>
    </div>
    <div>
        <label>Заголовок:</label>
        <input type="text" name="title" required>
    </div>
    <div>
        <label>Телефон:</label>
        <input type="text" name="phone" required>
    </div>
    <div>
        <label>Аудиторія:</label>
        <input type="text" name="classroom" required>
    </div>
    <button type="submit">Додати</button>
</form>
</body>
</html>
