<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Деталі кафедри</title>
</head>
<body>
<h1>Деталі кафедри</h1>
<div>
    <strong>Назва:</strong> ${department.name}<br>
    <strong>Заголовок:</strong> ${department.title}<br>
    <strong>Телефон:</strong> ${department.phone}<br>
    <strong>Аудиторія:</strong> ${department.classroom}
</div>
<div>
    <a href="/departments">Повернутись до списку кафедр</a>
</div>
</body>
</html>
